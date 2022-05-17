package com.bookstore.mapper;

import com.bookstore.domain.OrderDomain;
import com.bookstore.domain.OrderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderItemDtoMapper.class})
public interface OrderDtoMapper extends BaseDtoMapper<OrderDomain, OrderDto> {
}
