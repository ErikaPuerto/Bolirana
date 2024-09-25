package edu.avanzada.bolirana.modelo;

import java.io.Serializable;

/**
 * Clase que representa un juez en el juego Bolirana.
 * Un juez es una persona con una tarjeta profesional que lo habilita para supervisar el juego.
 * Hereda de la clase Persona e implementa Serializable para permitir la persistencia de los datos.
 */
public class Juez extends Persona implements Serializable {
    private String tarjetaProfesional;

    /**
     * Constructor para crear un nuevo juez.
     *
     * @param nombre              El nombre del juez.
     * @param cedula              La cédula del juez.
     * @param edad                La edad del juez.
     * @param tarjetaProfesional  La tarjeta profesional del juez.
     */
    public Juez(String nombre, String cedula, int edad, String tarjetaProfesional) {
        super(nombre, cedula, edad); // Llama al constructor de la clase Persona
        this.tarjetaProfesional = tarjetaProfesional;
    }

    /**
     * Obtiene la tarjeta profesional del juez.
     *
     * @return La tarjeta profesional del juez.
     */
    public String getTarjetaProfesional() {
        return tarjetaProfesional;
    }
}