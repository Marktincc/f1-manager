package org.example.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Circuit;
import org.example.models.Event;
import org.example.models.Season;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.example.models.Circuit;
import org.example.models.Event;

import static org.junit.jupiter.api.Assertions.*;

class CircuitServiceTest {
    private CircuitService circuitService;
    private List<Circuit> testCircuits;

    @BeforeEach
    void setUp() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream circuitStream = getClass().getResourceAsStream("/circuitos_carreras.json");
            assertNotNull(circuitStream, "El archivo circuitos_carreras.json no se encontró.");

            Season season = mapper.readValue(circuitStream, Season.class);
            testCircuits = season.getEvents().stream()
                    .map(Event::getCircuit)
                    .collect(Collectors.toList());

            circuitService = new CircuitService(testCircuits);
        } catch (Exception e) {
            fail("Fallo al cargar los datos de prueba: " + e.getMessage());
        }
    }

    @Test
    void getAllCircuits() {
        List<Circuit> circuits = circuitService.getAllCircuits();
        assertNotNull(circuits);
        assertFalse(circuits.isEmpty());
        assertEquals(testCircuits.size(), circuits.size());
    }

    @Test
    void getCircuitByName() {
        Optional<Circuit> circuito = circuitService.getCircuitByName("Monza");
        assertTrue(circuito.isPresent(), "No se encontró el circuito 'Monza'");
        assertEquals("Italia", circuito.get().getCountry());
    }

    @Test
    void getCircuitsByCountry() {
        List<Circuit> italianCircuits = circuitService.getCircuitsByCountry("Italia");
        assertNotNull(italianCircuits);
        assertFalse(italianCircuits.isEmpty(), "No se encontraron circuitos en Italia");
        italianCircuits.forEach(circuit ->
                assertEquals("Italia", circuit.getCountry())
        );
    }

    @Test
    void getCircuitByLength() {
        Circuit longestCircuit = circuitService.getCircuitByLength(true);
        Circuit shortestCircuit = circuitService.getCircuitByLength(false);

        assertNotNull(longestCircuit);
        assertNotNull(shortestCircuit);
        assertTrue(longestCircuit.getLength() >= shortestCircuit.getLength());

        testCircuits.forEach(circuit -> {
            assertTrue(longestCircuit.getLength() >= circuit.getLength());
            assertTrue(shortestCircuit.getLength() <= circuit.getLength());
        });
    }
}
