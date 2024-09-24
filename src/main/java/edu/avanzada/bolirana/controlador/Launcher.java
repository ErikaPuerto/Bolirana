package edu.avanzada.bolirana.controlador;

import edu.avanzada.bolirana.vista.MainFrame;
import edu.avanzada.bolirana.persistencia.PersistenciaJuego;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * Clase principal para iniciar el programa.
 */
public class Launcher {
    public static void main(String[] args) {
        JuegoSetup juegoSetup;
        File archivoDatos = new File("juego.dat");

        if (archivoDatos.exists()) {
            // Intentar cargar la configuración si el archivo existe
            try {
                juegoSetup = PersistenciaJuego.cargarJuego();
                JOptionPane.showMessageDialog(null, "Juego cargado exitosamente. Puede iniciar el juego con los datos previos.");
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Error al cargar el juego. Ingrese los datos manualmente.", "Error", JOptionPane.ERROR_MESSAGE);
                juegoSetup = new JuegoSetup(); // Iniciar configuración manual
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración. Ingrese los datos manualmente.", "Aviso", JOptionPane.WARNING_MESSAGE);
            juegoSetup = new JuegoSetup(); // Iniciar configuración manual
        }

        // Crear la ventana principal y pasarle la configuración del juego
        MainFrame mainFrame = new MainFrame();
        ControladorJuego controladorJuego = new ControladorJuego(mainFrame, juegoSetup);
        mainFrame.setVisible(true);
    }
}
