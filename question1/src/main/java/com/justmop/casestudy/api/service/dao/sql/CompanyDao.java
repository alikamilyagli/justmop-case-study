package com.justmop.casestudy.api.service.dao.sql;

import com.justmop.casestudy.api.entity.sql.Company;
import com.justmop.casestudy.api.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Service implementation for {@link Company} model
 *
 * @author Ali Kamil YAĞLI
 */
@Service
public class CompanyDao extends BaseDao implements CompanyService {

    public ArrayList<Company> findAll() {
        return (ArrayList<Company>) companyRepository.findAll();
    }

    public Page<Company> findAll(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    public long count() {
        return companyRepository.count();
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }

    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

    public void delete(Company company) {
        companyRepository.delete(company);
    }



}
