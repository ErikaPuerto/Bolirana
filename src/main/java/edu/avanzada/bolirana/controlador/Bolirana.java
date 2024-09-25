package edu.avanzada.bolirana.controlador;

import edu.avanzada.bolirana.modelo.Equipo;
import edu.avanzada.bolirana.modelo.Juez;
import edu.avanzada.bolirana.modelo.Jugador;
import edu.avanzada.bolirana.vista.JuegoFrame;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.*;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que simula el juego de Bolirana.
 */
public class Bolirana {

    private List<Equipo> equipos;
    private Juez juez;
    private JuegoFrame juegoFrame;
    protected boolean juegoActivo;
    private Random random;
    private Equipo equipoA;
    private Equipo equipoB;
    private ControladorEquipo controladorEquipoA;
    private ControladorEquipo controladorEquipoB;
    private int turnoActual;
    private int indiceJugadorEquipoA;
    private int indiceJugadorEquipoB;
    private Timer turnoTimer;
    private ControladorJuego controladorJuego;
    private Properties properties;
    private int PUNTAJE_GANADOR;

    /**
     * Constructor de la clase Bolirana que inicializa el juego con un juez, una lista de equipos y un controlador.
     *
     * @param juez El juez que supervisa el juego.
     * @param equipos La lista de equipos que participarán en el juego.
     * @param controladorJuego El controlador que maneja la lógica del juego.
     * @throws FileNotFoundException Si el archivo de configuración no se encuentra.
     * @throws IOException Si ocurre un error al cargar el archivo de configuración.
     */
    public Bolirana(Juez juez, List<Equipo> equipos, ControladorJuego controladorJuego) throws FileNotFoundException, IOException {
        this.juez = juez;
        this.equipos = equipos;
        this.juegoActivo = true;
        this.random = new Random();
        this.turnoActual = 0;
        this.indiceJugadorEquipoA = 0;
        this.indiceJugadorEquipoB = 0;
        this.controladorJuego = controladorJuego;
        properties = new Properties();
        InputStream entrada = new FileInputStream("Config.properties");
        properties.load(entrada);
        PUNTAJE_GANADOR = Integer.parseInt(properties.getProperty("puntaje.limite"));
    }

    /**
     * Constructor alternativo de la clase Bolirana, utilizado cuando no se necesita un controlador.
     *
     * @param juez El juez que supervisa el juego.
     * @param equipos La lista de equipos que participarán en el juego.
     */
    public Bolirana(Juez juez, List<Equipo> equipos) {
        this.juez = juez;
        this.equipos = equipos;
        this.juegoActivo = true;
        this.random = new Random();
        this.turnoActual = 0;
        this.indiceJugadorEquipoA = 0;
        this.indiceJugadorEquipoB = 0;
    }

    /**
     * Método que inicia el juego, verificando que un juez esté configurado.
     * Muestra la interfaz del juego y empieza el sorteo.
     */
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
        juegoFrame.agregarTurno("Por favor jugadores que no participen en el turno y espectadores mantener distancia de 2 metros.");

        Timer timer = new Timer(1000, e -> realizarSorteo());
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Método que realiza el sorteo para seleccionar dos equipos al azar que jugarán.
     * Verifica si los equipos están completos antes de continuar con el juego.
     */
    private void realizarSorteo() {
        equipoA = equipos.get(random.nextInt(equipos.size()));
        equipoB = equipos.get(random.nextInt(equipos.size()));

        while (equipoA.equals(equipoB)) {
            equipoB = equipos.get(random.nextInt(equipos.size()));
        }

        juegoFrame.agregarTurno("Los equipos seleccionados para jugar son: " + equipoA.getNombre() + " y " + equipoB.getNombre() + ".");

        boolean equipoACompleto = preguntarSiEquipoEstaCompleto(equipoA);
        boolean equipoBCompleto = preguntarSiEquipoEstaCompleto(equipoB);

        if (!equipoACompleto) {
            juegoFrame.agregarTurno("El equipo " + equipoA.getNombre() + " no está completo o no está disponible a la hora. El equipo pierde.");
            juegoActivo = false;
            return;
        }

        if (!equipoBCompleto) {
            juegoFrame.agregarTurno("El equipo " + equipoB.getNombre() + " no está completo o no está disponible a la hora. El equipo pierde.");
            juegoActivo = false;
            return;
        }

        Collections.shuffle(equipoA.getJugadoresSinLider());
        Collections.shuffle(equipoB.getJugadoresSinLider());
        controladorEquipoA = new ControladorEquipo(equipoA);
        controladorEquipoA.setPUNTAJE_GANADOR(PUNTAJE_GANADOR);
        controladorEquipoB = new ControladorEquipo(equipoB);
        controladorEquipoB.setPUNTAJE_GANADOR(PUNTAJE_GANADOR);

        iniciarTurnos();
    }

    /**
     * Pregunta si un equipo está completo y disponible para el juego.
     *
     * @param equipo El equipo a verificar.
     * @return true si el equipo está completo y disponible, de lo contrario, false.
     */
    private boolean preguntarSiEquipoEstaCompleto(Equipo equipo) {
        int respuesta = JOptionPane.showConfirmDialog(null, "¿El equipo " + equipo.getNombre() + " está completo y disponible a la hora acordada?", "Confirmar", JOptionPane.YES_NO_OPTION);
        return respuesta == JOptionPane.YES_OPTION;
    }

