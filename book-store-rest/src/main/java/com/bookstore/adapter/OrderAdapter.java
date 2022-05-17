package com.bookstore.adapter;

import com.bookstore.domain.OrderDomain;
import com.bookstore.domain.PageResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderAdapter {

    OrderDomain saveOrder(OrderDomain order);

    List<OrderDomain> getOrdersByCustomerId(String customerId);

    Optional<OrderDomain> getOrdersById(String orderId);

    PageResponse<OrderDomain> filterOrdersByDateRange(LocalDate from, LocalDate to, int page, int size);
}
