package com.bookstore.mapper;

import com.bookstore.domain.CustomerDomain;
import com.bookstore.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper extends BaseEntityMapper<CustomerEntity, CustomerDomain> {
}
