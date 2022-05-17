package com.bookstore.adapter.impl;

import com.bookstore.adapter.CustomerAdapter;
import com.bookstore.domain.CustomerDomain;
import com.bookstore.common.PersistenceAdapter;
import com.bookstore.entity.CustomerEntity;
import com.bookstore.mapper.CustomerEntityMapper;
import com.bookstore.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class CustomerAdapterImpl implements CustomerAdapter {

    private final CustomerRepository repository;
    private final CustomerEntityMapper mapper;

    @Override
    public Boolean saveCustomer(CustomerDomain customer) {
        CustomerEntity customerEntity = mapper.toEntity(customer);
        CustomerEntity result = repository.save(customerEntity);
        return result != null;
    }

    @Override
    public List<CustomerDomain> getAllCustomers() {
        return repository.findAll().stream().map(mapper::toDomainObject).collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDomain> loadUserByUsername(String username) {
        Optional<CustomerEntity> customerEntity = repository.findCustomerEntityByUsername(username);
        return customerEntity.isPresent() ? Optional.of(mapper.toDomainObject(customerEntity.get())) : Optional.empty();
    }
}
