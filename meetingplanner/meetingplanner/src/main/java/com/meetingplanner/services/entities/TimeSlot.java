package com.meetingplanner.services.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.LocalDateTime;

public class TimeSlot {
    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime from;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime to;
    private int ownerId;

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
