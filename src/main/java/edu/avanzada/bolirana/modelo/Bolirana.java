package edu.avanzada.bolirana.modelo;

import edu.avanzada.bolirana.controlador.ControladorEquipo;
import edu.avanzada.bolirana.controlador.ControladorJugador;
import edu.avanzada.bolirana.vista.JuegoFrame;

import javax.swing.*;
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
    private Equipo equipoGanador;

    /**
     * Constructor para crear un juego de Bolirana.
     *
     * @param juez El juez encargado del juego.
     * @param equipos Lista de equipos que participarán.
     */
    public Bolirana(Juez juez, List<Equipo> equipos) {
        this.juez = juez;
        this.equipos = equipos;
        this.juegoActivo = true;
        this.random = new Random(); // Inicializamos el generador aleatorio
        this.equipoGanador = null;
    }

    /**
     * Método que inicia el juego y realiza el sorteo para determinar qué equipos juegan.
     */
    public void iniciarJuego() {
        if (juez == null) {
            if (juegoFrame != null) {
                juegoFrame.agregarTurno("No se ha configurado un juez para este juego.");
            }
            return; // Evitar continuar si el juez no está configurado
        }

        // Crear la ventana del juego y la puntuación
        juegoFrame = new JuegoFrame(equipos.stream().map(Equipo::getNombre).toList());
        juegoFrame.setVisible(true);

        // Simular el lanzamiento de la moneda por parte del juez
        juegoFrame.agregarTurno("El juez " + juez.getNombre() + " lanza la moneda...");
        juegoFrame.agregarTurno("Por favor jugadores que no participen en el turno y espectadores, conservar una distancia de 2 metros. Solo debe estar en el área indicada el jugador correspondiente.");

        // Pausa de 5 segundos antes de mostrar el resultado del sorteo
        Timer timer = new Timer(4000, e -> realizarSorteo());
        timer.setRepeats(false);  // El sorteo solo se realiza una vez
        timer.start();
    }

    /**
     * Realiza el sorteo para determinar qué dos equipos jugarán el juego.
     */
    private void realizarSorteo() {
        // Selecciona dos equipos aleatoriamente
        equipoA = equipos.get(random.nextInt(equipos.size()));
        equipoB = equipos.get(random.nextInt(equipos.size()));
        
        // Asegurarse de que los dos equipos sean diferentes
        while (equipoA.equals(equipoB)) {
            equipoB = equipos.get(random.nextInt(equipos.size()));
        }

        // Mostrar los equipos seleccionados en la interfaz
        juegoFrame.agregarTurno("Los equipos seleccionados para jugar son: " + equipoA.getNombre() + " y " + equipoB.getNombre() + ".");

        // Iniciar el juego por turnos después del sorteo
        iniciarTurnos(equipoA);
    }

    /**
     * Inicia el juego por turnos después de que se haya decidido el equipo inicial.
     *
     * @param equipoInicial El equipo que comenzará el juego.
     */
    private void iniciarTurnos(Equipo equipoInicial) {
        realizarTurno(equipoInicial);
    }

    /**
     * Realiza el turno actual del equipo.
     *
     * @param equipo El equipo que está realizando el turno.
     */
    private void realizarTurno(Equipo equipo) {
        if (!juegoActivo) {
            return; // Detener el juego si ya terminó
        }

        // Mostrar el mensaje del juez antes de cada turno
        juegoFrame.agregarTurno("El juez verifica y da la orden para el lanzamiento.");

        // Excluir al líder, solo los jugadores juegan
        for (Jugador jugador : equipo.getJugadoresSinLider()) {
            ControladorJugador controladorJugador = new ControladorJugador(jugador);
            ControladorEquipo controladorEquipo = new ControladorEquipo(equipo);

            // Validaciones para el jugador antes de su turno
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
            if (option == JOptionPane.OK_OPTION) {
                if (sePasoDeLaLinea.isSelected() || lanzoCincoBolas.isSelected() || dosMetros.isSelected()) {
                    juegoFrame.agregarTurno("El turno de " + jugador.getNombre() + " ha sido anulado por no cumplir las reglas.");
                    continue;
                }
            } else {
                juegoFrame.agregarTurno("El turno de " + jugador.getNombre() + " ha sido cancelado.");
                continue;
            }

            // Simular 5 lanzamientos
            for (int i = 1; i <= 5; i++) {
                int finalI = i;
                Timer lanzamientoTimer = new Timer(750, e -> {
                    int puntaje = determinarPuntajeLanzamiento();
                    controladorEquipo.agregarPuntaje(puntaje);

                    String mensajeLanzamiento = "Lanzamiento de bola " + finalI + ": " + jugador.getNombre() + " del equipo " + equipo.getNombre() + " obtuvo " + puntaje + " puntos.";
                    juegoFrame.agregarTurno(mensajeLanzamiento);

                    juegoFrame.actualizarPuntaje(equipo.getNombre(), equipo.getPuntajeTotal());

                    if (controladorEquipo.haGanado()) {
                        juegoFrame.agregarTurno("¡El equipo " + equipo.getNombre() + " ha ganado el juego con " + equipo.getPuntajeTotal() + " puntos!");
                        juegoActivo = false;
                        equipoGanador = equipo;
                    }

                    // Verificar si se han completado los lanzamientos del jugador y del equipo
                    if (finalI == 5 && jugador == equipo.getJugadoresSinLider().get(equipo.getJugadoresSinLider().size() - 1)) {
                        if (equipo.equals(equipoA)) {
                            realizarTurno(equipoB); // Turno del equipo B
                        } else {
                            realizarTurno(equipoA); // Turno del equipo A
                        }
                    }
                });
                lanzamientoTimer.setRepeats(false);
                lanzamientoTimer.start();
            }
        }
    }

    /**
     * Determina el puntaje de un lanzamiento basado en los diferentes orificios del juego.
     *
     * @return El puntaje obtenido en el lanzamiento.
     */
    private int determinarPuntajeLanzamiento() {
        int resultado = random.nextInt(100);
        if (resultado < 10) {
            juegoFrame.agregarTurno("¡RANA! 50 puntos.");
            return 50;
        } else if (resultado < 30) {
            return 25;
        } else if (resultado < 60) {
            return 10;
        } else {
            return 5;
        }
    }
    
    public Equipo getEquipoGanador() {
        return equipoGanador;
    }
}
