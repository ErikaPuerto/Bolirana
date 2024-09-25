package edu.avanzada.bolirana.vista;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa la ventana principal del juego de Bolirana.
 * Permite al usuario configurar el juego o iniciar un nuevo juego.
 */
public final class MainFrame extends JFrame {

    private JButton btnIniciarJuego; // Botón para iniciar el juego
    private JButton btnConfigurarJuego; // Botón para configurar el juego

    /**
     * Constructor que inicializa y muestra la ventana principal del juego.
     * Configura la disposición de los componentes gráficos y muestra los botones.
     */
    public MainFrame() {
        CrearMainFrame(); // Método para crear la interfaz de usuario
        setVisible(true); // Hacer visible la ventana
        setResizable(false); // No permitir cambiar el tamaño de la ventana
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    /**
     * Método para configurar la interfaz gráfica de la ventana principal.
     * Establece el diseño de los botones y sus propiedades.
     */
    public void CrearMainFrame() {

        // Configuración básica de la ventana
        setTitle("Juego de Bolirana");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar completamente el programa al cerrar la ventana

        // Usar GridBagLayout para organizar los componentes
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbc.gridx = 0; // Columna 0
        gbc.gridy = 0; // Fila 0
        gbc.anchor = GridBagConstraints.CENTER; // Centrar los componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Expandir los componentes horizontalmente

        // Crear botones para iniciar y configurar el juego
        btnIniciarJuego = new JButton("Iniciar Juego");
        btnConfigurarJuego = new JButton("Configurar Juego");

        // Establecer el tamaño preferido de los botones
        Dimension buttonSize = new Dimension(200, 50);
        btnIniciarJuego.setPreferredSize(buttonSize);
        btnConfigurarJuego.setPreferredSize(buttonSize);

        // Establecer el color de fondo de los botones
        Color verdeSutil = Color.decode("#98D281"); // Color verde claro
        btnIniciarJuego.setBackground(verdeSutil);
        btnConfigurarJuego.setBackground(verdeSutil);

        // Agregar los botones a la ventana
        add(btnConfigurarJuego, gbc); // Agregar el botón de configurar juego
        gbc.gridy++; // Mover a la siguiente fila
        add(btnIniciarJuego, gbc); // Agregar el botón de iniciar juego
    }

    /**
     * Devuelve el botón para iniciar el juego.
     *
     * @return Botón de inicio de juego.
     */
    public JButton getBtnIniciarJuego() {
        return btnIniciarJuego;
    }

    /**
     * Devuelve el botón para configurar el juego.
     *
     * @return Botón de configuración del juego.
     */
    public JButton getBtnConfigurarJuego() {
        return btnConfigurarJuego;
    }

    /**
     * Agrega un ActionListener al botón de configuración del juego.
     * 
     * @param listener El ActionListener que manejará el evento.
     */
    public void addIniciarConfiguracionListener(java.awt.event.ActionListener listener) {
        btnConfigurarJuego.addActionListener(listener);
    }

    /**
     * Agrega un ActionListener al botón de inicio del juego.
     * 
     * @param listener El ActionListener que manejará el evento.
     */
    public void addIniciarJuegoListener(java.awt.event.ActionListener listener) {
        btnIniciarJuego.addActionListener(listener);
    }
}