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
        List<Team> equipos = teamService.getAllTeams();
        for (Team team : equipos) {
            System.out.println(team);
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
        List<Team> ordenados = teamService.getTeamsSortedByPoints();
        for (Team team : ordenados) {
            System.out.println(team.getName() + " - Puntos: " + team.getPoints());
        }
    }
}
