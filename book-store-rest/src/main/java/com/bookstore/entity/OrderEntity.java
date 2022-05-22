package com.bookstore.entity;

import com.bookstore.enums.OrderStatus;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity extends BaseEntity {
    @Id
    private String id;
    private String customerId;
    private List<OrderBookEntity> books = new ArrayList<>();
    private OrderStatus status;
    private BigDecimal amount;
    @CreatedDate
    private Date createdDate;
}