    /**
     * Inicia los turnos alternos de los equipos en el juego.
     */
    private void iniciarTurnos() {
        turnoTimer = new Timer(500, e -> {
            try {
                alternarTurnos();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Bolirana.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        turnoTimer.start();
    }

    /**
     * Alterna los turnos entre los dos equipos, haciendo que cada jugador participe en su turno correspondiente.
     * Si un equipo alcanza el puntaje ganador, se detiene el juego y se declara un ganador.
     *
     * @throws IOException Si ocurre un error de entrada/salida.
     * @throws ClassNotFoundException Si no se encuentra una clase necesaria.
     */
    private void alternarTurnos() throws IOException, ClassNotFoundException {
        if (!juegoActivo) {
            return;
        }

        Jugador jugadorActual;

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

        if (indiceJugadorEquipoA >= equipoA.getJugadoresSinLider().size()
                && indiceJugadorEquipoB >= equipoB.getJugadoresSinLider().size()) {
            indiceJugadorEquipoA = 0;
            indiceJugadorEquipoB = 0;
        }

        if (haAlcanzadoPuntajeGanador()) {
            mostrarEquipoGanador();
            turnoTimer.stop();
        }
    }

    /**
     * Muestra el equipo ganador al finalizar el juego.
     *
     * @throws IOException Si ocurre un error de entrada/salida.
     * @throws ClassNotFoundException Si no se encuentra una clase necesaria.
     */
    public void mostrarEquipoGanador() throws IOException, ClassNotFoundException {
        juegoActivo = false;
        juegoFrame.dispose();
        Equipo equipoGanador = controladorEquipoA.haGanado() ? equipoA : equipoB;

        controladorJuego.persistencia.GuardarEquiposGanadores(equipoGanador);
        StringBuilder mensaje = new StringBuilder();

        mensaje.append("El juez ").append(juez.getNombre()).append(" declara que:\n");
        mensaje.append("¡El equipo " + equipoGanador.getNombre() + " ha ganado el torneo!\n\n");
        mensaje.append("Jugadores del equipo ganador:\n");

        for (Jugador jugador : equipoGanador.getJugadores()) {
            mensaje.append(jugador.getNombre()).append(" - Cédula: ").append(jugador.getCedula()).append("\n");
        }

        mensaje.append("\nLíder: ").append(equipoGanador.getLider().getNombre());
        mensaje.append("\nPuntaje final del equipo: ").append(
            controladorEquipoA.haGanado() ? controladorEquipoA.getPuntajeTotal() : controladorEquipoB.getPuntajeTotal()
        ).append(" puntos.");

        controladorJuego.mainFrame.setVisible(true);
        controladorJuego.juegoSetup.ReiniciarPuntos();

        JOptionPane.showMessageDialog(juegoFrame, mensaje.toString(), "¡Equipo Ganador!", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Verifica si algún equipo ha alcanzado el puntaje necesario para ganar.
     *
     * @return true si algún equipo ha ganado, de lo contrario, false.
     */
    private boolean haAlcanzadoPuntajeGanador() {
        return controladorEquipoA.haGanado() || controladorEquipoB.haGanado();
    }

    /**
     * Realiza el turno de un jugador, calculando su puntaje y mostrando los resultados en la interfaz.
     *
     * @param jugador El jugador que está realizando su turno.
     * @param equipo El equipo al que pertenece el jugador.
     * @param controladorEquipo El controlador del equipo que maneja el puntaje.
     */
    private void realizarTurno(Jugador jugador, Equipo equipo, ControladorEquipo controladorEquipo) {
        if (!juegoActivo) {
            return;
        }

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
                    try {
                        mostrarEquipoGanador();
                    } catch (IOException ex) {
                        Logger.getLogger(Bolirana.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Bolirana.class.getName()).log(Level.SEVERE, null, ex);
                    }
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

    /**
     * Muestra una ventana para validar el turno de un jugador según ciertas reglas.
     *
     * @param jugador El jugador cuyo turno se va a validar.
     * @return true si el jugador pasa las validaciones, de lo contrario, false.
     */
    private boolean mostrarValidaciones(Jugador jugador) {
        JCheckBox sePasoDeLaLinea = new JCheckBox("¿El jugador se pasó de la línea?");
        JCheckBox lanzoDosBolas = new JCheckBox("¿El jugador lanzó dos bolas al tiempo?");
        JCheckBox dosMetros = new JCheckBox("¿El jugador no está a 2 metros?");
        Object[] message = {
            "Validaciones para " + jugador.getNombre() + ":",
            sePasoDeLaLinea,
            lanzoDosBolas,
            dosMetros
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Validación del jugador", JOptionPane.OK_CANCEL_OPTION);
        return option == JOptionPane.OK_OPTION && !(sePasoDeLaLinea.isSelected() || lanzoDosBolas.isSelected() || dosMetros.isSelected());
    }

    /**
     * Determina el puntaje obtenido por un jugador en su turno.
     *
     * @return El puntaje obtenido por el jugador.
     */
    private int determinarPuntajeLanzamiento() {
        int resultado = random.nextInt(100);
        if (resultado < 10) {
            return Integer.parseInt(properties.getProperty("puntaje.rana"));
        } else if (resultado < 30) {
            return Integer.parseInt(properties.getProperty("puntaje.botella1"));
        } else if (resultado < 60) {
            return Integer.parseInt(properties.getProperty("puntaje.orificio1"));
        } else {
            return Integer.parseInt(properties.getProperty("puntaje.orificio2"));
        }
    }
}