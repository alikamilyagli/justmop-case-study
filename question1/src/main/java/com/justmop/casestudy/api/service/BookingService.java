package com.justmop.casestudy.api.service;

import com.justmop.casestudy.api.entity.sql.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Service interface for {@link Booking} model
 *
 * @author Ali Kamil YAĞLI
 */
public interface BookingService {

    /**
     * Find bookings method
     * Returns an iterable collection
     * @return
     */
    public abstract ArrayList<Booking> findAll();

    /**
     * Find bookings method
     * Returns a paged collection for given pageable object
     *
     * @param pageable
     * @return
     */
    public abstract Page<Booking> findAll(Pageable pageable);

    /**
     * Returns element collection size
     * @return
     */
    public abstract long count();

    /**
     * Find single booking by id method
     * Returns booking details for given booking id
     *
     * @param id
     * @return
     */
    public abstract Optional<Booking> findById(Long id);

    /**
     * Save booking method
     * Persists given booking
     *
     * @param booking
     * @return
     */
    public abstract Booking save(Booking booking);

    /**
     * Delete booking method
     * Removes given booking from persistence layer.
     *
     * @param booking
     */
    public abstract void delete(Booking booking);

    /**
     * Delete booking by id method
     * Removes booking from persistence layer for given booking id.
     *
     * @param id
     */
    public abstract void deleteById(Long id);


}
