package com.bookstore.mapper;

import com.bookstore.domain.BookDomain;
import com.bookstore.domain.BookDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookDtoMapper extends BaseDtoMapper<BookDomain, BookDto> {
}
