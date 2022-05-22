package com.bookstore.controller;

import com.bookstore.domain.BookDto;
import com.bookstore.domain.ResponseWrapper;
import com.bookstore.service.BookService;
import com.bookstore.utils.Utils;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {
    @InjectMocks
    BookController bookController;
    @Mock
    BookService bookService;

    @Test
    public void getAllTest(){
        ResponseWrapper<List<BookDto>> build = ResponseWrapper.<List<BookDto>>builder().data(Lists.newArrayList(Utils.createBookDto())).build();
        when(bookService.getAllBook()).thenReturn(build);
        ResponseEntity<ResponseWrapper<List<BookDto>>> all = bookController.getAll();
        Assert.assertSame(HttpStatus.OK,all.getStatusCode());
    }

    @Test
    public void addBookTest(){
        ResponseWrapper<BookDto> build = ResponseWrapper.<BookDto>builder().data(Utils.createBookDto()).build();
        when(bookService.createBook(any(BookDto.class))).thenReturn(build);
        ResponseEntity<ResponseWrapper<BookDto>> responseEntity = bookController.addBook(Utils.createBookDto());
        Assert.assertNotNull(Objects.requireNonNull(responseEntity.getBody()).getData());
    }
}
