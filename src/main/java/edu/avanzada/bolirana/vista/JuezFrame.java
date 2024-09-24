package edu.avanzada.bolirana.vista;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana para configurar el juez.
 */
public class JuezFrame extends JFrame {
    private JTextField nombreJuez, cedulaJuez, edadJuez, tarjetaProfesionalJuez;
    private JButton btnGuardarJuez;

    public JuezFrame() {
        setTitle("Configuración del Juez");
        setSize(400, 300);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nombre del juez
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nombre del Juez:"), gbc);
        gbc.gridx = 1;
        nombreJuez = new JTextField(20);
        add(nombreJuez, gbc);

        // Cédula del juez
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Cédula del Juez:"), gbc);
        gbc.gridx = 1;
        cedulaJuez = new JTextField(20);
        add(cedulaJuez, gbc);

        // Edad del juez
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Edad del Juez:"), gbc);
        gbc.gridx = 1;
        edadJuez = new JTextField(20);
        add(edadJuez, gbc);

        // Tarjeta profesional del juez
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Tarjeta Profesional:"), gbc);
        gbc.gridx = 1;
        tarjetaProfesionalJuez = new JTextField(20);
        add(tarjetaProfesionalJuez, gbc);

        // Botón para guardar
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        btnGuardarJuez = new JButton("Guardar Juez");
        add(btnGuardarJuez, gbc);
    }

    public String getNombreJuez() {
        return nombreJuez.getText();
    }

    public String getCedulaJuez() {
        return cedulaJuez.getText();
    }

    public int getEdadJuez() {
        try {
            return Integer.parseInt(edadJuez.getText());
        } catch (NumberFormatException e) {
            return -1; // Indicador de error
        }
    }

    public String getTarjetaProfesionalJuez() {
        return tarjetaProfesionalJuez.getText();
    }

    public JButton getBtnGuardarJuez() {
        return btnGuardarJuez;
    }
}

