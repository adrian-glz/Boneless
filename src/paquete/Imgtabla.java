/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author AGONZALEZ
 */
public class Imgtabla extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object o,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (o instanceof JLabel) {
            
            JLabel lbl = (JLabel) o;
            return lbl;
        
        }
        return super.getTableCellRendererComponent(table, o, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.

    }
}
