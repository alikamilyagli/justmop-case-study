package com.justmop.casestudy.api.service.dao.sql;

import com.justmop.casestudy.api.entity.sql.Booking;
import com.justmop.casestudy.api.entity.sql.Cleaner;
import com.justmop.casestudy.api.entity.sql.Company;
import com.justmop.casestudy.api.exception.CleanerNotAvailableException;
import com.justmop.casestudy.api.service.CleanerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Service implementation for {@link Cleaner} model
 *
 * @author Ali Kamil YAĞLI
 */
@Service
public class CleanerDao extends BaseDao implements CleanerService {

    public ArrayList<Cleaner> findAll() {
        return (ArrayList<Cleaner>) cleanerRepository.findAll();
    }

    @Override
    public ArrayList<Cleaner> findAvailableCleaners(LocalDateTime dateTime) {
        return null;
    }

    public Page<Cleaner> findAll(Pageable pageable) {
        return cleanerRepository.findAll(pageable);
    }

    public Page<Cleaner> findByCompanyId(Long companyId, Pageable pageable) {
        return cleanerRepository.findByCompanyId(companyId, pageable);
    }

    public long count() {
        return cleanerRepository.count();
    }

    public Cleaner save(Cleaner cleaner) {
        Company company = companyRepository.findById(cleaner.getCompany().getId()).get();
        cleaner.setCompany(company);
        return cleanerRepository.save(cleaner);
    }

    public Optional<Cleaner> findById(Long id) {
        return cleanerRepository.findById(id);
    }

    public void deleteById(Long id) {
        cleanerRepository.deleteById(id);
    }

    public boolean isAvailable(Booking booking, Cleaner cleaner, LocalDate date, LocalTime time) {
        if (date.getDayOfWeek().equals(cleaner.getHoliday())) {
            return false;
        }

        if (cleaner.getExactWorkingHourStart().isAfter(time)) {
            throw new CleanerNotAvailableException("Cleaner working hour starts at: " + cleaner.getExactWorkingHourStart());
        }

        if (cleaner.getExactWorkingHourEnd().isBefore(time.plusHours(booking.getBookingType().getHours()))) {
            throw new CleanerNotAvailableException("Cleaner working hour ends at: " + cleaner.getExactWorkingHourEnd());
        }

        cleaner.getOccupiedTimes().forEach(workTime -> {
            if (workTime.getDate().equals(date) &&
                ((workTime.getStartTime().isBefore(time)) || (workTime.getStartTime().equals(time))) &&
                workTime.getEndTime().isAfter(time) &&
                !booking.equals(workTime.getBooking())) {
                throw new CleanerNotAvailableException("Cleaner id "+cleaner.getId()+" is occupied at: " + date + " " + time);
            }
        });

        return true;
    }

    public void delete(Cleaner cleaner) {
        cleanerRepository.delete(cleaner);
    }



}
