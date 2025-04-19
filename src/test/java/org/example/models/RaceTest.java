package org.example.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RaceTest {
    private Race race;
    private Circuit circuit;
    private LocalDate raceDate;

    @BeforeEach
    void setUp() {
        circuit = new Circuit("Monaco", "Monaco", 3.337);
        raceDate = LocalDate.of(2024, 5, 26);
        race = new Race("Monaco Grand Prix", circuit, raceDate);
    }

    @Test
    void testDefaultConstructor() {
        Race defaultRace = new Race();
        assertNotNull(defaultRace.getResults());
        assertTrue(defaultRace.getResults().isEmpty());
        assertFalse(defaultRace.isCompleted());
    }

    @Test
    void testParameterizedConstructor() {
        assertEquals("Monaco Grand Prix", race.getName());
        assertEquals(circuit, race.getCircuit());
        assertEquals(raceDate, race.getDate());
        assertNotNull(race.getResults());
        assertTrue(race.getResults().isEmpty());
        assertFalse(race.isCompleted());
    }

    @Test
    void testSettersAndGetters() {
        Circuit newCircuit = new Circuit("Spa", "Belgium", 7.004);
        LocalDate newDate = LocalDate.of(2024, 7, 28);
        
        race.setName("Belgian Grand Prix");
        race.setCircuit(newCircuit);
        race.setDate(newDate);
        race.setCompleted(true);

        assertEquals("Belgian Grand Prix", race.getName());
        assertEquals(newCircuit, race.getCircuit());
        assertEquals(newDate, race.getDate());
        assertTrue(race.isCompleted());
    }

    @Test
    void testResultsManagement() {
        RaceResult result1 = new RaceResult(1, "Driver1", "Team1");
        RaceResult result2 = new RaceResult(2, "Driver2", "Team2");



        race.addResult(result1);
        assertEquals(1, race.getResults().size());
        assertEquals(result1, race.getResults().get(0));

        List<RaceResult> newResults = new ArrayList<>();
        newResults.add(result1);
        newResults.add(result2);
        race.setResults(newResults);

        assertEquals(2, race.getResults().size());
        assertEquals(result1, race.getResults().get(0));
        assertEquals(result2, race.getResults().get(1));
    }


    @Test
    void testToString() {
        String expected = "Monaco Grand Prix at Monaco (2024-05-26)";
        assertEquals(expected, race.toString());
    }
    @Test
    void testFinishRaceSetsCompletedToTrue() {
        Race race = new Race();
        assertFalse(race.isCompleted(), "La carrera debería estar incompleta al inicio");

        race.finishRace();

        assertTrue(race.isCompleted(), "La carrera debería estar marcada como completada");
    }

}