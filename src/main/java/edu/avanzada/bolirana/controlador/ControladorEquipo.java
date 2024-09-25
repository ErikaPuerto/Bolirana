package edu.avanzada.bolirana.controlador;

import edu.avanzada.bolirana.modelo.Equipo;
import edu.avanzada.bolirana.modelo.Jugador;

/**
 * Clase que controla las operaciones de un equipo en el juego de Bolirana.
 * Esta clase permite agregar jugadores a un equipo, gestionar el puntaje
 * y determinar si el equipo ha alcanzado el puntaje ganador.
 */
public class ControladorEquipo {
    
    /**
     * Puntaje necesario para que un equipo gane.
     */
    protected int PUNTAJE_GANADOR;
    
    /**
     * Equipo gestionado por este controlador.
     */
    private Equipo equipo;

    /**
     * Constructor de la clase ControladorEquipo.
     * Inicializa el controlador con un equipo.
     *
     * @param equipo El equipo que será gestionado.
     */
    public ControladorEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    /**
     * Establece el puntaje necesario para ganar.
     *
     * @param PUNTAJE_GANADOR El puntaje que un equipo necesita para ganar.
     */
    public void setPUNTAJE_GANADOR(int PUNTAJE_GANADOR) {
        this.PUNTAJE_GANADOR = PUNTAJE_GANADOR;
    }
    
    /**
     * Agrega un jugador al equipo.
     *
     * @param jugador El jugador que se añadirá al equipo.
     */
    public void agregarJugador(Jugador jugador) {
        equipo.getJugadores().add(jugador);
    }

    /**
     * Agrega puntaje al equipo.
     *
     * @param puntaje El puntaje a agregar al total del equipo.
     */
    public void agregarPuntaje(int puntaje) {
        equipo.agregarPuntaje(puntaje);
    }

    /**
     * Verifica si el equipo ha alcanzado el puntaje ganador.
     *
     * @return true si el puntaje total del equipo es mayor o igual al puntaje ganador, de lo contrario false.
     */
    public boolean haGanado() {
        return equipo.getPuntajeTotal() >= PUNTAJE_GANADOR;
    }

    /**
     * Obtiene el puntaje total del equipo.
     *
     * @return El puntaje total del equipo.
     */
    public int getPuntajeTotal() {
        return equipo.getPuntajeTotal();  // Retorna el puntaje total del equipo
    }
}