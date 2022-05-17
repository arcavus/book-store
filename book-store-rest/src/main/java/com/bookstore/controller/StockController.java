package com.bookstore.controller;

import com.bookstore.domain.StockDto;
import com.bookstore.domain.UpdateStockRequestDto;
import com.bookstore.domain.ResponseWrapper;
import com.bookstore.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<StockDto>>> getAllStock() {
        return ResponseEntity.ok(stockService.getAllStock());
    }

    @PutMapping("/book/{bookId}")
    public ResponseEntity<ResponseWrapper<StockDto>> updateStockOfBook(@RequestBody Integer stock, @PathVariable String bookId) {
        UpdateStockRequestDto request = UpdateStockRequestDto.builder()
                .stock(stock)
                .bookId(bookId)
                .build();
        return ResponseEntity.ok(stockService.updateBookOfStock(request));
    }
    @GetMapping("/book/{bookId}")
    public ResponseEntity<ResponseWrapper<StockDto>> getStockOfBook(@PathVariable String bookId) {
        return ResponseEntity.ok(stockService.getStockByBookId(bookId));
    }
}
