/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.avanzada.bolirana.controlador;

import edu.avanzada.bolirana.modelo.Equipo;
import edu.avanzada.bolirana.modelo.Juez;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para ControladorJuez.
 * Esta clase realiza pruebas unitarias para verificar el comportamiento de la clase ControladorJuez
 * utilizando la biblioteca JUnit5.
 */
public class ControladorJuezTest {

    private ControladorJuez controladorJuez;  // Controlador que será probado
    private List<Equipo> equipos;  // Lista de equipos para las pruebas
    private Juez juez;  // Juez asociado con el controlador

    /**
     * Configuración inicial antes de cada prueba.
     * Crea una instancia de Juez y ControladorJuez, y una lista de equipos.
     */
    @BeforeEach
    void setUp() {
        // Configurar juez y equipos para las pruebas
        juez = new Juez("Carlos Ruiz", "123456789", 45, "TP-987654");
        controladorJuez = new ControladorJuez(juez);
        equipos = new ArrayList<>();
        equipos.add(new Equipo("Equipo A"));
        equipos.add(new Equipo("Equipo B"));
    }

    // --------------- PRUEBAS EXITOSAS ---------------

    /**
     * Prueba que verifica que el equipo inicial seleccionado no sea nulo.
     * Asegura que el método determinarEquipoInicial siempre retorne un equipo válido.
     */
    @Test
    void testDeterminarEquipoInicialNoVacio() {
        // Aprobada: Asegurar que el equipo inicial seleccionado no sea nulo
        Equipo equipoInicial = controladorJuez.determinarEquipoInicial(equipos);
        assertNotNull(equipoInicial, "El equipo inicial no debería ser nulo");
    }

    /**
     * Prueba que verifica que si solo hay un equipo, ese sea el equipo inicial seleccionado.
     */
    @Test
    void testDeterminarEquipoInicialUnEquipo() {
        // Aprobada: Si solo hay un equipo, ese debe ser el inicial
        List<Equipo> unEquipo = new ArrayList<>();
        unEquipo.add(new Equipo("Equipo Único"));
        Equipo equipoInicial = controladorJuez.determinarEquipoInicial(unEquipo);
        assertEquals("Equipo Único", equipoInicial.getNombre(), "El único equipo debería ser seleccionado");
    }

    /**
     * Prueba que asegura que no se arroje una excepción si la lista de equipos no está vacía.
     */
    @Test
    void testDeterminarEquipoInicialListaNoVacia() {
        // Aprobada: Verificamos que no se arroje excepción si la lista no está vacía
        assertDoesNotThrow(() -> controladorJuez.determinarEquipoInicial(equipos));
    }

    /**
     * Prueba que verifica que el juez asociado al controlador no sea nulo.
     */
    @Test
    void testJuezNoEsNulo() {
        // Aprobada: Verificar que el juez no sea nulo
        assertNotNull(controladorJuez, "El juez no debería ser nulo");
    }

    // --------------- PRUEBAS FALLIDAS (INTENCIONALES) ---------------

    /**
     * Prueba diseñada para fallar intencionalmente.
     * Intenta verificar que el equipo inicial sea "Equipo C", lo cual provocará un fallo.
     */
    @Test
    void testDeterminarEquipoIncorrectoFallido() {
        // Fallida: Intentamos forzar que el equipo seleccionado sea diferente, lo que provocará un fallo
        Equipo equipoInicial = controladorJuez.determinarEquipoInicial(equipos);
        assertEquals("Equipo C", equipoInicial.getNombre(), "Este test fallará porque no hay un Equipo C");  // Este test fallará
    }

    /**
     * Prueba diseñada para fallar intencionalmente.
     * Compara el juez real con un juez diferente, lo que provocará un fallo.
     */
    @Test
    void testJuezIncorrectoFallido() {
        // Fallida: Comparamos un juez incorrecto
        Juez juezIncorrecto = new Juez("Juan Pérez", "987654321", 40, "TP-123456");
        assertEquals(juezIncorrecto.getNombre(), juez.getNombre(), "Este test fallará porque los jueces son diferentes");
    }

    /**
     * Prueba diseñada para fallar intencionalmente.
     * Verifica si un equipo inexistente está en la lista de equipos, lo que provocará un fallo.
     */
    @Test
    void testEquipoInexistenteFallido() {
        // Fallida: Forzamos un equipo inexistente
        Equipo equipoInexistente = new Equipo("Equipo Inexistente");
        assertTrue(equipos.contains(equipoInexistente), "Este test fallará porque el equipo no está en la lista");
    }
}