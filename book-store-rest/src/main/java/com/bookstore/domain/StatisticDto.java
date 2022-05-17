package com.bookstore.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StatisticDto{
    private String customerId;
    private int totalOrder;
    private BigDecimal totalAmount;
    private int totalPurchaseBookCount;
    private int month;
}
