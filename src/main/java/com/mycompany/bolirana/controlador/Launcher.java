/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bolirana.controlador;

import com.mycompany.bolirana.vista.MainFrame;

/**
 *
 * @author nedic
 */
public class Launcher {
    public static void main(String[] args) {
        // Crear la ventana principal
        MainFrame mainFrame = new MainFrame();
        
        // Crear el objeto JuegoSetup (para manejar la configuración del juego)
        JuegoSetup juegoSetup = new JuegoSetup();
        
        // Crear el controlador que conectará la vista y el modelo
        ControladorJuego controlador = new ControladorJuego(mainFrame, juegoSetup);
        
        // Hacer visible la ventana principal
        mainFrame.setVisible(true);
    }
}

