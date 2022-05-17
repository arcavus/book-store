package com.bookstore.domain;

import com.bookstore.domain.BookDto;
import lombok.Data;

@Data
public class OrderBookResponse {
    private BookDto book;
    private Integer quantity;
}
