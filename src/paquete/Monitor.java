package paquete;

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
        imagendebarra();
        Pedidos();
    }
    DefaultTableModel md;
    Statement st;
    ResultSet rs;
    PreparedStatement ps = null;

    public void Vaciartabla() {
        DefaultTableModel vt = (DefaultTableModel) jtpedidos.getModel();
        for (int i = vt.getRowCount() - 1; i >= 0; i--) {
            vt.removeRow(i);
        }

    }

    public void Pedidos() {
        String data[][] = {};
        String cabeza[] = {"Folio", "Codigo", "Descripcion", "Detalle", "Cantidad", "Articulo", "Nota", "Fecha", "Estado", "Hora"};///definimos nombre cada columna en encabezado
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
            String estado = (String) cbestado.getSelectedItem().toString().replaceAll("[-]", "");;
            System.out.println("estado: >>>>>>>>>>>>" + estado);
            //Seleccionar datos
            rs = st.executeQuery("select * from pedidos where estado='" + estado + "'");
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
                        rs.getString("Hora"),});
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

    public void imagendebarra() {
        try {
            setIconImage(new ImageIcon(getClass().getResource("/img/logo.png")).getImage());
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtpedidos = new colorcelda();
        jPanel1 = new javax.swing.JPanel();
        btncancelar = new javax.swing.JButton();
        btnfinalizar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cbestado = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pedidos");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtpedidos.setAutoCreateRowSorter(true);
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

        btncancelar.setText("Cancelar articulo ");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        btnfinalizar.setText("Finalizar pedido");
        btnfinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfinalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnfinalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btncancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnfinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 70, -1, 160));

        jLabel2.setText("        ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 510, 520, -1));

        cbestado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbestado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--EN PROCESO--", "--ENTREGADO--", "--CANCELADO--" }));
        cbestado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbestadoActionPerformed(evt);
            }
        });
        getContentPane().add(cbestado, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 150, 50));

        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 140, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbestadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbestadoActionPerformed
        Pedidos();
    }//GEN-LAST:event_cbestadoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Principal p = new Principal();
        this.dispose();
        p.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnfinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfinalizarActionPerformed
        entregado();
    }//GEN-LAST:event_btnfinalizarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        cancelado();
    }//GEN-LAST:event_btncancelarActionPerformed
    public void entregado() {

       int filaseleccionada = jtpedidos.getSelectedRow();

            if (filaseleccionada >= 0) {

                String vfolio = (jtpedidos.getValueAt(filaseleccionada, 0).toString().trim());
                String vcodigo = (jtpedidos.getValueAt(filaseleccionada, 1).toString().trim());
                String vdescripcion = (jtpedidos.getValueAt(filaseleccionada, 2).toString().trim());
                String vdetalle = (jtpedidos.getValueAt(filaseleccionada, 3).toString().trim());
                String vcantidad = (jtpedidos.getValueAt(filaseleccionada, 4).toString().trim());
                String varticulo = (jtpedidos.getValueAt(filaseleccionada, 5).toString().trim());
                String vnota = (jtpedidos.getValueAt(filaseleccionada, 6).toString().trim());
                String vfecha = (jtpedidos.getValueAt(filaseleccionada, 7).toString().trim());
                String vestado = (jtpedidos.getValueAt(filaseleccionada, 8).toString().trim());
                String vhora = (jtpedidos.getValueAt(filaseleccionada, 9).toString().trim());

                //    JOptionPane.showMessageDialog(null, "SELECCIONASTE: " + itemcodigo);
                // System.out.println("este mero wee->>>>"+i);
                //
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
                    st = conexion.createStatement();
                    st.executeUpdate("use prueba");

                    String updateTableSQL = ("UPDATE pedidos SET estado='ENTREGADO' WHERE  folio='" + vfolio + "' and detalle='" + vdetalle + "'  and cantidad='" + vcantidad + "' and articulo='" + varticulo + "'    and fecha='" + vfecha + "' and estado='" + vestado + "'               ;");
                    PreparedStatement preparedStatement = conexion.prepareStatement(updateTableSQL);
                    preparedStatement.executeUpdate();
                    int n = preparedStatement.executeUpdate();

                    if (n > 0) {
                        JOptionPane.showMessageDialog(null, "¡Los datos han sido guardados exitósamente!" + updateTableSQL);
                    } else {

                    }
                    st.close();
                    Vaciartabla();
                    Pedidos();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "==" + ex);
                    Vaciartabla();
                    Pedidos();
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "==" + ex);
                }
                //

            } else {
                JOptionPane.showMessageDialog(null, "Selecciona una un registro");
            }
}
    
    public void cancelado(){
       int filaseleccionada = jtpedidos.getSelectedRow();

           if (filaseleccionada >= 0) {

               String vfolio = (jtpedidos.getValueAt(filaseleccionada, 0).toString().trim());
               String vcodigo = (jtpedidos.getValueAt(filaseleccionada, 1).toString().trim());
               String vdescripcion = (jtpedidos.getValueAt(filaseleccionada, 2).toString().trim());
               String vdetalle = (jtpedidos.getValueAt(filaseleccionada, 3).toString().trim());
               String vcantidad = (jtpedidos.getValueAt(filaseleccionada, 4).toString().trim());
               String varticulo = (jtpedidos.getValueAt(filaseleccionada, 5).toString().trim());
               String vnota = (jtpedidos.getValueAt(filaseleccionada, 6).toString().trim());
               String vfecha = (jtpedidos.getValueAt(filaseleccionada, 7).toString().trim());
               String vestado = (jtpedidos.getValueAt(filaseleccionada, 8).toString().trim());
               String vhora = (jtpedidos.getValueAt(filaseleccionada, 9).toString().trim());

               //    JOptionPane.showMessageDialog(null, "SELECCIONASTE: " + itemcodigo);
               // System.out.println("este mero wee->>>>"+i);
               //
               try {
                   Class.forName("com.mysql.jdbc.Driver");
                   java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
                   st = conexion.createStatement();
                   st.executeUpdate("use prueba");
                   String negativo="-"+vcantidad.trim();
                   String updateTableSQL = ("UPDATE pedidos SET estado='CANCELADO',cantidad ='"+negativo+" ' WHERE codigo='" + vcodigo + "'  and  folio='" + vfolio + "' and codigo='" + vcodigo + "'   and descripcion='" + vdescripcion + "' and detalle='" + vdetalle + "'  and cantidad='" + vcantidad + "' and articulo='" + varticulo + "'  and nota='" + vnota + "' and fecha='" + vfecha + "' and estado='" + vestado + "'  and hora='" + vhora + "'             ;");
                   PreparedStatement preparedStatement = conexion.prepareStatement(updateTableSQL);
                   preparedStatement.executeUpdate();
                   int n = preparedStatement.executeUpdate();

                   if (n > 0) {
                       JOptionPane.showMessageDialog(null, "¡Los datos han sido guardados exitósamente!" + updateTableSQL);
                   } else {

                   }
                   st.close();
                   Vaciartabla();
                   Pedidos();

               } catch (SQLException ex) {
                   JOptionPane.showMessageDialog(null, "==" + ex);
                   Vaciartabla();
                   Pedidos();
               } catch (ClassNotFoundException ex) {
                   JOptionPane.showMessageDialog(null, "==" + ex);
               }
               //

           } else {
               JOptionPane.showMessageDialog(null, "Selecciona una un registro");
           }

    }
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
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnfinalizar;
    private javax.swing.JComboBox cbestado;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtpedidos;
    // End of variables declaration//GEN-END:variables
}
