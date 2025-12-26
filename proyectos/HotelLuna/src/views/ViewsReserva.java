package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllers.Inventario;

public class ViewsReserva {

    private JPanel listarReserva(Inventario inv, JFrame frame) {
        JPanel pnl = new JPanel(new BorderLayout());
        pnl.setBackground(Color.WHITE);
        String[] columnTable = {"NUMERO HABITACION", "DOCUMENTO HUESPED", "NOMBRE HUESPED", "FECHA INGRESO", "FECHA SALIDA", "VIP"};
        // DefaultTableModel model = tableModel(inv.getDataRSV(), columnTable);
        DefaultTableModel model;
        JTable table = new JTable(model);
        table.setFocusable(false);
        JScrollPane scroll = new JScrollPane(table);
        JPanel pnlBtn = new JPanel(new FlowLayout());
        
        JButton btnEliminar = new JButton("ELIMINAR");
        btnEliminar.setEnabled(false);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                btnEliminar.setEnabled(true);
            }
        });
        pnlBtn.add(btnEliminar);
        btnEliminar.addActionListener((e) -> {
            int row = table.getSelectedRow();
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar Reserva?", "CONFIRMACION", JOptionPane.YES_NO_OPTION);
            boolean op = (opcion == JOptionPane.YES_OPTION);
            if(op){
                // inv.removeRSV(table.getValueAt(row, 0).toString());
                loadPnlIndex(pnl);
                pnl.add(listarReserva(inv, frame));
            }
            
        });
        pnl.add(scroll, BorderLayout.CENTER);
        pnl.add(pnlBtn, BorderLayout.SOUTH);
        return pnl;
    }
}
