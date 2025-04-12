package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.controllers.CircuitController;
import org.example.controllers.DriverController;
import org.example.controllers.EventController;
import org.example.controllers.TeamController;
import org.example.models.Circuit;
import org.example.models.Driver;
import org.example.models.Event;
import org.example.models.Season;
import org.example.models.Team;
import org.example.services.CircuitService;
import org.example.services.DriverService;
import org.example.services.EventService;
import org.example.services.TeamService;
import org.example.ui.MenuInteractivo;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Leer archivos JSON desde resources
            InputStream teamStream = Main.class.getResourceAsStream("/teams.json");
            InputStream driverStream = Main.class.getResourceAsStream("/drivers.json");
            InputStream circuitStream = Main.class.getResourceAsStream("/circuitos_carreras.json");

            // Deserializar listas
            List<Team> teams = mapper.readValue(teamStream, new TypeReference<List<Team>>() {});
            List<Driver> drivers = mapper.readValue(driverStream, new TypeReference<List<Driver>>() {});

            // Asociar pilotos a sus equipos por nombre
            for (Team team : teams) {
                for (String driverName : team.getDriverNames()) {
                    for (Driver driver : drivers) {
                        if (driver.getName().equalsIgnoreCase(driverName)) {
                            team.addDriver(driver);
                            driver.setTeam(team);
                        }
                    }
                }
                team.calculatePoints(); // Actualizar puntos del equipo basado en sus pilotos
            }

            // Deserializar Season y extraer circuitos
            Season season = mapper.readValue(circuitStream, Season.class);
            List<Circuit> circuits = season.getEvents().stream()
                    .map(Event::getCircuit)
                    .collect(Collectors.toList());
            List<Event> events = season.getEvents(); // Add this line to get the events list

            // Inicializar servicios
            TeamService teamService = new TeamService(teams);
            DriverService driverService = new DriverService(drivers);
            CircuitService circuitService = new CircuitService(circuits);
            EventService eventService = new EventService(events); // Now events is properly defined

            // Inicializar controladores
            TeamController teamController = new TeamController(teamService);
            DriverController driverController = new DriverController(driverService);
            CircuitController circuitController = new CircuitController(circuitService);
            EventController eventController = new EventController(eventService);

            // Ejemplos de uso
            System.out.println("===== EQUIPOS ORDENADOS POR PUNTOS =====");
            teamController.mostrarEquiposOrdenadosPorPuntos();

            System.out.println("\n===== EQUIPO RED BULL =====");
            teamController.mostrarEquipoPorNombre("Red bull");

            System.out.println("\n===== PILOTOS DE RED BULL RACING =====");
            driverController.mostrarPilotosPorEquipo("Red Bull");

            System.out.println("\n===== RANKING DE PILOTOS =====");
            driverController.mostrarRankingDePilotos();

            System.out.println("\n===== MOSTRAR TODOS LOS PILOTOS =====");
            driverController.mostrarTodosLosPilotos();

            System.out.println("\n===== MOSTRAR TODOS LOS CIRCUITOS =====");
            circuitController.getAllCircuits();

            System.out.println("\n===== CARRERAS EN ITALIA =====");
            circuitController.getCircuitsByCountry("Italia");

            eventController.mostrarInformacionGeneralCarreras();

            MenuInteractivo menu = new MenuInteractivo(driverService, teamService, circuitService);
            menu.mostrarMenuPrincipal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
