package edu.avanzada.bolirana.controlador;

import edu.avanzada.bolirana.modelo.Equipo;
import edu.avanzada.bolirana.modelo.Juez;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que se encarga de la configuración del juego.
 */
public class JuegoSetup {
    private Juez juez;
    private List<Equipo> equipos;
    private int totalEquipos;

    public JuegoSetup() {
        this.equipos = new ArrayList<>();
    }

    /**
     * Configura los datos del juez.
     */
    public void configurarJuez(String nombre, String cedula, int edad, String tarjetaProfesional) {
        this.juez = new Juez(nombre, cedula, edad, tarjetaProfesional);
    }

    /**
     * Verifica si la configuración está completa.
     */
    public boolean configuracionCompleta() {
        return juez != null && equipos.size() == totalEquipos;
    }

    /**
     * Agrega un equipo a la lista de equipos.
     */
    public void agregarEquipo(Equipo equipo) {
        equipos.add(equipo);
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public Juez getJuez() {
        return juez;
    }

    public void setTotalEquipos(int totalEquipos) {
        this.totalEquipos = totalEquipos;
    }

    public int getTotalEquipos() {
        return totalEquipos;
    }
}
