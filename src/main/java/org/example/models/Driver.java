package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    @JsonProperty("nombre")
    private String name;

    @JsonProperty("pais_origen")
    private String nationality;

    @JsonProperty("edad")
    private int age;

    @JsonProperty("equipo")
    private String teamName;

    @JsonProperty("campeonatos_ganados")
    private int championshipsWon;

    @JsonProperty("carreras_disputadas")
    private int racesDisputed;

    @JsonProperty("puntos_acumulados_2024")
    private int points;

    @JsonProperty("posiciones_carrera")
    private List<RacePosition> racePositions;

    @JsonIgnore
    private Team team;

    // Default constructor for Jackson
//    private List<RaceResult> racePositions = new ArrayList<>();
    public Driver() {
        this.racePositions = new ArrayList<>();
    }

    public Driver(String name, String nationality, int age) {
        this.name = name;
        this.nationality = nationality;
        this.age = age;
        this.points = 0;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getChampionshipsWon() {
        return championshipsWon;
    }

    public void setChampionshipsWon(int championshipsWon) {
        this.championshipsWon = championshipsWon;
    }

    public int getRacesDisputed() {
        return racesDisputed;
    }

    public void setRacesDisputed(int racesDisputed) {
        this.racesDisputed = racesDisputed;
    }

    public List<RacePosition> getRacePositions() {
        if (racePositions == null) {
            racePositions = new ArrayList<>();
        }
        return racePositions;
    }

    public void setRacePositions(List<RacePosition> racePositions) {
        this.racePositions = racePositions;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int additionalPoints) {
        this.points += additionalPoints;
    }

    @Override
    public String toString() {
        return name + " (" + nationality + ") - " + points + " pts";
    }

    // Inner class for race positions
    public static class RacePosition {
        private String raceName;

        @JsonProperty("posicionPartida")
        private int startPosition;

        @JsonProperty("posicionLlegada")
        private int finishPosition;
        private List<RaceResult> racePositions;

        // Default constructor for Jackson
        public RacePosition() {
        }

        public String getRaceName() {
            return raceName;
        }

        public void setRaceName(String raceName) {
            this.raceName = raceName;
        }

        public List<RaceResult> getRacePositions() {
            if (this.racePositions == null) {
                this.racePositions = new ArrayList<>();
            }
            return racePositions;
        }

        public void setRacePositions(List<RaceResult> racePositions) {
            this.racePositions = racePositions;
        }

        public int getFinishPosition() {
            return finishPosition;
        }

        public void setFinishPosition(int finishPosition) {
            this.finishPosition = finishPosition;
        }

        public void setStartPosition(int startPosition) {
            this.startPosition = startPosition;
        }

        public int getStartPosition() {
            return startPosition;
        }
    }
    public void addRacePosition(RacePosition position) {
        if (racePositions == null) {
            racePositions = new ArrayList<>();
        }
        racePositions.add(position);
    }

    public void clearRacePositions() {
        if (racePositions != null) {
            racePositions.clear();
        }
    }
}