package edu.avanzada.bolirana.persistencia;

import edu.avanzada.bolirana.modelo.Equipo;
import edu.avanzada.bolirana.controlador.JuegoSetup;

import java.io.*;

/**
 * Clase que maneja la persistencia de datos para guardar y cargar el estado del juego.
 */
public class PersistenciaJuego {

    /**
     * Guarda el estado del juego en un archivo llamado "juego.dat".
     *
     * @param juegoSetup El objeto que contiene la configuración del juego.
     * @throws IOException Si ocurre un error al guardar el archivo.
     */
    public static void guardarJuego(JuegoSetup juegoSetup) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("juego.dat"))) {
            oos.writeObject(juegoSetup);
        }
    }

    /**
     * Carga el estado del juego desde el archivo "juego.dat".
     *
     * @return El objeto JuegoSetup con la configuración del juego cargada.
     * @throws IOException            Si ocurre un error al leer el archivo.
     * @throws ClassNotFoundException Si la clase no puede ser encontrada.
     */
    public static JuegoSetup cargarJuego() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("juego.dat"))) {
            return (JuegoSetup) ois.readObject();
        }
    }

    /**
     * Guarda los datos del equipo ganador en un archivo "ganador.dat".
     *
     * @param equipo El equipo ganador.
     * @throws IOException Si ocurre un error al guardar el archivo.
     */
    public static void guardarEquipoGanador(Equipo equipo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ganador.dat"))) {
            oos.writeObject(equipo);
        }
    }
}

