package com.readingretailservices.utils;

import com.bookstore.domain.*;
import com.bookstore.domain.CustomerRegisterDto;
import com.bookstore.domain.OrderItemDto;
import com.bookstore.domain.OrderBookResponse;
import com.bookstore.entity.BookEntity;
import com.bookstore.entity.CustomerEntity;
import com.bookstore.entity.OrderBookEntity;
import com.bookstore.entity.OrderEntity;
import com.bookstore.enums.OrderStatus;
import org.assertj.core.util.Lists;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Utils {

    public static final int QUANTITY = 10;
    public static final double PRICE = 10.0;
    public static final double TOTAL_PRICE = 200.0;

    public static String generateRandomString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    public static CustomerEntity createCustomer() {
        CustomerEntity customer = new CustomerEntity();
        customer.setName(generateRandomString());
        customer.setSurname(generateRandomString());
        customer.setUsername(generateRandomString());
        return customer;
    }

    public static CustomerRegisterDto createCustomerRegisterDto(){
        CustomerRegisterDto dto = new CustomerRegisterDto();
        dto.setName(generateRandomString());
        dto.setSurname(generateRandomString());
        dto.setUsername(generateRandomString());
        dto.setPassword(generateRandomString());
        return dto;
    }

    public static CustomerDomain createCustomerDomain(){
        CustomerDomain customerDomain = new CustomerDomain();
        customerDomain.setName(generateRandomString());
        customerDomain.setSurname(generateRandomString());
        customerDomain.setUsername(generateRandomString());
        customerDomain.setEncryptedPassword(generateRandomString());
        return customerDomain;
    }

    public static BookEntity createBookEntity() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor(generateRandomString());
        bookEntity.setPrice(new BigDecimal("25"));
        bookEntity.setTitle(generateRandomString());
        bookEntity.setId("1");
        bookEntity.setShortDesc(generateRandomString());
        return bookEntity;
    }

    public static OrderBookEntity createOrderBookEntity() {
        OrderBookEntity orderBookEntity = new OrderBookEntity();
        orderBookEntity.setQuantity(5);
        orderBookEntity.setBook(createBookEntity());
        return orderBookEntity;
    }


    public static OrderEntity createOrderEntity() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomerId(createCustomer().getId());
        orderEntity.setBooks(Lists.newArrayList(createOrderBookEntity()));
        orderEntity.setAmount(new BigDecimal("25"));
        orderEntity.setStatus(OrderStatus.ORDER_PLACED);
        orderEntity.setCreatedDate(new Date());
        return orderEntity;
    }

    public static OrderItemDto createOrderItemDto(){
        OrderItemDto dto = new OrderItemDto();
        dto.setQuantity(5);
        dto.setBookId(createBookEntity().getId());
        return dto;
    }

    public static List<OrderItemDto> createOrderItemDtoList(){
        return Lists.newArrayList(createOrderItemDto());
    }
    public static OrderDto createOrderDto(){
        OrderDto dto = new OrderDto();
        dto.setAmount(new BigDecimal("25"));
        dto.setBooks(createOrderBookResponseList());
        return dto;
    }

    public static List<OrderBookResponse> createOrderBookResponseList() {
        return Lists.newArrayList(createOrderBookResponse());
    }
    public static OrderBookResponse createOrderBookResponse(){
        OrderBookResponse response = new OrderBookResponse();
        response.setBook(createBookDto());
        response.setQuantity(10);
        return response;
    }

    public static BookDto createBookDto(){
        BookDto dto = new BookDto();
        dto.setAuthor(generateRandomString());
        dto.setTitle(generateRandomString());
        dto.setDescription(generateRandomString());
        dto.setId("1");
        dto.setPrice(new BigDecimal("25"));
        return dto;
    }

    public static BookDomain createBookDomain(){
        BookDomain bookDomain = new BookDomain();
        bookDomain.setAuthor(generateRandomString());
        bookDomain.setTitle(generateRandomString());
        bookDomain.setShortDesc(generateRandomString());
        bookDomain.setId("1");
        bookDomain.setPrice(new BigDecimal("25"));
        return bookDomain;
    }

    public static StockDomain createStockDomain(){
        StockDomain stockDomain = new StockDomain();
        stockDomain.setStock(10);
        stockDomain.setId("1");
        stockDomain.setBookId("5");
        return stockDomain;
    }

    public static OrderDomain createOrderDomain() {
        OrderDomain orderDomain = new OrderDomain();
        orderDomain.setAmount(new BigDecimal("15"));
        orderDomain.setCreatedDate(new Date());
        orderDomain.setCustomerId("5");
        orderDomain.setBooks(Lists.newArrayList(createOrderItemDomain()));
        orderDomain.setId("5");
        orderDomain.setStatus(OrderStatus.ORDER_PLACED);
        return orderDomain;
    }

    public static OrderItemDomain createOrderItemDomain(){
        return OrderItemDomain.builder().book(createBookDomain()).quantity(5).build();
    }
}
