package org.example.controllers;

import org.example.models.Event;
import org.example.services.EventService;

import java.util.List;

public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    public void mostrarInformacionGeneralCarreras() {
        List<Event> eventos = eventService.getAllEvents();
        for (Event evento : eventos) {
            System.out.println("=".repeat(50));
            System.out.println("Carrera #" + evento.getRaceNumber() + ": " + evento.getName());
            System.out.println("Circuito: " + evento.getCircuit().getName());
            System.out.println("País: " + evento.getCircuit().getCountry());
            System.out.println("Ciudad: " + evento.getCircuit().getCity());
            System.out.printf("Longitud: %.3f km%n", evento.getCircuit().getLength());
            System.out.println("Vueltas: " + evento.getCircuit().getLaps());
            System.out.printf("Distancia total: %.3f km%n", evento.getCircuit().getTotalDistance());
            System.out.println("Fecha carrera: " + evento.getRaceDate());
            System.out.println("Fecha sprint: " + (evento.getSprintDate() != null ? evento.getSprintDate() : "No hay sprint"));
        }
        System.out.println("=".repeat(50));
    }
}