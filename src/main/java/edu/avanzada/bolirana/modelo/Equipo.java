package edu.avanzada.bolirana.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Equipo implements Serializable {
    private String nombre;
    private Lider lider;
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

    // Excluir al líder de la lista de jugadores
    public List<Jugador> getJugadoresSinLider() {
        return jugadores; // Solo jugadores, sin el líder
    }
}

