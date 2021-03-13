//package com.justmop.casestudy.api.validator;
//
//import com.justmop.casestudy.api.entity.sql.Booking;
//import com.justmop.casestudy.api.entity.sql.Cleaner;
//import com.justmop.casestudy.api.exception.BookingCleanerCompanyException;
//import com.justmop.casestudy.api.exception.BookingDateBeforeException;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//
//@Service
//public class CleanerValidator {
//
//    /**
//     * Validates the cleaner for pre-defined validation rules
//     * @param cleaner
//     * @throws BookingDateBeforeException
//     * @throws BookingCleanerCompanyException
//     */
//    public void validate(Cleaner cleaner) throws BookingDateBeforeException, BookingCleanerCompanyException {
////        validateBookingDate(booking);
////        validateCleaners(booking);
//    }
//
//    /**
//     * Validates if the booking date is past
//     * @param booking
//     * @throws BookingDateBeforeException
//     */
//    private void validateBookingDate(Booking booking) throws BookingDateBeforeException {
//        if (booking.getBookingDate().isBefore(LocalDateTime.now())) {
//            throw new BookingDateBeforeException("Booking date cannot be in past");
//        }
//    }
//
//    /**
//     * Validates cleaners belong to same company
//     * @param booking
//     * @throws BookingCleanerCompanyException
//     */
//    private void validateCleaners(Booking booking) throws BookingCleanerCompanyException {
//        ArrayList<Cleaner> cleaners = new ArrayList<>(booking.getCleaners());
//        for (int i = 0; i < cleaners.size(); i++) {
//            for (int j = i+1; j < cleaners.size(); j++) {
//                if (!cleaners.get(i).getCompany().equals(cleaners.get(j).getCompany())) {
//                    throw new BookingCleanerCompanyException("Cleaners must be from the same company");
//                }
//            }
//        }
//    }
//
//}
