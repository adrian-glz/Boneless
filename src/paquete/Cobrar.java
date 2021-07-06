
package paquete;
 
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.awt.event.WindowEvent;
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
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import static paquete.Login.nombrecompleto;
import static paquete.Principal.jtfinal;
import static paquete.Principal.rcambio;
import static paquete.Principal.rfecha;
import static paquete.Principal.rhora;
//import static paquete.Principal.txt_nota;

/**
 *
 * @author AGONZALEZ
 */
public class Cobrar extends javax.swing.JFrame {

//VARIABLES GLOBALES  
    String fecha = null;
    int folio; ///variable que almacena el folio cuando lo obtiene de sql 
    int ventafoliotemp; ///variable que almacena el folio cuando lo obtiene de sql 
    public static String FPP, FPD, FPT;
    ResultSet rs;
    int count = 0;
    DefaultTableModel md;
    Statement st;
    PreparedStatement ps = null;

    public Cobrar() {

        initComponents();
        imagendebarra();
        obtenerfolio();
        lblcambio.setText("$" + rcambio);
        btnefectuarpago.setEnabled(false);
        String vprecio = txttotalc.getText();//obtener valor de precio
        String vcantidad = txttotaldllsc.getText();///obtienes el valor de la cantidad
        String vp = vprecio.replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", "");//dejameos solo los elementos"[^0-1-2-3-4-5-6-7-8-9-.00]"
        String vc = vcantidad.replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", "");//dejameos solo los elementos"[^0-1-2-3-4-5-6-7-8-9-.00]"

    }

    public void obtenerfechaservidor() {//SELECT TIME_FORMAT(NOW(), "%r") AS Tiempo;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            st = conexion.createStatement();
            st.executeUpdate("use prueba");

            //Seleccionar datos
            rs = st.executeQuery("select CURDATE()");
            try {
                while (rs.next()) {
                    rfecha = rs.getString(1);
                }

                //   JOptionPane.showMessageDialog(this, "Son las "+rhora);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                //      alertasql();
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            // alertasql();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            //alertasql();
        }

    }

    public void imagendebarra() {
        try {
            setIconImage(new ImageIcon(getClass().getResource("/img/logo.png")).getImage());
        } catch (Exception e) {

        }

    }

