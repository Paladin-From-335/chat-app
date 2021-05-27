package com.github.chat.exceptions;

public class InternalServerError extends RuntimeException {
    public InternalServerError() {
    }

    public InternalServerError(String message) {
        super(message);
    }
}
