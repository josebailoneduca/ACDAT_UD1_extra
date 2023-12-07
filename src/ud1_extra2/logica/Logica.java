/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package ud1_extra2.logica;

import ud1_extra2.logica.liboperaciones.TipoArchivo;
import ud1_extra2.logica.liboperaciones.LibOperaciones;
import java.io.File;
import java.util.ArrayList;
import ud1_extra2.dto.Alumno;
import ud1_extra2.gui.ventanas.VentanaPrincipal;

/**
 * Punto de entrada del ejercicio UD1_EXTRA_2
 *
 * Esta clase se encarga de abrir la ventana principal y llevar la logica de
 * negocio principal de la aplicación. 
 * Se sirve de la clase LibOperaciones para hacer los accesos a disco.
 *
 * Conserva una referencia al archivo al que se refieren los datos en memoria
 * actuales haciendo un seguimiento que permite guardar sobre el mismo archivo
 * al margen de la exportacion y usando automaticamente el formato que tuviese
 * originalmente.
 *
 * USO DE LA APLICACION: 
 * La aplicacion empieza con contenido vacio y sin referencia a ningun archivo. 
 * Si el usurio agrega alumnos podrá darle al botón guardar(el primero arriba a 
 * la izquierda) y se le pedirá la ruta donde quiere guardarlo. 
 * Apartir de ese momento ese será el archivo de referencia para los
 * datos que hay en memoria.
 *
 * Cuando se importa un archivo este se convierte en el archivo de referencia.
 *
 * Cuando se exporta se crea un nuevo archivo sustituyendo el que hubiese en la
 * ruta designada(cuando se selecciona la ruta de exportación se pide
 * confirmacion de la sobreescritura). La exportacion no genera que el archivo
 * se convierta en el archivo de referencia. Solo el guardado hace esa función.
 *
 * Cuando el usuario quiere importar o exportar previamente tiene que designar
 * una ruta y posteriormente pulsar el boton de importar o exportar.
 *
 *
 * ARCHIVOS DE EJEMPLO:
 * Se pueden encontrar archivos de ejemplo de todos los formatos en la ruta
 * ./src/ud1_extra2/recursos
 * 
 * 
 * @see VentanaPrincipal
 * @see LibOperaciones
 * @author Jose Javier BO
 */
public class Logica {

    //codigos de retorno para diferentes operaciones
    public static final int CANCELADO = 0;
    public static final int GUARDADO = 1;
    public static final int CARGADO = 1;
    public static final int ERROR = -1;

    //referencia a la ventana principal
    private static VentanaPrincipal vp;

    //matricula mas alta actualmetne en el sistema
    private static int matriculaMasAlta = 0;

    //lista de alumnos en memoria
    private static final ArrayList<Alumno> alumnos = new ArrayList<>();

    //referencia a la libreria de operaciones en disco
    private static final LibOperaciones libOperaciones = new LibOperaciones();

    //tipo de archivo actual
    private static TipoArchivo tipoActual;
    //ruta del archivo actual
    private static String rutaActual = "";
    //registra si hay cambios sin guardar
    private static boolean hayCambios = false;

    /**
     * Devuelve la matricula siguiente a la ultima que exite
     *
     * @return La matricula
     */
    public static int getProximaMatricula() {
        return matriculaMasAlta + 1;
    }

    /**
     * Agrega un alumno a la lista de alumnos en memoria
     * @param alumno El alumno
     */
    public static void agregar(Alumno alumno) {
        alumnos.add(alumno);
        matriculaMasAlta = alumno.getMatricula();
        hayCambios = true;
    }

    /**
     * Edita un alumno de la lista en memoria
     * @param alumno  El alumno
     */
    public static void editar(Alumno alumno) {
        alumnos.set(alumnos.indexOf(alumno), alumno);
        hayCambios = true;
    }

    /**
     *  Devuelve el listado de alumnos
     * @return El listado de alumnos
     */
    public static ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    /**
     * Devuevle un alumno según el indice que le corresponde en la lista de alumnos
     * de memoria
     * 
     * @param indice El indice
     * @return  El alumno si existe o null si no existe
     */
    public static Alumno getAlumno(int indice) {
        if (indice >= alumnos.size()) {
            return null;
        }
        return alumnos.get(indice);
    }

    /**
     * Borra un alumno de la lista de la memoria
     * @param indice Indice del alumno en la lista de la memoria
     * @return true si lo ha borrado o false si no lo ha borrado
     */
    public static boolean deleteAlumno(int indice) {
        if (indice >= alumnos.size()) {
            return false;
        }
        hayCambios = true;
        return alumnos.remove(indice) != null;
    }

