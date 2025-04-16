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

    public List<Driver> getDriverPointsUpToRace(String raceName, List<String> allRaceNames) {
        // Find the index of the specified race
        int raceIndex = allRaceNames.indexOf(raceName);
        if (raceIndex == -1) {
            return new ArrayList<>();
        }

        // Get races up to the specified one
        List<String> racesUpToTarget = allRaceNames.subList(0, raceIndex + 1);

        // Create a copy of drivers to avoid modifying the original list
        List<Driver> driversWithCalculatedPoints = new ArrayList<>();

        for (Driver originalDriver : drivers) {
            Driver driverCopy = new Driver();
            driverCopy.setName(originalDriver.getName());
            driverCopy.setTeamName(originalDriver.getTeamName());

            // Calculate points up to the specified race
            int totalPoints = 0;
            for (Driver.RacePosition position : originalDriver.getRacePositions()) {
                if (racesUpToTarget.contains(position.getRaceName())) {
                    totalPoints += calculatePointsForPosition(position.getFinishPosition());
                }
            }
            driverCopy.setPoints(totalPoints);
            driversWithCalculatedPoints.add(driverCopy);
        }

        // Sort by points
        return driversWithCalculatedPoints.stream()
                .sorted(Comparator.comparing(Driver::getPoints).reversed())
                .collect(Collectors.toList());
    }

    private int calculatePointsForPosition(int position) {
        // F1 points system
        switch (position) {
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