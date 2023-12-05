/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
Lista de paquetes:
 */
package ud1_extra1.logica;

import java.io.File;
import java.util.ArrayList;
import ud1_extra1.dto.Archivo;
import ud1_extra1.gui.ventanas.VentanaPrincipal;

/**
 * Logica del ejercicio UD1_EXTRA1
 *
 * Hace las busquedas en disco
 *
 * @author Jose Javier BO
 */
public class Logica {
    //ATRIBUTOS

    //lista de archivos encontrados
    public static ArrayList<Archivo> listaArchivos = new ArrayList<Archivo>();

    //Referencia a la ventana principal
    public static VentanaPrincipal vp;

    /**
     * Devuelve si la ruta suministrada es directorio
     *
     * @param ruta Ruta a comprobar
     * @return True si existe y es directorio. False si no existe o no es
     * directorio
     */
    public static boolean existeDirectorio(String ruta) {
        File f = new File(ruta);
        if (f.exists() && f.isDirectory()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Busca todos los archivos de una ruta y los guarda en la listaArchivos de
     * la clase
     *
     * @param ruta La ruta en la que buscar
     */
    public static void buscarSinFiltro(String ruta) {
        //limpiar la lista
        listaArchivos.clear();
        //directorio en el que buscar
        File directorio = new File(ruta);
        //crea la lista de archivos 
        String[] lista = directorio.list();
        //Agregar un DTO del archivo a la lista por cada File
        for (String archivo : lista) {
            agregaArchivoAlResultado(new File(directorio.getAbsolutePath(), archivo));
        }
    }

    /**
     * Busca los archivos de una ruta filtrandolos por la extension
     *
     * @param ruta Ruta en la que buscar
     * @param extensionFiltro Extension a usar en el filtro
     */
    public static void buscarConFiltro(String ruta, String extensionFiltro) {
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
        Archivo arch = new Archivo(nombre, extension, tamano, esDirectorio);
        listaArchivos.add(arch);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        vp = new VentanaPrincipal();
        vp.setLocationRelativeTo(null);
        vp.setVisible(true);

    }
}
