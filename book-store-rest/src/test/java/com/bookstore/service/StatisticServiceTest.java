package com.bookstore.service;

import com.bookstore.adapter.StatisticAdapter;
import com.bookstore.domain.ResponseWrapper;
import com.bookstore.domain.StatisticDomain;
import com.bookstore.domain.StatisticDto;
import com.bookstore.mapper.StatisticDtoMapper;
import com.bookstore.service.impl.StatisticServiceImpl;
import com.bookstore.util.ResponseUtil;
import com.bookstore.utils.Utils;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatisticServiceTest {
    @InjectMocks
    StatisticServiceImpl statisticService;
    @Mock
    StatisticAdapter adapter;
    @Mock
    StatisticDtoMapper mapper;

    @Test
    public void getMonthlyStatisticTest() {
        when(adapter.groupMonthlyByCustomerId("2")).thenReturn(Lists.newArrayList(Utils.createStaisticDomain()));
        when(mapper.toListDTO(any())).thenReturn(Lists.newArrayList(Utils.createStatisticDto()));
        ResponseWrapper<List<StatisticDto>> monthlyStatistic = statisticService.getMonthlyStatistic("2");
        Assert.assertEquals(1, monthlyStatistic.getData().size());
    }
}
