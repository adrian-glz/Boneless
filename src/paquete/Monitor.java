package paquete;

import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

public class Monitor extends javax.swing.JFrame {

    public Monitor() {
        initComponents();
        Hamburguesas();
    } 
    DefaultTableModel md;
    Statement st;
    ResultSet rs;
    PreparedStatement ps = null;

     public void Hamburguesas() {
        String data[][] = {};
        String cabeza[] = {"Folio", "Codigo", "Descripcion", "Detalle","Cantidad","Articulo","Nota","Fecha","Estado","Hora"};///definimos nombre cada columna en encabezado
        jtpedidos.getTableHeader().setReorderingAllowed(false);//evitamos que no se pueda reordenar jtplatos 

        md = new DefaultTableModel(data, cabeza) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //   if (column != 4) {
                if (column != 10) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        jtpedidos.setModel(md); //igualamos en modelo en jplatos
        JTableHeader th;
        th = jtpedidos.getTableHeader();
        th.setFont(new java.awt.Font("tahoma", 0, 14));//seteamos fuente en el header
        //<Centrar el encabezado de la tabla>
        TableCellRenderer rendererFromHeader = jtpedidos.getTableHeader().getDefaultRenderer();//
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.LEFT);
        /////////////////////////////////////////////////////////>
        jtpedidos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jtpedidos.getColumnModel().getColumn(0).setPreferredWidth(60); //Matrícula
        jtpedidos.getColumnModel().getColumn(0).setMaxWidth(300);
        jtpedidos.getColumnModel().getColumn(0).setMinWidth(60);

        jtpedidos.getColumnModel().getColumn(1).setPreferredWidth(80); //Matrícula
        jtpedidos.getColumnModel().getColumn(1).setMaxWidth(300);
        jtpedidos.getColumnModel().getColumn(1).setMinWidth(80);

        jtpedidos.getColumnModel().getColumn(2).setPreferredWidth(180); //Nombre
        jtpedidos.getColumnModel().getColumn(2).setMaxWidth(300);
        jtpedidos.getColumnModel().getColumn(2).setMinWidth(180);

        jtpedidos.getColumnModel().getColumn(3).setPreferredWidth(150); //Nombre
        jtpedidos.getColumnModel().getColumn(3).setMaxWidth(200);
        jtpedidos.getColumnModel().getColumn(3).setMinWidth(150);
        
        jtpedidos.getColumnModel().getColumn(4).setPreferredWidth(60); //Nombre
        jtpedidos.getColumnModel().getColumn(4).setMaxWidth(120);
        jtpedidos.getColumnModel().getColumn(4).setMinWidth(60);
        
        jtpedidos.getColumnModel().getColumn(5).setPreferredWidth(60); //Nombre
        jtpedidos.getColumnModel().getColumn(5).setMaxWidth(120);
        jtpedidos.getColumnModel().getColumn(5).setMinWidth(60);
        
        jtpedidos.getColumnModel().getColumn(6).setPreferredWidth(120); //Nombre
        jtpedidos.getColumnModel().getColumn(6).setMaxWidth(120);
         jtpedidos.getColumnModel().getColumn(6).setMinWidth(120);

         jtpedidos.getColumnModel().getColumn(7).setPreferredWidth(120); //Nombre
         jtpedidos.getColumnModel().getColumn(7).setMaxWidth(120);
         jtpedidos.getColumnModel().getColumn(7).setMinWidth(120);

         jtpedidos.getColumnModel().getColumn(8).setPreferredWidth(120); //Nombre
         jtpedidos.getColumnModel().getColumn(8).setMaxWidth(120);
         jtpedidos.getColumnModel().getColumn(8).setMinWidth(120);

         jtpedidos.getColumnModel().getColumn(9).setPreferredWidth(120); //Nombre
         jtpedidos.getColumnModel().getColumn(9).setMaxWidth(120);
         jtpedidos.getColumnModel().getColumn(9).setMinWidth(120);
         jtpedidos.setDefaultRenderer(Object.class, new Imgtabla());
         try {
             Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            st = conexion.createStatement();
            st.executeUpdate("use prueba");

            //Seleccionar datos
            rs = st.executeQuery("select * from pedidos   ");
            md = (DefaultTableModel) jtpedidos.getModel();
            md.setRowCount(0);
            try {
                jtpedidos.setRowHeight(40);
                while (rs.next()) {
                
                    Object[] fila = (new Object[]{
                        rs.getString("Folio"),
                        rs.getString("Codigo"),
                        rs.getString("Descripcion"),
                        rs.getString("Detalle"),
                        rs.getString("Cantidad"),
                        rs.getString("Articulo"),
                        rs.getString("Nota"),
                        rs.getString("Fecha"),
                        rs.getString("Estado"),
                        rs.getString("Hora"),
                        
                       });
                    md.addRow(fila);
              
                   //   jtpedidos.setBackground(Color.decode("#058dc7"));
                    //
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtpedidos = new colorcelda();
        jPanel1 = new javax.swing.JPanel();
        btnfinalizar = new javax.swing.JToggleButton();
        btncancelar = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtpedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtpedidos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 950, 350));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnfinalizar.setText("Finalizar pedido");

        btncancelar.setText("Cancelar Pedido");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnfinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnfinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 70, 140, 110));

        jLabel1.setFont(new java.awt.Font("Gadugi", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pedidos");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 200, 50));

        jLabel2.setText("        ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 510, -1, -1));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--EN PROCESO--", "--ENTREGADO--", "--CANCELADO--" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 220, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    
 
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
            java.util.logging.Logger.getLogger(Monitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Monitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Monitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Monitor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Monitor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btncancelar;
    private javax.swing.JToggleButton btnfinalizar;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtpedidos;
    // End of variables declaration//GEN-END:variables
}
