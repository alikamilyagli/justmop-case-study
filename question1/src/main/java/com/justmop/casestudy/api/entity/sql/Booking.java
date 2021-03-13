package com.justmop.casestudy.api.entity.sql;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.justmop.casestudy.api.entity.DateAudit;
import com.justmop.casestudy.api.entity.enums.BookingType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Entity object representing a cleaner.
 *
 * @author Ali Kamil YAĞLI
 */
@Entity
@Table(name = "bookings", indexes = @Index(name = "booking_datetime_idx", columnList = "bookingDate, bookingTime"))
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, exclude = "cleaners")
@AllArgsConstructor
@NoArgsConstructor
public class Booking extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BookingType bookingType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookingDate;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime bookingTime;

    @ManyToMany
    @JoinTable(name="booking_cleaners",
            joinColumns=@JoinColumn(name="booking_id"),
            inverseJoinColumns=@JoinColumn(name="cleaner_id")
    )
    private Collection<Cleaner> cleaners = new ArrayList<Cleaner>();

    private long getDuration() {
        return this.bookingType.getHours();
    }

    @JsonIgnore
    public LocalDateTime getBookingDateTime() {
        return LocalDateTime.of(this.bookingDate, this.bookingTime);
    }

    public Booking(BookingType bookingType,
                   LocalDate bookingDate,
                   LocalTime bookingTime,
                   Cleaner... cleaners) {
        this.bookingType = bookingType;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.cleaners = Stream.of(cleaners).collect(Collectors.toList());
    }
}
