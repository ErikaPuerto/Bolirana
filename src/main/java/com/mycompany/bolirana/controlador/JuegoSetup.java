/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bolirana.controlador;

/**
 *
 * @author nedic
 */
import com.mycompany.bolirana.modelo.Bolirana;
import com.mycompany.bolirana.controlador.ControladorEquipo;
import com.mycompany.bolirana.modelo.Equipo;

import com.mycompany.bolirana.modelo.Juez;
import com.mycompany.bolirana.modelo.Jugador;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que se encarga de la configuración del juego, como la creación del juez,
 * los equipos y los jugadores. También se encarga de iniciar el juego.
 */
public class JuegoSetup {
    private Scanner scanner;
    private Juez juez;
    private List<Equipo> equipos;
    private ControladorJuez controladorJuez;

    /**
     * Constructor para crear la configuración del juego.
     * Inicializa el escáner para la entrada de datos y la lista de equipos.
     */
    public JuegoSetup() {
        this.scanner = new Scanner(System.in);
        this.equipos = new ArrayList<>(); 
    }

    /**
     * Método para configurar el juego pidiendo al usuario los datos del juez y los equipos.
     */
    public void configurarJuego() {
        configurarJuez();
        controladorJuez = new ControladorJuez(juez);
        configurarEquipos();
    }

    /**
     * Configura el juez pidiendo los datos al usuario.
     */
    private void configurarJuez() {
        System.out.println("Ingrese los datos del juez:");
        System.out.print("Nombre: ");
        String nombreJuez = scanner.nextLine();
        System.out.print("Cédula: ");
        String cedulaJuez = scanner.nextLine();
        System.out.print("Edad: ");
        int edadJuez = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.print("Tarjeta Profesional: ");
        String tarjetaProfesional = scanner.nextLine();

        juez = new Juez(nombreJuez, cedulaJuez, edadJuez, tarjetaProfesional);
    }

    
    /**
     * Configura los equipos pidiendo los datos al usuario.
     * Se permite un máximo de 6 equipos.
     */
    private void configurarEquipos() {
        System.out.print("¿Cuántos equipos participarán? (Máximo 6): ");
        int numEquipos = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        
        if (numEquipos <= 0 || numEquipos > 6) {
        throw new IllegalArgumentException("Número de equipos no válido. Debe ser entre 1 y 6.");
    }
        for (int i = 0; i < numEquipos; i++) {
            System.out.print("Ingrese el nombre del equipo " + (i + 1) + ": ");
            String nombreEquipo = scanner.nextLine();
            Equipo equipo = new Equipo(nombreEquipo);

            System.out.print("¿Cuantos jugadores tiene el equipo " + nombreEquipo + "?: ");
            int numJugadores = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            
            ControladorEquipo controladorEquipo = new ControladorEquipo(equipo); // Crear un controlador para este equipo

            for (int j = 0; j < numJugadores; j++) {
                System.out.println("Ingrese los datos del jugador " + (j + 1) + " del equipo " + nombreEquipo + ":");
                System.out.print("Nombre: ");
                String nombreJugador = scanner.nextLine();
                System.out.print("Cedula: ");
                String cedulaJugador = scanner.nextLine();
                System.out.print("Edad: ");
                int edadJugador = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                System.out.print("Posicion: ");
                String posicionJugador = scanner.nextLine();

                Jugador jugador = new Jugador(nombreJugador, cedulaJugador, edadJugador, posicionJugador);
                controladorEquipo.agregarJugador(jugador);
            }

            equipos.add(equipo);
        }
    }

    /**
     * Inicia el juego creando una instancia de Bolirana y pasando los equipos configurados.
     */
    public void iniciarJuego() {
        Bolirana bolirana = new Bolirana(juez);
        for (Equipo equipo : equipos) {
            bolirana.agregarEquipo(equipo);
        }
        bolirana.iniciarJuego();
    }
}
