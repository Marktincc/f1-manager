package org.example.services;

import org.example.models.Circuit;
import java.util.List;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

public class CircuitService {
    private List<Circuit> circuits;

    public CircuitService(List<Circuit> circuits) {
        this.circuits = circuits;
    }

    public List<Circuit> getAllCircuits() {
        return circuits;
    }

    public Optional<Circuit> getCircuitByName(String name) {
        return circuits.stream()
                .filter(circuit -> circuit.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public List<Circuit> getCircuitsByCountry(String country) {
        return circuits.stream()
                .filter(circuit -> circuit.getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());
    }

    // Fix: Create a list of races instead of using the integer directly
    public List<Circuit> getCircuitsByNumberOfRaces(int numberOfRaces) {
        return circuits.stream()
                .filter(circuit -> circuit.getRaces() == numberOfRaces)
                .collect(Collectors.toList());
    }

    // Fix: Create a list of races instead of using the integer directly
    public List<Circuit> getCircuitsSortedByNumberOfRaces() {
        return circuits.stream()
                .sorted(Comparator.comparing(Circuit::getRaces).reversed())
                .collect(Collectors.toList());
    }

    public Circuit getCircuitByLength(boolean longest) {
        if (circuits == null || circuits.isEmpty()) {
            return null;
        }
        
        return circuits.stream()
                .max(longest ? 
                     Comparator.comparingDouble(Circuit::getLength) : 
                     Comparator.comparingDouble(Circuit::getLength).reversed())
                .orElse(null);
    }
}
