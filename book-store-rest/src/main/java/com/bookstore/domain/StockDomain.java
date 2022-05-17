package com.bookstore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockDomain extends BaseDomain {
    private String bookId;
    private Integer stock;


    public void updateStockOfBook(Integer orderQuantity) {
        this.stock -= orderQuantity;
    }
}
