package JFrame;

import Process.Inventario;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class JReservar {

    public void dialog1(Inventario inv, JFrame frame) {
        JDialog dialog1 = new JDialog(frame, true);
        dialog1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        dialog1.setSize(550, 700);
        dialog1.setLayout(new BorderLayout());

        JPanel pnlBuscar = new JPanel(new FlowLayout());
        JTextField txtBuscar = new JTextField(20);
        JButton btnBuscar = new JButton("BUSCAR");
        btnBuscar.setFocusable(false);
        pnlBuscar.add(txtBuscar);
        pnlBuscar.add(btnBuscar);
        dialog1.add(pnlBuscar, BorderLayout.NORTH);

        String[] columnas = {"DOCUMENTO", "NOMBRE"};
        VentanasSecundarias vtns = new VentanasSecundarias();
        DefaultTableModel model = vtns.tableModel(inv.getDataHPD(), columnas);
        JTable table = new JTable(model);
        table.setFocusable(false);
        JButton btnSiguiente = new JButton("SIGUIENTE");
        JButton btnCancelar = new JButton("CANCELAR");
        btnSiguiente.setFocusable(false);
        btnCancelar.setFocusable(false);
        btnBuscar.addActionListener((e) -> {
            model.setRowCount(0);
            for (String[] obj : inv.buscarHPD("numDocumento", txtBuscar.getText())) {
                model.addRow(obj);
            }
            btnSiguiente.setEnabled(false);
        });
        JScrollPane scroll = new JScrollPane(table);
        dialog1.add(scroll, BorderLayout.CENTER);

        JPanel pnlBtn = new JPanel();
        btnCancelar.addActionListener((e) -> {
            dialog1.dispose();
        });
        btnSiguiente.setEnabled(false);
        pnlBtn.add(btnCancelar);
        pnlBtn.add(btnSiguiente);
        dialog1.add(pnlBtn, BorderLayout.SOUTH);

        List<String> datosHuesped = new ArrayList<>();

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                btnSiguiente.setEnabled(true);
            }
        });

        btnSiguiente.addActionListener(e -> {
            int row = table.getSelectedRow();
            datosHuesped.add((String) table.getValueAt(row, 1));
            datosHuesped.add((String) table.getValueAt(row, 2));
            dialog1.dispose();
            mostrarDialog2(datosHuesped, inv, frame);
        });
        dialog1.setVisible(true);
    }

    public void mostrarDialog2(List<String> datosHuesped, Inventario inv, JFrame frame) {
        JDialog dialog2 = new JDialog(frame, true);
        dialog2.setSize(550, 700);
        dialog2.setLayout(new BorderLayout());
        dialog2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        int opcion = JOptionPane.showConfirmDialog(null, "¿Es HABITACION VIP?", "TIPO HABITACION", JOptionPane.YES_NO_OPTION);
        boolean esVIP = (opcion == JOptionPane.YES_OPTION);
        String[] columnasHab;
        DefaultTableModel model;
        VentanasSecundarias vtns = new VentanasSecundarias();
        JTextField txtBuscar = new JTextField(20);
        JButton btnBuscar = new JButton("BUSCAR");
        JButton btnSiguiente = new JButton("SIGUIENTE");
        JButton btnCancelar = new JButton("CANCELAR");
        btnSiguiente.setFocusable(false);
        btnBuscar.setFocusable(false);
        btnCancelar.setFocusable(false);
        if (esVIP) {

            columnasHab = new String[]{"# HABITACION", "PRECIO", "TIPO", "ESTADO", "SERVICIOS EXTRA"};
            model = vtns.tableModel(inv.getDataHBTVIPDisponible(), columnasHab);
            
            btnBuscar.addActionListener((e) -> {
                model.setRowCount(0);
                for (String[] obj : inv.buscarHBTVIP("numero", txtBuscar.getText())) {
                    model.addRow(obj);
                }
                btnSiguiente.setEnabled(false);
            });
            datosHuesped.add("SI");
        } else {
            columnasHab = new String[]{"# HABITACION", "PRECIO", "TIPO", "ESTADO"};
            model = vtns.tableModel(inv.getDataHBTDisponible(), columnasHab);
            
            btnBuscar.addActionListener((e) -> {
                model.setRowCount(0);
                for (String[] obj : inv.buscarHBT("numero", txtBuscar.getText())) {
                    model.addRow(obj);
                }
                btnSiguiente.setEnabled(false);
            });
            datosHuesped.add("NO");
        }

        JTable table = new JTable(model);
        JScrollPane scrollHab = new JScrollPane(table);
        table.setFocusable(false);
        dialog2.setLayout(new BorderLayout());
        dialog2.add(scrollHab, BorderLayout.CENTER);

        JPanel pnlBuscar = new JPanel(new FlowLayout());

        pnlBuscar.add(txtBuscar);
        pnlBuscar.add(btnBuscar);
        dialog2.add(pnlBuscar, BorderLayout.NORTH);

        JPanel pnlBtn = new JPanel();
        
        pnlBtn.add(btnCancelar);
        pnlBtn.add(btnSiguiente);
        btnSiguiente.setEnabled(false);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                btnSiguiente.setEnabled(true);
            }
        });
        dialog2.add(pnlBtn, BorderLayout.SOUTH);

        btnCancelar.addActionListener((e) -> dialog2.dispose());
        btnSiguiente.addActionListener(e -> {
            int row = table.getSelectedRow();
            datosHuesped.add((String) table.getValueAt(row, 1));
            dialog2.dispose();
            mostrarDialog3(datosHuesped, inv, frame);
        });

        dialog2.setVisible(true);
    }

    public String[] dataFecha(Integer ini, Integer fin) {
        String data = "";
        for (Integer i = ini; i <= fin; i++) {

            if (i <= 9) {
                data += "0" + i;
            } else {
                data += "" + i;
            }
            if (Objects.equals(i, fin)) {
                break;
            } else {
                data += ",";
            }
        }
        return data.split(",");
    }

    public void mostrarDialog3(List<String> datosHuesped, Inventario inv, JFrame frame) {
        JDialog dialog3 = new JDialog(frame, true);
        dialog3.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        dialog3.setSize(550, 700);

        dialog3.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblIngreso = new JLabel("Fecha Ingreso:");
        JPanel pnlFechaIngreso = new JPanel(new FlowLayout());
        JComboBox cbxDiaIngreso = new JComboBox<>(dataFecha(1, 30));
        JComboBox cbxMesIngreso = new JComboBox<>(dataFecha(1, 12));
        JComboBox cbxAnioIngreso = new JComboBox<>(dataFecha(2025, 2027));
        cbxDiaIngreso.setFocusable(false);
        cbxMesIngreso.setFocusable(false);
        cbxAnioIngreso.setFocusable(false);
        pnlFechaIngreso.add(cbxDiaIngreso);
        pnlFechaIngreso.add(new JLabel("/"));
        pnlFechaIngreso.add(cbxMesIngreso);
        pnlFechaIngreso.add(new JLabel("/"));
        pnlFechaIngreso.add(cbxAnioIngreso);
        JLabel lblSalida = new JLabel("Fecha Salida:");
        JPanel pnlFechaSalida = new JPanel(new FlowLayout());
        JComboBox cbxDiaSalida = new JComboBox<>(dataFecha(1, 30));
        JComboBox cbxMesSalida = new JComboBox<>(dataFecha(1, 12));
        JComboBox cbxAnioSalida = new JComboBox<>(dataFecha(2025, 2027));
        cbxDiaSalida.setFocusable(false);
        cbxMesSalida.setFocusable(false);
        cbxAnioSalida.setFocusable(false);
        pnlFechaSalida.add(cbxDiaSalida);
        pnlFechaSalida.add(new JLabel("/"));
        pnlFechaSalida.add(cbxMesSalida);
        pnlFechaSalida.add(new JLabel("/"));
        pnlFechaSalida.add(cbxAnioSalida);
        JButton btnGenerar = new JButton("GENERAR RESERVA");
        JButton btnCancelar = new JButton("CANCELAR");
        btnGenerar.setFocusable(false);
        btnCancelar.setFocusable(false);
        
        
        btnCancelar.addActionListener((e) -> {
            dialog3.dispose();
        });
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        dialog3.add(lblIngreso, gbc);
        gbc.gridx = 1;
        dialog3.add(pnlFechaIngreso, gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        dialog3.add(lblSalida, gbc);
        gbc.gridx = 1;
        dialog3.add(pnlFechaSalida, gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        dialog3.add(new JLabel("Formato fecha(dd/MM/YYYY)"), gbc);
        gbc.gridy = 3;
        gbc.gridx = 0;
        dialog3.add(btnCancelar, gbc);
        gbc.gridx = 1;
        dialog3.add(btnGenerar, gbc);

        btnGenerar.addActionListener(e -> {
            datosHuesped.add(cbxDiaIngreso.getSelectedItem() + "/" + cbxMesIngreso.getSelectedItem()
                    + "/" + cbxAnioIngreso.getSelectedItem());
            datosHuesped.add(cbxDiaSalida.getSelectedItem() + "/" + cbxMesSalida.getSelectedItem()
                    + "/" + cbxAnioSalida.getSelectedItem());

            inv.generarReserva(datosHuesped, inv);
            dialog3.dispose();
        });
        dialog3.setVisible(true);
    }
}
