/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bolirana.vista;

import javax.swing.*;
import java.awt.*;

/**
 * Panel para ingresar datos de un jugador.
 */
public class JugadorPanel extends JPanel {
    private JTextField txtNombre;
    private JTextField txtCedula;
    private JTextField txtPosicion;
    
    public JugadorPanel() {
        setLayout(new GridLayout(3, 2));
        
        // Nombre
        add(new JLabel("Nombre:"));
        txtNombre = new JTextField(15);
        add(txtNombre);
        
        // Cédula
        add(new JLabel("Cédula:"));
        txtCedula = new JTextField(15);
        add(txtCedula);
        
        // Posición
        add(new JLabel("Posición:"));
        txtPosicion = new JTextField(15);
        add(txtPosicion);
    }

    public String getNombre() {
        return txtNombre.getText();
    }

    public String getCedula() {
        return txtCedula.getText();
    }

    public String getPosicion() {
        return txtPosicion.getText();
    }
}
