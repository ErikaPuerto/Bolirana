/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bolirana.controlador;

import com.mycompany.bolirana.modelo.Equipo;
import com.mycompany.bolirana.modelo.Jugador;

/**
 *
 * @author nedic
 */
public class ControladorEquipo {
    private Equipo equipo;

    public ControladorEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public void agregarJugador(Jugador jugador) {
        equipo.getJugadores().add(jugador);
    }

    public void agregarPuntaje(int puntaje) {
        equipo.setPuntajeTotal(equipo.getPuntajeTotal() + puntaje);
    }

    public boolean haGanado() {
        return equipo.getPuntajeTotal() >= 5000;
    }
}