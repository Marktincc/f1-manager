package org.example.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SeasonTest {
    private Season season;
    private List<Event> events;
    private Event event1;
    private Event event2;

    @BeforeEach
    void setUp() {
        season = new Season();
        events = new ArrayList<>();
        
        Circuit circuit1 = new Circuit("Silverstone", "United Kingdom", 5.891);
        Circuit circuit2 = new Circuit("Monza", "Italy", 5.793);
        
        event1 = new Event("British Grand Prix", circuit1, "2024-07-07", "2024-07-06", 10);
        event2 = new Event("Italian Grand Prix", circuit2, "2024-09-01", "2024-08-31", 14);
        
        events.add(event1);
        events.add(event2);
    }

    @Test
    void testDefaultConstructor() {
        Season defaultSeason = new Season();
        assertEquals(0, defaultSeason.getYear());
        assertNull(defaultSeason.getEvents());
    }

    @Test
    void testSettersAndGetters() {
        season.setYear(2024);
        season.setEvents(events);

        assertEquals(2024, season.getYear());
        assertNotNull(season.getEvents());
        assertEquals(2, season.getEvents().size());
        assertEquals(event1, season.getEvents().get(0));
        assertEquals(event2, season.getEvents().get(1));
    }

    @Test
    void testYearSetter() {
        season.setYear(2025);
        assertEquals(2025, season.getYear());
    }

    @Test
    void testEventsList() {
        season.setEvents(new ArrayList<>());
        assertTrue(season.getEvents().isEmpty());

        season.setEvents(events);
        assertEquals(2, season.getEvents().size());
        
        List<Event> newEvents = new ArrayList<>();
        Event event3 = new Event("Spanish Grand Prix", 
                               new Circuit("Barcelona", "Spain", 4.675),
                               "2024-06-23", 
                               null, 
                               8);
        newEvents.add(event3);
        season.setEvents(newEvents);
        
        assertEquals(1, season.getEvents().size());
        assertEquals(event3, season.getEvents().get(0));
    }
}