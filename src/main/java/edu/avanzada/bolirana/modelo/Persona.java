package edu.avanzada.bolirana.modelo;

import java.io.Serializable;

/**
 * Clase que representa a una persona en el juego de Bolirana.
 * Esta clase contiene atributos comunes a todas las personas, como nombre, cédula y edad.
 * Implementa la interfaz Serializable para permitir la persistencia de objetos de tipo Persona.
 */
public class Persona implements Serializable {
    private String nombre;
    private String cedula;
    private int edad;

    /**
     * Constructor para crear una persona con nombre, cédula y edad.
     *
     * @param nombre El nombre de la persona.
     * @param cedula La cédula de la persona.
     * @param edad   La edad de la persona.
     */
    public Persona(String nombre, String cedula, int edad) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
    }

    /**
     * Constructor para crear una persona usando solo la cédula.
     *
     * @param cedula La cédula de la persona.
     */
    public Persona(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Constructor para crear una persona con nombre y cédula.
     *
     * @param nombre El nombre de la persona.
     * @param cedula La cédula de la persona.
     */
    public Persona(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
    }

    /**
     * Getter para obtener el nombre de la persona.
     *
     * @return El nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Getter para obtener la cédula de la persona.
     *
     * @return La cédula de la persona.
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Getter para obtener la edad de la persona.
     *
     * @return La edad de la persona.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Setter para establecer la edad de la persona.
     *
     * @param edad La nueva edad de la persona.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }
}