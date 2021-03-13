package com.justmop.casestudy.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Pagination interface for all models.
 *
 * @author Ali Kamil YAĞLI
 */
@NoRepositoryBean
public interface PagingAndSortingRepository<T, ID> extends CrudRepository<T, ID> {
    /**
     * Returns a paged collection for given pageable object
     *
     * @param pageable
     * @return
     */
    Page<T> findAll(Pageable pageable);
}