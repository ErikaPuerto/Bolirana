package edu.avanzada.bolirana.controlador;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Listener para manejar el inicio del juego.
 */
class IniciarJuegoListener implements ActionListener {
    private JuegoSetup juegoSetup;

    // Constructor que recibe JuegoSetup
    public IniciarJuegoListener(JuegoSetup juegoSetup) {
        this.juegoSetup = juegoSetup;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Verificar que el juez y al menos un equipo estén configurados antes de iniciar el juego
        if (juegoSetup.getJuez() == null) {
            JOptionPane.showMessageDialog(null, "Por favor configure al juez antes de iniciar el juego.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

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

