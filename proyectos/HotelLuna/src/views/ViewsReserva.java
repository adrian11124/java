package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
        JPanel pnl_south = new JPanel();
        JButton btn_remove = new JButton("ELIMINAR");
        JScrollPane scroll = new JScrollPane();
        DefaultTableModel model;
        
        pnl.setLayout(new BorderLayout());
        pnl.setBackground(Color.WHITE);
        pnl_south.setLayout(new FlowLayout());
        
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
        pnl_south.add(btn_remove);
        pnl.add(scroll, BorderLayout.CENTER);
        pnl.add(pnl_south, BorderLayout.SOUTH);
        return pnl;
    }

    public void reservarHabitacion(JFrame frame) {
        dialog1Reserva(frame);
    }

    public void dialog1Reserva(JFrame frame) {
        JDialog dialog1 = new JDialog(frame, true);

        JPanel pnl_buscar = new JPanel();
        JPanel pnl_south = new JPanel();
        JTable table = new JTable();
        JScrollPane scroll = new JScrollPane();
        DefaultTableModel model;
        JTextField txt_buscar = new JTextField();
        JTextField txt_data = new JTextField();
        JButton btn_buscar = new JButton();
        JButton btn_siguiente = new JButton();
        JButton btn_cancelar = new JButton();

        
        dialog1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        dialog1.setSize(550, 700);
        dialog1.setLayout(new BorderLayout());
        pnl_buscar.setLayout(new FlowLayout());
        scroll.setViewportView(table);
        table.setModel(model);
        table.setFocusable(false);
        txt_buscar.setColumns(30);
        btn_buscar.setText("BUSCAR");
        btn_buscar.setFocusable(false);
        btn_siguiente.setText("SIGUIENTE");
        btn_siguiente.setFocusable(false);
        btn_siguiente.setEnabled(false);
        btn_cancelar.setText("CANCELAR");
        btn_cancelar.setFocusable(false);

        CtrHuesped ctr = new CtrHuesped();
        List<String> datosHuesped = new ArrayList<>();
        model = genery.tableModel(ctr.getDataHPD(), ctr.atributeTable());
        
        btn_buscar.addActionListener((e) -> {
            model.setRowCount(0);
            for (String[] obj : CtrHuesped.buscarHPD("numDocumento", txt_buscar.getText())) {
                model.addRow(obj);
            }
            btn_siguiente.setEnabled(false);
        });
        btn_cancelar.addActionListener((e) -> {
            dialog1.dispose();
        });
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                btn_siguiente.setEnabled(true);
            }
        });
        btn_siguiente.addActionListener(e -> {
            int row = table.getSelectedRow();
            txt_data.setText(
                table.getValueAt(row, 1)
                +","+
                table.getValueAt(row, 2)
            );
            dialog1.dispose();
            mostrarDialog2(txt_data, frame);
        });

        pnl_south.add(btn_cancelar);
        pnl_south.add(btn_siguiente);
        dialog1.add(pnl_buscar, BorderLayout.NORTH);
        dialog1.add(scroll, BorderLayout.CENTER);
        dialog1.add(pnl_south, BorderLayout.SOUTH);
        dialog1.setVisible(true);
    }

    public void mostrarDialog2(JTextField data, JFrame frame) {
        JDialog dialog2 = new JDialog(frame, true);

        JPanel pnl_south = new JPanel();
        JPanel pnl_buscar = new JPanel();
        JScrollPane scroll = new JScrollPane();
        JTable tbl_table = new JTable();
        DefaultTableModel model;
        JTextField txt_buscar = new JTextField();
        JButton btn_buscar = new JButton();
        JButton btn_siguiente = new JButton();
        JButton btn_cancelar = new JButton();


        dialog2.setSize(550, 700);
        dialog2.setLayout(new BorderLayout());
        dialog2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        pnl_buscar.setLayout(new FlowLayout());
        scroll.setViewportView(tbl_table);
        tbl_table.setModel(model);
        tbl_table.setFocusable(false);
        txt_buscar.setColumns(20);
        btn_buscar.setText("BUSCAR");
        btn_buscar.setFocusable(false);
        btn_siguiente.setText("SIGUIENTE");
        btn_siguiente.setFocusable(false);
        btn_siguiente.setEnabled(false);
        btn_cancelar.setText("CANCELAR");
        btn_cancelar.setFocusable(false);

        int opcion = JOptionPane.showConfirmDialog(null, "¿Es HABITACION VIP?", "TIPO HABITACION", JOptionPane.YES_NO_OPTION);
        boolean esVIP = (opcion == JOptionPane.YES_OPTION);
        
        CtrHabitacion ctr = new CtrHabitacion();
        if (esVIP) {
            model = genery.tableModel(ctr.getDataHBTVIPDisponible(), ctr.atributeTable());
            btn_buscar.addActionListener((e) -> {
                model.setRowCount(0);
                for (String[] obj : CtrHabitacion.buscarHBTVIP("numero", txt_buscar.getText())) {
                    model.addRow(obj);
                }
                btn_siguiente.setEnabled(false);
            });
            data.setText(data.getText()+",SI");
        } else {
            model = genery.tableModel(ctr.getDataHBTDisponible(), ctr.atributeTable());
            btn_buscar.addActionListener((e) -> {
                model.setRowCount(0);
                for (String[] obj :ctr.buscarHBT("numero", txt_buscar.getText())) {
                    model.addRow(obj);
                }
                btn_siguiente.setEnabled(false);
            });
            data.setText(data.getText()+",NO");
        }

        tbl_table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tbl_table.getSelectedRow() != -1) {
                btn_siguiente.setEnabled(true);
            }
        });
        btn_siguiente.addActionListener(e -> {
            int row = tbl_table.getSelectedRow();
            data.setText(data.getText()+","+tbl_table.getValueAt(row, 1));
            dialog2.dispose();
            mostrarDialog3(data, frame);
        });
        btn_cancelar.addActionListener((e) -> dialog2.dispose());

        pnl_buscar.add(txt_buscar);
        pnl_buscar.add(btn_buscar);
        pnl_south.add(btn_cancelar);
        pnl_south.add(btn_siguiente);
        dialog2.add(pnl_buscar, BorderLayout.NORTH);
        dialog2.add(scroll, BorderLayout.CENTER);
        dialog2.add(pnl_south, BorderLayout.SOUTH);
        
        dialog2.setVisible(true);
    }

    

    public void mostrarDialog3(JTextField data, JFrame frame) {
        JDialog dialog3 = new JDialog(frame, true);
        
        JPanel pnl_fechaIngreso = new JPanel();
        JLabel lbl_ingreso = new JLabel();
        pnl_fechaIngreso.setLayout(new FlowLayout());
        lbl_ingreso.setText("Fecha Ingreso:");
        dialog3.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        dialog3.setSize(550, 700);

        dialog3.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        
        
        JComboBox cbxDiaIngreso = new JComboBox<>(dataFecha(1, 30));
        JComboBox cbxMesIngreso = new JComboBox<>(dataFecha(1, 12));
        JComboBox cbxAnioIngreso = new JComboBox<>(dataFecha(2025, 2027));
        cbxDiaIngreso.setFocusable(false);
        cbxMesIngreso.setFocusable(false);
        cbxAnioIngreso.setFocusable(false);
        pnl_fechaIngreso.add(cbxDiaIngreso);
        pnl_fechaIngreso.add(new JLabel("/"));
        pnl_fechaIngreso.add(cbxMesIngreso);
        pnl_fechaIngreso.add(new JLabel("/"));
        pnl_fechaIngreso.add(cbxAnioIngreso);
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
        JButton btn_cancelar = new JButton("CANCELAR");
        btnGenerar.setFocusable(false);
        btn_cancelar.setFocusable(false);
        
        
        btn_cancelar.addActionListener((e) -> {
            dialog3.dispose();
        });
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        dialog3.add(lbl_ingreso, gbc);
        gbc.gridx = 1;
        dialog3.add(pnl_fechaIngreso, gbc);
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
        dialog3.add(btn_cancelar, gbc);
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
}
