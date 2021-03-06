/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import models.EmailReport;
import reports.TareasReport;

/**
 *
 * @author Joel
 */
public class IndexView extends javax.swing.JPanel {

    /**
     * Creates new form IndexView
     */
    public IndexView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        encabezado = new javax.swing.JLabel();
        depto = new javax.swing.JLabel();
        creditosLabel = new javax.swing.JLabel();
        directorySelected = new javax.swing.JTextField();
        examinarButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        archivos_cargados = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        continuarButton = new javax.swing.JButton();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        jFormattedTextField1.setText("jFormattedTextField1");

        encabezado.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N
        encabezado.setText("GENERADOR DE REPORTES");

        depto.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        depto.setText("Depto. de Asesores Academicos");

        creditosLabel.setForeground(new java.awt.Color(102, 102, 102));
        creditosLabel.setText("Desarrollado por: ISC. Joel Rueda Robles");

        directorySelected.setEditable(false);
        directorySelected.setFocusable(false);

        examinarButton.setText("Examinar");
        examinarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                examinarButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(archivos_cargados);

        jLabel1.setText("Archivos cargados:");

        continuarButton.setText("Continuar");
        continuarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continuarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(depto))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(directorySelected, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(examinarButton))
                            .addComponent(jLabel1)
                            .addComponent(creditosLabel)
                            .addComponent(continuarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(encabezado)
                .addGap(73, 73, 73))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(encabezado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(depto)
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(directorySelected, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(examinarButton))
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(continuarButton)
                .addGap(18, 18, 18)
                .addComponent(creditosLabel)
                .addGap(28, 28, 28))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void examinarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_examinarButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(this);

        if (result != JFileChooser.CANCEL_OPTION) {

            File file = fileChooser.getSelectedFile();

            if ((file == null) || (file.getName().equals(""))) {
                directorySelected.setText(file.getName());
            } else {

                if (file.listFiles().length != 0) {
                    directorySelected.setText(file.getAbsolutePath());
                    DefaultListModel<String> lista = new DefaultListModel<>();

                    for (File f : file.listFiles()) {
                        if (!f.isDirectory()) {
                            lista.addElement(f.getAbsolutePath());
                        }
                    }

                    archivos_cargados.setModel(lista);
                } else {
                    JOptionPane.showMessageDialog(this, "El directorio seleccionado esta vacio", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_examinarButtonActionPerformed

    private void continuarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continuarButtonActionPerformed

        if (archivos_cargados.getModel().getSize() != 0) {
            File directory = new File(directorySelected.getText());

            TareasReport tr = new TareasReport(directory);
            List<String> reportes = tr.createReportForEachAlumnos();
            List<EmailReport> emailReports = tr.createEmailReportForEachAlumnos();
            String str_reportes = "";

            for (String reporte : reportes) {
                //System.out.println(reporte);
                str_reportes += "\n" + reporte + "\n";
            }

            JOptionPane.showMessageDialog(this, "Proceso completado", "Notificacion", JOptionPane.INFORMATION_MESSAGE);

            PreviewResults pr = new PreviewResults(null, true);
            pr.setLocationRelativeTo(null);
            pr.setTextInResultTextArea(str_reportes);
            pr.setEmailReports(emailReports);
            pr.show();

        } else {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un directorio antes de continuar", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_continuarButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> archivos_cargados;
    private javax.swing.JButton continuarButton;
    private javax.swing.JLabel creditosLabel;
    private javax.swing.JLabel depto;
    private javax.swing.JTextField directorySelected;
    private javax.swing.JLabel encabezado;
    private javax.swing.JButton examinarButton;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
