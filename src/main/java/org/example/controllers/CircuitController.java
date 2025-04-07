package org.example.controllers;

import org.example.models.Circuit;
import org.example.models.Race;
import org.example.services.CircuitService;

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

    public void mostrarCircuitoPorNombre(String nombre) {
        Optional<Circuit> circuito = circuitService.getCircuitByName(nombre);
        circuito.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("No se encontró el circuito: " + nombre)
        );
    }

    public void getCircuitsByCountry(String pais) {
        List<Circuit> carreras = circuitService.getCircuitsByCountry(pais);
        if (carreras.isEmpty()) {
            System.out.println("No hay carreras registradas en el país: " + pais);
        } else {
            carreras.forEach(System.out::println);
        }
    }

    public void getAllCircuits() {
        List<Circuit> todas = circuitService.getAllCircuits();
        todas.forEach(System.out::println);
    }
}
