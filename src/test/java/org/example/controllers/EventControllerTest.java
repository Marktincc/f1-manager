package org.example.controllers;

import org.example.models.Circuit;
import org.example.models.Event;
import org.example.services.EventService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventControllerTest {
    private EventController eventController;
    private EventService eventService;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    private List<Event> testEvents;

    @BeforeEach
    void setUp() {
        // Setup output capture
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Create test circuits
        Circuit monaco = new Circuit("Monaco", "Monaco", 3.337);
        monaco.setCity("Monte Carlo");
        monaco.setLaps(78);

        Circuit silverstone = new Circuit("Silverstone", "United Kingdom", 5.891);
        silverstone.setCity("Silverstone");
        silverstone.setLaps(52);

        // Create test events
        Event monacoGP = new Event("Monaco Grand Prix", monaco, "2024-05-26", null, 7);
        Event britishGP = new Event("British Grand Prix", silverstone, "2024-07-07", "2024-07-06", 10);

        testEvents = new ArrayList<>(Arrays.asList(monacoGP, britishGP));
        eventService = new EventService(testEvents);
        eventController = new EventController(eventService);
    }

    @Test
    void testMostrarInformacionGeneralCarreras() {
        eventController.mostrarInformacionGeneralCarreras();
        
        String output = outputStream.toString();
        
        // Verify Monaco GP information
        assertTrue(output.contains("Carrera #7: Monaco Grand Prix"));
        assertTrue(output.contains("Circuito: Monaco"));
        assertTrue(output.contains("País: Monaco"));
        assertTrue(output.contains("Ciudad: Monte Carlo"));
        assertTrue(output.contains("Longitud: 3,337 km"));
        assertTrue(output.contains("Vueltas: 78"));
        assertTrue(output.contains("Fecha carrera: 2024-05-26"));
        assertTrue(output.contains("No hay sprint"));
        
        // Verify British GP information
        assertTrue(output.contains("Carrera #10: British Grand Prix"));
        assertTrue(output.contains("Circuito: Silverstone"));
        assertTrue(output.contains("País: United Kingdom"));
        assertTrue(output.contains("Ciudad: Silverstone"));
        assertTrue(output.contains("Longitud: 5,891 km"));
        assertTrue(output.contains("Vueltas: 52"));
        assertTrue(output.contains("Fecha carrera: 2024-07-07"));
        assertTrue(output.contains("Fecha sprint: 2024-07-06"));
    }

    @Test
    void testMostrarInformacionGeneralCarrerasWithEmptyList() {
        // Create a controller with an empty list
        EventController emptyController = new EventController(new EventService(new ArrayList<>()));
        
        // Reset output stream
        outputStream.reset();
        
        emptyController.mostrarInformacionGeneralCarreras();
        
        String output = outputStream.toString().trim();
        assertEquals("==================================================", output);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}
