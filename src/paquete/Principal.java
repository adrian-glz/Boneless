package paquete;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

 
public class Principal extends javax.swing.JFrame {

    ResultSet rs,rs2;
    int count = 0;
    DefaultTableModel md;
    Statement st;
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    int  folio;
    
    public Principal() {
        initComponents();///inicializamos componentes al inicio del metodo
        Bebidas();//llamamos el metodo de bebidas para llenar tablas
        Hamburguesas();//llamamos el metodo de Comidas para llenar Comidas
        tablafinal();
        jtfinal.getTableHeader().setReorderingAllowed(false);///INHABILITA EL MOVER CABECERAS los titulos de la tabla jtfinal

    }
    public void keyPressed(KeyEvent e){///funcio mediante teclas no utilizada
               /* if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    JOptionPane.showMessageDialog(null, "Has pulsado Enter");
                }
                if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                    System.exit(0);
                }*/
            }
 
    public void agregarfinal(){
       DefaultTableModel model = (DefaultTableModel) jtfinal.getModel();

        int filaseleccionada = jthamburguesas.getSelectedRow();//OBTIENES EL ELEMENTO DE LA TABLA
        if (filaseleccionada >= 0) {
            Object obj0 = (jthamburguesas.getValueAt(filaseleccionada, 0));///OBTIENES EL PRIMER FILA
            Object obj1 = (jthamburguesas.getValueAt(filaseleccionada, 1));///OBTIENES EL PRIMER FILA
            Object obj2 = (jthamburguesas.getValueAt(filaseleccionada, 2));//OBTIENES LA SEGUNDA FILA
            
            /*String cod= obj0.toString();    /// CAMBIAS LOS OBJETOS A TIPO STRING
            String descripcionp = obj1.toString();    /// CAMBIAS LOS OBJETOS A TIPO STRING
            String cantidadp = obj2.toString();///*/

            String combinar = "";
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
                combinar += "SIN KETCHUP";
            }
            else{
            
            combinar="CON TODO";
            }
            model.addRow(new Object[]{obj0,obj1, obj2, 1,combinar});
        }
        sumar();

    }
    public void aumentarfolio(){
            try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.80:55024", "usounds", "madljda");
            Statement st = conexion.createStatement();
            st.executeUpdate("USE cml;");

            ps = conexion.prepareStatement("UPDATE `folios` SET `folio`=folio+1 WHERE `caja`=1      ");

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
    public void obtenerfolio(){
     
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            st = conexion.createStatement();
            st.executeUpdate("use prueba");

            //Seleccionar datos
            rs = st.executeQuery("SELECT folio from folios where caja ='1'");
            md = (DefaultTableModel) jthamburguesas.getModel();
            md.setRowCount(0);
            try {
                jthamburguesas.setRowHeight(40);
                while (rs.next()) {
                    try{
                    folio=rs.getInt(1);
                    }catch( Exception e ){ 
                    this.dispose();
                    } 
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage()+"><><");
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage()+"><");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex+"xd");
        }
     
    }
    public void Hamburguesas() {
        String data[][] = {};
        String cabeza[] = {"Codigo","Descripcion", "Precio","Imagen"};///definimos nombre cada columna en encabezado
      //  String cabeza[] = {"Descripcion", "Precio", "Imagen","xxx"};///definimos nombre cada columna en encabezado
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
            rs = st.executeQuery("SELECT `codigo`, `descripcion`, `precio`, `cantidad`, `categoria`, `imagen` from productos where categoria='hamburguesa'");
            md = (DefaultTableModel) jthamburguesas.getModel();
            md.setRowCount(0);
            try {
                jthamburguesas.setRowHeight(40);
                while (rs.next()) {
                   String RUTA = "/img/" + rs.getString(6);
                   Object[] fila = (new Object[]{rs.getString(1),rs.getString(2),"$"+rs.getString(3),new JLabel(new ImageIcon(getClass().getResource(""+ RUTA + ""))) });
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
        String cabeza[] = {"1", "2", "3", "4"};
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
        th.setFont(new java.awt.Font("tahoma", 0, 18));

        //Centrar el encabezado de la tabla
        TableCellRenderer rendererFromHeader = jtbebidas.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);

        jtbebidas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        jtbebidas.getColumnModel().getColumn(0).setPreferredWidth(170);
        jtbebidas.getColumnModel().getColumn(0).setMaxWidth(170);
        jtbebidas.getColumnModel().getColumn(0).setMinWidth(170);

        jtbebidas.getColumnModel().getColumn(1).setPreferredWidth(120);
        jtbebidas.getColumnModel().getColumn(1).setMaxWidth(120);
        jtbebidas.getColumnModel().getColumn(1).setMinWidth(120);

        jtbebidas.getColumnModel().getColumn(2).setPreferredWidth(150);
        jtbebidas.getColumnModel().getColumn(2).setMaxWidth(150);
        jtbebidas.getColumnModel().getColumn(2).setMinWidth(150);

        jtbebidas.getColumnModel().getColumn(3).setPreferredWidth(150);
        jtbebidas.getColumnModel().getColumn(3).setMaxWidth(150);
        jtbebidas.getColumnModel().getColumn(3).setMinWidth(150);

        jtbebidas.setDefaultRenderer(Object.class, new Imgtabla());
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            st = conexion.createStatement();
            st.executeUpdate("use prueba");

            rs = st.executeQuery("select descripcion,precio,imagen from productos where categoria='bebida'");
            md = (DefaultTableModel) jtbebidas.getModel();
            md.setRowCount(0);
            try {
                jtbebidas.setRowHeight(90);
                while (rs.next()) {
                    String RUTA = "/img/" + rs.getString(3);
                    Object[] fila = (new Object[]{rs.getString(1), "$" + rs.getInt(2) + ".00", new JLabel(new ImageIcon(getClass().getResource("" + RUTA + "")))});
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
        double totaldll = r / 22.50;
        txttotaldlls.setText("$" + df.format(totaldll));
        Totaldearticulos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu1 = new java.awt.PopupMenu();
        panelmenu = new javax.swing.JTabbedPane();
        Bebidas = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jthamburguesas = new javax.swing.JTable(){
            public boolean isCellEditable (int rowIndex, int colIndex) {
                return false; // No permitir la edición de ninguna celda
            }};
            jPanel1 = new javax.swing.JPanel();
            ch1 = new javax.swing.JCheckBox();
            ch4 = new javax.swing.JCheckBox();
            ch2 = new javax.swing.JCheckBox();
            ch5 = new javax.swing.JCheckBox();
            ch6 = new javax.swing.JCheckBox();
            ch3 = new javax.swing.JCheckBox();
            txt_codigo = new javax.swing.JTextField();
            jLabel6 = new javax.swing.JLabel();
            jButton3 = new javax.swing.JButton();
            btnagregar = new javax.swing.JButton();
            jButton4 = new javax.swing.JButton();
            jButton5 = new javax.swing.JButton();
            Ordenes = new javax.swing.JPanel();
            jScrollPane5 = new javax.swing.JScrollPane();
            jtbebidas = new javax.swing.JTable();
            Ordenes2 = new javax.swing.JPanel();
            jScrollPane6 = new javax.swing.JScrollPane();
            jtboneless = new javax.swing.JTable();
            Ordenes1 = new javax.swing.JPanel();
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
                btnNada1 = new javax.swing.JToggleButton();
                btnTodo1 = new javax.swing.JToggleButton();
                btnagregarhotdog = new javax.swing.JToggleButton();
                Menu1 = new javax.swing.JPanel();
                btnagregarplato1 = new javax.swing.JToggleButton();
                filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
                jScrollPane2 = new javax.swing.JScrollPane();
                jtfinal = new javax.swing.JTable();
                c = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                txttotal = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                txttotaldlls = new javax.swing.JLabel();
                btnconfirmar = new javax.swing.JButton();
                txttotal1 = new javax.swing.JLabel();
                txttotalarticulos = new javax.swing.JLabel();
                btnEliminarpieza1 = new javax.swing.JButton();
                jButton1 = new javax.swing.JButton();
                jButton2 = new javax.swing.JButton();

                popupMenu1.setLabel("popupMenu1");

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setBackground(new java.awt.Color(255, 255, 51));
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
                jthamburguesas.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        jthamburguesasKeyPressed(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        jthamburguesasKeyTyped(evt);
                    }
                });
                jScrollPane4.setViewportView(jthamburguesas);
                if (jthamburguesas.getColumnModel().getColumnCount() > 0) {
                    jthamburguesas.getColumnModel().getColumn(0).setResizable(false);
                }

                jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                ch1.setText("SIN CEBOLLA");
                ch1.setPreferredSize(new java.awt.Dimension(90, 22));
                jPanel1.add(ch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 102, -1));

                ch4.setText("SIN TOMATE");
                ch4.setPreferredSize(new java.awt.Dimension(90, 22));
                jPanel1.add(ch4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 2, 100, 20));

                ch2.setText("SIN CHILE");
                ch2.setPreferredSize(new java.awt.Dimension(90, 22));
                jPanel1.add(ch2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 20, 102, -1));

                ch5.setText("SIN MOSTAZA");
                ch5.setPreferredSize(new java.awt.Dimension(90, 22));
                jPanel1.add(ch5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 22, 100, 20));

                ch6.setText("SIN KETCHUP");
                ch6.setPreferredSize(new java.awt.Dimension(90, 22));
                ch6.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        ch6ActionPerformed(evt);
                    }
                });
                jPanel1.add(ch6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 40, 140, -1));

                ch3.setText("SIN PEPINILLOS");
                ch3.setPreferredSize(new java.awt.Dimension(90, 22));
                jPanel1.add(ch3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 60, 140, -1));

                txt_codigo.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txt_codigoKeyReleased(evt);
                    }
                });

                jLabel6.setText("Buscar:");

                jButton3.setText("VER REG");
                jButton3.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton3ActionPerformed(evt);
                    }
                });

                btnagregar.setText("Agregar");
                btnagregar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnagregarActionPerformed(evt);
                    }
                });

                jButton4.setText("Con todo");
                jButton4.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton4ActionPerformed(evt);
                    }
                });

                jButton5.setText("Sin nada");
                jButton5.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton5ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout BebidasLayout = new javax.swing.GroupLayout(Bebidas);
                Bebidas.setLayout(BebidasLayout);
                BebidasLayout.setHorizontalGroup(
                    BebidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BebidasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(BebidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(BebidasLayout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(BebidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnagregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(BebidasLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)))
                        .addContainerGap(18, Short.MAX_VALUE))
                );
                BebidasLayout.setVerticalGroup(
                    BebidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BebidasLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(BebidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(BebidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_codigo)
                                .addComponent(jButton3))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(BebidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BebidasLayout.createSequentialGroup()
                                .addGroup(BebidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(BebidasLayout.createSequentialGroup()
                                        .addComponent(jButton5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton4)))
                                .addGap(0, 112, Short.MAX_VALUE))
                            .addComponent(btnagregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );

                panelmenu.addTab("Hamburguesas", Bebidas);

                jtbebidas.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {
                        {null, null, null}
                    },
                    new String [] {
                        "Title 1", "Title 2", "Title 3"
                    }
                ));
                jtbebidas.setRowHeight(32);
                jScrollPane5.setViewportView(jtbebidas);

                javax.swing.GroupLayout OrdenesLayout = new javax.swing.GroupLayout(Ordenes);
                Ordenes.setLayout(OrdenesLayout);
                OrdenesLayout.setHorizontalGroup(
                    OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                );
                OrdenesLayout.setVerticalGroup(
                    OrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OrdenesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(414, Short.MAX_VALUE))
                );

                panelmenu.addTab("Refrescos", Ordenes);

                jtboneless.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {
                        {null, null, null}
                    },
                    new String [] {
                        "Title 1", "Title 2", "Title 3"
                    }
                ));
                jtboneless.setRowHeight(32);
                jScrollPane6.setViewportView(jtboneless);

                javax.swing.GroupLayout Ordenes2Layout = new javax.swing.GroupLayout(Ordenes2);
                Ordenes2.setLayout(Ordenes2Layout);
                Ordenes2Layout.setHorizontalGroup(
                    Ordenes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                );
                Ordenes2Layout.setVerticalGroup(
                    Ordenes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Ordenes2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(414, Short.MAX_VALUE))
                );

                panelmenu.addTab("Boneless & Alitas", Ordenes2);

                javax.swing.GroupLayout Ordenes1Layout = new javax.swing.GroupLayout(Ordenes1);
                Ordenes1.setLayout(Ordenes1Layout);
                Ordenes1Layout.setHorizontalGroup(
                    Ordenes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 445, Short.MAX_VALUE)
                );
                Ordenes1Layout.setVerticalGroup(
                    Ordenes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 642, Short.MAX_VALUE)
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
                jPanel2.add(ch7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 102, -1));

                ch8.setText("SIN TOMATE");
                ch8.setPreferredSize(new java.awt.Dimension(90, 22));
                jPanel2.add(ch8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 2, 100, 20));

                ch9.setText("SIN CHILE");
                ch9.setPreferredSize(new java.awt.Dimension(90, 22));
                jPanel2.add(ch9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 20, 102, -1));

                ch10.setText("SIN MOSTAZA");
                ch10.setPreferredSize(new java.awt.Dimension(90, 22));
                jPanel2.add(ch10, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 22, 100, 20));

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
                        .addContainerGap(602, Short.MAX_VALUE)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(MenuLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_codigo1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(4, 4, 4)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnagregarhotdog, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
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

                btnagregarplato1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                btnagregarplato1.setText("Añadir");
                btnagregarplato1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnagregarplato1ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout Menu1Layout = new javax.swing.GroupLayout(Menu1);
                Menu1.setLayout(Menu1Layout);
                Menu1Layout.setHorizontalGroup(
                    Menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Menu1Layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addGroup(Menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnagregarplato1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Menu1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 3, Short.MAX_VALUE))
                );
                Menu1Layout.setVerticalGroup(
                    Menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Menu1Layout.createSequentialGroup()
                        .addContainerGap(535, Short.MAX_VALUE)
                        .addGroup(Menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnagregarplato1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40))
                );

                panelmenu.addTab("Tortas & Tacos", Menu1);

                getContentPane().add(panelmenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 670));

                jtfinal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                jtfinal.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Codigo", "Descripcion", "Precio", "Cantidad", "Detalles"
                    }
                ) {
                    boolean[] canEdit = new boolean [] {
                        true, false, false, false, false
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
                    jtfinal.getColumnModel().getColumn(4).setPreferredWidth(10);
                }

                getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 520, 340));

                c.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
                getContentPane().add(c, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 500, 160, 30));

                jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                jLabel3.setText("DLLS");
                getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 420, 70, 30));

                txttotal.setBackground(new java.awt.Color(255, 255, 255));
                txttotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
                txttotal.setText("$ 0.00");
                getContentPane().add(txttotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 390, 160, 30));

                jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                jLabel4.setText("TOTAL:");
                getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 360, 210, 30));

                jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                jLabel5.setText("MXN");
                getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 390, 70, 30));

                txttotaldlls.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
                txttotaldlls.setText("$ 0.00");
                getContentPane().add(txttotaldlls, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 420, 160, 30));

                btnconfirmar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                btnconfirmar.setForeground(new java.awt.Color(64, 190, 64));
                btnconfirmar.setText("CONFIRMAR");
                btnconfirmar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnconfirmarActionPerformed(evt);
                    }
                });
                getContentPane().add(btnconfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 470, 510, 180));

                txttotal1.setBackground(new java.awt.Color(255, 255, 255));
                txttotal1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
                txttotal1.setText("TOTAL DE ARTICULO(S):");
                getContentPane().add(txttotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 300, 45));

                txttotalarticulos.setBackground(new java.awt.Color(255, 255, 255));
                txttotalarticulos.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
                txttotalarticulos.setForeground(new java.awt.Color(255, 51, 51));
                txttotalarticulos.setText("0");
                getContentPane().add(txttotalarticulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 1, 77, 40));

                btnEliminarpieza1.setText("Quitar pieza");
                btnEliminarpieza1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnEliminarpieza1ActionPerformed(evt);
                    }
                });
                getContentPane().add(btnEliminarpieza1, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 400, 100, 35));

                jButton1.setText("Opciones");
                jButton1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton1ActionPerformed(evt);
                    }
                });
                getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 3, 110, 40));

                jButton2.setText("Limpiar todo");
                jButton2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton2ActionPerformed(evt);
                    }
                });
                getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 400, 100, 35));

                pack();
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

    private void jthamburguesasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jthamburguesasKeyTyped
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                JOptionPane.showMessageDialog(null, "Has pulsado Enter");
            }
            if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
                System.exit(0);
            }

    }//GEN-LAST:event_jthamburguesasKeyTyped

    private void jthamburguesasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jthamburguesasKeyPressed
    /*    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            JOptionPane.showMessageDialog(null, "Has pulsado Enter");
        }
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
        }*/

    }//GEN-LAST:event_jthamburguesasKeyPressed

    private void jthamburguesasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jthamburguesasMouseClicked
        if (evt.getClickCount() == 2) { 
            agregarfinal();
        }
    }//GEN-LAST:event_jthamburguesasMouseClicked

    private void btnagregarplato1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarplato1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnagregarplato1ActionPerformed

    private void ch6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ch6ActionPerformed
    DefaultTableModel dmm;

    private void filtro(String consulta, JTable jtableBuscar) {
        dmm = (DefaultTableModel) jthamburguesas.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dmm);
        jtableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }
    private void txt_codigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_codigoKeyReleased
        filtro(txt_codigo.getText().toUpperCase(), jthamburguesas);
    }//GEN-LAST:event_txt_codigoKeyReleased

    private void btnEliminarpieza1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarpieza1ActionPerformed
        int filaseleccionada = jtfinal.getSelectedRow();
        if (filaseleccionada >= 0) {
            String itemcodigo = (jtfinal.getValueAt(filaseleccionada, 3).toString().trim());
            DefaultTableModel dtm = (DefaultTableModel) jtfinal.getModel(); //TableProducto es el nombre de mi tabla ;)
            dtm.removeRow(jtfinal.getSelectedRow());
            sumar();
        } else {
            JOptionPane.showMessageDialog(null, "NO SELECCIONASTE NINGUN CODIGO ");
        }
    }//GEN-LAST:event_btnEliminarpieza1ActionPerformed

    private void txt_codigo1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_codigo1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_codigo1KeyReleased

    private void jthotdogsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jthotdogsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jthotdogsMouseClicked

    private void jthotdogsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jthotdogsKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jthotdogsKeyPressed

    private void jthotdogsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jthotdogsKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jthotdogsKeyTyped

    private void ch11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ch11ActionPerformed

    private void btnNada1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNada1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNada1ActionPerformed

    private void btnTodo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTodo1ActionPerformed

    private void btnagregarhotdogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarhotdogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnagregarhotdogActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Vaciartabla();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        double numarticulo = 0;
        obtenerfolio();
              System.out.println("paso1");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            Statement st = conexion.createStatement();
             st.executeUpdate("USE prueba;");
                  System.out.println("paso la conexion");
            //Seleccionar datos
            //  rs = st.executeQuery("SELECT `codigo`, `descripcion`, `precio`, `cantidad`, `categoria`, `imagen` from productos where categoria='hamburguesa'");
            // rs2 = st.executeQuery("SELECT  from productos where categoria='hamburguesa'");
            for (int x = 0; x < jtfinal.getRowCount(); x++) {
                  System.out.println("llegando al ciclo");
                  String vcodigo = ((String) jtfinal.getValueAt(x, 0));//obtener valor de precio
                String vdescripcion = ((String) jtfinal.getValueAt(x, 1));//obtener valor de precio
                String vprecio =  ((String) jtfinal.getValueAt(x, 2));///obtienes el valor de la cantidad
                int vcantidad =  ((int) jtfinal.getValueAt(x, 3));///obtienes el valor de la cantidad
                
                
                  String vprecioformateado = vprecio.replaceAll("[^0-1-2-3-4-5-6-7-8-9-.00]", "");//dejameos solo los elementos"[^0-1-2-3-4-5-6-7-8-9-.00]"
                double vprecioparseado = Double.parseDouble(vprecioformateado);
                ps = conexion.prepareStatement("INSERT INTO `ventas`(`Fecha`, `Sucursal`, `Folio`, `Caja`, `Articulo`, `Codigo`, `Grupo`, `Cantidad`, `Precioventa`, `Vendedor`, `Cajero`, `Claveventa`, `Hora`) "
                         + "VALUES (getdate(),'1','"+folio+"','1','"+numarticulo+"','"+vcodigo+"','00','"+vcantidad +"','"+vprecioformateado+"','1111','00',777,'11:21'");


                      numarticulo =numarticulo+ 1;
                 
              }
            
            //      System.out.println(">>>>>xxxx" + txtgondola.getText().toUpperCase() + txtcantidad.getText().toUpperCase() + date);
           int n = ps.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "¡Los datos han sido guardados exitósamente!");
                aumentarfolio();
                
                
                //limpiarventanas();
            }
        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error en la base de datos");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
     agregarfinal(); 
    }//GEN-LAST:event_btnagregarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ch1.setSelected(false);
        ch2.setSelected(false);
        ch3.setSelected(false);
        ch4.setSelected(false);
        ch5.setSelected(false);
        ch6.setSelected(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      ch1.setSelected(true);
        ch2.setSelected(true);
        ch3.setSelected(true);
        ch4.setSelected(true);
        ch5.setSelected(true);
        ch6.setSelected(true);
    }//GEN-LAST:event_jButton5ActionPerformed

   
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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JPanel Bebidas;
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel Menu1;
    private javax.swing.JPanel Ordenes;
    private javax.swing.JPanel Ordenes1;
    private javax.swing.JPanel Ordenes2;
    private javax.swing.JButton btnEliminarpieza1;
    private javax.swing.JToggleButton btnNada1;
    private javax.swing.JToggleButton btnTodo1;
    private javax.swing.JButton btnagregar;
    private javax.swing.JToggleButton btnagregarhotdog;
    private javax.swing.JToggleButton btnagregarplato1;
    private javax.swing.JButton btnconfirmar;
    private javax.swing.JLabel c;
    private javax.swing.JCheckBox ch1;
    private javax.swing.JCheckBox ch10;
    private javax.swing.JCheckBox ch11;
    private javax.swing.JCheckBox ch12;
    private javax.swing.JCheckBox ch2;
    private javax.swing.JCheckBox ch3;
    private javax.swing.JCheckBox ch4;
    private javax.swing.JCheckBox ch5;
    private javax.swing.JCheckBox ch6;
    private javax.swing.JCheckBox ch7;
    private javax.swing.JCheckBox ch8;
    private javax.swing.JCheckBox ch9;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jtbebidas;
    private javax.swing.JTable jtboneless;
    public static javax.swing.JTable jtfinal;
    private javax.swing.JTable jthamburguesas;
    private javax.swing.JTable jthotdogs;
    private javax.swing.JTabbedPane panelmenu;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_codigo1;
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
              //  System.out.println("r " + r);
                //System.out.println("vcantidad " + vcantidad);
            }
            txttotalarticulos.setText("" + r);//preciototal
        }
 
    public void Vaciartabla() {
        DefaultTableModel vt = (DefaultTableModel) jtfinal.getModel();
        for (int i = vt.getRowCount() - 1; i >= 0; i--) {
            vt.removeRow(i);
        }
        sumar();
    }
   public void tablafinal() {
     String data[][] = {};
        String cabeza[] = {"Codigo","Descripcion", "Precio", "Cantidad","Detalles"};
        jtfinal.getTableHeader().setReorderingAllowed(false);
        md = new DefaultTableModel(data, cabeza) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column != 5) {
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
        jtfinal.getColumnModel().getColumn(4).setMinWidth(220); }
}
