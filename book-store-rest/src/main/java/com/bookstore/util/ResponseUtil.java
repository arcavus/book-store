package com.bookstore.util;

import com.bookstore.domain.ResponseWrapper;

public class ResponseUtil {

    public static <T> ResponseWrapper<T> buildSuccess(T data) {
        return ResponseWrapper.<T>builder().success(true).data(data).build();
    }

    public static <T> ResponseWrapper<T> buildError() {
        return ResponseWrapper.<T>builder().success(false).data(null).build();
    }
}
