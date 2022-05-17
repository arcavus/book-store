package com.bookstore.service.impl;

import com.bookstore.adapter.StatisticAdapter;
import com.bookstore.domain.StatisticDomain;
import com.bookstore.domain.StatisticDto;
import com.bookstore.domain.ResponseWrapper;
import com.bookstore.mapper.StatisticDtoMapper;
import com.bookstore.service.StatisticService;
import com.bookstore.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final StatisticAdapter adapter;
    private final StatisticDtoMapper mapper;

    @Override
    public ResponseWrapper<List<StatisticDto>> getMonthlyStatistic(String customerId) {
        List<StatisticDomain> statisticResult = adapter.groupMonthlyByCustomerId(customerId);
        return ResponseUtil.buildSuccess(mapper.toListDTO(statisticResult));
    }
}
