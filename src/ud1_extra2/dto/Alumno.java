/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package ud1_extra2.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

/**
 * DTO de alumno
 *
 * @author Jose Javier BO
 */
public class Alumno implements Serializable {

    //limite de nombre a usar para la escritura del formato de acceso aleatorio
    public static final int RA_NOMBRE_TAMANO = 50;

    private int matricula;
    private String nombre;
    private long fechaNac;
    private int nota;

    public Alumno(int matricula) {
        this.matricula = matricula;
        this.nombre = "";
        this.fechaNac = System.currentTimeMillis();
        this.nota = 1;
    }

    public Alumno(int matricula, String nombre, long fecha, int nota) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.fechaNac = fecha;
        this.nota = nota;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(long fechaNac) {
        this.fechaNac = fechaNac;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    /**
     * Devuelve la fecha de nacimeinto en formato string
     * @return La fecha en formato dd/MM/yyyy
     */
    public String getFechaNacStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fechaNac);
    }

    /**
     * Calcula la edad a partir de la fecha de nacimiento
     * @return
     */
    public int getEdad() {
        Date fnac = new Date(fechaNac);
        Date ahora = new Date();
        LocalDate fnacLD = LocalDate.ofInstant(fnac.toInstant(), ZoneId.systemDefault());
        LocalDate ahoraLD = LocalDate.ofInstant(ahora.toInstant(), ZoneId.systemDefault());
        Period diff = Period.between(fnacLD, ahoraLD);
        return diff.getYears();
    }
}
