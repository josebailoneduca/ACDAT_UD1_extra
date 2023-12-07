/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package ud1_extra2.logica.selectorarchivo;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Clase que filtra si la extension del archivo coincide con la configurada
 * en el constructor del filtro
 * @author Jose Javier Bailón Ortiz
 */
class FiltroExtension implements FilenameFilter{
    //ATRIBUTOS
    
    //filtro a comprobar
    private String ext="";
    
    
    //METODOS
    
    /**
     * Constructor
     * @param ext extensiones a permitir
     */
    public FiltroExtension(String ext){
        this.ext=ext.toLowerCase();
    }
    /**
     * Acepta el archivo si es de la extension adecuada
     * 
     * @param dir Directorio del archivo a filtrar
     * @param name nombre del archivo a filtrar
     * @return  True si el nombre termina como la extension definida, False si el nombre no termina como la extension definida
     */
    @Override
    public boolean accept(File dir, String name) {
        //Pasar a lowercase
        String nombreLC=name.toLowerCase();
        //devolver la comprobacion de la terminacion en xml
        return nombreLC.endsWith(ext);
    }
}//end FiltroXML
