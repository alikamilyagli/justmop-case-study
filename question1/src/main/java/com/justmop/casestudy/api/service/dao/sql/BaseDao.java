package com.justmop.casestudy.api.service.dao.sql;

import com.justmop.casestudy.api.repository.BookingRepository;
import com.justmop.casestudy.api.repository.CleanerRepository;
import com.justmop.casestudy.api.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Base Dao.
 * Contains sql repository objects
 *
 * @author Ali Kamil YAĞLI
 */
public abstract class BaseDao {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CleanerRepository cleanerRepository;

    @Autowired
    BookingRepository bookingRepository;
}
