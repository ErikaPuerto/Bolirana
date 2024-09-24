package edu.avanzada.bolirana.modelo;

import java.io.Serializable;

public class Juez extends Persona implements Serializable {
    private String tarjetaProfesional;

    public Juez(String nombre, String cedula, int edad, String tarjetaProfesional) {
        super(nombre, cedula, edad);
        this.tarjetaProfesional = tarjetaProfesional;
    }

    public String getTarjetaProfesional() {
        return tarjetaProfesional;
    }
}
