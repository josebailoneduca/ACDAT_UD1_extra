/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package ud1_extra2.logica.operaciones;

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
 *
 * @author Jose Javier Bailon Ortiz
 */
public class IOBinario implements IOArchivoInterface<Alumno> {

    /**
     *
     * @param archivo
     * @return
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
     *
     * @param alumnos
     * @param archivo
     * @return
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
