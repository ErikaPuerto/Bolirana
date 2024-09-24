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
    private int turnoActual;
    private boolean juegoActivo;
    private Random random;
    private int contadorPisadas;
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
        this.contadorPisadas = 0;   // Contador de pisadas de la línea límite
        this.equipoGanador = null;
    }

    /**
     * Método que inicia el juego y realiza el sorteo para determinar qué equipo comienza.
     */
    public void iniciarJuego() {
        if (juez == null) {
            juegoFrame.agregarTurno("No se ha configurado un juez para este juego.");
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
     * Realiza el sorteo para determinar qué equipo comienza el juego.
     */
    private void realizarSorteo() {
        // Elegir un equipo aleatoriamente para que comience
        int equipoInicialIndex = random.nextInt(equipos.size());
        Equipo equipoInicial = equipos.get(equipoInicialIndex);

        // Mostrar el resultado del sorteo en la interfaz
        juegoFrame.agregarTurno("El equipo " + equipoInicial.getNombre() + " ha ganado el sorteo y comenzará el juego.");

        // Iniciar el juego por turnos después del sorteo
        iniciarTurnos(equipoInicial);
    }

    /**
     * Inicia el juego por turnos después de que se haya decidido el equipo inicial.
     *
     * @param equipoInicial El equipo que comenzará el juego.
     */
    private void iniciarTurnos(Equipo equipoInicial) {
        turnoActual = equipos.indexOf(equipoInicial);
        realizarTurno();
    }

    /**
     * Realiza el turno actual del equipo.
     */
    private void realizarTurno() {
        if (!juegoActivo) {
            return; // Detener el juego si ya terminó
        }

        // Mostrar el mensaje del juez antes de cada turno
        juegoFrame.agregarTurno("El juez verifica y da la orden para el lanzamiento.");

        // Determinar el equipo actual que tiene el turno
        Equipo equipoActual = equipos.get(turnoActual % equipos.size());

        // Excluir al líder, solo los jugadores juegan
        for (Jugador jugador : equipoActual.getJugadoresExcluyendoLider()) {  // Usamos el método que excluye al líder
            ControladorJugador controladorJugador = new ControladorJugador(jugador);
            ControladorEquipo controladorEquipo = new ControladorEquipo(equipoActual);

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
                // Si el jugador no cumple con alguna de las reglas
                if (sePasoDeLaLinea.isSelected() || lanzoCincoBolas.isSelected() || dosMetros.isSelected()) {
                    juegoFrame.agregarTurno("El turno de " + jugador.getNombre() + " ha sido anulado por no cumplir las reglas.");
                    continue; // Saltar al siguiente jugador en el bucle
                }
            } else {
                juegoFrame.agregarTurno("El turno de " + jugador.getNombre() + " ha sido cancelado.");
                continue; // Si el diálogo se cancela, saltar al siguiente jugador
            }

            // Simular 5 lanzamientos
            for (int i = 1; i <= 5; i++) {
                // Delay entre lanzamientos (1.5 segundos)
                int finalI = i;
                Timer lanzamientoTimer = new Timer(750, e -> {
                    // Simular el lanzamiento de la bola
                    int puntaje = determinarPuntajeLanzamiento();
                    controladorEquipo.agregarPuntaje(puntaje);

                    // Mostrar el resultado del lanzamiento
                    String mensajeLanzamiento = "Lanzamiento de bola " + finalI + ": " + jugador.getNombre() + " del equipo " + equipoActual.getNombre() + " obtuvo " + puntaje + " puntos.";
                    juegoFrame.agregarTurno(mensajeLanzamiento);

                    // Actualizar la puntuación del equipo
                    juegoFrame.actualizarPuntaje(equipoActual.getNombre(), equipoActual.getPuntajeTotal());

                    // Verificar si el equipo ha ganado
                    if (controladorEquipo.haGanado()) {
                        juegoFrame.agregarTurno("¡El equipo " + equipoActual.getNombre() + " ha ganado el juego con " + equipoActual.getPuntajeTotal() + " puntos!");
                        juegoActivo = false; // Finalizar el juego
                        equipoGanador = equipoActual;  // Asignamos el equipo ganador
                    }

                    // Verificar si se han completado los lanzamientos del jugador y del equipo
                    if (finalI == 5 && jugador == equipoActual.getJugadoresExcluyendoLider().get(equipoActual.getJugadoresExcluyendoLider().size() - 1)) {
                        turnoActual++;
                        realizarTurno(); // Llamar a realizarTurno para el siguiente equipo
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

    public Equipo getEquipoGanador(){
        return equipoGanador;
    }
}
