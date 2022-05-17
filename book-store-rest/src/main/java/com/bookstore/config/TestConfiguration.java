package com.bookstore.config;

import com.bookstore.service.BookService;
import com.bookstore.service.CustomerService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class TestConfiguration {
    @Bean
    @Primary
    public BookService bookService() {
        return Mockito.mock(BookService.class);
    }

    @Bean
    @Primary
    public CustomerService customerService() {
        return Mockito.mock(CustomerService.class);
    }
}
