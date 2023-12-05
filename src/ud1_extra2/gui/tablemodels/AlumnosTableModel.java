/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package ud1_extra2.gui.tablemodels;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import ud1_extra2.dto.Alumno;

/**
 * Table model para tablas que muestren archivos
 * @author Jose Javier BO
 */
public class AlumnosTableModel extends AbstractTableModel{

    

    //ATRIBUTOS:
    private final List<Alumno> listaAlumnos;//lista actual de archivos
    private final String[] columnas = new String[]{"Matricula", "Nombre alumno", "Fecha Nacimiento", "Nota Media", "Edad"};

    //METODOS:
    //Constructor
    public AlumnosTableModel(List<Alumno> listaAlumno) {
        this.listaAlumnos = listaAlumno;
    }

    @Override
    public int getRowCount() {
        return this.listaAlumnos.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value;
        switch (columnIndex) {
            case 0 -> value =listaAlumnos.get(rowIndex).getMatricula();
            case 1 -> value = listaAlumnos.get(rowIndex).getNombre();
            case 2 -> value = listaAlumnos.get(rowIndex).getFechaNacStr();
            case 3 -> value = listaAlumnos.get(rowIndex).getNota();
            case 4 -> value = listaAlumnos.get(rowIndex).getEdad();
            default -> {
                value = null;
                throw new AssertionError();
            }
        }
        return value;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?> value;
        switch (columnIndex) {
            case 0 -> value = Integer.class;
            case 1 -> value = String.class;
            case 2 -> value = String.class;
            case 3 -> value = Integer.class;
            case 4 -> value = Integer.class;
            default -> {
                value = null;
                throw new AssertionError();
            }
        }
        return value;    
    }
    
}
