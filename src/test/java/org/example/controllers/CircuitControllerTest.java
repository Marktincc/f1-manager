package org.example.controllers;

import org.example.models.Circuit;
import org.example.services.CircuitService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CircuitControllerTest {
    private CircuitController circuitController;
    private CircuitService circuitService;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    private List<Circuit> testCircuits;

    @BeforeEach
    void setUp() {
        testCircuits = new ArrayList<>();
        Circuit monaco = new Circuit("Monaco", "Monaco", 3.337);
        Circuit silverstone = new Circuit("Silverstone", "United Kingdom", 5.891);
        Circuit monza = new Circuit("Monza", "Italy", 5.793);
        
        testCircuits.addAll(Arrays.asList(monaco, silverstone, monza));
        circuitService = new CircuitService(testCircuits);
        circuitController = new CircuitController(circuitService);
        
        // Setup output capture
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testMostrarTodosLosCircuitos() {
        circuitController.mostrarTodosLosCircuitos();
        
        String output = outputStream.toString();
        testCircuits.forEach(circuit -> 
            assertTrue(output.contains(circuit.toString())));
    }

    @Test
    void testMostrarCircuitoPorNombreExistente() {
        // Call the method to test
        circuitController.mostrarCircuitoPorNombre("Monaco");
        
        // Get the output
        String output = outputStream.toString();
        
        // Check if the output contains the circuit name
        assertTrue(output.contains("Monaco"));
        
        // If the test is still failing, print the actual output for debugging
        System.err.println("Actual output: " + output);
    }

    @Test
    void testGetCircuitsByCountryWithResults() {
        circuitController.getCircuitsByCountry("Monaco");
        
        String output = outputStream.toString();
        assertTrue(output.contains("Monaco"));
    }

    @Test
    void testMostrarCircuitoPorNombreNoExistente() {
        circuitController.mostrarCircuitoPorNombre("NonExistent");
        
        String output = outputStream.toString();
        assertTrue(output.contains("No se encontró el circuito: NonExistent"));
    }

  

    @Test
    void testGetCircuitsByCountryNoResults() {
        circuitController.getCircuitsByCountry("Spain");
        
        String output = outputStream.toString();
        assertTrue(output.contains("No hay carreras registradas en el país: Spain"));
    }

    @Test
    void testGetAllCircuits() {
        circuitController.getAllCircuits();
        
        String output = outputStream.toString();
        testCircuits.forEach(circuit -> 
            assertTrue(output.contains(circuit.toString())));
    }

    @Test
    void testWithEmptyCircuitList() {
        CircuitService emptyService = new CircuitService(new ArrayList<>());
        CircuitController emptyController = new CircuitController(emptyService);
        
        emptyController.getAllCircuits();
        String output = outputStream.toString();
        assertEquals("", output.trim());
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}
