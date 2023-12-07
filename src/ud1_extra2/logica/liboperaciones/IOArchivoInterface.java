/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
Lista de paquetes:
 */
package ud1_extra2.logica.liboperaciones;

import java.io.File;
import java.util.ArrayList;

/**
 * Interfaz que especifica el contrato de las clases de lectura y escritura desde y a archivo
 * @author Jose Javier Bailon Ortiz
 * @param <T> Clase del objeto que maneja la interfaz
 * 
 * @see IOTexto
 * @see IOBinario
 * @see IOObjeto
 * @see IOAccesoAleatorio
 * @see LibOperaciones
 */
public interface IOArchivoInterface<T> {
    public boolean guardar(ArrayList<T>datos,File archivo);
    public ArrayList<T> cargar(File archivo);
}
