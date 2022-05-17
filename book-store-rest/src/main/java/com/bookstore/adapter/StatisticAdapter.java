package com.bookstore.adapter;

import com.bookstore.domain.StatisticDomain;

import java.util.List;

public interface StatisticAdapter {
    List<StatisticDomain> groupMonthlyByCustomerId(String customerId);
}
