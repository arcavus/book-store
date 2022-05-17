package com.bookstore.mapper;

import com.bookstore.domain.StockDomain;
import com.bookstore.entity.StockEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockEntityMapper extends BaseEntityMapper<StockEntity, StockDomain> {
}
