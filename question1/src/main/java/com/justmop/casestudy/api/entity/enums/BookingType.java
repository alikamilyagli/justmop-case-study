package com.justmop.casestudy.api.entity.enums;

public enum BookingType {
    TWO_HOURS(2),
    FOUR_HOURS(4);

    private final long hours;

    BookingType(final long hours) {
        this.hours = hours;
    }

    public long getHours() { return hours; }
}