    /**
     * Guarda el listado de alumnos que hay en memoria al disco en el archivo de referencia.
     * Si no hubiese archivo de referncia se le pedirá al usuario una ruta para crearlo.
     * 
     * @return 
     * Logica.GUARDADO si se ha guardado. 
     * Logica.CANCELADO si el usuario canceló el guardado. 
     * Logica.ERROR si hubo algun error en el guardado.
     */
    public static int guardar() {
        int resultado = CANCELADO;
        if (tipoActual == null) {
            resultado = guardarNuevo();
        } else {
            resultado = guardarActual();
        }
        if (resultado == GUARDADO) {
            hayCambios = false;
        }
        return resultado;
    }

    /**
     * Guarda los datos que hay en memoria creando un nuevo archivo de referencia
     * @return 
     * Logica.GUARDADO si se ha guardado. 
     * Logica.CANCELADO si el usuario canceló el guardado. 
     * Logica.ERROR si hubo algun error en el guardado.
     */
    private static int guardarNuevo() {
        File archivo = vp.abrirSelectorDestino("Seleccione un destino para guardar");
        if (archivo != null) {
            if (libOperaciones.exportar(archivo, alumnos)) {
                setNuevoArchivo(archivo);
                return GUARDADO;
            } else {
                return ERROR;
            }
        } else {
            return CANCELADO;
        }
    }

    /**
     * Guarda los datos que hay en memoria en el archivo de referencia
     * 
     * @return 
     * Logica.GUARDADO si se ha guardado. 
     * Logica.ERROR si hubo algun error en el guardado.
     */
    private static int guardarActual() {
        if (libOperaciones.exportar(new File(rutaActual), alumnos)) {
            return GUARDADO;
        } else {
            return ERROR;
        }
    }

    /**
     * Exporta los datos que hay en memoria a un archivo. 
     * 
     * @param archivo El archivo en el que guardar los datos
     * Logica.GUARDADO si se ha guardado. 
     * Logica.CANCELADO si el usuario canceló el guardado. 
     * Logica.ERROR si hubo algun error en el guardado.
     */
    public static int exportar(File archivo) {
        if (archivo != null) {
            if (libOperaciones.exportar(archivo, alumnos)) {
                return GUARDADO;
            } else {
                return ERROR;
            }
        } else {
            return CANCELADO;
        }
    }
    
    /**
     * Define un archivo como el archivo de referencia. Guardando su ruta y el
     * tipo de archivo que es.
     * @param archivo  El archivo
     */
    private static void setNuevoArchivo(File archivo) {
        tipoActual = libOperaciones.getTipoArchivo(archivo);
        rutaActual = archivo.getAbsolutePath();
        hayCambios = false;
    }

    /**
     * Devuelve el titulo a poner como titulo de la aplicacion.
     * Se compondrá de la ruta del archivo de referencia 
     * y agrega un asterisco al inicio si hay cambios sin guardar
     * 
     * @return El titulo
     */
    public static String getTitulo() {
        String salida;
        if (rutaActual.length() == 0) {
            salida = "Nuevo";
        } else {
            salida = rutaActual;
        }
        if (hayCambios) {
            salida = "* " + salida;
        }
        return salida;
    }

    /**
     * Devuelve si hay cambios sin guardar
     * @return True si hay cambios False si no hay cambios sin guardar
     */
    public static boolean hayCambios() {
        return hayCambios;
    }

    /**
     * Devuelve si hay datos en memoria sobre alumnos
     * @return True si hay al menos un alumno. False si no hay alumnos
     */
    public static boolean hayDatos() {
        return alumnos != null && !alumnos.isEmpty();
    }

    /**
     * Importa los datos de un archivo. Si tiene exito sustituye los datos 
     * que hay en memoria con los datos cargados del archivo.
     * 
     * @param archivo El archivo a cargar
     * @return
     * Logica.CARGADO si se ha guardado. 
     * Logica.ERROR si hubo algun error en el guardado.
     */
    public static int importar(File archivo) {
        if (archivo != null) {
            ArrayList<Alumno> cargados = libOperaciones.importar(archivo);
            if (cargados != null) {
                alumnos.clear();
                alumnos.addAll(cargados);
                //actualizar referencia a matricula mas alta
                if (!alumnos.isEmpty()) {
                    matriculaMasAlta = alumnos.get(alumnos.size() - 1).getMatricula();
                } else {
                    matriculaMasAlta = 0;
                }
                setNuevoArchivo(archivo);
                return CARGADO;
            }
        }
        return ERROR;
    }

    /**
     * METODO MAIN
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
}
