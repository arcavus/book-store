package com.bookstore.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StockDto  extends BaseDto{
    private String bookId;
    private Integer stock;
}
