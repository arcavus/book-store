package com.bookstore.domain;

import com.bookstore.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderDomain extends BaseDomain {
    private String customerId;
    private List<OrderItemDomain> books;
    private OrderStatus status;
    private BigDecimal amount;
    private Date createdDate;

    public OrderDomain() {
        this.books = new ArrayList<>();
        this.status = OrderStatus.IN_PROGRESS;
        this.amount = BigDecimal.ZERO;
    }

    public OrderDomain(String customerId) {
        this.customerId = customerId;
        this.books = new ArrayList<>();
        this.amount = BigDecimal.ZERO;
        this.status = OrderStatus.IN_PROGRESS;
    }

    public void addItem(BookDomain book, Integer quantity) {
        books.add(OrderItemDomain.builder().book(book).quantity(quantity).build());

        BigDecimal cost = book.getPrice().multiply(BigDecimal.valueOf(quantity));
        this.amount = this.amount.add(cost);
    }
}
