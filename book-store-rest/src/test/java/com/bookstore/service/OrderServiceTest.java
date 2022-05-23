package com.bookstore.service;

import com.bookstore.adapter.BookAdapter;
import com.bookstore.adapter.OrderAdapter;
import com.bookstore.adapter.StockAdapter;
import com.bookstore.domain.OrderDomain;
import com.bookstore.domain.OrderDto;
import com.bookstore.mapper.OrderDtoMapper;
import com.bookstore.repository.OrderRepository;
import com.bookstore.service.impl.OrderServiceImpl;
import com.bookstore.utils.Utils;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderServiceImpl orderDetailService;

    @Mock
    CustomerService customerService;

    @Mock
    BookService bookService;

    @Mock
    BookAdapter bookAdapter;

    @Mock
    OrderAdapter adapter;

    @Mock
    StockAdapter stockAdapter;

    @Mock
    OrderDtoMapper mapper;

    @Mock
    OrderRepository repository;
    @Mock
    StringRedisTemplate redisTemplate;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


   @Test
    public void createOrderTest() {
        when(bookAdapter.getBookById("1")).thenReturn(Optional.of(Utils.createBookDomain()));
        when(stockAdapter.getStockOfBook("1")).thenReturn(Optional.of(Utils.createStockDomain()));
        when(adapter.saveOrder(any(OrderDomain.class))).thenReturn(Utils.createOrderDomain());
        when(mapper.toDTO(any(OrderDomain.class))).thenReturn(Utils.createOrderDto());
        OrderDto result = orderDetailService.createOrder("5",Utils.createOrderItemDtoList()).getData();
        Assert.assertNotNull(result);
    }

    @Test
    public void getOrdersByIdTest(){
        when(adapter.getOrdersById("2")).thenReturn(Optional.of(Utils.createOrderDomain()));
        when(mapper.toDTO(any(OrderDomain.class))).thenReturn(Utils.createOrderDto());
        OrderDto data = orderDetailService.getOrdersById("2").getData();
        Assert.assertNotNull(data);
    }

    @Test
    public void getOrdersByCustomerIdTest(){
        when(adapter.getOrdersByCustomerId("2")).thenReturn(Lists.newArrayList(Utils.createOrderDomain()));
        List<OrderDto> data = orderDetailService.getOrdersByCustomerId("2").getData();
        Assert.assertNotNull(data);
    }
}


