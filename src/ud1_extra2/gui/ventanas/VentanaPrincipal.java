/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
Lista de paquetes:
 */
package ud1_extra2.gui.ventanas;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableRowSorter;
import ud1_extra2.dto.Alumno;
import ud1_extra2.gui.dialogos.DAlumno;
import ud1_extra2.gui.dialogos.DSelectorArchivo;
import ud1_extra2.gui.tablemodels.AlumnosTableModel;
import ud1_extra2.logica.Logica;

/**
 * Ventana principal de la aplicación
 *
 * @author Jose Javier BO
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    //sorter de la tabla de alumnos
    TableRowSorter<AlumnosTableModel> rowSorter;

    //Ruta por defecto para dialogos de archivos
    static final String RUTA_RECURSOS = "src/ud1_extra2/recursos";

    /**
     * Creates new form NewJFrame
     */
    public VentanaPrincipal() {
        initComponents();
        //inicializar tabla
        inicializarTabla();
        //actualizar estado de botones y contenido de la tabla
        actualizarDatos();
    }
    /**
     * Inicializa la tabla asignandole modelo y sorter
     */
    private void inicializarTabla() {
        //EMPLEADOS ACTIVOS
        AlumnosTableModel tm = new AlumnosTableModel(Logica.getAlumnos());
        tblAlumnos.setModel(tm);
        //seleccionable
        tblAlumnos.setRowSelectionAllowed(true);

        //sorter
        rowSorter = new TableRowSorter<>(tm);
        tblAlumnos.setRowSorter(rowSorter);

        //ordenacion inicial
        List<SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new SortKey(0, SortOrder.ASCENDING));
        rowSorter.setSortKeys(sortKeys);
    }
    
    
    /**
     * Actualiza el estado de botones y contenido de la tabla en funcion de si
     * hay cambios en los datos o de si hay datos
     */
    private void actualizarDatos() {
        ((AlumnosTableModel) tblAlumnos.getModel()).fireTableDataChanged();
        this.setTitle(Logica.getTitulo());
        btnGuardar.setEnabled(Logica.hayCambios());
        btnExport.setEnabled(Logica.hayDatos());
        btnExportRuta.setEnabled(Logica.hayDatos());
    }

    
    
    /**
     * Devuelve el indice del alumno seleccionado en el array de alumnos
     *
     * @return la indice del alumno o -1 si no hay seleccionados
     */
    private int getIndiceAlumnoSeleccionado() {
        int seleccionado = tblAlumnos.getSelectedRow();
        if (seleccionado < 0) {
            return -1;
        }
        //extraer indice de alumno en el array
        return tblAlumnos.convertRowIndexToModel(seleccionado);
    }


    /**
     * Abre el selector de archivo para guardados
     * @param titulo Titulo a mostrar
     * @return el archivo seleccionado como ruta de guardado o null si no se ha seleccionado
     */
    public File abrirSelectorDestino(String titulo) {
        //configurar selector
        JFileChooser fc = new JFileChooser(new File(RUTA_RECURSOS));
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Texto(.txt)", "txt"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Binario(.bin)", "bin"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Objeto(.obj)", "obj"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Acceso Aleatorio(.dat)", "dat"));
        fc.setDialogTitle(titulo);

        //pedir hasta que se haya elegido uno o cancelado
        while (true) {
            int resultado = fc.showSaveDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                //comprobar extension
                File archivo = fc.getSelectedFile();
                FileNameExtensionFilter ff = (FileNameExtensionFilter) fc.getFileFilter();
                if (!ff.accept(archivo)) {
                    archivo = new File(archivo.getAbsolutePath() + "." + ff.getExtensions()[0]);
                }
                //si el archivo existe preguntamos confirmacion de sobreescritura
                if (archivo.exists() && confirmacion("El archivo existe. ¿Desea sobreescribirlo?")) {
                    return archivo;
                    //si no existe se devuelve directamente el archivo
                } else {
                    return archivo;
                }
                //si no ha elegido nada devuelve null    
            } else {
                return null;
            }
        }
    }

    /**
     * Muestra un mensaje de error
     *
     * @param msg El mensaje
     */
    private void mensajeError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un mensaje de aviso
     *
     * @param msg El mensaje
     */
    private void mensajeAviso(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Muestra un mensaje informativo
     *
     * @param msg El mensaje
     */
    private void mensajeInfo(String msg) {

        JOptionPane.showMessageDialog(this, msg, "", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Muestra un mensaje para confirmación
     * @param msg El mensaje a mostrar
     * @return  True si ha elegido SI y False en cualquier otro caso.
     */
    private boolean confirmacion(String msg) {
        return JOptionPane.showConfirmDialog(this, msg, "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    
    /**
     * Filtra el contenido de la tabla de alumnos segun el filtro elegido
     */
    private void filtrar() {
        //el indice del combobox de seleccion de tipo de filtro coincide con el indice de
        //la columna por la que se debe filtrar
        int indiceFiltro = inputSelectFiltro.getSelectedIndex();
        RowFilter<AlumnosTableModel, Integer> rf = RowFilter.regexFilter(inputFiltro.getText(), indiceFiltro);
        rowSorter.setRowFilter(rf);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pBotonera = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        lbFiltro = new javax.swing.JLabel();
        inputFiltro = new javax.swing.JTextField();
        btnQuitarFiltro = new javax.swing.JButton();
        inputSelectFiltro = new javax.swing.JComboBox<>();
        scrollTabla = new javax.swing.JScrollPane();
        tblAlumnos = new javax.swing.JTable();
        pGeneralExportImport = new javax.swing.JPanel();
        pImport = new javax.swing.JPanel();
        lbImportFichero = new javax.swing.JLabel();
        inputImportRuta = new javax.swing.JTextField();
        btnImportRuta = new javax.swing.JButton();
        btnImport = new javax.swing.JButton();
        pExport = new javax.swing.JPanel();
        lbExportFichero = new javax.swing.JLabel();
        inputExportRuta = new javax.swing.JTextField();
        btnExportRuta = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ud1_extra2/gui/imagenes/save.png"))); // NOI18N
        btnGuardar.setToolTipText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ud1_extra2/gui/imagenes/add.png"))); // NOI18N
        btnAdd.setToolTipText("Alta");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ud1_extra2/gui/imagenes/delete.png"))); // NOI18N
        btnDelete.setToolTipText("Baja");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ud1_extra2/gui/imagenes/edit.png"))); // NOI18N
        btnEdit.setToolTipText("Modificar");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        lbFiltro.setText("Filtro:");

        inputFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputFiltroKeyReleased(evt);
            }
        });

        btnQuitarFiltro.setText("X");
        btnQuitarFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarFiltroActionPerformed(evt);
            }
        });

        inputSelectFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matricula", "Nombre" }));
        inputSelectFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputSelectFiltroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pBotoneraLayout = new javax.swing.GroupLayout(pBotonera);
        pBotonera.setLayout(pBotoneraLayout);
        pBotoneraLayout.setHorizontalGroup(
            pBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBotoneraLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnGuardar)
                .addGap(30, 30, 30)
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete)
                .addGroup(pBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pBotoneraLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(lbFiltro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuitarFiltro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputSelectFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pBotoneraLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pBotoneraLayout.setVerticalGroup(
            pBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBotoneraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(inputFiltro)
                        .addComponent(inputSelectFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbFiltro)
                        .addComponent(btnQuitarFiltro))
                    .addGroup(pBotoneraLayout.createSequentialGroup()
                        .addGroup(pBotoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tblAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollTabla.setViewportView(tblAlumnos);

        pImport.setBorder(javax.swing.BorderFactory.createTitledBorder("Importar"));
        pImport.setMaximumSize(new java.awt.Dimension(1000, 32767));

        lbImportFichero.setText("Fichero:");

        inputImportRuta.setEditable(false);

        btnImportRuta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ud1_extra2/gui/imagenes/open.png"))); // NOI18N
        btnImportRuta.setMaximumSize(new java.awt.Dimension(50, 50));
        btnImportRuta.setMinimumSize(new java.awt.Dimension(50, 50));
        btnImportRuta.setPreferredSize(new java.awt.Dimension(30, 30));
        btnImportRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportRutaActionPerformed(evt);
            }
        });

        btnImport.setText("Importar");
        btnImport.setToolTipText("");
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pImportLayout = new javax.swing.GroupLayout(pImport);
        pImport.setLayout(pImportLayout);
        pImportLayout.setHorizontalGroup(
            pImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pImportLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lbImportFichero)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImport)
                    .addComponent(inputImportRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImportRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pImportLayout.setVerticalGroup(
            pImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pImportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnImportRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbImportFichero)
                        .addComponent(inputImportRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(btnImport)
                .addContainerGap())
        );

        pExport.setBorder(javax.swing.BorderFactory.createTitledBorder("Exportar"));
        pExport.setMaximumSize(new java.awt.Dimension(1000, 32767));

        lbExportFichero.setText("Fichero:");

        inputExportRuta.setEditable(false);

        btnExportRuta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ud1_extra2/gui/imagenes/open.png"))); // NOI18N
        btnExportRuta.setEnabled(false);
        btnExportRuta.setMaximumSize(new java.awt.Dimension(50, 50));
        btnExportRuta.setMinimumSize(new java.awt.Dimension(50, 50));
        btnExportRuta.setPreferredSize(new java.awt.Dimension(30, 30));
        btnExportRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportRutaActionPerformed(evt);
            }
        });

        btnExport.setText("Exportar");
        btnExport.setToolTipText("");
        btnExport.setEnabled(false);
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pExportLayout = new javax.swing.GroupLayout(pExport);
        pExport.setLayout(pExportLayout);
        pExportLayout.setHorizontalGroup(
            pExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pExportLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lbExportFichero)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExport)
                    .addComponent(inputExportRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExportRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pExportLayout.setVerticalGroup(
            pExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pExportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnExportRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbExportFichero)
                        .addComponent(inputExportRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExport)
                .addContainerGap())
        );

        javax.swing.GroupLayout pGeneralExportImportLayout = new javax.swing.GroupLayout(pGeneralExportImport);
        pGeneralExportImport.setLayout(pGeneralExportImportLayout);
        pGeneralExportImportLayout.setHorizontalGroup(
            pGeneralExportImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pGeneralExportImportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pGeneralExportImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pImport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pExport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pGeneralExportImportLayout.setVerticalGroup(
            pGeneralExportImportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pGeneralExportImportLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(pImport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollTabla)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pGeneralExportImport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
            .addComponent(pBotonera, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pBotonera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(pGeneralExportImport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Evento del boton de alta.
     * Crea un nuevo alumno vacio con la proxima matricula y lanza el dialogo de 
     * edicion de Alumno en modo AGREGAR
     * @param evt 
     */
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        DAlumno dialogo = new DAlumno(this, new Alumno(Logica.getProximaMatricula()), DAlumno.AGREGAR);
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
        actualizarDatos();
    }//GEN-LAST:event_btnAddActionPerformed

    /**
     * Evento del boton de edicion.
     * Obtiene el alumno seleccionado y abre el dialogo de edicion en modo EDITAR
     * @param evt 
     */
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        //detectar trabajo seleccionado
        int indice = getIndiceAlumnoSeleccionado();
        if (indice == -1)
            mensajeAviso("Seleccione un alumno de la tabla");
        else {

            //recoger alumno
            Alumno alumno = Logica.getAlumno(indice);
            if (alumno == null) {
                mensajeError("El alummno no existe");
                return;
            }
            //abrir dialogo de edicion
            DAlumno da = new DAlumno(this, alumno, DAlumno.EDITAR);
            da.setLocationRelativeTo(this);
            da.setVisible(true);
            actualizarDatos();
        }
    }//GEN-LAST:event_btnEditActionPerformed

    
    /**
     * Evento del boton de eliminar
     * Elimina el alumno seleccionado en la tabla
     * @param evt 
     */
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        //detectar trabajo seleccionado
        int indice = getIndiceAlumnoSeleccionado();
        if (indice == -1)
            mensajeAviso("Seleccione un alumno de la tabla");
        else {
            if (Logica.deleteAlumno(indice)) {
                mensajeInfo("Alumno eliminiado");
                actualizarDatos();
            } else {
                mensajeError("El alummno no existe");
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    /**
     * Evento del boton guardar.
     * Pide a lógica guardar el estado en memoria al archivo actual. 
     * @param evt 
     */
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        int resultado = Logica.guardar();
        switch (resultado) {
            case Logica.GUARDADO -> {
                mensajeInfo("Datos guardados");
                actualizarDatos();
            }
            case Logica.ERROR -> {
                mensajeError("No se pudo guardar");
            }
            default -> {
            }
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    /**
     * Evento del boton de importar. Lanza la importación usando la ruta
     * que hay actualmente en el campo de ruta de importar
     * @param evt 
     */
    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed
        String ruta = inputImportRuta.getText();
        if (ruta.length() == 0) {
            mensajeAviso("PreviamenteDebe seleccionar el archivo a cargar");
            return;
        }
        File seleccionado = new File(ruta);

        //si no hay cambios o confirma
        if (!Logica.hayCambios() || confirmacion("Se perderan los datos no guardados ¿Desea continuar?")) {
            if (Logica.importar(seleccionado) == Logica.CARGADO) {
                mensajeInfo("Archivo importado");
                actualizarDatos();
            } else {
                mensajeError("Error cargando " + ruta);
            }
        }
    }//GEN-LAST:event_btnImportActionPerformed

    /**
     * Evento de seleccion de ruta de importacion.
     * Muestra un dialogo DSelectorArchivo y guarda la ruta del archivo
     * seleccionado en el ampo de ruta de importar
     * @param evt 
     */
    private void btnImportRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportRutaActionPerformed
        DSelectorArchivo sa = new DSelectorArchivo(this, new File(RUTA_RECURSOS).getAbsolutePath());
        sa.setLocationRelativeTo(this);
        sa.setVisible(true);
        File seleccionado = sa.getSeleccionado();
        if (seleccionado == null) 
            return;
        
        inputImportRuta.setText(seleccionado.getAbsolutePath());
    }//GEN-LAST:event_btnImportRutaActionPerformed

    /**
     * Evento de seleccion de ruta de exportacion.
     * Muestra un dialogo JFileChooser y guarda la ruta del archivo
     * seleccionado en el campo de ruta de exportar
     * @param evt 
     */
    private void btnExportRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportRutaActionPerformed
        File seleccionado = abrirSelectorDestino("Seleccione el archivo al que exportar");
        if (seleccionado != null)
            inputExportRuta.setText(seleccionado.getAbsolutePath());
    }//GEN-LAST:event_btnExportRutaActionPerformed

    /**
     * Evento del boton de exportar.
     * Lanza la exportacion con la ruta almacenada en el campo de ruta de exportar
     * @param evt 
     */
    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        String ruta = inputExportRuta.getText();
        if (ruta.length() == 0) {
            mensajeAviso("PreviamenteDebe seleccionar el archivo al que exportar");
            return;
        }
        File seleccionado = new File(ruta);
        int resultado = Logica.exportar(seleccionado);
        switch (resultado) {
            case Logica.GUARDADO -> {
                mensajeInfo("Datos exportados a " + seleccionado.getAbsolutePath());
                actualizarDatos();
            }
            case Logica.ERROR -> {
                mensajeError("No se pudo guardar a " + seleccionado.getAbsolutePath());
            }
            default -> {
            }
        }
    }//GEN-LAST:event_btnExportActionPerformed

    /**
     * Evento de pulsacion de tecla en el campo de filtro.
     * Lanza el filtrado de la tabla de alumnos
     * @param evt 
     */
    private void inputFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputFiltroKeyReleased
        filtrar();
    }//GEN-LAST:event_inputFiltroKeyReleased

    /**
     * Evento de cambio de tipo de filtro 
     * Lanza el filtrado de la tabla de alumnos
     * @param evt 
     */
    private void inputSelectFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputSelectFiltroActionPerformed
        filtrar();
    }//GEN-LAST:event_inputSelectFiltroActionPerformed

    /**
     * Evento de limpieza de campo de filtro. Limpia el campo de filtrado
     * @param evt 
     */
    private void btnQuitarFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarFiltroActionPerformed
        inputFiltro.setText("");
        filtrar();
    }//GEN-LAST:event_btnQuitarFiltroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnExportRuta;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnImportRuta;
    private javax.swing.JButton btnQuitarFiltro;
    private javax.swing.JTextField inputExportRuta;
    private javax.swing.JTextField inputFiltro;
    private javax.swing.JTextField inputImportRuta;
    private javax.swing.JComboBox<String> inputSelectFiltro;
    private javax.swing.JLabel lbExportFichero;
    private javax.swing.JLabel lbFiltro;
    private javax.swing.JLabel lbImportFichero;
    private javax.swing.JPanel pBotonera;
    private javax.swing.JPanel pExport;
    private javax.swing.JPanel pGeneralExportImport;
    private javax.swing.JPanel pImport;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JTable tblAlumnos;
    // End of variables declaration//GEN-END:variables

}
