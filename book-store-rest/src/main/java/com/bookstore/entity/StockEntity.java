package com.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stocks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockEntity extends BaseEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String bookId;
    private Integer stock;
}
