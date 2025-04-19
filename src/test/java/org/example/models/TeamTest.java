package org.example.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {
    private Team team;
    private Driver driver1;
    private Driver driver2;

    @BeforeEach
    void setUp() {
        team = new Team("Red Bull Racing", "Austria");
        driver1 = new Driver("Max Verstappen", "Netherlands", 33);
        driver2 = new Driver("Sergio Perez", "Mexico", 11);
    }

    @Test
    void testDefaultConstructor() {
        Team defaultTeam = new Team();
        assertNotNull(defaultTeam.getDrivers());
        assertTrue(defaultTeam.getDrivers().isEmpty());
        assertEquals(0, defaultTeam.getPoints());
    }

    @Test
    void testParameterizedConstructor() {
        assertEquals("Red Bull Racing", team.getName());
        assertEquals("Austria", team.getCountry());
        assertNotNull(team.getDrivers());
        assertEquals(0, team.getPoints());
    }

    @Test
    void testAddDriver() {
        team.addDriver(driver1);
        assertEquals(1, team.getDrivers().size());
        assertEquals(driver1, team.getDrivers().get(0));

        team.addDriver(driver2);
        assertEquals(2, team.getDrivers().size());
        assertTrue(team.getDrivers().contains(driver1));
        assertTrue(team.getDrivers().contains(driver2));
    }

    @Test
    void testCalculatePoints() {
        driver1.setPoints(25);
        driver2.setPoints(18);
        
        team.addDriver(driver1);
        team.addDriver(driver2);
        
        team.calculatePoints();
        assertEquals(43, team.getPoints());
    }

    @Test
    void testGetDriversSorting() {
        driver1.setPoints(18);
        driver2.setPoints(25);
        
        team.addDriver(driver1);
        team.addDriver(driver2);

        List<Driver> sortedDrivers = team.getDrivers();
        assertEquals(driver2, sortedDrivers.get(0)); // Higher points should be first
        assertEquals(driver1, sortedDrivers.get(1));
    }

    @Test
    void testSettersAndGetters() {
        team.setTeamPrincipal("Christian Horner");
        team.setChampionshipsWon(5);
        team.setPoints(595);
        List<String> driverNames = Arrays.asList("Max Verstappen", "Sergio Perez");
        team.setDriverNames(driverNames);

        assertEquals("Christian Horner", team.getTeamPrincipal());
        assertEquals(5, team.getChampionshipsWon());
        assertEquals(595, team.getPoints());
        assertEquals(driverNames, team.getDriverNames());
    }

    @Test
    void testToString() {
        team.setPoints(595);
        String expected = "Red Bull Racing (Austria) - 595 pts";
        assertEquals(expected, team.toString());
    }
    @Test
    void testSetAndGetCountry() {
        Team team = new Team();
        team.setCountry("España");

        assertEquals("España", team.getCountry(), "El país del equipo debería ser 'España'");
    }
    @Test
    void testSetAndGetName() {
        Team team = new Team();
        team.setName("Ferrari");

        assertEquals("Ferrari", team.getName(), "El nombre del equipo debería ser 'Ferrari'");
    }
}