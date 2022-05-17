package com.bookstore.repository;

import com.bookstore.entity.StockEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StockRepository extends MongoRepository<StockEntity, String> {
    Optional<StockEntity> findStockEntityByBookId(String bookId);
}
