package com.justmop.casestudy.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CleanerNotAvailableException extends AppException {

    public CleanerNotAvailableException() {
        super();
    }

    public CleanerNotAvailableException(String message) {
        super(message);
    }
}
