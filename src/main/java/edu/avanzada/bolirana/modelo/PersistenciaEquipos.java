/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avanzada.bolirana.modelo;

/**
 *
 * @author nedic
 */

import edu.avanzada.bolirana.modelo.Equipo;

import java.io.*;
import java.util.List;

public class PersistenciaEquipos {

    private static final String ARCHIVO_EQUIPOS = "equipos.dat";

    // Guardar los equipos en un archivo
    public static void guardarEquipos(List<Equipo> equipos) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_EQUIPOS))) {
            oos.writeObject(equipos);
        }
    }

    // Cargar los equipos desde un archivo
    public static List<Equipo> cargarEquipos() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_EQUIPOS))) {
            return (List<Equipo>) ois.readObject();
        }
    }
}
