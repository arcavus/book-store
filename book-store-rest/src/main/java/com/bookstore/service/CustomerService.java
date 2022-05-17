package com.bookstore.service;

import com.bookstore.domain.CustomerDto;
import com.bookstore.domain.CustomerRegisterDto;
import com.bookstore.domain.ResponseWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface CustomerService extends UserDetailsService {

    ResponseWrapper<Boolean> registerCustomer(CustomerRegisterDto request);

    ResponseWrapper<List<CustomerDto>> getAllCustomers();
}
