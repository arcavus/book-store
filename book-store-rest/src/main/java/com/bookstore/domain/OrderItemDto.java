package com.bookstore.domain;

import lombok.Data;

@Data
public class OrderItemDto {
    private String bookId;
    private Integer quantity;
}
