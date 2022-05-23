package com.bookstore.service.impl;

import com.bookstore.domain.*;
import com.bookstore.enums.ErrorCodeEnum;
import com.bookstore.exception.CustomRuntimeException;
import com.bookstore.adapter.BookAdapter;
import com.bookstore.adapter.OrderAdapter;
import com.bookstore.adapter.StockAdapter;
import com.bookstore.domain.OrderItemDto;
import com.bookstore.domain.ResponseWrapper;
import com.bookstore.mapper.OrderDtoMapper;
import com.bookstore.service.OrderService;
import com.bookstore.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderAdapter adapter;
    private final BookAdapter bookAdapter;
    private final StockAdapter stockAdapter;
    private final OrderDtoMapper mapper;
    private final StringRedisTemplate redisTemplate;


    @TimeToLive
    @Override
    public ResponseWrapper<OrderDto> createOrder(String customerId, List<OrderItemDto> request) {
        if (request.isEmpty())
            throw new CustomRuntimeException(ErrorCodeEnum.FIELD_VALIDATION_ERROR);

        OrderDomain orderDomain = new OrderDomain(customerId);
        //race condition prevent simultaneously decrease stock count
        lock("stockCount");
        for (OrderItemDto order : request) {
            Optional<BookDomain> book = bookAdapter.getBookById(order.getBookId());
            book.orElseThrow(() -> new CustomRuntimeException(ErrorCodeEnum.CONTENT_NOT_FOUND));

            Optional<StockDomain> stockOfBook = stockAdapter.getStockOfBook(order.getBookId());
            if (!stockOfBook.isPresent() || order.getQuantity() > stockOfBook.get().getStock())
                throw new CustomRuntimeException(ErrorCodeEnum.STOCK_ERROR);

            StockDomain stockDomain = stockOfBook.get();
            stockDomain.updateStockOfBook(order.getQuantity());

            orderDomain.addItem(book.get(), order.getQuantity());
            stockAdapter.updateStockOfBook(stockDomain);
        }

        OrderDomain saveOrder = adapter.saveOrder(orderDomain);
        unlock("stockCount");
        OrderDto orderDto = mapper.toDTO(saveOrder);
        return ResponseUtil.buildSuccess(orderDto);
    }

    @Override
    public ResponseWrapper<List<OrderDto>> getOrdersByCustomerId(String customerId) {
        List<OrderDto> orders = adapter.getOrdersByCustomerId(customerId).stream().map(mapper::toDTO).collect(Collectors.toList());
        return ResponseUtil.buildSuccess(orders);
    }

    @Override
    public ResponseWrapper<OrderDto> getOrdersById(String orderId) {
        Optional<OrderDomain> order = adapter.getOrdersById(orderId);
        order.orElseThrow(() -> new CustomRuntimeException(ErrorCodeEnum.CONTENT_NOT_FOUND));
        OrderDto orderDto = order.map(mapper::toDTO).get();
        return ResponseUtil.buildSuccess(orderDto);
    }

    @Override
    public PageResponse<OrderDto> filterOrdersByDateRange(LocalDate from, LocalDate to, Integer page, Integer size) {
        int newSize = Integer.max(size, 0);
        int newPage = Integer.max(page, 0);
        PageResponse<OrderDomain> pageableOrder = adapter.filterOrdersByDateRange(from, to, newPage, newSize);

        return PageResponse.<OrderDto>builder()
                .totalPages(pageableOrder.getTotalPages())
                .totalContent(pageableOrder.getTotalContent())
                .size(pageableOrder.getSize())
                .page(pageableOrder.getPage())
                .list(mapper.toListDTO(pageableOrder.getList()))
                .build();
    }

    private Boolean lock(String lockKey){
        Long timeOut = redisTemplate.getExpire(lockKey);
        SessionCallback<Boolean> sessionCallback = new SessionCallback<Boolean>() {
            List<Object> exec = null;
            @Override
            @SuppressWarnings("unchecked")
            public Boolean execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                operations.opsForValue().setIfAbsent(lockKey,"lock");
                if(timeOut == null || timeOut == -2) {
                    operations.expire(lockKey, 30, TimeUnit.MILLISECONDS);
                }
                exec = operations.exec();
                if(exec.size() > 0) {
                    return (Boolean) exec.get(0);
                }
                return false;
            }
        };
        return redisTemplate.execute(sessionCallback);
    }

    private void unlock(String lockKey){
        redisTemplate.delete(lockKey);
    }

}
