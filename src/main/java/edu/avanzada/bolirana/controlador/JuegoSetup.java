package edu.avanzada.bolirana.controlador;

import edu.avanzada.bolirana.modelo.Equipo;
import edu.avanzada.bolirana.modelo.Juez;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de gestionar la configuración del juego de Bolirana.
 * Proporciona métodos para configurar el juez, los equipos, y verificar 
 * si la configuración está completa antes de iniciar el juego.
 */
public class JuegoSetup implements Serializable {

    private Juez juez;
    private List<Equipo> equipos;
    private int totalEquipos;

    /**
     * Constructor que inicializa la lista de equipos.
     */
    public JuegoSetup() {
        this.equipos = new ArrayList<>();
    }

    /**
     * Configura los datos del juez asignado al juego.
     * 
     * @param nombre El nombre del juez.
     * @param cedula La cédula del juez.
     * @param edad La edad del juez.
     * @param tarjetaProfesional El número de la tarjeta profesional del juez.
     */
    public void configurarJuez(String nombre, String cedula, int edad, String tarjetaProfesional) {
        this.juez = new Juez(nombre, cedula, edad, tarjetaProfesional);
    }

    /**
     * Verifica si la configuración del juego está completa. 
     * La configuración se considera completa si el juez está configurado 
     * y la cantidad de equipos registrados es igual al número total de equipos esperado.
     * 
     * @return true si la configuración está completa, false de lo contrario.
     */
    public boolean configuracionCompleta() {
        return juez != null && equipos.size() == totalEquipos;
    }

    /**
     * Reinicia los puntos de todos los equipos registrados, 
     * estableciendo el puntaje total de cada equipo a cero.
     */
    public void ReiniciarPuntos() {
        for (Equipo equipo : equipos) {
            equipo.setPuntajeTotal(0);
        }
    }

    /**
     * Agrega un nuevo equipo a la lista de equipos registrados para el juego.
     * 
     * @param equipo El equipo que se va a agregar.
     */
    public void agregarEquipo(Equipo equipo) {
        equipos.add(equipo);
    }

    /**
     * Obtiene la lista de equipos registrados.
     * 
     * @return Una lista de objetos Equipo.
     */
    public List<Equipo> getEquipos() {
        return equipos;
    }

    /**
     * Obtiene el juez asignado al juego.
     * 
     * @return El juez del juego.
     */
    public Juez getJuez() {
        return juez;
    }

    /**
     * Establece el número total de equipos que participarán en el juego.
     * 
     * @param totalEquipos El número total de equipos.
     */
    public void setTotalEquipos(int totalEquipos) {
        this.totalEquipos = totalEquipos;
    }

    /**
     * Obtiene el número total de equipos que deben registrarse en el juego.
     * 
     * @return El número total de equipos.
     */
    public int getTotalEquipos() {
        return totalEquipos;
    }
}