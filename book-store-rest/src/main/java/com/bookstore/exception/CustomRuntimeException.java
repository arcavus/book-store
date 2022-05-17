package com.bookstore.exception;

import com.bookstore.enums.ErrorCodeEnum;
import lombok.Getter;

@Getter
public class CustomRuntimeException extends RuntimeException{
    private ErrorCodeEnum errorCode;

    public CustomRuntimeException(ErrorCodeEnum errorCode) {
        this.errorCode = errorCode;
    }
}
