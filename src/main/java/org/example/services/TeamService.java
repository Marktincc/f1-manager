package org.example.services;

import org.example.models.Team;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TeamService {
    private List<Team> teams;

    public TeamService(List<Team> teams) {
        this.teams = teams;
    }

    public List<Team> getAllTeams() {
        return teams;
    }

    public Optional<Team> getTeamByName(String name) {
        return teams.stream()
                .filter(team -> team.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public List<Team> getTeamsSortedByPoints() {
        return teams.stream()
                .sorted(Comparator.comparing(Team::getPoints).reversed())
                .collect(Collectors.toList());
    }

    public void updateTeamPoints() {
        for (Team team : teams) {
            team.calculatePoints();
        }
    }
}