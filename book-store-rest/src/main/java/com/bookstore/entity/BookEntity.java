package com.bookstore.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "books")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity extends BaseEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String title;
    private String author;
    private String shortDesc;
    private BigDecimal price;
}
