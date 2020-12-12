/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.awt.event.WindowEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static paquete.Principal.jtfinal;

/**
 *
 * @author AGONZALEZ
 */
public class Cobrar extends javax.swing.JFrame {

//VARIABLES GLOBALES  
       String fecha=null;
    double costeo = 21.00;
    ResultSet rs;
    int count = 0;
    DefaultTableModel md;
    Statement st;
     PreparedStatement ps = null;
    public Cobrar() {

        initComponents();
        btnefectuarpago.setEnabled(false);
        String vprecio = txttotalc.getText();//obtener valor de precio
        String vcantidad = txttotaldllsc.getText();///obtienes el valor de la cantidad
        String vp = vprecio.replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", "");//dejameos solo los elementos"[^0-1-2-3-4-5-6-7-8-9-.00]"
        String vc = vcantidad.replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", "");//dejameos solo los elementos"[^0-1-2-3-4-5-6-7-8-9-.00]"

    }


   
    
public void Enviartotal(){

           
            String vprecio =  txttotalc.getText( );//obtener valor de precio
            String vcantidad =   txttotaldllsc.getText();///obtienes el valor de la cantidad
            String vprecioformateado = vprecio.replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", "");//dejameos solo los elementos"[^0-1-2-3-4-5-6-7-8-9-.00]"
            String vcantidadformateado = vcantidad.replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", "");//dejameos solo los elementos"[^0-1-2-3-4-5-6-7-8-9-.00]"

            double vprecioparseado = Double.parseDouble(vprecioformateado);
            double vpcantidadparseado = Double.parseDouble(vcantidadformateado);
            
    if(vprecioparseado>0){

     //   System.out.println("searmoelranchi");
    } 
    else{
  //   System.out.println("ERROR VUELVA A REINTENTAR LA OPERACION NO CONTINUEE+"+vprecioparseado+vpcantidadparseado);
    }
 
}


public void calcularcambio(){

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
         txtcr.setText("0.00");///TXTCAMBIORESTANTE

        txtc.setText("" + cambio);///TXTCAMBIO

        btnefectuarpago.setEnabled(true);
    } else {

        btnefectuarpago.setEnabled(false);

    }

} 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtxttarjeta = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtxtpesos = new javax.swing.JTextField();
        jtxtdolares = new javax.swing.JTextField();
        btnefectuarpago = new javax.swing.JButton();
        txttotaldllsc = new javax.swing.JLabel();
        txttotalc = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtc = new javax.swing.JLabel();
        txtcr = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnregresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("DEBITO/CREDITO");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Metodo de pago");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("$ PESOS MXN");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("$ DOLARES USA");

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

        btnefectuarpago.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnefectuarpago.setForeground(new java.awt.Color(0, 128, 63));
        btnefectuarpago.setText("EFECTUAR PAGO");
        btnefectuarpago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnefectuarpagoActionPerformed(evt);
            }
        });

        txttotaldllsc.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txttotaldllsc.setText("$ 0.00");

        txttotalc.setBackground(new java.awt.Color(255, 255, 255));
        txttotalc.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txttotalc.setText("$ 0.00");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("FALTAN");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("MXN");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("DLLS");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("TOTAL A PAGAR:");

        txtc.setBackground(new java.awt.Color(255, 255, 255));
        txtc.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtc.setText("$ 0.00");

        txtcr.setBackground(new java.awt.Color(255, 255, 255));
        txtcr.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtcr.setForeground(new java.awt.Color(245, 2, 2));
        txtcr.setText("$ 0.00");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txttotaldllsc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txttotalc, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jtxtpesos, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jtxtdolares, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jtxttarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtpesos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtxtdolares, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxttarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
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

        jtxttarjeta.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtdolaresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtdolaresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtdolaresActionPerformed

    private void jtxtpesosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtpesosKeyReleased
             calcularcambio();
    }//GEN-LAST:event_jtxtpesosKeyReleased

    private void jtxtpesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtpesosActionPerformed
        
    }//GEN-LAST:event_jtxtpesosActionPerformed

    private void jtxtpesosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtpesosMouseClicked
           if(evt.getClickCount()==1){
           }
    }//GEN-LAST:event_jtxtpesosMouseClicked

    private void jtxtdolaresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtdolaresKeyReleased
     calcularcambio();
    }//GEN-LAST:event_jtxtdolaresKeyReleased

    private void jtxttarjetaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxttarjetaKeyReleased
        // TODO add your handling code here:
        calcularcambio();
    }//GEN-LAST:event_jtxttarjetaKeyReleased

    private void btnregresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregresarActionPerformed
       
        


this.dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_btnregresarActionPerformed

    private void btnefectuarpagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnefectuarpagoActionPerformed
