/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avanzada.bolirana.modelo;

import javax.swing.JOptionPane;

/**
 * Clase que representa al líder de un equipo en el juego de Bolirana.
 * Hereda de la clase Persona e incluye los años de experiencia.
 */
public class Lider extends Persona {
    private int añosExperiencia;

    /**
     * Constructor para crear un líder.
     *
     * @param nombre El nombre del líder.
     * @param cedula La cédula del líder.
     * @param edad La edad del líder.
     * @param añosExperiencia Los años de experiencia del líder.
     */
    public Lider(String nombre, String cedula, int edad, int añosExperiencia) {
        super(nombre, cedula, edad);
        setAñosExperiencia(añosExperiencia); // Valida y asigna la experiencia
    }

    /**
     * Método para obtener los años de experiencia del líder.
     * @return los años de experiencia.
     */
    public int getAñosExperiencia() {
        return añosExperiencia;
    }

    /**
     * Método para asignar los años de experiencia.
     * Se asegura de que la experiencia sea menor que la edad.
     * Si no lo es, pide al usuario que ingrese un valor válido.
     */
    public void setAñosExperiencia(int añosExperiencia) {
        // Variable temporal para almacenar años válidos
        int experienciaValida = añosExperiencia;

        // Validar la experiencia
        while (experienciaValida >= getEdad()) {
            // Mostrar mensaje de error y volver a pedir la experiencia
            String mensaje = "Los años de experiencia no pueden ser mayores o iguales a la edad del líder (" + getEdad() + ").\n"
                           + "Por favor, ingrese un valor de años de experiencia válido.";
            JOptionPane.showMessageDialog(null, mensaje, "Error en la experiencia", JOptionPane.ERROR_MESSAGE);

            // Pedir un nuevo valor de años de experiencia
            String nuevaExperiencia = JOptionPane.showInputDialog("Ingrese los años de experiencia del líder:");
            try {
                experienciaValida = Integer.parseInt(nuevaExperiencia);
            } catch (NumberFormatException e) {
                // Si no es un número válido, forzar la repetición del bucle
                experienciaValida = getEdad();  // Forzar el bucle a repetirse
            }
        }

        // Asignar solo el valor válido
        this.añosExperiencia = experienciaValida;
    }
}