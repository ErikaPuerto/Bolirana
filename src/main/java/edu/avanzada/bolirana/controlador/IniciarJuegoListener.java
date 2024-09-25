package edu.avanzada.bolirana.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Listener para manejar el evento de inicio del juego en la interfaz gráfica.
 * Esta clase se encarga de verificar si los requisitos mínimos están cumplidos
 * (juez configurado y al menos un equipo) antes de iniciar el juego.
 */
class IniciarJuegoListener implements ActionListener {
    private JuegoSetup juegoSetup;

    /**
     * Constructor que recibe una instancia de JuegoSetup, la cual contiene
     * la configuración del juego, como los equipos y el juez.
     * 
     * @param juegoSetup Instancia de JuegoSetup que contiene la configuración del juego.
     */
    public IniciarJuegoListener(JuegoSetup juegoSetup) {
        this.juegoSetup = juegoSetup;
    }

    /**
     * Maneja el evento de acción cuando el usuario intenta iniciar el juego.
     * Verifica si el juez y al menos un equipo están configurados, mostrando un mensaje
     * de error si faltan datos. Si todo está configurado correctamente, inicia el juego.
     * 
     * @param e El evento que dispara la acción.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Verificar que el juez esté configurado
        if (juegoSetup.getJuez() == null) {
            JOptionPane.showMessageDialog(null, "Por favor configure al juez antes de iniciar el juego.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar que al menos un equipo esté configurado
        if (juegoSetup.getEquipos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor configure al menos un equipo antes de iniciar el juego.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear la instancia del juego (Bolirana) usando la configuración del JuegoSetup
        Bolirana bolirana = new Bolirana(juegoSetup.getJuez(), juegoSetup.getEquipos()); // Pasar el juez y la lista de equipos
        
        // Iniciar el juego
        bolirana.iniciarJuego();
    }
}