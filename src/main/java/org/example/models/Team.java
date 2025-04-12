package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Team {
    @JsonProperty("nombre")
    private String name;

    @JsonProperty("directorGeneral")
    private String teamPrincipal;

    @JsonProperty("paisOrigen")
    private String country;

    @JsonProperty("campeonatosGanados")
    private int championshipsWon;

    @JsonProperty("puntosAcumulados")
    private int points;

    @JsonProperty("pilotosOficiales")
    private List<String> driverNames;

    private List<Driver> drivers;

    // Default constructor for Jackson
    public Team() {
        this.drivers = new ArrayList<>();
    }

    public Team(String name, String country) {
        this.name = name;
        this.country = country;
        this.drivers = new ArrayList<>();
        this.points = 0;
    }

    public void addDriver(Driver driver) {
        System.out.println("Adding driver " + driver.getName() + " to team " + name);
        drivers.add(driver);
    }

    public List<Driver> getDrivers() {
        // Sort drivers by points in descending order
        Collections.sort(drivers, new Comparator<Driver>() {
            @Override
            public int compare(Driver a, Driver b) {
                return Integer.compare(b.getPoints(), a.getPoints());
            }
        });
        return drivers;
    }

    public void calculatePoints() {
        int totalPoints = 0;
        for (Driver driver : drivers) {
            totalPoints += driver.getPoints();
        }
        this.points = totalPoints;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamPrincipal() {
        return teamPrincipal;
    }

    public void setTeamPrincipal(String teamPrincipal) {
        this.teamPrincipal = teamPrincipal;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getChampionshipsWon() {
        return championshipsWon;
    }

    public void setChampionshipsWon(int championshipsWon) {
        this.championshipsWon = championshipsWon;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<String> getDriverNames() {
        return driverNames;
    }

    public void setDriverNames(List<String> driverNames) {
        this.driverNames = driverNames;
    }

    @Override
    public String toString() {
        return name + " (" + country + ") - " + points + " pts";
    }
}