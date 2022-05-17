package com.bookstore.adapter;

import com.bookstore.domain.CustomerDomain;

import java.util.List;
import java.util.Optional;

public interface CustomerAdapter {
    Boolean saveCustomer(CustomerDomain customer);

    List<CustomerDomain> getAllCustomers();

    Optional<CustomerDomain> loadUserByUsername(String username);
}
