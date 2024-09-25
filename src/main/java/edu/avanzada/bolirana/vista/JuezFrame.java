package edu.avanzada.bolirana.vista;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana que permite configurar los datos del juez en el juego de Bolirana.
 */
public class JuezFrame extends JFrame {
    private JTextField nombreJuez, cedulaJuez, edadJuez, tarjetaProfesionalJuez; // Campos de texto para ingresar los datos del juez
    private JButton btnGuardarJuez; // Botón para guardar la configuración del juez

    /**
     * Constructor para inicializar la ventana de configuración del juez.
     * Configura la interfaz de usuario con campos para el nombre, cédula, edad y tarjeta profesional del juez.
     */
    public JuezFrame() {
        // Configuración básica de la ventana
        setResizable(false);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setTitle("Configuración del Juez"); // Establecer el título de la ventana
        setSize(400, 300); // Definir el tamaño de la ventana
        setLayout(new GridBagLayout()); // Usar GridBagLayout para el diseño de los componentes

        GridBagConstraints gbc = new GridBagConstraints(); // Usado para controlar la disposición de los componentes
        gbc.insets = new Insets(5, 5, 5, 5); // Añadir márgenes entre los componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Los componentes se expandirán horizontalmente para llenar el espacio

        // Campo para el nombre del juez
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nombre del Juez:"), gbc); // Etiqueta para el nombre del juez
        gbc.gridx = 1;
        nombreJuez = new JTextField(20); // Campo de texto para el nombre del juez
        add(nombreJuez, gbc);

        // Campo para la cédula del juez
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Cédula del Juez:"), gbc); // Etiqueta para la cédula del juez
        gbc.gridx = 1;
        cedulaJuez = new JTextField(20); // Campo de texto para la cédula del juez
        add(cedulaJuez, gbc);

        // Campo para la edad del juez
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Edad del Juez:"), gbc); // Etiqueta para la edad del juez
        gbc.gridx = 1;
        edadJuez = new JTextField(20); // Campo de texto para la edad del juez
        add(edadJuez, gbc);

        // Campo para la tarjeta profesional del juez
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Tarjeta Profesional:"), gbc); // Etiqueta para la tarjeta profesional
        gbc.gridx = 1;
        tarjetaProfesionalJuez = new JTextField(20); // Campo de texto para la tarjeta profesional del juez
        add(tarjetaProfesionalJuez, gbc);

        // Botón para guardar los datos del juez
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        btnGuardarJuez = new JButton("Guardar Juez"); // Botón para guardar la configuración del juez
        add(btnGuardarJuez, gbc);
    }

    /**
     * Método para obtener el nombre ingresado del juez.
     *
     * @return El nombre del juez como texto.
     */
    public String getNombreJuez() {
        return nombreJuez.getText();
    }

    /**
     * Método para obtener la cédula ingresada del juez.
     *
     * @return La cédula del juez como texto.
     */
    public String getCedulaJuez() {
        return cedulaJuez.getText();
    }

    /**
     * Método para obtener la edad ingresada del juez.
     * Si el valor no es numérico, retorna -1 como indicador de error.
     *
     * @return La edad del juez como entero o -1 si no es un valor válido.
     */
    public int getEdadJuez() {
        try {
            return Integer.parseInt(edadJuez.getText());
        } catch (NumberFormatException e) {
            return -1; // Indicador de error si no se ingresa un número válido
        }
    }

    /**
     * Método para obtener la tarjeta profesional ingresada del juez.
     *
     * @return La tarjeta profesional del juez como texto.
     */
    public String getTarjetaProfesionalJuez() {
        return tarjetaProfesionalJuez.getText();
    }

    /**
     * Método para obtener el botón de guardar juez.
     *
     * @return El botón de guardar juez.
     */
    public JButton getBtnGuardarJuez() {
        return btnGuardarJuez;
    }
}