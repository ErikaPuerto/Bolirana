package edu.avanzada.bolirana.vista;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que representa la ventana del juego, donde se muestra el progreso del juego y las puntuaciones.
 */
public class JuegoFrame extends JFrame {
    private JTextArea areaTexto; // Para mostrar el progreso del juego
    private Map<String, JLabel> etiquetasPuntaje; // Para mostrar la puntuación de cada equipo

    /**
     * Constructor de JuegoFrame que recibe una lista de nombres de equipos.
     *
     * @param nombresEquipos Lista con los nombres de los equipos.
     */
    public JuegoFrame(List<String> nombresEquipos) {
        setTitle("Juego de Bolirana");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Área de texto para mostrar los mensajes del juego
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        add(scrollPane, BorderLayout.CENTER);

        // Panel para mostrar las puntuaciones de los equipos
        JPanel panelPuntajes = new JPanel();
        panelPuntajes.setLayout(new GridLayout(nombresEquipos.size(), 1));
        etiquetasPuntaje = new HashMap<>();

        // Crear una etiqueta para cada equipo y agregarla al panel
        for (String equipo : nombresEquipos) {
            JLabel etiquetaPuntaje = new JLabel(equipo + ": 0 puntos");
            etiquetasPuntaje.put(equipo, etiquetaPuntaje);
            panelPuntajes.add(etiquetaPuntaje);
        }

        add(panelPuntajes, BorderLayout.EAST);
    }

    /**
     * Método para agregar un turno al área de texto.
     *
     * @param texto El texto a agregar.
     */
    public void agregarTurno(String texto) {
        areaTexto.append(texto + "\n");
    }

    /**
     * Método para actualizar el puntaje de un equipo en la interfaz.
     *
     * @param nombreEquipo Nombre del equipo.
     * @param puntaje Nuevo puntaje del equipo.
     */
    public void actualizarPuntaje(String nombreEquipo, int puntaje) {
        JLabel etiquetaPuntaje = etiquetasPuntaje.get(nombreEquipo);
        if (etiquetaPuntaje != null) {
            etiquetaPuntaje.setText(nombreEquipo + ": " + puntaje + " puntos");
        }
    }
}
