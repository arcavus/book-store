package com.bookstore.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerDto extends BaseDto {
    private String username;
    private String password;
    private String name;
    private String surname;
}
