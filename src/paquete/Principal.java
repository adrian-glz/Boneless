package paquete;

import java.awt.HeadlessException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import static paquete.Login.nombrecompleto;

public class Principal extends javax.swing.JFrame {

   public  int folio; ///variable que almacena el folio cuando lo obtiene de sql 
    DefaultTableModel md;
    Statement st;
    ResultSet rs;
    PreparedStatement ps = null;
    public static double rcambio;
    public static int rfolio;
    public static int numerocajero;
    public static String rhora;
    public static String rfecha;
    public static String rtxtnota;
    String combinar;
     String user = System.getProperty("user.name");
              

    public Principal() {

        initComponents();///inicializamos componentes al inicio del metodo
        obtenerfolio();
        imagendebarra();
        recuperafolio();
        recuperacostodolar();
        Bebidas();
        //llamamos el metodo de bebidas para llenar tablas
        Hamburguesas();//llamamos el metodo de Comidas para llenar Comidas
        Boneless();
        Tortasytacos();
        Antojos();
        tablafinal();
        jtfinal.getTableHeader().setReorderingAllowed(false);///INHABILITA EL MOVER CABECERAS los titulos de la tabla jtfinal
    }

    public void imagendebarra() {
        try {
            setIconImage(new ImageIcon(getClass().getResource("/img/logo.png")).getImage());
        } catch (Exception e) {
            
        }
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
                alertasql();
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            alertasql();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            alertasql();
        }

    }

    public void obtenerhoraservidor() {//SELECT TIME_FORMAT(NOW(), "%r") AS Tiempo;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            st = conexion.createStatement();
            st.executeUpdate("use prueba");

            //Seleccionar datos
            rs = st.executeQuery("SELECT TIME_FORMAT(NOW(), \"%r\") AS Tiempo;");
            try {
                while (rs.next()) {
                    rhora = rs.getString(1);
                }

                //   JOptionPane.showMessageDialog(this, "Son las "+rhora);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                alertasql();
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            alertasql();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            alertasql();
        }

    }

    public void alertasql() {
        JOptionPane.showMessageDialog(this, "Error con SQL revisa los ajustes y consultas SQL, se va a cerrar el programa");
        System.exit(0);
    }

    public void recuperacostodolar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            st = conexion.createStatement();
            st.executeUpdate("use prueba");

            //Seleccionar datos
            rs = st.executeQuery("select compra from `costeo`"
                    + " where id='1'");
            try {
                while (rs.next()) {
                    rcambio = rs.getInt(1);
                }
                txtdolar.setText("" + rcambio);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                alertasql();
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            alertasql();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            alertasql();
        }
    }

    public void recuperafolio() {///metodo para traer cajero, folio, 

        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            st = conexion.createStatement();
            st.executeUpdate("use prueba");
            //Seleccionar datos
            rs = st.executeQuery("select folio from  folios "
                    + " where caja='1'");
            try {
                while (rs.next()) {
                    folio = rs.getInt(1);
                }
                txtfolio.setText("" + folio);
                txtcajero.setText(nombrecompleto);
                //   System.out.println("si CORRE"+nombrecompleto);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                alertasql();
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            alertasql();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            alertasql();
        }

    }

 
   
    public void insertapedido() {

        PreparedStatement p = null;
        double numarticulo = 1;
        obtenerfolio();
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

                String vprecioformateado = vprecio.replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", "");//dejameos solo los elementos"[^0-1-2-3-4-5-6-7-8-9-.00]"
                double vprecioparseado = Double.parseDouble(vprecioformateado);
                p = conexion.prepareStatement("INSERT INTO `pedidos`(`folio`, `Codigo`, `Descripcion`, `Detalle`, `cantidad`, `Articulo`, `Nota`,`fecha`, `Estado`,`hora`)  "
                        + "VALUES ( '" + folio + "','" + vcodigo + "','" + vdescripcion + "','" + vdetalle + "','" + vcantidad + "','" + numarticulo + "','" + txt_nota.getText().toUpperCase() + "','" + rfecha + "','EN PROCESO','" + rhora + "')");
                numarticulo = numarticulo + 1;
                n = p.executeUpdate();

            } catch (HeadlessException | SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error en la base de datos 902" + ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }///fin del cliclo for perro
        if (n > 0) {

            aumentarfolio();
        }
    }

    public void Hamburguesaagregarfinal() {
        DefaultTableModel model = (DefaultTableModel) jtfinal.getModel();

        int filaseleccionada = jthamburguesas.getSelectedRow();//OBTIENES EL ELEMENTO DE LA TABLA
        if (filaseleccionada >= 0) {
            Object obj0 = (jthamburguesas.getValueAt(filaseleccionada, 0));///OBTIENES EL PRIMER FILA
            Object obj1 = (jthamburguesas.getValueAt(filaseleccionada, 1));///OBTIENES EL PRIMER FILA
            Object obj2 = (jthamburguesas.getValueAt(filaseleccionada, 2));//OBTIENES LA SEGUNDA FILA
            combinar = "";
            if (ch1.isSelected()) {
                combinar += "SIN CEBOLLA,";
            }
            if (ch2.isSelected()) {
                combinar += "SIN CHILE,";
            }
            if (ch3.isSelected()) {
                combinar += "SIN PEPINILLOS,";
            }
            if (ch4.isSelected()) {
                combinar += "SIN TOMATE,";
            }
            if (ch5.isSelected()) {
                combinar += "SIN MOSTAZA,";
            }
            if (ch6.isSelected()) {
                combinar += "SIN KETCHUP.";
            }
            if (ch1.isSelected() && ch2.isSelected() && ch3.isSelected() && ch4.isSelected() && ch5.isSelected() && ch6.isSelected()) {
                combinar = "SIN NADA";
            }
            if (ch1.isSelected() == false && ch2.isSelected() == false && ch3.isSelected() == false && ch4.isSelected() == false && ch5.isSelected() == false && ch6.isSelected() == false) {
                combinar = "CON TODO";
            }
            String detalle = txt_nota.getText().trim().toUpperCase();

            model.addRow(new Object[]{obj0, obj1, obj2, 1, combinar, detalle});
        }
        sumar();
    }

    public void Tortasytacosagregarfinal() {
        DefaultTableModel model = (DefaultTableModel) jtfinal.getModel();

        int filaseleccionada = jttortasytacos.getSelectedRow();//OBTIENES EL ELEMENTO DE LA TABLA
        if (filaseleccionada >= 0) {
            Object obj0 = (jttortasytacos.getValueAt(filaseleccionada, 0));///OBTIENES EL PRIMER FILA
            Object obj1 = (jttortasytacos.getValueAt(filaseleccionada, 1));///OBTIENES EL PRIMER FILA
            Object obj2 = (jttortasytacos.getValueAt(filaseleccionada, 2));//OBTIENES LA SEGUNDA FILA
            combinar = "";
            if (ch19.isSelected()) {
                combinar += "SIN CEBOLLA,";
            }
            if (ch21.isSelected()) {
                combinar += "SIN CHILE,";
            }
            if (ch24.isSelected()) {
                combinar += "SIN PEPINILLOS,";
            }
            if (ch20.isSelected()) {
                combinar += "SIN TOMATE,";
            }
            if (ch22.isSelected()) {
                combinar += "SIN MOSTAZA,";
            }
            if (ch23.isSelected()) {
                combinar += "SIN KETCHUP.";
            }
            if (ch19.isSelected() && ch20.isSelected() && ch21.isSelected() && ch22.isSelected() && ch23.isSelected() && ch24.isSelected()) {
                combinar = "SIN NADA";
            }
            if (ch19.isSelected() == false && ch20.isSelected() == false && ch21.isSelected() == false && ch22.isSelected() == false && ch23.isSelected() == false && ch24.isSelected() == false) {
                combinar = "CON TODO";
            }
            String detalle = txt_notaTortasytacos.getText().trim().toUpperCase();

            model.addRow(new Object[]{obj0, obj1, obj2, 1, combinar, detalle});
        }
        sumar();
    }

    public void antojosagregarfinal() {
        DefaultTableModel model = (DefaultTableModel) jtfinal.getModel();

        int filaseleccionada = jtantojos.getSelectedRow();//OBTIENES EL ELEMENTO DE LA TABLA
        if (filaseleccionada >= 0) {
            Object obj0 = (jtantojos.getValueAt(filaseleccionada, 0));///OBTIENES EL PRIMER FILA
            Object obj1 = (jtantojos.getValueAt(filaseleccionada, 1));///OBTIENES EL PRIMER FILA
            Object obj2 = (jtantojos.getValueAt(filaseleccionada, 2));//OBTIENES LA SEGUNDA FILA
            combinar = "";
         
            String detalle = txt_notaantojo.getText().trim().toUpperCase();

            model.addRow(new Object[]{obj0, obj1, obj2, 1, combinar, detalle});
        }
        sumar();
    }

    public void bonelessagregarfinal() {
        DefaultTableModel model = (DefaultTableModel) jtfinal.getModel();

        int filaseleccionada = jtboneless.getSelectedRow();//OBTIENES EL ELEMENTO DE LA TABLA
        if (filaseleccionada >= 0) {
            Object obj0 = (jtboneless.getValueAt(filaseleccionada, 0));///OBTIENES EL PRIMER FILA
            Object obj1 = (jtboneless.getValueAt(filaseleccionada, 1));///OBTIENES EL PRIMER FILA
            Object obj2 = (jtboneless.getValueAt(filaseleccionada, 2));//OBTIENES LA SEGUNDA FILA
            combinar = "";

            String detalle = txt_notaboneless.getText().trim().toUpperCase();

            model.addRow(new Object[]{obj0, obj1, obj2, 1, combinar, detalle});
        }
        sumar();
    }

    public void agregarfinalbebidas() {
        DefaultTableModel model = (DefaultTableModel) jtfinal.getModel();

        int filaseleccionada = jtbebidas.getSelectedRow();//OBTIENES EL ELEMENTO DE LA TABLA
        if (filaseleccionada >= 0) {
            Object obj0 = (jtbebidas.getValueAt(filaseleccionada, 0));///OBTIENES EL PRIMER FILA
            Object obj1 = (jtbebidas.getValueAt(filaseleccionada, 1));///OBTIENES EL PRIMER FILA
            Object obj2 = (jtbebidas.getValueAt(filaseleccionada, 2));//OBTIENES LA SEGUNDA FILA

            model.addRow(new Object[]{obj0, obj1, obj2,1 ,"",""});
        }
        sumar();
    }

    public void aumentarfolio() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            Statement st = conexion.createStatement();
            st.executeUpdate("use prueba;");

            ps = conexion.prepareStatement("update  folios  set  folio = folio+1 where `caja`=1 ");

            int n = ps.executeUpdate();
            if (n > 0) {
                //   JOptionPane.showMessageDialog(null, "¡Los datos han sido guardados exitósamente!");
                st.close();
                Principal pri = new Principal();
                pri.setVisible(true);
                recuperafolio();
                Vaciartabla();

                //  limpiarcampos();
            }
        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error en la base de datos, no se pudo guardar el folio" + ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
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

    public void Hamburguesas() {
        String data[][] = {};
        String cabeza[] = {"Codigo", "Descripcion", "Precio", "Imagen"};///definimos nombre cada columna en encabezado
        jthamburguesas.getTableHeader().setReorderingAllowed(false);//evitamos que no se pueda reordenar jtplatos 

        md = new DefaultTableModel(data, cabeza) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //   if (column != 4) {
                if (column != 4) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        jthamburguesas.setModel(md); //igualamos en modelo en jplatos
        JTableHeader th;
        th = jthamburguesas.getTableHeader();
        th.setFont(new java.awt.Font("tahoma", 0, 14));//seteamos fuente en el header
        //<Centrar el encabezado de la tabla>
        TableCellRenderer rendererFromHeader = jthamburguesas.getTableHeader().getDefaultRenderer();//
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.LEFT);
        /////////////////////////////////////////////////////////>
        jthamburguesas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jthamburguesas.getColumnModel().getColumn(0).setPreferredWidth(60); //Matrícula
        jthamburguesas.getColumnModel().getColumn(0).setMaxWidth(300);
        jthamburguesas.getColumnModel().getColumn(0).setMinWidth(60);

        jthamburguesas.getColumnModel().getColumn(1).setPreferredWidth(180); //Matrícula
        jthamburguesas.getColumnModel().getColumn(1).setMaxWidth(300);
        jthamburguesas.getColumnModel().getColumn(1).setMinWidth(180);

        jthamburguesas.getColumnModel().getColumn(2).setPreferredWidth(70); //Nombre
        jthamburguesas.getColumnModel().getColumn(2).setMaxWidth(120);
        jthamburguesas.getColumnModel().getColumn(2).setMinWidth(70);

        jthamburguesas.getColumnModel().getColumn(3).setPreferredWidth(120); //Nombre
        jthamburguesas.getColumnModel().getColumn(3).setMaxWidth(120);
        jthamburguesas.getColumnModel().getColumn(3).setMinWidth(120);
        jthamburguesas.setDefaultRenderer(Object.class, new Imgtabla());
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            st = conexion.createStatement();
            st.executeUpdate("use prueba");

            //Seleccionar datos
            rs = st.executeQuery("select `codigo`, `descripcion`, `precio`, `cantidad`, `categoria`, `imagen` from productos where categoria='hamburguesa'");
            md = (DefaultTableModel) jthamburguesas.getModel();
            md.setRowCount(0);
            try {
                jthamburguesas.setRowHeight(40);
                while (rs.next()) {
                    //  String Dest = "C:\\Users\\" + user + "\\Pictures/" + archivo.getName();//destnull123.png
                    String user = System.getProperty("user.name");

                    String RUTA = "/imgcatalogo/" + rs.getString(6).trim();

//      String RUTA ="/imgcatalogo/" + rs.getString(6).trim();
                    Object[] fila = (new Object[]{rs.getString(1), rs.getString(2), "$" + rs.getString(3), new JLabel(new ImageIcon(getClass().getResource("" + RUTA + "")))});
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

    public void Hotdogs() {
        String data[][] = {};
        String cabeza[] = {"Codigo", "Descripcion", "Precio", "Imagen"};///definimos nombre cada columna en encabezado
        jthotdogs.getTableHeader().setReorderingAllowed(false);//evitamos que no se pueda reordenar jtplatos 

        md = new DefaultTableModel(data, cabeza) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //   if (column != 4) {
                if (column != 4) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        jthotdogs.setModel(md); //igualamos en modelo en jplatos
        JTableHeader th;
        th = jthotdogs.getTableHeader();
        th.setFont(new java.awt.Font("tahoma", 0, 14));//seteamos fuente en el header
        //<Centrar el encabezado de la tabla>
        TableCellRenderer rendererFromHeader = jthotdogs.getTableHeader().getDefaultRenderer();//
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.LEFT);
        /////////////////////////////////////////////////////////>
        jthotdogs.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jthotdogs.getColumnModel().getColumn(0).setPreferredWidth(60); //Matrícula
        jthotdogs.getColumnModel().getColumn(0).setMaxWidth(300);
        jthotdogs.getColumnModel().getColumn(0).setMinWidth(60);

        jthotdogs.getColumnModel().getColumn(1).setPreferredWidth(180); //Matrícula
        jthotdogs.getColumnModel().getColumn(1).setMaxWidth(300);
        jthotdogs.getColumnModel().getColumn(1).setMinWidth(180);

        jthotdogs.getColumnModel().getColumn(2).setPreferredWidth(70); //Nombre
        jthotdogs.getColumnModel().getColumn(2).setMaxWidth(120);
        jthotdogs.getColumnModel().getColumn(2).setMinWidth(70);

        jthotdogs.getColumnModel().getColumn(3).setPreferredWidth(120); //Nombre
        jthotdogs.getColumnModel().getColumn(3).setMaxWidth(120);
        jthotdogs.getColumnModel().getColumn(3).setMinWidth(120);
        jthotdogs.setDefaultRenderer(Object.class, new Imgtabla());
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            st = conexion.createStatement();
            st.executeUpdate("use prueba");

            //Seleccionar datos
            rs = st.executeQuery("select `codigo`, `descripcion`, `precio`, `cantidad`, `categoria`, `imagen` from productos where categoria='HOTDOGS'");
            md = (DefaultTableModel) jthotdogs.getModel();
            md.setRowCount(0);
            try {
                jthotdogs.setRowHeight(40);
                while (rs.next()) {
                    String RUTA = "/imgcatalogo/" + rs.getString(6).trim();
                    Object[] fila = (new Object[]{rs.getString(1), rs.getString(2), "$" + rs.getString(3), new JLabel(new ImageIcon(getClass().getResource("" + RUTA + "")))});
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

    public void Tortasytacos() {
        String data[][] = {};
        String cabeza[] = {"Codigo", "Descripcion", "Precio", "Imagen"};///definimos nombre cada columna en encabezado
        jttortasytacos.getTableHeader().setReorderingAllowed(false);//evitamos que no se pueda reordenar jtplatos 

        md = new DefaultTableModel(data, cabeza) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //   if (column != 4) {
                if (column != 4) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        jttortasytacos.setModel(md); //igualamos en modelo en jplatos
        JTableHeader th;
        th = jttortasytacos.getTableHeader();
        th.setFont(new java.awt.Font("tahoma", 0, 14));//seteamos fuente en el header
        //<Centrar el encabezado de la tabla>
        TableCellRenderer rendererFromHeader = jttortasytacos.getTableHeader().getDefaultRenderer();//
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.LEFT);
        /////////////////////////////////////////////////////////>
        jttortasytacos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jttortasytacos.getColumnModel().getColumn(0).setPreferredWidth(60); //Matrícula
        jttortasytacos.getColumnModel().getColumn(0).setMaxWidth(300);
        jttortasytacos.getColumnModel().getColumn(0).setMinWidth(60);

        jttortasytacos.getColumnModel().getColumn(1).setPreferredWidth(180); //Matrícula
        jttortasytacos.getColumnModel().getColumn(1).setMaxWidth(300);
        jttortasytacos.getColumnModel().getColumn(1).setMinWidth(180);

        jttortasytacos.getColumnModel().getColumn(2).setPreferredWidth(70); //Nombre
        jttortasytacos.getColumnModel().getColumn(2).setMaxWidth(120);
        jttortasytacos.getColumnModel().getColumn(2).setMinWidth(70);

        jttortasytacos.getColumnModel().getColumn(3).setPreferredWidth(120); //Nombre
        jttortasytacos.getColumnModel().getColumn(3).setMaxWidth(120);
        jttortasytacos.getColumnModel().getColumn(3).setMinWidth(120);
        jttortasytacos.setDefaultRenderer(Object.class, new Imgtabla());
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            st = conexion.createStatement();
            st.executeUpdate("use prueba");

            //Seleccionar datos
            rs = st.executeQuery("select `codigo`, `descripcion`, `precio`, `cantidad`, `categoria`, `imagen` from productos where categoria='TORTAS Y TACOS'");
            md = (DefaultTableModel) jttortasytacos.getModel();
            md.setRowCount(0);
            try {
                jttortasytacos.setRowHeight(40);
                while (rs.next()) {
                    String RUTA = "/imgcatalogo/" + rs.getString(6).trim();
                    Object[] fila = (new Object[]{rs.getString(1), rs.getString(2), "$" + rs.getString(3), new JLabel(new ImageIcon(getClass().getResource("" + RUTA + "")))});
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

    public void Bebidas() {
        String data[][] = {};
        String cabeza[] = {"Codigo", "Descripcion", "Precio", "Imagen"};///definimos nombre cada columna en encabezado

        jtbebidas.getTableHeader().setReorderingAllowed(false);
        md = new DefaultTableModel(data, cabeza) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column != 4) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        jtbebidas.setModel(md);
        JTableHeader th;
        th = jtbebidas.getTableHeader();
        th.setFont(new java.awt.Font("tahoma", 0, 14));

        //Centrar el encabezado de la tabla
        TableCellRenderer rendererFromHeader = jtbebidas.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.LEFT);

        jtbebidas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jtbebidas.getColumnModel().getColumn(0).setPreferredWidth(60); //Matrícula
        jtbebidas.getColumnModel().getColumn(0).setMaxWidth(300);
        jtbebidas.getColumnModel().getColumn(0).setMinWidth(60);

        jtbebidas.getColumnModel().getColumn(1).setPreferredWidth(180); //Matrícula
        jtbebidas.getColumnModel().getColumn(1).setMaxWidth(300);
        jtbebidas.getColumnModel().getColumn(1).setMinWidth(180);

        jtbebidas.getColumnModel().getColumn(2).setPreferredWidth(70); //Nombre
        jtbebidas.getColumnModel().getColumn(2).setMaxWidth(120);
        jtbebidas.getColumnModel().getColumn(2).setMinWidth(70);

        jtbebidas.getColumnModel().getColumn(3).setPreferredWidth(120); //Nombre
        jtbebidas.getColumnModel().getColumn(3).setMaxWidth(120);
        jtbebidas.getColumnModel().getColumn(3).setMinWidth(120);
        jtbebidas.setDefaultRenderer(Object.class, new Imgtabla());
        try {
            //MEMO HERDEZ PACITO

            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            st = conexion.createStatement();
            st.executeUpdate("use prueba");

            rs = st.executeQuery("SELECT `codigo`, `descripcion`, `precio`, `cantidad`, `categoria`, `imagen` from productos where categoria='bebidas'");
            md = (DefaultTableModel) jtbebidas.getModel();
            md.setRowCount(0);
            try {

                jtbebidas.setRowHeight(40);
                while (rs.next()) {
                    String RUTA = "/imgcatalogo/" + rs.getString(6).trim();
                    //       System.out.println("><ruta"+RUTA);
                    Object[] fila = (new Object[]{rs.getString(1), rs.getString(2), "$" + rs.getString(3), new JLabel(new ImageIcon(getClass().getResource("" + RUTA + "")))});
                    md.addRow(fila);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + "No se encontro el servidor");
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "No se encontro el servidor");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sumar() {
        double r = 0;
        for (int x = 0; x < jtfinal.getRowCount(); x++) {
            String vprecio = ((String) jtfinal.getValueAt(x, 2));//obtener valor de precio
            int vcantidad = ((int) jtfinal.getValueAt(x, 3));///obtienes el valor de la cantidad
            String vprecioformateado = vprecio.replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", "");//dejameos solo los elementos"[^0-1-2-3-4-5-6-7-8-9-.00]"
            double vprecioparseado = Double.parseDouble(vprecioformateado);
            r += vprecioparseado * vcantidad;
        }

        txttotal.setText("");
        txttotal.setText("$" + r);
        txttotaldlls.setText("");
        DecimalFormat df = new DecimalFormat("#0.00");
        double totaldll = r / rcambio;
        txttotaldlls.setText("$" + df.format(totaldll));
        Totaldearticulos();
    }

    public void Antojos() {
        String data[][] = {};
        String cabeza[] = {"Codigo", "Descripcion", "Precio", "Imagen"};///definimos nombre cada columna en encabezado
        jtantojos.getTableHeader().setReorderingAllowed(false);//evitamos que no se pueda reordenar jtplatos 

        md = new DefaultTableModel(data, cabeza) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //   if (column != 4) {
                if (column != 4) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        jtantojos.setModel(md); //igualamos en modelo en jplatos
        JTableHeader th;
        th = jtantojos.getTableHeader();
        th.setFont(new java.awt.Font("tahoma", 0, 14));//seteamos fuente en el header
        //<Centrar el encabezado de la tabla>
        TableCellRenderer rendererFromHeader = jtantojos.getTableHeader().getDefaultRenderer();//
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.LEFT);
        /////////////////////////////////////////////////////////>
        jtantojos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jtantojos.getColumnModel().getColumn(0).setPreferredWidth(60); //Matrícula
        jtantojos.getColumnModel().getColumn(0).setMaxWidth(300);
        jtantojos.getColumnModel().getColumn(0).setMinWidth(60);

        jtantojos.getColumnModel().getColumn(1).setPreferredWidth(180); //Matrícula
        jtantojos.getColumnModel().getColumn(1).setMaxWidth(300);
        jtantojos.getColumnModel().getColumn(1).setMinWidth(180);

        jtantojos.getColumnModel().getColumn(2).setPreferredWidth(70); //Nombre
        jtantojos.getColumnModel().getColumn(2).setMaxWidth(120);
        jtantojos.getColumnModel().getColumn(2).setMinWidth(70);

        jtantojos.getColumnModel().getColumn(3).setPreferredWidth(120); //Nombre
        jtantojos.getColumnModel().getColumn(3).setMaxWidth(120);
        jtantojos.getColumnModel().getColumn(3).setMinWidth(120);
        jtantojos.setDefaultRenderer(Object.class, new Imgtabla());
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            st = conexion.createStatement();
            st.executeUpdate("use prueba");

            //Seleccionar datos
            rs = st.executeQuery("select `codigo`, `descripcion`, `precio`, `cantidad`, `categoria`, `imagen` from productos where categoria='ANTOJO'");
            md = (DefaultTableModel) jtantojos.getModel();
            md.setRowCount(0);
            try {
                jtantojos.setRowHeight(40);
                while (rs.next()) {
                    String RUTA = "/imgcatalogo/" + rs.getString(6).trim();
                    Object[] fila = (new Object[]{rs.getString(1), rs.getString(2), "$" + rs.getString(3), new JLabel(new ImageIcon(getClass().getResource("" + RUTA + "")))});
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

    public void Boneless() {
        String data[][] = {};
        String cabeza[] = {"Codigo", "Descripcion", "Precio", "Imagen"};///definimos nombre cada columna en encabezado
        jtboneless.getTableHeader().setReorderingAllowed(false);//evitamos que no se pueda reordenar jtplatos 

        md = new DefaultTableModel(data, cabeza) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //   if (column != 4) {
                if (column != 4) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        jtboneless.setModel(md); //igualamos en modelo en jplatos
        JTableHeader th;
        th = jtboneless.getTableHeader();
        th.setFont(new java.awt.Font("tahoma", 0, 14));//seteamos fuente en el header
        //<Centrar el encabezado de la tabla>
        TableCellRenderer rendererFromHeader = jtboneless.getTableHeader().getDefaultRenderer();//
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.LEFT);
        /////////////////////////////////////////////////////////>
        jtboneless.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jtboneless.getColumnModel().getColumn(0).setPreferredWidth(60); //Matrícula
        jtboneless.getColumnModel().getColumn(0).setMaxWidth(300);
        jtboneless.getColumnModel().getColumn(0).setMinWidth(60);

        jtboneless.getColumnModel().getColumn(1).setPreferredWidth(180); //Matrícula
        jtboneless.getColumnModel().getColumn(1).setMaxWidth(300);
        jtboneless.getColumnModel().getColumn(1).setMinWidth(180);

        jtboneless.getColumnModel().getColumn(2).setPreferredWidth(70); //Nombre
        jtboneless.getColumnModel().getColumn(2).setMaxWidth(120);
        jtboneless.getColumnModel().getColumn(2).setMinWidth(70);

        jtboneless.getColumnModel().getColumn(3).setPreferredWidth(120); //Nombre
        jtboneless.getColumnModel().getColumn(3).setMaxWidth(120);
        jtboneless.getColumnModel().getColumn(3).setMinWidth(120);
        jtboneless.setDefaultRenderer(Object.class, new Imgtabla());
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            st = conexion.createStatement();
            st.executeUpdate("use prueba");

            //Seleccionar datos
            rs = st.executeQuery("select `codigo`, `descripcion`, `precio`, `cantidad`, `categoria`, `imagen` from productos where categoria='boneless'");
            md = (DefaultTableModel) jtboneless.getModel();
            md.setRowCount(0);
            try {
                jtboneless.setRowHeight(40);
                while (rs.next()) {
                    String RUTA = "/imgcatalogo/" + rs.getString(6).trim();
                    Object[] fila = (new Object[]{rs.getString(1), rs.getString(2), "$" + rs.getString(3), new JLabel(new ImageIcon(getClass().getResource("" + RUTA + "")))});
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu1 = new java.awt.PopupMenu();
        panelmenu = new javax.swing.JTabbedPane();
        jpfondo = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jthamburguesas = new javax.swing.JTable(){
            public boolean isCellEditable (int rowIndex, int colIndex) {
                return false; // No permitir la edición de ninguna celda
            }};
            jpingredentes = new javax.swing.JPanel();
            ch1 = new javax.swing.JCheckBox();
            ch4 = new javax.swing.JCheckBox();
            ch2 = new javax.swing.JCheckBox();
            ch5 = new javax.swing.JCheckBox();
            ch6 = new javax.swing.JCheckBox();
            ch3 = new javax.swing.JCheckBox();
            txt_hamburguesa = new javax.swing.JTextField();
            editbuscar = new javax.swing.JLabel();
            btnagregarajtfinal = new javax.swing.JButton();
            btncontodo = new javax.swing.JButton();
            btnsinnada = new javax.swing.JButton();
            txt_nota = new javax.swing.JTextField();
            jLabel6 = new javax.swing.JLabel();
            Ordenes = new javax.swing.JPanel();
            jScrollPane8 = new javax.swing.JScrollPane();
            jtbebidas = new javax.swing.JTable(){
                public boolean isCellEditable (int rowIndex, int colIndex) {
                    return false; // No permitir la edición de ninguna celda
                }};
                btnagregarajtfinal1 = new javax.swing.JButton();
                editbuscar1 = new javax.swing.JLabel();
                txt_bebidas = new javax.swing.JTextField();
                Ordenes2 = new javax.swing.JPanel();
                jScrollPane6 = new javax.swing.JScrollPane();
                jtboneless = new javax.swing.JTable();
                editbuscar4 = new javax.swing.JLabel();
                txt_boneless = new javax.swing.JTextField();
                btnagregarajtfinal3 = new javax.swing.JButton();
                txt_notaboneless = new javax.swing.JTextField();
                jLabel12 = new javax.swing.JLabel();
                Ordenes1 = new javax.swing.JPanel();
                editbuscar3 = new javax.swing.JLabel();
                txt_antojos = new javax.swing.JTextField();
                jScrollPane9 = new javax.swing.JScrollPane();
                jtantojos = new javax.swing.JTable(){
                    public boolean isCellEditable (int rowIndex, int colIndex) {
                        return false; // No permitir la edición de ninguna celda
                    }};
                    btnsinnada1 = new javax.swing.JButton();
                    btncontodo1 = new javax.swing.JButton();
                    jLabel10 = new javax.swing.JLabel();
                    txt_notaantojo = new javax.swing.JTextField();
                    btnagregarajtfinalantojos = new javax.swing.JButton();
                    Menu = new javax.swing.JPanel();
                    filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
                    jLabel7 = new javax.swing.JLabel();
                    txt_codigo1 = new javax.swing.JTextField();
                    jScrollPane7 = new javax.swing.JScrollPane();
                    jthotdogs = new javax.swing.JTable(){
                        public boolean isCellEditable (int rowIndex, int colIndex) {
                            return false; // No permitir la edición de ninguna celda
                        }};
                        jPanel2 = new javax.swing.JPanel();
                        ch7 = new javax.swing.JCheckBox();
                        ch8 = new javax.swing.JCheckBox();
                        ch9 = new javax.swing.JCheckBox();
                        ch10 = new javax.swing.JCheckBox();
                        ch11 = new javax.swing.JCheckBox();
                        ch12 = new javax.swing.JCheckBox();
                        ch25 = new javax.swing.JCheckBox();
                        btnNada1 = new javax.swing.JToggleButton();
                        btnTodo1 = new javax.swing.JToggleButton();
                        btnagregarhotdog = new javax.swing.JToggleButton();
                        Menu1 = new javax.swing.JPanel();
                        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
                        editbuscar2 = new javax.swing.JLabel();
                        txt_tortas = new javax.swing.JTextField();
                        jScrollPane5 = new javax.swing.JScrollPane();
                        jttortasytacos = new javax.swing.JTable(){
                            public boolean isCellEditable (int rowIndex, int colIndex) {
                                return false; // No permitir la edición de ninguna celda
                            }};
                            btnagregarajtfinal2 = new javax.swing.JButton();
                            jpingredentestortasytacos = new javax.swing.JPanel();
                            ch19 = new javax.swing.JCheckBox();
                            ch20 = new javax.swing.JCheckBox();
                            ch21 = new javax.swing.JCheckBox();
                            ch22 = new javax.swing.JCheckBox();
                            ch23 = new javax.swing.JCheckBox();
                            ch24 = new javax.swing.JCheckBox();
                            txt_notaTortasytacos = new javax.swing.JTextField();
                            jLabel9 = new javax.swing.JLabel();
                            btncontodo2 = new javax.swing.JButton();
                            btnsinnada2 = new javax.swing.JButton();
                            jScrollPane2 = new javax.swing.JScrollPane();
                            jtfinal = new javax.swing.JTable();
                            jLabel3 = new javax.swing.JLabel();
                            txttotal = new javax.swing.JLabel();
                            jLabel4 = new javax.swing.JLabel();
                            jLabel5 = new javax.swing.JLabel();
                            txttotaldlls = new javax.swing.JLabel();
                            btnconfirmar = new javax.swing.JButton();
                            txttotal1 = new javax.swing.JLabel();
                            txttotalarticulos = new javax.swing.JLabel();
                            btnEliminarpieza = new javax.swing.JButton();
                            btncambiar = new javax.swing.JButton();
                            Limpiartblfinal = new javax.swing.JButton();
                            jLabel1 = new javax.swing.JLabel();
                            jLabel2 = new javax.swing.JLabel();
                            jLabel8 = new javax.swing.JLabel();
                            txtcajero = new javax.swing.JLabel();
                            txtfolio = new javax.swing.JLabel();
                            txtdolar = new javax.swing.JLabel();
                            btnconfiguracion = new javax.swing.JButton();
                            btnmonitor = new javax.swing.JButton();
                            jLabel11 = new javax.swing.JLabel();

                            popupMenu1.setLabel("popupMenu1");

                            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                            setTitle("Bienvenido Punto de venta");
                            setBackground(new java.awt.Color(255, 255, 51));
                            setResizable(false);
                            addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    formKeyPressed(evt);
                                }
                            });
                            getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                            jthamburguesas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                            jthamburguesas.setModel(new javax.swing.table.DefaultTableModel(
                                new Object [][] {
                                    {null, null, null}
                                },
                                new String [] {
                                    "Title 1", "Title 2", "Title 3"
                                }
                            ) {
                                boolean[] canEdit = new boolean [] {
                                    false, true, true
                                };

                                public boolean isCellEditable(int rowIndex, int columnIndex) {
                                    return canEdit [columnIndex];
                                }
                            });
                            jthamburguesas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                            jthamburguesas.setIntercellSpacing(new java.awt.Dimension(0, 0));
                            jthamburguesas.setRowHeight(1);
                            jthamburguesas.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    jthamburguesasMouseClicked(evt);
                                }
                            });
                            jScrollPane4.setViewportView(jthamburguesas);
                            if (jthamburguesas.getColumnModel().getColumnCount() > 0) {
                                jthamburguesas.getColumnModel().getColumn(0).setResizable(false);
                            }

                            jpingredentes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                            jpingredentes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                            ch1.setText("SIN CEBOLLA");
                            ch1.setPreferredSize(new java.awt.Dimension(90, 22));
                            jpingredentes.add(ch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 110, -1));

                            ch4.setText("SIN TOMATE");
                            ch4.setPreferredSize(new java.awt.Dimension(90, 22));
                            jpingredentes.add(ch4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 60, 120, 20));

                            ch2.setText("SIN CHILE");
                            ch2.setPreferredSize(new java.awt.Dimension(90, 22));
                            jpingredentes.add(ch2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 20, 102, -1));

                            ch5.setText("SIN MOSTAZA");
                            ch5.setPreferredSize(new java.awt.Dimension(90, 22));
                            jpingredentes.add(ch5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 3, 110, 20));

                            ch6.setText("SIN KETCHUP");
                            ch6.setPreferredSize(new java.awt.Dimension(90, 22));
                            ch6.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    ch6ActionPerformed(evt);
                                }
                            });
                            jpingredentes.add(ch6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 120, -1));

                            ch3.setText("SIN PEPINILLOS");
                            ch3.setPreferredSize(new java.awt.Dimension(90, 22));
                            jpingredentes.add(ch3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 40, 140, -1));

                            txt_hamburguesa.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyReleased(java.awt.event.KeyEvent evt) {
                                    txt_hamburguesaKeyReleased(evt);
                                }
                            });

                            editbuscar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            editbuscar.setText("Buscar:");

                            btnagregarajtfinal.setText("Agregar");
                            btnagregarajtfinal.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnagregarajtfinalActionPerformed(evt);
                                }
                            });

                            btncontodo.setText("Con todo");
                            btncontodo.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btncontodoActionPerformed(evt);
                                }
                            });

                            btnsinnada.setText("Sin nada");
                            btnsinnada.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnsinnadaActionPerformed(evt);
                                }
                            });

                            jLabel6.setText("Anotaciones, Detalles,Nombre cliente:");

                            javax.swing.GroupLayout jpfondoLayout = new javax.swing.GroupLayout(jpfondo);
                            jpfondo.setLayout(jpfondoLayout);
                            jpfondoLayout.setHorizontalGroup(
                                jpfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jpfondoLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jpfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jpfondoLayout.createSequentialGroup()
                                            .addGroup(jpfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jpfondoLayout.createSequentialGroup()
                                                    .addGroup(jpfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(jpfondoLayout.createSequentialGroup()
                                                            .addComponent(jpingredentes, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                                        .addGroup(jpfondoLayout.createSequentialGroup()
                                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addGap(14, 14, 14)))
                                                    .addGroup(jpfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(btncontodo, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                                        .addComponent(btnsinnada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addComponent(txt_nota))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnagregarajtfinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jpfondoLayout.createSequentialGroup()
                                            .addComponent(editbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txt_hamburguesa, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(16, Short.MAX_VALUE))
                            );
                            jpfondoLayout.setVerticalGroup(
                                jpfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jpfondoLayout.createSequentialGroup()
                                    .addGap(9, 9, 9)
                                    .addGroup(jpfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_hamburguesa, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                        .addComponent(editbuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jpfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnagregarajtfinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jpfondoLayout.createSequentialGroup()
                                            .addGroup(jpfondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jpingredentes, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jpfondoLayout.createSequentialGroup()
                                                    .addComponent(btnsinnada)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btncontodo)))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel6)
                                            .addGap(23, 23, 23)
                                            .addComponent(txt_nota, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)))
                                    .addContainerGap())
                            );

                            panelmenu.addTab("Hamburguesas", jpfondo);

                            jtbebidas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                            jtbebidas.setModel(new javax.swing.table.DefaultTableModel(
                                new Object [][] {
                                    {null, null, null}
                                },
                                new String [] {
                                    "Title 1", "Title 2", "Title 3"
                                }
                            ) {
                                boolean[] canEdit = new boolean [] {
                                    false, true, true
                                };

                                public boolean isCellEditable(int rowIndex, int columnIndex) {
                                    return canEdit [columnIndex];
                                }
                            });
                            jtbebidas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                            jtbebidas.setIntercellSpacing(new java.awt.Dimension(0, 0));
                            jtbebidas.setRowHeight(1);
                            jtbebidas.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    jtbebidasMouseClicked(evt);
                                }
                            });
                            jScrollPane8.setViewportView(jtbebidas);
                            if (jtbebidas.getColumnModel().getColumnCount() > 0) {
                                jtbebidas.getColumnModel().getColumn(0).setResizable(false);
                            }

                            btnagregarajtfinal1.setText("Agregar");
                            btnagregarajtfinal1.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnagregarajtfinal1ActionPerformed(evt);
                                }
                            });

                            editbuscar1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            editbuscar1.setText("Buscar:");

                            txt_bebidas.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyReleased(java.awt.event.KeyEvent evt) {
                                    txt_bebidasKeyReleased(evt);
                                }
                            });

                            javax.swing.GroupLayout OrdenesLayout = new javax.swing.GroupLayout(Ordenes);
                            Ordenes.setLayout(OrdenesLayout);
                            OrdenesLayout.setHorizontalGroup(
                                OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(OrdenesLayout.createSequentialGroup()
                                    .addGroup(OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(OrdenesLayout.createSequentialGroup()
                                            .addContainerGap()
                                            .addGroup(OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(OrdenesLayout.createSequentialGroup()
                                                    .addComponent(editbuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txt_bebidas, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(OrdenesLayout.createSequentialGroup()
                                            .addGap(339, 339, 339)
                                            .addComponent(btnagregarajtfinal1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(16, Short.MAX_VALUE))
                            );
                            OrdenesLayout.setVerticalGroup(
                                OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(OrdenesLayout.createSequentialGroup()
                                    .addGap(9, 9, 9)
                                    .addGroup(OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_bebidas, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                        .addComponent(editbuscar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnagregarajtfinal1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                    .addContainerGap())
                            );

                            panelmenu.addTab("Refrescos", Ordenes);

                            jtboneless.setModel(new javax.swing.table.DefaultTableModel(
                                new Object [][] {

                                },
                                new String [] {
                                    "Title 1", "Title 2", "Title 3"
                                }
                            ));
                            jtboneless.setRowHeight(32);
                            jtboneless.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    jtbonelessMouseClicked(evt);
                                }
                            });
                            jScrollPane6.setViewportView(jtboneless);

                            editbuscar4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            editbuscar4.setText("Buscar:");

                            txt_boneless.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyReleased(java.awt.event.KeyEvent evt) {
                                    txt_bonelessKeyReleased(evt);
                                }
                            });

                            btnagregarajtfinal3.setText("Agregar");
                            btnagregarajtfinal3.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnagregarajtfinal3ActionPerformed(evt);
                                }
                            });

                            jLabel12.setText("Anotaciones, Detalles,Nombre cliente:");

                            javax.swing.GroupLayout Ordenes2Layout = new javax.swing.GroupLayout(Ordenes2);
                            Ordenes2.setLayout(Ordenes2Layout);
                            Ordenes2Layout.setHorizontalGroup(
                                Ordenes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(Ordenes2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(Ordenes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addGroup(Ordenes2Layout.createSequentialGroup()
                                            .addComponent(editbuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(12, 12, 12)
                                            .addComponent(txt_boneless, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Ordenes2Layout.createSequentialGroup()
                                            .addGroup(Ordenes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(Ordenes2Layout.createSequentialGroup()
                                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGap(100, 100, 100))
                                                .addComponent(txt_notaboneless, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                            .addComponent(btnagregarajtfinal3)))
                                    .addContainerGap())
                            );
                            Ordenes2Layout.setVerticalGroup(
                                Ordenes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(Ordenes2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(Ordenes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_boneless, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                        .addComponent(editbuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addGroup(Ordenes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnagregarajtfinal3, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(Ordenes2Layout.createSequentialGroup()
                                            .addComponent(jLabel12)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txt_notaboneless, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(20, 20, 20))
                            );

                            panelmenu.addTab("Boneless & Alitas", Ordenes2);

                            editbuscar3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            editbuscar3.setText("Buscar:");

                            txt_antojos.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyReleased(java.awt.event.KeyEvent evt) {
                                    txt_antojosKeyReleased(evt);
                                }
                            });

                            jtantojos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                            jtantojos.setModel(new javax.swing.table.DefaultTableModel(
                                new Object [][] {
                                    {null, null, null}
                                },
                                new String [] {
                                    "Title 1", "Title 2", "Title 3"
                                }
                            ) {
                                boolean[] canEdit = new boolean [] {
                                    false, true, true
                                };

                                public boolean isCellEditable(int rowIndex, int columnIndex) {
                                    return canEdit [columnIndex];
                                }
                            });
                            jtantojos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                            jtantojos.setIntercellSpacing(new java.awt.Dimension(0, 0));
                            jtantojos.setRowHeight(1);
                            jtantojos.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    jtantojosMouseClicked(evt);
                                }
                            });
                            jScrollPane9.setViewportView(jtantojos);
                            if (jtantojos.getColumnModel().getColumnCount() > 0) {
                                jtantojos.getColumnModel().getColumn(0).setResizable(false);
                            }

                            btnsinnada1.setText("Sin nada");
                            btnsinnada1.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnsinnada1ActionPerformed(evt);
                                }
                            });

                            btncontodo1.setText("Con todo");
                            btncontodo1.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btncontodo1ActionPerformed(evt);
                                }
                            });

                            jLabel10.setText("Anotaciones, Detalles,Nombre cliente:");

                            btnagregarajtfinalantojos.setText("Agregar");
                            btnagregarajtfinalantojos.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnagregarajtfinalantojosActionPerformed(evt);
                                }
                            });

                            javax.swing.GroupLayout Ordenes1Layout = new javax.swing.GroupLayout(Ordenes1);
                            Ordenes1.setLayout(Ordenes1Layout);
                            Ordenes1Layout.setHorizontalGroup(
                                Ordenes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(Ordenes1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(Ordenes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(Ordenes1Layout.createSequentialGroup()
                                            .addGroup(Ordenes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(Ordenes1Layout.createSequentialGroup()
                                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(14, 14, 14)
                                                    .addGroup(Ordenes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(btncontodo1, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                                        .addComponent(btnsinnada1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addComponent(txt_notaantojo))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnagregarajtfinalantojos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(Ordenes1Layout.createSequentialGroup()
                                            .addComponent(editbuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(12, 12, 12)
                                            .addComponent(txt_antojos, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(16, Short.MAX_VALUE))
                            );
                            Ordenes1Layout.setVerticalGroup(
                                Ordenes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(Ordenes1Layout.createSequentialGroup()
                                    .addGap(9, 9, 9)
                                    .addGroup(Ordenes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_antojos, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                        .addComponent(editbuscar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(Ordenes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(Ordenes1Layout.createSequentialGroup()
                                            .addComponent(btnsinnada1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btncontodo1)
                                            .addGap(64, 64, 64)
                                            .addComponent(jLabel10)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txt_notaantojo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(btnagregarajtfinalantojos, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                                    .addContainerGap())
                            );

                            panelmenu.addTab("Antojos", Ordenes1);

                            jLabel7.setText("Buscar:");

                            txt_codigo1.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyReleased(java.awt.event.KeyEvent evt) {
                                    txt_codigo1KeyReleased(evt);
                                }
                            });

                            jthotdogs.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                            jthotdogs.setModel(new javax.swing.table.DefaultTableModel(
                                new Object [][] {
                                    {null, null, null}
                                },
                                new String [] {
                                    "Title 1", "Title 2", "Title 3"
                                }
                            ) {
                                boolean[] canEdit = new boolean [] {
                                    false, true, true
                                };

                                public boolean isCellEditable(int rowIndex, int columnIndex) {
                                    return canEdit [columnIndex];
                                }
                            });
                            jthotdogs.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                            jthotdogs.setIntercellSpacing(new java.awt.Dimension(0, 0));
                            jthotdogs.setRowHeight(1);
                            jthotdogs.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    jthotdogsMouseClicked(evt);
                                }
                            });
                            jthotdogs.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    jthotdogsKeyPressed(evt);
                                }
                                public void keyTyped(java.awt.event.KeyEvent evt) {
                                    jthotdogsKeyTyped(evt);
                                }
                            });
                            jScrollPane7.setViewportView(jthotdogs);
                            if (jthotdogs.getColumnModel().getColumnCount() > 0) {
                                jthotdogs.getColumnModel().getColumn(0).setResizable(false);
                            }

                            jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                            jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                            ch7.setText("SIN CEBOLLA");
                            ch7.setPreferredSize(new java.awt.Dimension(90, 22));
                            jPanel2.add(ch7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 110, -1));

                            ch8.setText("SIN TOMATE");
                            ch8.setPreferredSize(new java.awt.Dimension(90, 22));
                            jPanel2.add(ch8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 2, 120, 20));

                            ch9.setText("SIN CHILE");
                            ch9.setPreferredSize(new java.awt.Dimension(90, 22));
                            jPanel2.add(ch9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 20, 102, -1));

                            ch10.setText("SIN QUESO");
                            ch10.setPreferredSize(new java.awt.Dimension(90, 22));
                            jPanel2.add(ch10, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 120, 20));

                            ch11.setText("SIN KETCHUP");
                            ch11.setPreferredSize(new java.awt.Dimension(90, 22));
                            ch11.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    ch11ActionPerformed(evt);
                                }
                            });
                            jPanel2.add(ch11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 40, 140, -1));

                            ch12.setText("SIN PEPINILLOS");
                            ch12.setPreferredSize(new java.awt.Dimension(90, 22));
                            jPanel2.add(ch12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 60, 140, -1));

                            ch25.setText("SIN MOSTAZA");
                            ch25.setPreferredSize(new java.awt.Dimension(90, 22));
                            jPanel2.add(ch25, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 22, 120, 20));

                            btnNada1.setText("Sin nada");
                            btnNada1.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnNada1ActionPerformed(evt);
                                }
                            });

                            btnTodo1.setText("Todo");
                            btnTodo1.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnTodo1ActionPerformed(evt);
                                }
                            });

                            btnagregarhotdog.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                            btnagregarhotdog.setText("Añadir");
                            btnagregarhotdog.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnagregarhotdogActionPerformed(evt);
                                }
                            });

                            javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
                            Menu.setLayout(MenuLayout);
                            MenuLayout.setHorizontalGroup(
                                MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(MenuLayout.createSequentialGroup()
                                    .addGap(265, 265, 265)
                                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 180, Short.MAX_VALUE))
                                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(MenuLayout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(MenuLayout.createSequentialGroup()
                                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(btnNada1, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                                    .addComponent(btnTodo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnagregarhotdog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(MenuLayout.createSequentialGroup()
                                                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(MenuLayout.createSequentialGroup()
                                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txt_codigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addGap(14, 14, 14)))
                            );
                            MenuLayout.setVerticalGroup(
                                MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuLayout.createSequentialGroup()
                                    .addContainerGap(592, Short.MAX_VALUE)
                                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(40, 40, 40))
                                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(MenuLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_codigo1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnagregarhotdog, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                                            .addGroup(MenuLayout.createSequentialGroup()
                                                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(MenuLayout.createSequentialGroup()
                                                        .addComponent(btnTodo1)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(btnNada1))
                                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addContainerGap()))
                            );

                            panelmenu.addTab("Hot Dogs", Menu);

                            editbuscar2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                            editbuscar2.setText("Buscar:");

                            txt_tortas.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyReleased(java.awt.event.KeyEvent evt) {
                                    txt_tortasKeyReleased(evt);
                                }
                            });

                            jttortasytacos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                            jttortasytacos.setModel(new javax.swing.table.DefaultTableModel(
                                new Object [][] {
                                    {null, null, null}
                                },
                                new String [] {
                                    "Title 1", "Title 2", "Title 3"
                                }
                            ) {
                                boolean[] canEdit = new boolean [] {
                                    false, true, true
                                };

                                public boolean isCellEditable(int rowIndex, int columnIndex) {
                                    return canEdit [columnIndex];
                                }
                            });
                            jttortasytacos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                            jttortasytacos.setIntercellSpacing(new java.awt.Dimension(0, 0));
                            jttortasytacos.setRowHeight(1);
                            jttortasytacos.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    jttortasytacosytacosMouseClicked(evt);
                                }
                            });
                            jScrollPane5.setViewportView(jttortasytacos);
                            if (jttortasytacos.getColumnModel().getColumnCount() > 0) {
                                jttortasytacos.getColumnModel().getColumn(0).setResizable(false);
                            }

                            btnagregarajtfinal2.setText("Agregar");
                            btnagregarajtfinal2.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnagregarajtfinal2ActionPerformed(evt);
                                }
                            });

                            jpingredentestortasytacos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                            jpingredentestortasytacos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                            ch19.setText("SIN CEBOLLA");
                            ch19.setPreferredSize(new java.awt.Dimension(90, 22));
                            jpingredentestortasytacos.add(ch19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 110, -1));

                            ch20.setText("SIN TOMATE");
                            ch20.setPreferredSize(new java.awt.Dimension(90, 22));
                            jpingredentestortasytacos.add(ch20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 60, 120, 20));

                            ch21.setText("SIN CHILE");
                            ch21.setPreferredSize(new java.awt.Dimension(90, 22));
                            jpingredentestortasytacos.add(ch21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 20, 102, -1));

                            ch22.setText("SIN MOSTAZA");
                            ch22.setPreferredSize(new java.awt.Dimension(90, 22));
                            jpingredentestortasytacos.add(ch22, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 3, 110, 20));

                            ch23.setText("SIN KETCHUP");
                            ch23.setPreferredSize(new java.awt.Dimension(90, 22));
                            ch23.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    ch23ActionPerformed(evt);
                                }
                            });
                            jpingredentestortasytacos.add(ch23, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 120, -1));

                            ch24.setText("SIN PEPINILLOS");
                            ch24.setPreferredSize(new java.awt.Dimension(90, 22));
                            jpingredentestortasytacos.add(ch24, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 40, 140, -1));

                            jLabel9.setText("Anotaciones, Detalles,Nombre cliente:");

                            btncontodo2.setText("Con todo");
                            btncontodo2.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btncontodo2ActionPerformed(evt);
                                }
                            });

                            btnsinnada2.setText("Sin nada");
                            btnsinnada2.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnsinnada2ActionPerformed(evt);
                                }
                            });

                            javax.swing.GroupLayout Menu1Layout = new javax.swing.GroupLayout(Menu1);
                            Menu1.setLayout(Menu1Layout);
                            Menu1Layout.setHorizontalGroup(
                                Menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(Menu1Layout.createSequentialGroup()
                                    .addGroup(Menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Menu1Layout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addGroup(Menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(Menu1Layout.createSequentialGroup()
                                                    .addGap(265, 265, 265)
                                                    .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(Menu1Layout.createSequentialGroup()
                                                    .addGroup(Menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(Menu1Layout.createSequentialGroup()
                                                            .addGap(2, 2, 2)
                                                            .addComponent(jpingredentestortasytacos, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(10, 10, 10)
                                                            .addGroup(Menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(btnsinnada2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(btncontodo2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addComponent(jLabel9)
                                                        .addComponent(txt_notaTortasytacos, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btnagregarajtfinal2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(Menu1Layout.createSequentialGroup()
                                            .addGap(12, 12, 12)
                                            .addGroup(Menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(Menu1Layout.createSequentialGroup()
                                                    .addComponent(editbuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(12, 12, 12)
                                                    .addComponent(txt_tortas, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(16, 16, 16))
                            );
                            Menu1Layout.setVerticalGroup(
                                Menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(Menu1Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(Menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(editbuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_tortas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(7, 7, 7)
                                    .addGroup(Menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(Menu1Layout.createSequentialGroup()
                                            .addGroup(Menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jpingredentestortasytacos, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(Menu1Layout.createSequentialGroup()
                                                    .addGap(7, 7, 7)
                                                    .addComponent(btnsinnada2)
                                                    .addGap(5, 5, 5)
                                                    .addComponent(btncontodo2)))
                                            .addGap(9, 9, 9)
                                            .addComponent(jLabel9)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txt_notaTortasytacos, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(btnagregarajtfinal2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(25, 25, 25))
                            );

                            panelmenu.addTab("Tortas & Tacos", Menu1);

                            getContentPane().add(panelmenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 680));

                            jtfinal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                            jtfinal.setModel(new javax.swing.table.DefaultTableModel(
                                new Object [][] {

                                },
                                new String [] {
                                    "Codigo", "Descripcion", "Precio", "Cantidad", "Detalle"
                                }
                            ) {
                                boolean[] canEdit = new boolean [] {
                                    true, false, false, false, true
                                };

                                public boolean isCellEditable(int rowIndex, int columnIndex) {
                                    return canEdit [columnIndex];
                                }
                            });
                            jtfinal.setRowHeight(32);
                            jScrollPane2.setViewportView(jtfinal);
                            if (jtfinal.getColumnModel().getColumnCount() > 0) {
                                jtfinal.getColumnModel().getColumn(1).setPreferredWidth(100);
                                jtfinal.getColumnModel().getColumn(2).setPreferredWidth(50);
                                jtfinal.getColumnModel().getColumn(3).setPreferredWidth(50);
                                jtfinal.getColumnModel().getColumn(4).setResizable(false);
                            }

                            getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 520, 360));

                            jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                            jLabel3.setText("DLLS");
                            getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 470, 70, 30));

                            txttotal.setBackground(new java.awt.Color(255, 255, 255));
                            txttotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
                            txttotal.setText("$ 0.00");
                            getContentPane().add(txttotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 440, 160, 30));

                            jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                            jLabel4.setText("TOTAL:");
                            getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 360, 210, 30));

                            jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                            jLabel5.setText("MXN");
                            getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 440, 70, 30));

                            txttotaldlls.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
                            txttotaldlls.setText("$ 0.00");
                            getContentPane().add(txttotaldlls, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 470, 160, 30));

                            btnconfirmar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                            btnconfirmar.setForeground(new java.awt.Color(64, 190, 64));
                            btnconfirmar.setText("CONFIRMAR");
                            btnconfirmar.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnconfirmarActionPerformed(evt);
                                }
                            });
                            getContentPane().add(btnconfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 560, 520, 90));

                            txttotal1.setBackground(new java.awt.Color(255, 255, 255));
                            txttotal1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
                            txttotal1.setText("TOTAL DE ARTICULO(S):");
                            getContentPane().add(txttotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 515, 300, 40));

                            txttotalarticulos.setBackground(new java.awt.Color(255, 255, 255));
                            txttotalarticulos.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
                            txttotalarticulos.setForeground(new java.awt.Color(255, 51, 51));
                            txttotalarticulos.setText("0");
                            getContentPane().add(txttotalarticulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 515, 77, 40));

                            btnEliminarpieza.setText("Quitar pieza");
                            btnEliminarpieza.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnEliminarpiezaActionPerformed(evt);
                                }
                            });
                            getContentPane().add(btnEliminarpieza, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 440, 140, 35));

                            btncambiar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                            btncambiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cambiar.png"))); // NOI18N
                            btncambiar.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btncambiarActionPerformed(evt);
                                }
                            });
                            getContentPane().add(btncambiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, 60, 50));

                            Limpiartblfinal.setText("Limpiar todo");
                            Limpiartblfinal.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    LimpiartblfinalActionPerformed(evt);
                                }
                            });
                            getContentPane().add(Limpiartblfinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 440, 130, 35));

                            jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
                            jLabel1.setText("FOLIO:");
                            getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 70, 30));

                            jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
                            jLabel2.setText("CAJERO:");
                            getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 80, 30));

                            jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
                            jLabel8.setText("TIPO CAMBIO:");
                            getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 140, 30));

                            txtcajero.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
                            txtcajero.setForeground(new java.awt.Color(255, 51, 51));
                            txtcajero.setText("INICIE SESION");
                            getContentPane().add(txtcajero, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 380, 30));

                            txtfolio.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
                            txtfolio.setForeground(new java.awt.Color(0, 0, 204));
                            txtfolio.setText("ERROR");
                            getContentPane().add(txtfolio, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, 80, 30));

                            txtdolar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
                            txtdolar.setForeground(new java.awt.Color(0, 204, 0));
                            txtdolar.setText("NA");
                            getContentPane().add(txtdolar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 60, 30));

                            btnconfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/com.png"))); // NOI18N
                            btnconfiguracion.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnconfiguracionActionPerformed(evt);
                                }
                            });
                            getContentPane().add(btnconfiguracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, 60, 50));

                            btnmonitor.setText("Monitor Pedidos");
                            btnmonitor.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnmonitorActionPerformed(evt);
                                }
                            });
                            getContentPane().add(btnmonitor, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 480, 140, 35));

                            jLabel11.setText("     ");
                            getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 670, -1, -1));

                            pack();
                            setLocationRelativeTo(null);
                        }// </editor-fold>//GEN-END:initComponents

    public static void vaciartabla(Object[] sergioelbailador) {
        DefaultTableModel mode = (DefaultTableModel) jtfinal.getModel();
        int filas = mode.getRowCount();///pasamos el total de elementos a filas
        for (int i = 0; filas > i; i++) {
            mode.removeRow(0); //removes las columnas en base a la longitud de el arreglo
        }
        txttotal.setText("$0.00");///seteamos el valor en 0
        txttotaldlls.setText("$0.00");///seteamos el valor en 0
    }

    private void btnconfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconfirmarActionPerformed

        if (jtfinal.getRowCount() > 0) {
            Cobrar cb = new Cobrar();
            String TXTTTOTAL = txttotal.getText();
            String TXTTOTALDLLS = txttotaldlls.getText();
            cb.setVisible(true);
            cb.txttotalc.setText(TXTTTOTAL);
            cb.txttotaldllsc.setText(TXTTOTALDLLS);
            cb.Enviartotal();
            obtenerhoraservidor();
            obtenerfechaservidor();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No has capturado ningun producto");
        }
    }//GEN-LAST:event_btnconfirmarActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        /*    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         JOptionPane.showMessageDialog(null, "Has pulsado Enter");
         }
         if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){             
         }*/
    }//GEN-LAST:event_formKeyPressed
    DefaultTableModel th;

    private void filtro(String consulta, JTable jtableBuscar) {
        th = (DefaultTableModel) jthamburguesas.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(th);
        jtableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }

    private void btnEliminarpiezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarpiezaActionPerformed
        int filaseleccionada = jtfinal.getSelectedRow();
        if (filaseleccionada >= 0) {
            String itemcodigo = (jtfinal.getValueAt(filaseleccionada, 3).toString().trim());
            DefaultTableModel dtm = (DefaultTableModel) jtfinal.getModel(); //TableProducto es el nombre de mi tabla ;)
            dtm.removeRow(jtfinal.getSelectedRow());
            sumar();
        } else {
            JOptionPane.showMessageDialog(null, "No Seleccionaste ningun codigo ");
        }
    }//GEN-LAST:event_btnEliminarpiezaActionPerformed

    private void LimpiartblfinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiartblfinalActionPerformed
        Vaciartabla();
    }//GEN-LAST:event_LimpiartblfinalActionPerformed

    private void btncambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncambiarActionPerformed
        Verusuarios vu = new Verusuarios();
        vu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btncambiarActionPerformed
    DefaultTableModel tb;

    private void filtrobebidas(String consulta, JTable jtableBuscar) {
        tb = (DefaultTableModel) jtbebidas.getModel();
        TableRowSorter<DefaultTableModel> trb = new TableRowSorter<>(tb);
        jtableBuscar.setRowSorter(trb);
        trb.setRowFilter(RowFilter.regexFilter(consulta));
    }
    private void btnconfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconfiguracionActionPerformed
        Configuracion c = new Configuracion();
        c.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnconfiguracionActionPerformed

    private void btnagregarajtfinal2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarajtfinal2ActionPerformed
        Tortasytacosagregarfinal();
    }//GEN-LAST:event_btnagregarajtfinal2ActionPerformed

    private void jttortasytacosytacosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jttortasytacosytacosMouseClicked
        if (evt.getClickCount() == 2) {
            Tortasytacosagregarfinal();
        }
    }//GEN-LAST:event_jttortasytacosytacosMouseClicked

    private void txt_tortasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tortasKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tortasKeyReleased

    private void btnagregarhotdogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarhotdogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnagregarhotdogActionPerformed

    private void btnTodo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTodo1ActionPerformed

    private void btnNada1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNada1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNada1ActionPerformed

    private void ch11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ch11ActionPerformed

    private void jthotdogsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jthotdogsKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jthotdogsKeyTyped

    private void jthotdogsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jthotdogsKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jthotdogsKeyPressed

    private void jthotdogsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jthotdogsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jthotdogsMouseClicked

    private void txt_codigo1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_codigo1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_codigo1KeyReleased

    private void txt_bebidasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bebidasKeyReleased
        filtrobebidas(txt_bebidas.getText().toUpperCase(), jtbebidas);
    }//GEN-LAST:event_txt_bebidasKeyReleased

    private void btnagregarajtfinal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarajtfinal1ActionPerformed
        agregarfinalbebidas();
    }//GEN-LAST:event_btnagregarajtfinal1ActionPerformed

    private void jtbebidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbebidasMouseClicked
        if (evt.getClickCount() == 2) {
            agregarfinalbebidas();
        }
    }//GEN-LAST:event_jtbebidasMouseClicked

    private void btnsinnadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsinnadaActionPerformed
        ch1.setSelected(true);
        ch2.setSelected(true);
        ch3.setSelected(true);
        ch4.setSelected(true);
        ch5.setSelected(true);
        ch6.setSelected(true);
    }//GEN-LAST:event_btnsinnadaActionPerformed

    private void btncontodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncontodoActionPerformed
        ch1.setSelected(false);
        ch2.setSelected(false);
        ch3.setSelected(false);
        ch4.setSelected(false);
        ch5.setSelected(false);
        ch6.setSelected(false);
    }//GEN-LAST:event_btncontodoActionPerformed

    private void btnagregarajtfinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarajtfinalActionPerformed
        Hamburguesaagregarfinal();
    }//GEN-LAST:event_btnagregarajtfinalActionPerformed

    private void txt_hamburguesaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_hamburguesaKeyReleased
        filtro(txt_hamburguesa.getText().toUpperCase(), jthamburguesas);
    }//GEN-LAST:event_txt_hamburguesaKeyReleased

    private void ch6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ch6ActionPerformed

    private void jthamburguesasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jthamburguesasMouseClicked
        if (evt.getClickCount() == 2) {
            Hamburguesaagregarfinal();
        }
    }//GEN-LAST:event_jthamburguesasMouseClicked

    private void ch23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ch23ActionPerformed

    private void btncontodo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncontodo2ActionPerformed
        ch19.setSelected(false);
        ch20.setSelected(false);
        ch21.setSelected(false);
        ch22.setSelected(false);
        ch23.setSelected(false);
        ch24.setSelected(false);
    }//GEN-LAST:event_btncontodo2ActionPerformed

    private void btnsinnada2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsinnada2ActionPerformed
        ch19.setSelected(true);
        ch20.setSelected(true);
        ch21.setSelected(true);
        ch22.setSelected(true);
        ch23.setSelected(true);
        ch24.setSelected(true);
    }//GEN-LAST:event_btnsinnada2ActionPerformed

    private void txt_antojosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_antojosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_antojosKeyReleased

    private void jtantojosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtantojosMouseClicked
          if (evt.getClickCount() == 2) {
              antojosagregarfinal();
          }       
    }//GEN-LAST:event_jtantojosMouseClicked

    private void btnsinnada1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsinnada1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsinnada1ActionPerformed

    private void btncontodo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncontodo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btncontodo1ActionPerformed

    private void btnagregarajtfinalantojosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarajtfinalantojosActionPerformed
        antojosagregarfinal();
    }//GEN-LAST:event_btnagregarajtfinalantojosActionPerformed

    private void btnmonitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmonitorActionPerformed
        Monitor m = new Monitor();
        m.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnmonitorActionPerformed

    private void txt_bonelessKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bonelessKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bonelessKeyReleased

    private void btnagregarajtfinal3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarajtfinal3ActionPerformed
        bonelessagregarfinal();
    }//GEN-LAST:event_btnagregarajtfinal3ActionPerformed

    private void jtbonelessMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbonelessMouseClicked
        if (evt.getClickCount() == 2) {
              bonelessagregarfinal();
          }  
    }//GEN-LAST:event_jtbonelessMouseClicked

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Limpiartblfinal;
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel Menu1;
    private javax.swing.JPanel Ordenes;
    private javax.swing.JPanel Ordenes1;
    private javax.swing.JPanel Ordenes2;
    private javax.swing.JButton btnEliminarpieza;
    private javax.swing.JToggleButton btnNada1;
    private javax.swing.JToggleButton btnTodo1;
    private javax.swing.JButton btnagregarajtfinal;
    private javax.swing.JButton btnagregarajtfinal1;
    private javax.swing.JButton btnagregarajtfinal2;
    private javax.swing.JButton btnagregarajtfinal3;
    private javax.swing.JButton btnagregarajtfinalantojos;
    private javax.swing.JToggleButton btnagregarhotdog;
    private javax.swing.JButton btncambiar;
    private javax.swing.JButton btnconfiguracion;
    private javax.swing.JButton btnconfirmar;
    private javax.swing.JButton btncontodo;
    private javax.swing.JButton btncontodo1;
    private javax.swing.JButton btncontodo2;
    private javax.swing.JButton btnmonitor;
    private javax.swing.JButton btnsinnada;
    private javax.swing.JButton btnsinnada1;
    private javax.swing.JButton btnsinnada2;
    private javax.swing.JCheckBox ch1;
    private javax.swing.JCheckBox ch10;
    private javax.swing.JCheckBox ch11;
    private javax.swing.JCheckBox ch12;
    private javax.swing.JCheckBox ch19;
    private javax.swing.JCheckBox ch2;
    private javax.swing.JCheckBox ch20;
    private javax.swing.JCheckBox ch21;
    private javax.swing.JCheckBox ch22;
    private javax.swing.JCheckBox ch23;
    private javax.swing.JCheckBox ch24;
    private javax.swing.JCheckBox ch25;
    private javax.swing.JCheckBox ch3;
    private javax.swing.JCheckBox ch4;
    private javax.swing.JCheckBox ch5;
    private javax.swing.JCheckBox ch6;
    private javax.swing.JCheckBox ch7;
    private javax.swing.JCheckBox ch8;
    private javax.swing.JCheckBox ch9;
    private javax.swing.JLabel editbuscar;
    private javax.swing.JLabel editbuscar1;
    private javax.swing.JLabel editbuscar2;
    private javax.swing.JLabel editbuscar3;
    private javax.swing.JLabel editbuscar4;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPanel jpfondo;
    private javax.swing.JPanel jpingredentes;
    private javax.swing.JPanel jpingredentestortasytacos;
    private javax.swing.JTable jtantojos;
    private javax.swing.JTable jtbebidas;
    private javax.swing.JTable jtboneless;
    public static javax.swing.JTable jtfinal;
    private javax.swing.JTable jthamburguesas;
    private javax.swing.JTable jthotdogs;
    private javax.swing.JTable jttortasytacos;
    private javax.swing.JTabbedPane panelmenu;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JTextField txt_antojos;
    private javax.swing.JTextField txt_bebidas;
    private javax.swing.JTextField txt_boneless;
    private javax.swing.JTextField txt_codigo1;
    private javax.swing.JTextField txt_hamburguesa;
    public static javax.swing.JTextField txt_nota;
    public static javax.swing.JTextField txt_notaTortasytacos;
    public static javax.swing.JTextField txt_notaantojo;
    public static javax.swing.JTextField txt_notaboneless;
    private javax.swing.JTextField txt_tortas;
    private javax.swing.JLabel txtcajero;
    private javax.swing.JLabel txtdolar;
    private javax.swing.JLabel txtfolio;
    private static javax.swing.JLabel txttotal;
    private static javax.swing.JLabel txttotal1;
    private static javax.swing.JLabel txttotalarticulos;
    private static javax.swing.JLabel txttotaldlls;
    // End of variables declaration//GEN-END:variables
    public void Totaldearticulos() {
        int r = 0;
        for (int x = 0; x < jtfinal.getRowCount(); x++) {
            int vcantidad = Integer.parseInt(jtfinal.getValueAt(x, 3).toString().replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", ""));///obtienes el valor de la cantidad
            r = vcantidad + r;
        }
        txttotalarticulos.setText("" + r);//preciototal
    }

    public void Vaciartabla() {
        DefaultTableModel vt = (DefaultTableModel) jtfinal.getModel();
        for (int i = vt.getRowCount() - 1; i >= 0; i--) {
            vt.removeRow(i);
        }
        txt_nota.setText("");
        sumar();
    }

    public void tablafinal() {
        String data[][] = {};
        String cabeza[] = {"Codigo", "Descripcion", "Precio", "Cantidad", "Detalles", "Nota"};
        jtfinal.getTableHeader().setReorderingAllowed(false);
        md = new DefaultTableModel(data, cabeza) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column != 6) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        jtfinal.setModel(md);
        JTableHeader th;
        th = jtfinal.getTableHeader();
        th.setFont(new java.awt.Font("tahoma", 0, 14));

        //Centrar el encabezado de la tabla
        TableCellRenderer rendererFromHeader = jtfinal.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);

        jtfinal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        jtfinal.getColumnModel().getColumn(0).setPreferredWidth(60);
        jtfinal.getColumnModel().getColumn(0).setMaxWidth(80);
        jtfinal.getColumnModel().getColumn(0).setMinWidth(60);

        jtfinal.getColumnModel().getColumn(1).setPreferredWidth(170);
        jtfinal.getColumnModel().getColumn(1).setMaxWidth(170);
        jtfinal.getColumnModel().getColumn(1).setMinWidth(170);

        jtfinal.getColumnModel().getColumn(2).setPreferredWidth(70);
        jtfinal.getColumnModel().getColumn(2).setMaxWidth(80);
        jtfinal.getColumnModel().getColumn(2).setMinWidth(70);

        jtfinal.getColumnModel().getColumn(3).setPreferredWidth(50);
        jtfinal.getColumnModel().getColumn(3).setMaxWidth(80);
        jtfinal.getColumnModel().getColumn(3).setMinWidth(50);

        jtfinal.getColumnModel().getColumn(4).setPreferredWidth(220);
        jtfinal.getColumnModel().getColumn(4).setMaxWidth(500);
        jtfinal.getColumnModel().getColumn(4).setMinWidth(220);

        jtfinal.getColumnModel().getColumn(5).setPreferredWidth(220);
        jtfinal.getColumnModel().getColumn(5).setMaxWidth(500);
        jtfinal.getColumnModel().getColumn(5).setMinWidth(220);

    }// carga estructura nada mas
}
