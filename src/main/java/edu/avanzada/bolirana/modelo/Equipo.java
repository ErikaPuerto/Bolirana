package edu.avanzada.bolirana.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Equipo implements Serializable {
    private String nombre;
    private Lider lider;  // Se ha cambiado el tipo a Lider
    private List<Jugador> jugadores;
    private int puntajeTotal;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.jugadores = new ArrayList<>();
        this.puntajeTotal = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }
    
     // Nuevo método para obtener jugadores excluyendo al líder
    public List<Jugador> getJugadoresExcluyendoLider() {
        return jugadores.stream()
                        .filter(jugador -> !jugador.getNombre().equalsIgnoreCase(lider.getNombre()))
                        .collect(Collectors.toList());
    }

     public Lider getLider() {
        return lider;
    }

    public void setLider(Lider lider) {
        this.lider = lider;
    }

    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    public void agregarPuntaje(int puntaje) {
        this.puntajeTotal += puntaje;
    }

    // Agregar jugador al equipo
    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }
}