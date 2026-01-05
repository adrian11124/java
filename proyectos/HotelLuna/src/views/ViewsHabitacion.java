package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Models.Habitacion;
import Models.HabitacionVIP;
import controllers.CtrHabitacion;

/**
 *
 * @author adrian11124
 */
public class ViewsHabitacion {
    private GeneryViews genery = new GeneryViews();

    public JPanel listarHabitacionesVIP() {

        JPanel pnl = new JPanel();
        JPanel pnl_buscar = new JPanel();
        JPanel pnl_south = new JPanel();
        JScrollPane scroll = new JScrollPane();
        JTable tbl_table = new JTable();
        DefaultTableModel model;
        JTextField txt_buscar = new JTextField();
        JButton btn_buscar = new JButton();
        JButton btn_actualizar = new JButton();
        JLabel lbl_cantHbt = new JLabel();

        CtrHabitacion ctr = new CtrHabitacion();    
        model = genery.tableModel(ctr.getDataHBTVIP(), ctr.atributeTableTwo());

        pnl_south.setLayout(new FlowLayout());
        btn_actualizar.setText("ACTUALIZAR");
        btn_buscar.setText("BUSCAR");
        pnl.setBackground(Color.WHITE);
        pnl.setLayout(new BorderLayout());
        tbl_table.setModel(model);
        scroll.setViewportView(tbl_table);
        tbl_table.setFocusable(false);
        tbl_table.setBackground(Color.WHITE);
        pnl_buscar.setBackground(Color.WHITE);
        txt_buscar.setColumns(30);
        btn_buscar.setFocusable(false);
        lbl_cantHbt.setText(null);
        btn_actualizar.setFocusable(false);
        btn_actualizar.setEnabled(false);
        
        btn_buscar.addActionListener((e) -> {
            model.setRowCount(0);
            for (String[] obj : ctr.buscarHBTVIP("numero", txt_buscar.getText())) {
                model.addRow(obj);
            }
            btn_actualizar.setEnabled(false);
        });
        tbl_table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tbl_table.getSelectedRow() != -1) {
                btn_actualizar.setEnabled(true);
            }
        });
        btn_actualizar.addActionListener(e -> {
            int row = tbl_table.getSelectedRow();
            String numHabitacion = ""+tbl_table.getValueAt(row, 1);
            actualizarHBTVIP(pnl, numHabitacion);
        });
        
        pnl_south.add(btn_actualizar);
        pnl_buscar.add(txt_buscar);
        pnl_buscar.add(btn_buscar);
        pnl_buscar.add(lbl_cantHbt);
        pnl.add(pnl_buscar, BorderLayout.NORTH);
        pnl.add(scroll, BorderLayout.CENTER);
        pnl.add(pnl_south, BorderLayout.SOUTH);

        return pnl;
    }


    /**
     *Genera un vista que enlista todas las habitaciones(normal, vip)
     */
    public JPanel listarHabitaciones() {
        JPanel pnl = new JPanel();
        JPanel pnl_buscar = new JPanel();
        JPanel pnl_south = new JPanel();
        JTable tbl_table = new JTable();
        JScrollPane scroll = new JScrollPane();
        DefaultTableModel model;
        JTextField txt_buscar = new JTextField();
        JButton btn_buscar = new JButton();
        JButton btn_actualizar = new JButton();
        JLabel cant_habitaciones = new JLabel();
        
        CtrHabitacion ctr = new CtrHabitacion();
        model = genery.tableModel(ctr.getDataHBT(), ctr.atributeTable());

        tbl_table.setModel(model);
        tbl_table.setFocusable(false);
        scroll.setViewportView(tbl_table);
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
        
        tbl_table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tbl_table.getSelectedRow() != -1) {
                btn_actualizar.setEnabled(true);
            }
        });
        btn_buscar.addActionListener((e) -> {
            model.setRowCount(0);
            for (String[] obj : ctr.buscarHBT("numero", txt_buscar.getText())) {
                model.addRow(obj);
            }
           btn_actualizar.setEnabled(false);
        });
        btn_actualizar.addActionListener(e -> {
            int row = tbl_table.getSelectedRow();
            String numHabitacion = tbl_table.getValueAt(row, 1)+"";
            actualizarHBT(pnl, numHabitacion);
        });
        
        pnl_buscar.add(cant_habitaciones);
        pnl_south.add(btn_actualizar);
        pnl.add(pnl_buscar, BorderLayout.NORTH);
        pnl.add(scroll, BorderLayout.CENTER);
        pnl.add(pnl_south, BorderLayout.SOUTH);
        return pnl;
    }

    private void actualizarHBT(JPanel pnl_origin, String numHabitacion) {
        try {
            JDialog dg = new JDialog();
            
            JPanel pnl = new JPanel();
            JPanel pnl_cbx = new JPanel();
            JTextField txt_numHbt = new JTextField();
            JTextField txt_precio = new JTextField();
            JButton btn_cancelar = new JButton();
            JButton btn_actualizar = new JButton();
            JLabel lbl_message = new JLabel();
            JLabel lbl_title = new JLabel();
            JLabel lbl_id = new JLabel();
            
            CtrHabitacion ctr = new CtrHabitacion();
            Habitacion hbt = ctr.searhHabitacionByNumHabitacion(numHabitacion);
            String charOne = String.valueOf(hbt.getNumero().charAt(0));
            String stringNumHbt = hbt.getNumero().substring(1, hbt.getNumero().length());
            JComboBox cbx_char = new JComboBox<>(ctr.charOnes(charOne));
            JComboBox cbx_tipo = new JComboBox<>(ctr.arrayTipo(hbt.getTipo()));
            JComboBox cbx_estado = new JComboBox<>(ctr.arrayEstado(hbt.getEstado()));
            GridBagConstraints gbc = new GridBagConstraints();
            
            dg.setSize(550, 700);
            dg.setLayout(new BorderLayout());
            dg.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            dg.setUndecorated(true);
            dg.setModal(true);
            pnl.setBackground(Color.WHITE);
            pnl.setLayout(new GridBagLayout());
            pnl_cbx.setBackground(Color.WHITE);
            cbx_char.setBackground(Color.WHITE);
            cbx_char.setEnabled(false);
            cbx_char.setFocusable(false);
            cbx_tipo.setFocusable(false);
            cbx_estado.setFocusable(false);
            cbx_tipo.setBackground(Color.WHITE);
            cbx_estado.setBackground(Color.WHITE);
            cbx_tipo.setPreferredSize(new Dimension(225, 20));
            cbx_estado.setPreferredSize(new Dimension(225, 20));
            txt_precio.setText(hbt.getPrecio() + "");
            txt_numHbt.setEnabled(false);
            txt_numHbt.setText(stringNumHbt);
            txt_numHbt.setColumns(16);
            txt_precio.setColumns(16);
            btn_cancelar.setFocusable(false);
            btn_cancelar.setText("CANCELAR");
            btn_actualizar.setText("ACTUALIZAR");
            btn_actualizar.setFocusable(false);
            lbl_title.setText("FORMULARIO ACTUALIZACION DE HABITACION ");
            lbl_id.setText(hbt.getId() + "");
            lbl_id.setVisible(false);

            gbc.gridwidth = 2;
            gbc.gridy = 0;
            gbc.gridx = 0;
            gbc.insets = new Insets(10, 10, 10, 10);
            pnl.add(lbl_title, gbc);
            gbc.gridy = 3;
            gbc.gridx = 0;
            pnl.add(lbl_message, gbc);
            gbc.gridy = 1;
            gbc.gridx = 0;
            gbc.gridwidth = 1;
            pnl.add(new JLabel("# HABITACION: "), gbc);
            gbc.gridx = 1;
            pnl.add(pnl_cbx, gbc);
            gbc.gridy = 2;
            gbc.gridx = 0;
            pnl.add(new JLabel("PRECIO: "), gbc);
            gbc.gridx = 1;
            pnl.add(txt_precio, gbc);
            gbc.gridy = 3;
            gbc.gridx = 0;
            pnl.add(new JLabel("TIPO HABITACION: "), gbc);
            gbc.gridx = 1;
            pnl.add(cbx_tipo, gbc);
            gbc.gridy = 4;
            gbc.gridx = 0;
            pnl.add(new JLabel("ESTADO"), gbc);
            gbc.gridx = 1;
            pnl.add(cbx_estado, gbc);
            gbc.gridy = 5;
            gbc.gridx = 0;
            gbc.gridwidth = 2;
            pnl.add(lbl_message, gbc);
            gbc.gridy = 6;
            gbc.gridx = 0;
            gbc.gridwidth = 1;
            pnl.add(btn_cancelar, gbc);
            gbc.gridx = 1;
            pnl.add(btn_actualizar, gbc);
            gbc.gridy = 7;
            gbc.gridx = 0;
            pnl.add(lbl_id, gbc);

            btn_actualizar.addActionListener((ActionEvent e) -> {
                hbt.setId(Integer.valueOf(lbl_id.getText()));
                hbt.setNumero("" + cbx_char.getSelectedItem() + txt_numHbt.getText());
                hbt.setPrecio(Integer.valueOf(txt_precio.getText()));
                hbt.setTipo("" + cbx_tipo.getSelectedItem());
                hbt.setEstado("" + cbx_estado.getSelectedItem());
                ctr.actualizarHBT(hbt);
                genery.ajustarNewPanel(pnl_origin, listarHabitaciones());
                dg.dispose();
            });
            btn_cancelar.addActionListener((ActionEvent e) -> {
                dg.dispose();
            });
            txt_precio.addKeyListener(genery.txtKeyListenerNumber());
            txt_numHbt.addKeyListener(genery.txtKeyListenerNumber());

            pnl_cbx.add(cbx_char);
            pnl_cbx.add(txt_numHbt);
            dg.add(pnl);
            dg.setVisible(true);
        } catch (Exception e) {
            System.out.println("Aptualizacion de registro no sera posible, comuniquese con soporte ");
        }

    }
    
    private void actualizarHBTVIP(JPanel pnl_origin, String numHabitacion) {
        try {
            JDialog dg = new JDialog();
            JPanel pnl = new JPanel();
            JPanel pnl_cbx = new JPanel();
            JPanel pnl_vip = new JPanel();
            JTextField txt_precio = new JTextField();
            JTextField txt_numHbt = new JTextField(16);
            JButton btn_cancelar = new JButton();
            JButton btn_actualizar = new JButton();
            JLabel lbl_title = new JLabel();
            JLabel lbl_id = new JLabel();
            JLabel lbl_vip = new JLabel();
            JLabel lbl_message = new JLabel();
            CtrHabitacion ctr = new CtrHabitacion();
            HabitacionVIP hbt = ctr.searhHabitacionVIPByNumHabitacion(numHabitacion);
            GridBagConstraints gbc = new GridBagConstraints();
            String charOne = String.valueOf(hbt.getNumero().charAt(0));
            String stringNumHbt = hbt.getNumero().substring(1, hbt.getNumero().length());
            txt_numHbt.setText(stringNumHbt);
            JComboBox cbx_char = new JComboBox<>(ctr.charOnes(charOne));
            JComboBox cbx_tipo = new JComboBox<>(ctr.arrayTipo(hbt.getTipo()));
            JComboBox cbx_estado = new JComboBox<>(ctr.arrayEstado(hbt.getEstado()));
            JCheckBox op1 = new JCheckBox();
            JCheckBox op2 = new JCheckBox();
            JCheckBox op3 = new JCheckBox();
            JCheckBox op4 = new JCheckBox();

            dg.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            dg.setSize(550, 700);
            dg.setLayout(new BorderLayout());
            dg.setUndecorated(true);
            dg.setModal(true);
            pnl.setLayout(new GridBagLayout());
            pnl.setBackground(Color.WHITE);
            pnl_vip.setLayout(new GridLayout(2, 2));
            pnl_cbx.setBackground(Color.WHITE);
            cbx_char.setFocusable(false);
            cbx_char.setBackground(Color.WHITE);
            cbx_char.setEnabled(false);
            txt_numHbt.setEnabled(false);
            cbx_tipo.setBackground(Color.WHITE);
            cbx_estado.setBackground(Color.WHITE);
            cbx_tipo.setFocusable(false);
            cbx_estado.setFocusable(false);
            cbx_tipo.setPreferredSize(new Dimension(225, 20));
            cbx_estado.setPreferredSize(new Dimension(225, 20));
            txt_numHbt.setColumns(16);
            txt_numHbt.setText(stringNumHbt);
            txt_precio.setColumns(20);
            btn_cancelar.setText("CANCELAR");
            btn_cancelar.setFocusable(false);
            btn_actualizar.setText("ACTUALIZAR");
            btn_actualizar.setFocusable(false);
            lbl_title.setText("FORMULARIO ACTUALIZACION DE HABITACION");
            lbl_id.setText(hbt.getId() + "");
            lbl_id.setVisible(false);
            pnl_vip.setBackground(Color.WHITE);
            lbl_vip.setVisible(false);
            op1.setText("TELEVISIÓN TÁCTIL");
            op2.setText("JACUZZI");
            op3.setText("VISTA AL MAR");
            op4.setText("SALA DE SPA");
            op1.setBackground(Color.WHITE);
            op2.setBackground(Color.WHITE);
            op3.setBackground(Color.WHITE);
            op4.setBackground(Color.WHITE);
            op1.setFocusable(false);
            op2.setFocusable(false);
            op3.setFocusable(false);
            op4.setFocusable(false);

            gbc.gridwidth = 2;
            gbc.gridy = 0;
            gbc.gridx = 0;
            gbc.insets = new Insets(10, 10, 10, 10);
            pnl.add(lbl_title, gbc);
            gbc.gridy = 3;
            gbc.gridx = 0;
            pnl.add(lbl_message, gbc);
            gbc.gridy = 1;
            gbc.gridx = 0;
            gbc.gridwidth = 1;
            pnl.add(new JLabel("# HABITACION: "), gbc);
            gbc.gridx = 1;
            pnl.add(pnl_cbx, gbc);
            gbc.gridy = 2;
            gbc.gridx = 0;
            pnl.add(new JLabel("PRECIO: "), gbc);
            gbc.gridx = 1;
            pnl.add(txt_precio, gbc);
            gbc.gridy = 3;
            gbc.gridx = 0;
            pnl.add(new JLabel("TIPO HABITACION: "), gbc);
            gbc.gridx = 1;
            pnl.add(cbx_tipo, gbc);
            gbc.gridy = 4;
            gbc.gridx = 0;
            pnl.add(new JLabel("ESTADO"), gbc);
            gbc.gridx = 1;
            pnl.add(cbx_estado, gbc);
            gbc.gridy = 5;
            gbc.gridx = 0;
            pnl.add(new JLabel("SERVICIOS EXTRA: "), gbc);
            gbc.gridx = 1;
            pnl.add(pnl_vip, gbc);
            gbc.gridx = 2;
            pnl.add(lbl_vip, gbc);
            gbc.gridy = 6;
            gbc.gridx = 0;
            gbc.gridwidth = 2;
            pnl.add(lbl_message, gbc);
            gbc.gridy = 7;
            gbc.gridx = 0;
            gbc.gridwidth = 1;
            pnl.add(btn_cancelar, gbc);
            gbc.gridx = 1;
            pnl.add(btn_actualizar, gbc);
            gbc.gridy = 8;
            gbc.gridx = 0;
            pnl.add(lbl_id, gbc);

            if (hbt.getServiciosExtras().contains("TELEVISIÓN TÁCTIL")) {
                op1.setSelected(true);
                lbl_vip.setText(op1.getText()+"-");
            }
            if (hbt.getServiciosExtras().contains("JACUZZI")) {
                op2.setSelected(true);
                lbl_vip.setText(op2.getText()+"-");
            }
            if (hbt.getServiciosExtras().contains("VISTA AL MAR")) {
                op3.setSelected(true);
                lbl_vip.setText(op3.getText()+"-");
            }
            if (hbt.getServiciosExtras().contains("SALA DE SPA")) {
                op4.setSelected(true);
                lbl_vip.setText(op4.getText()+"-");
            }

            ActionListener listener = (var e1) -> {
                lbl_vip.setText(
                        (op1.isSelected() ? op1.getText() + "-" : "")
                        + (op2.isSelected() ? op2.getText() + "-" : "")
                        + (op3.isSelected() ? op3.getText() + "-" : "")
                        + (op4.isSelected() ? op4.getText() + "-" : "")
                );
            };
            btn_actualizar.addActionListener((ActionEvent e) -> {
                hbt.setId(Integer.valueOf(lbl_id.getText()));
                hbt.setNumero("" + cbx_char.getSelectedItem() + txt_numHbt.getText());
                hbt.setPrecio(Integer.valueOf(txt_precio.getText()));
                hbt.setTipo("" + cbx_tipo.getSelectedItem());
                hbt.setEstado("" + cbx_estado.getSelectedItem());
                String servicios = lbl_vip.getText();
                hbt.setServiciosExtras(servicios.substring(0, servicios.length() - 1));
                ctr.actualizarHBTVIP(hbt);
                genery.ajustarNewPanel(pnl_origin, listarHabitacionesVIP());
                dg.dispose();
            });
            btn_cancelar.addActionListener((ActionEvent e) -> {
                dg.dispose();
            });
            txt_numHbt.addKeyListener(genery.txtKeyListenerNumber());
            txt_precio.addKeyListener(genery.txtKeyListenerNumber());

            op1.addActionListener(listener);
            op2.addActionListener(listener);
            op3.addActionListener(listener);
            op4.addActionListener(listener);
            pnl_cbx.add(cbx_char);
            pnl_cbx.add(txt_numHbt);
            pnl_vip.add(op1);
            pnl_vip.add(op2);
            pnl_vip.add(op3);
            pnl_vip.add(op4);
            dg.add(pnl);

            dg.setVisible(true);
        } catch (Exception e) {
            System.out.println("Aptualizacion de registro no sera posible, comuniquese con soporte "+e);
        }

    }
}
