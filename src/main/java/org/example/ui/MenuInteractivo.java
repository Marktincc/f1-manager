
package org.example.ui;

import org.example.models.Circuit;
import org.example.models.Driver;
import org.example.models.Team;
import org.example.services.CircuitService;
import org.example.services.DriverService;
import org.example.services.TeamService;

import java.util.List;
import java.util.Scanner;

public class MenuInteractivo {
    private final Scanner scanner;
    private final DriverService driverService;
    private final TeamService teamService;
    private final CircuitService circuitService;

    public MenuInteractivo(DriverService driverService, TeamService teamService, CircuitService circuitService) {
        this.scanner = new Scanner(System.in);
        this.driverService = driverService;
        this.teamService = teamService;
        this.circuitService = circuitService;
    }

    public void mostrarMenuPrincipal() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n===== F1 MANAGER 2024 =====");
            System.out.println("1. Información de Pilotos");
            System.out.println("2. Información de Equipos");
            System.out.println("3. Estadísticas de la Temporada");
            System.out.println("4. Comparativas");
            System.out.println("5. Información de Circuitos");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = obtenerOpcionNumerica();

            switch (opcion) {
                case 1:
                    mostrarMenuPilotos();
                    break;
                case 2:
                    mostrarMenuEquipos();
                    break;
                case 3:
                    mostrarMenuEstadisticas();
                    break;
                case 4:
                    mostrarMenuComparativas();
                    break;
                case 5:
                    mostrarMenuCircuitos();
                    break;
                case 6:
                    salir = true;
                    System.out.println("¡Gracias por usar F1 Manager 2024!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private void mostrarMenuPilotos() {
        boolean volver = false;

        while (!volver) {
            System.out.println("\n===== INFORMACIÓN DE PILOTOS =====");
            System.out.println("1. Ver todos los pilotos");
            System.out.println("2. Buscar piloto por nombre");
            System.out.println("3. Ver pilotos ordenados por puntos");
            System.out.println("4. Ver pilotos por equipo");
            System.out.println("5. Ver detalles de un piloto");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = obtenerOpcionNumerica();

            switch (opcion) {
                case 1:
                    mostrarTodosLosPilotos();
                    break;
                case 2:
                    buscarPilotoPorNombre();
                    break;
                case 3:
                    mostrarPilotosOrdenadosPorPuntos();
                    break;
                case 4:
                    mostrarPilotosPorEquipo();
                    break;
                case 5:
                    mostrarDetallesPiloto();
                    break;
                case 6:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private void mostrarMenuEquipos() {
        boolean volver = false;

        while (!volver) {
            System.out.println("\n===== INFORMACIÓN DE EQUIPOS =====");
            System.out.println("1. Ver todos los equipos");
            System.out.println("2. Buscar equipo por nombre");
            System.out.println("3. Ver equipos ordenados por puntos");
            System.out.println("4. Ver pilotos de un equipo");
            System.out.println("5. Ver rendimiento de un equipo por circuito");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = obtenerOpcionNumerica();

            switch (opcion) {
                case 1:
                    mostrarTodosLosEquipos();
                    break;
                case 2:
                    buscarEquipoPorNombre();
                    break;
                case 3:
                    mostrarEquiposOrdenadosPorPuntos();
                    break;
                case 4:
                    mostrarPilotosDeEquipo();
                    break;
                case 5:
                    mostrarRendimientoEquipoPorCircuito();
                    break;
                case 6:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private void mostrarMenuEstadisticas() {
        boolean volver = false;

        while (!volver) {
            System.out.println("\n===== ESTADÍSTICAS DE LA TEMPORADA =====");
            System.out.println("1. Clasificación de pilotos");
            System.out.println("2. Clasificación de equipos");
            System.out.println("3. Mejores rendimientos en clasificación");
            System.out.println("4. Mejores rendimientos en carrera");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = obtenerOpcionNumerica();

            switch (opcion) {
                case 1:
                    mostrarClasificacionPilotos();
                    break;
                case 2:
                    mostrarClasificacionEquipos();
                    break;
                case 3:
                    mostrarMejoresClasificacion();
                    break;
                case 4:
                    mostrarMejoresCarrera();
                    break;
                case 5:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private void mostrarMenuComparativas() {
        boolean volver = false;

        while (!volver) {
            System.out.println("\n===== COMPARATIVAS =====");
            System.out.println("1. Comparar pilotos");
            System.out.println("2. Comparar equipos");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = obtenerOpcionNumerica();

            switch (opcion) {
                case 1:
                    compararPilotos();
                    break;
                case 2:
                    compararEquipos();
                    break;
                case 3:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
    private void mostrarMenuCircuitos() {
        boolean volver = false;

        while (!volver) {
            System.out.println("\n===== INFORMACIÓN DE CIRCUITOS =====");
            System.out.println("1. Ver todos los circuitos");
            System.out.println("2. Buscar circuito por nombre");
            System.out.println("3. Ver circuitos por país");
            System.out.println("4. Ver detalles de un circuito");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = obtenerOpcionNumerica();

            switch (opcion) {
                case 1:
                    mostrarTodosLosCircuitos();
                    break;
                case 2:
                    buscarCircuitoPorNombre();
                    break;
                case 3:
                    mostrarCircuitosPorPais();
                    break;
                case 4:
                    mostrarDetallesCircuito();
                    break;
                case 5:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
    private void mostrarTodosLosCircuitos() {
        System.out.println("\n===== TODOS LOS CIRCUITOS =====");
        List<Circuit> circuitos = circuitService.getAllCircuits();
        for (Circuit circuito : circuitos) {
            System.out.println(circuito.getName() + " - " + circuito.getCountry());
        }
        esperarEnter();
    }

    private void buscarCircuitoPorNombre() {
        System.out.print("\nIngrese el nombre del circuito: ");
        String nombre = scanner.nextLine();
        circuitService.getCircuitByName(nombre).ifPresentOrElse(circuit -> {
            System.out.println("\n===== INFORMACIÓN DEL CIRCUITO =====");
            System.out.println("Nombre: " + circuit.getName());
            System.out.println("País: " + circuit.getCountry());
            System.out.println("Longitud: " + circuit.getLength() + " km");
        }, () -> System.out.println("Circuito no encontrado."));

        esperarEnter();
    }

    private void mostrarCircuitosPorPais() {
        System.out.print("\nIngrese el país: ");
        String pais = scanner.nextLine();

        List<Circuit> circuitos = circuitService.getCircuitsByCountry(pais);

        System.out.println("\n===== CIRCUITOS EN " + pais.toUpperCase() + " =====");

        if (circuitos.isEmpty()) {
            System.out.println("No se encontraron circuitos en este país.");
        } else {
            circuitos.forEach(circuito -> {
                System.out.println("- " + circuito.getName() + " (" + circuito.getLength() + " km)");
            });
        }

        esperarEnter();
    }


    private void mostrarDetallesCircuito() {
        System.out.print("\nIngrese el nombre del circuito: ");
        String nombre = scanner.nextLine();

        circuitService.getCircuitByName(nombre).ifPresentOrElse(circuit -> {
            System.out.println("\n===== DETALLES DEL CIRCUITO: " + circuit.getName().toUpperCase() + " =====");
            System.out.println("Nombre: " + circuit.getName());
            System.out.println("País: " + circuit.getCountry());
            System.out.println("Longitud: " + circuit.getLength() + " km");
            System.out.println("Vueltas: " + circuit.getLaps());
            System.out.println("Distancia total: " + circuit.getTotalDistance() + " km");
        }, () -> System.out.println("Circuito no encontrado."));

        esperarEnter();
    }

    // Métodos para mostrar información de pilotos
    private void mostrarTodosLosPilotos() {
        System.out.println("\n===== TODOS LOS PILOTOS =====");
        List<Driver> pilotos = driverService.getAllDrivers();
        for (Driver piloto : pilotos) {
            System.out.println(piloto.getName() + " - " + piloto.getTeamName());
        }
        esperarEnter();
    }

    private void buscarPilotoPorNombre() {
        System.out.print("\nIngrese el nombre del piloto: ");
        String nombre = scanner.nextLine();
        driverService.getDriverByName(nombre).ifPresentOrElse(
                piloto -> {
                    System.out.println("\n===== INFORMACIÓN DEL PILOTO =====");
                    System.out.println("Nombre: " + piloto.getName());
                    System.out.println("Equipo: " + piloto.getTeamName());
                    System.out.println("País: " + piloto.getNationality());
                    System.out.println("Edad: " + piloto.getAge());
                    System.out.println("Puntos 2024: " + piloto.getPoints());
                },
                () -> System.out.println("Piloto no encontrado.")
        );
        esperarEnter();
    }

    private void mostrarPilotosOrdenadosPorPuntos() {
        System.out.println("\n===== PILOTOS ORDENADOS POR PUNTOS =====");
        List<Driver> pilotos = driverService.getDriversSortedByPoints();
        int posicion = 1;
        for (Driver piloto : pilotos) {
            System.out.println(posicion + ". " + piloto.getName() + " - " + piloto.getPoints() + " puntos");
            posicion++;
        }
        esperarEnter();
    }

    private void mostrarPilotosPorEquipo() {
        System.out.print("\nIngrese el nombre del equipo: ");
        String equipo = scanner.nextLine();
        System.out.println("\n===== PILOTOS DE " + equipo.toUpperCase() + " =====");
        List<Driver> pilotos = driverService.getDriversByTeam(equipo);
        if (pilotos.isEmpty()) {
            System.out.println("No se encontraron pilotos para este equipo o el equipo no existe.");
        } else {
            for (Driver piloto : pilotos) {
                System.out.println(piloto.getName() + " - " + piloto.getPoints() + " puntos");
            }
        }
        esperarEnter();
    }

    private void mostrarDetallesPiloto() {
        System.out.print("\nIngrese el nombre del piloto: ");
        String nombre = scanner.nextLine();
        driverService.getDriverByName(nombre).ifPresentOrElse(
                piloto -> {
                    System.out.println("\n===== DETALLES DE " + piloto.getName().toUpperCase() + " =====");
                    System.out.println("Equipo: " + piloto.getTeamName());
                    System.out.println("País: " + piloto.getNationality());
                    System.out.println("Edad: " + piloto.getAge());
                    System.out.println("Campeonatos ganados: " + piloto.getChampionshipsWon());
                    // System.out.println("Carreras disputadas: " + piloto.getRacesCompleted());
                    System.out.println("Puntos 2024: " + piloto.getPoints());

                    System.out.println("\nResultados 2024:");
                    piloto.getRacePositions().forEach(posicion -> {
                        System.out.println(posicion.getRaceName() + ": P" + posicion.getStartPosition() + " → P" + posicion.getFinishPosition());
                    });
                },
                () -> System.out.println("Piloto no encontrado.")
        );
        esperarEnter();
    }

    // Métodos para mostrar información de equipos
    private void mostrarTodosLosEquipos() {
        System.out.println("\n===== TODOS LOS EQUIPOS =====");
        List<Team> equipos = teamService.getAllTeams();
        for (Team equipo : equipos) {
            System.out.println(equipo.getName());
        }
        esperarEnter();
    }

    private void buscarEquipoPorNombre() {
        System.out.print("\nIngrese el nombre del equipo: ");
        String nombre = scanner.nextLine();
        teamService.getTeamByName(nombre).ifPresentOrElse(
                equipo -> {
                    System.out.println("\n===== INFORMACIÓN DEL EQUIPO =====");
                    System.out.println("Nombre: " + equipo.getName());
                    System.out.println("Puntos: " + equipo.getPoints());
                },
                () -> System.out.println("Equipo no encontrado.")
        );
        esperarEnter();
    }

    private void mostrarEquiposOrdenadosPorPuntos() {
        System.out.println("\n===== EQUIPOS ORDENADOS POR PUNTOS =====");
        List<Team> equipos = teamService.getTeamsSortedByPoints();
        int posicion = 1;
        for (Team equipo : equipos) {
            System.out.println(posicion + ". " + equipo.getName() + " - " + equipo.getPoints() + " puntos");
            posicion++;
        }
        esperarEnter();
    }

    private void mostrarPilotosDeEquipo() {
        System.out.print("\nIngrese el nombre del equipo: ");
        String nombre = scanner.nextLine();
        teamService.getTeamByName(nombre).ifPresentOrElse(
                equipo -> {
                    System.out.println("\n===== PILOTOS DE " + equipo.getName().toUpperCase() + " =====");
                    // List<Driver> pilotos = teamService.getDriversByTeam(nombre);
                    // for (Driver piloto : pilotos) {
                    //     System.out.println(piloto.getName() + " - " + piloto.getPoints() + " puntos");
                    // }
                },
                () -> System.out.println("Equipo no encontrado.")
        );
        esperarEnter();
    }

    private void mostrarRendimientoEquipoPorCircuito() {
        // Implementación pendiente
        System.out.println("\nFuncionalidad en desarrollo.");
        esperarEnter();
    }

    // Métodos para mostrar estadísticas
    private void mostrarClasificacionPilotos() {
        System.out.println("\n===== CLASIFICACIÓN DE PILOTOS 2024 =====");
        List<Driver> pilotos = driverService.getDriversSortedByPoints();
        int posicion = 1;
        for (Driver piloto : pilotos) {
            System.out.println(posicion + ". " + piloto.getName() + " (" + piloto.getTeamName() + ") - " + piloto.getPoints() + " puntos");
            posicion++;
        }
        esperarEnter();
    }

    private void mostrarClasificacionEquipos() {
        System.out.println("\n===== CLASIFICACIÓN DE EQUIPOS 2024 =====");
        List<Team> equipos = teamService.getTeamsSortedByPoints();
        int posicion = 1;
        for (Team equipo : equipos) {
            System.out.println(posicion + ". " + equipo.getName() + " - " + equipo.getPoints() + " puntos");
            posicion++;
        }
        esperarEnter();
    }

    private void mostrarMejoresClasificacion() {
        // Implementación pendiente
        System.out.println("\nFuncionalidad en desarrollo.");
        esperarEnter();
    }

    private void mostrarMejoresCarrera() {
        // Implementación pendiente
        System.out.println("\nFuncionalidad en desarrollo.");
        esperarEnter();
    }

    // Métodos para comparativas
    private void compararPilotos() {
        System.out.print("\nIngrese el nombre del primer piloto: ");
        String nombre1 = scanner.nextLine();
        System.out.print("Ingrese el nombre del segundo piloto: ");
        String nombre2 = scanner.nextLine();

        var piloto1 = driverService.getDriverByName(nombre1);
        var piloto2 = driverService.getDriverByName(nombre2);

        if (piloto1.isPresent() && piloto2.isPresent()) {
            System.out.println("\n===== COMPARATIVA: " + piloto1.get().getName() + " vs " + piloto2.get().getName() + " =====");
            System.out.println("Puntos: " + piloto1.get().getPoints() + " vs " + piloto2.get().getPoints());
            System.out.println("Equipo: " + piloto1.get().getTeamName() + " vs " + piloto2.get().getTeamName());
            System.out.println("Edad: " + piloto1.get().getAge() + " vs " + piloto2.get().getAge());
            System.out.println("Campeonatos: " + piloto1.get().getChampionshipsWon() + " vs " + piloto2.get().getChampionshipsWon());
            // System.out.println("Carreras disputadas: " + piloto1.get().getRacesCompleted() + " vs " + piloto2.get().getRacesCompleted());
        } else {
            System.out.println("Uno o ambos pilotos no fueron encontrados.");
        }
        esperarEnter();
    }

    private void compararEquipos() {
        System.out.print("\nIngrese el nombre del primer equipo: ");
        String nombre1 = scanner.nextLine();
        System.out.print("Ingrese el nombre del segundo equipo: ");
        String nombre2 = scanner.nextLine();

        var equipo1 = teamService.getTeamByName(nombre1);
        var equipo2 = teamService.getTeamByName(nombre2);

        if (equipo1.isPresent() && equipo2.isPresent()) {
            System.out.println("\n===== COMPARATIVA: " + equipo1.get().getName() + " vs " + equipo2.get().getName() + " =====");
            System.out.println("Puntos: " + equipo1.get().getPoints() + " vs " + equipo2.get().getPoints());
        } else {
            System.out.println("Uno o ambos equipos no fueron encontrados.");
        }
        esperarEnter();
    }

    // Métodos auxiliares
    private int obtenerOpcionNumerica() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void esperarEnter() {
        System.out.println("\nPresione ENTER para continuar...");
        scanner.nextLine();
    }
}
