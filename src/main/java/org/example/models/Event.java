package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {

    @JsonProperty("nombre")
    private String name;

    @JsonProperty("circuito")
    private Circuit circuit;

    @JsonProperty("fecha_carrera")
    private String raceDate; // Se puede mapear como LocalDate si lo deseas

    @JsonProperty("fecha_sprint")
    private String sprintDate; // También se puede mapear como LocalDate

    @JsonProperty("numero_carrera")
    private int raceNumber;

    // Constructor vacío para Jackson
    public Event() {
    }

    // Getters y setters
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

    public String getRaceDate() {
        return raceDate;
    }

    public void setRaceDate(String raceDate) {
        this.raceDate = raceDate;
    }

    public String getSprintDate() {
        return sprintDate;
    }

    public void setSprintDate(String sprintDate) {
        this.sprintDate = sprintDate;
    }

    public int getRaceNumber() {
        return raceNumber;
    }

    public void setRaceNumber(int raceNumber) {
        this.raceNumber = raceNumber;
    }

    public Event(String name, Circuit circuit, String raceDate, String sprintDate, int raceNumber) {
        this.name = name;
        this.circuit = circuit;
        this.raceDate = raceDate;
        this.sprintDate = sprintDate;
        this.raceNumber = raceNumber;
    }


    @Override
    public String toString() {
        return "Evento: " + name +
                "\nCircuito: " + circuit +
                "\nFecha carrera: " + raceDate +
                (sprintDate != null ? "\nFecha sprint: " + sprintDate : "") +
                "\nNúmero de carrera: " + raceNumber;
    }
}