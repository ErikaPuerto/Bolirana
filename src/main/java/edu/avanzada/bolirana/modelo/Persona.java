package edu.avanzada.bolirana.modelo;

/**
 * Clase que representa a una persona en el juego de Bolirana.
 * Tiene atributos comunes a todas las personas como nombre, cédula y edad.
 */
public class Persona {
    private String nombre;
    private String cedula;
    private int edad;

    /**
     * Constructor para crear una persona.
     *
     * @param nombre El nombre de la persona.
     * @param cedula La cédula de la persona.
     * @param edad La edad de la persona.
     */
    public Persona(String nombre, String cedula, int edad) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
    }

    /**
     * Getter para nombre.
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Getter para cédula.
     * @return cédula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Getter para edad.
     * @return edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Setter para la edad.
     * @param edad La nueva edad.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }
}