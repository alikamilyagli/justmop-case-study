package com.justmop.casestudy.api.service;

import com.justmop.casestudy.api.entity.sql.Booking;
import com.justmop.casestudy.api.entity.sql.Cleaner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Service interface for {@link Cleaner} model
 *
 * @author Ali Kamil YAĞLI
 */
public interface CleanerService {

    /**
     * Find cleaners method
     * Returns an iterable collection
     * @return
     */
    public abstract ArrayList<Cleaner> findAll();

    /**
     * Find cleaners method
     * Returns an iterable collection
     * @return
     */
    public abstract ArrayList<Cleaner> findAvailableCleaners(LocalDateTime dateTime);

    /**
     * Find cleaners method
     * Returns a paged collection for given pageable object
     *
     * @param pageable
     * @return
     */
    public abstract Page<Cleaner> findAll(Pageable pageable);

    /**
     * Find cleaners by company id
     * Returns a paged collection for given company id
     * @param companyId
     * @param pageable
     * @return
     */
    public abstract Page<Cleaner> findByCompanyId(Long companyId, Pageable pageable);

    /**
     * Returns element collection size
     * @return
     */
    public abstract long count();

    /**
     * Find single Cleaner by id method
     * Returns Cleaner details for given Cleaner id
     *
     * @param id
     * @return
     */
    public abstract Optional<Cleaner> findById(Long id);

    /**
     * Save Cleaner method
     * Persists given Cleaner
     *
     * @param cleaner
     * @return
     */
    public abstract Cleaner save(Cleaner cleaner);

    /**
     * Delete Cleaner method
     * Removes given Cleaner from persistence layer.
     *
     * @param Cleaner
     */
    public abstract void delete(Cleaner Cleaner);

    /**
     * Delete Cleaner by id method
     * Removes Cleaner from persistence layer for given Cleaner id.
     *
     * @param id
     */
    public abstract void deleteById(Long id);


    /**
     * Checks if a cleaner is available at given date time
     *
     * @param booking
     * @param cleaner
     * @param date
     * @param time
     * @return
     */
    public abstract boolean isAvailable(Booking booking, Cleaner cleaner, LocalDate date, LocalTime time);

}
