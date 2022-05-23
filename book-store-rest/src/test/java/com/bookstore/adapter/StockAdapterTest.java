package com.bookstore.adapter;

import com.bookstore.adapter.impl.StockAdapterImpl;
import com.bookstore.domain.StockDomain;
import com.bookstore.entity.StockEntity;
import com.bookstore.mapper.StockEntityMapper;
import com.bookstore.repository.StockRepository;
import com.bookstore.utils.Utils;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StockAdapterTest {
    @InjectMocks
    StockAdapterImpl stockAdapter;
    @Mock
    StockRepository repository;
    @Mock
    StockEntityMapper mapper;


    @Test
    public void updateStockOfBookTest(){
        when(repository.findStockEntityByBookId("5")).thenReturn(Optional.of(Utils.createStockEntity()));
        StockDomain stockDomain = Utils.createStockDomain();
        when(mapper.toDomainObject(any(StockEntity.class))).thenReturn(stockDomain);
        Optional<StockDomain> result = stockAdapter.updateStockOfBook(stockDomain);
        Assert.assertTrue(result.isPresent());
    }
    @Test
    public void createStockOfBookTest(){
        when(mapper.toEntity(any(StockDomain.class))).thenReturn(Utils.createStockEntity());
        when(repository.save(any(StockEntity.class))).thenReturn(Utils.createStockEntity());
        when(mapper.toDomainObject(any(StockEntity.class))).thenReturn(Utils.createStockDomain());
        Optional<StockDomain> result = stockAdapter.createStockOfBook(Utils.createStockDomain());
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void updateStockOfMultipleBookTest(){
        List<StockEntity> stockEntities = Lists.newArrayList(Utils.createStockEntity());
        when(mapper.toListEntity(any())).thenReturn(Lists.newArrayList(Utils.createStockEntity()));
        when(repository.saveAll(any())).thenReturn(stockEntities);
        when(mapper.toDomainObject(any(StockEntity.class))).thenReturn(Utils.createStockDomain());
        List<StockDomain> result = stockAdapter.updateStockOfMultipleBook(Lists.newArrayList(Utils.createStockDomain()));
        Assert.assertEquals(1,result.size());
    }

    @Test
    public void getStockOfBookTest() {
        when(repository.findStockEntityByBookId("2")).thenReturn(Optional.of(Utils.createStockEntity()));
        when(mapper.toDomainObject(any())).thenReturn(Utils.createStockDomain());
        Optional<StockDomain> result = stockAdapter.getStockOfBook("2");
        assert result.isPresent();
    }

    @Test
    public void getAllBookOfStockTest(){
        when(repository.findAll()).thenReturn(Lists.newArrayList(Utils.createStockEntity()));
        when(mapper.toDomainObject(any())).thenReturn(Utils.createStockDomain());
        List<StockDomain> allBookOfStock = stockAdapter.getAllBookOfStock();
        Assert.assertEquals(1, allBookOfStock.size());
    }
}

