package com.justmop.casestudy.api.entity.sql;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.justmop.casestudy.api.entity.DateAudit;
import com.justmop.casestudy.api.entity.http.WorkTime;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * Entity object representing a cleaner.
 *
 * @author Ali Kamil YAĞLI
 */
@Entity
@Table(name = "cleaners")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Cleaner extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Company company;

    private String firstName;
    private String lastName;
    private DayOfWeek holiday = DayOfWeek.FRIDAY;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime workingHourStart;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime workingHourEnd;

    @JsonIgnore
    @ManyToMany(mappedBy = "cleaners")
    @MapKey(name = "id")
    private Map<Long, Booking> bookings = new HashMap<Long, Booking>();

    @Transient
    private ArrayList<WorkTime> occupiedTimes;

    @JsonIgnore
    public LocalTime getExactWorkingHourStart() {
        return workingHourStart.isAfter(company.getWorkingHourStart()) ?
                workingHourStart : getCompany().getWorkingHourStart();
    }

    @JsonIgnore
    public LocalTime getExactWorkingHourEnd() {
        return workingHourEnd.isBefore(company.getWorkingHourEnd()) ?
                workingHourEnd : getCompany().getWorkingHourEnd();
    }

    @PostLoad
    private void postLoad() {
        this.occupiedTimes = new ArrayList<>();

        this.bookings.forEach((aLong, booking) -> {
            if (booking.getBookingDateTime().isBefore(LocalDateTime.now())) {
                return;
            }

            WorkTime workTime = new WorkTime(
                    booking,
                    booking.getBookingDate(),
                    booking.getBookingTime(),
                    booking.getBookingTime().plusHours(booking.getBookingType().getHours()));
            this.occupiedTimes.add(workTime);
        });
    }
}
