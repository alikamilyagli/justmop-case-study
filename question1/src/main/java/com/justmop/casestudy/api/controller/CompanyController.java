package com.justmop.casestudy.api.controller;

import com.justmop.casestudy.api.entity.sql.Cleaner;
import com.justmop.casestudy.api.entity.sql.Company;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Company Controller.
 *
 * @author Ali Kamil YAĞLI
 */
@RestController
@RequestMapping({"/api/company"})
public class CompanyController extends BaseController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * List method
     * Returns a paged company collection
     *
     * @param size
     * @param page
     * @param sortBy
     * @param direction
     * @return
     */
    @ApiOperation(value = "View a list of companies", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 400, message = "An error occurred on client"),
            @ApiResponse(code = 500, message = "An error occurred on server")
    })
    @GetMapping
    @ResponseBody
    public ResponseEntity<Page<Company>> getCompanies(@RequestParam(required = false, defaultValue = "10") int size,
                                      @RequestParam(required = false, defaultValue = "0") int page,
                                      @RequestParam(required = false, defaultValue = "id") String sortBy,
                                      @RequestParam(required = false, defaultValue = "DESC") String direction) {
        Pageable pageable;
        if (direction.equals("DESC")) {
            pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        }

        Page<Company> companies = companyService.findAll(pageable);
        LOGGER.info("Companies retrieved");
        return ResponseEntity.ok().body(companies);
    }

    /**
     * Find method
     * Returns details of company for given company id
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "View details of a company", response = Company.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved details"),
            @ApiResponse(code = 400, message = "An error occurred on client"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "An error occurred on server")
    })
    @GetMapping(path = {"/{id}"})
    @ResponseBody
    public ResponseEntity<Company> getCompany(@PathVariable long id) {
        Optional<Company> companyOptional = companyService.findById(id);
        if(!companyOptional.isPresent()) {
            LOGGER.info("Company not found id: {}", id);
            return ResponseEntity.notFound().build();
        }

        LOGGER.info("Company retrieved id: {}", id);
        return ResponseEntity.ok().body(companyOptional.get());
    }

    @ApiOperation(value = "View details of a company", response = Company.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved details"),
            @ApiResponse(code = 400, message = "An error occurred on client"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "An error occurred on server")
    })
    @GetMapping(path = {"/{id}/cleaners"})
    @ResponseBody
    public ResponseEntity<Page<Cleaner>> getCompanyCleaners(@PathVariable long id,
                                                      @RequestParam(required = false, defaultValue = "10") int size,
                                                      @RequestParam(required = false, defaultValue = "0") int page,
                                                      @RequestParam(required = false, defaultValue = "id") String sortBy,
                                                      @RequestParam(required = false, defaultValue = "DESC") String direction) {
        Pageable pageable;
        if (direction.equals("DESC")) {
            pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        }

        Page<Cleaner> cleaners = cleanerService.findByCompanyId(id, pageable);
        if(cleaners.getTotalElements() == 0) {
            LOGGER.info("No cleaner found for company id: {}", id);
            return ResponseEntity.notFound().build();
        }

        LOGGER.info("Cleaners retrieved for company id: {}", id);
        return ResponseEntity.ok().body(cleaners);
    }

    /**
     * Create method
     * Returns the details of created company
     *
     * @param company
     * @return
     */
    @ApiOperation(value = "Create a new company", response = Company.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created"),
            @ApiResponse(code = 400, message = "An error occurred on client"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "An error occurred on server")
    })
    @PostMapping
    @ResponseBody
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        try {
            company = companyService.save(company);
            LOGGER.info("Company created id: {}", company.getId());
        } catch (Exception e) {
            LOGGER.error("Company creat failed. Reason: {}", e.getMessage());
        }
        return ResponseEntity.ok().body(company);
    }

    /**
     * Update method
     * Returns the updated company details
     *
     * @param id
     * @param company
     * @return
     */
    @ApiOperation(value = "Update existing company", response = Company.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 400, message = "An error occurred on client"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "An error occurred on server")
    })
    @PutMapping(value="/{id}")
    @ResponseBody
    public ResponseEntity<Company> updateCompany(@PathVariable("id") long id,
                                                 @RequestBody Company company) {
        Optional<Company> companyOptional = companyService.findById(id);
        if(!companyOptional.isPresent()) {
            LOGGER.info("Company not found for update id: {}", id);
            return ResponseEntity.notFound().build();
        }

        company.setId(companyOptional.get().getId());
        Company updatedCompany = companyService.save(company);
        LOGGER.info("Company updated id: {}", company.getId());
        return ResponseEntity.ok().body(updatedCompany);
    }

    @ApiOperation(value = "Delete company")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 400, message = "An error occurred on client"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "An error occurred on server")
    })
    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> deleteCompany(@PathVariable("id") long id) {
        Optional<Company> companyOptional = companyService.findById(id);
        if(!companyOptional.isPresent()) {
            LOGGER.info("Company not found for delete id: {}", id);
            return ResponseEntity.notFound().build();
        }

        companyService.deleteById(id);
        LOGGER.info("Company deleted id: {}", id);
        return ResponseEntity.ok().build();
    }
}
