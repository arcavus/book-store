package com.bookstore.controller;

import com.bookstore.domain.CustomerDto;
import com.bookstore.domain.CustomerRegisterDto;
import com.bookstore.domain.ResponseWrapper;
import com.bookstore.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<ResponseWrapper<Boolean>> registerCustomer(@Valid @RequestBody CustomerRegisterDto request) {
        return ResponseEntity.ok(customerService.registerCustomer(request));
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<CustomerDto>>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
}
