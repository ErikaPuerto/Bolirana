package edu.avanzada.bolirana.modelo;

import java.io.Serializable;

public class Jugador implements Serializable {
    private String nombre;
    private String cedula;
    private int edad;
    private int numero;

    public Jugador(String nombre, String cedula, int edad, int numero) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public int getEdad() {
        return edad;
    }

    public int getNumero() {
        return numero;
    }
}
