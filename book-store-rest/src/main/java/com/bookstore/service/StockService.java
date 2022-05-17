package com.bookstore.service;

import com.bookstore.domain.StockDto;
import com.bookstore.domain.UpdateStockRequestDto;
import com.bookstore.domain.ResponseWrapper;

import java.util.List;

public interface StockService {
    ResponseWrapper<StockDto> updateBookOfStock(UpdateStockRequestDto request);

    ResponseWrapper<List<StockDto>> getAllStock();

    ResponseWrapper<StockDto> getStockByBookId(String bookId);
}
