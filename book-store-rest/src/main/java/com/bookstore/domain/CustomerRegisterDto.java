package com.bookstore.domain;

import lombok.Data;

@Data
public class CustomerRegisterDto {
    private String username;
    private String password;
    private String name;
    private String surname;
}
