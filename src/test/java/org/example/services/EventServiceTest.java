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
    private List<Event> events;
    private Event monacoGP;
    private Event britishGP;
    private Event italianGP;

    @BeforeEach
    void setUp() {
        events = new ArrayList<>();
        
        Circuit monaco = new Circuit("Monaco", "Monaco", 3.337);
        Circuit silverstone = new Circuit("Silverstone", "United Kingdom", 5.891);
        Circuit monza = new Circuit("Monza", "Italy", 5.793);
        
        monacoGP = new Event("Monaco Grand Prix", monaco, "2024-05-26", null, 7);
        britishGP = new Event("British Grand Prix", silverstone, "2024-07-07", "2024-07-06", 10);
        italianGP = new Event("Italian Grand Prix", monza, "2024-09-01", "2024-08-31", 14);
        
        events.add(monacoGP);
        events.add(britishGP);
        events.add(italianGP);
        
        eventService = new EventService(events);
    }

    @Test
    void testGetAllEvents() {
        List<Event> result = eventService.getAllEvents();
        assertEquals(3, result.size());
        assertTrue(result.contains(monacoGP));
        assertTrue(result.contains(britishGP));
        assertTrue(result.contains(italianGP));
    }

    @Test
    void testGetEventByName() {
        Event result = eventService.getEventByName("Monaco Grand Prix");
        assertNotNull(result);
        assertEquals(monacoGP, result);
        
        // Test case insensitive search
        result = eventService.getEventByName("monaco grand prix");
        assertNotNull(result);
        assertEquals(monacoGP, result);
        
        // Test non-existent event
        result = eventService.getEventByName("Spanish Grand Prix");
        assertNull(result);
    }

    @Test
    void testGetEventsByCountry() {
        List<Event> ukEvents = eventService.getEventsByCountry("United Kingdom");
        assertEquals(1, ukEvents.size());
        assertEquals(britishGP, ukEvents.get(0));
        
        // Test case insensitive search
        List<Event> italyEvents = eventService.getEventsByCountry("ITALY");
        assertEquals(1, italyEvents.size());
        assertEquals(italianGP, italyEvents.get(0));
        
        // Test non-existent country
        List<Event> nonExistentEvents = eventService.getEventsByCountry("Spain");
        assertTrue(nonExistentEvents.isEmpty());
    }

    @Test
    void testGetEventByDate() {
        // Test race date search
        Event result = eventService.getEventByDate("2024-05-26");
        assertNotNull(result);
        assertEquals(monacoGP, result);
        
        // Test sprint date search
        result = eventService.getEventByDate("2024-07-06");
        assertNotNull(result);
        assertEquals(britishGP, result);
        
        // Test non-existent date
        result = eventService.getEventByDate("2024-12-25");
        assertNull(result);
    }

    @Test
    void testWithEmptyEventsList() {
        EventService emptyService = new EventService(new ArrayList<>());
        
        assertTrue(emptyService.getAllEvents().isEmpty());
        assertNull(emptyService.getEventByName("Any Name"));
        assertTrue(emptyService.getEventsByCountry("Any Country").isEmpty());
        assertNull(emptyService.getEventByDate("2024-01-01"));
    }
}
