/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
Lista de paquetes:
 */
package ud1_extra2.logica.selectorarchivo;

import java.io.File;
import java.util.ArrayList;
import ud1_extra2.dto.Archivo;

/**
 * Logica del ejercicio UD1_EXTRA1
 *
 * Hace las busquedas en disco
 *
 * @author Jose Javier BO
 */
public class LogicaSelectorArchivo {
    //ATRIBUTOS

    //lista de archivos encontrados
    public static ArrayList<Archivo> listaArchivos = new ArrayList<>();


    /**
     * Devuelve si la ruta suministrada es directorio
     *
     * @param ruta Ruta a comprobar
     * @return True si existe y es directorio. False si no existe o no es
     * directorio
     */
    public static boolean existeDirectorio(String ruta) {
        File f = new File(ruta);
        return f.exists() && f.isDirectory();
    }
 

    /**
     * Busca los archivos de una ruta filtrandolos por la extension
     *
     * @param ruta Ruta en la que buscar
     * @param extensionFiltro Extension a usar en el filtro
     */
    public static void buscar(String ruta, String extensionFiltro) {
        //limpiar lista de encontrados
        listaArchivos.clear();
        File directorio = new File(ruta);
        //crea la lista de archivos  usando la extension como filtro
        String[] lista = directorio.list(new FiltroExtension(extensionFiltro));
        //muestra la lista en pantalla
        for (String nombreArchivo : lista) {
            File archivo = new File(directorio.getAbsolutePath(), nombreArchivo);
            boolean esDirectorio= archivo.isDirectory();
            if (!archivo.isDirectory())
            agregaArchivoAlResultado(archivo);
        }
    }

    private static void agregaArchivoAlResultado(File file) {
        String nombreCompleto = file.getName();
        String extension = "";
        String nombre = nombreCompleto;
        long tamano = file.length();
        //si es directorio calcular extension y nombre
        boolean esDirectorio = file.isDirectory();
        if (!esDirectorio) {
            //diivision nombre.extension
            int puntoExtension = nombreCompleto.lastIndexOf(".");

            if (puntoExtension != -1) {
                nombre = nombreCompleto.substring(0, puntoExtension);
                extension = nombreCompleto.substring(puntoExtension, nombreCompleto.length());
            }
        }
        //generar DTO y agregar a la lista
        Archivo arch = new Archivo(nombre, extension, tamano, esDirectorio, file.getAbsolutePath());
        listaArchivos.add(arch);
    }
 
    public static Archivo getArchivo(int indice){
    return listaArchivos.get(indice);
    }
}
