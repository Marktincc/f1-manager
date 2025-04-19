package org.example.services;

import org.example.models.Driver;
import org.example.models.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TeamServiceTest {
    private TeamService teamService;
    private List<Team> teams;
    private Team redBull;
    private Team mercedes;
    private Team ferrari;

    @BeforeEach
    void setUp() {
        teams = new ArrayList<>();
        
        redBull = new Team("Red Bull Racing", "Austria");
        mercedes = new Team("Mercedes", "Germany");
        ferrari = new Team("Ferrari", "Italy");
        
        // Add drivers to teams
        Driver verstappen = new Driver("Max Verstappen", "Netherlands", 33);
        Driver perez = new Driver("Sergio Perez", "Mexico", 11);
        verstappen.setPoints(25);
        perez.setPoints(18);
        redBull.addDriver(verstappen);
        redBull.addDriver(perez);
        
        Driver hamilton = new Driver("Lewis Hamilton", "United Kingdom", 44);
        Driver russell = new Driver("George Russell", "United Kingdom", 63);
        hamilton.setPoints(15);
        russell.setPoints(12);
        mercedes.addDriver(hamilton);
        mercedes.addDriver(russell);
        
        teams.add(redBull);
        teams.add(mercedes);
        teams.add(ferrari);
        
        teamService = new TeamService(teams);
    }

    @Test
    void testGetAllTeams() {
        List<Team> result = teamService.getAllTeams();
        assertEquals(3, result.size());
        assertTrue(result.contains(redBull));
        assertTrue(result.contains(mercedes));
        assertTrue(result.contains(ferrari));
    }

    @Test
    void testGetTeamByName() {
        Optional<Team> result = teamService.getTeamByName("Red Bull Racing");
        assertTrue(result.isPresent());
        assertEquals(redBull, result.get());
        
        // Test case insensitive search
        result = teamService.getTeamByName("red bull racing");
        assertTrue(result.isPresent());
        assertEquals(redBull, result.get());
        
        // Test non-existent team
        result = teamService.getTeamByName("McLaren");
        assertFalse(result.isPresent());
    }

    @Test
    void testGetTeamsSortedByPoints() {
        teamService.updateTeamPoints(); // Calculate points first
        
        List<Team> sortedTeams = teamService.getTeamsSortedByPoints();
        assertEquals(3, sortedTeams.size());
        assertEquals(redBull, sortedTeams.get(0)); // 43 points (25 + 18)
        assertEquals(mercedes, sortedTeams.get(1)); // 27 points (15 + 12)
        assertEquals(ferrari, sortedTeams.get(2)); // 0 points
    }

    @Test
    void testUpdateTeamPoints() {
        teamService.updateTeamPoints();
        
        assertEquals(43, redBull.getPoints()); // 25 + 18
        assertEquals(27, mercedes.getPoints()); // 15 + 12
        assertEquals(0, ferrari.getPoints()); // No drivers
    }

    @Test
    void testWithEmptyTeamsList() {
        TeamService emptyService = new TeamService(new ArrayList<>());
        
        assertTrue(emptyService.getAllTeams().isEmpty());
        assertFalse(emptyService.getTeamByName("Any Team").isPresent());
        assertTrue(emptyService.getTeamsSortedByPoints().isEmpty());
    }
}