/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package ud1_extra2.logica.liboperaciones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import ud1_extra2.dto.Alumno;

/**
 * Clase para cargar y guardar listados de alumnos en formato binario
 * 
 * El formato es una sucesion de fichas de alumno.
 * Cada ficha esta compuesta por la sucesion de los siguietnes valores:
 * int matricula
 * int longitud del nombre
 * char tantos como caracteres especificados en el int anterior
 * long fecha
 * int nota
 * 
 * @see IOArchivoInterface
 * @see LibOperaciones
 * @author Jose Javier Bailon Ortiz
 */
public class IOBinario implements IOArchivoInterface<Alumno> {

    /**
     * Carga el listado de alumnos desde un archivo
     * @param archivo El archivo
     * @return El listado de alumnos cargado o null si no ha podido cargarlo
     */
    @Override
    public ArrayList<Alumno> cargar(File archivo) {
        ArrayList<Alumno> salida = new ArrayList<>();

        //1- preparacion readers
        FileInputStream fis = null;
        DataInputStream dis = null;
        try {
            fis = new FileInputStream(archivo);
            dis = new DataInputStream(fis);
            
            //2- lectura
            while (true) {
                int matricula = dis.readInt();
                int longNombre=dis.readInt();
                String nombre="";
                for (int i=0;i<longNombre;i++){
                nombre+=dis.readChar();
                }
                long fecha = dis.readLong();
                int nota = dis.readInt();
                salida.add(new Alumno(matricula, nombre, fecha, nota));
            }

        } catch (FileNotFoundException fnf) {
            System.out.println("Archivo de texto no encontrado");
            return null;
        }catch (EOFException eofex){
            //GESTION DE FIN DE ARCHIVO
            System.out.println("Fin de archivo");
                    
        } 
        catch (IOException io) {
            System.out.println("Error accediendo al archivo: " + io.getMessage());
            return null;
        } finally {
            //3 - cierre de streams
            try {
                if (dis != null) {
                    dis.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                return null;
            }
        }
        return salida;
    }

    /**
     * Guarda un listado de alumnos en un archivo en formato binario
     * @param alumnos El listado de alumnos
     * @param archivo El archivo
     * @return True si los ha guradado, False si no ha podido.
     */
    @Override
    public boolean guardar(ArrayList<Alumno> alumnos, File archivo) {
        //1- preparacion writters
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        try {
            //3- Escritura del archivo
            fos = new FileOutputStream(archivo);
            dos = new DataOutputStream(fos);
            for (Alumno alumno : alumnos) {
                //matricula
                dos.writeInt(alumno.getMatricula());
                //longitudnombre
                dos.writeInt(alumno.getNombre().length());
                //nombre
                String nombre= alumno.getNombre();
                for (int i=0;i<nombre.length();i++){
                     dos.writeChar(nombre.charAt(i));
                }
                //fecha
                dos.writeLong(alumno.getFechaNac());
                //nota
                dos.writeInt(alumno.getNota());
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
                if (dos != null) {
                    dos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                return false;
            }
        }
        return true;
    }

    

}//end IOBinario
