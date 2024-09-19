/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bolirana.controlador;

import com.mycompany.bolirana.modelo.Bolirana;
import com.mycompany.bolirana.vista.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador que maneja la lógica del juego entre el modelo y la vista.
 */
public class ControladorJuego {
    private MainFrame mainFrame;
    private JuegoSetup juegoSetup;
    //private Bolirana bolirana;
    
    public ControladorJuego(MainFrame mainFrame, JuegoSetup juegoSetup) {
        this.mainFrame = mainFrame;
        this.juegoSetup = juegoSetup;
        
        this.mainFrame.addIniciarJuegoListener(new IniciarJuegoListener(juegoSetup));
    }
    
}

