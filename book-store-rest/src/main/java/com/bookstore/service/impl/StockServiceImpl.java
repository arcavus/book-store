package com.bookstore.service.impl;

import com.bookstore.enums.ErrorCodeEnum;
import com.bookstore.exception.CustomRuntimeException;
import com.bookstore.adapter.BookAdapter;
import com.bookstore.adapter.StockAdapter;
import com.bookstore.domain.BookDomain;
import com.bookstore.domain.StockDomain;
import com.bookstore.domain.StockDto;
import com.bookstore.domain.UpdateStockRequestDto;
import com.bookstore.domain.ResponseWrapper;
import com.bookstore.mapper.StockDtoMapper;
import com.bookstore.service.StockService;
import com.bookstore.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockAdapter adapter;
    private final BookAdapter bookAdapter;
    private final StockDtoMapper mapper;

    @Override
    public ResponseWrapper<StockDto> updateBookOfStock(UpdateStockRequestDto request) {
        Optional<BookDomain> book = bookAdapter.getBookById(request.getBookId());
        book.orElseThrow(() -> new CustomRuntimeException(ErrorCodeEnum.CONTENT_NOT_FOUND));

        StockDomain stockDomain = mapper.toDomainObjectFromRequest(request);
        Optional<StockDomain> updateStockResult = adapter.updateStockOfBook(stockDomain);
        updateStockResult.orElseThrow(() -> new CustomRuntimeException(ErrorCodeEnum.STOCK_RECORD_NOT_FOUND));

        StockDto stockDto = mapper.toDTO(updateStockResult.get());
        return ResponseUtil.buildSuccess(stockDto);
    }

    @Override
    public ResponseWrapper<List<StockDto>> getAllStock() {
        List<StockDto> stocks = adapter.getAllBookOfStock().stream().map(mapper::toDTO).collect(Collectors.toList());
        return ResponseUtil.buildSuccess(stocks);
    }

    @Override
    public ResponseWrapper<StockDto> getStockByBookId(String bookId) {
        Optional<StockDomain> stockOfBook = adapter.getStockOfBook(bookId);
        Optional<StockDto> stockDto = stockOfBook.map(mapper::toDTO);
        if (stockOfBook.isPresent() && stockDto.isPresent()) {
            return ResponseUtil.buildSuccess(stockDto.get());
        } else return ResponseUtil.buildError();
    }
}
