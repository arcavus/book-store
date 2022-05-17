package com.bookstore.adapter.impl;

import com.bookstore.adapter.OrderAdapter;
import com.bookstore.common.PersistenceAdapter;
import com.bookstore.domain.OrderDomain;
import com.bookstore.domain.PageResponse;
import com.bookstore.entity.OrderEntity;
import com.bookstore.mapper.OrderEntityMapper;
import com.bookstore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class OrderAdapterImpl implements OrderAdapter {

    private final OrderRepository repository;
    private final OrderEntityMapper mapper;

    @Override
    public OrderDomain saveOrder(OrderDomain order) {
        OrderEntity orderEntity = mapper.toEntity(order);
        return mapper.toDomainObject(repository.save(orderEntity));
    }

    @Override
    public List<OrderDomain> getOrdersByCustomerId(String customerId) {
        List<OrderEntity> orderEntityByCustomerId = repository.findOrderEntityByCustomerId(customerId);
        return orderEntityByCustomerId.stream().map(mapper::toDomainObject).collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDomain> getOrdersById(String orderId) {
        return repository.findOrderEntityById(orderId).map(mapper::toDomainObject);
    }

    @Override
    public PageResponse<OrderDomain> filterOrdersByDateRange(LocalDate from, LocalDate to, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<OrderEntity> pageOrderEntity = repository.findOrderEntityByCreatedDateBetween(from, to, pageable);

        return PageResponse.<OrderDomain>builder()
                .list(mapper.toListDomainObject(pageOrderEntity.toList()))
                .page(page)
                .size(pageOrderEntity.getSize())
                .totalContent(pageOrderEntity.getTotalElements())
                .totalPages(pageOrderEntity.getTotalPages())
                .build();
    }

}
