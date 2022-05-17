package com.bookstore.mapper;

import com.bookstore.domain.CustomerDomain;
import com.bookstore.domain.CustomerDto;
import com.bookstore.domain.CustomerRegisterDto;
import com.bookstore.util.Helper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CustomerDtoMapper extends BaseDtoMapper<CustomerDomain, CustomerDto> {

    @Mapping(target = "encryptedPassword", source = "password", qualifiedByName = "encryptValue")
    CustomerDomain toDomainObjectFromLogin(CustomerRegisterDto registerRequest);

    @Named("encryptValue")
    default String encryptValue(String password){
        return Helper.encryptionValue(password);
    }


}
