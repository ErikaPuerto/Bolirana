/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bolirana.vista;

import com.mycompany.bolirana.controlador.JuegoSetup;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Ventana principal que contiene todos los elementos de la interfaz.
 */
public class MainFrame extends JFrame {
    private JButton btnConfigurarJuego;
    private JButton btnIniciarJuego;
    private JPanel mainPanel;
    private JuegoSetup juegoSetup;
    
    public MainFrame() {
        setTitle("Juego de Bolirana");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        //
        juegoSetup = new JuegoSetup();
        
        // Botones
        btnConfigurarJuego = new JButton("Configurar Juego");
        btnIniciarJuego = new JButton("Iniciar Juego");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnConfigurarJuego);
        buttonPanel.add(btnIniciarJuego);
        
        // Agregar paneles
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Agregar panel principal a la ventana
        add(mainPanel);// Configurar listeners
        
        
        addConfigurarJuegoListener(e -> {
            juegoSetup.configurarJuego();
            JOptionPane.showMessageDialog(this, "Juego configurado correctamente.");
        });

        addIniciarJuegoListener(e -> {
            juegoSetup.iniciarJuego();
        });
    }
    
    public void addConfigurarJuegoListener(ActionListener listener) {
        btnConfigurarJuego.addActionListener(listener);
    }
    
    public void addIniciarJuegoListener(ActionListener listener) {
        btnIniciarJuego.addActionListener(listener);
    }
    
    public JuegoSetup getJuegoSetup() {
        return juegoSetup;
    }
}
