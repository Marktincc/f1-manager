package org.example.controllers;

import org.example.models.Circuit;
import org.example.models.Race;
import org.example.services.CircuitService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CircuitController {
    private CircuitService circuitService;

    public CircuitController(CircuitService circuitService) {
        this.circuitService = circuitService;
    }

    public void mostrarTodosLosCircuitos() {
        for (Circuit circuit : circuitService.getAllCircuits()) {
            System.out.println(circuit);
        }
    }


    public void mostrarCircuitoPorNombre(String name) {
        Optional<Circuit> circuit = circuitService.getCircuitByName(name);
        if (circuit.isPresent()) {
            System.out.println(circuit.get());
        } else {
            System.out.println("No se encontró el circuito: " + name);
        }
    }


    public void getCircuitsByCountry(String country) {
        List<Circuit> circuitsByCountry = circuitService.getCircuitsByCountry(country);
        if (circuitsByCountry.isEmpty()) {
            System.out.println("No hay carreras registradas en el país: " + country);
        } else {
            for (Circuit circuit : circuitsByCountry) {
                System.out.println(circuit);
            }
        }
    }




    public void getAllCircuits() {
        List<Circuit> todas = circuitService.getAllCircuits();
        todas.forEach(System.out::println);
    }
}