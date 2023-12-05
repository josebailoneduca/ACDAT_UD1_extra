/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package ud1_extra1.gui.tablemodels;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import ud1_extra1.dto.Archivo;

/**
 * Table model para tablas que muestren archivos
 * @author Jose Javier BO
 */
public class ArchivosTableModel extends AbstractTableModel{

    

    //ATRIBUTOS:
    private final List<Archivo> listaArchivos;//lista actual de archivos
    private final String[] columnas = new String[]{"Nombre", "Extension", "Tamaño", "D/A"};

    //METODOS:
    //Constructor
    public ArchivosTableModel(List<Archivo> listaArchivos) {
        this.listaArchivos = listaArchivos;
    }

    @Override
    public int getRowCount() {
        return this.listaArchivos.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value;
        switch (columnIndex) {
            case 0 -> value =listaArchivos.get(rowIndex).getNombre();
            case 1 -> value = listaArchivos.get(rowIndex).getExt();
            case 2 -> value = listaArchivos.get(rowIndex).getTamano();
            case 3 -> value = (listaArchivos.get(rowIndex).isEsDirectorio())?"Es directorio":"Es archivo";
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
            case 0 -> value = String.class;
            case 1 -> value = String.class;
            case 2 -> value = Long.class;
            case 3 -> value = String.class;
            default -> {
                value = null;
                throw new AssertionError();
            }
        }
        return value;    
    }
    
}