String cantidadpago= jtxtpesos.getText();
String cambio= txttotalc.getText();
        GregorianCalendar gg = new GregorianCalendar();
        SimpleDateFormat dd = new SimpleDateFormat("YYYY/MM/dd");
        SimpleDateFormat ddd = new SimpleDateFormat("HH:mm");

        for (int i = 0; i < jtfinal.getRowCount(); i++) {

            String prod = jtfinal.getValueAt(i, 0).toString().trim();
            double prec = Double.parseDouble(jtfinal.getValueAt(i, 1).toString().replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", ""));
            String pzs = jtfinal.getValueAt(i, 2).toString().trim();
            String deta = jtfinal.getValueAt(i, 3).toString().trim();
            System.out.println(prod + "   " + prec + "   " + pzs + "   " + deta);
            
            try {
                //Conexión a la BD   
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
                Statement st = conexion.createStatement();
                st.executeUpdate("use prueba");


                System.out.println(prec + "-" + deta + "-" + prod + "" + pzs + "" + prec + dd.format(gg.getTime()));
                ps = conexion.prepareStatement("INSERT INTO ventas ( Producto, Cantidad, Precio, Detalle, Fecha, hora) VALUES('" + prod + "', '" + pzs + "' ,'" + prec + "', '" + deta + "', '" + dd.format(gg.getTime()) + "','" + ddd.format(gg.getTime()) + "' );");
                int n = ps.executeUpdate();

                if (n > 0) {
                // ingresardatosaventaspagos(v);
                }
            } catch (HeadlessException | SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error en la base de datos");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Cobrar.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
              
       Principal.vaciartabla(new Object[]{1});//prepara el valor 1 para enviar metodo a otra clase para limpiar la tabla
       this.dispose();//cerramos la ventana
       
       
       
      
    }//GEN-LAST:event_btnefectuarpagoActionPerformed

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
         if (jtxtpesos.getText (). trim (). equals ("") || jtxtpesos.getText (). trim (). toLowerCase (). equals ("0")) {
            jtxtpesos.setText ("0");//////////////////INGRESA EL 0 EN CAJA CUANDO NO ESTAS EN LA CAJA
         ///  System.out.println("focus lost");  
        }
    }//GEN-LAST:event_jtxtpesosFocusLost

    private void jtxtdolaresFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtdolaresFocusGained
        // TODO add your handling code here:
                     if (jtxtdolares.getText (). trim (). toLowerCase (). equals ("0")) {
            jtxtdolares.setText ("");
            jtxtdolares.setForeground (Color.BLACK);
        }
    }//GEN-LAST:event_jtxtdolaresFocusGained

    private void jtxtdolaresFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtdolaresFocusLost
        // TODO add your handling code here:
               if (jtxtdolares.getText (). trim (). equals ("") || jtxtdolares.getText (). trim (). toLowerCase (). equals ("0")) {
            jtxtdolares.setText ("0");
         
        }
    }//GEN-LAST:event_jtxtdolaresFocusLost

    private void jtxttarjetaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxttarjetaFocusGained
                 if (jtxttarjeta.getText (). trim (). toLowerCase (). equals ("0")) {
            jtxttarjeta.setText ("");
            jtxttarjeta.setForeground (Color.BLACK);
        }
    }//GEN-LAST:event_jtxttarjetaFocusGained

    private void jtxttarjetaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxttarjetaFocusLost
             if (jtxttarjeta.getText (). trim (). equals ("") || jtxttarjeta.getText (). trim (). toLowerCase (). equals ("0")) {
            jtxttarjeta.setText ("0");
        }
    }//GEN-LAST:event_jtxttarjetaFocusLost

        public static void main(String args[]) {
             try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {                
                ConexionMySQL mysql = new ConexionMySQL();
                java.sql.Connection cn = mysql.Conectar();
                if (cn != null) {
                    JOptionPane.showMessageDialog(null, "Bienvenido a Punto de venta  usted esta Conectado");
                    new Cobrar().setVisible(true);                    
                    try {
                        cn.close();
                    } catch (SQLException ex) {
                        System.out.println("Error al conectar " + ex +" verifique su conexion ");
                    }
                }                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnefectuarpago;
    private javax.swing.JButton btnregresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jtxtdolares;
    private javax.swing.JTextField jtxtpesos;
    private javax.swing.JTextField jtxttarjeta;
    private javax.swing.JLabel txtc;
    private javax.swing.JLabel txtcr;
    public javax.swing.JLabel txttotalc;
    public javax.swing.JLabel txttotaldllsc;
    // End of variables declaration//GEN-END:variables

   public void ingresardatosaventaspagos( String pago, String cambio) {
    
          try { 
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
                Statement st = conexion.createStatement();
                st.executeUpdate("USE prueba");
            
    
                   ps=conexion.prepareStatement("INSERT INTO ventaspagos ( cantidadpago, cambio ) VALUES('"+ pago+"', '"+cambio+"' );");
                int n = ps.executeUpdate();
                      
     
            }catch (HeadlessException | SQLException ex) {
                    
              JOptionPane.showMessageDialog(rootPane, "Error en la base de datos");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Cobrar.class.getName()).log(Level.SEVERE, null, ex);
      
            
    }}
}
