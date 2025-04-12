package org.example.services;

import org.example.models.Event;

import java.util.List;
import java.util.stream.Collectors;

public class EventService {
    private final List<Event> events;

    public EventService(List<Event> events) {
        this.events = events;
    }

    public List<Event> getAllEvents() {
        return events;
    }

    public Event getEventByName(String name) {
        return events.stream()
                .filter(event -> event.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public List<Event> getEventsByCountry(String country) {
        return events.stream()
                .filter(event -> event.getCircuit().getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());
    }

    public Event getEventByDate(String date) {
        return events.stream()
                .filter(event -> (event.getRaceDate() != null && event.getRaceDate().equals(date)) ||
                        (event.getSprintDate() != null && event.getSprintDate().equals(date)))
                .findFirst()
                .orElse(null);
    }
}