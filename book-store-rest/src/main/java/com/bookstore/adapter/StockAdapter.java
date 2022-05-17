package com.bookstore.adapter;

import com.bookstore.domain.StockDomain;

import java.util.List;
import java.util.Optional;

public interface StockAdapter {
    Optional<StockDomain> updateStockOfBook(StockDomain stockOfBook);

    Optional<StockDomain> createStockOfBook(StockDomain stockOfBook);

    List<StockDomain> updateStockOfMultipleBook(List<StockDomain> stockOfBookList);

    Optional<StockDomain> getStockOfBook(String bookId);

    List<StockDomain> getAllBookOfStock();
}
