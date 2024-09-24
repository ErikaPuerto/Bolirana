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
 *
 * @author nedic
 */
public class ControladorJuez {

    private Juez juez;

    public ControladorJuez(Juez juez) {
        this.juez = juez;
    }

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
