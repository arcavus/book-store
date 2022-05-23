package com.bookstore.controller;

import com.bookstore.domain.OrderDto;
import com.bookstore.domain.PageResponse;
import com.bookstore.domain.ResponseWrapper;
import com.bookstore.service.OrderService;
import com.bookstore.utils.Utils;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
    @InjectMocks
    OrderController orderController;
    @Mock
    OrderService orderService;

    @Test
    public void createOrderTest() {
        ResponseWrapper<OrderDto> dtoResponseWrapper = ResponseWrapper.<OrderDto>builder().data(Utils.createOrderDto()).success(true).build();

        final var principal = mock(Principal.class);
        final var userAuthentication = mock(Authentication.class);
        when(userAuthentication.getCredentials()).thenReturn("2");

        when(orderService.createOrder("2", Lists.newArrayList(Utils.createOrderItemDto()))).thenReturn(dtoResponseWrapper);
        ResponseEntity<ResponseWrapper<OrderDto>> order = orderController.createOrder(userAuthentication, Lists.newArrayList(Utils.createOrderItemDto()));
        Assert.assertNotNull(Objects.requireNonNull(order.getBody()).getData());
    }

    @Test
    public void getCustomerOrdersTest() {
        ResponseWrapper<List<OrderDto>> dtoResponseWrapper = ResponseWrapper.<List<OrderDto>>builder().data(Lists.newArrayList(Utils.createOrderDto())).success(true).build();
        final var principal = mock(Principal.class);

        final var userAuthentication = mock(Authentication.class);
        when(userAuthentication.getCredentials()).thenReturn("2");
        when(orderService.getOrdersByCustomerId("2")).thenReturn(dtoResponseWrapper);
        ResponseEntity<ResponseWrapper<List<OrderDto>>> ordersById = orderController.getCustomerOrders(userAuthentication);
        Assert.assertEquals(1,Objects.requireNonNull(ordersById.getBody()).getData().size());
    }
    @Test
    public void getOrdersByCustomerIdTest(){
        ResponseWrapper<List<OrderDto>> dtoResponseWrapper = ResponseWrapper.<List<OrderDto>>builder().data(Lists.newArrayList(Utils.createOrderDto())).success(true).build();
        when(orderService.getOrdersByCustomerId("2")).thenReturn(dtoResponseWrapper);
        ResponseEntity<ResponseWrapper<List<OrderDto>>> ordersByCustomerId = orderController.getOrdersByCustomerId("2");
        Assert.assertEquals(1, Objects.requireNonNull(ordersByCustomerId.getBody()).getData().size());
    }
    @Test
    public void getOrdersByIdTest(){
        ResponseWrapper<OrderDto> dtoResponseWrapper = ResponseWrapper.<OrderDto>builder().data(Utils.createOrderDto()).success(true).build();
        when(orderService.getOrdersById("2")).thenReturn(dtoResponseWrapper);
        ResponseEntity<ResponseWrapper<OrderDto>> ordersById = orderController.getOrdersById("2");
        Assert.assertNotNull(Objects.requireNonNull(ordersById.getBody()).getData());
    }
    @Test
    public void filterOrdersByDateRangeTest(){
        PageResponse<OrderDto> orderDtoPageResponse = PageResponse.<OrderDto>builder()
                .totalPages(1)
                .totalContent(20)
                .size(20)
                .page(1)
                .list(Lists.newArrayList(Utils.createOrderDto()))
                .build();
        when(orderService.filterOrdersByDateRange(LocalDate.now(), LocalDate.now().plusDays(1) , 1, 20)).thenReturn(orderDtoPageResponse);
        ResponseEntity<PageResponse<OrderDto>> ordersByDateRange = orderController.getOrdersByDateRange(LocalDate.now(), LocalDate.now().plusDays(1), 1, 20);
        Assert.assertEquals(1,ordersByDateRange.getBody().getList().size());
    }
}

