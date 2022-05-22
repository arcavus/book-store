package com.bookstore.adapter;

import com.bookstore.adapter.impl.StatisticAdapterImpl;
import com.bookstore.domain.StatisticDomain;
import com.bookstore.repository.OrderRepository;
import com.bookstore.utils.Utils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import java.util.List;

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
       /*Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project().and(month("$date")).as("month"),
                Aggregation.group("$month").count().as("count"),
                project("count").and("month").previousOperation(),
                Aggregation.match(where("month").is(2)));

        AggregationResults<StatisticDomain> aggregate = mongoTemplate.aggregate(aggregation, "demo", StatisticDomain.class);

        aggregate.getMappedResults().add(Utils.createStaisticDomain())
        when(repository.groupMonthlyByCustomerId("2")).thenReturn(aggregate);
        List<StatisticDomain> statisticDomains = statisticAdapter.groupMonthlyByCustomerId("2");
        Assert.assertNotNull(statisticDomains);*/
    }

}




