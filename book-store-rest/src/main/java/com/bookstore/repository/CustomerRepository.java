package com.bookstore.repository;

import com.bookstore.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {

    Optional<CustomerEntity> findCustomerEntityByUsername(String username);
}
