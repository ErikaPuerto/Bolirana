package edu.avanzada.bolirana.controlador;

import edu.avanzada.bolirana.modelo.Equipo;
import edu.avanzada.bolirana.modelo.Jugador;

/**
 * Controlador para gestionar las operaciones de un equipo.
 */
public class ControladorEquipo {
    private static final int PUNTAJE_GANADOR = 5000;
    private Equipo equipo;

    public ControladorEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public void agregarJugador(Jugador jugador) {
        equipo.getJugadores().add(jugador);
    }

    /**
     * Agrega puntaje al equipo.
     * @param puntaje Puntaje a agregar.
     */
    public void agregarPuntaje(int puntaje) {
        equipo.agregarPuntaje(puntaje); // Sumar el puntaje al puntajeTotal del equipo
    }

    /**
     * Verifica si el equipo ha ganado.
     * @return true si el puntaje total es mayor o igual a 5000.
     */
    public boolean haGanado() {
        return equipo.getPuntajeTotal() >= PUNTAJE_GANADOR;  // Verificar el puntajeTotal del equipo
    }

    public int getPuntajeTotal() {
        return equipo.getPuntajeTotal();  // Retorna el puntaje total del equipo
    }
}
