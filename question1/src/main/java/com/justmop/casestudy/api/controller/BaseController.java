package com.justmop.casestudy.api.controller;

import com.justmop.casestudy.api.exception.AppException;
import com.justmop.casestudy.api.service.BookingService;
import com.justmop.casestudy.api.service.CleanerService;
import com.justmop.casestudy.api.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Base Controller.
 * Contains common objects for all controllers
 *
 * @author Ali Kamil YAĞLI
 */
public abstract class BaseController {

    @Autowired
    CompanyService companyService;

    @Autowired
    CleanerService cleanerService;

    @Autowired
    BookingService bookingService;

    /**
     * Handles {@link SQLIntegrityConstraintViolationException} exceptions
     * and generates custom response
     *
     * @return
     */
    @ExceptionHandler({ SQLIntegrityConstraintViolationException.class })
    public ResponseEntity<?> handleException() {
        return new ResponseEntity<Object>("Duplicate entry", HttpStatus.FORBIDDEN);
    }

    /**
     * Handles {@link AppException} exceptions
     * and generates custom response
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(AppException.class)
    public final ResponseEntity<String> handleAllExceptions(AppException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles {@link RuntimeException} exceptions
     * and generates custom response
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<String> handleAllExceptions(RuntimeException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
