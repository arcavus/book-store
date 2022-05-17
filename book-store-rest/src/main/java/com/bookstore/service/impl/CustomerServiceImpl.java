package com.bookstore.service.impl;

import com.bookstore.enums.ErrorCodeEnum;
import com.bookstore.exception.CustomRuntimeException;
import com.bookstore.adapter.CustomerAdapter;
import com.bookstore.config.security.CustomerPrincipal;
import com.bookstore.domain.CustomerDomain;
import com.bookstore.domain.CustomerDto;
import com.bookstore.domain.CustomerRegisterDto;
import com.bookstore.domain.ResponseWrapper;
import com.bookstore.mapper.CustomerDtoMapper;
import com.bookstore.service.CustomerService;
import com.bookstore.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDtoMapper mapper;
    private final CustomerAdapter adapter;

    @Override
    public ResponseWrapper<Boolean> registerCustomer(CustomerRegisterDto request) {
        CustomerDomain customerDomain = mapper.toDomainObjectFromLogin(request);
        Boolean registerResult = adapter.saveCustomer(customerDomain);
        return registerResult ? ResponseUtil.buildSuccess(Boolean.TRUE) : ResponseUtil.buildError();
    }

    @Override
    public ResponseWrapper<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customers = adapter.getAllCustomers().stream().map(mapper::toDTO).collect(Collectors.toList());
        return ResponseUtil.buildSuccess(customers);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CustomerDomain> customerDomain = adapter.loadUserByUsername(username);
        if (!customerDomain.isPresent())
            throw new CustomRuntimeException(ErrorCodeEnum.CONTENT_NOT_FOUND);

        return new CustomerPrincipal(username, customerDomain.get().getEncryptedPassword(), customerDomain.get().getId());
    }
}
