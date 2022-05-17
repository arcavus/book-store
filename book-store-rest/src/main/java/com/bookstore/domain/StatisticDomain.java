package com.bookstore.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StatisticDomain {
    private String customerId;
    private int totalOrder;
    private BigDecimal totalAmount;
    private int totalPurchaseBookCount;
    private int month;
}
