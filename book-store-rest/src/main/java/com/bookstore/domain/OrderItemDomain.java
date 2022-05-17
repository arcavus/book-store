package com.bookstore.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class OrderItemDomain {
    private BookDomain book;
    private Integer quantity;
}
