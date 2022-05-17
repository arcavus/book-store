package com.bookstore.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDomain extends BaseDomain {
    private String name;
    private String surname;
    private String username;
    private String encryptedPassword;
}
