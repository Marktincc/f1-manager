package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Season {
    @JsonProperty("temporada")
    private int year;

    @JsonProperty("eventos")
    private List<Event> events;

    // Default constructor for Jackson
    public Season() {
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}