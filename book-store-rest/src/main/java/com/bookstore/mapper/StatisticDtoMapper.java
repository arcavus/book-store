package com.bookstore.mapper;

import com.bookstore.domain.StatisticDomain;
import com.bookstore.domain.StatisticDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatisticDtoMapper extends BaseDtoMapper<StatisticDomain, StatisticDto> {
}
