/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bolirana.modelo;

/**
 *
 * @author nedic
 */

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
    * Getter para nombre
    * @return nombre
    */
    public String getNombre(){
        return nombre;    
    }
}

