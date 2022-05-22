package com.bookstore.repository;

import com.bookstore.domain.StatisticDomain;
import com.bookstore.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {

    List<OrderEntity> findOrderEntityByCustomerId(String customerId);

    Optional<OrderEntity> findOrderEntityById(String orderId);

    Page<OrderEntity> findOrderEntityByCreatedDateBetween(LocalDate from, LocalDate to, Pageable pageable);

    @Aggregation(pipeline = {
                    "{ $group: {"
                        + " _id: { month: { $month: $createdDate } }, "
                        + " totalOrder: {$sum: 1},"
                        + " customerId: {$first: '$customerId'},"
                        + " month: {$first: { $month: $createdDate }},"
                        + " totalPurchaseBookCount: {$sum: { $size:'$books' }},"
                        + " totalAmount:{ $sum: {$toDecimal:'$amount'} }"
                    + "}}," +
                    "{ $project: {"
                        + " month: '$month',"
                        + " _id: 0,"
                        + " customerId: '$customerId',"
                        + " totalOrder: '$totalOrder',"
                        + " totalPurchaseBookCount: '$totalPurchaseBookCount',"
                        + " totalAmount: '$totalAmount'"
                    + "}}," + "{ $sort : { 'createdDate' : 1}} "

    })
    AggregationResults<StatisticDomain> groupMonthlyByCustomerId(String customerId);
}
