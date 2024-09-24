package edu.avanzada.bolirana.modelo;

import edu.avanzada.bolirana.controlador.ControladorEquipo;
import edu.avanzada.bolirana.vista.JuegoFrame;

import javax.swing.*;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Clase que simula el juego de Bolirana.
 */
public class Bolirana {
    private List<Equipo> equipos;
    private Juez juez;
    private JuegoFrame juegoFrame;
    private boolean juegoActivo;
    private Random random;
    private Equipo equipoA;
    private Equipo equipoB;
    private ControladorEquipo controladorEquipoA;
    private ControladorEquipo controladorEquipoB;
    private int turnoActual;
    private int indiceJugadorEquipoA;
    private int indiceJugadorEquipoB;
    private Timer turnoTimer;
    private static final int PUNTAJE_GANADOR = 5000;

    public Bolirana(Juez juez, List<Equipo> equipos) {
        this.juez = juez;
        this.equipos = equipos;
        this.juegoActivo = true;
        this.random = new Random();
        this.turnoActual = 0;
        this.indiceJugadorEquipoA = 0;
        this.indiceJugadorEquipoB = 0;
    }

    public void iniciarJuego() {
        if (juez == null) {
            if (juegoFrame != null) {
                juegoFrame.agregarTurno("No se ha configurado un juez para este juego.");
            }
            return;
        }

        juegoFrame = new JuegoFrame(equipos.stream().map(Equipo::getNombre).toList());
        juegoFrame.setVisible(true);

        juegoFrame.agregarTurno("El juez " + juez.getNombre() + " lanza la moneda...");
        juegoFrame.agregarTurno("Por favor jugadores que no participen en el turno y espectadores, mantener distancia de 2 metros.");

        Timer timer = new Timer(1000, e -> realizarSorteo());
        timer.setRepeats(false);
        timer.start();
    }

    private void realizarSorteo() {
        equipoA = equipos.get(random.nextInt(equipos.size()));
        equipoB = equipos.get(random.nextInt(equipos.size()));

        while (equipoA.equals(equipoB)) {
            equipoB = equipos.get(random.nextInt(equipos.size()));
        }

        juegoFrame.agregarTurno("Los equipos seleccionados para jugar son: " + equipoA.getNombre() + " y " + equipoB.getNombre() + ".");

        Collections.shuffle(equipoA.getJugadoresSinLider());
        Collections.shuffle(equipoB.getJugadoresSinLider());

        controladorEquipoA = new ControladorEquipo(equipoA);
        controladorEquipoB = new ControladorEquipo(equipoB);

        iniciarTurnos();
    }

    private void iniciarTurnos() {
        turnoTimer = new Timer(500, e -> alternarTurnos());
        turnoTimer.start();
    }

    private void alternarTurnos() {
        if (!juegoActivo) return;

        Jugador jugadorActual;

        // Alternar entre los equipos A y B estrictamente
        if (turnoActual % 2 == 0 && indiceJugadorEquipoA < equipoA.getJugadoresSinLider().size()) {
            jugadorActual = equipoA.getJugadoresSinLider().get(indiceJugadorEquipoA);
            realizarTurno(jugadorActual, equipoA, controladorEquipoA);
            indiceJugadorEquipoA++;
        } else if (turnoActual % 2 == 1 && indiceJugadorEquipoB < equipoB.getJugadoresSinLider().size()) {
            jugadorActual = equipoB.getJugadoresSinLider().get(indiceJugadorEquipoB);
            realizarTurno(jugadorActual, equipoB, controladorEquipoB);
            indiceJugadorEquipoB++;
        }

        turnoActual++;

        // Verificar si todos los jugadores han jugado y reiniciar índices
        if (indiceJugadorEquipoA >= equipoA.getJugadoresSinLider().size() && 
            indiceJugadorEquipoB >= equipoB.getJugadoresSinLider().size()) {
            
            indiceJugadorEquipoA = 0;
            indiceJugadorEquipoB = 0;
        }

        // Verificar si algún equipo ha alcanzado el puntaje necesario para ganar
        if (haAlcanzadoPuntajeGanador()) {
            mostrarEquipoGanador();
            turnoTimer.stop();
        }
    }

