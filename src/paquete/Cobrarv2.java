/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AGONZALEZ
 */
public class Cobrarv2 extends javax.swing.JFrame {
 ResultSet rs;
    int count = 0;
    DefaultTableModel md;
    Statement st;
    
   

    public Cobrarv2() {

        initComponents();
        llenarcomboboxformaspago();

        CB2.setEnabled(false);
        jtxtdolares.setEnabled(false);
        jcheck2.setEnabled(false);
        CB3.setEnabled(false);
        jtxttarjeta.setEnabled(false);
    }

    public void calcularcambio() {

        double costeo = 21;

        String totalapagar = txttotalc.getText();//obtener valor total del label
        String totalapagardolares = txttotaldllsc.getText();//obtener total dolares del label

        String vartotalapagar = totalapagar.replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", "");//dejameos solo los elementos"[^0-1-2-3-4-5-6-7-8-9-.00]"
        String vartotalapagardolares = totalapagardolares.replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", "");//dejameos solo los elementos"[^0-1-2-3-4-5-6-7-8-9-.00]"

        double vtotal = Double.parseDouble(vartotalapagar);// parseamos a tipo double
        double vtotaldolares = Double.parseDouble(vartotalapagardolares);// parseamos a tipo double
        // System.out.println("valores parseados  " + "pesos: " + vtotal + " dolares: " + vtotaldolares);
        ///xxx
        //*****************************************************************************

        String fppesos = jtxtpesos.getText();
        double vfppesos = Double.parseDouble(fppesos);

        String fpdolares = jtxtdolares.getText();
        double vfpdolares = Double.parseDouble(fpdolares);
        String fptarjeta = jtxttarjeta.getText();
        double vfptarjeta = Double.parseDouble(fptarjeta);

        double totalformadepago = vfppesos + (vfpdolares * costeo) + vfptarjeta;

        double cantidadrestante = (vtotal - totalformadepago);
        txtcr.setText("" + cantidadrestante);

        if (totalformadepago >= vtotal) {

            double cambio = totalformadepago - vtotal;
            //  System.out.println("" + totalformadepago + "" + vtotal);
            txtcr.setText("0.00");

            txtc.setText("" + cambio);

            btnefectuarpago.setEnabled(true);
        } else {
            txtc.setText("0.00");
            btnefectuarpago.setEnabled(false);

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jtxtpesos = new javax.swing.JTextField();
        jcheck1 = new javax.swing.JCheckBox();
        jtxtdolares = new javax.swing.JTextField();
        jtxttarjeta = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txttotalc = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txttotaldllsc = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtcr = new javax.swing.JLabel();
        txtc = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnregresar = new javax.swing.JButton();
        btnefectuarpago = new javax.swing.JButton();
        jcheck2 = new javax.swing.JCheckBox();
        CB2 = new javax.swing.JComboBox();
        CB3 = new javax.swing.JComboBox();
        CB1 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Metodo de pago");

        jtxtpesos.setText("0");
        jtxtpesos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtpesosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtpesosFocusLost(evt);
            }
        });
        jtxtpesos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxtpesosMouseClicked(evt);
            }
        });
        jtxtpesos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtpesosActionPerformed(evt);
            }
        });
        jtxtpesos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtpesosKeyReleased(evt);
            }
        });

        jcheck1.setText("Añadir otra forma de pago");
        jcheck1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcheck1ItemStateChanged(evt);
            }
        });

        jtxtdolares.setText("0");
        jtxtdolares.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtdolaresFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtdolaresFocusLost(evt);
            }
        });
        jtxtdolares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtdolaresActionPerformed(evt);
            }
        });
        jtxtdolares.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtdolaresKeyReleased(evt);
            }
        });

        jtxttarjeta.setText("0");
        jtxttarjeta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxttarjetaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxttarjetaFocusLost(evt);
            }
        });
        jtxttarjeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxttarjetaKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("TOTAL A PAGAR:");

        txttotalc.setBackground(new java.awt.Color(255, 255, 255));
        txttotalc.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txttotalc.setText("$ 0.00");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("MXN");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("DLLS");

        txttotaldllsc.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txttotaldllsc.setText("$ 0.00");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("FALTAN");

        txtcr.setBackground(new java.awt.Color(255, 255, 255));
        txtcr.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtcr.setForeground(new java.awt.Color(245, 2, 2));
        txtcr.setText("$ 0.00");

        txtc.setBackground(new java.awt.Color(255, 255, 255));
        txtc.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtc.setText("$ 0.00");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("CAMBIO");

        btnregresar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnregresar.setForeground(new java.awt.Color(0, 128, 63));
        btnregresar.setText("REGRESAR");
        btnregresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregresarActionPerformed(evt);
            }
        });

        btnefectuarpago.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnefectuarpago.setForeground(new java.awt.Color(0, 128, 63));
        btnefectuarpago.setText("EFECTUAR PAGO");
        btnefectuarpago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnefectuarpagoActionPerformed(evt);
            }
        });

        jcheck2.setText("Añadir otra forma de pago");
        jcheck2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcheck2ItemStateChanged(evt);
            }
        });

        CB2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CB2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CB2ItemStateChanged(evt);
            }
        });

        CB3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        CB1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnregresar, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnefectuarpago, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(txtcr, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtc, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(CB3, javax.swing.GroupLayout.Alignment.LEADING, 0, 184, Short.MAX_VALUE)
                            .addComponent(CB2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtdolares, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxttarjeta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcheck2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txttotaldllsc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txttotalc, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jcheck1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                            .addComponent(CB1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addComponent(jtxtpesos, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtpesos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CB1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcheck1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtxtdolares, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CB2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jcheck2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxttarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CB3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttotalc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttotaldllsc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnefectuarpago, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnregresar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtpesosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtpesosFocusGained
        // TODO add your handling code here:
        if (jtxtpesos.getText().trim().toLowerCase().equals("0")) {
            jtxtpesos.setText("");
            jtxtpesos.setForeground(Color.BLACK);

            ///   System.out.println("focus gaina");  ////AL ESTAR EN LA CAJA ELIMINA EL CONTENIDO EN LA CAJA
        }
    }//GEN-LAST:event_jtxtpesosFocusGained

    private void jtxtpesosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtpesosFocusLost
        // TODO add your handling code here:
        if (jtxtpesos.getText().trim().equals("") || jtxtpesos.getText().trim().toLowerCase().equals("0")) {
            jtxtpesos.setText("0");//////////////////INGRESA EL 0 EN CAJA CUANDO NO ESTAS EN LA CAJA
            ///  System.out.println("focus lost");
        }
    }//GEN-LAST:event_jtxtpesosFocusLost

    private void jtxtpesosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtpesosMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==1){

        }
    }//GEN-LAST:event_jtxtpesosMouseClicked

    private void jtxtpesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtpesosActionPerformed
        // TODO add your handling code here:
        //        String valor=t1.getText();
        //jtxtpesos.setText(valor);
    }//GEN-LAST:event_jtxtpesosActionPerformed

    private void jtxtpesosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtpesosKeyReleased

        calcularcambio();
    }//GEN-LAST:event_jtxtpesosKeyReleased

    private void jcheck1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcheck1ItemStateChanged
        // TODO add your handling code here:

        if (evt.getStateChange() == ItemEvent.SELECTED) {
            CB2.setEnabled(true);
            jtxtdolares.setEnabled(true);
            jcheck2.setEnabled(true);
        } //Acción cuando es seleccionado
        else {
            jtxtdolares.setText("0");
            CB2.setEnabled(false);
            jtxtdolares.setEnabled(false);
            jcheck2.setEnabled(false);
            calcularcambio();
        }
    }//GEN-LAST:event_jcheck1ItemStateChanged

    private void jtxtdolaresFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtdolaresFocusGained
        // TODO add your handling code here:
        if (jtxtdolares.getText().trim().toLowerCase().equals("0")) {
            jtxtdolares.setText("");
            jtxtdolares.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jtxtdolaresFocusGained

    private void jtxtdolaresFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtdolaresFocusLost
        // TODO add your handling code here:
        if (jtxtdolares.getText().trim().equals("") || jtxtdolares.getText().trim().toLowerCase().equals("0")) {
            jtxtdolares.setText("0");

        }
    }//GEN-LAST:event_jtxtdolaresFocusLost

    private void jtxtdolaresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtdolaresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtdolaresActionPerformed

    private void jtxtdolaresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtdolaresKeyReleased
        calcularcambio();
    }//GEN-LAST:event_jtxtdolaresKeyReleased

    private void jtxttarjetaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxttarjetaFocusGained
        // TODO add your handling code here:

        if (jtxttarjeta.getText().trim().toLowerCase().equals("0")) {
            jtxttarjeta.setText("");
            jtxttarjeta.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jtxttarjetaFocusGained

    private void jtxttarjetaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxttarjetaFocusLost
        // TODO add your handling code here:

        if (jtxttarjeta.getText().trim().equals("") || jtxttarjeta.getText().trim().toLowerCase().equals("0")) {
            jtxttarjeta.setText("0");

        }
    }//GEN-LAST:event_jtxttarjetaFocusLost

    private void jtxttarjetaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxttarjetaKeyReleased
        // TODO add your handling code here:
        calcularcambio();
    }//GEN-LAST:event_jtxttarjetaKeyReleased

    private void btnregresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregresarActionPerformed

        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnregresarActionPerformed

    private void btnefectuarpagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnefectuarpagoActionPerformed
        // TODO add your handling code here:

        Principal.vaciartabla(new Object[]{1});//prepara el valor 1 para enviar metodo a otra clase para limpiar la tabla
        this.dispose();//cerramos la ventana
        JOptionPane.showMessageDialog(null, "USTED PAGO");
    }//GEN-LAST:event_btnefectuarpagoActionPerformed

    private void jcheck2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcheck2ItemStateChanged
        // TODO add your handling code here:
        
        
        
        if(evt.getStateChange()==ItemEvent.SELECTED){
             CB3.setEnabled(true);
            jtxttarjeta.setEnabled(true);
            //  jcheck2.setEnabled(true);
        }
        //Acción cuando es seleccionado
        else
        {
             jtxttarjeta.setText("0");
             CB3.setEnabled(false);
            jtxttarjeta.setEnabled(false);
                  calcularcambio();
        }
    }//GEN-LAST:event_jcheck2ItemStateChanged
