package com.timeslot.timeslotapi.payload;

import java.sql.Date;
import java.time.LocalDateTime;

public class TimeSlot {
    private LocalDateTime from;
    private LocalDateTime to;
    private int ownerId;

    public TimeSlot(LocalDateTime from, LocalDateTime to, int ownerId) {

        this.from = from;
        this.to = to;
        this.ownerId = ownerId;
    }

    public static TimeSlot from(String from, String to, int ownerId) {
        return new TimeSlot(LocalDateTime.parse(from), LocalDateTime.parse(to), ownerId);
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public int getOwnerId() {
        return ownerId;
    }
}
