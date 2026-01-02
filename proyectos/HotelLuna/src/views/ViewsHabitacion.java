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
    public JPanel listarHabitacionesVIP() {

        JPanel pnl = new JPanel();
        JPanel pnl_buscar = new JPanel();
        JPanel pnlBtn = new JPanel();
        JScrollPane scroll = new JScrollPane();
        JTable table = new JTable();
        DefaultTableModel model;
        JTextField txt_buscar = new JTextField();
        JButton btn_buscar = new JButton();
        JButton btn_actualizar = new JButton();
        JLabel lbl_cantHbt = new JLabel();

        CtrHabitacion ctr = new CtrHabitacion();    
        model = genery.tableModel(ctr.getDataHBT(), ctr.atributeTable());

        pnlBtn.setLayout(new FlowLayout());
        btn_actualizar.setText("ACTUALIZAR");
        btn_buscar.setText("BUSCAR");
        pnl.setBackground(Color.WHITE);
        pnl.setLayout(new BorderLayout());
        table.setModel(model);
        scroll.setViewportView(table);
        table.setFocusable(false);
        table.setBackground(Color.WHITE);
        pnl_buscar.setBackground(Color.WHITE);
        txt_buscar.setColumns(30);
        btn_buscar.setFocusable(false);
        lbl_cantHbt.setText(null);
        btn_actualizar.setFocusable(false);
        btn_actualizar.setEnabled(false);
        
        btn_buscar.addActionListener((e) -> {
            model.setRowCount(0);
            for (String[] obj : CtrHabitacion.buscarHBTVIP("numero", txt_buscar.getText())) {
                model.addRow(obj);
            }
            btn_actualizar.setEnabled(false);
        });
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                btn_actualizar.setEnabled(true);
            }
        });
        btn_actualizar.addActionListener(e -> {
            String data = "";
            ctr.actualizarHBTVIP(data);
        });
        
        pnl_buscar.add(txt_buscar);
        pnl_buscar.add(btn_buscar);
        pnl_buscar.add(lbl_cantHbt);
        pnl.add(pnl_buscar, BorderLayout.NORTH);
        pnl.add(scroll, BorderLayout.CENTER);
        pnl.add(pnlBtn, BorderLayout.SOUTH);

        return pnl;
    }


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
            String[] arrayDataNew = new String[5];
            CtrHabitacion.actualizarHBT(arrayDataNew);
        });
        
        pnl_buscar.add(cant_habitaciones);
        pnl_south.add(btn_actualizar);
        pnl.add(pnl_buscar, BorderLayout.NORTH);
        pnl.add(scroll, BorderLayout.CENTER);
        pnl.add(pnl_south, BorderLayout.SOUTH);
        return pnl;
    }
    
}
