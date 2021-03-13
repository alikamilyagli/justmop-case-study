package com.justmop.casestudy.api.entity.sql;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.justmop.casestudy.api.entity.DateAudit;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

/**
 * Entity object representing a company.
 *
 * @author Ali Kamil YAĞLI
 */
@Entity
@Table(name = "companies")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Company extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime workingHourStart;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime workingHourEnd;
}
