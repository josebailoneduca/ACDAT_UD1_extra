/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package ud1_extra2.logica;

import java.io.File;
import java.util.ArrayList;
import ud1_extra2.dto.Alumno;
import ud1_extra2.gui.ventanas.VentanaPrincipal;

/**
 *
 * @author Jose Javier BO
 */
public class Logica {

    public static final int CANCELADO = 0;
    public static final int GUARDADO = 1;
    public static final int CARGADO = 1;
    public static final int ERROR = -1;
    private static VentanaPrincipal vp;
    private static int matriculaMasAlta = 0;
    private static ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
    private static OperacionesArchivo opArchivo = new OperacionesArchivo();

    private static TipoArchivo tipoActual;
    private static String rutaActual="";
    private static boolean hayCambios=false;

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            vp = new VentanaPrincipal();
            vp.setLocationRelativeTo(null);
            vp.setVisible(true);
        });
    }

    public static int getProximaMatricula() {
        return matriculaMasAlta + 1;
    }

    public static void agregar(Alumno alumno) {
        alumnos.add(alumno);
        matriculaMasAlta = alumno.getMatricula();
        hayCambios=true;
    }

    public static void editar(Alumno alumno) {
        alumnos.set(alumnos.indexOf(alumno), alumno);
        hayCambios=true;
    }

    public static ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public static Alumno getAlumno(int indice) {
        if (indice >= alumnos.size()) {
            return null;
        }
        return alumnos.get(indice);
    }

    public static boolean deleteAlumno(int indice) {
        if (indice >= alumnos.size()) {
            return false;
        }
        hayCambios=true;
        return alumnos.remove(indice) != null;
    }

    public static int guardar() {
        int resultado=CANCELADO;
        if (tipoActual==null)
                resultado= guardarNuevo();
        else
            resultado=guardarActual();
        if (resultado==GUARDADO)
            hayCambios=false;
        return resultado;
    }

    private static int guardarNuevo() {
        File archivo = vp.abrirSelectorDestino("Seleccione un destino para guardar");
        if (archivo != null) {
            if (opArchivo.exportar(archivo,alumnos)) {
                setNuevoArchivo(archivo);
                return GUARDADO;
            } else {
                return ERROR;
            }
        } else {
            return CANCELADO;
        }
    }

    private static int guardarActual() {
        if (opArchivo.exportar(new File(rutaActual),alumnos)) {
            return GUARDADO;
        } else {
            return ERROR;
        }
    }

    public static int exportar(File archivo){
        if (archivo != null) {
            if (opArchivo.exportar(archivo,alumnos)) {
                return GUARDADO;
            } else {
                return ERROR;
            }
        } else {
            return CANCELADO;
        }
    }
    
    
    private static void setNuevoArchivo(File archivo) {
        tipoActual = opArchivo.getTipoArchivo(archivo);
        rutaActual = archivo.getAbsolutePath();
         hayCambios=false;

    }

    public static String getTitulo() {
        String salida="";
        if (rutaActual.length()==0)
            salida="Nuevo";
        else
            salida=rutaActual;
        if (hayCambios)
            salida="* "+salida;
        return salida;
    }

    public static boolean hayCambios() {
        return hayCambios;
    }

    public static boolean hayDatos() {
        return alumnos!=null&&alumnos.size()>0;
    }

    public static int importar(File archivo) {
        if (archivo != null) {
            ArrayList<Alumno> cargados= opArchivo.importar(archivo);
            if (cargados!=null) {
                alumnos.clear();
                alumnos.addAll(cargados);
                setNuevoArchivo(archivo);
                return CARGADO;
            } 
        }  
        return ERROR;
    }
}
