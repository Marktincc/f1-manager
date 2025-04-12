package org.example.controllers;

import org.example.models.Driver;
import org.example.services.DriverService;

import java.util.List;
import java.util.Optional;

public class DriverController {
    private DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    public void mostrarTodosLosPilotos() {
        List<Driver> drivers = driverService.getAllDrivers();
        for (Driver driver : drivers) {
            System.out.println(driver);
        }
    }

    public void mostrarPilotoPorNombreCompleto(String nombreCompleto) {
        Optional<Driver> piloto = driverService.getDriverByName(nombreCompleto);
        piloto.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Piloto no encontrado: " + nombreCompleto)
        );
    }

    public void mostrarPilotosPorEquipo(String teamName) {
        List<Driver> drivers = driverService.getDriversByTeam(teamName);
        if (drivers.isEmpty()) {
            System.out.println("No hay pilotos registrados para el equipo: " + teamName);
        } else {
            drivers.forEach(System.out::println);
        }
    }

    public void mostrarRankingDePilotos() {
        List<Driver> drivers = driverService.getDriversSortedByPoints();
        int position = 1;
        for (Driver driver : drivers) {
            System.out.println(position + ". " + driver.getName() + " - " + driver.getPoints() + " puntos");
            position++;
        }
    }

    public void mostrarPosicionesPilotosEnCarrera(String nombreCarrera) {
        List<Driver> posiciones = driverService.getDriverPositionsForRace(nombreCarrera);

        if (posiciones.isEmpty()) {
            System.out.println("No hay datos para la carrera: " + nombreCarrera);
            return;
        }

        System.out.println("\n===== POSICIONES DE PILOTOS EN " + nombreCarrera.toUpperCase() + " =====");
        int posicion = 1;
        for (Driver driver : posiciones) {
            Driver.RacePosition racePos = driver.getRacePositions().stream()
                    .filter(pos -> pos.getRaceName().equalsIgnoreCase(nombreCarrera))
                    .findFirst().get();

            System.out.println(posicion + ". " + driver.getName() + " (" + driver.getTeamName() + ") - " +
                    "Salida: P" + racePos.getStartPosition() + ", Llegada: P" + racePos.getFinishPosition());
            posicion++;
        }
    }

    public void mostrarPuntosPilotosHastaCarrera(String nombreCarrera, List<String> todasLasCarreras) {
        List<Driver> clasificacion = driverService.getDriverPointsUpToRace(nombreCarrera, todasLasCarreras);

        if (clasificacion.isEmpty()) {
            System.out.println("No hay datos hasta la carrera: " + nombreCarrera);
            return;
        }

        System.out.println("\n===== PUNTOS DE PILOTOS HASTA " + nombreCarrera.toUpperCase() + " =====");
        int posicion = 1;
        for (Driver driver : clasificacion) {
            System.out.println(posicion + ". " + driver.getName() + " (" + driver.getTeamName() + ") - " +
                    driver.getPoints() + " puntos");
            posicion++;
        }
    }
}