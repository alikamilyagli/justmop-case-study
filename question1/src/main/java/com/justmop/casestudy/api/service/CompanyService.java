package com.justmop.casestudy.api.service;

import com.justmop.casestudy.api.entity.sql.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Service interface for {@link Company} model
 *
 * @author Ali Kamil YAĞLI
 */
public interface CompanyService {

    /**
     * Find companies method
     * Returns an iterable collection
     * @return
     */
    public abstract ArrayList<Company> findAll();

    /**
     * Find companies method
     * Returns a paged collection for given pageable object
     *
     * @param pageable
     * @return
     */
    public abstract Page<Company> findAll(Pageable pageable);

    /**
     * Returns element collection size
     * @return
     */
    public abstract long count();

    /**
     * Find single company by id method
     * Returns company details for given company id
     *
     * @param id
     * @return
     */
    public abstract Optional<Company> findById(Long id);

    /**
     * Save company method
     * Persists given company
     *
     * @param company
     * @return
     */
    public abstract Company save(Company company);

    /**
     * Delete company method
     * Removes given company from persistence layer.
     *
     * @param company
     */
    public abstract void delete(Company company);

    /**
     * Delete company by id method
     * Removes company from persistence layer for given company id.
     *
     * @param id
     */
    public abstract void deleteById(Long id);


}
