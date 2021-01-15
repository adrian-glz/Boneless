/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static paquete.ConexionMySQL.IPSUCURSAL;
import static paquete.ConexionMySQL.SUCURSAL;
import static paquete.ConexionMySQL.NOMBRESUCURSAL;
import static paquete.ConexionMySQL.TELEFONOSUCURSAL;
import static paquete.ConexionMySQL.IMPRESORA;
/**
 *
 * @author AGONZALEZ
 */
public class ConexionMySQL  {         
    public String db = "cml";
    public String url = "jdbc:jtds:sqlserver://192.168.1.80:55024/"+db;
    public String user = "usounds";
    public String pass = "madljda";
    
    public static String IPSUCURSAL;
    public static String SUCURSAL;
    public static String NOMBRESUCURSAL;
    public static String TELEFONOSUCURSAL;
    public static String IMPRESORA;

        public Connection Conectar() {
            Connection link = null;
            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                link = DriverManager.getConnection(this.url, this.user, this.pass);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "ERROR VERIFIQUE LA CONEXION O COMUNIQUESE CON SISTEMAS" + ex);
            }
            return link;
        }
    }

    class Leer_fichero {

        public static void leerconexion() {
            String texto;
            File ruta = new File("C:\\Program Files\\Sistema/config.txt");
            if (ruta.exists()) {
                try {
                    //  System.out.println(System.getProperty("user.name")); 
                    // String user = System.getProperty("user.name");
                    //  File archivo = new File("C:\\Users\\" + user + "\\Desktop\\LEEME.txt");
                    File archivo = new File("C:\\Program Files\\Sistema/config.txt");

                    FileReader entrada = new FileReader(archivo);
                    BufferedReader br = new BufferedReader(entrada);
                    ArrayList arreglo = new ArrayList();
                    while ((texto = br.readLine()) != null) {
                        arreglo.add(texto);
                    }
                    entrada.close();

                    IPSUCURSAL = arreglo.get(0).toString();
                    SUCURSAL = arreglo.get(1).toString();
                    NOMBRESUCURSAL = arreglo.get(2).toString();
                    TELEFONOSUCURSAL = arreglo.get(3).toString();
                    IMPRESORA = arreglo.get(4).toString();

                    //     System.out.println("" + IPSUCURSAL + SUCURSAL + NOMBRESUCURSAL + TELEFONOSUCURSAL+IMPRESORA);
                } catch (IOException e) {

                }
            } else {
                JOptionPane.showMessageDialog(null, "compruebe que config.txt EXISTA Y/O tenga los parametros necesarios");
                System.out.println("No se ha encontrado el archivo:en C:\\Program Files\\Sistema\\config.txt");
            System.exit(0);                
            }
        }
    }
