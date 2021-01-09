/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.awt.HeadlessException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class Costeo extends javax.swing.JFrame {

    Statement st;
    PreparedStatement ps = null;
    ResultSet rs;
    String Compra , Venta ;

    public Costeo() {
        initComponents();
        recuperacosteo();
    }

 public void actualizar(){
 try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", ""); 
            Statement st = conexion.createStatement();
            st.executeUpdate("use prueba;");

          ps = conexion.prepareStatement("UPDATE  costeo  SET  `compra`='" + txt_compra.getText().trim() + "',`venta`='" + txt_venta.getText().trim() + "' WHERE id='1'");
   
            int n = ps.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "¡Los datos han sido guardados exitósamente!");
                st.close();
                //  limpiarcampos();
            }
        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error en la base de datos" + ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Costeo.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
 
    public void confirmar() {
        int result = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres modificar el costeo?", "ATENCION",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            actualizar();
        } else if (result == JOptionPane.NO_OPTION) {
        }
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_compra = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnrefrescar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_venta = new javax.swing.JTextField();
        btnvolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cambio de Costeo");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(txt_compra, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 170, 40));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Compra:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 60, 40));

        jButton1.setBackground(new java.awt.Color(51, 255, 51));
        jButton1.setText("Actualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 170, 45));

        btnrefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh.png"))); // NOI18N
        btnrefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefrescarActionPerformed(evt);
            }
        });
        getContentPane().add(btnrefrescar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 43, 38));

        jLabel1.setText("  ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, 40, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Venta:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 50, 40));
        getContentPane().add(txt_venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 170, 40));

        btnvolver.setText("Volver");
        btnvolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvolverActionPerformed(evt);
            }
        });
        getContentPane().add(btnvolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 170, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    public void recuperacosteo() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            st = conexion.createStatement();
            st.executeUpdate("use prueba");

            //Seleccionar datos
            rs = st.executeQuery("select compra,venta from costeo where id='1' ");
            try {
                while (rs.next()) {
                    Compra = rs.getString(1);
                    Venta = rs.getString(2);
                }
                txt_compra.setText(Compra);
                txt_venta.setText(Venta);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Costeo.class.getName()).log(Level.SEVERE, null, ex);
        }


        
    }
    private void btnrefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefrescarActionPerformed
        recuperacosteo();
    }//GEN-LAST:event_btnrefrescarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (txt_compra.equals(" ")
                || txt_venta.equals(" ")) {
            JOptionPane.showMessageDialog(rootPane, "Un campo esta vacio");
        } else {
            confirmar();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnvolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvolverActionPerformed
      Principal p= new Principal();
      p.setVisible(true);
    }//GEN-LAST:event_btnvolverActionPerformed

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
            java.util.logging.Logger.getLogger(Costeo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Costeo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Costeo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Costeo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Costeo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnrefrescar;
    private javax.swing.JButton btnvolver;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txt_compra;
    private javax.swing.JTextField txt_venta;
    // End of variables declaration//GEN-END:variables
}
