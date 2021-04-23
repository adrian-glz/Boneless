/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import static paquete.Login.nombrecompleto;

/**
 * @author AGONZALEZ
 */
public class Corte extends javax.swing.JFrame {

    Statement st;
    PreparedStatement ps = null;
    ResultSet rs;
    String Fondo, Corte;
    GregorianCalendar gg = new GregorianCalendar();

    public Corte() {
        initComponents();
        imagendebarra();

        SimpleDateFormat dd = new SimpleDateFormat("yyyy/MM/dd");
        String fechadisplay = dd.format(gg.getTime());
        jtfecha.setDate(gg.getTime());
        fondo();
       
      //  imprimeconsola();
    }

    public void imagendebarra() {
        try {
            setIconImage(new ImageIcon(getClass().getResource("/img/logo.png")).getImage());
        } catch (Exception e) {

        }

    }

     

    public void corte() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = sdf.format(jtfecha.getDate());
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            st = conexion.createStatement();
            st.executeUpdate("use prueba");
            System.out.println("fecha llengado ad corte"+fecha);
            //Seleccionar datos
            rs = st.executeQuery("select sum(cantidad*precioventa) from ventas where caja='1'and fecha= '" + fecha + "'  ");
            try {
                while (rs.next()) {
                    Corte = rs.getString(1);
                    System.out.println(">>>>>>>>>" + Corte);
                }


            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Corte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void confirmar() {
        int result = JOptionPane.showConfirmDialog(null, "¿Se va a realizar el corte estas seguro, se reiniciara el contador de folio y no podras ingresar mas ventas a este dia?", "ATENCION",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
         //   imprimeconsola();
            imprimeticketcorte();
            //reiniciafolio();
        } else if (result == JOptionPane.NO_OPTION) {

        }
    }

    public void imprimeticketcorte() {
        corte();
        String user = System.getProperty("user.name");
        double total = Double.parseDouble(Corte);
        // File archivo = new File("C:\\Users\\" + user + "\\Desktop\\LEEME.txt");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = sdf.format(jtfecha.getDate());

            String cajero = nombrecompleto;
            ConexionMySQL con = new ConexionMySQL();
            Connection conn = con.Conectar();
        System.out.println("cajero::" + cajero);
          JasperReport reporte = null;
          Map parametro = new HashMap(); // MAPEO DE MAPA TIPO HASH
          parametro.put("txt_fecha", "'" +fecha+ "'");
       //   System.out.println("fecha"+fecha);
          parametro.put("txt_total",total);          
          parametro.put("txtcajero",cajero.trim());          
          String path = "C:\\Program Files\\PV\\src\\Plantillas\\Corte.jasper";
       //   String path = "C:\\Users\\"+user+"\\Documents\\NetBeansProjects\\Inventario\\src\\reportes\\Dia.jasper";
          reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
          JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, conn);
          JasperViewer view = new JasperViewer(jprint, false);
          view.setTitle("Corte");
          view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          view.setVisible(true); 
         // this.dispose();
        } catch (JRException ex) {
            Logger.getLogger(Corte.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null, ">>"+ex);
            
        }
    }
    public void reiniciafolio() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            Statement st = conexion.createStatement();
            st.executeUpdate("use prueba;");

            ps = conexion.prepareStatement("update  folios  set FOLIO = '1' where `caja`=1");

            int n = ps.executeUpdate();
            if (n > 0) {
                //   JOptionPane.showMessageDialog(null, "¡Los datos han sido guardados exitósamente!");
                st.close();

                //  limpiarcampos();
            }
        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error en la base de datos, no se pudo guardar el folio" + ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        btngenerarcorte = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnvolver = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jtfecha = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Realiza corte");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel7.setText("Fecha corte:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 100, 60));

        btngenerarcorte.setBackground(new java.awt.Color(51, 255, 51));
        btngenerarcorte.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btngenerarcorte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/procesar_1.png"))); // NOI18N
        btngenerarcorte.setText("Generar corte");
        btngenerarcorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngenerarcorteActionPerformed(evt);
            }
        });
        getContentPane().add(btngenerarcorte, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 360, 60));

        jLabel1.setText("  ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, 40, -1));

        btnvolver.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnvolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/volver.png"))); // NOI18N
        btnvolver.setText("Volver");
        btnvolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvolverActionPerformed(evt);
            }
        });
        getContentPane().add(btnvolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 360, 60));

        jLabel2.setText("           ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, -1, -1));

        jtfecha.setDateFormatString("yyyy/MM/dd");
        jtfecha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jtfecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfechaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfechaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfechaKeyTyped(evt);
            }
        });
        getContentPane().add(jtfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 270, 60));

        jLabel3.setText(" ");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 250, 30, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    public void fondo() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            st = conexion.createStatement();
            st.executeUpdate("use prueba");

            //Seleccionar datos
            rs = st.executeQuery("select fondo from folios where caja='1' ");
            try {
                while (rs.next()) {
                    Fondo = rs.getString("fondo");
                    System.out.println("metodo fondo "+Fondo);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Corte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btngenerarcorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngenerarcorteActionPerformed
        if (jtfecha.equals(" ")) {
            JOptionPane.showMessageDialog(rootPane, "Un campo esta vacio");
        } else {
            confirmar();
        }
    }//GEN-LAST:event_btngenerarcorteActionPerformed

    private void btnvolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvolverActionPerformed
        Principal p = new Principal();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnvolverActionPerformed

    private void jtfechaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfechaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jtfecha.requestFocus();
        }
    }//GEN-LAST:event_jtfechaKeyPressed

    private void jtfechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfechaKeyReleased
        /*  // TODO add your handling code here:
         String jc = txtfecha.getDateFormatString().toString();
         System.out.println("mames"+jc);
         btngenerar.setEnabled(
         jc.length() != 0
         );*/
    }//GEN-LAST:event_jtfechaKeyReleased

    private void jtfechaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfechaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfechaKeyTyped

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
            java.util.logging.Logger.getLogger(Corte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Corte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Corte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Corte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Corte().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btngenerarcorte;
    private javax.swing.JButton btnvolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private com.toedter.calendar.JDateChooser jtfecha;
    // End of variables declaration//GEN-END:variables
}
