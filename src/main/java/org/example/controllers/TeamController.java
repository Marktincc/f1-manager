package org.example.controllers;

import org.example.models.Team;
import org.example.services.TeamService;

import java.util.List;
import java.util.Optional;

public class TeamController {
    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    public void mostrarTodosLosEquipos() {
        if (teamService.getAllTeams().isEmpty()) {
            System.out.println("No hay equipos registrados.");
        } else {
            teamService.getAllTeams().forEach(team -> System.out.println(team));
        }
    }


    public void mostrarEquipoPorNombre(String nombre) {
        Optional<Team> equipo = teamService.getTeamByName(nombre);
        equipo.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("No se encontró el equipo: " + nombre)
        );
    }

    public void mostrarEquiposOrdenadosPorPuntos() {
        System.out.println("RANKING DE EQUIPOS");
        teamService.getAllTeams()
                .stream()
                .sorted((team1, team2) -> Integer.compare(team2.getPoints(), team1.getPoints())) // Ordenar por puntos
                .forEach(team -> System.out.println(team.getName() + " - " + team.getPoints() + " puntos"));
    }


}