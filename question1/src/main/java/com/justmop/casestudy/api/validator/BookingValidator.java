package com.justmop.casestudy.api.validator;

import com.justmop.casestudy.api.entity.sql.Booking;
import com.justmop.casestudy.api.entity.sql.Cleaner;
import com.justmop.casestudy.api.exception.BookingCleanerCompanyException;
import com.justmop.casestudy.api.exception.BookingDateBeforeException;
import com.justmop.casestudy.api.exception.CleanerNotAvailableException;
import com.justmop.casestudy.api.service.CleanerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class BookingValidator {

    @Autowired
    CleanerService cleanerService;

    /**
     * Validates the booking for pre-defined validation rules
     * @param booking
     * @throws BookingDateBeforeException
     * @throws BookingCleanerCompanyException
     */
    public void validate(Booking booking) throws BookingDateBeforeException, BookingCleanerCompanyException {
        validateBookingDate(booking);
        validateCleaners(booking);
    }

    /**
     * Validates if the booking date is past
     * @param booking
     * @throws BookingDateBeforeException
     */
    private void validateBookingDate(Booking booking) throws BookingDateBeforeException {
        if (booking.getBookingDate().isBefore(LocalDate.now())) {
            throw new BookingDateBeforeException("Booking date cannot be in past");
        }
    }

    /**
     * Validates if all cleaners are available for the booking
     * @param booking
     * @throws BookingCleanerCompanyException
     */
    private void validateCleaners(Booking booking) throws BookingCleanerCompanyException {
        ArrayList<Cleaner> cleaners = new ArrayList<>(booking.getCleaners());
        ArrayList<Cleaner> unavailableCleaners = new ArrayList<Cleaner>();
        for (int i = 0; i < cleaners.size(); i++) {
            for (int j = i+1; j < cleaners.size(); j++) {
                if (!cleaners.get(i).getCompany().equals(cleaners.get(j).getCompany())) {
                    throw new BookingCleanerCompanyException("Cleaners must be from the same company");
                }
            }

            if (!cleanerService.isAvailable(booking, cleaners.get(i), booking.getBookingDate(), booking.getBookingTime())) {
                unavailableCleaners.add(cleaners.get(i));
            }
        }

        if (unavailableCleaners.size() > 0) {
            throw new CleanerNotAvailableException("Cleaners not available at booking time. ids: " +
                unavailableCleaners.stream()
                        .map(p -> String.valueOf(p.getId()))
                        .collect(Collectors.joining(",")));
        }
    }

}
