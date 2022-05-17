package com.bookstore.adapter.impl;

import com.bookstore.adapter.StockAdapter;
import com.bookstore.domain.StockDomain;
import com.bookstore.entity.StockEntity;
import com.bookstore.repository.StockRepository;
import com.bookstore.common.PersistenceAdapter;
import com.bookstore.mapper.StockEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
@Slf4j
@RequiredArgsConstructor
public class StockAdapterImpl implements StockAdapter {

    private final StockRepository repository;
    private final StockEntityMapper mapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Optional<StockDomain> updateStockOfBook(StockDomain stockOfBook) {
        //race condition prevent simultaneously decrease stock count
        Boolean lock = (Boolean) redisTemplate.opsForValue().get("lock");
        if (lock != null && lock) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                log.error("Race condition prevent error", e);
            }
            updateStockOfBook(stockOfBook);
        } else {
            redisTemplate.opsForValue().set("lock", Boolean.TRUE);
        }
        redisTemplate.opsForValue().set("lock", Boolean.FALSE);
        Optional<StockEntity> stockEntity = repository.findStockEntityByBookId(stockOfBook.getBookId());
        if (!stockEntity.isPresent()) return Optional.empty();


        stockEntity.get().setStock(stockOfBook.getStock());
        repository.save(stockEntity.get());
        return Optional.of(mapper.toDomainObject(stockEntity.get()));
    }

    @Override
    public Optional<StockDomain> createStockOfBook(StockDomain stockOfBook) {
        StockEntity entity = repository.save(mapper.toEntity(stockOfBook));
        return Optional.of(mapper.toDomainObject(entity));
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
