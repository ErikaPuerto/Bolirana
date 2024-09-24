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
    private  int PUNTAJE_GANADOR;
    
    public Bolirana(Juez juez, List<Equipo> equipos, ControladorJuego controladorJuego) throws FileNotFoundException, IOException {
        this.juez = juez;
        this.equipos = equipos;
        this.juegoActivo = true;
        this.random = new Random();
        this.turnoActual = 0;
        this.indiceJugadorEquipoA = 0;
        this.indiceJugadorEquipoB = 0;
        this.controladorJuego = controladorJuego;
        properties= new Properties();
        InputStream entrada =new FileInputStream("Config.properties");
        properties.load(entrada);
        PUNTAJE_GANADOR=Integer.parseInt(properties.getProperty("puntaje.limite")) ;
    }
    
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
        int valorMax =  Integer.parseInt(properties.getProperty("puntaje.limite")) ;
        controladorEquipoA = new ControladorEquipo(equipoA);
        controladorEquipoB = new ControladorEquipo(equipoB);
        
        iniciarTurnos();
    }
    
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
    
    private void alternarTurnos() throws IOException, ClassNotFoundException {
        if (!juegoActivo) {
            return;
        }
        
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
        if (indiceJugadorEquipoA >= equipoA.getJugadoresSinLider().size()
                && indiceJugadorEquipoB >= equipoB.getJugadoresSinLider().size()) {
            
            indiceJugadorEquipoA = 0;
            indiceJugadorEquipoB = 0;
        }

        // Verificar si algún equipo ha alcanzado el puntaje necesario para ganar
        if (haAlcanzadoPuntajeGanador()) {
            mostrarEquipoGanador();
            turnoTimer.stop();
        }
    }
    
    public void mostrarEquipoGanador() throws IOException, ClassNotFoundException {
        juegoActivo = false;
        juegoFrame.dispose();
        Equipo equipoGanador = controladorEquipoA.haGanado() ? equipoA : equipoB;
        
        controladorJuego.persistencia.GuardarEquiposGanadores(equipoGanador);
        StringBuilder mensaje = new StringBuilder("¡El equipo " + equipoGanador.getNombre() + " ha ganado el torneo!\n\n");
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
    
    private boolean haAlcanzadoPuntajeGanador() {
        return controladorEquipoA.haGanado() || controladorEquipoB.haGanado();
    }
    
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
            return Integer.parseInt(properties.getProperty("puntaje.rana")) ;
        } else if (resultado < 30) {
            return Integer.parseInt(properties.getProperty("puntaje.botella1")) ;
        } else if (resultado < 60) {
            return Integer.parseInt(properties.getProperty("puntaje.orificio1")) ;
        } else {
            return Integer.parseInt(properties.getProperty("puntaje.orificio2")) ;
        }
    }
}
