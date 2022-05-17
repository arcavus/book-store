package com.bookstore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    INTERNAL_SERVER_ERROR(1000,"Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR),
    CONTENT_NOT_FOUND(1001,"Content not found.", HttpStatus.BAD_REQUEST),
    UN_AUTHORIZATION(1003,"Un Authorized.", HttpStatus.UNAUTHORIZED),
    STOCK_RECORD_NOT_FOUND(1005,"Stock record not found.", HttpStatus.BAD_REQUEST),
    STOCK_ERROR(1004,"Sorry, we do not have enough book in stock.", HttpStatus.BAD_REQUEST),
    FIELD_VALIDATION_ERROR(1002,"Field validation error.", HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    private HttpStatus httpStatus;

}
