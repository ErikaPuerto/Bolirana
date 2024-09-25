package edu.avanzada.bolirana.vista;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana para registrar los datos de un jugador en el juego de Bolirana.
 */
public class JugadorFrame extends JFrame {
    private JTextField txtNombreJugador;  // Campo de texto para el nombre del jugador
    private JTextField txtCedulaJugador;  // Campo de texto para la cédula del jugador
    private JTextField txtEdadJugador;    // Campo de texto para la edad del jugador
    private JTextField txtNumeroJugador;  // Campo de texto para el número del jugador
    private JButton btnAgregarJugador;    // Botón para agregar al jugador

    /**
     * Constructor de JugadorFrame que inicializa la ventana para ingresar los datos del jugador.
     * Configura la interfaz de usuario con campos de texto para el nombre, cédula, edad y número del jugador.
     */
    public JugadorFrame() {
        // Configuración básica de la ventana
        setResizable(false);
        setLocationRelativeTo(null);  // Centrar la ventana en la pantalla
        setTitle("Registrar Jugador"); // Establecer el título de la ventana
        setSize(400, 300); // Definir el tamaño de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar la ventana sin afectar el resto del programa
        setLayout(new GridBagLayout()); // Usar GridBagLayout para organizar los componentes
        GridBagConstraints gbc = new GridBagConstraints(); // Para controlar la disposición de los componentes
        gbc.insets = new Insets(10, 10, 10, 10); // Añadir márgenes entre los componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Los componentes se expandirán horizontalmente

        // Campo para el nombre del jugador
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nombre:"), gbc); // Etiqueta para el nombre del jugador
        txtNombreJugador = new JTextField(20); // Campo de texto para el nombre del jugador
        gbc.gridx = 1;
        add(txtNombreJugador, gbc);

        // Campo para la cédula del jugador
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Cédula:"), gbc); // Etiqueta para la cédula del jugador
        txtCedulaJugador = new JTextField(20); // Campo de texto para la cédula del jugador
        gbc.gridx = 1;
        add(txtCedulaJugador, gbc);

        // Campo para la edad del jugador
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Edad:"), gbc); // Etiqueta para la edad del jugador
        txtEdadJugador = new JTextField(20); // Campo de texto para la edad del jugador
        gbc.gridx = 1;
        add(txtEdadJugador, gbc);

        // Campo para el número del jugador
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Número del Jugador:"), gbc); // Etiqueta para el número del jugador
        txtNumeroJugador = new JTextField(20); // Campo de texto para el número del jugador
        gbc.gridx = 1;
        add(txtNumeroJugador, gbc);

        // Botón para agregar el jugador
        btnAgregarJugador = new JButton("Agregar Jugador"); // Botón para confirmar el registro del jugador
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(btnAgregarJugador, gbc);
    }

    /**
     * Obtiene el nombre ingresado del jugador.
     *
     * @return El nombre del jugador como texto.
     */
    public String getNombreJugador() {
        return txtNombreJugador.getText();
    }

    /**
     * Obtiene la cédula ingresada del jugador.
     *
     * @return La cédula del jugador como texto.
     */
    public String getCedulaJugador() {
        return txtCedulaJugador.getText();
    }

    /**
     * Obtiene la edad ingresada del jugador.
     * Si el valor ingresado no es numérico, retorna -1 como indicador de error.
     *
     * @return La edad del jugador como entero, o -1 si no es válida.
     */
    public int getEdadJugador() {
        try {
            return Integer.parseInt(txtEdadJugador.getText());
        } catch (NumberFormatException e) {
            return -1; // Valor de error si no se ingresa un número válido
        }
    }

    /**
     * Obtiene el número ingresado del jugador.
     * Si el valor ingresado no es numérico, retorna -1 como indicador de error.
     *
     * @return El número del jugador como entero, o -1 si no es válido.
     */
    public int getNumeroJugador() {
        try {
            return Integer.parseInt(txtNumeroJugador.getText());
        } catch (NumberFormatException e) {
            return -1; // Valor de error si no se ingresa un número válido
        }
    }

    /**
     * Obtiene el botón para agregar al jugador.
     *
     * @return El botón para agregar al jugador.
     */
    public JButton getBtnAgregarJugador() {
        return btnAgregarJugador;
    }
}