package com.bookstore.domain;

import com.bookstore.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDto extends BaseDto {
    private List<OrderBookResponse> books;
    private OrderStatus status;
    private BigDecimal amount;
    private Date createdDate;
}
