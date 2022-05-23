package com.bookstore.controller;

import com.bookstore.domain.OrderDto;
import com.bookstore.domain.ResponseWrapper;
import com.bookstore.domain.StockDto;
import com.bookstore.domain.UpdateStockRequestDto;
import com.bookstore.service.StockService;
import com.bookstore.utils.Utils;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StockControllerTest {
    @InjectMocks
    StockController stockController;
    @Mock
    StockService stockService;

    @Test
    public void getAllStockTest() {
        ResponseWrapper<List<StockDto>> dtoResponseWrapper = ResponseWrapper.<List<StockDto>>builder().data(Lists.newArrayList(Utils.createStockDto())).success(true).build();
        when(stockService.getAllStock()).thenReturn(dtoResponseWrapper);
        ResponseEntity<ResponseWrapper<List<StockDto>>> allStock = stockController.getAllStock();
        Assert.assertEquals(1, allStock.getBody().getData().size());
    }

    @Test
    public void updateStockOfBookTest(){
        ResponseWrapper<StockDto> dtoResponseWrapper = ResponseWrapper.<StockDto>builder().data(Utils.createStockDto()).success(true).build();
        when(stockService.updateBookOfStock(any(UpdateStockRequestDto.class))).thenReturn(dtoResponseWrapper);
        ResponseEntity<ResponseWrapper<StockDto>> responseEntity = stockController.updateStockOfBook(2, "2");
        Assert.assertNotNull(Objects.requireNonNull(responseEntity.getBody()).getData());
    }
    @Test
    public void getStockOfBookTest(){
        ResponseWrapper<StockDto> dtoResponseWrapper = ResponseWrapper.<StockDto>builder().data(Utils.createStockDto()).success(true).build();
        when(stockService.getStockByBookId("2")).thenReturn(dtoResponseWrapper);
        ResponseEntity<ResponseWrapper<StockDto>> stockOfBook = stockController.getStockOfBook("2");
        Assert.assertNotNull(Objects.requireNonNull(stockOfBook.getBody()).getData());
    }
}
