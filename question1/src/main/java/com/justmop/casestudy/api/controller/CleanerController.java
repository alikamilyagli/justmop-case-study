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

import java.util.ArrayList;
import java.util.Optional;

/**
 * Cleaner Controller.
 *
 * @author Ali Kamil YAĞLI
 */
@RestController
@RequestMapping({"/api/cleaner"})
public class CleanerController extends BaseController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * List method
     * Returns a paged cleaner collection
     *
     * @param size
     * @param page
     * @param sortBy
     * @param direction
     * @return
     */
    @ApiOperation(value = "View a list of cleaners", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 400, message = "An error occurred on client"),
            @ApiResponse(code = 500, message = "An error occurred on server")
    })
    @GetMapping
    @ResponseBody
    public ResponseEntity<Page<Cleaner>> getCleaners(@RequestParam(required = false, defaultValue = "10") int size,
                                      @RequestParam(required = false, defaultValue = "0") int page,
                                      @RequestParam(required = false, defaultValue = "id") String sortBy,
                                      @RequestParam(required = false, defaultValue = "DESC") String direction) {
        Pageable pageable;
        if (direction.equals("DESC")) {
            pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        }

        Page<Cleaner> cleaners = cleanerService.findAll(pageable);
        LOGGER.info("Cleaners retrieved");
        return ResponseEntity.ok().body(cleaners);
    }

    /**
     * Find method
     * Returns details of cleaner for given cleaner id
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "View details of a cleaner", response = Cleaner.class)
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
    public ResponseEntity<Cleaner> getCleaner(@PathVariable long id) {
        Optional<Cleaner> cleanerOptional = cleanerService.findById(id);
        if(!cleanerOptional.isPresent()) {
            LOGGER.info("Cleaner not found id: {}", id);
            return ResponseEntity.notFound().build();
        }

        LOGGER.info("Cleaner retrieved id: {}", id);
        return ResponseEntity.ok().body(cleanerOptional.get());
    }

    /**
     * Create method
     * Returns the details of created cleaner
     *
     * @param cleaner
     * @return
     */
    @ApiOperation(value = "Create a new cleaner", response = Cleaner.class)
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
    public ResponseEntity<Cleaner> createCleaner(@RequestBody Cleaner cleaner) {
        try {
            Optional<Company> companyOptional = companyService.findById(cleaner.getCompany().getId());
            if(!companyOptional.isPresent()) {
                LOGGER.info("Company not found id: {}", cleaner.getCompany().getId());
                return ResponseEntity.notFound().build();
            }

            cleaner = cleanerService.save(cleaner);
            LOGGER.info("Cleaner created id: {}", cleaner.getId());
        } catch (Exception e) {
            LOGGER.error("Cleaner creat failed. Reason: {}", e.getMessage());
        }
        return ResponseEntity.ok().body(cleaner);
    }

    /**
     * Update method
     * Returns the updated cleaner details
     *
     * @param id
     * @param cleaner
     * @return
     */
    @ApiOperation(value = "Update existing cleaner", response = Cleaner.class)
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
    public ResponseEntity<Cleaner> updateCleaner(@PathVariable("id") long id,
                                                 @RequestBody Cleaner cleaner) {
        Optional<Cleaner> cleanerOptional = cleanerService.findById(id);
        if(!cleanerOptional.isPresent()) {
            LOGGER.info("Cleaner not found for update id: {}", id);
            return ResponseEntity.notFound().build();
        }

        cleaner.setId(cleanerOptional.get().getId());
        Cleaner updatedCleaner = cleanerService.save(cleaner);
        LOGGER.info("Cleaner updated id: {}", cleaner.getId());
        return ResponseEntity.ok().body(updatedCleaner);
    }

    @ApiOperation(value = "Delete cleaner")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 400, message = "An error occurred on client"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "An error occurred on server")
    })
    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> deleteCleaner(@PathVariable("id") long id) {
        Optional<Cleaner> cleanerOptional = cleanerService.findById(id);
        if(!cleanerOptional.isPresent()) {
            LOGGER.info("Cleaner not found for delete id: {}", id);
            return ResponseEntity.notFound().build();
        }

        cleanerService.deleteById(id);
        LOGGER.info("Cleaner deleted id: {}", id);
        return ResponseEntity.ok().build();
    }
}
