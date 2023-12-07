/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package ud1_extra2.logica.operaciones;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import ud1_extra2.dto.Alumno;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
public class IOObjeto implements IOArchivoInterface<Alumno> {

    @Override
    public boolean guardar(ArrayList<Alumno> alumnos, File archivo) {

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            //preparar streams
            fos = new FileOutputStream(archivo);
            oos = new ObjectOutputStream(fos);

            //escribir el objetos en disco
            for (Alumno alumno : alumnos) {
                oos.writeObject(alumno);
            }
            //tratamiento de excepciones y cierre de streams
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado " + archivo.getAbsolutePath());
            return false;
        } catch (IOException ex) {
            System.out.println("Error escribiendo a archivo " + archivo.getAbsolutePath());
            return false;
        } finally {
            try {
                if (oos != null) {
                    oos.close();
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

@Override
public ArrayList<Alumno> cargar(File archivo) {
    ArrayList<Alumno> alumnos = new ArrayList<>();
    FileInputStream fis=null;
    ObjectInputStream ois=null;
    try{
        fis=new FileInputStream(archivo);
        ois=new ObjectInputStream(fis);
        while(true){
            alumnos.add((Alumno)ois.readObject());
        }
    
    }   catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado " + archivo.getAbsolutePath());
            return null;
    }catch (EOFException eofe) {
            //FIN DE ARCHIVO
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error leyendo archivo " + archivo.getAbsolutePath());
            return null;
    }

    
    return alumnos;
    
    }

   

}//end IOObjeto
