/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit este template
 */
package edu.avanzada.bolirana.controlador;

import edu.avanzada.bolirana.modelo.Jugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para ControladorJugador.
 * Esta clase realiza pruebas unitarias para verificar el comportamiento de la clase ControladorJugador
 * utilizando JUnit5.
 */
public class ControladorJugadorTest {

    private ControladorJugador controladorJugador;  // Instancia del controlador a probar
    private Jugador jugador;  // Instancia de un jugador de prueba

    /**
     * Método que se ejecuta antes de cada prueba.
     * Inicializa un jugador y el controlador correspondiente.
     */
    @BeforeEach
    void setUp() {
        // Creamos un jugador antes de cada prueba
        jugador = new Jugador("Juan Pérez", "123456789", 25, 10);
        controladorJugador = new ControladorJugador(jugador);
    }

    // --------------- PRUEBAS EXITOSAS ---------------

    /**
     * Prueba que verifica que el valor del lanzamiento está dentro del rango esperado (0-300).
     */
    @Test
    void testLanzarValorDentroDelRango() {
        // Aprobada: Verificamos que el lanzamiento esté entre 0 y 300.
        int resultado = controladorJugador.lanzar();
        assertTrue(resultado >= 0 && resultado <= 300, "El puntaje está fuera del rango esperado");
    }

    /**
     * Prueba que verifica que el jugador no sea nulo.
     */
    @Test
    void testJugadorNoNulo() {
        // Aprobada: Verificamos que el jugador no sea nulo.
        assertNotNull(controladorJugador, "El jugador no debería ser nulo");
    }

    /**
     * Prueba que verifica que el nombre del jugador sea correcto.
     */
    @Test
    void testJugadorNombreCorrecto() {
        // Aprobada: Verificamos que el nombre del jugador sea correcto.
        assertEquals("Juan Pérez", jugador.getNombre(), "El nombre del jugador no es correcto");
    }

    /**
     * Prueba que verifica que la cédula del jugador sea la correcta.
     */
    @Test
    void testCedulaJugadorCorrecta() {
        // Aprobada: Verificamos que la cédula del jugador sea la correcta.
        assertEquals("123456789", jugador.getCedula(), "La cédula del jugador no es correcta");
    }

    // --------------- PRUEBAS FALLIDAS (INTENCIONALES) ---------------

    /**
     * Prueba diseñada para fallar intencionalmente.
     * Verifica que el valor del lanzamiento sea mayor que 300, lo cual nunca será cierto.
     */
    @Test
    void testLanzarValorFueraDelRangoFallido() {
        // Fallida: Intentamos verificar si el lanzamiento es mayor que 300, lo que nunca será cierto.
        int resultado = controladorJugador.lanzar();
        assertTrue(resultado > 300, "El valor debería estar fuera del rango permitido (300)");  // Este test fallará siempre
    }

    /**
     * Prueba diseñada para fallar intencionalmente.
     * Verifica incorrectamente que el nombre del jugador sea "Pedro", cuando en realidad es "Juan Pérez".
     */
    @Test
    void testNombreJugadorIncorrectoFallido() {
        // Fallida: Verificamos que el nombre del jugador sea incorrecto.
        assertEquals("Pedro", jugador.getNombre(), "El nombre del jugador debería ser Pedro");  // Este test fallará
    }

    /**
     * Prueba diseñada para fallar intencionalmente.
     * Verifica incorrectamente que la cédula del jugador sea "987654321", cuando en realidad es "123456789".
     */
    @Test
    void testCedulaJugadorIncorrectaFallido() {
        // Fallida: Verificamos que la cédula del jugador sea incorrecta.
        assertEquals("987654321", jugador.getCedula(), "La cédula del jugador debería ser 987654321");  // Este test fallará
    }

    /**
     * Prueba diseñada para fallar intencionalmente.
     * Verifica incorrectamente que el valor del lanzamiento sea negativo, lo cual nunca será cierto.
     */
    @Test
    void testLanzarValorNegativoFallido() {
        // Fallida: Verificamos si el valor del lanzamiento es negativo, lo cual nunca es el caso.
        int resultado = controladorJugador.lanzar();
        assertTrue(resultado < 0, "El valor debería ser negativo");  // Este test fallará siempre
    }
}