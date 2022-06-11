package com.timeslot.timeslotapi.controller;

import com.timeslot.timeslotapi.payload.TimeSlot;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/timeslot")
public class TimeSlotController {

    private static ArrayList<TimeSlot> timeSlotStorage =
            new ArrayList<>() {
                {
                    add(TimeSlot.from("2022-06-11T15:43", "2022-06-11T16:43", 1));
                    add(TimeSlot.from("2022-06-11T17:43", "2022-06-11T18:43", 1));
                    add(TimeSlot.from("2022-06-11T19:43", "2022-06-11T19:53", 1));
                    add(TimeSlot.from("2022-06-11T10:43", "2022-06-11T11:43", 4));
                    add(TimeSlot.from("2022-06-11T15:43", "2022-06-11T16:43", 4));
                    add(TimeSlot.from("2022-06-11T16:43", "2022-06-11T16:43", 5));
                    add(TimeSlot.from("2022-06-11T17:20", "2022-06-11T16:43", 5));
                    add(TimeSlot.from("2022-06-11T09:00", "2022-06-11T10:40", 6));
                    add(TimeSlot.from("2022-06-11T13:12", "2022-06-11T18:43", 6));
                    add(TimeSlot.from("2022-06-11T22:45", "2022-06-11T23:43", 6));
                    add(TimeSlot.from("2022-06-11T16:43", "2022-06-11T16:40", 7));
                    add(TimeSlot.from("2022-06-11T15:12", "2022-06-11T16:40", 8));
                    add(TimeSlot.from("2022-06-11T12:43", "2022-06-11T15:43", 9));
                    add(TimeSlot.from("2022-06-11T16:43", "2022-06-11T18:43", 9));
                }
            };

    @GetMapping("/all")
    public List<TimeSlot> getAllTimeslots() {
        return timeSlotStorage;
    }

    @GetMapping("{ownerId}")
    public List<TimeSlot>  getTimeSlotsByOwner(
            @PathVariable("ownerId") int ownerId,
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return timeSlotStorage.stream()
                .filter(t -> t.getOwnerId() == ownerId)
                .filter(t -> overlapsWith(from, to, t))
                .toList();
    }

    private boolean overlapsWith(LocalDateTime from, LocalDateTime to, TimeSlot t) {
        return ((t.getFrom().isAfter(from) || t.getFrom().isEqual(from)) && t.getFrom().isBefore(to)) ||
                (t.getTo().isAfter(from) && (t.getTo().isBefore(to) || t.getTo().isEqual(to)));
    }
}
