package com.bookstore.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticDto{
    private String customerId;
    private int totalOrder;
    private BigDecimal totalAmount;
    private int totalPurchaseBookCount;
    private int month;
}
