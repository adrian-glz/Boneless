/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

/**
 *
 * @author AGONZALEZ
 */
public class Opciones extends javax.swing.JFrame {

    /**
     * Creates new form Opciones
     */
    public Opciones() {
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

        btnagregarproducto = new javax.swing.JButton();
        Realizarcorte = new javax.swing.JButton();
        btnCambiarCosteo1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnagregarproducto.setText("Agregar producto nuevo");
        getContentPane().add(btnagregarproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 200, 60));

        Realizarcorte.setText("Hacer Corte");
        Realizarcorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RealizarcorteActionPerformed(evt);
            }
        });
        getContentPane().add(Realizarcorte, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 200, 60));

        btnCambiarCosteo1.setText("Cambiar costeo");
        btnCambiarCosteo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarCosteo1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCambiarCosteo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 200, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RealizarcorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RealizarcorteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RealizarcorteActionPerformed

    private void btnCambiarCosteo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarCosteo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCambiarCosteo1ActionPerformed

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
            java.util.logging.Logger.getLogger(Opciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Opciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Opciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Opciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Opciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Realizarcorte;
    private javax.swing.JButton btnCambiarCosteo1;
    private javax.swing.JButton btnagregarproducto;
    // End of variables declaration//GEN-END:variables
}