package com.justmop.casestudy.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookingCleanerCompanyException extends AppException {

    public BookingCleanerCompanyException() {
        super();
    }

    public BookingCleanerCompanyException(String message) {
        super(message);
    }
}
