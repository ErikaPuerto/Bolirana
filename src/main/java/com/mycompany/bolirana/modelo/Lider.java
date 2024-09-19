/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bolirana.modelo;

/**
 * Clase que representa al líder de un equipo en el juego de Bolirana.
 * Hereda de la clase Persona e incluye los años de experiencia.
 */
class Lider extends Persona {
    private int añosExperiencia;

    /**
     * Constructor para crear un líder.
     *
     * @param nombre El nombre del líder.
     * @param cedula La cédula del líder.
     * @param edad La edad del líder.
     * @param aniosExperiencia Los años de experiencia del líder.
     */
    public Lider(String nombre, String cedula, int edad, int añosExperiencia) {
        super(nombre, cedula, edad);
        this.añosExperiencia = añosExperiencia;
    }

    /**
    * Método get para años de experiencia 
    */
    public int getAñosExperiencia() {
        return añosExperiencia;
    }
}
