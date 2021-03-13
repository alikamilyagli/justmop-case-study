package com.justmop.casestudy.api.exception;

public abstract class AppException extends RuntimeException {

    public AppException() {
        super();
    }

    public AppException(String message) {
        super(message);
    }
}
