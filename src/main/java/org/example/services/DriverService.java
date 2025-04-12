package org.example.services;

import org.example.models.Driver;
import org.example.models.Team;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DriverService {
    private List<Driver> drivers;

    public DriverService(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public List<Driver> getAllDrivers() {
        return drivers;
    }

    public Optional<Driver> getDriverByName(String name) {
        return drivers.stream()
                .filter(driver -> driver.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public List<Driver> getDriversByTeam(String teamName) {
        return drivers.stream()
                .filter(driver -> driver.getTeam() != null &&
                        driver.getTeam().getName().equalsIgnoreCase(teamName))
                .collect(Collectors.toList());
    }

    public List<Driver> getDriversSortedByPoints() {
        return drivers.stream()
                .sorted(Comparator.comparing(Driver::getPoints).reversed())
                .collect(Collectors.toList());
    }

    public void assignDriverToTeam(Driver driver, Team team) {
        team.addDriver(driver);
        driver.setTeam(team);
    }

    public void updateDriverPoints(Driver driver, int points) {
        driver.addPoints(points);
        if (driver.getTeam() != null) {
            driver.getTeam().calculatePoints();
        }
    }

    public List<Driver> getDriverPositionsForRace(String raceName) {
        return drivers.stream()
                .filter(driver -> driver.getRacePositions().stream()
                        .anyMatch(pos -> pos.getRaceName().equalsIgnoreCase(raceName)))
                .sorted(Comparator.comparing(driver ->
                        driver.getRacePositions().stream()
                                .filter(pos -> pos.getRaceName().equalsIgnoreCase(raceName))
                                .findFirst()
                                .get()
                                .getFinishPosition()))
                .collect(Collectors.toList());
    }

    public List<Driver> getDriverPointsUpToRace(String raceName, List<String> allRaces) {
        // Filtra las carreras que ocurrieron hasta la carrera 'raceName' en la lista 'allRaces'
        int raceIndex = allRaces.indexOf(raceName);
        if (raceIndex == -1) {
            return new ArrayList<>();
        }

        List<String> racesUpToRace = allRaces.subList(0, raceIndex + 1); // Incluye hasta la carrera 'raceName'

        // Calcula los puntos acumulados hasta la carrera
        for (Driver driver : drivers) {
            int totalPoints = 0;
            for (Driver.RacePosition position : driver.getRacePositions()) {
                if (racesUpToRace.contains(position.getRaceName())) {
                    totalPoints += calculatePointsForPosition(position.getFinishPosition());
                }
            }
            driver.setPoints(totalPoints); // Asegúrate de actualizar los puntos acumulados
        }

        return drivers;
    }

    private int calculatePointsForPosition(int finishPosition) {
        // Asegúrate de que las posiciones estén correctamente mapeadas a puntos
        switch (finishPosition) {
            case 1: return 25;
            case 2: return 18;
            case 3: return 15;
            case 4: return 12;
            case 5: return 10;
            case 6: return 8;
            case 7: return 6;
            case 8: return 4;
            case 9: return 2;
            case 10: return 1;
            default: return 0;
        }
    }

}