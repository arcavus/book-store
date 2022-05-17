package com.bookstore.controller;

import com.bookstore.domain.OrderDto;
import com.bookstore.domain.PageResponse;
import com.bookstore.domain.OrderItemDto;
import com.bookstore.domain.ResponseWrapper;
import com.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ResponseWrapper<OrderDto>> createOrder(Authentication principal,
                                                                @Valid @RequestBody List<OrderItemDto> request) {
        String customerId = principal.getCredentials().toString();
        return ResponseEntity.ok(orderService.createOrder(customerId, request));
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<OrderDto>>> getCustomerOrders(Authentication principal) {
        String customerId = principal.getCredentials().toString();
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(customerId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ResponseWrapper<List<OrderDto>>> getOrdersByCustomerId(@PathVariable String customerId) {
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(customerId));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseWrapper<OrderDto>> getOrdersById(@PathVariable String orderId) {
        return ResponseEntity.ok(orderService.getOrdersById(orderId));
    }

    @GetMapping("/filter")
    public ResponseEntity<PageResponse<OrderDto>> getOrdersByDateRange(@RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                                                       @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
                                                                       @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                                       @RequestParam(value = "size", required = false, defaultValue = "5") Integer size) {
        return ResponseEntity.ok(orderService.filterOrdersByDateRange(from, to , page, size));
    }
}
