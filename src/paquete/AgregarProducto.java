package paquete;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AgregarProducto extends javax.swing.JFrame {

    Statement st;
    PreparedStatement ps = null;
    ResultSet rs;
    String codigo;
    int cantidad;
    String categoria;
    String descripcionproducto;
    double precio;
    String urlimagen;

    public AgregarProducto() {
        initComponents();
        llenarcategorias();
        imagendebarra();
    }
  public void imagendebarra(){
      try{
          setIconImage(new ImageIcon(getClass().getResource("/img/logo.png")).getImage());
        }catch(Exception  e){
        
        }
    
    }

    public void copiaimagen() {
                 try {
                //Definimos el destino del archivo, que será la carpeta Imágenes"
                String Dest = System.getProperty("user.dir") + "/img/" + urlimagen;
                System.out.println("Dest"+Dest);
                Path Destino = Paths.get(Dest);
                //Definimos el origen, que será el archivo seleccionado 
               
                Path Origen = Paths.get(Dest);
                //Copiamos el nuevo archivo con la clase Files, reemplazamos si a existe uno igual .
                Files.copy(Origen, Destino, REPLACE_EXISTING);
                //Mostramos mensaje de confirmación de la copia realizada y la r ta.
                JOptionPane.showMessageDialog(null, "El archivo fué copiado con 'xito a la carpeta: " + Dest);
            } catch (IOException ex) {
                Logger.getLogger(AgregarProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void insertarcodigo() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SS");
        LocalDateTime date = LocalDateTime.now();
        System.out.println(dtf.format(date));
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            Statement st = conexion.createStatement();
            st.executeUpdate("use prueba");
            //      System.out.println(">>>>>xxxx" + txtgondola.getText().toUpperCase() + txtcantidad.getText().toUpperCase() + date);
            ps = conexion.prepareStatement("INSERT INTO `productos`(`codigo`, `descripcion`, `precio`, `cantidad`, `categoria`, `imagen`)\n"
                    + "   VALUES('" + codigo + "','" + descripcionproducto + "','" + precio + "','" + cantidad + "','" + categoria + "','" + urlimagen + "')    ");
            int n = ps.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "¡Los datos han sido guardados exitósamente!");
                
             //   copiaimagen();
            }
        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error en la base de datos 111");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AgregarProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void comprobarcodigo() {
        String x = txtcodigo.getText();
        if (x.length() < 1) {
            System.out.println(txtcodigo.getText());
            JOptionPane.showMessageDialog(null, "Teclea el codigo", "Alerta", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
                st = conexion.createStatement();
                st.executeUpdate("use prueba");
                rs = st.executeQuery("select codigo  from productos where codigo='" + txtcodigo.getText() + "'");

                boolean friv = rs.next();
                String s1 = Boolean.toString(friv);
                try {
                    System.out.println("><><>" + s1);
                    if (s1.equals("true")) {
                        JOptionPane.showMessageDialog(null, "UPPS!!  el codigo ----> " + txtcodigo.getText() + "  Ya existe ", "Alerta", JOptionPane.WARNING_MESSAGE);
                        while (rs.next()) {
                        }
                    } else {
                        compruebacampos();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, "101" + ex.getMessage());
                }
            } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage()+"102");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AgregarProducto.class.getName()).log(Level.SEVERE, null, ex+"103");
        }      
        }
    }
    
    public void limpiarcampos(){
    txtcodigo.setText("");
    cbcantidad.setValue(0);
    llenarcategorias();
    txt_nombreproducto.setText("");
    txt_precio.setText("");
    }
    
    public void compruebacampos() {

        codigo = txtcodigo.getText().trim();
        cantidad = (int) cbcantidad.getValue();
        categoria = cbcategorias.getSelectedItem().toString().trim();
        descripcionproducto = txt_nombreproducto.getText().trim();
        precio = Double.parseDouble(txt_precio.getText().trim());

        if ((codigo.isEmpty()) || (categoria.equals("-------SELECCIONA-------")) || (descripcionproducto.isEmpty())) {
            JOptionPane.showMessageDialog(null, "Comprueba que todos los campos esten llenos");
      
        } else {   //Checar que no estén vacíos
            insertarcodigo();
        }
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
            // System.out.println("ERROR: failed to load HSQLDB JDBC driver.");
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
        txt_nombreproducto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btncancelar = new javax.swing.JButton();
        btnbuscar = new javax.swing.JButton();
        txt_ruta = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Agregar nuevo producto");
        setResizable(false);
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
        btnagregar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnagregar.setText("Agregar");
        btnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarActionPerformed(evt);
            }
        });
        getContentPane().add(btnagregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 340, 40));

        btnagregarcategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar1.png"))); // NOI18N
        btnagregarcategoria.setContentAreaFilled(false);
        btnagregarcategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarcategoriaActionPerformed(evt);
            }
        });
        getContentPane().add(btnagregarcategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 40, 40));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Nombre de producto:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 130, 34));
        getContentPane().add(txt_nombreproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 170, 35));

        jLabel1.setText("       ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, 40, 20));

        btncancelar.setBackground(new java.awt.Color(255, 51, 51));
        btncancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btncancelar.setText("Cancelar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btncancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 340, 40));

        btnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/file.png"))); // NOI18N
        btnbuscar.setText("...");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 100, 30));

        txt_ruta.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txt_ruta.setText(" ");
        getContentPane().add(txt_ruta, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 160, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
        comprobarcodigo();
    }//GEN-LAST:event_btnagregarActionPerformed

    private void btnagregarcategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarcategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnagregarcategoriaActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        this.dispose();
        Principal p=new Principal();
        p.setVisible(true);

    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
      JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        file.showOpenDialog(this);
        File archivo = file.getSelectedFile();
        //Verificamos la selección de un archivo
        if (archivo != null) {
            try {
                String user = System.getProperty("user.name");
                String Dest = "C:\\Users\\" + user + "\\Pictures\\img/" + archivo.getName();//destnull123.png
                urlimagen = archivo.getName();
                Path Destino = Paths.get(Dest);
                String Orig = archivo.getPath();
                Path Origen = Paths.get(Orig);
                Files.copy(Origen, Destino, REPLACE_EXISTING);
                txt_ruta.setText(urlimagen.toString());
                System.out.println("completo"+Dest);
            } catch (IOException ex) {
                System.out.println("error");
                Logger.getLogger(AgregarProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnbuscarActionPerformed
 
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
            java.util.logging.Logger.getLogger(AgregarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgregarProducto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnagregar;
    private javax.swing.JButton btnagregarcategoria;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JSpinner cbcantidad;
    private javax.swing.JComboBox cbcategorias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txt_nombreproducto;
    private javax.swing.JTextField txt_precio;
    private javax.swing.JLabel txt_ruta;
    private javax.swing.JTextField txtcodigo;
    // End of variables declaration//GEN-END:variables
}
