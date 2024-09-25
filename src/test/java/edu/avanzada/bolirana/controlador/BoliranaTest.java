/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.avanzada.bolirana.controlador;

import edu.avanzada.bolirana.controlador.Bolirana;
import edu.avanzada.bolirana.controlador.ControladorJuego;
import edu.avanzada.bolirana.modelo.Equipo;
import edu.avanzada.bolirana.modelo.Juez;
import org.junit.jupiter.api.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para la clase Bolirana.
 * Esta clase contiene pruebas unitarias que verifican el comportamiento del 
 * sistema utilizando JUnit5. Algunas pruebas emplean Reflection para acceder a
 * atributos y métodos privados.
 */
class BoliranaTest {
    
    private Bolirana bolirana;  // Objeto principal que será probado
    private Juez juez;          // Juez del juego
    private List<Equipo> equipos; // Lista de equipos para las pruebas
    private ControladorJuego controladorJuego; // Controlador del juego

    /**
     * Método de configuración global antes de todas las pruebas.
     * Se ejecuta una vez al inicio y es útil para inicializar configuraciones comunes.
     */
    @BeforeAll
    static void setupGlobal() {
        System.out.println("Inicio de pruebas de la clase Bolirana.");
    }

    /**
     * Método de configuración antes de cada prueba.
     * Se ejecuta antes de cada test individual para asegurar un estado limpio.
     */
    @BeforeEach
    void setup() throws FileNotFoundException, IOException {
        juez = new Juez("Pedro", "12345", 45, "123-Tarjeta");
        equipos = new ArrayList<>();
        equipos.add(new Equipo("Equipo A"));
        equipos.add(new Equipo("Equipo B"));
        controladorJuego = new ControladorJuego(null, null);
        bolirana = new Bolirana(juez, equipos, controladorJuego);
        System.out.println("Configuración antes de cada prueba.");
    }

    /**
     * Método de limpieza después de cada prueba.
     * Este método permite limpiar recursos o restablecer estados modificados por las pruebas.
     */
    @AfterEach
    void teardown() {
        System.out.println("Limpieza después de cada prueba.");
    }

    /**
     * Método de limpieza global después de todas las pruebas.
     * Se ejecuta una vez al final de todas las pruebas de esta clase.
     */
    @AfterAll
    static void teardownGlobal() {
        System.out.println("Finalización de todas las pruebas de la clase Bolirana.");
    }

    /**
     * Utiliza Reflection para acceder al atributo privado equipoA.
     * @param bolirana Instancia de la clase Bolirana.
     * @return El equipo A.
     * @throws Exception Si ocurre un error al acceder al campo privado.
     */
    private Equipo getEquipoA(Bolirana bolirana) throws Exception {
        Field equipoAField = Bolirana.class.getDeclaredField("equipoA");
        equipoAField.setAccessible(true);  // Hacer accesible el campo privado
        return (Equipo) equipoAField.get(bolirana);
    }

    /**
     * Utiliza Reflection para acceder al atributo privado equipoB.
     * @param bolirana Instancia de la clase Bolirana.
     * @return El equipo B.
     * @throws Exception Si ocurre un error al acceder al campo privado.
     */
    private Equipo getEquipoB(Bolirana bolirana) throws Exception {
        Field equipoBField = Bolirana.class.getDeclaredField("equipoB");
        equipoBField.setAccessible(true);  // Hacer accesible el campo privado
        return (Equipo) equipoBField.get(bolirana);
    }

    /**
     * Utiliza Reflection para acceder al método privado haAlcanzadoPuntajeGanador.
     * @param bolirana Instancia de la clase Bolirana.
     * @return Verdadero si se alcanzó el puntaje ganador, falso de lo contrario.
     * @throws Exception Si ocurre un error al acceder al método privado.
     */
    private boolean haAlcanzadoPuntajeGanador(Bolirana bolirana) throws Exception {
        Method method = Bolirana.class.getDeclaredMethod("haAlcanzadoPuntajeGanador");
        method.setAccessible(true);  // Hacer accesible el método privado
        return (boolean) method.invoke(bolirana);
    }

    // --------------- PRUEBAS EXITOSAS ---------------

    /**
     * Prueba que verifica si el juego se inicia correctamente cuando hay un juez presente.
     */
    @Test
    void iniciarJuegoConJuez() {
        bolirana.iniciarJuego();
        assertTrue(bolirana.juegoActivo, "El juego debería estar activo si hay juez.");
    }

    /**
     * Prueba que verifica que los equipos sorteados son diferentes.
     * Utiliza Reflection para acceder a los equipos privados.
     */
    @Test
    void realizarSorteoEquiposDiferentes() throws Exception {
        bolirana.iniciarJuego();
        assertNotEquals(getEquipoA(bolirana), getEquipoB(bolirana), "Los equipos seleccionados deben ser diferentes.");
    }

