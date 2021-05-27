package com.github.chat.exceptions;

public class ExpiredTokenException extends RuntimeException {

    public ExpiredTokenException() {
    }

    public ExpiredTokenException(String message) {
        super(message);
    }
}
