package com.bookstore.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseWrapper<T> {
    private boolean success;
    private T data;
}
