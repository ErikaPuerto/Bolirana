/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bolirana.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa el juego de Bolirana.
 * Se encarga de gestionar los equipos, turnos y puntajes de la partida.
 */
public class Bolirana {
    private List<Equipo> equipos;
    private Juez juez;

    /**
     * Constructor para crear un juego de Bolirana.
     *
     * @param juez El juez encargado del juego.
     */
    public Bolirana(Juez juez) {
        this.equipos = new ArrayList<>();
        this.juez = juez;
    }

    /**
     * Método para agregar un equipo a la partida.
     *
     * @param equipo El equipo a agregar.
     */
    public void agregarEquipo(Equipo equipo) {
        if (equipos.size() < 6) {
            equipos.add(equipo);
        } else {
            throw new IllegalStateException("No se pueden agregar más de 6 equipos.");
        }
    }

    /**
     * Inicia el juego, determinando el equipo inicial y llamando al método para jugar la partida.
     */
    public void iniciarJuego() {
        Equipo equipoInicial = juez.determinarEquipoInicial(equipos);
        System.out.println("El juez ha decidido que el equipo " + equipoInicial.getNombre() + " empieza.");
        jugarPartida(equipoInicial);
    }

    /**
     * Método que maneja la lógica de la partida, alternando los turnos entre los equipos
     * hasta que uno alcance el puntaje objetivo.
     *
     * @param equipoInicial El equipo que comienza la partida.
     */
    private void jugarPartida(Equipo equipoInicial) {
        boolean juegoActivo = true;
        int turno = equipos.indexOf(equipoInicial);
        int cantidadEquipos = equipos.size();

        while (juegoActivo) {
            for (int i = 0; i < equipos.get(turno).getJugadores().size(); i++) {
                Equipo equipoActual = equipos.get(turno);
                Jugador jugador = equipoActual.getJugadores().get(i);

                int puntaje = jugador.lanzar();
                equipoActual.agregarPuntaje(puntaje);

                System.out.println(jugador.getNombre() + " del equipo " + equipoActual.getNombre() + 
                                   " lanzo y obtuvo " + puntaje + " puntos. Puntaje total del equipo: " + equipoActual.getPuntajeTotal());

                if (equipoActual.haGanado()) {
                    System.out.println("El equipo " + equipoActual.getNombre() + " ha ganado el juego con " + equipoActual.getPuntajeTotal() + " puntos!");
                    juegoActivo = false;
                    break;
                }
            }
            turno = (turno + 1) % cantidadEquipos;
        }
    }
}
