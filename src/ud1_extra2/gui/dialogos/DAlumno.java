/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
Lista de paquetes:
 */
package ud1_extra2.gui.dialogos;

import java.util.Date;
import javax.swing.JOptionPane;
import ud1_extra2.dto.Alumno;
import ud1_extra2.logica.Logica;

/**
 *
 * @author Jose Javier BO
 */
public class DAlumno extends javax.swing.JDialog {
    public static final int AGREGAR=0;
    public static final int EDITAR=1;
    Alumno alumno;
    int modo;
    /**
     * Creates new form Alumno
     */
    public DAlumno(java.awt.Frame parent, Alumno alumno, int modo) {
        super(parent, true);
        this.alumno=alumno;
        initComponents();
        this.alumno=alumno;
        this.modo=modo;
        rellenar();
    }

    
    private void rellenar() {
        inputMatricula.setText(""+alumno.getMatricula());
        inputNombre.setText(alumno.getNombre());
        if (alumno.getFechaNac()!=0)
        inputFecha.setValue(new Date(alumno.getFechaNac()));
        inputNota.setValue(alumno.getNota());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbAccion = new javax.swing.JLabel();
        pCampos = new javax.swing.JPanel();
        lbMatricula = new javax.swing.JLabel();
        inputMatricula = new javax.swing.JTextField();
        lbNombre = new javax.swing.JLabel();
        inputNombre = new javax.swing.JTextField();
        lbFecha = new javax.swing.JLabel();
        inputFecha = new javax.swing.JSpinner();
        lbNota = new javax.swing.JLabel();
        inputNota = new javax.swing.JSpinner();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        lbAccion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbAccion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAccion.setText("CREAR ALUMNO");

        pCampos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lbMatricula.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbMatricula.setText("Matricula:");
        lbMatricula.setToolTipText("");

        inputMatricula.setEditable(false);

        lbNombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbNombre.setText("Nombre:");
        lbNombre.setToolTipText("");

        lbFecha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbFecha.setText("Fecha de Nacimiento:");
        lbFecha.setToolTipText("");

        inputFecha.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, new java.util.Date(), java.util.Calendar.DAY_OF_MONTH));
        javax.swing.JSpinner.DateEditor timeEditor = new javax.swing.JSpinner.DateEditor(inputFecha, "dd/MM/yyyy");
        inputFecha.setEditor(timeEditor);

        lbNota.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbNota.setText("Nota:");
        lbNota.setToolTipText("");

        inputNota.setModel(new javax.swing.SpinnerNumberModel(1, 1, 14, 1));

        javax.swing.GroupLayout pCamposLayout = new javax.swing.GroupLayout(pCampos);
        pCampos.setLayout(pCamposLayout);
        pCamposLayout.setHorizontalGroup(
            pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCamposLayout.createSequentialGroup()
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pCamposLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputMatricula)
                            .addComponent(inputNombre)))
                    .addGroup(pCamposLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbFecha)
                            .addComponent(lbNota, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inputFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputNota))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pCamposLayout.setVerticalGroup(
            pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCamposLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMatricula)
                    .addComponent(inputMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNombre)
                    .addComponent(inputNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbFecha)
                    .addComponent(inputFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNota)
                    .addComponent(inputNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbAccion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pCampos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(btnAceptar)
                .addGap(53, 53, 53)
                .addComponent(btnCancelar)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbAccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        //recoger valores
        String nombre=this.inputNombre.getText();
        long fecha= ((Date)this.inputFecha.getValue()).getTime();
        int nota= (int) this.inputNota.getValue();
        //comprobar validez
        if (nombre.length()<2){
            JOptionPane.showMessageDialog(this.getParent(), "El nombre debe tener al menos 2 caracteres","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (nombre.length()>Alumno.RA_NOMBRE_TAMANO){
            JOptionPane.showMessageDialog(this.getParent(), "El nombre est� limitado a "+Alumno.RA_NOMBRE_TAMANO+" caracteres","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        alumno.setNombre(nombre);
        alumno.setFechaNac(fecha);
        alumno.setNota(nota);
        //salvado
        if (this.modo==DAlumno.AGREGAR)
            Logica.agregar(alumno);
        else
           Logica.editar(alumno);
        
        this.dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JSpinner inputFecha;
    private javax.swing.JTextField inputMatricula;
    private javax.swing.JTextField inputNombre;
    private javax.swing.JSpinner inputNota;
    private javax.swing.JLabel lbAccion;
    private javax.swing.JLabel lbFecha;
    private javax.swing.JLabel lbMatricula;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbNota;
    private javax.swing.JPanel pCampos;
    // End of variables declaration//GEN-END:variables

}
