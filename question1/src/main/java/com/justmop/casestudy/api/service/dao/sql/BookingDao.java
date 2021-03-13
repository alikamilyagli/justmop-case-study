package com.justmop.casestudy.api.service.dao.sql;

import com.justmop.casestudy.api.entity.sql.Booking;
import com.justmop.casestudy.api.entity.sql.Cleaner;
import com.justmop.casestudy.api.exception.ResourceNotFoundException;
import com.justmop.casestudy.api.service.BookingService;
import com.justmop.casestudy.api.validator.BookingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Service implementation for {@link Booking} model
 *
 * @author Ali Kamil YAĞLI
 */
@Service
public class BookingDao extends BaseDao implements BookingService {

    @Autowired
    BookingValidator bookingValidator;

    public ArrayList<Booking> findAll() {
        return (ArrayList<Booking>) bookingRepository.findAll();
    }

    public Page<Booking> findAll(Pageable pageable) {
        return bookingRepository.findAll(pageable);
    }

    public long count() {
        return bookingRepository.count();
    }

    public Booking save(Booking booking) {
        booking = mapBookingRequest(booking);
        bookingValidator.validate(booking);
        return bookingRepository.save(booking);
    }

    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }

    public void delete(Booking booking) {
        bookingRepository.delete(booking);
    }

    private Booking mapBookingRequest(Booking booking) {
        ArrayList<Cleaner> cleaners = new ArrayList<>();
        for (Cleaner cleanerRequest: booking.getCleaners()) {
            Optional<Cleaner> cleanerOptional = cleanerRepository.findById(cleanerRequest.getId());
            if (!cleanerOptional.isPresent()) {
                throw new ResourceNotFoundException("cleaner id: " + cleanerRequest.getId());
            }
            cleaners.add(cleanerOptional.get());
        }

        booking.setCleaners(cleaners);
        return booking;
    }


}
