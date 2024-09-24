/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avanzada.bolirana.controlador;

import edu.avanzada.bolirana.modelo.Jugador;
import java.util.Random;

public class ControladorJugador {
    private Jugador jugador;

    public ControladorJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public int lanzar() {
        return new Random().nextInt(301); // Simulación de lanzamiento (0-300 puntos)
    }
}

