/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bolirana.vista;

import javax.swing.*;
import java.awt.*;

/**
 * Panel para ingresar datos de los equipos.
 */
public class EquipoPanel extends JPanel {
    private JTextField txtNombreEquipo;
    private JButton btnAgregarJugador;
    private JPanel jugadoresPanel;
    
    public EquipoPanel() {
        setLayout(new BorderLayout());
        
        // Nombre del equipo
        JPanel nombrePanel = new JPanel();
        nombrePanel.add(new JLabel("Nombre del equipo:"));
        txtNombreEquipo = new JTextField(20);
        nombrePanel.add(txtNombreEquipo);
        
        // Panel de jugadores
        jugadoresPanel = new JPanel();
        jugadoresPanel.setLayout(new BoxLayout(jugadoresPanel, BoxLayout.Y_AXIS));
        btnAgregarJugador = new JButton("Agregar Jugador");
        
        // Agregar componentes
        add(nombrePanel, BorderLayout.NORTH);
        add(jugadoresPanel, BorderLayout.CENTER);
        add(btnAgregarJugador, BorderLayout.SOUTH);
    }

    public String getNombreEquipo() {
        return txtNombreEquipo.getText();
    }

    public void addJugadorPanel(JPanel jugadorPanel) {
        jugadoresPanel.add(jugadorPanel);
        revalidate();
        repaint();
    }

    public JButton getBtnAgregarJugador() {
        return btnAgregarJugador;
    }
}
