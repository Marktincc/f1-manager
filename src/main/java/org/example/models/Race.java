package org.example.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Race {
    private String name;
    private Circuit circuit;
    private LocalDate date;
    private List<RaceResult> results;
    private boolean completed;

    // Constructor por defecto para deserialización JSON
    public Race() {
        this.results = new ArrayList<>();
        this.completed = false;  // Inicialización del estado de la carrera
    }

    // Constructor parametrizado
    public Race(String name, Circuit circuit, LocalDate date) {
        this.name = name;
        this.circuit = circuit;
        this.date = date;
        this.results = new ArrayList<>();
        this.completed = false;  // Carrera comienza como no completada
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<RaceResult> getResults() {
        return results;
    }

    public void setResults(List<RaceResult> results) {
        this.results = results;
    }

    // Método para agregar resultados a la carrera
    public void addResult(RaceResult result) {
        this.results.add(result);
    }

    // Método para marcar la carrera como completada
    public void finishRace() {
        this.completed = true;
    }

    // Método para comprobar si la carrera está completa
    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return name + " at " + circuit.getName() + " (" + date + ")";
    }
}
