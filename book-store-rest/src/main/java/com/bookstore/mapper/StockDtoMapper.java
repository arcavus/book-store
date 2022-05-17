package com.bookstore.mapper;

import com.bookstore.domain.StockDomain;
import com.bookstore.domain.StockDto;
import com.bookstore.domain.UpdateStockRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockDtoMapper extends BaseDtoMapper<StockDomain, StockDto> {

    StockDomain toDomainObjectFromRequest(UpdateStockRequestDto request);
}
