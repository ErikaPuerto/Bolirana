/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.avanzada.bolirana.controlador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import edu.avanzada.bolirana.modelo.Equipo;
import edu.avanzada.bolirana.modelo.Juez;
import edu.avanzada.bolirana.modelo.Persistencia;
import edu.avanzada.bolirana.controlador.ControladorJuego;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControladorJuegoTest {

    private JuegoSetup juegoSetup;
    private Persistencia persistencia;
    private ControladorJuego controladorJuego;
    private List<Equipo> equipos;
    private Juez juez;

    @BeforeEach
    public void setUp() {
        // Mock para Persistencia
        juegoSetup = new JuegoSetup();
        persistencia = Mockito.mock(Persistencia.class);

        juez = new Juez("Carlos", "123456", 40, "ABC123");
        juegoSetup.configurarJuez(juez.getNombre(), juez.getCedula(), juez.getEdad(), juez.getTarjetaProfesional());

        equipos = new ArrayList<>();
        equipos.add(new Equipo("Equipo 1"));
        equipos.add(new Equipo("Equipo 2"));
        juegoSetup.setTotalEquipos(2);
        juegoSetup.getEquipos().addAll(equipos);

        controladorJuego = new ControladorJuego(juegoSetup, persistencia);
    }

    @AfterEach
    public void tearDown() {
        controladorJuego = null;
    }

    // **Pruebas que pasan**
    @Test
    public void testConfigurarJuegoCorrectamente() {
        // Verifica que los equipos y el juez estén configurados correctamente
        assertEquals(2, juegoSetup.getEquipos().size());
        assertNotNull(juegoSetup.getJuez());
        assertEquals("Carlos", juegoSetup.getJuez().getNombre());
    }

    @Test
    public void testIniciarJuegoEntreDosEquipos() throws IOException {
        // Verifica que el juego se inicie correctamente entre dos equipos
        controladorJuego.iniciarJuegoEntreDosEquiposAleatorios();
        assertNotNull(controladorJuego);
    }

    // **Pruebas que deberían fallar**
    @Test
    public void testConfigurarJuegoSinJuez() {
        // Configuración sin juez
        juegoSetup = new JuegoSetup();  // Reiniciar setup sin juez
        controladorJuego = new ControladorJuego(juegoSetup, persistencia);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            controladorJuego.iniciarJuegoEntreDosEquiposAleatorios();
        });
        assertEquals("La configuración del juego no está completa.", exception.getMessage());
    }

    @Test
    public void testIniciarJuegoSinEquipos() {
        // Reinicia la configuración sin equipos
        juegoSetup.getEquipos().clear();

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            controladorJuego.iniciarJuegoEntreDosEquiposAleatorios();
        });
        assertEquals("Debe haber al menos dos equipos registrados para iniciar el juego.", exception.getMessage());
    }
}
