 
package paquete;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Agregar extends javax.swing.JFrame {

    Statement st;
    PreparedStatement ps = null;
    ResultSet rs;

    public Agregar() {
        initComponents();
        btnagregar.setEnabled(false);
        llenarcategorias();
    }

    public void llenarcategorias() {
        
        cbcategorias.removeAllItems();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            st = conexion.createStatement();
            st.executeUpdate("use prueba;");
            rs = st.executeQuery("select * from categorias");
            cbcategorias.addItem("-------SELECCIONA-------");
            while (rs.next()) {
                String elemento = rs.getString(1);
                cbcategorias.addItem(elemento);
            }
            st.close();
        } catch (Exception e) {
            System.out.println("ERROR: failed to load HSQLDB JDBC driver.");
            e.printStackTrace();
            return;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbcategorias = new javax.swing.JComboBox();
        txtcodigo = new javax.swing.JTextField();
        cbcantidad = new javax.swing.JSpinner();
        txt_precio = new javax.swing.JTextField();
        btnagregar = new javax.swing.JButton();
        btnagregarcategoria = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txt_nombreproducto1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Agregar nuevo producto");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Codigo:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 50, 34));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Categoria:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 70, 35));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Cantidad:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 70, 35));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Precio:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 50, 35));

        cbcategorias.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(cbcategorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 170, 35));
        getContentPane().add(txtcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 170, 35));
        getContentPane().add(cbcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 170, 35));
        getContentPane().add(txt_precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 170, 35));

        btnagregar.setBackground(new java.awt.Color(51, 255, 51));
        btnagregar.setText("Aceptar");
        btnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarActionPerformed(evt);
            }
        });
        getContentPane().add(btnagregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 230, 40));

        btnagregarcategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/agregar1.png"))); // NOI18N
        btnagregarcategoria.setContentAreaFilled(false);
        btnagregarcategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarcategoriaActionPerformed(evt);
            }
        });
        getContentPane().add(btnagregarcategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 40, 30));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Nombre de producto:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 130, 34));
        getContentPane().add(txt_nombreproducto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 170, 35));

        jLabel1.setText("       ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 270, 40, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnagregarActionPerformed

    private void btnagregarcategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarcategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnagregarcategoriaActionPerformed
 
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
            java.util.logging.Logger.getLogger(Agregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Agregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Agregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Agregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Agregar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnagregar;
    private javax.swing.JButton btnagregarcategoria;
    private javax.swing.JSpinner cbcantidad;
    private javax.swing.JComboBox cbcategorias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txt_nombreproducto1;
    private javax.swing.JTextField txt_precio;
    private javax.swing.JTextField txtcodigo;
    // End of variables declaration//GEN-END:variables
}
