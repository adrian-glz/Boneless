package paquete;

import java.awt.HeadlessException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import static paquete.Login.nombrecompleto;
import static paquete.Principal.jtfinal;

/**
 *
 * @author AGONZALEZ
 */
public class Verusuarios extends javax.swing.JFrame {

    private DefaultTableModel md;
    private Statement st;
    private ResultSet rs;

    /**
     * Creates new form Verusuarios
     */
    public Verusuarios() {
        initComponents();
        llenartabla();
        imagendebarra();
    }
 public void imagendebarra() {
        try {
            setIconImage(new ImageIcon(getClass().getResource("/img/logo.png")).getImage());
        } catch (Exception e) {

        }

    }
    public void llenartabla() {

        String data[][] = {};
        String cabeza[] = {"Id", "Nombre"};///definimos nombre cada columna en encabezado
        jtverusuarios.getTableHeader().setReorderingAllowed(false);//evitamos que no se pueda reordenar jtplatos 

        md = new DefaultTableModel(data, cabeza) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //   if (column != 4) {
                if (column != 2) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        jtverusuarios.setModel(md); //igualamos en modelo en jplatos
        JTableHeader th;
        th = jtverusuarios.getTableHeader();
        th.setFont(new java.awt.Font("tahoma", 0, 14));//seteamos fuente en el header
        //<Centrar el encabezado de la tabla>
        TableCellRenderer rendererFromHeader = jtverusuarios.getTableHeader().getDefaultRenderer();//
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.LEFT);
        /////////////////////////////////////////////////////////>
        jtverusuarios.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jtverusuarios.getColumnModel().getColumn(0).setPreferredWidth(80); //Matrícula
        jtverusuarios.getColumnModel().getColumn(0).setMaxWidth(300);
        jtverusuarios.getColumnModel().getColumn(0).setMinWidth(80);

        jtverusuarios.getColumnModel().getColumn(1).setPreferredWidth(200); //Matrícula
        jtverusuarios.getColumnModel().getColumn(1).setMaxWidth(300);
        jtverusuarios.getColumnModel().getColumn(1).setMinWidth(200);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            st = conexion.createStatement();
            st.executeUpdate("use prueba");

            //Seleccionar datos
            rs = st.executeQuery("select id, nombre, apellidopaterno, apellidomaterno  from usuarios ");
            md = (DefaultTableModel) jtverusuarios.getModel();
            md.setRowCount(0);
            try {
                jtverusuarios.setRowHeight(25);
                while (rs.next()) {

                    Object[] fila = (new Object[]{rs.getString(1), rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4)});
                    md.addRow(fila);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cambiarusuario() {
        DefaultTableModel model = (DefaultTableModel) jtverusuarios.getModel();

        int filaseleccionada = jtverusuarios.getSelectedRow();//OBTIENES EL ELEMENTO DE LA TABLA
        if (filaseleccionada >= 0) {
            String seleccion = (String) (jtverusuarios.getValueAt(filaseleccionada, 1));///OBTIENES EL PRIMER FILA
            nombrecompleto = seleccion;
            Principal p = new Principal();
            p.recuperafolio();
            this.dispose();
            p.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btncambiarusuario = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtverusuarios = new javax.swing.JTable();
        btncancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cambiar Usuario");
        setResizable(false);

        btncambiarusuario.setText("Cambiar usuario");
        btncambiarusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncambiarusuarioActionPerformed(evt);
            }
        });

        jtverusuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jtverusuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtverusuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtverusuarios);

        btncancelar.setBackground(new java.awt.Color(255, 51, 51));
        btncancelar.setText("Cancelar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btncancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btncambiarusuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btncambiarusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btncancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btncambiarusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncambiarusuarioActionPerformed
        cambiarusuario();
    }//GEN-LAST:event_btncambiarusuarioActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        this.dispose();
        Principal p = new Principal();
        p.setVisible(true);

    }//GEN-LAST:event_btncancelarActionPerformed

    private void jtverusuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtverusuariosMouseClicked
        if (evt.getClickCount() == 2) {
            cambiarusuario();
        }
    }//GEN-LAST:event_jtverusuariosMouseClicked

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
            java.util.logging.Logger.getLogger(Verusuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Verusuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Verusuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Verusuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Verusuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncambiarusuario;
    private javax.swing.JButton btncancelar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtverusuarios;
    // End of variables declaration//GEN-END:variables
}
