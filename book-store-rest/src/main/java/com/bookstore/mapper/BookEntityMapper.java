package com.bookstore.mapper;

import com.bookstore.domain.BookDomain;
import com.bookstore.entity.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookEntityMapper extends BaseEntityMapper<BookEntity, BookDomain> {
}
