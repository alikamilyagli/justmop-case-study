package com.justmop.casestudy.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookingDateBeforeException extends AppException {

    public BookingDateBeforeException() {
        super();
    }

    public BookingDateBeforeException(String message) {
        super(message);
    }
}
