package com.meetingplanner.controller;

import com.meetingplanner.payload.IsMeetingPossibleResponse;
import com.meetingplanner.services.TimeSlotService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

@RestController
@RequestMapping("meeting/plan")
public class PlanMeetingController {

    @GetMapping("/ispossible")
    public IsMeetingPossibleResponse checkIfMeetingIsPossible(
            @RequestParam("attendees") int[] attendees,
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) throws IOException {
        var service = new TimeSlotService();
        var allAttendeesHaveTime =
                Arrays.stream(attendees).allMatch(ownerId -> {
                    try {
                        return service.getTimeSlotsFor(ownerId, from, to).length == 0;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        return new IsMeetingPossibleResponse(allAttendeesHaveTime);
    }
}
