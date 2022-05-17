package com.bookstore.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Version;

@Getter
@Setter
public class BaseEntity {
    @Version
    private Long version;
}
