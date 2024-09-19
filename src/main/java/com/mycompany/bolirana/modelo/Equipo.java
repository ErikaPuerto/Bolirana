/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bolirana.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un equipo en el juego de Bolirana.
 * Un equipo tiene un nombre, una lista de jugadores y un puntaje total.
 */
class Equipo {
    private String nombre;
    private List<Jugador> jugadores;
    private int puntajeTotal;

    /**
     * Constructor para crear un equipo.
     *
     * @param nombre El nombre del equipo.
     */
    public Equipo(String nombre) {
        this.nombre = nombre;
        this.jugadores = new ArrayList<>();
        this.puntajeTotal = 0;
    }

    /**
     * Método para agregar un jugador al equipo.
     *
     * @param jugador El jugador a agregar.
     */
    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }


    /**
     * Método que agrega puntos al puntaje total del equipo.
     *
     * @param puntos Los puntos que se sumarán al puntaje total.
     */
    public void agregarPuntaje(int puntaje) {
        this.puntajeTotal += puntaje;
    }

    /**
     * Metodo que verifica si el equipo ha alcanzado o superado los 5000 puntos.
     *
     * @return true si el equipo ha ganado, de lo contrario false.
     */
    public boolean haGanado() {
        return puntajeTotal >= 5000;
    }
    /**
     * Metodos getters
     */
    
    public String getNombre() {
        return nombre;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }
    
    public int getPuntajeTotal() {
        return puntajeTotal;
    }
}