    public void obtenerfolio() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            st = conexion.createStatement();
            st.executeUpdate("use prueba");
            rs = st.executeQuery("SELECT folio from folios where caja ='1'");
            try {
                while (rs.next()) {
                    try {
                        folio = rs.getInt(1);
                    } catch (Exception e) {
                        this.dispose();
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + "><><");
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "><");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex + "xd");
        }
        System.out.println("-----");
    }

    public void Enviartotal() {

        String vprecio = txttotalc.getText();//obtener valor de precio
        String vcantidad = txttotaldllsc.getText();///obtienes el valor de la cantidad
        String vprecioformateado = vprecio.replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", "");//dejameos solo los elementos"[^0-1-2-3-4-5-6-7-8-9-.00]"
        String vcantidadformateado = vcantidad.replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", "");//dejameos solo los elementos"[^0-1-2-3-4-5-6-7-8-9-.00]"
        double vprecioparseado = Double.parseDouble(vprecioformateado);
        double vpcantidadparseado = Double.parseDouble(vcantidadformateado);

    }

    public void calcularcambio() {

        String totalapagar = txttotalc.getText();//obtener valor total del label
        String totalapagardolares = txttotaldllsc.getText();//obtener total dolares del label

        String vartotalapagar = totalapagar.replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", "");//dejameos solo los elementos"[^0-1-2-3-4-5-6-7-8-9-.00]"
        String vartotalapagardolares = totalapagardolares.replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", "");//dejameos solo los elementos"[^0-1-2-3-4-5-6-7-8-9-.00]"

        double vtotal = Double.parseDouble(vartotalapagar);// parseamos a tipo double
        double vtotaldolares = Double.parseDouble(vartotalapagardolares);// parseamos a tipo double
        // System.out.println("valores parseados  " + "pesos: " + vtotal + " dolares: " + vtotaldolares);
        ///xxx
        //*****************************************************************************

        String fppesos = jtxtpesos.getText().replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", "");
        double vfppesos = 0;
        try {
            vfppesos = Double.parseDouble(fppesos);

        } catch (Exception e) {
        }

        String fpdolares = jtxtdolares.getText().replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", "");
        double vfpdolares = Double.parseDouble(fpdolares);
        String fptarjeta = jtxttarjeta.getText().replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", "");
        double vfptarjeta = Double.parseDouble(fptarjeta);
        System.out.println("costo cambio" + rcambio);
        double totalformadepago = vfppesos + (vfpdolares * rcambio) + vfptarjeta;

        double cantidadrestante = (vtotal - totalformadepago);
        txtcr.setText("" + cantidadrestante);

        if (totalformadepago >= vtotal) {

            double cambio = totalformadepago - vtotal;
            txtcr.setText("0.00");///TXTCAMBIORESTANTE
            txtc.setText("" + cambio);///TXTCAMBIO

            btnefectuarpago.setEnabled(true);
        } else {
            txtc.setText("0.00");
            btnefectuarpago.setEnabled(false);
        }

   
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtxttarjeta = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtxtpesos = new javax.swing.JTextField();
        jtxtdolares = new javax.swing.JTextField();
        btnefectuarpago = new javax.swing.JButton();
        txttotaldllsc = new javax.swing.JLabel();
        txttotalc = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtc = new javax.swing.JLabel();
        txtcr = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnregresar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        lblcambio = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Formas de pago");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtxttarjeta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtxttarjeta.setText("0");
        jtxttarjeta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxttarjetaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxttarjetaFocusLost(evt);
            }
        });
        jtxttarjeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxttarjetaKeyReleased(evt);
            }
        });
        getContentPane().add(jtxttarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 154, 165, 40));
        jtxttarjeta.getAccessibleContext().setAccessibleName("");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("$ DEBITO/CREDITO");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 152, 165, 40));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Metodo de pago");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 134, 48));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("$ PESOS MXN");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 53, 165, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("$ DOLARES USA");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 106, 165, 40));

        jtxtpesos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtxtpesos.setText("0");
        jtxtpesos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtpesosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtpesosFocusLost(evt);
            }
        });
        jtxtpesos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxtpesosMouseClicked(evt);
            }
        });
        jtxtpesos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtpesosKeyReleased(evt);
            }
        });
        getContentPane().add(jtxtpesos, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 55, 165, 40));

        jtxtdolares.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtxtdolares.setText("0");
        jtxtdolares.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtdolaresFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtdolaresFocusLost(evt);
            }
        });
        jtxtdolares.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtdolaresKeyReleased(evt);
            }
        });
        getContentPane().add(jtxtdolares, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 106, 165, 40));

        btnefectuarpago.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnefectuarpago.setForeground(new java.awt.Color(0, 128, 63));
        btnefectuarpago.setText("EFECTUAR PAGO");
        btnefectuarpago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnefectuarpagoActionPerformed(evt);
            }
        });
        getContentPane().add(btnefectuarpago, new org.netbeans.lib.awtextra.AbsoluteConstraints(222, 455, 194, 53));

        txttotaldllsc.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txttotaldllsc.setText("$ 0.00");
        getContentPane().add(txttotaldllsc, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 150, 30));

        txttotalc.setBackground(new java.awt.Color(255, 255, 255));
        txttotalc.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txttotalc.setText("$ 0.00");
        getContentPane().add(txttotalc, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 150, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("FALTAN");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 60, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("MXN");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, 80, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("DLLS");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 240, 81, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("TOTAL A PAGAR:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 130, 30));

        txtc.setBackground(new java.awt.Color(255, 255, 255));
        txtc.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtc.setText("$ 0.00");
        getContentPane().add(txtc, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, 185, 30));

        txtcr.setBackground(new java.awt.Color(255, 255, 255));
        txtcr.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtcr.setForeground(new java.awt.Color(245, 2, 2));
        txtcr.setText("$ 0.00");
        getContentPane().add(txtcr, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 185, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("CAMBIO");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, 70, 30));

        btnregresar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnregresar.setForeground(new java.awt.Color(0, 128, 63));
        btnregresar.setText("REGRESAR");
        btnregresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregresarActionPerformed(evt);
            }
        });
        getContentPane().add(btnregresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 455, 194, 53));

        jLabel10.setText("         ");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 550, -1, -1));

        lblcambio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblcambio.setForeground(new java.awt.Color(0, 153, 51));
        getContentPane().add(lblcambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 150, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Dolar costo");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 80, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtpesosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtpesosKeyReleased
        calcularcambio();
    }//GEN-LAST:event_jtxtpesosKeyReleased

    private void jtxtpesosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtpesosMouseClicked
        if (evt.getClickCount() == 1) {
        }
    }//GEN-LAST:event_jtxtpesosMouseClicked

    private void jtxtdolaresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtdolaresKeyReleased
        calcularcambio();
    }//GEN-LAST:event_jtxtdolaresKeyReleased

    private void jtxttarjetaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxttarjetaKeyReleased
        calcularcambio();
    }//GEN-LAST:event_jtxttarjetaKeyReleased

    private void btnregresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregresarActionPerformed
        this.dispose();
        Principal p = new Principal();
        p.setVisible(true);
    }//GEN-LAST:event_btnregresarActionPerformed

    private void btnefectuarpagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnefectuarpagoActionPerformed
        String cantidadpago = jtxtpesos.getText();
        String cambio = txttotalc.getText();
        GregorianCalendar gg = new GregorianCalendar();
        SimpleDateFormat dd = new SimpleDateFormat("YYYY/MM/dd");
        SimpleDateFormat ddd = new SimpleDateFormat("HH:mm");

        
        Statement e;
        PreparedStatement pse = null;
        double numarticulo = 1;
       
        
        int n = 0;
        System.out.println("xxx1xxx");
        for (int x = 0; x < jtfinal.getRowCount(); x++) {

            try {
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
                Statement ste = conexion.createStatement();
                ste.executeUpdate("use prueba;");
                String vcodigo = ((String) jtfinal.getValueAt(x, 0));//obtener valor de precio
                String vdescripcion = ((String) jtfinal.getValueAt(x, 1));//obtener valor de precio
                String vprecio = ((String) jtfinal.getValueAt(x, 2));///obtienes el valor de la cantidad
                int vcantidad = ((int) jtfinal.getValueAt(x, 3));///obtienes el valor de la cantidad
                String vprecioformateado = vprecio.replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", "");//dejameos solo los elementos"[^0-1-2-3-4-5-6-7-8-9-.00]"
                double vprecioparseado = Double.parseDouble(vprecioformateado);
                pse = conexion.prepareStatement("INSERT INTO ventas ( `fecha`,`Sucursal`, `Folio`, `Caja`, `Articulo`, `Codigo`, `Grupo`, `Cantidad`, `Precioventa`, `Vendedor`, `Cajero`, `Claveventa`, `Hora`) "
                        + "VALUES ( '" + rfecha + "','1','" + folio + "','1','" + numarticulo + "','" + vcodigo + "','00','" + vcantidad + "','" + vprecioformateado + "','1111','00','777','" + rhora + "')");
             /*   System.out.println("INSERT INTO ventas ( `fecha`,`Sucursal`, `Folio`, `Caja`, `Articulo`, `Codigo`, `Grupo`, `Cantidad`, `Precioventa`, `Vendedor`, `Cajero`, `Claveventa`, `Hora`) "
                        + "VALUES ( '" + rfecha + "','1','" + folio + "','1','" + numarticulo + "','" + vcodigo + "','00','" + vcantidad + "','" + vprecioformateado + "','1111','00','777','" + rhora + "')");
                */
                numarticulo = numarticulo + 1;
                n = pse.executeUpdate();

            } catch (HeadlessException | SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error en la base de datos 901" + ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }///fin del cliclo for perro
        if (n > 0) {
            // JOptionPane.showMessageDialog(null, "¡Los datos han sido guardados exitósamente!");
            insertaventapagos();
            //  aumentarfolio(); 
        }
        
        Principal.vaciartabla(new Object[]{1});//prepara el valor 1 para enviar metodo a otra clase para limpiar la tabla
        this.dispose();//cerramos la ventana


    }//GEN-LAST:event_btnefectuarpagoActionPerformed
    public void insertaventapagos() {

        Statement e;
        PreparedStatement pse = null;
        double numarticulo = 1;
        int n = 0;
        double total = Double.parseDouble(txttotalc.getText().replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", ""));
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            Statement ste = conexion.createStatement();
            ste.executeUpdate("use prueba;");
            pse = conexion.prepareStatement("insert into `ventaspagos`(`fecha`,`Sucursal`,  `Caja`, `Folio`, `Importe`, `Clavepago`, `NumeroTarjeta`, `NombreCliente`, `DireccionCliente`, `TelefonoCliente`, `CorreoCliente`,`fpp`,`fpd`,`fpt`,`Cambio`) \n"
                    + "VALUES ('" + rfecha + "','1','1','" + folio + "','" + total + "','01','0000','NULL','NULL','NULL','NULL','" + jtxtpesos.getText().trim() + "','" + jtxtdolares.getText().trim() + "','" + jtxttarjeta.getText().trim() + "','" + txtc.getText().trim() + "')");
            numarticulo = numarticulo + 1;
            n = pse.executeUpdate();
        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error en la base de datos903" + ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (n > 0) {
            insertapedido();
            JOptionPane.showMessageDialog(null, "Venta Registrada");
        }
    }
    private void jtxtpesosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtpesosFocusGained
        if (jtxtpesos.getText().trim().toLowerCase().equals("0")) {
            jtxtpesos.setText("");
            jtxtpesos.setForeground(Color.BLACK);
            calcularcambio();
        }
    }//GEN-LAST:event_jtxtpesosFocusGained

    public void insertapedido() {

        PreparedStatement p = null;
        double numarticulo = 1;
       // obtenerfolio();
        int n = 0;
        for (int x = 0; x < jtfinal.getRowCount(); x++) {

            try {
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
                Statement ste = conexion.createStatement();
                ste.executeUpdate("use prueba;");
                String vcodigo = ((String) jtfinal.getValueAt(x, 0));//obtener valor de precio
                String vdescripcion = ((String) jtfinal.getValueAt(x, 1));//obtener valor de precio
                String vprecio = ((String) jtfinal.getValueAt(x, 2));///obtienes el valor de la cantidad
                int vcantidad = ((int) jtfinal.getValueAt(x, 3));///obtienes el valor de la cantidad
                String vdetalle = ((String) jtfinal.getValueAt(x, 4));///obtienes el valor de la cantidad
                String vnota = ((String) jtfinal.getValueAt(x, 5));///obtienes el valor de la cantidad

                String vprecioformateado = vprecio.replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", "");//dejameos solo los elementos"[^0-1-2-3-4-5-6-7-8-9-.00]"
                double vprecioparseado = Double.parseDouble(vprecioformateado);
                p = conexion.prepareStatement("INSERT INTO `pedidos`(`folio`, `Codigo`, `Descripcion`, `Detalle`, `cantidad`, `Articulo`, `Nota`,`fecha`, `Estado`,`hora`)  "
                        + "VALUES ( '" + folio + "','" + vcodigo + "','" + vdescripcion + "','" + vdetalle + "','" + vcantidad + "','" + numarticulo + "','" + vnota + "','" + rfecha + "','EN PROCESO','" + rhora + "')");
                numarticulo = numarticulo + 1;
                n = p.executeUpdate();

            } catch (HeadlessException | SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error en la base de datos 902" + ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }///fin del cliclo for perro
        if (n > 0) {
            
     //  imprimeticketventa();
            imprimirpedido();
            Principal pr = new Principal();
            pr.aumentarfolio();
            //imprimirfolio
        }
    }
    public void totalventafoliotemp(){
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            st = conexion.createStatement();
            st.executeUpdate("use prueba");
            rs = st.executeQuery("SELECT sum(cantidad*precioventa) from ventas where folio ='"+folio+"' and fecha='"+rfecha+"'    ");
            try {
                while (rs.next()) {
                    try {
                       ventafoliotemp = rs.getInt(1);
                    } catch (Exception e) {
                        this.dispose();
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + "><><");
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "><");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex + "xd");
        }
        System.out.println("-----");
    }
    
     public void imprimeticketventa () {
         //necesito fecha y folio padre
         totalventafoliotemp();
         obtenerfechaservidor();
         String user = System.getProperty("user.name");
      //  double total = Double.parseDouble(Corte);
         // File archivo = new File("C:\\Users\\" + user + "\\Desktop\\LEEME.txt");
         try {
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

             String cajero = nombrecompleto;
             ConexionMySQL con = new ConexionMySQL();
             Connection conn = con.Conectar();
             System.out.println("cajero:" + cajero);
             JasperReport reporte = null;
             Map parametro = new HashMap(); // MAPEO DE MAPA TIPO HASH
             parametro.put("txt_fecha", "'" + rfecha + "'");
             parametro.put("txt_total", ventafoliotemp);
             parametro.put("txt_folio", folio);
             System.out.println(" imprime variables folio:  " + folio + "FECHA:" + rfecha+"totsl de venta "+ventafoliotemp);
             parametro.put("txt_cajero", cajero.trim());
             String path = "C:\\Users\\agonzalez\\Documents\\GitHub\\Boneless\\src\\Plantillas/Ticket.jasper";
             //   String path = "C:\\Users\\"+user+"\\Documents\\NetBeansProjects\\Inventario\\src\\reportes\\Dia.jasper";
             reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
             JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, conn);
             JasperPrintManager.printReport(jprint, true);
           
        } catch (JRException ex) {
            Logger.getLogger(Corte.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ">>" + ex);

        }
    }
     public void imprimirpedido () {
       try {
              ConexionMySQL con = new ConexionMySQL();
             Connection conn = con.Conectar();
             JasperReport reporte = null;
             Map parametro = new HashMap(); // MAPEO DE MAPA TIPO HASH
             parametro.put("txt_fecha", "'" + rfecha + "'");
             parametro.put("txt_folio", folio);
             System.out.println(" imprime variables folio:  " + folio + "FECHA:" + rfecha+"totsl de venta "+ventafoliotemp);
              String path = "C:\\Users\\agonzalez\\Documents\\GitHub\\Boneless\\src\\Plantillas/Ticketpedido.jasper";
             //   String path = "C:\\Users\\"+user+"\\Documents\\NetBeansProjects\\Inventario\\src\\reportes\\Dia.jasper";
             reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
             JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, conn);
             JasperPrintManager.printReport(jprint, true);
           
        } catch (JRException ex) {
            Logger.getLogger(Corte.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ">>" + ex);

        }
    }
    private void jtxtpesosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtpesosFocusLost
        // TODO add your handling code here:
        if (jtxtpesos.getText().trim().equals("") || jtxtpesos.getText().trim().toLowerCase().equals("0")) {
            jtxtpesos.setText("0");//////////////////INGRESA EL 0 EN CAJA CUANDO NO ESTAS EN LA CAJA
            calcularcambio();
            ///  System.out.println("focus lost");  
        }
    }//GEN-LAST:event_jtxtpesosFocusLost

    private void jtxtdolaresFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtdolaresFocusGained
        // TODO add your handling code here:
        if (jtxtdolares.getText().trim().toLowerCase().equals("0")) {
            jtxtdolares.setText("");
            jtxtdolares.setForeground(Color.BLACK);
            calcularcambio();
        }
    }//GEN-LAST:event_jtxtdolaresFocusGained

    private void jtxtdolaresFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtdolaresFocusLost
        // TODO add your handling code here:
        if (jtxtdolares.getText().trim().equals("") || jtxtdolares.getText().trim().toLowerCase().equals("0")) {
            jtxtdolares.setText("0");
            calcularcambio();
        }
    }//GEN-LAST:event_jtxtdolaresFocusLost

    private void jtxttarjetaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxttarjetaFocusGained
        if (jtxttarjeta.getText().trim().toLowerCase().equals("0")) {
            jtxttarjeta.setText("");
            jtxttarjeta.setForeground(Color.BLACK);
            calcularcambio();
        }
    }//GEN-LAST:event_jtxttarjetaFocusGained

    private void jtxttarjetaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxttarjetaFocusLost
        if (jtxttarjeta.getText().trim().equals("") || jtxttarjeta.getText().trim().toLowerCase().equals("0")) {
            jtxttarjeta.setText("0");
            calcularcambio();
        }
    }//GEN-LAST:event_jtxttarjetaFocusLost

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ConexionMySQL mysql = new ConexionMySQL();
                java.sql.Connection cn = mysql.Conectar();
                if (cn != null) {
                    JOptionPane.showMessageDialog(null, "Bienvenido a Punto de venta  usted esta Conectado");
                    new Cobrar().setVisible(true);
                    try {
                        cn.close();
                    } catch (SQLException ex) {
                        System.out.println("Error al conectar " + ex + " verifique su conexion ");
                    }
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnefectuarpago;
    private javax.swing.JButton btnregresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jtxtdolares;
    private javax.swing.JTextField jtxtpesos;
    private javax.swing.JTextField jtxttarjeta;
    private javax.swing.JLabel lblcambio;
    private javax.swing.JLabel txtc;
    private javax.swing.JLabel txtcr;
    public javax.swing.JLabel txttotalc;
    public javax.swing.JLabel txttotaldllsc;
    // End of variables declaration//GEN-END:variables

   public void ingresardatosaventaspagos( String pago, String cambio) {
    
          try { 
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
                Statement st = conexion.createStatement();
                st.executeUpdate("use prueba");
            
    
                   ps=conexion.prepareStatement("INSERT INTO ventaspagos ( cantidadpago, cambio ) VALUES('"+ pago+"', '"+cambio+"' );");
                int n = ps.executeUpdate();
                      
     
            }catch (HeadlessException | SQLException ex) {
                    
              JOptionPane.showMessageDialog(rootPane, "Error en la base de datos");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Cobrar.class.getName()).log(Level.SEVERE, null, ex);
      
            
    }}
}
