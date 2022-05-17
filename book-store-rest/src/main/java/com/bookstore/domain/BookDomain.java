package com.bookstore.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BookDomain extends BaseDomain {
    private String title;
    private String author;
    private String shortDesc;
    private BigDecimal price;
}
