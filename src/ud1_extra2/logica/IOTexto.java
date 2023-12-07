/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package ud1_extra2.logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import ud1_extra2.dto.Alumno;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
public class IOTexto implements IODatosInterface<Alumno> {

    @Override
    public ArrayList<Alumno> cargar(File archivo) {
        ArrayList<Alumno> salida = new ArrayList<Alumno>();

        //1- preparacion readers
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;

            //2- lectura
            while ((linea = br.readLine()) != null) {
                salida.add(textoToAlumno(linea));
            }

        } catch (FileNotFoundException fnf) {
            System.out.println("Archivo de texto no encontrado");
            return null;
        } catch (IOException io) {
            System.out.println("Error accediendo al archivo: " + io.getMessage());
            return null;
        } finally {
            //3 - cierre de streams
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                return null;
            }
        }
        return salida;
    }

    @Override
    public boolean guardar(ArrayList<Alumno> alumnos, File archivo) {

        //1- preparacion writters
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            //2- preparar lineas
            ArrayList<String> lineas = new ArrayList<String>();
            for (Alumno alumno : alumnos) {
                lineas.add(alumnoToTexto(alumno));
            }

            //3- Escritura del archivo
            fw = new FileWriter(archivo);
            bw = new BufferedWriter(fw);

            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("Archivo no encontrado: " + fnf.getMessage());
            return false;
        } catch (IOException ioe) {
            System.out.println("Error de E/S:" + ioe.getMessage());
            return false;
        } finally {
            //4- Cierre de streams
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                return false;
            }
        }
        return true;
    }

    private String alumnoToTexto(Alumno alumno) {
        String salida = "";
        salida += alumno.getMatricula() + ";";
        salida += alumno.getNombre() + ";";
        salida += alumno.getFechaNac() + ";";
        salida += alumno.getNota() + ";";
        return salida;
    }

    private Alumno textoToAlumno(String linea) {
        StringTokenizer st = new StringTokenizer(linea, ";");
        int matricula = Integer.parseInt(st.nextToken());
        String nombre = st.nextToken();
        long fecha = Long.parseLong(st.nextToken());
        int nota = Integer.parseInt(st.nextToken());
        return new Alumno(matricula, nombre, fecha, nota);
    }

}//end IOTexto
