package com.bookstore.mapper;

import com.bookstore.domain.OrderItemDomain;
import com.bookstore.entity.OrderBookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BookEntityMapper.class})
public interface OrderBookEntityMapper extends BaseEntityMapper<OrderBookEntity, OrderItemDomain> {
}
