package com.justmop.casestudy.api.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class CleanerDto {
    private Long id;
    private Long companyId;
    private String firstName;
    private String lastName;
    private DayOfWeek holiday;
    private LocalTime workingHourStart;
    private LocalTime workingHourEnd;
}
