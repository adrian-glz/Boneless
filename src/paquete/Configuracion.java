package paquete;

public class Configuracion extends javax.swing.JFrame {

    public Configuracion() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnagregarproducto = new javax.swing.JButton();
        Realizarcorte = new javax.swing.JButton();
        btnCambiarCosteo1 = new javax.swing.JButton();
        btnTablaMaestra = new javax.swing.JButton();
        btnFondo = new javax.swing.JButton();
        Realizarcorte1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configuracion y ajustes");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnagregarproducto.setText("Agregar producto nuevo");
        btnagregarproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarproductoActionPerformed(evt);
            }
        });
        getContentPane().add(btnagregarproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 200, 60));

        Realizarcorte.setText(" Corte");
        Realizarcorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RealizarcorteActionPerformed(evt);
            }
        });
        getContentPane().add(Realizarcorte, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 200, 60));

        btnCambiarCosteo1.setText("Dolar");
        btnCambiarCosteo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarCosteo1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCambiarCosteo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 200, 60));

        btnTablaMaestra.setText("Editar Registros");
        btnTablaMaestra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablaMaestraActionPerformed(evt);
            }
        });
        getContentPane().add(btnTablaMaestra, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 200, 60));

        btnFondo.setText("Fondo de caja");
        btnFondo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFondoActionPerformed(evt);
            }
        });
        getContentPane().add(btnFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 200, 60));

        Realizarcorte1.setText("Cancela Pedido");
        Realizarcorte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Realizarcorte1ActionPerformed(evt);
            }
        });
        getContentPane().add(Realizarcorte1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 200, 60));

        jLabel1.setText("       ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 140, 30, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void RealizarcorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RealizarcorteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RealizarcorteActionPerformed

    private void btnCambiarCosteo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarCosteo1ActionPerformed
        Costeo c = new Costeo();
        c.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCambiarCosteo1ActionPerformed

    private void btnagregarproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarproductoActionPerformed
        Agregar a = new Agregar();
        this.dispose();
        a.setVisible(true);
    }//GEN-LAST:event_btnagregarproductoActionPerformed

    private void btnTablaMaestraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablaMaestraActionPerformed
       EditaProductos t= new EditaProductos();
       t.setVisible(true);
       this.dispose();
               
    }//GEN-LAST:event_btnTablaMaestraActionPerformed

    private void btnFondoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFondoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFondoActionPerformed

    private void Realizarcorte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Realizarcorte1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Realizarcorte1ActionPerformed

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
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Configuracion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Realizarcorte;
    private javax.swing.JButton Realizarcorte1;
    private javax.swing.JButton btnCambiarCosteo1;
    private javax.swing.JButton btnFondo;
    private javax.swing.JButton btnTablaMaestra;
    private javax.swing.JButton btnagregarproducto;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
