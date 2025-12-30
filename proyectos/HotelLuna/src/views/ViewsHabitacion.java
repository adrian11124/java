package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.CtrHabitacion;


/**
 *
 * @author adrian11124
 */
public class ViewsHabitacion {
    private GeneryViews genery = new GeneryViews();
    private static DefaultTableModel model = new DefaultTableModel();
    // public JPanel listarHabitacionesVIP(Inventario inventario, JFrame frame) {
    //     JPanel pnl = new JPanel(new BorderLayout());
    //     pnl.setBackground(Color.WHITE);

    //     String[] columnTable = {"# HABITACION",
    //         "PRECIO",
    //         "TIPO",
    //         "ESTADO",
    //         "VIP"};
    //     DefaultTableModel model = tableModel(CtrHabitacion.getDataHBTVIP(), columnTable);
    //     JTable table = new JTable(model);
    //     JScrollPane scroll = new JScrollPane(table);
    //     table.setFocusable(false);
    //     table.setBackground(Color.WHITE);

    //     JPanel pnl_buscar = new JPanel();
    //     pnl_buscar.setBackground(Color.WHITE);
    //     JTextField txt_buscar = new JTextField(30);
    //     JButton btnBuscar = new JButton("BUSCAR");
    //     btnBuscar.setFocusable(false);
    //     pnl_buscar.add(txt_buscar);
    //     pnl_buscar.add(btnBuscar);
    //     JButton btnActualizar = new JButton("ACTUALIZAR");
    //     btnActualizar.setFocusable(false);
    //     btnActualizar.setEnabled(false);
    //     pnl_buscar.add(new JLabel("CANTIDAD HABITACIONES: " + inventario.getDataHBTVIP().size()));
    //     btnBuscar.addActionListener((e) -> {
    //         model.setRowCount(0);
    //         for (String[] obj : inventario.buscarHBTVIP("numero", txt_buscar.getText())) {
    //             model.addRow(obj);
    //         }
    //         btnActualizar.setEnabled(false);
    //     });

    //     JPanel pnlBtn = new JPanel(new FlowLayout());
        
    //     table.getSelectionModel().addListSelectionListener(e -> {
    //         if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
    //             btnActualizar.setEnabled(true);
    //         }
    //     });
    //     pnlBtn.add(btnActualizar);
    //     btnActualizar.addActionListener(e -> {
    //         int row = table.getSelectedRow();
    //         // actualizarHBTVIP(inventario, frame, "" + table.getValueAt(row, 1), pnl);
    //     });
    //     pnl.add(pnl_buscar, BorderLayout.NORTH);
    //     pnl.add(scroll, BorderLayout.CENTER);
    //     pnl.add(pnlBtn, BorderLayout.SOUTH);
    //     return pnl;
    // }


    /**
     *Genera un vista que enlista todas las habitaciones(normal, vip)
     */
    public JPanel listarHabitaciones() {
        JPanel pnl = new JPanel();
        JPanel pnl_buscar = new JPanel();
        JPanel pnl_south = new JPanel(new FlowLayout());
        JTable table = new JTable();
        JScrollPane scroll = new JScrollPane();
        
        JTextField txt_buscar = new JTextField();
        JButton btn_buscar = new JButton();
        JButton btn_actualizar = new JButton();
        JLabel cant_habitaciones = new JLabel();
        
        CtrHabitacion ctr = new CtrHabitacion();
        model = genery.tableModel(ctr.getDataHBT(), ctr.atributeTable());

        table.setModel(model);
        table.setFocusable(false);
        scroll.setViewportView(table);
        pnl_south.setLayout(new FlowLayout());
        pnl.setLayout(new BorderLayout());
        pnl.setBackground(Color.WHITE);
        pnl_buscar.setBackground(Color.WHITE);
        txt_buscar.setColumns(30);
        btn_buscar.setText("BUSCAR");
        btn_buscar.setFocusable(false);
        pnl_buscar.add(txt_buscar);
        pnl_buscar.add(btn_buscar);
        btn_actualizar.setText("ACTUALIZAR");
        btn_actualizar.setEnabled(false);
        btn_actualizar.setFocusable(false);
        cant_habitaciones.setText("CANTIDAD HABITACIONES: N/A");
        
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                btn_actualizar.setEnabled(true);
            }
        });
        btn_buscar.addActionListener((e) -> {
            model.setRowCount(0);
            for (String[] obj : CtrHabitacion.buscarHBT("numero", txt_buscar.getText())) {
                model.addRow(obj);
            }
           btn_actualizar.setEnabled(false);
        });
        btn_actualizar.addActionListener(e -> {
            int row = table.getSelectedRow();
            table.getRowHeight(row);
            // actualizarHBT(inventario, frame, "" + table.getValueAt(row, 1), pnl);
        });

        
        pnl_buscar.add(cant_habitaciones);
        pnl_south.add(btn_actualizar);
        pnl.add(pnl_buscar, BorderLayout.NORTH);
        pnl.add(scroll, BorderLayout.CENTER);
        pnl.add(pnl_south, BorderLayout.SOUTH);
        return pnl;
    }
    
}
