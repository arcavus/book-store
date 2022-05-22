package com.bookstore.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderBookEntity {
    private BookEntity book;
    private Integer quantity;
}
