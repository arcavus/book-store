package com.bookstore.adapter.impl;

import com.bookstore.adapter.StockAdapter;
import com.bookstore.common.PersistenceAdapter;
import com.bookstore.domain.StockDomain;
import com.bookstore.entity.StockEntity;
import com.bookstore.mapper.StockEntityMapper;
import com.bookstore.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
@Slf4j
@RequiredArgsConstructor
public class StockAdapterImpl implements StockAdapter {

    private final StockRepository repository;
    private final StockEntityMapper mapper;

    @Override
    public Optional<StockDomain> updateStockOfBook(StockDomain stockOfBook) {
        Optional<StockEntity> stockEntity = repository.findStockEntityByBookId(stockOfBook.getBookId());
        if (!stockEntity.isPresent()) return Optional.empty();

        StockEntity s = stockEntity.get();
        s.setStock(stockOfBook.getStock());
        repository.save(s);
        return Optional.of(mapper.toDomainObject(s));
    }

    @Override
    public Optional<StockDomain> createStockOfBook(StockDomain stockOfBook) {
        StockEntity stockEntity = mapper.toEntity(stockOfBook);
        stockEntity= repository.save(stockEntity);
        return Optional.of(mapper.toDomainObject(stockEntity));
    }


    @Override
    public List<StockDomain> updateStockOfMultipleBook(List<StockDomain> stockOfBookList) {
        List<StockEntity> stockEntities = mapper.toListEntity(stockOfBookList);
        return repository.saveAll(stockEntities).stream().map(mapper::toDomainObject).collect(Collectors.toList());
    }

    @Override
    public Optional<StockDomain> getStockOfBook(String bookId) {
        Optional<StockEntity> stockOfBook = repository.findStockEntityByBookId(bookId);
        return stockOfBook.stream().map(mapper::toDomainObject).findFirst();
    }

    @Override
    public List<StockDomain> getAllBookOfStock() {
        return repository.findAll().stream().map(mapper::toDomainObject).collect(Collectors.toList());
    }
}