    private void mostrarEquipoGanador() {
        juegoActivo = false;
        Equipo equipoGanador = controladorEquipoA.haGanado() ? equipoA : equipoB;

        StringBuilder mensaje = new StringBuilder("¡El equipo " + equipoGanador.getNombre() + " ha ganado el torneo!\n\n");
        mensaje.append("Jugadores del equipo ganador:\n");

        for (Jugador jugador : equipoGanador.getJugadores()) {
            mensaje.append(jugador.getNombre()).append(" - Cédula: ").append(jugador.getCedula()).append("\n");
        }

        mensaje.append("\nLíder: ").append(equipoGanador.getLider().getNombre());
        mensaje.append("\nPuntaje final del equipo: ").append(
            controladorEquipoA.haGanado() ? controladorEquipoA.getPuntajeTotal() : controladorEquipoB.getPuntajeTotal()
        ).append(" puntos.");

        JOptionPane.showMessageDialog(juegoFrame, mensaje.toString(), "¡Equipo Ganador!", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean haAlcanzadoPuntajeGanador() {
        return controladorEquipoA.haGanado() || controladorEquipoB.haGanado();
    }

    private void realizarTurno(Jugador jugador, Equipo equipo, ControladorEquipo controladorEquipo) {
        if (!juegoActivo) return;

        boolean validado = mostrarValidaciones(jugador);
        if (!validado) {
            juegoFrame.agregarTurno("El turno de " + jugador.getNombre() + " ha sido cancelado.");
            return;
        }

        final Timer[] lanzamientoTimer = {null};
        final int[] lanzamientoActual = {1};

        lanzamientoTimer[0] = new Timer(200, e -> {
            if (lanzamientoActual[0] <= 5 && juegoActivo) {
                int puntaje = determinarPuntajeLanzamiento();
                controladorEquipo.agregarPuntaje(puntaje);

                juegoFrame.agregarTurno("Lanzamiento " + lanzamientoActual[0] + ": " + jugador.getNombre() + " del equipo " + equipo.getNombre() + " obtuvo " + puntaje + " puntos.");
                juegoFrame.actualizarPuntaje(equipo.getNombre(), controladorEquipo.getPuntajeTotal());

                if (controladorEquipo.haGanado()) {
                    mostrarEquipoGanador();
                    lanzamientoTimer[0].stop();
                }

                lanzamientoActual[0]++;
            } else {
                lanzamientoTimer[0].stop();
            }
        });

        lanzamientoTimer[0].setRepeats(true);
        lanzamientoTimer[0].start();
    }

    private boolean mostrarValidaciones(Jugador jugador) {
        JCheckBox sePasoDeLaLinea = new JCheckBox("¿El jugador se pasó de la línea?");
        JCheckBox lanzoCincoBolas = new JCheckBox("¿El jugador no lanzó las 5 bolas?");
        JCheckBox dosMetros = new JCheckBox("¿El jugador no está a 2 metros?");
        Object[] message = {
            "Validaciones para " + jugador.getNombre() + ":",
            sePasoDeLaLinea,
            lanzoCincoBolas,
            dosMetros
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Validación del jugador", JOptionPane.OK_CANCEL_OPTION);
        return option == JOptionPane.OK_OPTION && !(sePasoDeLaLinea.isSelected() || lanzoCincoBolas.isSelected() || dosMetros.isSelected());
    }

    private int determinarPuntajeLanzamiento() {
        int resultado = random.nextInt(100);
        if (resultado < 10) {
            return 50;
        } else if (resultado < 30) {
            return 25;
        } else if (resultado < 60) {
            return 10;
        } else {
            return 5;
        }
    }
}
