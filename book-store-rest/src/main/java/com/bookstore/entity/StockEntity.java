package com.bookstore.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stocks")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockEntity extends BaseEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String bookId;
    private Integer stock;
}
