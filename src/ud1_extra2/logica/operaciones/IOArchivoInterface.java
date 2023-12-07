/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
Lista de paquetes:
 */
package ud1_extra2.logica.operaciones;

import java.io.File;
import java.util.ArrayList;
import ud1_extra2.dto.Alumno;

/**
 * Interfaz que especifica el contrato de las clases de lectura y escritura desde archivo
 * @author Jose Javier Bailon Ortiz
 * @param <T> Clase del objeto que maneja la interfaz
 */
public interface IOArchivoInterface<T> {
    public boolean guardar(ArrayList<T>datos,File archivo);
    public ArrayList<Alumno> cargar(File archivo);
}
