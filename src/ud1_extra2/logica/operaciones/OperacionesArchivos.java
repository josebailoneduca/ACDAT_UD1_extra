/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package ud1_extra2.logica.operaciones;

import java.io.File;
import java.util.ArrayList;
import javax.swing.filechooser.FileNameExtensionFilter;
import ud1_extra2.dto.Alumno;
import static ud1_extra2.logica.operaciones.TipoArchivo.ACCESO_ALEATORIO;
import static ud1_extra2.logica.operaciones.TipoArchivo.BINARIO;
import static ud1_extra2.logica.operaciones.TipoArchivo.OBJETO;
import static ud1_extra2.logica.operaciones.TipoArchivo.TEXTO;

/**
 * Clase de funciones de acceso y lectura desde disco
 *
 * @author Jose Javier BO
 */
public class OperacionesArchivos {

    IOArchivoInterface iotexto = new IOTexto();
    IOArchivoInterface iobinario = new IOBinario();
    IOArchivoInterface ioobjeto = new IOObjeto();
    IOArchivoInterface ioaleatorio=new IOAccesoAleatorio();

    public boolean exportar(File archivo, ArrayList<Alumno> alumnos) {
        TipoArchivo tipo = getTipoArchivo(archivo);
        if (tipo == null) {
            return false;
        }
        switch (tipo) {
            case TEXTO -> {return iotexto.guardar(alumnos, archivo);}
            case BINARIO -> {return iobinario.guardar(alumnos, archivo);}
            case OBJETO -> {return ioobjeto.guardar(alumnos, archivo);}
            case ACCESO_ALEATORIO -> {return ioaleatorio.guardar(alumnos, archivo);}
            default -> {return false;}
        }
    }

    public ArrayList<Alumno> importar(File archivo) {
        TipoArchivo tipo = getTipoArchivo(archivo);
        if (tipo == null) {
            return null;
        }
        switch (tipo) {
            case TEXTO -> {return iotexto.cargar(archivo);}
            case BINARIO -> {return iobinario.cargar(archivo);}
            case OBJETO -> {return ioobjeto.cargar(archivo);}
            case ACCESO_ALEATORIO -> {return ioaleatorio.cargar(archivo);}
            default -> {return null;}
        }
    }

    public TipoArchivo getTipoArchivo(File archivo) {
        FileNameExtensionFilter tf = new FileNameExtensionFilter("Texto", "txt");
        if (tf.accept(archivo)) {
            return TipoArchivo.TEXTO;
        }
        FileNameExtensionFilter bf = new FileNameExtensionFilter("Binario", "bin");
        if (bf.accept(archivo)) {
            return TipoArchivo.BINARIO;
        }
        FileNameExtensionFilter of = new FileNameExtensionFilter("Objeto", "obj");
        if (of.accept(archivo)) {
            return TipoArchivo.OBJETO;
        }
        FileNameExtensionFilter af = new FileNameExtensionFilter("Acceso Aleatorio", "dat");
        if (af.accept(archivo)) {
            return TipoArchivo.ACCESO_ALEATORIO;
        }
        return null;
    }
}
