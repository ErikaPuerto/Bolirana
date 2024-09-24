package edu.avanzada.bolirana.vista;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana para configurar los equipos.
 */
public class EquipoFrame extends JFrame {
    private JTextField nombreEquipo;
    private JButton btnAgregarEquipo;

    public EquipoFrame() {
        setTitle("Configuración del Equipo");
        setSize(400, 200);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nombre del equipo
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nombre del Equipo:"), gbc);
        gbc.gridx = 1;
        nombreEquipo = new JTextField(20);
        add(nombreEquipo, gbc);

        // Botón para agregar equipo
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        btnAgregarEquipo = new JButton("Agregar Equipo");
        add(btnAgregarEquipo, gbc);
    }

    public String getNombreEquipo() {
        return nombreEquipo.getText();
    }
     
    public JButton getBtnAgregarEquipo() {
        return btnAgregarEquipo;
    }
}
