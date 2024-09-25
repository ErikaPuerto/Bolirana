/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avanzada.bolirana.modelo;

import edu.avanzada.bolirana.controlador.JuegoSetup;
import edu.avanzada.bolirana.modelo.Equipo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Clase que gestiona la persistencia de datos del juego de Bolirana.
 * Permite guardar y cargar la configuración del juego, así como registrar los equipos ganadores.
 * Utiliza serialización para escribir y leer objetos de archivos.
 * 
 * @author anaro
 */
public class Persistencia {

    /**
     * Guarda la configuración del juego en un archivo.
     *
     * @param juegoSetup La configuración del juego que será guardada.
     * @throws FileNotFoundException Si no se puede encontrar el archivo.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    public void GuardarJuego(JuegoSetup juegoSetup) throws FileNotFoundException, IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("DatosJuego.dat");
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(juegoSetup);
        }
    }

    /**
     * Carga la configuración del juego desde un archivo.
     *
     * @return Un objeto de tipo JuegoSetup que contiene la configuración del juego.
     * @throws IOException Si ocurre un error al leer el archivo.
     * @throws ClassNotFoundException Si no se puede encontrar la clase durante la deserialización.
     */
    public JuegoSetup SacarJuego() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("DatosJuego.dat");
        JuegoSetup juegoSetupenfichero;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            juegoSetupenfichero = (JuegoSetup) objectInputStream.readObject();
        }
        return juegoSetupenfichero;
    }

    /**
     * Guarda el equipo ganador en un archivo de equipos ganadores.
     *
     * @param equipo El equipo ganador que será guardado.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     * @throws ClassNotFoundException Si no se puede encontrar la clase durante la deserialización.
     */
    public void GuardarEquiposGanadores(Equipo equipo) throws IOException, ClassNotFoundException {
        ArrayList<Equipo> equiposgan = new ArrayList<>();
        if (SacarEquiposGanadores() != null) {
            equiposgan = SacarEquiposGanadores();
        }
        equiposgan.add(equipo);
        FileOutputStream fileOutputStream = new FileOutputStream("EquiposGanadores.dat");
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(equiposgan);
        }
    }

    /**
     * Carga la lista de equipos ganadores desde un archivo.
     *
     * @return Una lista de equipos ganadores.
     * @throws IOException Si ocurre un error al leer el archivo.
     * @throws ClassNotFoundException Si no se puede encontrar la clase durante la deserialización.
     */
    public ArrayList<Equipo> SacarEquiposGanadores() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("EquiposGanadores.dat");
        ArrayList<Equipo> EquiposGan;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            EquiposGan = (ArrayList<Equipo>) objectInputStream.readObject();
        }
        return EquiposGan;
    }
}
