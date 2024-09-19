/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bolirana.modelo;

import java.util.Random;

/**
 * Clase que representa a un jugador en el juego de Bolirana.
 * Hereda de la clase Persona e incluye la posición del jugador.
 */
public class Jugador extends Persona {
    private String posicion;

    /**
     * Constructor para crear un jugador.
     *
     * @param nombre El nombre del jugador.
     * @param cedula La cédula del jugador.
     * @param edad La edad del jugador.
     * @param posicion La posición del jugador en el juego.
     */
    public Jugador(String nombre, String cedula, int edad, String posicion) {
        super(nombre, cedula, edad);
        this.posicion = posicion;
    }
    /**
     * Metodo get para posicion
     *
     */
    public String getPosicion() {
        return posicion;
    }
}
