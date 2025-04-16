package org.example.services;

import org.example.models.Circuit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CircuitServiceTest {
    private CircuitService circuitService;
    private List<Circuit> circuits;
    private Circuit monza;
    private Circuit silverstone;
    private Circuit monaco;

    @BeforeEach
    void setUp() {
        circuits = new ArrayList<>();

        monza = new Circuit("Monza", "Italy", 5.793);
        monza.setRaces(72);

        silverstone = new Circuit("Silverstone", "United Kingdom", 5.891);
        silverstone.setRaces(56);

        monaco = new Circuit("Monaco", "Monaco", 3.337);
        monaco.setRaces(68);

        circuits.add(monza);
        circuits.add(silverstone);
        circuits.add(monaco);

        circuitService = new CircuitService(circuits);
    }

    @Test
    void testGetAllCircuits() {
        List<Circuit> result = circuitService.getAllCircuits();
        assertEquals(3, result.size());
        assertTrue(result.contains(monza));
        assertTrue(result.contains(silverstone));
        assertTrue(result.contains(monaco));
    }

    @Test
    void testGetCircuitByName() {
        Optional<Circuit> result = circuitService.getCircuitByName("Monza");
        assertTrue(result.isPresent());
        assertEquals(monza, result.get());

        result = circuitService.getCircuitByName("NonExistent");
        assertFalse(result.isPresent());
    }

    @Test
    void testGetCircuitsByCountry() {
        List<Circuit> italianCircuits = circuitService.getCircuitsByCountry("Italy");
        assertEquals(1, italianCircuits.size());
        assertEquals(monza, italianCircuits.get(0));

        List<Circuit> nonExistentCountry = circuitService.getCircuitsByCountry("Spain");
        assertTrue(nonExistentCountry.isEmpty());
    }

    @Test
    void testGetCircuitsByNumberOfRaces() {
        List<Circuit> circuits72Races = circuitService.getCircuitsByNumberOfRaces(72);
        assertEquals(1, circuits72Races.size());
        assertEquals(monza, circuits72Races.get(0));

        List<Circuit> noCircuits = circuitService.getCircuitsByNumberOfRaces(100);
        assertTrue(noCircuits.isEmpty());
    }

    @Test
    void testGetCircuitsSortedByNumberOfRaces() {
        List<Circuit> sortedCircuits = circuitService.getCircuitsSortedByNumberOfRaces();
        assertEquals(3, sortedCircuits.size());
        assertEquals(monza, sortedCircuits.get(0)); // 72 races
        assertEquals(monaco, sortedCircuits.get(1)); // 68 races
        assertEquals(silverstone, sortedCircuits.get(2)); // 56 races
    }

    @Test
    void testGetCircuitByLength() {
        Circuit longest = circuitService.getCircuitByLength(true);
        assertEquals(silverstone, longest);

        Circuit shortest = circuitService.getCircuitByLength(false);
        assertEquals(monaco, shortest);
    }

    @Test
    void testGetCircuitByLengthWithEmptyList() {
        CircuitService emptyService = new CircuitService(new ArrayList<>());
        assertNull(emptyService.getCircuitByLength(true));
        assertNull(emptyService.getCircuitByLength(false));
    }

    @Test
    void testCaseInsensitiveSearch() {
        Optional<Circuit> result = circuitService.getCircuitByName("monza");
        assertTrue(result.isPresent());
        assertEquals(monza, result.get());

        List<Circuit> italianCircuits = circuitService.getCircuitsByCountry("ITALY");
        assertEquals(1, italianCircuits.size());
        assertEquals(monza, italianCircuits.get(0));
    }
}
