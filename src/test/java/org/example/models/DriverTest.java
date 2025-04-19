package org.example.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {
    private Driver driver;
    private Team team;

    @BeforeEach
    void setUp() {
        driver = new Driver("Max Verstappen", "Netherlands", 26);
        team = new Team("Red Bull Racing", "Austria");
    }

    @Test
    void testDefaultConstructor() {
        Driver emptyDriver = new Driver();
        assertNull(emptyDriver.getName());
        assertNull(emptyDriver.getNationality());
        assertEquals(0, emptyDriver.getAge());
        assertEquals(0, emptyDriver.getPoints());
    }

    @Test
    void testParameterizedConstructor() {
        assertEquals("Max Verstappen", driver.getName());
        assertEquals("Netherlands", driver.getNationality());
        assertEquals(26, driver.getAge());
        assertEquals(0, driver.getPoints());
    }

    @Test
    void testSettersAndGetters() {
        driver.setName("Lewis Hamilton");
        driver.setNationality("United Kingdom");
        driver.setAge(39);
        driver.setTeamName("Mercedes");
        driver.setChampionshipsWon(7);
        driver.setRacesDisputed(332);
        driver.setPoints(25);
        driver.setTeam(team);

        assertEquals("Lewis Hamilton", driver.getName());
        assertEquals("United Kingdom", driver.getNationality());
        assertEquals(39, driver.getAge());
        assertEquals("Mercedes", driver.getTeamName());
        assertEquals(7, driver.getChampionshipsWon());
        assertEquals(332, driver.getRacesDisputed());
        assertEquals(25, driver.getPoints());
        assertEquals(team, driver.getTeam());
    }

    @Test
    void testAddPoints() {
        driver.setPoints(10);
        driver.addPoints(15);
        assertEquals(25, driver.getPoints());
    }

    @Test
    void testToString() {
        driver.setPoints(25);
        assertEquals("Max Verstappen (Netherlands) - 25 pts", driver.toString());
    }

    @Test
    void testRacePositions() {
        List<Driver.RacePosition> positions = new ArrayList<>();

        Driver.RacePosition monaco = new Driver.RacePosition();
        monaco.setRaceName("Monaco GP");
        monaco.setStartPosition(1);
        monaco.setFinishPosition(1);

        Driver.RacePosition silverstone = new Driver.RacePosition();
        silverstone.setRaceName("British GP");
        silverstone.setStartPosition(2);
        silverstone.setFinishPosition(1);

        positions.add(monaco);
        positions.add(silverstone);

        driver.setRacePositions(positions);

        assertEquals(2, driver.getRacePositions().size());
        assertEquals("Monaco GP", driver.getRacePositions().get(0).getRaceName());
        assertEquals(1, driver.getRacePositions().get(0).getStartPosition());
        assertEquals(1, driver.getRacePositions().get(0).getFinishPosition());
        assertEquals("British GP", driver.getRacePositions().get(1).getRaceName());
    }

    @Test
    void testRacePositionClass() {
        Driver.RacePosition position = new Driver.RacePosition();
        position.setRaceName("Monaco GP");
        position.setStartPosition(3);
        position.setFinishPosition(1);

        assertEquals("Monaco GP", position.getRaceName());
        assertEquals(3, position.getStartPosition());
        assertEquals(1, position.getFinishPosition());
    }

    @Test
    void testTeamAssociation() {
        driver.setTeam(team);
        team.addDriver(driver);

        assertEquals(team, driver.getTeam());
        assertTrue(team.getDrivers().contains(driver));
    }
    @Test
    void testGetRacePositionsWhenNull() {
        assertNotNull(driver.getRacePositions(), "Race positions should never be null");
        assertTrue(driver.getRacePositions().isEmpty(), "Race positions should be an empty list when it's null");
    }

    @Test
    void testGetRacePositionsWhenNotNull() {
        // Crear una lista de posiciones de carrera usando Driver.RacePosition y asignarla a la lista de racePositions
        List<Driver.RacePosition> racePositions = new ArrayList<>();
        Driver.RacePosition racePosition = new Driver.RacePosition();
        racePosition.setRaceName("Monaco GP");
        racePosition.setStartPosition(1);
        racePosition.setFinishPosition(1);

        racePositions.add(racePosition);  // Crear un ejemplo de posición de carrera
        driver.setRacePositions(racePositions);

        // Verificar que la lista devuelta no sea vacía y que contenga el valor correcto
        assertNotNull(driver.getRacePositions(), "Race positions should not be null");
        assertEquals(1, driver.getRacePositions().size(), "Race positions should contain 1 item");
        assertEquals("Monaco GP", driver.getRacePositions().get(0).getRaceName(), "Race name should match");
        assertEquals(1, driver.getRacePositions().get(0).getStartPosition(), "Start position should match");
        assertEquals(1, driver.getRacePositions().get(0).getFinishPosition(), "Finish position should match");
    }

    @Test
    void testSetRacePositions() {
        // Crear una lista de posiciones de carrera usando Driver.RacePosition
        List<Driver.RacePosition> racePositions = new ArrayList<>();
        Driver.RacePosition racePosition = new Driver.RacePosition();
        racePosition.setRaceName("Monaco GP");
        racePosition.setStartPosition(1);
        racePosition.setFinishPosition(1);

        racePositions.add(racePosition); // Agregar el resultado de carrera

        // Establecer la lista de posiciones de carrera usando setRacePositions
        driver.setRacePositions(racePositions);

        // Verificar que la lista se haya establecido correctamente
        assertEquals(1, driver.getRacePositions().size(), "There should be 1 race result after setting race positions");
        assertEquals("Monaco GP", driver.getRacePositions().get(0).getRaceName(), "Race name should match");
    }
    @Test
    void testClearRacePositionsWhenNotNull() {
        // Crear un Driver con algunas posiciones de carrera
        Driver driver = new Driver();
        List<Driver.RacePosition> positions = new ArrayList<>();
        positions.add(new Driver.RacePosition());
        driver.setRacePositions(positions);

        // Verificar que la lista no esté vacía antes de limpiar
        assertFalse(driver.getRacePositions().isEmpty(), "Race positions should not be empty initially");

        // Llamar al método clearRacePositions
        driver.clearRacePositions();

        // Verificar que la lista esté vacía después de llamar a clearRacePositions
        assertTrue(driver.getRacePositions().isEmpty(), "Race positions should be empty after clearing");
    }
    @Test
    void testClearRacePositionsWhenNull() {
        // Crear un Driver sin posiciones de carrera
        Driver driver = new Driver();

        // Verificar que la lista no sea null y esté vacía
        assertNotNull(driver.getRacePositions(), "Race positions should never be null");
        assertTrue(driver.getRacePositions().isEmpty(), "Race positions should be empty initially");

        // Llamar al método clearRacePositions cuando la lista es null (no inicializada)
        driver.clearRacePositions();

        // Verificar que no haya cambiado nada y siga siendo una lista vacía
        assertTrue(driver.getRacePositions().isEmpty(), "Race positions should still be empty after clearing");
    }
    @Test
    void testRacePositionGetRacePositionsWhenNull() {
        Driver.RacePosition racePosition = new Driver.RacePosition();

        List<RaceResult> result = racePosition.getRacePositions();

        assertNotNull(result, "RacePosition's racePositions should not be null");
        assertTrue(result.isEmpty(), "RacePosition's racePositions should be empty if initially null");
    }
    @Test
    void testRacePositionGetRacePositionsWhenNotNull() {
        Driver.RacePosition racePosition = new Driver.RacePosition();
        List<RaceResult> customList = new ArrayList<>();
        customList.add(new RaceResult()); // Asumiendo que tienes una clase RaceResult definida

        racePosition.setRacePositions(customList);

        assertSame(customList, racePosition.getRacePositions(), "Should return the same list that was set");
    }

}