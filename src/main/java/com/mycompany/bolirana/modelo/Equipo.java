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
public class Equipo {
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

    public void setPuntajeTotal(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
