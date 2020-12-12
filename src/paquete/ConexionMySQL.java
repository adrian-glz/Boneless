/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AGONZALEZ
 */
public class ConexionMySQL  {         
    public String db = "cml";
    public String url = "jdbc:jtds:sqlserver://192.168.1.80:55024/"+db;
    public String user = "usounds";
    public String pass = "madljda";

   public Connection Conectar(){
       Connection link = null;
       try{
           Class.forName("net.sourceforge.jtds.jdbc.Driver");
           link = DriverManager.getConnection(this.url, this.user, this.pass);
       }catch(Exception ex){
           JOptionPane.showMessageDialog(null, "ERROR VERIFIQUE LA CONEXION O COMUNIQUESE CON SISTEMAS"+ex);
       }
       return link;
   }
}
