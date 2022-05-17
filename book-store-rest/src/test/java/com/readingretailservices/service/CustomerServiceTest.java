package com.readingretailservices.service;

import com.bookstore.adapter.CustomerAdapter;
import com.bookstore.domain.CustomerDomain;
import com.bookstore.domain.CustomerRegisterDto;
import com.bookstore.entity.CustomerEntity;
import com.bookstore.exception.CustomRuntimeException;
import com.bookstore.mapper.CustomerDtoMapper;
import com.bookstore.repository.CustomerRepository;
import com.bookstore.service.impl.CustomerServiceImpl;
import com.readingretailservices.utils.Utils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CustomerDtoMapper mapper;

    @Mock
    CustomerAdapter adapter;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void createCustomer_shouldBeSuccess() {
        CustomerEntity customer = Utils.createCustomer();

        CustomerRegisterDto customerRegisterDto = Utils.createCustomerRegisterDto();
        when(mapper.toDomainObjectFromLogin(customerRegisterDto)).thenReturn(Utils.createCustomerDomain());
        when(adapter.saveCustomer(any(CustomerDomain.class))).thenReturn(Boolean.TRUE);
        Boolean result = customerService.registerCustomer(customerRegisterDto).getData();
        Assert.assertEquals(result,Boolean.TRUE);
    }


    @Test
    public void getCustomerById_shouldBeSuccess() {
        when(adapter.loadUserByUsername("aref")).thenReturn(java.util.Optional.of(Utils.createCustomerDomain()));
        UserDetails result = customerService.loadUserByUsername("aref");
        Assert.assertNotNull(result);
    }


    @Test
    public void getCustomerById_shouldBeError_customerNotFound() {
        CustomerDomain customer = Utils.createCustomerDomain();
        expectedException.expect(CustomRuntimeException.class);
        customerService.loadUserByUsername(customer.getId());
    }

}
