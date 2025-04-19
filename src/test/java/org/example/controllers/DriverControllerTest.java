package org.example.controllers;

import org.example.models.Driver;
import org.example.models.RaceResult;
import org.example.models.Team;
import org.example.services.DriverService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DriverControllerTest {
    private DriverController driverController;
    private DriverService driverService;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    private List<Driver> testDrivers;
    private Driver verstappen;
    private Driver perez;
    private Driver hamilton;
    private Team redBull;
    private Team mercedes;
    private List<RaceResult> racePositions;
    @BeforeEach
    void setUp() {
        // Setup output capture
        racePositions = new ArrayList<>();
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Create teams
        redBull = new Team("Red Bull Racing", "Austria");
        mercedes = new Team("Mercedes", "Germany");

        // Create drivers
        verstappen = new Driver("Max Verstappen", "Netherlands", 33);
        perez = new Driver("Sergio Perez", "Mexico", 11);
        hamilton = new Driver("Lewis Hamilton", "United Kingdom", 44);

        // Setup driver-team relationships
        verstappen.setTeam(redBull);
        perez.setTeam(redBull);
        hamilton.setTeam(mercedes);

        // Set points
        verstappen.setPoints(25);
        perez.setPoints(18);
        hamilton.setPoints(15);

        // Add race positions
// Add race positions for Monaco GP
        Driver.RacePosition monacoPosVer = new Driver.RacePosition();
        monacoPosVer.setRaceName("Monaco GP");
        monacoPosVer.setStartPosition(1);
        monacoPosVer.setFinishPosition(1);
        verstappen.addRacePosition(monacoPosVer);

        Driver.RacePosition monacoPosPer = new Driver.RacePosition();
        monacoPosPer.setRaceName("Monaco GP");
        monacoPosPer.setStartPosition(3);
        monacoPosPer.setFinishPosition(2);
        perez.addRacePosition(monacoPosPer);

        Driver.RacePosition monacoPosHam = new Driver.RacePosition();
        monacoPosHam.setRaceName("Monaco GP");
        monacoPosHam.setStartPosition(4);
        monacoPosHam.setFinishPosition(3);
        hamilton.addRacePosition(monacoPosHam);
        testDrivers = new ArrayList<>(Arrays.asList(verstappen, perez, hamilton));
        driverService = new DriverService(testDrivers);
        driverController = new DriverController(driverService);

    }

    @Test
    void testMostrarTodosLosPilotos() {
        driverController.mostrarTodosLosPilotos();
        
        String output = outputStream.toString();
        testDrivers.forEach(driver -> 
            assertTrue(output.contains(driver.toString())));
    }

    @Test
    void testMostrarPilotoPorNombreCompleto() {
        driverController.mostrarPilotoPorNombreCompleto("Max Verstappen");
        
        String output = outputStream.toString();
        assertTrue(output.contains("Max Verstappen"));
        assertTrue(output.contains("Netherlands"));
    }

    @Test
    void testMostrarPilotoPorNombreCompletoNoExistente() {
        driverController.mostrarPilotoPorNombreCompleto("Fernando Alonso");
        
        String output = outputStream.toString();
        assertTrue(output.contains("Piloto no encontrado: Fernando Alonso"));
    }

    @Test
    void testMostrarPilotosPorEquipo() {
        driverController.mostrarPilotosPorEquipo("Red Bull Racing");
        
        String output = outputStream.toString();
        assertTrue(output.contains("Max Verstappen"));
        assertTrue(output.contains("Sergio Perez"));
    }

    @Test
    void testMostrarPilotosPorEquipoNoExistente() {
        driverController.mostrarPilotosPorEquipo("Ferrari");
        
        String output = outputStream.toString();
        assertTrue(output.contains("No hay pilotos registrados para el equipo: Ferrari"));
    }

    @Test
    void testMostrarRankingDePilotos() {
        driverController.mostrarRankingDePilotos();
        
        String output = outputStream.toString();
        assertTrue(output.contains("1. Max Verstappen - 25 puntos"));
        assertTrue(output.contains("2. Sergio Perez - 18 puntos"));
        assertTrue(output.contains("3. Lewis Hamilton - 15 puntos"));
    }

    @Test
    void testMostrarPosicionesPilotosEnCarrera() {
        driverController.mostrarPosicionesPilotosEnCarrera("Monaco GP");
        
        String output = outputStream.toString();
        assertTrue(output.contains("POSICIONES DE PILOTOS EN MONACO GP"));
        assertTrue(output.contains("1. Max Verstappen"));
        assertTrue(output.contains("2. Sergio Perez"));
    }

    @Test
    void testMostrarPosicionesPilotosEnCarreraNoExistente() {
        driverController.mostrarPosicionesPilotosEnCarrera("Spanish GP");
        
        String output = outputStream.toString();
        assertTrue(output.contains("No hay datos para la carrera: Spanish GP"));
    }

    @Test
    void testMostrarPuntosPilotosHastaCarrera() {
        List<String> todasLasCarreras = Arrays.asList("Bahrain GP", "Monaco GP", "Spanish GP");
        driverController.mostrarPuntosPilotosHastaCarrera("Monaco GP", todasLasCarreras);
        
        String output = outputStream.toString();
        assertTrue(output.contains("PUNTOS DE PILOTOS HASTA MONACO GP"));
    }
    @Test
    void testMostrarPuntosPilotosHastaCarrera_WhenNoData() {

        List<String> todasLasCarreras = Arrays.asList("Bahrain GP", "Spanish GP");
        String carreraNoExistente = "Monaco GP";


        driverController.mostrarPuntosPilotosHastaCarrera(carreraNoExistente, todasLasCarreras);


        String output = outputStream.toString();

        assertTrue(output.contains("No hay datos hasta la carrera: Monaco GP"));
    }
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}
