package edu.avanzada.bolirana.controlador;

import edu.avanzada.bolirana.modelo.Persistencia;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * Clase principal que lanza la aplicación del juego de Bolirana.
 * Se encarga de cargar los datos previos del juego si existen o de iniciar 
 * una nueva configuración en caso contrario. También ofrece al usuario la opción 
 * de reutilizar los equipos previamente guardados.
 */
public class Launcher {
    public static void main(String[] args) {
        JuegoSetup juegoSetup;
        Persistencia persistencia = new Persistencia();
        File archivoDatos = new File("DatosJuego.dat");

        // Verificar si existe un archivo de datos guardados
        if (archivoDatos.exists()) {
            // Preguntar al usuario si quiere cargar los equipos guardados
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea usar los equipos guardados?", "Cargar Equipos", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {
                // Intentar cargar los equipos previamente guardados
                try {
                    juegoSetup = persistencia.SacarJuego();
                    JOptionPane.showMessageDialog(null, "Juego cargado exitosamente. Puede iniciar el juego con los datos previos.");
                } catch (IOException | ClassNotFoundException e) {
                    // Mostrar un mensaje de error si ocurre algún problema al cargar los datos
                    JOptionPane.showMessageDialog(null, "Error al cargar el juego. Ingrese los datos manualmente.", "Error", JOptionPane.ERROR_MESSAGE);
                    juegoSetup = new JuegoSetup(); // Iniciar configuración manual si falla la carga
                }
            } else {
                // Si se elige no usar los equipos guardados, eliminar el archivo y empezar desde cero
                archivoDatos.delete();
                JOptionPane.showMessageDialog(null, "Equipos anteriores eliminados. Ingrese los datos de los nuevos equipos.");
                juegoSetup = new JuegoSetup(); // Iniciar configuración manual
            }
        } else {
            // Si no existe un archivo de datos guardados, iniciar configuración manual
            JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración. Ingrese los datos manualmente.", "Aviso", JOptionPane.WARNING_MESSAGE);
            juegoSetup = new JuegoSetup(); // Iniciar configuración manual
        }

        // Crear el controlador principal del juego con la configuración actual
        ControladorJuego controladorJuego = new ControladorJuego(juegoSetup, persistencia);
    }
}