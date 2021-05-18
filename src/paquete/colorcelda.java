package paquete;

import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class colorcelda extends JTable {

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int ColumnIndex) {
        Component componente = super.prepareRenderer(renderer, rowIndex, ColumnIndex);
        if (getValueAt(rowIndex, ColumnIndex).getClass().equals(String.class)) {///cambiar if para pintar solo una celda
            String valor = ((String) this.getValueAt(rowIndex, ColumnIndex));

            if (valor.equals("EN PROCESO")) {
                componente.setBackground(Color.yellow);
                componente.setForeground(Color.black);
            }
            if (valor.equals("ENTREGADO")) {
                componente.setBackground(Color.green);
                componente.setForeground(Color.black);
            }
        } else {
            componente.setBackground(white);
            componente.setForeground(Color.black);
        }
        return componente;
    }
}
