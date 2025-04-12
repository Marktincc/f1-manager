package org.example.services;

import org.example.models.Driver;
import org.example.models.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DriverServiceTest {
    private DriverService driverService;
    private List<Driver> drivers;
    private Driver driver1;
    private Driver driver2;
    private Driver driver3;
    private Team team1;
    private Team team2;

    @BeforeEach
    void setUp() {
        drivers = new ArrayList<>();
        
        driver1 = new Driver("Max Verstappen", "Netherlands", 33);
        driver2 = new Driver("Lewis Hamilton", "United Kingdom", 44);
        driver3 = new Driver("Charles Leclerc", "Monaco", 16);
        
        team1 = new Team("Red Bull Racing", "Austria");
        team2 = new Team("Mercedes", "Germany");
        
        drivers.addAll(Arrays.asList(driver1, driver2, driver3));
        driverService = new DriverService(drivers);
    }

    @Test
    void testGetAllDrivers() {
        List<Driver> result = driverService.getAllDrivers();
        assertEquals(3, result.size());
        assertTrue(result.containsAll(Arrays.asList(driver1, driver2, driver3)));
    }

    @Test
    void testGetDriverByName() {
        Optional<Driver> result = driverService.getDriverByName("Max Verstappen");
        assertTrue(result.isPresent());
        assertEquals(driver1, result.get());

        result = driverService.getDriverByName("NonExistent");
        assertFalse(result.isPresent());
    }

    @Test
    void testGetDriversByTeam() {
        driverService.assignDriverToTeam(driver1, team1);
        driverService.assignDriverToTeam(driver2, team2);

        List<Driver> redBullDrivers = driverService.getDriversByTeam("Red Bull Racing");
        assertEquals(1, redBullDrivers.size());
        assertEquals(driver1, redBullDrivers.get(0));
    }

    @Test
    void testGetDriversSortedByPoints() {
        driver1.setPoints(25);
        driver2.setPoints(18);
        driver3.setPoints(15);

        List<Driver> sorted = driverService.getDriversSortedByPoints();
        assertEquals(driver1, sorted.get(0));
        assertEquals(driver2, sorted.get(1));
        assertEquals(driver3, sorted.get(2));
    }

    @Test
    void testAssignDriverToTeam() {
        driverService.assignDriverToTeam(driver1, team1);
        assertEquals(team1, driver1.getTeam());
        assertTrue(team1.getDrivers().contains(driver1));
    }

    @Test
    void testUpdateDriverPoints() {
        driverService.assignDriverToTeam(driver1, team1);
        driverService.updateDriverPoints(driver1, 25);
        
        assertEquals(25, driver1.getPoints());
        assertEquals(25, team1.getPoints());
    }

    @Test
    void testGetDriverPositionsForRace() {
        Driver.RacePosition pos1 = new Driver.RacePosition();
        pos1.setRaceName("Monaco GP");
        pos1.setStartPosition(3);
        pos1.setFinishPosition(1);
        
        Driver.RacePosition pos2 = new Driver.RacePosition();
        pos2.setRaceName("Monaco GP");
        pos2.setStartPosition(1);
        pos2.setFinishPosition(2);
        
        List<Driver.RacePosition> positions1 = new ArrayList<>();
        positions1.add(pos1);
        driver1.setRacePositions(positions1);
        
        List<Driver.RacePosition> positions2 = new ArrayList<>();
        positions2.add(pos2);
        driver2.setRacePositions(positions2);

        List<Driver> raceResults = driverService.getDriverPositionsForRace("Monaco GP");
        assertEquals(2, raceResults.size());
        assertEquals(driver1, raceResults.get(0));
        assertEquals(driver2, raceResults.get(1));
    }


    @Test
    void testGetDriverPointsUpToRace() {
        Driver.RacePosition race1Pos1 = new Driver.RacePosition();
        race1Pos1.setRaceName("Race1");
        race1Pos1.setFinishPosition(1);
    
        Driver.RacePosition race2Pos1 = new Driver.RacePosition();
        race2Pos1.setRaceName("Race2");
        race2Pos1.setFinishPosition(2);
    
        Driver.RacePosition race1Pos2 = new Driver.RacePosition();
        race1Pos2.setRaceName("Race1");
        race1Pos2.setFinishPosition(2);
    
        Driver.RacePosition race2Pos2 = new Driver.RacePosition();
        race2Pos2.setRaceName("Race2");
        race2Pos2.setFinishPosition(1);
    
        driver1.getRacePositions().add(race1Pos1);
        driver1.getRacePositions().add(race2Pos1);
    
        driver2.getRacePositions().add(race1Pos2);
        driver2.getRacePositions().add(race2Pos2);
    
        // Remove driver3 from the list for this test
        drivers.remove(driver3);
    
        List<String> allRaces = Arrays.asList("Race1", "Race2", "Race3");
    
        List<Driver> standings = driverService.getDriverPointsUpToRace("Race1", allRaces);
        assertEquals(2, standings.size());
        assertEquals(25, standings.get(0).getPoints());
        assertEquals(18, standings.get(1).getPoints());
    }


    @Test
    void testEmptyRaceResults() {
        List<Driver> raceResults = driverService.getDriverPositionsForRace("NonExistentRace");
        assertTrue(raceResults.isEmpty());
    }

    @Test
    void testDriverWithNoTeam() {
        List<Driver> teamDrivers = driverService.getDriversByTeam("NonExistentTeam");
        assertTrue(teamDrivers.isEmpty());
    }

    @Test
    void testUpdateDriverPointsWithNullTeam() {
        driverService.updateDriverPoints(driver1, 25);
        assertEquals(25, driver1.getPoints());
    }

    @Test
    void testCalculatePointsForPosition() {
        Driver.RacePosition racePosition = new Driver.RacePosition();
        racePosition.setRaceName("TestRace");
        racePosition.setFinishPosition(1);
        
        List<Driver.RacePosition> positions = new ArrayList<>();
        positions.add(racePosition);
        driver1.setRacePositions(positions);
        
        List<String> races = Arrays.asList("TestRace");
        
        List<Driver> results = driverService.getDriverPointsUpToRace("TestRace", races);
        assertEquals(25, results.get(0).getPoints()); // 1st place should get 25 points
    }
}