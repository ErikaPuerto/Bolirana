package edu.avanzada.bolirana.controlador;

import edu.avanzada.bolirana.modelo.Equipo;
import edu.avanzada.bolirana.modelo.Jugador;
import edu.avanzada.bolirana.modelo.Lider;
import edu.avanzada.bolirana.modelo.Persistencia;
import edu.avanzada.bolirana.vista.EquipoFrame;
import edu.avanzada.bolirana.vista.JuegoFrame;
import edu.avanzada.bolirana.vista.JuezFrame;
import edu.avanzada.bolirana.vista.JugadorFrame;
import edu.avanzada.bolirana.vista.MainFrame;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controlador principal que gestiona la configuración y ejecución del juego de Bolirana.
 * Incluye la lógica para la selección de equipos, asignación de jugadores y líderes,
 * y el manejo del inicio y desarrollo de los partidos.
 */
public class ControladorJuego {

    protected MainFrame mainFrame;
    JuegoSetup juegoSetup;
    private JuegoFrame juegoFrame;
    protected Persistencia persistencia;
    private Bolirana bolirana;  // Instancia de la clase Bolirana para manejar la lógica del juego
    private List<Equipo> equiposRestantes; // Lista de equipos para la selección aleatoria
    private boolean partidoEnCurso = false; // Variable para controlar si un partido está en curso
    private int turno = 0;  // Usamos este contador para alternar entre los dos equipos.
    private int indiceJugadorEquipo1 = 0;  // Índice del jugador actual del equipo 1
    private int indiceJugadorEquipo2 = 0;  // Índice del jugador actual del equipo 2

    /**
     * Constructor que inicializa el controlador del juego.
     *
     * @param juegoSetup Objeto que contiene la configuración del juego.
     * @param persistencia Objeto para gestionar la persistencia de los datos.
     */
    public ControladorJuego(JuegoSetup juegoSetup, Persistencia persistencia) {
        this.mainFrame = new MainFrame();
        this.persistencia = persistencia;
        this.juegoSetup = juegoSetup;

        // Conectar los listeners
        conectarListeners();
    }

