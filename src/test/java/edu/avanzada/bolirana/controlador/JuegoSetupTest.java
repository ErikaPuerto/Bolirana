/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit este template
 */
package edu.avanzada.bolirana.controlador;

import edu.avanzada.bolirana.modelo.Equipo;
import edu.avanzada.bolirana.modelo.Juez;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para JuegoSetup.
 * Esta clase realiza pruebas unitarias para verificar el comportamiento de la clase JuegoSetup
 * utilizando JUnit5.
 */
public class JuegoSetupTest {

    private JuegoSetup juegoSetup;  // Instancia de JuegoSetup a probar
    private Juez juez;  // Instancia de Juez para pruebas
    private Equipo equipo1;  // Primer equipo de prueba
    private Equipo equipo2;  // Segundo equipo de prueba

    /**
     * Método que se ejecuta antes de cada prueba.
     * Inicializa el JuegoSetup, los equipos y el juez para cada prueba.
     */
    @BeforeEach
    public void setUp() {
        // Inicializamos las instancias necesarias para cada prueba
        juegoSetup = new JuegoSetup();
        juez = new Juez("Juez Principal", "123456789", 45, "TP-2020");
        equipo1 = new Equipo("Equipo A");
        equipo2 = new Equipo("Equipo B");
    }

    /**
     * Método que se ejecuta después de cada prueba.
     * Limpia las referencias de las variables de prueba.
     */
    @AfterEach
    public void tearDown() {
        // Limpiamos las referencias después de cada prueba
        juegoSetup = null;
        juez = null;
        equipo1 = null;
        equipo2 = null;
    }

    // --------------- PRUEBAS EXITOSAS ---------------

    /**
     * Prueba exitosa: Configurar juez y verificar que se ha configurado correctamente.
     * Verifica que el juez configurado sea el esperado.
     */
    @Test
    public void testConfigurarJuez() {
        juegoSetup.configurarJuez(juez.getNombre(), juez.getCedula(), juez.getEdad(), juez.getTarjetaProfesional());
        assertNotNull(juegoSetup.getJuez(), "El juez debería estar configurado.");
        assertEquals(juez.getNombre(), juegoSetup.getJuez().getNombre(), "El nombre del juez debería coincidir.");
    }

    /**
     * Prueba exitosa: Agregar un equipo y verificar que la lista de equipos contiene 1 equipo.
     * Verifica que la lista de equipos tenga exactamente un equipo registrado.
     */
    @Test
    public void testAgregarEquipo() {
        juegoSetup.agregarEquipo(equipo1);
        assertEquals(1, juegoSetup.getEquipos().size(), "Debería haber un equipo registrado.");
    }

    /**
     * Prueba exitosa: Reiniciar puntos de los equipos.
     * Verifica que el puntaje del equipo se reinicia a 0.
     */
    @Test
    public void testReiniciarPuntos() {
        equipo1.agregarPuntaje(500);
        juegoSetup.agregarEquipo(equipo1);
        juegoSetup.ReiniciarPuntos();
        assertEquals(0, equipo1.getPuntajeTotal(), "El puntaje del equipo debería ser 0 después de reiniciar.");
    }

    /**
     * Prueba exitosa: Verificar si la configuración del juego está completa.
     * Verifica que se haya configurado el juez y se haya registrado el número correcto de equipos.
     */
    @Test
    public void testConfiguracionCompleta() {
        juegoSetup.configurarJuez(juez.getNombre(), juez.getCedula(), juez.getEdad(), juez.getTarjetaProfesional());
        juegoSetup.agregarEquipo(equipo1);
        juegoSetup.agregarEquipo(equipo2);
        juegoSetup.setTotalEquipos(2);
        assertTrue(juegoSetup.configuracionCompleta(), "La configuración debería estar completa.");
    }

    // --------------- PRUEBAS FALLIDAS INTENCIONALES ---------------

    /**
     * Prueba fallida intencionalmente: El nombre del juez no es el correcto.
     * Verifica incorrectamente que el nombre del juez sea el esperado.
     */
    @Test
    public void testConfigurarJuezFalloNombre() {
        juegoSetup.configurarJuez("Juez Incorrecto", juez.getCedula(), juez.getEdad(), juez.getTarjetaProfesional());
        assertEquals(juez.getNombre(), juegoSetup.getJuez().getNombre(), 
            "El nombre del juez debería coincidir, pero fallará.");
    }

    /**
     * Prueba fallida intencionalmente: La lista de equipos debería contener 1 equipo, pero verificamos si contiene 2.
     * Verifica incorrectamente que haya más equipos de los realmente registrados.
     */
    @Test
    public void testAgregarEquipoFalloCantidad() {
        juegoSetup.agregarEquipo(equipo1);
        assertEquals(2, juegoSetup.getEquipos().size(), 
            "Esta prueba está diseñada para fallar, ya que solo se agregó un equipo.");
    }

    /**
     * Prueba fallida intencionalmente: El puntaje del equipo debería ser 0 después de reiniciar, pero esperamos 500.
     * Verifica incorrectamente que el puntaje no se reinicia a 0.
     */
    @Test
    public void testReiniciarPuntosFalloPuntaje() {
        equipo1.agregarPuntaje(500);
        juegoSetup.agregarEquipo(equipo1);
        juegoSetup.ReiniciarPuntos();
        assertEquals(500, equipo1.getPuntajeTotal(), 
            "Esta prueba fallará, ya que el puntaje debería haber sido reiniciado a 0.");
    }

    /**
     * Prueba fallida intencionalmente: Verificamos si la configuración está completa antes de configurar el juez y los equipos.
     * Verifica incorrectamente que la configuración esté completa sin haber registrado el juez ni los equipos.
     */
    @Test
    public void testConfiguracionCompletaFalloSinConfigurar() {
        assertTrue(juegoSetup.configuracionCompleta(), 
            "Esta prueba fallará, ya que no se ha configurado el juez ni los equipos.");
    }
}