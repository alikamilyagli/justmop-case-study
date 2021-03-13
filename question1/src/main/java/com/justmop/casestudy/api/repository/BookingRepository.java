package com.justmop.casestudy.api.repository;

import com.justmop.casestudy.api.entity.sql.Booking;
import com.justmop.casestudy.api.entity.sql.Cleaner;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Repository interface for {@link Cleaner} instances.
 *
 * @author Ali Kamil YAĞLI
 */
@Repository
public interface BookingRepository extends PagingAndSortingRepository<Booking, Long> {

    ArrayList<Booking> findAllByBookingDateAndCleaners(LocalDate bookingDate, Cleaner cleaner);

}