public void llenarcomboboxformaspago(){
    CB1.removeAllItems();
    CB2.removeAllItems();
    CB3.removeAllItems();
    try {
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        java.sql.Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.80:56704", "usounds", "madljda");
        st = conexion.createStatement();
        st.executeUpdate("use cml");

            // System.out.println("llego 2");
        //Seleccionar datos
        rs = st.executeQuery("select clavepago,descripcion from formaspagos");

        try {
            while (rs.next()) {
                CB1.addItem(rs.getString(2));
                CB2.addItem(rs.getString(2));
                CB3.addItem(rs.getString(2));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    } catch (HeadlessException | NumberFormatException | SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Cobrarv2.class.getName()).log(Level.SEVERE, null, ex);
    }


   } 
    
   
    private void CB2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CB2ItemStateChanged
        // TODO add your handling code here:

        //Acción cuando no está seleccionado
    }//GEN-LAST:event_CB2ItemStateChanged

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
            java.util.logging.Logger.getLogger(Cobrarv2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cobrarv2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cobrarv2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cobrarv2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cobrarv2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CB1;
    private javax.swing.JComboBox CB2;
    private javax.swing.JComboBox CB3;
    private javax.swing.JButton btnefectuarpago;
    private javax.swing.JButton btnregresar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JCheckBox jcheck1;
    private javax.swing.JCheckBox jcheck2;
    private javax.swing.JTextField jtxtdolares;
    private javax.swing.JTextField jtxtpesos;
    private javax.swing.JTextField jtxttarjeta;
    private javax.swing.JLabel txtc;
    private javax.swing.JLabel txtcr;
    public javax.swing.JLabel txttotalc;
    public javax.swing.JLabel txttotaldllsc;
    // End of variables declaration//GEN-END:variables

     public void Enviartotal() {

        String vprecio = txttotalc.getText();//obtener valor de precio
        String vcantidad = txttotaldllsc.getText();///obtienes el valor de la cantidad
        String vprecioformateado = vprecio.replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", "");//dejameos solo los elementos"[^0-1-2-3-4-5-6-7-8-9-.00]"
        String vcantidadformateado = vcantidad.replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", "");//dejameos solo los elementos"[^0-1-2-3-4-5-6-7-8-9-.00]"

        double vprecioparseado = Double.parseDouble(vprecioformateado);
        double vpcantidadparseado = Double.parseDouble(vcantidadformateado);

        if (vprecioparseado > 0) {

            //   System.out.println("searmoelranchi");
        } else {
            //   System.out.println("ERROR VUELVA A REINTENTAR LA OPERACION NO CONTINUEE+"+vprecioparseado+vpcantidadparseado);
        }

    }
}
