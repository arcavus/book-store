package com.bookstore.exception;

public class JwtTokenException extends RuntimeException {
    public JwtTokenException(String message) {
        super(message);
    }
}
