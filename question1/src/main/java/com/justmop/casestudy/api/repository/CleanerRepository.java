package com.justmop.casestudy.api.repository;

import com.justmop.casestudy.api.entity.sql.Cleaner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for {@link Cleaner} instances.
 *
 * @author Ali Kamil YAĞLI
 */
@Repository
public interface CleanerRepository extends PagingAndSortingRepository<Cleaner, Long> {

    Page<Cleaner> findByCompanyId(Long companyId, Pageable pageable);
    Optional<Cleaner> findByIdAndCompanyId(Long id, Long companyId);

}
