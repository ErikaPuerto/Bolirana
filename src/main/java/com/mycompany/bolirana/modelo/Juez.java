/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bolirana.modelo;

import java.util.List;
import java.util.Random;

/**
 * Clase que representa al juez del juego de Bolirana.
 * Hereda de la clase Persona e incluye la tarjeta profesional.
 */
class Juez extends Persona {
    private String tarjetaProfesional;

    /**
     * Constructor para crear un juez.
     *
     * @param nombre El nombre del juez.
     * @param cedula La cédula del juez.
     * @param edad La edad del juez.
     * @param tarjetaProfesional La tarjeta profesional del juez.
     */
    public Juez(String nombre, String cedula, int edad, String tarjetaProfesional) {
        super(nombre, cedula, edad);
        this.tarjetaProfesional = tarjetaProfesional;
    }
    /**
     * Método getter para tarjeta profesional
     */
    public String getTarjetaProfesional() {
        return tarjetaProfesional;
    }

    /**
     * Método que determina aleatoriamente cuál equipo comienza el juego.
     *
     * @param equipos Lista de equipos que participan en el juego.
     * @return El equipo que fue seleccionado para iniciar.
     */
    
    public Equipo determinarEquipoInicial(List<Equipo> equipos) {
        Random random = new Random();
        return equipos.get(random.nextInt(equipos.size()));
    }
}