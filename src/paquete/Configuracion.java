package paquete;

import javax.swing.ImageIcon;

public class Configuracion extends javax.swing.JFrame {

    public Configuracion() {
        initComponents();
        imagendebarra();
    }

    public void imagendebarra() {
        try {
            setIconImage(new ImageIcon(getClass().getResource("/img/logo.png")).getImage());
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnagregarproducto = new javax.swing.JButton();
        Realizarcorte = new javax.swing.JButton();
        btnCambiarCosteo = new javax.swing.JButton();
        btnTablaMaestra = new javax.swing.JButton();
        btnFondo = new javax.swing.JButton();
        CancelarPedido = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnFondo1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configuracion y ajustes Adicionales");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnagregarproducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        btnagregarproducto.setText("Agregar producto nuevo");
        btnagregarproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarproductoActionPerformed(evt);
            }
        });
        getContentPane().add(btnagregarproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 200, 60));

        Realizarcorte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tijera.png"))); // NOI18N
        Realizarcorte.setText(" Corte");
        Realizarcorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RealizarcorteActionPerformed(evt);
            }
        });
        getContentPane().add(Realizarcorte, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 200, 60));

        btnCambiarCosteo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/verde.png"))); // NOI18N
        btnCambiarCosteo.setText("Cambiar precio Dolar");
        btnCambiarCosteo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarCosteoActionPerformed(evt);
            }
        });
        getContentPane().add(btnCambiarCosteo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 200, 60));

        btnTablaMaestra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png"))); // NOI18N
        btnTablaMaestra.setText("Editar Registros");
        btnTablaMaestra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablaMaestraActionPerformed(evt);
            }
        });
        getContentPane().add(btnTablaMaestra, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 200, 60));

        btnFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo.png"))); // NOI18N
        btnFondo.setText("Fondo de caja");
        btnFondo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFondoActionPerformed(evt);
            }
        });
        getContentPane().add(btnFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 200, 60));

        CancelarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancel.png"))); // NOI18N
        CancelarPedido.setText("Cancela Pedido");
        CancelarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarPedidoActionPerformed(evt);
            }
        });
        getContentPane().add(CancelarPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 200, 60));

        jLabel1.setText("       ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 640, 0));

        btnFondo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reimprimir.png"))); // NOI18N
        btnFondo1.setText("Reimpresion");
        btnFondo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFondo1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnFondo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 200, 60));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void RealizarcorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RealizarcorteActionPerformed
        Corte c = new Corte();
        c.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_RealizarcorteActionPerformed

    private void btnCambiarCosteoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarCosteoActionPerformed
        Costeo c = new Costeo();
        c.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCambiarCosteoActionPerformed

    private void btnagregarproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarproductoActionPerformed
        Agregar a = new Agregar();
        this.dispose();
        a.setVisible(true);
    }//GEN-LAST:event_btnagregarproductoActionPerformed

    private void btnTablaMaestraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablaMaestraActionPerformed
        EditaProductos t = new EditaProductos();
        t.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btnTablaMaestraActionPerformed

    private void btnFondoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFondoActionPerformed
        Fondo f = new Fondo();
        f.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btnFondoActionPerformed

    private void CancelarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CancelarPedidoActionPerformed

    private void btnFondo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFondo1ActionPerformed
  
    }//GEN-LAST:event_btnFondo1ActionPerformed

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
                if ("Windows".equals(info.getName())) {
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
    private javax.swing.JButton CancelarPedido;
    private javax.swing.JButton Realizarcorte;
    private javax.swing.JButton btnCambiarCosteo;
    private javax.swing.JButton btnFondo;
    private javax.swing.JButton btnFondo1;
    private javax.swing.JButton btnTablaMaestra;
    private javax.swing.JButton btnagregarproducto;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
