package com.bookstore.controller;

import com.bookstore.domain.CustomerDto;
import com.bookstore.domain.ResponseWrapper;
import com.bookstore.service.CustomerService;
import com.bookstore.utils.Utils;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {
    @InjectMocks
    CustomerController customerController;
    @Mock
    CustomerService customerService;

    @Test
    public void registerCustomerTest(){
        when(customerService.registerCustomer(any())).thenReturn(ResponseWrapper.<Boolean>builder().data(Boolean.TRUE).build());
        ResponseEntity<ResponseWrapper<Boolean>> responseEntity = customerController.registerCustomer(Utils.createCustomerRegisterDto());
        Assert.assertTrue(Objects.requireNonNull(responseEntity.getBody()).getData());
    }

    @Test
    public void getAllCustomerTest(){
        when(customerService.getAllCustomers()).thenReturn(ResponseWrapper.<List<CustomerDto>>builder().data(Lists.newArrayList(Utils.createCustomerDto())).build());
        ResponseEntity<ResponseWrapper<List<CustomerDto>>> allCustomers = customerController.getAllCustomers();
        List<CustomerDto> customerDtos = Objects.requireNonNull(allCustomers.getBody()).getData();
        Assert.assertEquals(1,customerDtos.size());
    }
}
