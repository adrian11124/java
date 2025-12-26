package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;

import Models.Habitacion;
import Models.HabitacionVIP;
import Models.Huesped;
import bd.conexion;
import controllers.CtrHabitacion;
import controllers.CtrHuesped;
import controllers.Inventario;

/**
 *
 * @author adrian11124
 */
public class ViewsHabitacion {

    private JPanel listarHabitacionesVIP(Inventario inventario, JFrame frame) {
        JPanel pnl = new JPanel(new BorderLayout());
        pnl.setBackground(Color.WHITE);

        String[] columnTable = {"# HABITACION",
            "PRECIO",
            "TIPO",
            "ESTADO",
            "VIP"};
        DefaultTableModel model = tableModel(CtrHabitacion.getDataHBTVIP(), columnTable);
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        table.setFocusable(false);
        table.setBackground(Color.WHITE);

        JPanel pnlBuscar = new JPanel();
        pnlBuscar.setBackground(Color.WHITE);
        JTextField txtbuscar = new JTextField(30);
        JButton btnBuscar = new JButton("BUSCAR");
        btnBuscar.setFocusable(false);
        pnlBuscar.add(txtbuscar);
        pnlBuscar.add(btnBuscar);
        JButton btnActualizar = new JButton("ACTUALIZAR");
        btnActualizar.setFocusable(false);
        btnActualizar.setEnabled(false);
        pnlBuscar.add(new JLabel("CANTIDAD HABITACIONES: " + inventario.getDataHBTVIP().size()));
        btnBuscar.addActionListener((e) -> {
            model.setRowCount(0);
            for (String[] obj : inventario.buscarHBTVIP("numero", txtbuscar.getText())) {
                model.addRow(obj);
            }
            btnActualizar.setEnabled(false);
        });

        JPanel pnlBtn = new JPanel(new FlowLayout());
        
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                btnActualizar.setEnabled(true);
            }
        });
        pnlBtn.add(btnActualizar);
        btnActualizar.addActionListener(e -> {
            int row = table.getSelectedRow();
            // actualizarHBTVIP(inventario, frame, "" + table.getValueAt(row, 1), pnl);
        });
        pnl.add(pnlBuscar, BorderLayout.NORTH);
        pnl.add(scroll, BorderLayout.CENTER);
        pnl.add(pnlBtn, BorderLayout.SOUTH);
        return pnl;
    }

    private JPanel listarHabitaciones(Inventario inventario, JFrame frame) {
        JPanel pnl = new JPanel(new BorderLayout());
        pnl.setBackground(Color.WHITE);

        String[] columnTable = {"# HABITACION",
            "PRECIO",
            "TIPO",
            "ESTADO"
        };
        DefaultTableModel model = tableModel(CtrHabitacion.getDataHBT(), columnTable);
        JTable table = new JTable(model);
        table.setFocusable(false);
        JScrollPane scroll = new JScrollPane(table);

        JPanel pnlBuscar = new JPanel();
        pnlBuscar.setBackground(Color.WHITE);
        JTextField txtbuscar = new JTextField(30);
        JButton btnBuscar = new JButton("BUSCAR");
        btnBuscar.setFocusable(false);
        pnlBuscar.add(txtbuscar);
        pnlBuscar.add(btnBuscar);
        JButton btnActualizar = new JButton("ACTUALIZAR");
        btnActualizar.setFocusable(false);
        // pnlBuscar.add(new JLabel("CANTIDAD HABITACIONES: " + inventario.getDataHBT().size()));
        pnlBuscar.add(new JLabel("CANTIDAD HABITACIONES: N/A"));
        btnBuscar.addActionListener((e) -> {
            model.setRowCount(0);
            for (String[] obj : CtrHabitacion.buscarHBT("numero", txtbuscar.getText())) {
                model.addRow(obj);
            }
           btnActualizar.setEnabled(false);
        });

        JPanel pnlBtn = new JPanel(new FlowLayout());
        
        btnActualizar.setEnabled(false);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                btnActualizar.setEnabled(true);
            }
        });
        btnActualizar.addActionListener(e -> {
            int row = table.getSelectedRow();
            table.getRowHeight(row);

            // actualizarHBT(inventario, frame, "" + table.getValueAt(row, 1), pnl);
        });
        
        pnlBtn.add(btnActualizar);

        pnl.add(pnlBuscar, BorderLayout.NORTH);
        pnl.add(scroll, BorderLayout.CENTER);
        pnl.add(pnlBtn, BorderLayout.SOUTH);
        return pnl;
    }
    public void loadPnlIndex(JPanel pnl) {
        pnl.setVisible(false);
        pnl.removeAll();
        pnl.setVisible(true);
    }
}
