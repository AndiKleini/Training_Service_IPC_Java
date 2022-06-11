package com.meetingplanner.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meetingplanner.services.entities.TimeSlot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

public class TimeSlotService {

    private ObjectMapper objectMapper = new ObjectMapper();

    public TimeSlot[] getTimeSlotsFor(int ownerId, LocalDateTime from, LocalDateTime to) throws IOException {
        HttpURLConnection connection = getTimeSlotsConnectionFor(ownerId, from, to);
        TimeSlot[] timeSlots = getTimeSlotsOf(connection);
        connection.disconnect();
        return timeSlots;
    }

    private HttpURLConnection getTimeSlotsConnectionFor
            (int ownerId, LocalDateTime from, LocalDateTime to) throws IOException {
        URL getTimeSlotsForCustomerUrl = new URL(
                String.format(
                        "http://localhost:8080/timeslot/%s?from=%s&to=%s",
                        ownerId,
                        from,
                        to));
        HttpURLConnection connection =
                (HttpURLConnection) getTimeSlotsForCustomerUrl.openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }

    private TimeSlot[] getTimeSlotsOf(HttpURLConnection con) throws IOException {
        BufferedReader responseSteamReader = null;
        StringBuffer content = null;
        TimeSlot[] timeSlots = null;
        try {
            responseSteamReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            content = new StringBuffer();
            while ((inputLine = responseSteamReader.readLine()) != null) {
                content.append(inputLine);
            }
            timeSlots = this.objectMapper.readValue(content.toString(), TimeSlot[].class);
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            if (responseSteamReader != null) {
                responseSteamReader.close();
            }
        }
        return timeSlots;
    }
}
