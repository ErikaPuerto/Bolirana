package edu.avanzada.bolirana.vista;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana para configurar los equipos en el juego de Bolirana.
 * Esta ventana permite al usuario ingresar el nombre de un equipo y agregarlo.
 */
public class EquipoFrame extends JFrame {
    private JTextField nombreEquipo;
    private JButton btnAgregarEquipo;

    /**
     * Constructor para inicializar la ventana de configuración de equipos.
     * Establece el tamaño, el diseño y los componentes visuales de la ventana.
     */
    public EquipoFrame() {
        // Configuración básica de la ventana
        setResizable(false);
        setLocationRelativeTo(null);  // Centrar la ventana
        setTitle("Configuración del Equipo");  // Establecer el título de la ventana
        setSize(400, 200);  // Establecer el tamaño de la ventana
        setLayout(new GridBagLayout());  // Usar un diseño GridBagLayout para la disposición de los componentes

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Establecer márgenes entre los componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Los componentes ocupan todo el ancho de la celda

        // Configuración del campo de texto para el nombre del equipo
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nombre del Equipo:"), gbc);  // Etiqueta para el nombre del equipo
        gbc.gridx = 1;
        nombreEquipo = new JTextField(20);  // Campo de texto para ingresar el nombre del equipo
        add(nombreEquipo, gbc);

        // Configuración del botón para agregar equipo
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;  // El botón ocupará dos columnas
        btnAgregarEquipo = new JButton("Agregar Equipo");  // Botón para agregar el equipo
        add(btnAgregarEquipo, gbc);
    }

    /**
     * Método para obtener el nombre del equipo ingresado.
     *
     * @return El texto ingresado en el campo de nombre del equipo.
     */
    public String getNombreEquipo() {
        return nombreEquipo.getText();
    }

    /**
     * Método para obtener el botón "Agregar Equipo".
     *
     * @return El botón para agregar el equipo.
     */
    public JButton getBtnAgregarEquipo() {
        return btnAgregarEquipo;
    }
}