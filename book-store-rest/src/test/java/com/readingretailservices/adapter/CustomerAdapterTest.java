package com.readingretailservices.adapter;


import com.bookstore.adapter.impl.CustomerAdapterImpl;
import com.bookstore.domain.BookDomain;
import com.bookstore.domain.CustomerDomain;
import com.bookstore.entity.BookEntity;
import com.bookstore.entity.CustomerEntity;
import com.bookstore.mapper.BookEntityMapper;
import com.bookstore.mapper.CustomerEntityMapper;
import com.bookstore.repository.CustomerRepository;
import com.readingretailservices.utils.Utils;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerAdapterTest {
    @InjectMocks
    CustomerAdapterImpl customerAdapter;
    @Mock
    CustomerRepository customerRepository;
    @Mock
    CustomerEntityMapper mapper;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void saveCustomerTest() {
        CustomerDomain customerDomain = Utils.createCustomerDomain();
        when(mapper.toEntity(customerDomain)).thenReturn(Utils.createCustomer());
        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(Utils.createCustomer());
        Boolean result = customerAdapter.saveCustomer(customerDomain);
        Assert.assertEquals(true,result);
    }

    @Test
    public void getAllcustomersTest() {
        when(customerRepository.findAll()).thenReturn(Lists.newArrayList(Utils.createCustomer()));
        when(mapper.toDomainObject(any(CustomerEntity.class))).thenReturn(Utils.createCustomerDomain());
        List<CustomerDomain> domainList = customerAdapter.getAllCustomers();
        Assert.assertEquals(1, domainList.size());
    }

    @Test
    public void getcustomerByNameTest(){
        when(customerRepository.findCustomerEntityByUsername("aref")).thenReturn(Optional.of(Utils.createCustomer()));
        when(mapper.toDomainObject(any(CustomerEntity.class))).thenReturn(Utils.createCustomerDomain());
        Optional<CustomerDomain> domain = customerAdapter.loadUserByUsername("aref");
        Assert.assertEquals(true,domain.isPresent());

    }

}
