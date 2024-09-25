/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avanzada.bolirana.controlador;

import edu.avanzada.bolirana.modelo.Equipo;
import edu.avanzada.bolirana.modelo.Juez;

import java.util.List;
import java.util.Random;

/**
 * Clase que gestiona la lógica relacionada con el juez en el juego de Bolirana.
 * El juez tiene la capacidad de determinar el equipo que iniciará el partido
 * de manera aleatoria, entre otros comportamientos relacionados.
 * 
 * @author nedic
 */
public class ControladorJuez {

    private Juez juez;

    /**
     * Constructor que inicializa el controlador del juez.
     * 
     * @param juez El juez del juego que gestionará decisiones importantes durante el partido.
     */
    public ControladorJuez(Juez juez) {
        this.juez = juez;
    }

    /**
     * Determina de manera aleatoria el equipo que iniciará el partido.
     * 
     * @param equipos Lista de equipos participantes.
     * @return El equipo que ha sido seleccionado para iniciar el partido.
     * @throws IllegalArgumentException Si la lista de equipos está vacía.
     */
    public Equipo determinarEquipoInicial(List<Equipo> equipos) {
        if (equipos.isEmpty()) {
            throw new IllegalArgumentException("La lista de equipos no puede estar vacía.");
        }
        if (equipos.size() == 1) {
            return equipos.get(0); // Si solo hay un equipo, ese será el inicial.
        }

        Random random = new Random();
        int equipoInicialIndex = random.nextInt(equipos.size());
        return equipos.get(equipoInicialIndex);
    }
}