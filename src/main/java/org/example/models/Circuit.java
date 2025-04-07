package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Circuit {
    @JsonProperty("nombre")
    private String name;
    
    @JsonProperty("pais")
    private String country;
    
    @JsonProperty("ciudad")
    private String city;
    
    @JsonProperty("longitud")
    private double length;
    
    @JsonProperty("vueltas")
    private int laps;
    
    @JsonProperty("distancia_total")
    private double totalDistance;
    
    private String trackType;
    private int corners;
    private int races;
    
    // Default constructor for Jackson
    public Circuit() {
    }
    public Circuit(String name, String country, double length) {
        this.name = name;
        this.country = country;
        this.length = length;
    }
    
    // Getters and setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public double getLength() {
        return length;
    }
    
    public void setLength(double length) {
        this.length = length;
    }
    
    public int getLaps() {
        return laps;
    }
    
    public void setLaps(int laps) {
        this.laps = laps;
    }
    
    public double getTotalDistance() {
        return totalDistance;
    }
    
    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }
    
    public String getTrackType() {
        return trackType;
    }
    
    public void setTrackType(String trackType) {
        this.trackType = trackType;
    }
    
    public int getCorners() {
        return corners;
    }
    
    public void setCorners(int corners) {
        this.corners = corners;
    }
    
    public int getRaces() {
        return races;
    }
    
    public void setRaces(int races) {
        this.races = races;
    }
    
    @Override
    public String toString() {
        return name + " (" + country + ")";
    }
}