    /**
     * Prueba que verifica que no se lanza una excepción al mostrar el equipo ganador.
     */
    @Test
    void mostrarEquipoGanador() throws Exception {
        bolirana.iniciarJuego();
        assertDoesNotThrow(() -> bolirana.mostrarEquipoGanador(), "No debería lanzar excepciones al mostrar el equipo ganador.");
    }

    /**
     * Prueba que valida que el equipo que alcanza el puntaje ganador sea correctamente identificado.
     */
    @Test
    void validarPuntajeGanador() throws Exception {
        // Usando Reflection para acceder a los controladores de los equipos
        Field controladorEquipoAField = Bolirana.class.getDeclaredField("controladorEquipoA");
        Field controladorEquipoBField = Bolirana.class.getDeclaredField("controladorEquipoB");
        controladorEquipoAField.setAccessible(true);
        controladorEquipoBField.setAccessible(true);

        Equipo equipoA = new Equipo("Equipo A");
        Equipo equipoB = new Equipo("Equipo B");

        controladorEquipoAField.set(bolirana, new ControladorEquipo(equipoA));
        controladorEquipoBField.set(bolirana, new ControladorEquipo(equipoB));

        equipoA.setPuntajeTotal(5000);  // Puntaje ganador
        equipoB.setPuntajeTotal(3000);  // Puntaje inferior

        assertTrue(haAlcanzadoPuntajeGanador(bolirana), "El equipo A debería haber alcanzado el puntaje ganador.");
    }

    /**
     * Prueba que verifica que el juego no se inicie si no hay un juez configurado.
     */
    @Test
    void juegoNoIniciaSinJuez() throws IOException {
        bolirana = new Bolirana(null, equipos, controladorJuego);
        bolirana.iniciarJuego();
        assertFalse(bolirana.juegoActivo, "El juego no debería estar activo si no hay juez.");
    }

    /**
     * Prueba que verifica que el equipo no alcanza el puntaje ganador si está por debajo del límite.
     */
    @Test
    void puntajeEquipoNoSuperaLimite() throws Exception {
        Field controladorEquipoAField = Bolirana.class.getDeclaredField("controladorEquipoA");
        controladorEquipoAField.setAccessible(true);

        Equipo equipoA = new Equipo("Equipo A");
        controladorEquipoAField.set(bolirana, new ControladorEquipo(equipoA));

        equipoA.setPuntajeTotal(1000);  // Puntaje inferior al límite
        assertFalse(haAlcanzadoPuntajeGanador(bolirana), "El equipo A no debería haber alcanzado el puntaje ganador.");
    }

    // --------------- PRUEBAS FALLIDAS (INTENCIONALES) ---------------

    /**
     * Prueba diseñada para fallar intencionalmente comparando valores incorrectos.
     */
    @Test
    void pruebaFalloIntencional1() {
        assertEquals(1, 2, "Esta prueba fallará intencionalmente.");
    }

    /**
     * Prueba que verifica incorrectamente que los equipos sorteados sean iguales (falla intencional).
     */
    @Test
    void equiposIgualesDespuesDeSorteo() throws Exception {
        bolirana.iniciarJuego();
        assertEquals(getEquipoA(bolirana), getEquipoB(bolirana), "Los equipos seleccionados deberían ser iguales (falla intencional).");
    }

    /**
     * Prueba que intenta incorrectamente verificar un puntaje ganador erróneo (falla intencional).
     */
    @Test
    void equipoGanadorNoCumplePuntaje() throws Exception {
        Field controladorEquipoAField = Bolirana.class.getDeclaredField("controladorEquipoA");
        Field controladorEquipoBField = Bolirana.class.getDeclaredField("controladorEquipoB");
        controladorEquipoAField.setAccessible(true);
        controladorEquipoBField.setAccessible(true);

        Equipo equipoA = new Equipo("Equipo A");
        Equipo equipoB = new Equipo("Equipo B");

        controladorEquipoAField.set(bolirana, new ControladorEquipo(equipoA));
        controladorEquipoBField.set(bolirana, new ControladorEquipo(equipoB));

        equipoA.setPuntajeTotal(2000);  // Puntaje inferior
        equipoB.setPuntajeTotal(3000);  // Puntaje superior

        assertTrue(haAlcanzadoPuntajeGanador(bolirana), "El equipo A debería haber alcanzado el puntaje ganador (falla intencional).");
    }

    /**
     * Prueba que verifica incorrectamente que el juego esté activo sin un juez (falla intencional).
     */
    @Test
    void juegoActivoSinJuez() throws IOException {
        bolirana = new Bolirana(null, equipos, controladorJuego);
        bolirana.iniciarJuego();
        assertTrue(bolirana.juegoActivo, "El juego debería estar activo aunque no haya juez (falla intencional).");
    }
}