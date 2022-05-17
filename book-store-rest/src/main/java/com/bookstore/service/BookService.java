package com.bookstore.service;

import com.bookstore.domain.BookDto;
import com.bookstore.domain.ResponseWrapper;

import java.util.List;

public interface BookService {
    ResponseWrapper<BookDto> createBook(BookDto request);

    ResponseWrapper<List<BookDto>> getAllBook();
}
