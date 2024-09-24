package edu.avanzada.bolirana.vista;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana para registrar jugadores.
 */
public class JugadorFrame extends JFrame {
    private JTextField txtNombreJugador;
    private JTextField txtCedulaJugador;
    private JTextField txtEdadJugador;
    private JTextField txtNumeroJugador;
    private JButton btnAgregarJugador;

    public JugadorFrame() {
        setTitle("Registrar Jugador");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo para el nombre del jugador
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nombre:"), gbc);
        txtNombreJugador = new JTextField(20);
        gbc.gridx = 1;
        add(txtNombreJugador, gbc);

        // Campo para la cédula del jugador
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Cédula:"), gbc);
        txtCedulaJugador = new JTextField(20);
        gbc.gridx = 1;
        add(txtCedulaJugador, gbc);

        // Campo para la edad del jugador
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Edad:"), gbc);
        txtEdadJugador = new JTextField(20);
        gbc.gridx = 1;
        add(txtEdadJugador, gbc);

        // Campo para el número del jugador
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Número del Jugador:"), gbc);
        txtNumeroJugador = new JTextField(20);
        gbc.gridx = 1;
        add(txtNumeroJugador, gbc);

        // Botón para agregar el jugador
        btnAgregarJugador = new JButton("Agregar Jugador");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(btnAgregarJugador, gbc);
    }

    public String getNombreJugador() {
        return txtNombreJugador.getText();
    }

    public String getCedulaJugador() {
        return txtCedulaJugador.getText();
    }

    public int getEdadJugador() {
        try {
            return Integer.parseInt(txtEdadJugador.getText());
        } catch (NumberFormatException e) {
            return -1; // Valor de error
        }
    }

    public int getNumeroJugador() {
        try {
            return Integer.parseInt(txtNumeroJugador.getText());
        } catch (NumberFormatException e) {
            return -1; // Valor de error si no es un número válido
        }
    }

    public JButton getBtnAgregarJugador() {
        return btnAgregarJugador;
    }
}


