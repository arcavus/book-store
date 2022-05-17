package com.bookstore.adapter;

import com.bookstore.domain.BookDomain;

import java.util.List;
import java.util.Optional;

public interface BookAdapter {
    BookDomain saveBook(BookDomain request);

    List<BookDomain> getAllBook();

    Optional<BookDomain> getBookById(String bookId);

}
