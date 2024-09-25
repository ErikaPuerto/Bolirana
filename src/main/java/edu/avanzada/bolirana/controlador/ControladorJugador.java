/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avanzada.bolirana.controlador;

import edu.avanzada.bolirana.modelo.Jugador;
import java.util.Random;

/**
 * Clase que controla la lógica asociada a un jugador en el juego de Bolirana.
 * La clase permite realizar acciones como simular un lanzamiento.
 * 
 * @author nedic
 */
public class ControladorJugador {

    private Jugador jugador;

    /**
     * Constructor que inicializa el controlador del jugador con un objeto de tipo Jugador.
     * 
     * @param jugador El jugador que será gestionado por este controlador.
     */
    public ControladorJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    /**
     * Simula el lanzamiento de un jugador en el juego. El puntaje del lanzamiento
     * es un número aleatorio entre 0 y 300 puntos.
     * 
     * @return Un valor entero que representa el puntaje obtenido en el lanzamiento.
     */
    public int lanzar() {
        return new Random().nextInt(301); // Simulación de lanzamiento (0-300 puntos)
    }
}