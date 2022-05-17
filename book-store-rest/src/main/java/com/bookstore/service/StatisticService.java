package com.bookstore.service;

import com.bookstore.domain.StatisticDto;
import com.bookstore.domain.ResponseWrapper;

import java.util.List;

public interface StatisticService {
    ResponseWrapper<List<StatisticDto>> getMonthlyStatistic(String customerId);
}
