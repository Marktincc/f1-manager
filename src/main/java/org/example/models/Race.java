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

    public Race() {
        // Default constructor for JSON deserialization
        this.results = new ArrayList<>();
        this.completed = false;
    }

    public Race(String name, Circuit circuit, LocalDate date) {
        this.name = name;
        this.circuit = circuit;
        this.date = date;
        this.results = new ArrayList<>();
        this.completed = false;
    }

    // Getters and Setters
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

    public void addResult(RaceResult result) {
        this.results.add(result);
    }

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
