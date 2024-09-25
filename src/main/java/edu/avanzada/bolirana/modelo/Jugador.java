package edu.avanzada.bolirana.modelo;

import java.io.Serializable;

/**
 * Clase que representa a un jugador en el juego Bolirana.
 * Un jugador tiene un nombre, cédula, edad y un número de jugador.
 * Implementa Serializable para permitir la persistencia de los datos.
 */
public class Jugador implements Serializable {
    private String nombre;
    private String cedula;
    private int edad;
    private int numero;

    /**
     * Constructor para crear un nuevo jugador.
     *
     * @param nombre  El nombre del jugador.
     * @param cedula  La cédula del jugador.
     * @param edad    La edad del jugador.
     * @param numero  El número asignado al jugador.
     */
    public Jugador(String nombre, String cedula, int edad, int numero) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
        this.numero = numero;
    }

    /**
     * Obtiene el nombre del jugador.
     *
     * @return El nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la cédula del jugador.
     *
     * @return La cédula del jugador.
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Obtiene la edad del jugador.
     *
     * @return La edad del jugador.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Obtiene el número asignado al jugador.
     *
     * @return El número del jugador.
     */
    public int getNumero() {
        return numero;
    }
}