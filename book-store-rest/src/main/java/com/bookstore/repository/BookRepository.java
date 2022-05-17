package com.bookstore.repository;

import com.bookstore.entity.BookEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<BookEntity, String> {
}
