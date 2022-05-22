package com.bookstore.service;

import com.bookstore.adapter.BookAdapter;
import com.bookstore.adapter.StockAdapter;
import com.bookstore.domain.*;
import com.bookstore.enums.ErrorCodeEnum;
import com.bookstore.exception.CustomRuntimeException;
import com.bookstore.mapper.StockDtoMapper;
import com.bookstore.service.impl.StockServiceImpl;
import com.bookstore.util.ResponseUtil;
import com.bookstore.utils.Utils;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StockServiceTest {

    @InjectMocks
    StockServiceImpl stockService;
    @Mock
    StockAdapter adapter;
    @Mock
    BookAdapter bookAdapter;
    @Mock
    StockDtoMapper mapper;

    @Test
    public void updateBookOfStockWhenBookIsNullTest() {
        when(bookAdapter.getBookById("2")).thenReturn(Optional.empty());
        try {
            stockService.updateBookOfStock(Utils.createUpdateStockRequest());
        } catch (CustomRuntimeException exception) {
            if (ErrorCodeEnum.CONTENT_NOT_FOUND.getCode() != exception.getErrorCode().getCode()) {
                Assert.fail();
            }
        }
    }

    @Test
    public void updateBookOfStockWhenStockNotFoundTest() {
        when(bookAdapter.getBookById("2")).thenReturn(Optional.of(Utils.createBookDomain()));
        when(mapper.toDomainObjectFromRequest(any(UpdateStockRequestDto.class))).thenReturn(Utils.createStockDomain());
        when(adapter.updateStockOfBook(any(StockDomain.class))).thenReturn(Optional.empty());
        try {
            stockService.updateBookOfStock(Utils.createUpdateStockRequest());
        } catch (CustomRuntimeException exception) {
            if (ErrorCodeEnum.STOCK_RECORD_NOT_FOUND.getCode() != exception.getErrorCode().getCode()) {
                Assert.fail();
            }
        }
    }

    @Test
    public void updateBookOfStockTest() {
        when(bookAdapter.getBookById("2")).thenReturn(Optional.of(Utils.createBookDomain()));
        when(mapper.toDomainObjectFromRequest(any(UpdateStockRequestDto.class))).thenReturn(Utils.createStockDomain());
        when(adapter.updateStockOfBook(any(StockDomain.class))).thenReturn(Optional.of(Utils.createStockDomain()));
        when(mapper.toDTO(any(StockDomain.class))).thenReturn(Utils.createStockDto());
        ResponseWrapper<StockDto> responseWrapper = stockService.updateBookOfStock(Utils.createUpdateStockRequest());
        Assert.assertNotNull(responseWrapper.getData());
    }

    @Test
    public void getAllBookStockTest() {
        when(adapter.getAllBookOfStock()).thenReturn(Lists.newArrayList(Utils.createStockDomain()));
        when(mapper.toDTO(any(StockDomain.class))).thenReturn(Utils.createStockDto());
        ResponseWrapper<List<StockDto>> dtoList = stockService.getAllStock();
        Assert.assertEquals(1, dtoList.getData().size());
    }

    @Test
    public void getBookByIdTest() {
        when(adapter.getStockOfBook("2")).thenReturn(Optional.of(Utils.createStockDomain()));
        when(mapper.toDTO(any(StockDomain.class))).thenReturn(Utils.createStockDto());
        ResponseWrapper<StockDto> responseWrapper = stockService.getStockByBookId("2");
        Assert.assertNotNull(responseWrapper.getData());
    }
}
