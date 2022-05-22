package com.bookstore.controller;

import com.bookstore.domain.OrderDto;
import com.bookstore.domain.ResponseWrapper;
import com.bookstore.domain.StatisticDto;
import com.bookstore.service.StatisticService;
import com.bookstore.utils.Utils;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatisticControllerTest {
    @InjectMocks
    StatisticController statisticController;

    @Mock
    StatisticService statisticService;

    @Test
    public void getMonthlyStatisticTest(){
        ResponseWrapper<List<StatisticDto>> dtoResponseWrapper = ResponseWrapper.<List<StatisticDto>>builder().data(Lists.newArrayList(Utils.createStatisticDto())).success(true).build();
        final var principal = mock(Principal.class);
        final var userAuthentication = mock(Authentication.class);
        when(userAuthentication.getCredentials()).thenReturn("2");
        when(statisticService.getMonthlyStatistic("2")).thenReturn(dtoResponseWrapper);
        ResponseEntity<ResponseWrapper<List<StatisticDto>>> monthlyStatistic = statisticController.getMonthlyStatistic(userAuthentication);
        Assert.assertEquals(1, Objects.requireNonNull(monthlyStatistic.getBody()).getData().size());
    }
}
