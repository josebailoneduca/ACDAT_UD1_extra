/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
Lista de paquetes:
 */
package ud1_extra2.logica;

import java.io.File;
import java.util.ArrayList;
import ud1_extra2.dto.Alumno;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
public interface IODatosInterface<T> {
    public boolean guardar(ArrayList<T>datos,File archivo);
    public ArrayList<Alumno> cargar(File archivo);
}
