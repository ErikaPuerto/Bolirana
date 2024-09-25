package edu.avanzada.bolirana.vista;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que representa la ventana principal del juego de Bolirana, donde se muestra
 * el progreso del juego y las puntuaciones de los equipos.
 */
public class JuegoFrame extends JFrame {

    private JTextArea areaTexto; // Área de texto para mostrar el progreso del juego
    private Map<String, JLabel> etiquetasPuntaje; // Mapa para asociar cada equipo con su etiqueta de puntuación

    /**
     * Constructor para inicializar la ventana del juego con los nombres de los equipos.
     *
     * @param nombresEquipos Lista con los nombres de los equipos que participan en el juego.
     */
    public JuegoFrame(List<String> nombresEquipos) {
        // Configuración básica de la ventana
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null); // Centrar la ventana
        setTitle("Juego de Bolirana"); // Título de la ventana
        setSize(600, 400); // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana
        setLayout(new BorderLayout()); // Usar BorderLayout para la disposición de los componentes

        // Crear el área de texto donde se mostrará el progreso del juego
        areaTexto = new JTextArea();
        areaTexto.setEditable(false); // El área de texto no será editable por el usuario
        JScrollPane scrollPane = new JScrollPane(areaTexto); // Añadir el área de texto dentro de un panel desplazable
        add(scrollPane, BorderLayout.CENTER); // Colocar el área de texto en el centro de la ventana

        // Crear un panel para mostrar las puntuaciones de los equipos
        JPanel panelPuntajes = new JPanel();
        panelPuntajes.setLayout(new GridLayout(nombresEquipos.size(), 1)); // Crear una cuadrícula con filas según el número de equipos
        etiquetasPuntaje = new HashMap<>(); // Inicializar el mapa que almacenará las etiquetas de puntuación

        // Crear una etiqueta para cada equipo y agregarla al panel de puntuaciones
        for (String equipo : nombresEquipos) {
            JLabel etiquetaPuntaje = new JLabel(equipo + ": 0 puntos"); // Inicializar con 0 puntos
            etiquetasPuntaje.put(equipo, etiquetaPuntaje); // Guardar la etiqueta en el mapa
            panelPuntajes.add(etiquetaPuntaje); // Agregar la etiqueta al panel
        }

        add(panelPuntajes, BorderLayout.EAST); // Colocar el panel de puntuaciones en el lado derecho de la ventana
    }

    /**
     * Agrega un mensaje de texto al área que muestra el progreso del juego.
     *
     * @param texto El texto que se agregará al área de progreso.
     */
    public void agregarTurno(String texto) {
        areaTexto.append(texto + "\n"); // Añadir el texto seguido de un salto de línea
    }

    /**
     * Actualiza la puntuación de un equipo en la interfaz de usuario.
     *
     * @param nombreEquipo Nombre del equipo cuya puntuación se va a actualizar.
     * @param puntaje El nuevo puntaje del equipo.
     */
    public void actualizarPuntaje(String nombreEquipo, int puntaje) {
        JLabel etiquetaPuntaje = etiquetasPuntaje.get(nombreEquipo); // Obtener la etiqueta de puntuación del equipo
        if (etiquetaPuntaje != null) { // Verificar que el equipo exista
            etiquetaPuntaje.setText(nombreEquipo + ": " + puntaje + " puntos"); // Actualizar el texto con el nuevo puntaje
        }
    }
}