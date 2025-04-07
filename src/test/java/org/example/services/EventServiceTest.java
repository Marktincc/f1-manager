package org.example.services;

import org.example.models.Circuit;
import org.example.models.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventServiceTest {
    private EventService eventService;
    private List<Event> testEvents;

    @BeforeEach
    void setUp() {
        testEvents = new ArrayList<>();
        Circuit circuit1 = new Circuit("Monza", "Italia", 5.793);
        Circuit circuit2 = new Circuit("Silverstone", "Reino Unido", 5.891);

        Event event1 = new Event("Gran Premio de Italia", circuit1, "2023-09-03", null, 1);
        Event event2 = new Event("British Grand Prix", circuit2, "2023-07-09", null, 2);


        testEvents.add(event1);
        testEvents.add(event2);

        eventService = new EventService(testEvents);
    }

    @Test
    void getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        assertNotNull(events);
        assertEquals(2, events.size());
    }

    @Test
    void getEventByName() {
        Event event = eventService.getEventByName("Gran Premio de Italia");
        assertNotNull(event);
        assertEquals("Monza", event.getCircuit().getName());
    }

    @Test
    void getEventsByCountry() {
        List<Event> italianEvents = eventService.getEventsByCountry("Italia");
        assertNotNull(italianEvents);
        assertEquals(1, italianEvents.size());
        assertEquals("Monza", italianEvents.get(0).getCircuit().getName());
    }

    @Test
    void getEventByDate() {
        Event event = eventService.getEventByDate("2023-09-03");
        assertNotNull(event);
        assertEquals("Gran Premio de Italia", event.getName());
    }
}
