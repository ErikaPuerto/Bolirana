/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.avanzada.bolirana.controlador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import edu.avanzada.bolirana.modelo.Equipo;
import edu.avanzada.bolirana.modelo.Jugador;
import edu.avanzada.bolirana.controlador.ControladorEquipo;

/**
 * Clase de prueba para ControladorEquipo.
 * Esta clase contiene pruebas unitarias para verificar el comportamiento de la clase ControladorEquipo.
 * Utiliza JUnit5 para realizar las pruebas de forma automática.
 */
public class ControladorEquipoTest {

    private Equipo equipo;  // Instancia del equipo a ser controlado
    private ControladorEquipo controladorEquipo;  // Controlador que será probado

    /**
     * Configuración inicial antes de cada prueba.
     * Crea un equipo y un controlador de equipo para usar en cada prueba.
     */
    @BeforeEach
    public void setUp() {
        equipo = new Equipo("Equipo A");
        controladorEquipo = new ControladorEquipo(equipo);
        controladorEquipo.setPUNTAJE_GANADOR(5000); // Establece el puntaje necesario para ganar
    }

    /**
     * Método que se ejecuta después de cada prueba.
     * Limpia las instancias creadas para mantener independencia entre pruebas.
     */
    @AfterEach
    public void tearDown() {
        equipo = null;
        controladorEquipo = null;
    }

    // --------------- PRUEBAS EXITOSAS ---------------

    /**
     * Prueba que verifica la correcta adición de un jugador al equipo.
     * Se comprueba que el jugador ha sido agregado a la lista de jugadores.
     */
    @Test
    public void testAgregarJugador() {
        Jugador jugador = new Jugador("Juan", "123456", 25, 10);
        controladorEquipo.agregarJugador(jugador);

        // Verifica que el jugador ha sido agregado correctamente
        assertEquals(1, equipo.getJugadores().size());
    }

    /**
     * Prueba que verifica la correcta suma de puntajes al equipo.
     * Se comprueba que el puntaje total se actualiza correctamente.
     */
    @Test
    public void testAgregarPuntaje() {
        controladorEquipo.agregarPuntaje(3000);
        assertEquals(3000, controladorEquipo.getPuntajeTotal());

        controladorEquipo.agregarPuntaje(2000); // Agrega puntaje adicional
        assertEquals(5000, controladorEquipo.getPuntajeTotal());
    }

    /**
     * Prueba que verifica si un equipo ha ganado al alcanzar o superar el puntaje ganador.
     */
    @Test
    public void testHaGanado() {
        controladorEquipo.agregarPuntaje(5000);
        assertTrue(controladorEquipo.haGanado());

        controladorEquipo.agregarPuntaje(1000); // Sumar puntaje adicional
        assertTrue(controladorEquipo.haGanado());
    }

    /**
     * Prueba que verifica que un equipo no ha ganado si no ha alcanzado el puntaje necesario.
     */
    @Test
    public void testNoHaGanado() {
        controladorEquipo.agregarPuntaje(4000);
        assertFalse(controladorEquipo.haGanado());
    }

    // --------------- PRUEBAS QUE DEBERÍAN FALLAR ---------------

    /**
     * Prueba diseñada para fallar, esperando un puntaje incorrecto después de la suma.
     * Esta prueba demuestra cómo un error en la lógica afectaría el resultado.
     */
    @Test
    public void testAgregarPuntajeIncorrecto() {
        controladorEquipo.agregarPuntaje(3000);
        // Esta prueba fallará porque estamos esperando un puntaje incorrecto
        assertEquals(4000, controladorEquipo.getPuntajeTotal());
    }

    /**
     * Prueba diseñada para fallar, verificando incorrectamente si el equipo ha ganado antes de alcanzar el puntaje.
     * Se espera que esta prueba falle porque el puntaje no es suficiente.
     */
    @Test
    public void testHaGanadoIncorrecto() {
        controladorEquipo.agregarPuntaje(3000);
        // Esta prueba fallará porque el equipo aún no ha ganado
        assertTrue(controladorEquipo.haGanado());
    }

    /**
     * Prueba diseñada para fallar, esperando un valor incorrecto del puntaje total.
     * Se utiliza para simular cómo errores en la lógica de cálculo afectarían el resultado.
     */
    @Test
    public void testPuntajeTotalIncorrecto() {
        controladorEquipo.agregarPuntaje(1000);
        // Esta prueba fallará porque el puntaje no coincide
        assertEquals(5000, controladorEquipo.getPuntajeTotal());
    }
}