    /**
     * Conecta los listeners a los botones de la interfaz gráfica para iniciar la configuración y el juego.
     */
    private void conectarListeners() {
        mainFrame.addIniciarConfiguracionListener(e -> abrirVentanaJuez());
        mainFrame.addIniciarJuegoListener(e -> {
            mainFrame.setVisible(false);
            try {
                iniciarJuegoEntreDosEquiposAleatorios();  // Iniciar el juego directamente entre dos equipos aleatorios
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(mainFrame, "Ocurrió un error al iniciar el juego: "
                        + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    /**
     * Método para abrir la ventana de configuración del juez.
     * Permite al usuario ingresar los datos del juez antes de iniciar el juego.
     */
    public void abrirVentanaJuez() {
        if (!juegoSetup.configuracionCompleta()) {
            mainFrame.setVisible(false);
            JuezFrame juezFrame = new JuezFrame();
            juezFrame.setVisible(true);

            juezFrame.getBtnGuardarJuez().addActionListener(e -> {
                String nombre = juezFrame.getNombreJuez();
                String cedula = juezFrame.getCedulaJuez();
                int edad = juezFrame.getEdadJuez();
                String tarjetaProfesional = juezFrame.getTarjetaProfesionalJuez();

                if (nombre.isEmpty() || cedula.isEmpty() || edad <= 0 || tarjetaProfesional.isEmpty()) {
                    JOptionPane.showMessageDialog(juezFrame, "Por favor completa todos los campos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                juegoSetup.configurarJuez(nombre, cedula, edad, tarjetaProfesional);
                juezFrame.dispose(); // Cerrar la ventana del juez
                preguntarNumeroEquipos();
            });
        } else {
            JOptionPane.showMessageDialog(null, "Ya se ingresaron los datos.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Método para preguntar al usuario cuántos equipos participarán en el juego.
     * Permite seleccionar entre 2 y 6 equipos.
     */
    public void preguntarNumeroEquipos() {
        String input = JOptionPane.showInputDialog(mainFrame, "¿Cuántos equipos desea inscribir? (2-6 equipos)", "Número de equipos", JOptionPane.QUESTION_MESSAGE);
        try {
            int totalEquipos = Integer.parseInt(input);
            if (totalEquipos < 2 || totalEquipos > 6) {
                JOptionPane.showMessageDialog(mainFrame, "El número de equipos debe ser entre 2 y 6.", "Error", JOptionPane.ERROR_MESSAGE);
                preguntarNumeroEquipos();
            } else {
                juegoSetup.setTotalEquipos(totalEquipos);
                configurarEquipoSiguiente();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(mainFrame, "Por favor ingresa un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            preguntarNumeroEquipos();
        }
    }

    /**
     * Método para configurar el siguiente equipo. Si ya se configuraron todos los equipos, permite avanzar.
     */
    public void configurarEquipoSiguiente() {
        if (juegoSetup.getEquipos().size() < juegoSetup.getTotalEquipos()) {
            abrirVentanaEquipo();
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Todos los equipos han sido configurados.");
            mainFrame.setVisible(true);
        }
    }

    /**
     * Abre la ventana para registrar un nuevo equipo, solicitando el nombre del equipo.
     */
    public void abrirVentanaEquipo() {
        EquipoFrame equipoFrame = new EquipoFrame();
        equipoFrame.setVisible(true);

        equipoFrame.getBtnAgregarEquipo().addActionListener(e -> {
            String nombreEquipo = equipoFrame.getNombreEquipo();
            if (nombreEquipo.isEmpty()) {
                JOptionPane.showMessageDialog(equipoFrame, "Por favor ingresa un nombre para el equipo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Equipo equipo = new Equipo(nombreEquipo);
            juegoSetup.agregarEquipo(equipo);
            equipoFrame.dispose();
            preguntarNumeroJugadores(equipo);  // Configurar los jugadores del equipo
        });
    }

    /**
     * Solicita el número de jugadores para el equipo actual.
     * Cada equipo debe tener exactamente 6 jugadores.
     *
     * @param equipo El equipo que está siendo configurado.
     */
    public void preguntarNumeroJugadores(Equipo equipo) {
        JOptionPane.showMessageDialog(mainFrame, "Cada equipo debe tener exactamente 6 jugadores.", "Información", JOptionPane.INFORMATION_MESSAGE);
        abrirVentanaJugador(equipo, 6);  // Registrar jugadores
    }

    /**
     * Abre la ventana de inscripción de jugadores para un equipo dado.
     *
     * @param equipo El equipo al cual se agregarán los jugadores.
     * @param jugadoresPorEquipo Número total de jugadores por equipo.
     */
    public void abrirVentanaJugador(Equipo equipo, int jugadoresPorEquipo) {
        JugadorFrame jugadorFrame = new JugadorFrame();
        jugadorFrame.setVisible(true);

        jugadorFrame.getBtnAgregarJugador().addActionListener(e -> {
            String nombre = jugadorFrame.getNombreJugador();
            String cedula = jugadorFrame.getCedulaJugador();
            int edad = jugadorFrame.getEdadJugador();
            int numero = jugadorFrame.getNumeroJugador();

            if (nombre.isEmpty() || cedula.isEmpty() || edad <= 0 || numero <= 0) {
                JOptionPane.showMessageDialog(jugadorFrame, "Por favor completa todos los campos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Jugador jugador = new Jugador(nombre, cedula, edad, numero);
            equipo.getJugadores().add(jugador);
            jugadorFrame.dispose();

            if (equipo.getJugadores().size() < jugadoresPorEquipo) {
                abrirVentanaJugador(equipo, jugadoresPorEquipo);  // Continuar añadiendo jugadores
            } else {
                try {
                    elegirLider(equipo);  // Elegir capitán después de registrar jugadores
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    /**
     * Método para seleccionar el líder del equipo actual.
     *
     * @param equipo El equipo para el cual se seleccionará un líder.
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void elegirLider(Equipo equipo) throws ClassNotFoundException, IOException {
        String nombreLider = JOptionPane.showInputDialog(mainFrame, "¿Quién será el líder del equipo " + equipo.getNombre() + "?");

        Jugador jugadorLider = equipo.getJugadores().stream()
                .filter(jugador -> jugador.getNombre().equalsIgnoreCase(nombreLider))
                .findFirst()
                .orElse(null);

        if (jugadorLider == null) {
            JOptionPane.showMessageDialog(mainFrame, "El líder no coincide con ningún jugador registrado. Inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
            elegirLider(equipo);
        } else {
            boolean experienciaValida = false;
            int experiencia = 0;

            while (!experienciaValida) {
                experiencia = Integer.parseInt(JOptionPane.showInputDialog(mainFrame, "¿Cuántos años de experiencia tiene el líder?"));

                if (experiencia >= jugadorLider.getEdad()) {
                    JOptionPane.showMessageDialog(mainFrame, "La experiencia no puede ser igual o mayor a la edad del líder (" + jugadorLider.getEdad() + "). Inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    experienciaValida = true;
                }
            }

            equipo.setLider(new Lider(jugadorLider.getNombre(), jugadorLider.getCedula(), jugadorLider.getEdad(), experiencia));
            persistencia.GuardarJuego(juegoSetup);
            JOptionPane.showMessageDialog(mainFrame, "El líder del equipo " + equipo.getNombre() + " es " + jugadorLider.getNombre() + " con " + experiencia + " años de experiencia.");
            configurarEquipoSiguiente();
        }
    }

    /**
     * Inicia el juego seleccionando dos equipos aleatorios entre los equipos configurados.
     *
     * @throws IOException En caso de que ocurra un error durante el proceso.
     */
    public void iniciarJuegoEntreDosEquiposAleatorios() throws IOException {
        if (!juegoSetup.configuracionCompleta()) {
            JOptionPane.showMessageDialog(mainFrame, "La configuración del juego no está completa. Asegúrate de que el juez y los equipos estén configurados.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        equiposRestantes = new ArrayList<>(juegoSetup.getEquipos());

        if (equiposRestantes.size() < 2) {
            JOptionPane.showMessageDialog(mainFrame, "Debe haber al menos dos equipos registrados para iniciar el juego.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Seleccionar dos equipos aleatorios
        Equipo[] equiposSeleccionados = seleccionarDosEquiposAleatorios(equiposRestantes);

        // Iniciar el partido entre los dos equipos seleccionados
        iniciarPartido(equiposSeleccionados[0], equiposSeleccionados[1]);
    }

    /**
     * Selecciona dos equipos aleatorios de la lista de equipos configurados.
     *
     * @param equipos Lista de equipos configurados.
     * @return Un arreglo con los dos equipos seleccionados.
     */
    public Equipo[] seleccionarDosEquiposAleatorios(List<Equipo> equipos) {
        Collections.shuffle(equipos);  // Mezclar la lista de equipos
        return new Equipo[]{equipos.get(0), equipos.get(1)};  // Seleccionar los dos primeros después de mezclar
    }

    /**
     * Inicia un partido entre dos equipos seleccionados, alternando los turnos de los jugadores.
     *
     * @param equipo1 El primer equipo seleccionado.
     * @param equipo2 El segundo equipo seleccionado.
     * @throws IOException En caso de que ocurra un error durante el proceso.
     */
    public void iniciarPartido(Equipo equipo1, Equipo equipo2) throws IOException {
        mainFrame.setVisible(false);
        List<Jugador> jugadoresEquipo1 = equipo1.getJugadores();
        List<Jugador> jugadoresEquipo2 = equipo2.getJugadores();

        // Si ambos equipos respondieron que sí, continuar con el partido
        this.bolirana = new Bolirana(juegoSetup.getJuez(), List.of(equipo1, equipo2), this);

        JOptionPane.showMessageDialog(mainFrame, "¡El partido entre " + equipo1.getNombre() + " y " + equipo2.getNombre() + " va a comenzar!");

        bolirana.iniciarJuego();  // Iniciar el partido

        // Usar un Timer para alternar entre los jugadores de los dos equipos
        Timer timer = new Timer(1000, e -> {
            Jugador jugadorActual;

            // Alternar entre los equipos. Si turno es par, equipo 1 juega; si es impar, equipo 2 juega.
            if (turno % 2 == 0) {
                // Es el turno de un jugador del equipo 1
                if (indiceJugadorEquipo1 < jugadoresEquipo1.size()) {
                    jugadorActual = jugadoresEquipo1.get(indiceJugadorEquipo1);
                    indiceJugadorEquipo1++;  // Pasar al siguiente jugador del equipo 1
                }
            } else {
                // Es el turno de un jugador del equipo 2
                if (indiceJugadorEquipo2 < jugadoresEquipo2.size()) {
                    jugadorActual = jugadoresEquipo2.get(indiceJugadorEquipo2);
                    indiceJugadorEquipo2++;  // Pasar al siguiente jugador del equipo 2
                }
            }

            turno++;  // Cambiar el turno para alternar entre los equipos

            // Verificar si ya todos los jugadores han tenido su turno
            if (indiceJugadorEquipo1 >= jugadoresEquipo1.size() && indiceJugadorEquipo2 >= jugadoresEquipo2.size()) {
                ((Timer) e.getSource()).stop();  // Detener el Timer cuando todos han jugado
            }
        });
        timer.start();  // Iniciar el Timer
    }
}