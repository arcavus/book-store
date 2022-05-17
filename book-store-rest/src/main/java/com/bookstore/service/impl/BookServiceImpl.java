package com.bookstore.service.impl;

import com.bookstore.adapter.BookAdapter;
import com.bookstore.adapter.StockAdapter;
import com.bookstore.domain.BookDomain;
import com.bookstore.domain.BookDto;
import com.bookstore.domain.StockDomain;
import com.bookstore.domain.ResponseWrapper;
import com.bookstore.mapper.BookDtoMapper;
import com.bookstore.service.BookService;
import com.bookstore.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookAdapter bookAdapter;
    private final StockAdapter stockAdapter;
    private final BookDtoMapper mapper;

    @Override
    public ResponseWrapper<BookDto> createBook(BookDto request) {
        BookDomain bookDomain = mapper.toDomainObject(request);
        BookDomain result = bookAdapter.saveBook(bookDomain);
        StockDomain stockDomain = new StockDomain(result.getId(), 0);
        stockAdapter.createStockOfBook(stockDomain);

        BookDto data = mapper.toDTO(result);
        return ResponseUtil.buildSuccess(data);
    }

    @Override
    public ResponseWrapper<List<BookDto>> getAllBook() {
        List<BookDto> bookList = bookAdapter.getAllBook().stream().map(mapper::toDTO).collect(Collectors.toList());
        return ResponseUtil.buildSuccess(bookList);
    }
}
