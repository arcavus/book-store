package com.bookstore.domain;

import lombok.Data;

@Data
public class StockDto  extends BaseDto{
    private String bookId;
    private Integer stock;
}
