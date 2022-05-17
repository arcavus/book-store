package com.readingretailservices.service;


import com.bookstore.adapter.BookAdapter;
import com.bookstore.adapter.StockAdapter;
import com.bookstore.domain.BookDomain;
import com.bookstore.domain.BookDto;
import com.bookstore.mapper.BookDtoMapper;
import com.bookstore.service.impl.BookServiceImpl;
import com.readingretailservices.utils.Utils;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    BookAdapter bookAdapter;
    @Mock
    StockAdapter stockAdapter;
    @Mock
    BookDtoMapper mapper;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void createBook_shouldBeSuccess() {
        BookDto bookDto = Utils.createBookDto();
        when(mapper.toDomainObject(any(BookDto.class))).thenReturn(Utils.createBookDomain());
        when(bookAdapter.saveBook(any(BookDomain.class))).thenReturn(Utils.createBookDomain());
        when(mapper.toDTO(any(BookDomain.class))).thenReturn(bookDto);
        BookDto result = bookService.createBook(bookDto).getData();
        Assert.assertNotNull(result);
    }

    @Test
    public void getAllBook_serviceSuccess() {
        when(bookAdapter.getAllBook()).thenReturn(Lists.newArrayList(Utils.createBookDomain()));
        when(mapper.toDTO(any(BookDomain.class))).thenReturn(Utils.createBookDto());
        List<BookDto> result = bookService.getAllBook().getData();
        Assert.assertNotNull(result);
    }


}
