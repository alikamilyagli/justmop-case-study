package com.justmop.casestudy.api.controller;

import com.justmop.casestudy.api.entity.http.BookingDateRequest;
import com.justmop.casestudy.api.entity.sql.Booking;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Booking Controller.
 *
 * @author Ali Kamil YAĞLI
 */
@RestController
@RequestMapping({"/api/booking"})
public class BookingController extends BaseController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * List method
     * Returns a paged booking collection
     *
     * @param size
     * @param page
     * @param sortBy
     * @param direction
     * @return
     */
    @ApiOperation(value = "View a list of bookings", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 400, message = "An error occurred on client"),
            @ApiResponse(code = 500, message = "An error occurred on server")
    })
    @GetMapping
    @ResponseBody
    public ResponseEntity<Page<Booking>> getBookings(@RequestParam(required = false, defaultValue = "10") int size,
                                      @RequestParam(required = false, defaultValue = "0") int page,
                                      @RequestParam(required = false, defaultValue = "id") String sortBy,
                                      @RequestParam(required = false, defaultValue = "DESC") String direction) {
        Pageable pageable;
        if (direction.equals("DESC")) {
            pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        }

        Page<Booking> bookings = bookingService.findAll(pageable);
        LOGGER.info("Bookings retrieved");
        return ResponseEntity.ok().body(bookings);
    }

    /**
     * Find method
     * Returns details of booking for given booking id
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "View details of a booking", response = Booking.class)
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
    public ResponseEntity<Booking> getBooking(@PathVariable long id) {
        Optional<Booking> bookingOptional = bookingService.findById(id);
        if(!bookingOptional.isPresent()) {
            LOGGER.info("Booking not found id: {}", id);
            return ResponseEntity.notFound().build();
        }

        LOGGER.info("Booking retrieved id: {}", id);
        return ResponseEntity.ok().body(bookingOptional.get());
    }

    /**
     * Create method
     * Returns the details of created booking
     *
     * @param booking
     * @return
     */
    @ApiOperation(value = "Create a new booking", response = Booking.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created", response = Booking.class),
            @ApiResponse(code = 400, message = "An error occurred on client"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "An error occurred on server")
    })
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
        try {
            booking = bookingService.save(booking);
            LOGGER.info("Booking created id: {}", booking.getId());
            return ResponseEntity.ok().body(booking);
        } catch (Exception e) {
            LOGGER.error("Booking creat failed. Reason: {}", e.getMessage());
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update method
     * Returns the updated booking details
     *
     * @param id
     * @param booking
     * @return
     */
    @ApiOperation(value = "Update existing booking", response = Booking.class)
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
    public ResponseEntity<Booking> updateBooking(@PathVariable("id") long id,
                                                 @RequestBody BookingDateRequest bookingDateRequest) {
        Optional<Booking> bookingOptional = bookingService.findById(id);
        if(!bookingOptional.isPresent()) {
            LOGGER.info("Booking not found for update id: {}", id);
            return ResponseEntity.notFound().build();
        }

        Booking booking = bookingOptional.get();
        booking.setBookingDate(bookingDateRequest.getBookingDate());
        booking.setBookingTime(bookingDateRequest.getBookingTime());
        booking = bookingService.save(booking);
        LOGGER.info("Booking updated id: {}", booking.getId());
        return ResponseEntity.ok().body(booking);
    }

    @ApiOperation(value = "Delete booking")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 400, message = "An error occurred on client"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "An error occurred on server")
    })
    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> deleteBooking(@PathVariable("id") long id) {
        Optional<Booking> bookingOptional = bookingService.findById(id);
        if(!bookingOptional.isPresent()) {
            LOGGER.info("Booking not found for delete id: {}", id);
            return ResponseEntity.notFound().build();
        }

        bookingService.deleteById(id);
        LOGGER.info("Booking deleted id: {}", id);
        return ResponseEntity.ok().build();
    }
}
