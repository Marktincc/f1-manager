package org.example.controllers;

import org.example.models.Driver;
import org.example.models.Team;
import org.example.services.TeamService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeamControllerTest {
    private TeamController teamController;
    private TeamService teamService;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    private List<Team> testTeams;
    private Team redBull;
    private Team mercedes;
    private Team ferrari;

    @BeforeEach
    void setUp() {
        // Setup output capture
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Create test teams
        redBull = new Team("Red Bull Racing", "Austria");
        mercedes = new Team("Mercedes", "Germany");
        ferrari = new Team("Ferrari", "Italy");

        // Add drivers to teams
        Driver verstappen = new Driver("Max Verstappen", "Netherlands", 1);
        Driver perez = new Driver("Sergio Perez", "Mexico", 11);
        verstappen.setPoints(25);
        perez.setPoints(18);
        redBull.addDriver(verstappen);
        redBull.addDriver(perez);

        Driver hamilton = new Driver("Lewis Hamilton", "United Kingdom", 44);
        hamilton.setPoints(15);
        mercedes.addDriver(hamilton);

        // Calculate team points
        redBull.calculatePoints();
        mercedes.calculatePoints();
        ferrari.calculatePoints();

        testTeams = new ArrayList<>(Arrays.asList(redBull, mercedes, ferrari));
        teamService = new TeamService(testTeams);
        teamController = new TeamController(teamService);
    }

    @Test
    void testMostrarTodosLosEquipos() {
        teamController.mostrarTodosLosEquipos();
        
        String output = outputStream.toString();
        testTeams.forEach(team -> 
            assertTrue(output.contains(team.toString())));
    }

    @Test
    void testMostrarEquipoPorNombre() {
        teamController.mostrarEquipoPorNombre("Red Bull Racing");
        
        String output = outputStream.toString();
        assertTrue(output.contains("Red Bull Racing"));
        assertTrue(output.contains("Austria"));
    }

    @Test
    void testMostrarEquipoPorNombreNoExistente() {
        teamController.mostrarEquipoPorNombre("McLaren");
        
        String output = outputStream.toString();
        assertTrue(output.contains("No se encontró el equipo: McLaren"));
    }

    @Test
    void testMostrarEquiposOrdenadosPorPuntos() {
        teamController.mostrarEquiposOrdenadosPorPuntos();
        
        String output = outputStream.toString();
        assertTrue(output.contains("RANKING DE EQUIPOS"));
        assertTrue(output.contains("Red Bull Racing"));
        assertTrue(output.contains("Mercedes"));
    }

    @Test
    void testWithEmptyTeamsList() {
        // Create a new controller with an empty list
        TeamService emptyService = new TeamService(new ArrayList<>());
        TeamController emptyController = new TeamController(emptyService);
        
        // Reset output stream to clear previous output
        outputStream.reset();
        
        emptyController.mostrarTodosLosEquipos();
        
        String output = outputStream.toString().trim();
        assertEquals("No hay equipos registrados.", output);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}
