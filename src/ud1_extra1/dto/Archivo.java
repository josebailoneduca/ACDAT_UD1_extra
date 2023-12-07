/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package ud1_extra1.dto;

/**
 * DTO para archivos
 * @author Jose Javier BO
 */
public class Archivo {
    String nombre;
    String ext;
    Long tamano;
    boolean esDirectorio;

    public Archivo(String nombre, String ext, Long tamano, boolean esDirectorio) {
        this.nombre = nombre;
        this.ext = ext;
        this.tamano = tamano;
        this.esDirectorio = esDirectorio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public long getTamano() {
        return tamano;
    }

    public void setTamano(long tamano) {
        this.tamano = tamano;
    }

    public boolean isEsDirectorio() {
        return esDirectorio;
    }

    public void setEsDirectorio(boolean esDirectorio) {
        this.esDirectorio = esDirectorio;
    }
            
}
