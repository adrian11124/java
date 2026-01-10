package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

import bd.conexion;
import controllers.CtrHuesped;
import controllers.CtrReserva;

/**
 *
 * @author adrian11124
 */
public class ViewsReserva {

    private GeneryViews genery = new GeneryViews();
    /**
     *Vista de todas las reservas generadas
     */
    public JPanel listarReserva() {
        CtrReserva ctr = new CtrReserva();
        JPanel pnl = new JPanel();
        JTable table = new JTable();
        JPanel pnl_south = new JPanel();
        JButton btn_remove = new JButton("ELIMINAR");
        JScrollPane scroll = new JScrollPane();
        DefaultTableModel model = genery.tableModel(ctr.listToArrayReserva(), ctr.atributeTable());

        pnl.setLayout(new BorderLayout());
        pnl.setBackground(Color.WHITE);
        pnl_south.setLayout(new FlowLayout());
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
                ctr.removeReservaById(table.getValueAt(row, 0).toString());
                JPanel lstReserva =  listarReserva();
                genery.ajustarNewPanel(pnl, lstReserva);
            }
            
        });
        pnl_south.add(btn_remove);
        pnl.add(scroll, BorderLayout.CENTER);
        pnl.add(pnl_south, BorderLayout.SOUTH);
        return pnl;
    }

    public void reservarHabitacion() {
        selectedHuesped();
    }

    public void selectedHuesped() {
        CtrReserva ctr = new CtrReserva();
        JDialog dg = new JDialog();

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

        model = genery.tableModel(ctr.listToArrayHuesped(), ctr.atributeTableHuesped());

        dg.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        dg.setSize(550, 700);
        dg.setModal(true);
        dg.setUndecorated(true);
        dg.setLayout(new BorderLayout());
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

        btn_buscar.addActionListener((e) -> {
            model.setRowCount(0);
            for (String[] obj : ctr.searhHuesped("numDocumento", txt_buscar.getText())) {
                model.addRow(obj);
            }
            btn_siguiente.setEnabled(false);
        });
        btn_cancelar.addActionListener((e) -> {
            dg.dispose();
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
            dg.dispose();
            selectedHabitacion(txt_data);
        });

        pnl_south.add(btn_cancelar);
        pnl_south.add(btn_siguiente);
        dg.add(pnl_buscar, BorderLayout.NORTH);
        dg.add(scroll, BorderLayout.CENTER);
        dg.add(pnl_south, BorderLayout.SOUTH);
        dg.setVisible(true);
    }

    public void selectedHabitacion(JTextField data) {
        CtrReserva ctr = new CtrReserva();
        JDialog dg = new JDialog();
        JPanel pnl_south = new JPanel();
        JPanel pnl_buscar = new JPanel();
        JScrollPane scroll = new JScrollPane();
        JTable tbl_table = new JTable();
        DefaultTableModel model;
        JTextField txt_buscar = new JTextField();
        JButton btn_buscar = new JButton();
        JButton btn_siguiente = new JButton();
        JButton btn_cancelar = new JButton();

        dg.setSize(550, 700);
        dg.setLayout(new BorderLayout());
        dg.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        dg.setModal(true);
        pnl_buscar.setLayout(new FlowLayout());
        scroll.setViewportView(tbl_table);
        
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
        
        
        if (esVIP) {
            model = genery.tableModel(ctr.listToArrayDataVipDisponible(), ctr.atributeTableHabitacionVip());
            btn_buscar.addActionListener((e) -> {
                model.setRowCount(0);
                for (String[] obj : ctr.searchHabitacionVIP("numero", txt_buscar.getText())) {
                    model.addRow(obj);
                }
                btn_siguiente.setEnabled(false);
            });
            data.setText(data.getText()+",SI");
        } else {
            model = genery.tableModel(ctr.listToArrayHabitacionDisponible(), ctr.atributeTableHabitacion());
            btn_buscar.addActionListener((e) -> {
                model.setRowCount(0);
                for (String[] obj :ctr.searchHabitacion("numero", txt_buscar.getText())) {
                    model.addRow(obj);
                }
                btn_siguiente.setEnabled(false);
            });
            data.setText(data.getText()+",NO");
        }tbl_table.setModel(model);

        tbl_table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tbl_table.getSelectedRow() != -1) {
                btn_siguiente.setEnabled(true);
            }
        });
        btn_siguiente.addActionListener(e -> {
            int row = tbl_table.getSelectedRow();
            data.setText(data.getText()+","+tbl_table.getValueAt(row, 1));
            dg.dispose();
            createReserva(data);
        });
        btn_cancelar.addActionListener((e) -> dg.dispose());

        pnl_buscar.add(txt_buscar);
        pnl_buscar.add(btn_buscar);
        pnl_south.add(btn_cancelar);
        pnl_south.add(btn_siguiente);
        dg.add(pnl_buscar, BorderLayout.NORTH);
        dg.add(scroll, BorderLayout.CENTER);
        dg.add(pnl_south, BorderLayout.SOUTH);
        
        dg.setVisible(true);
    }

    

    public void createReserva(JTextField data) {
        CtrReserva ctr = new CtrReserva();
        JDialog dg = new JDialog();
        JComboBox cbx_diaIngreso = new JComboBox<>(dataFecha(1, 30)); 
        JComboBox cbx_mesIngreso  = new JComboBox<>(dataFecha(1, 12));
        JComboBox cbx_anioIngreso = new JComboBox<>(dataFecha(2025, 2027)); 
        JComboBox cbx_diaSalida = new JComboBox<>(dataFecha(1, 30));
        JComboBox cbx_mesSalida = new JComboBox<>(dataFecha(1, 12));; 
        JComboBox cbx_anioSalida = new JComboBox<>(dataFecha(2025, 2027)); 
        JPanel pnl_fechaIngreso = new JPanel();
        JPanel pnl_fechaSalida = new JPanel();
        JLabel lbl_ingreso = new JLabel();
        JLabel lbl_salida = new JLabel();
        JButton btn_generar = new JButton();
        JButton btn_cancelar = new JButton();
        GridBagConstraints gbc;

        dg.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        dg.setSize(550, 700);
        dg.setLayout(new GridBagLayout());
        dg.setModal(true);
        dg.setUndecorated(true);
        gbc = new GridBagConstraints();
        pnl_fechaIngreso.setLayout(new FlowLayout());
        pnl_fechaSalida.setLayout(new FlowLayout());
        lbl_ingreso.setText("Fecha Ingreso:");
        lbl_salida.setText("Fecha Salida:");
        btn_generar.setText("GENERAR RESERVA");
        btn_cancelar.setText("CANCELAR");
        btn_generar.setFocusable(false);
        btn_cancelar.setFocusable(false);
        cbx_diaIngreso.setFocusable(false);
        cbx_mesIngreso.setFocusable(false);
        cbx_anioIngreso.setFocusable(false);
        cbx_diaSalida.setFocusable(false);
        cbx_mesSalida.setFocusable(false);
        cbx_anioSalida.setFocusable(false);

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        dg.add(lbl_ingreso, gbc);
        gbc.gridx = 1;
        dg.add(pnl_fechaIngreso, gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        dg.add(lbl_salida, gbc);
        gbc.gridx = 1;
        dg.add(pnl_fechaSalida, gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        dg.add(new JLabel("Formato fecha(dd/MM/YYYY)"), gbc);
        gbc.gridy = 3;
        gbc.gridx = 0;
        dg.add(btn_cancelar, gbc);
        gbc.gridx = 1;
        dg.add(btn_generar, gbc);

        btn_generar.addActionListener(e -> {
            data.setText(
                    data.getText()+","+
                       cbx_diaIngreso.getSelectedItem() + "/" 
                     + cbx_mesIngreso.getSelectedItem()
                     + "/" + cbx_anioIngreso.getSelectedItem()
                     +","+
                       cbx_diaSalida.getSelectedItem() + "/" 
                     + cbx_mesSalida.getSelectedItem()+ "/" 
                     + cbx_anioSalida.getSelectedItem()
                    );;

            ctr.createReserva(data.getText());
            dg.dispose();
        });
        btn_cancelar.addActionListener((e) -> dg.dispose());

        pnl_fechaIngreso.add(cbx_diaIngreso);
        pnl_fechaIngreso.add(new JLabel("/"));
        pnl_fechaIngreso.add(cbx_mesIngreso);
        pnl_fechaIngreso.add(new JLabel("/"));
        pnl_fechaIngreso.add(cbx_anioIngreso);
        pnl_fechaSalida.add(cbx_diaSalida);
        pnl_fechaSalida.add(new JLabel("/"));
        pnl_fechaSalida.add(cbx_mesSalida);
        pnl_fechaSalida.add(new JLabel("/"));
        pnl_fechaSalida.add(cbx_anioSalida);
        dg.setVisible(true);
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
