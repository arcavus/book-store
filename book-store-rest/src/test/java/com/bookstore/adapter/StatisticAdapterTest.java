package com.bookstore.adapter;

import com.bookstore.adapter.impl.StatisticAdapterImpl;
import com.bookstore.domain.StatisticDomain;
import com.bookstore.repository.OrderRepository;
import com.bookstore.utils.Utils;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.DateOperators.Month.month;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@RunWith(MockitoJUnitRunner.class)
public class StatisticAdapterTest {

    @InjectMocks
    StatisticAdapterImpl statisticAdapter;

    @Mock
    OrderRepository repository;
    @Mock
    MongoTemplate mongoTemplate;

    @Test
    public void groupMonthlyByCustomerIdTest(){
        ArgumentCaptor<TypedAggregation> aggregationParamCaptor = ArgumentCaptor.forClass(TypedAggregation.class);
        AggregationResults resultMock = mock(AggregationResults.class);
        when(resultMock.getMappedResults()).thenReturn(Lists.newArrayList(Utils.createStaisticDomain()));
        when(repository.groupMonthlyByCustomerId("2")).thenReturn(resultMock);
        List<StatisticDomain> statisticDomains = statisticAdapter.groupMonthlyByCustomerId("2");
        Assert.assertEquals(1, statisticDomains.size());
    }

}




