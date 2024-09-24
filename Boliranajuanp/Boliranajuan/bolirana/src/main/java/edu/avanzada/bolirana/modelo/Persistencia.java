/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avanzada.bolirana.modelo;

import edu.avanzada.bolirana.controlador.JuegoSetup;
import edu.avanzada.bolirana.modelo.Equipo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author anaro
 */
public class Persistencia {

    public void GuardarJuego(JuegoSetup juegoSetup) throws FileNotFoundException, IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("DatosJuego.dat");
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(juegoSetup);
        }
    }

    public JuegoSetup SacarJuego() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("DatosJuego.dat");
        JuegoSetup juegoSetupenfichero;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            juegoSetupenfichero = (JuegoSetup) objectInputStream.readObject();
        }
        return juegoSetupenfichero;

    }

    public void GuardarEquiposGanadores(Equipo equipo) throws IOException, ClassNotFoundException {
        ArrayList<Equipo> equiposgan = new ArrayList<Equipo>();
        if (SacarEquiposGanadores() != null) {
            equiposgan = SacarEquiposGanadores();
        }
        equiposgan.add(equipo);
        FileOutputStream fileOutputStream = new FileOutputStream("EquiposGanadores.dat");
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(equiposgan);
        }
    }

    public ArrayList<Equipo> SacarEquiposGanadores() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("EquiposGanadores.dat");
        ArrayList<Equipo> EquiposGan;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            EquiposGan = (ArrayList) objectInputStream.readObject();
            
        }
        return EquiposGan;
    }

}
