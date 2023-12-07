/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package ud1_extra2.logica.liboperaciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import ud1_extra2.dto.Alumno;

/**
 * Clase para cargar y guardar listados de alumnos en formato texto
 * 
 * El formato usado es una linea por alumno separando sus campos por ;
 * Las fechas estan guardadas en formato dd/MM/yyyy
 * 
 * @see IOArchivoInterface
 * @see LibOperaciones
 * @author Jose Javier Bailon Ortiz
 */
public class IOTexto implements IOArchivoInterface<Alumno> {

    /**
     * Carga el listado de alumnos desde un archivo
     * @param archivo El archivo
     * @return El listado de alumnos cargado o null si no ha podido cargarlo
     */
    @Override
    public ArrayList<Alumno> cargar(File archivo) {
        ArrayList<Alumno> salida = new ArrayList<>();

        //1- preparacion readers
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;

            //2- lectura
            while ((linea = br.readLine()) != null) {
                Alumno alumno = textoToAlumno(linea);
                if (alumno==null)
                    return null;
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

    /**
     * Guarda un listado de alumnos en un archivo en formato texto
     * @param alumnos El listado de alumnos
     * @param archivo El archivo
     * @return True si los ha guradado, False si no ha podido.
     */
    @Override
    public boolean guardar(ArrayList<Alumno> alumnos, File archivo) {

        //1- preparacion writters
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            //2- preparar lineas
            ArrayList<String> lineas = new ArrayList<>();
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

    /**
     * Transforma un alumno en una linea de texto separado por punto y coma
     * @param alumno El alumno
     * @return  La linea de texto formateada
     */
    private String alumnoToTexto(Alumno alumno) {
        String salida = "";
        salida += alumno.getMatricula() + ";";
        salida += alumno.getNombre() + ";";
        salida += new SimpleDateFormat("dd/MM/yyyy").format(new Date(alumno.getFechaNac()))+ ";";
        salida += alumno.getNota() + ";";
        return salida;
    }

    /**
     * Transforma una linea formateada por punto y coma a un DTO Alumno
     * @param linea La linea
     * @return  El alumno o null si no ha podido
     */
    private Alumno textoToAlumno(String linea) {
        StringTokenizer st = new StringTokenizer(linea, ";");
        int matricula = Integer.parseInt(st.nextToken());
        String nombre = st.nextToken();
        long fecha;
        try {
            fecha = new SimpleDateFormat("dd/MM/yyyy").parse(st.nextToken()).getTime();
        } catch (ParseException ex) {
            return null;
        }
        int nota = Integer.parseInt(st.nextToken());
        return new Alumno(matricula, nombre, fecha, nota);
    }

}//end IOTexto
