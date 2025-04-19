package org.example.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    private Event event;
    private Circuit circuit;

    @BeforeEach
    void setUp() {
        circuit = new Circuit("Silverstone", "United Kingdom", 5.891);
        event = new Event("British Grand Prix", circuit, "2024-07-07", "2024-07-06", 10);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("British Grand Prix", event.getName());
        assertEquals(circuit, event.getCircuit());
        assertEquals("2024-07-07", event.getRaceDate());
        assertEquals("2024-07-06", event.getSprintDate());
        assertEquals(10, event.getRaceNumber());
    }

    @Test
    void testSetters() {
        Circuit newCircuit = new Circuit("Monza", "Italy", 5.793);
        event.setName("Italian Grand Prix");
        event.setCircuit(newCircuit);
        event.setRaceDate("2024-09-01");
        event.setSprintDate("2024-08-31");
        event.setRaceNumber(14);

        assertEquals("Italian Grand Prix", event.getName());
        assertEquals(newCircuit, event.getCircuit());
        assertEquals("2024-09-01", event.getRaceDate());
        assertEquals("2024-08-31", event.getSprintDate());
        assertEquals(14, event.getRaceNumber());
    }

    @Test
    void testToString() {
        String expected = "Evento: British Grand Prix\n" +
                "Circuito: " + circuit.toString() + "\n" +
                "Fecha carrera: 2024-07-07\n" +
                "Fecha sprint: 2024-07-06\n" +
                "Número de carrera: 10";
        assertEquals(expected, event.toString());
    }

    @Test
    void testToStringWithoutSprintDate() {
        event.setSprintDate(null);
        String expected = "Evento: British Grand Prix\n" +
                "Circuito: " + circuit.toString() + "\n" +
                "Fecha carrera: 2024-07-07\n" +
                "Número de carrera: 10";
        assertEquals(expected, event.toString());
    }

    @Test
    void testEmptyConstructor() {
        Event emptyEvent = new Event();
        assertNull(emptyEvent.getName());
        assertNull(emptyEvent.getCircuit());
        assertNull(emptyEvent.getRaceDate());
        assertNull(emptyEvent.getSprintDate());
        assertEquals(0, emptyEvent.getRaceNumber());
    }
}