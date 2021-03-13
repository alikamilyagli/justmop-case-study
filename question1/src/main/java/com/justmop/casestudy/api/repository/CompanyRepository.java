package com.justmop.casestudy.api.repository;

import com.justmop.casestudy.api.entity.sql.Company;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link Company} instances.
 *
 * @author Ali Kamil YAĞLI
 */
@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {

}
