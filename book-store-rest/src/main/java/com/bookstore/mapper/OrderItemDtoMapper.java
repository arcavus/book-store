package com.bookstore.mapper;

import com.bookstore.domain.OrderItemDomain;
import com.bookstore.domain.OrderItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BookDtoMapper.class})
public interface OrderItemDtoMapper extends BaseDtoMapper<OrderItemDomain, OrderItemDto> {

    @Mapping(target = "bookId", source = "book.id")
    @Override
    OrderItemDto toDTO(OrderItemDomain orderItemDomain);

    @Mapping(target = "book.id", source = "bookId")
    @Override
    OrderItemDomain toDomainObject(OrderItemDto orderItemDto);
}
