package com.readingretailservices.adapter;


import com.bookstore.adapter.impl.BookAdapterImpl;
import com.bookstore.domain.BookDomain;
import com.bookstore.entity.BookEntity;
import com.bookstore.mapper.BookEntityMapper;
import com.bookstore.repository.BookRepository;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookAdapterTest {
    @InjectMocks
    BookAdapterImpl bookAdapter;
    @Mock
    BookRepository bookRepository;
    @Mock
    BookEntityMapper mapper;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void saveBookTest() {
        BookDomain bookDomain = Utils.createBookDomain();
        when(mapper.toEntity(bookDomain)).thenReturn(Utils.createBookEntity());
        when(bookRepository.save(any(BookEntity.class))).thenReturn(Utils.createBookEntity());
        when(mapper.toDomainObject(any(BookEntity.class))).thenReturn(bookDomain);
        BookDomain result = bookAdapter.saveBook(bookDomain);
        Assert.assertNotNull(result);
    }

    @Test
    public void getAllBooksTest() {
        when(bookRepository.findAll()).thenReturn(Lists.newArrayList(Utils.createBookEntity()));
        when(mapper.toDomainObject(any(BookEntity.class))).thenReturn(Utils.createBookDomain());
        List<BookDomain> domainList = bookAdapter.getAllBook();
        Assert.assertEquals(1, domainList.size());
    }

    @Test
    public void getBookByIdTest(){
        when(bookRepository.findById("2")).thenReturn(Optional.of(Utils.createBookEntity()));
        when(mapper.toDomainObject(any(BookEntity.class))).thenReturn(Utils.createBookDomain());
        Optional<BookDomain> domain = bookAdapter.getBookById("2");
        Assert.assertEquals(true,domain.isPresent());

    }

}
