package com.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity extends BaseEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    private String encryptedPassword;
    private String name;
    private String surname;
}
