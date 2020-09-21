package com.internet.shop.exceptions;

public class DataBaseProcessingException extends RuntimeException {
    public DataBaseProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
