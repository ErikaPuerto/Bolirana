package edu.avanzada.bolirana.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un equipo en el juego Bolirana.
 * Cada equipo tiene un nombre, un líder, una lista de jugadores y un puntaje total.
 * Implementa la interfaz Serializable para permitir la persistencia de los datos.
 */
public class Equipo implements Serializable {
    private String nombre;
    private Lider lider;
    private List<Jugador> jugadores;
    private int puntajeTotal;

    /**
     * Constructor que inicializa un equipo con un nombre.
     * Inicializa la lista de jugadores y el puntaje total en 0.
     *
     * @param nombre El nombre del equipo.
     */
    public Equipo(String nombre) {
        this.nombre = nombre;
        this.jugadores = new ArrayList<>();
        this.puntajeTotal = 0;
    }

    /**
     * Obtiene el nombre del equipo.
     *
     * @return El nombre del equipo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el puntaje total del equipo.
     *
     * @param puntajeTotal El nuevo puntaje total.
     */
    public void setPuntajeTotal(int puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }

    /**
     * Obtiene la lista completa de jugadores del equipo.
     *
     * @return Lista de jugadores.
     */
    public List<Jugador> getJugadores() {
        return jugadores;
    }

    /**
     * Obtiene el líder del equipo.
     *
     * @return El líder del equipo.
     */
    public Lider getLider() {
        return lider;
    }

    /**
     * Establece el líder del equipo.
     *
     * @param lider El nuevo líder del equipo.
     */
    public void setLider(Lider lider) {
        this.lider = lider;
    }

    /**
     * Obtiene el puntaje total del equipo.
     *
     * @return El puntaje total del equipo.
     */
    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    /**
     * Agrega puntaje al puntaje total del equipo.
     *
     * @param puntaje El puntaje a agregar.
     */
    public void agregarPuntaje(int puntaje) {
        this.puntajeTotal += puntaje;
    }

    /**
     * Agrega un jugador a la lista de jugadores del equipo.
     *
     * @param jugador El jugador a agregar.
     */
    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    /**
     * Obtiene una lista de jugadores del equipo, excluyendo al líder.
     *
     * @return Lista de jugadores sin incluir al líder.
     */
    public List<Jugador> getJugadoresSinLider() {
        List<Jugador> jugadoresSinLider = new ArrayList<>(jugadores);
        if (lider != null) {
            jugadoresSinLider.removeIf(jugador -> jugador.getCedula().equals(lider.getCedula()));
        }
        return jugadoresSinLider;
    }

    /**
     * Representación en cadena de texto de la clase Equipo.
     *
     * @return Una cadena de texto con la información del equipo.
     */
    @Override
    public String toString() {
        return "Equipo{" + "nombre=" + nombre + ", lider=" + lider + ", jugadores=" + jugadores + ", puntajeTotal=" + puntajeTotal + '}';
    }
}