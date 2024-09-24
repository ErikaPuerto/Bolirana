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
     * Constructor que inicializa el controlador.
     *
     * @param juegoSetup
     */
    public ControladorJuego(JuegoSetup juegoSetup, Persistencia persistencia) {
        this.mainFrame = new MainFrame();
        this.persistencia = persistencia;
        this.juegoSetup = juegoSetup;

        // Conectar los listeners
        conectarListeners();
    }

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
     * Método para abrir la ventana del juez para configurarlo.
     */
    private void abrirVentanaJuez() {
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
            JOptionPane.showMessageDialog(null, "Ya se ingresaron los datos.", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Método para preguntar cuántos equipos se desean inscribir.
     */
    private void preguntarNumeroEquipos() {
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
     * Método para configurar el siguiente equipo.
     */
    private void configurarEquipoSiguiente() {
        if (juegoSetup.getEquipos().size() < juegoSetup.getTotalEquipos()) {
            abrirVentanaEquipo();
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Todos los equipos han sido configurados.");
            mainFrame.setVisible(true);
        }
    }

    /**
     * Método para abrir la ventana de inscripción de equipos.
     */
    private void abrirVentanaEquipo() {
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
     * Preguntar cuántos jugadores tendrá el equipo actual.
     */
    private void preguntarNumeroJugadores(Equipo equipo) {
        JOptionPane.showMessageDialog(mainFrame, "Cada equipo debe tener exactamente 6 jugadores.", "Información", JOptionPane.INFORMATION_MESSAGE);
        abrirVentanaJugador(equipo, 6);  // Registrar jugadores
    }

    /**
     * Método para abrir la ventana de inscripción de jugadores.
     */
    private void abrirVentanaJugador(Equipo equipo, int jugadoresPorEquipo) {
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
     * Método para seleccionar un capitán de equipo.
     */
    private void elegirLider(Equipo equipo) throws ClassNotFoundException, IOException {
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

    private void iniciarJuegoEntreDosEquiposAleatorios() throws IOException {
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
     * Seleccionar dos equipos aleatorios de la lista.
     */
    private Equipo[] seleccionarDosEquiposAleatorios(List<Equipo> equipos) {
        Collections.shuffle(equipos);  // Mezclar la lista de equipos
        return new Equipo[]{equipos.get(0), equipos.get(1)};  // Seleccionar los dos primeros después de mezclar
    }

    /**
     * Iniciar un partido entre dos equipos seleccionados con alternancia de jugadores.
     */
    private void iniciarPartido(Equipo equipo1, Equipo equipo2) throws IOException {
        mainFrame.setVisible(false);
        List<Jugador> jugadoresEquipo1 = equipo1.getJugadores();
        List<Jugador> jugadoresEquipo2 = equipo2.getJugadores();

        //this.juegoFrame = new JuegoFrame(List.of(equipo1.getNombre(), equipo2.getNombre()));
        this.bolirana = new Bolirana(juegoSetup.getJuez(), List.of(equipo1, equipo2), this);

        JOptionPane.showMessageDialog(mainFrame, "¡El partido entre " + equipo1.getNombre() + " y " + equipo2.getNombre() + " va a comenzar!");

        bolirana.iniciarJuego();  // Iniciar el partido

        // Usar un Timer para alternar entre los jugadores de los dos equipos
        Timer timer = new Timer(1000, e -> {
            Jugador jugadorActual;

            // Alternar entre los equipos. Si turno es par, equipo 1 juega, si es impar, equipo 2 juega.
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
