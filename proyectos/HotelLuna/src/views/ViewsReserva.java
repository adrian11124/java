package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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

import controllers.CtrHabitacion;
import controllers.CtrHuesped;
import controllers.CtrReserva;
import controllers.Inventario;

/**
 *
 * @author adrian11124
 */
public class ViewsReserva {

    private GeneryViews genery = new GeneryViews();

    private JPanel listarReserva() {
        CtrReserva ctr = new CtrReserva();

        JPanel pnl = new JPanel();
        JTable table = new JTable();
        JPanel pnl_btn = new JPanel();
        JButton btn_remove = new JButton("ELIMINAR");
        JScrollPane scroll = new JScrollPane();
        DefaultTableModel model;
        
        pnl.setLayout(new BorderLayout());
        pnl.setBackground(Color.WHITE);
        pnl_btn.setLayout(new FlowLayout());
        
        model = genery.tableModel(ctr.getDataRSV(), ctr.atributeTable());
        scroll.setViewportView(table);
        table.setModel(model);
        table.setFocusable(false);
        btn_remove.setEnabled(false);
        
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                btn_remove.setEnabled(true);
            }
        });
        btn_remove.addActionListener((e) -> {
            int row = table.getSelectedRow();
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar Reserva?", "CONFIRMACION", JOptionPane.YES_NO_OPTION);
            boolean op = (opcion == JOptionPane.YES_OPTION);
            if(op){
                // inv.removeRSV(table.getValueAt(row, 0).toString());
                JPanel lstReserva =  listarReserva();
                genery.ajustarNewPanel(pnl, lstReserva);
            }
            
        });
        pnl_btn.add(btn_remove);
        pnl.add(scroll, BorderLayout.CENTER);
        pnl.add(pnl_btn, BorderLayout.SOUTH);
        return pnl;
    }

    public void reservarHabitacion(JFrame frame) {
        dialog1Reserva(frame);
    }

    public void dialog1Reserva(JFrame frame) {
        JDialog dialog1 = new JDialog(frame, true);

        dialog1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        dialog1.setSize(550, 700);
        dialog1.setLayout(new BorderLayout());
        JPanel pnl_buscar = new JPanel();
        JTextField txt_buscar = new JTextField();
        JButton btn_buscar = new JButton();
        
        pnl_buscar.setLayout(new FlowLayout());
        txt_buscar.setColumns(30);
        btn_buscar.setText("BUSCAR");
        btn_buscar.setFocusable(false);
        
        dialog1.add(pnl_buscar, BorderLayout.NORTH);

        String[] columnas = {"DOCUMENTO", "NOMBRE"};
        VentanasSecundarias vtns = new VentanasSecundarias();
        DefaultTableModel model = genery.tableModel(CtrHuesped.getDataHPD(), columnas);
        JTable table = new JTable(model);
        table.setFocusable(false);
        JButton btnSiguiente = new JButton("SIGUIENTE");
        JButton btnCancelar = new JButton("CANCELAR");
        btnSiguiente.setFocusable(false);
        btnCancelar.setFocusable(false);
        btn_buscar.addActionListener((e) -> {
            model.setRowCount(0);
            for (String[] obj : CtrHuesped.buscarHPD("numDocumento", txt_buscar.getText())) {
                model.addRow(obj);
            }
            btnSiguiente.setEnabled(false);
        });
        JScrollPane scroll = new JScrollPane(table);
        dialog1.add(scroll, BorderLayout.CENTER);

        JPanel pnl_btn = new JPanel();
        btnCancelar.addActionListener((e) -> {
            dialog1.dispose();
        });
        btnSiguiente.setEnabled(false);
        pnl_btn.add(btnCancelar);
        pnl_btn.add(btnSiguiente);
        dialog1.add(pnl_btn, BorderLayout.SOUTH);

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
        JTextField txt_buscar = new JTextField(20);
        JButton btn_buscar = new JButton("BUSCAR");
        JButton btnSiguiente = new JButton("SIGUIENTE");
        JButton btnCancelar = new JButton("CANCELAR");
        btnSiguiente.setFocusable(false);
        btn_buscar.setFocusable(false);
        btnCancelar.setFocusable(false);
        if (esVIP) {

            columnasHab = new String[]{"# HABITACION", "PRECIO", "TIPO", "ESTADO", "SERVICIOS EXTRA"};
            model = vtns.tableModel(CtrHabitacion.getDataHBTVIPDisponible(), columnasHab);
            
            btn_buscar.addActionListener((e) -> {
                model.setRowCount(0);
                for (String[] obj : CtrHabitacion.buscarHBTVIP("numero", txt_buscar.getText())) {
                    model.addRow(obj);
                }
                btnSiguiente.setEnabled(false);
            });
            datosHuesped.add("SI");
        } else {
            columnasHab = new String[]{"# HABITACION", "PRECIO", "TIPO", "ESTADO"};
            model = vtns.tableModel(CtrHabitacion.getDataHBTDisponible(), columnasHab);
            
            btn_buscar.addActionListener((e) -> {
                model.setRowCount(0);
                for (String[] obj : CtrHabitacion.buscarHBT("numero", txt_buscar.getText())) {
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

        JPanel pnl_buscar = new JPanel(new FlowLayout());

        pnl_buscar.add(txt_buscar);
        pnl_buscar.add(btn_buscar);
        dialog2.add(pnl_buscar, BorderLayout.NORTH);

        JPanel pnl_btn = new JPanel();
        
        pnl_btn.add(btnCancelar);
        pnl_btn.add(btnSiguiente);
        btnSiguiente.setEnabled(false);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                btnSiguiente.setEnabled(true);
            }
        });
        

        btnCancelar.addActionListener((e) -> dialog2.dispose());
        btnSiguiente.addActionListener(e -> {
            int row = table.getSelectedRow();
            datosHuesped.add((String) table.getValueAt(row, 1));
            dialog2.dispose();
            mostrarDialog3(datosHuesped, inv, frame);
        });

        pnl_buscar.add(txt_buscar);
        pnl_buscar.add(btn_buscar);
        dialog2.add(pnl_btn, BorderLayout.SOUTH);
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

            CtrReserva.generarReserva(datosHuesped);
            dialog3.dispose();
        });
        dialog3.setVisible(true);
    }
}
