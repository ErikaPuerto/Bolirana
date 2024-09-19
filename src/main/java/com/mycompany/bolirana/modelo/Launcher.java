/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bolirana.modelo;

/**
 *
 * @author nedic
 */
public class Launcher {
    public static void main(String[] args) {
        // Configuración e inicio del juego
        JuegoSetup juegoSetup = new JuegoSetup();
        juegoSetup.configurarJuego();
        juegoSetup.iniciarJuego();
    }
}

