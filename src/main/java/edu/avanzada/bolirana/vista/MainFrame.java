package edu.avanzada.bolirana.vista;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JButton btnIniciarJuego;
    private JButton btnConfigurarJuego;

    public MainFrame() {
        setTitle("Juego de Bolirana");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        btnIniciarJuego = new JButton("Iniciar Juego");
        btnConfigurarJuego = new JButton("Configurar Juego");

        Dimension buttonSize = new Dimension(200, 50);
        btnIniciarJuego.setPreferredSize(buttonSize);
        btnConfigurarJuego.setPreferredSize(buttonSize);

        Color verdeSutil = Color.decode("#98D281");
        btnIniciarJuego.setBackground(verdeSutil);
        btnConfigurarJuego.setBackground(verdeSutil);

        add(btnConfigurarJuego, gbc);
        gbc.gridy++;
        add(btnIniciarJuego, gbc);
    }

    public JButton getBtnIniciarJuego() {
        return btnIniciarJuego;
    }

    public JButton getBtnConfigurarJuego() {
        return btnConfigurarJuego;
    }

    public void addIniciarConfiguracionListener(java.awt.event.ActionListener listener) {
        btnConfigurarJuego.addActionListener(listener);
    }

    public void addIniciarJuegoListener(java.awt.event.ActionListener listener) {
        btnIniciarJuego.addActionListener(listener);
    }
}
