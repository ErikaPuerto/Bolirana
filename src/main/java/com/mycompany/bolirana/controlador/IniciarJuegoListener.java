/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bolirana.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author nedic
 */
class IniciarJuegoListener implements ActionListener {
    private JuegoSetup juegoSetup;
        public void actionPerformed(ActionEvent e) {
            juegoSetup.iniciarJuego();
            juegoSetup.configurarJuego();
        }
        // Constructor que recibe JuegoSetup
    public IniciarJuegoListener(JuegoSetup juegoSetup) {
        this.juegoSetup = juegoSetup;
    }
}
