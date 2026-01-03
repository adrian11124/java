package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
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
import controllers.CtrHabitacion;
import controllers.Inventario;

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
        JPanel pnl_south = new JPanel();
        JScrollPane scroll = new JScrollPane();
        JTable table = new JTable();
        DefaultTableModel model;
        JTextField txt_buscar = new JTextField();
        JButton btn_buscar = new JButton();
        JButton btn_actualizar = new JButton();
        JLabel lbl_cantHbt = new JLabel();

        CtrHabitacion ctr = new CtrHabitacion();    
        model = genery.tableModel(ctr.getDataHBT(), ctr.atributeTable());

        pnl_south.setLayout(new FlowLayout());
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
            for (String[] obj : ctr.buscarHBT("numero", txt_buscar.getText())) {
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

    private void actualizarHBT(String numHabitacion) {
        try {
            JFrame frame = Index.jfFrame();
            Habitacion hbt = null;
            // for (Habitacion h : inv.getListHBT()) {
            //     if (h.getNumero().equals(numHabitacion)) {
            //         hbt = h;
            //         break;
            //     }
            // }
            JDialog dialog = new JDialog(frame, true);
            dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            dialog.setSize(550, 700);
            dialog.setLayout(new BorderLayout());
            JPanel pnl = new JPanel(new GridBagLayout());
            dialog.add(pnl);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = 2;
            gbc.gridy = 0;
            gbc.gridx = 0;
            gbc.insets = new Insets(10, 10, 10, 10);
            
            pnl.setBackground(Color.WHITE);
            pnl.add(new JLabel("FORMULARIO ACTUALIZACION DE HABITACION "), gbc);
            gbc.gridy = 3;
            gbc.gridx = 0;
            JLabel lblMessage = new JLabel();
            pnl.add(lblMessage, gbc);
            String charOne = String.valueOf(hbt.getNumero().charAt(0));
            String stringNumHbt = hbt.getNumero().substring(1, hbt.getNumero().length());
            String[] charOnes = {charOne, "A", "B", "C", "D", "E", "F", "K", "M"};
            JComboBox cbxChar = new JComboBox<>(charOnes);
            cbxChar.setEnabled(false);
            cbxChar.setFocusable(false);
            cbxChar.setBackground(Color.WHITE);
            JTextField txtNumHbt = new JTextField(16);
            txtNumHbt.setEnabled(false);
            txtNumHbt.setText(stringNumHbt);
            //txtNumHbt.addKeyListener(txtKeyListenerNumber());
            JPanel pnlCbx = new JPanel();
            pnlCbx.setBackground(Color.WHITE);
            pnlCbx.add(cbxChar);
            pnlCbx.add(txtNumHbt);

            JTextField txtPrecio = new JTextField(20);
            txtPrecio.setText(hbt.getPrecio() + "");
            txtPrecio.addKeyListener(txtKeyListenerNumber());

            String[] tipo = {
                hbt.getTipo(),
                "INDIVIDUAL",
                "DOBLE",
                "SUITE",
                "FAMILIAR",
                "PRESIDENCIAL",
                "ECONOMICA"};
            JComboBox cbxTipoHbt = new JComboBox<>(tipo);
            String[] estado = {
                hbt.getEstado(),
                "DISPONIBLE",
                "OCUPADA",
                "RESERVADA",
                "MANTENIMIENTO"
            };
            JComboBox cbxEstado = new JComboBox<>(estado);
            cbxTipoHbt.setFocusable(false);
            cbxEstado.setFocusable(false);
            cbxTipoHbt.setBackground(Color.WHITE);
            cbxEstado.setBackground(Color.WHITE);
            cbxTipoHbt.setPreferredSize(new Dimension(225, 20));
            cbxEstado.setPreferredSize(new Dimension(225, 20));

            JLabel message = new JLabel();

            JButton btnCancelar = new JButton("CANCELAR");
            JButton btnActualizar = new JButton("ACTUALIZAR");
            btnCancelar.setFocusable(false);
            btnActualizar.setFocusable(false);

            gbc.gridy = 1;
            gbc.gridx = 0;
            gbc.gridwidth = 1;
            pnl.add(new JLabel("# HABITACION: "), gbc);

            gbc.gridx = 1;
            pnl.add(pnlCbx, gbc);
            gbc.gridy = 2;
            gbc.gridx = 0;
            pnl.add(new JLabel("PRECIO: "), gbc);
            gbc.gridx = 1;
            pnl.add(txtPrecio, gbc);

            gbc.gridy = 3;
            gbc.gridx = 0;
            pnl.add(new JLabel("TIPO HABITACION: "), gbc);
            gbc.gridx = 1;
            pnl.add(cbxTipoHbt, gbc);

            gbc.gridy = 4;
            gbc.gridx = 0;
            pnl.add(new JLabel("ESTADO"), gbc);
            gbc.gridx = 1;
            pnl.add(cbxEstado, gbc);

            gbc.gridy = 5;
            gbc.gridx = 0;
            gbc.gridwidth = 2;
            pnl.add(message, gbc);

            gbc.gridy = 6;
            gbc.gridx = 0;
            gbc.gridwidth = 1;
            pnl.add(btnCancelar, gbc);
            gbc.gridx = 1;
            pnl.add(btnActualizar, gbc);

            JLabel lblId = new JLabel(hbt.getId() + "");
            lblId.setVisible(false);
            gbc.gridy = 7;
            gbc.gridx = 0;
            pnl.add(lblId, gbc);

            btnCancelar.addActionListener((ActionEvent e) -> {
                dialog.dispose();
            });
            btnActualizar.addActionListener((ActionEvent e) -> {
                Habitacion hbt1 = new Habitacion();
                hbt1.setId(Integer.valueOf(lblId.getText()));
                hbt1.setNumero("" + cbxChar.getSelectedItem() + txtNumHbt.getText());
                hbt1.setPrecio(Integer.valueOf(txtPrecio.getText()));
                System.out.println("Precio:: "+txtPrecio.getText());
                hbt1.setTipo("" + cbxTipoHbt.getSelectedItem());
                hbt1.setEstado("" + cbxEstado.getSelectedItem());
                //inv.actualizarHBT(hbt1);
                loadPnlIndex(pnl2);
                //pnl2.add(listarHabitaciones());
                dialog.dispose();
            });

            dialog.setVisible(true);
        } catch (Exception e) {
            System.out.println("Aptualizacion de registro no sera posible, comuniquese con soporte");
        }

    }
    
    private void actualizarHBTVIP(Inventario inv, JFrame frame, String numHabitacion, JPanel pnl2) {
        try {
            HabitacionVIP hbt = null;
            // for (HabitacionVIP h : inv.getListHBTVIP()) {
            //     if (h.getNumero().equals(numHabitacion)) {
            //         hbt = h;
            //         break;
            //     }
            // }
            JDialog dialog = new JDialog(frame, true);
            dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            dialog.setSize(550, 700);
            dialog.setLayout(new BorderLayout());
            JPanel pnl = new JPanel(new GridBagLayout());
            dialog.add(pnl);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = 2;
            gbc.gridy = 0;
            gbc.gridx = 0;
            gbc.insets = new Insets(10, 10, 10, 10);
            pnl.setBackground(Color.WHITE);
            pnl.add(new JLabel("FORMULARIO ACTUALIZACION DE HABITACION"), gbc);
            gbc.gridy = 3;
            gbc.gridx = 0;
            JLabel lblMessage = new JLabel();
            pnl.add(lblMessage, gbc);
            String charOne = String.valueOf(hbt.getNumero().charAt(0));
            String stringNumHbt = hbt.getNumero().substring(1, hbt.getNumero().length());
            String[] charOnes = {charOne, "A", "B", "C", "D", "E", "F", "K", "M"};
            JComboBox cbxChar = new JComboBox<>(charOnes);
            cbxChar.setFocusable(false);
            cbxChar.setBackground(Color.WHITE);
            JTextField txtNumHbt = new JTextField(16);
            txtNumHbt.setText(stringNumHbt);
            txtNumHbt.addKeyListener(txtKeyListenerNumber());
            JPanel pnlCbx = new JPanel();
            pnlCbx.setBackground(Color.WHITE);
            cbxChar.setEnabled(false);
            txtNumHbt.setEnabled(false);
            pnlCbx.add(cbxChar);
            pnlCbx.add(txtNumHbt);

            JTextField txtPrecio = new JTextField(20);
            txtPrecio.setText(hbt.getPrecio() + "");
            txtPrecio.addKeyListener(txtKeyListenerNumber());

            String[] tipo = {
                hbt.getTipo(),
                "INDIVIDUAL",
                "DOBLE",
                "SUITE",
                "FAMILIAR",
                "PRESENCIAL",
                "ECONOMICA"};
            JComboBox cbxTipoHbt = new JComboBox<>(tipo);
            String[] estado = {
                hbt.getEstado(),
                "DISPONIBLE",
                "OCUPADA",
                "RESERVADA",
                "MANTENIMIENTO"
            };
            JComboBox cbxEstado = new JComboBox<>(estado);
            cbxTipoHbt.setBackground(Color.WHITE);
            cbxEstado.setBackground(Color.WHITE);
            cbxTipoHbt.setFocusable(false);
            cbxEstado.setFocusable(false);
            cbxTipoHbt.setPreferredSize(new Dimension(225, 20));
            cbxEstado.setPreferredSize(new Dimension(225, 20));

            JPanel pnlVip = new JPanel(new GridLayout(2, 2));
            pnlVip.setBackground(Color.WHITE);
            JCheckBox op1 = new JCheckBox("TELEVISIÓN TÁCTIL");
            JCheckBox op2 = new JCheckBox("JACUZZI");
            JCheckBox op3 = new JCheckBox("VISTA AL MAR");
            JCheckBox op4 = new JCheckBox("SALA DE SPA");
            op1.setBackground(Color.WHITE);
            op2.setBackground(Color.WHITE);
            op3.setBackground(Color.WHITE);
            op4.setBackground(Color.WHITE);
            op1.setFocusable(false);
            op2.setFocusable(false);
            op3.setFocusable(false);
            op4.setFocusable(false);
            JLabel lblVip = new JLabel();
            if (hbt.getServiciosExtras().contains("TELEVISIÓN TÁCTIL")) {
                op1.setSelected(true);
                lblVip.setText(op1.getText()+"-");
            }
            if (hbt.getServiciosExtras().contains("JACUZZI")) {
                op2.setSelected(true);
                lblVip.setText(op2.getText()+"-");
            }
            if (hbt.getServiciosExtras().contains("VISTA AL MAR")) {
                op3.setSelected(true);
                lblVip.setText(op3.getText()+"-");
            }
            if (hbt.getServiciosExtras().contains("SALA DE SPA")) {
                op4.setSelected(true);
                lblVip.setText(op4.getText()+"-");
            }

            
            lblVip.setVisible(false);
            ActionListener listener = (var e1) -> {
                lblVip.setText(
                        (op1.isSelected() ? op1.getText() + "-" : "")
                        + (op2.isSelected() ? op2.getText() + "-" : "")
                        + (op3.isSelected() ? op3.getText() + "-" : "")
                        + (op4.isSelected() ? op4.getText() + "-" : "")
                );
            };
            op1.addActionListener(listener);
            op2.addActionListener(listener);
            op3.addActionListener(listener);
            op4.addActionListener(listener);
            pnlVip.add(op1);
            pnlVip.add(op2);
            pnlVip.add(op3);
            pnlVip.add(op4);
            JLabel message = new JLabel();

            JButton btnCancelar = new JButton("CANCELAR");
            JButton btnActualizar = new JButton("ACTUALIZAR");
            btnCancelar.setFocusable(false);
            btnActualizar.setFocusable(false);

            gbc.gridy = 1;
            gbc.gridx = 0;
            gbc.gridwidth = 1;
            pnl.add(new JLabel("# HABITACION: "), gbc);

            gbc.gridx = 1;
            pnl.add(pnlCbx, gbc);
            gbc.gridy = 2;
            gbc.gridx = 0;
            pnl.add(new JLabel("PRECIO: "), gbc);
            gbc.gridx = 1;
            pnl.add(txtPrecio, gbc);

            gbc.gridy = 3;
            gbc.gridx = 0;
            pnl.add(new JLabel("TIPO HABITACION: "), gbc);
            gbc.gridx = 1;
            pnl.add(cbxTipoHbt, gbc);

            gbc.gridy = 4;
            gbc.gridx = 0;
            pnl.add(new JLabel("ESTADO"), gbc);
            gbc.gridx = 1;
            pnl.add(cbxEstado, gbc);

            gbc.gridy = 5;
            gbc.gridx = 0;
            pnl.add(new JLabel("SERVICIOS EXTRA: "), gbc);
            gbc.gridx = 1;
            pnl.add(pnlVip, gbc);
            gbc.gridx = 2;
            pnl.add(lblVip, gbc);

            gbc.gridy = 6;
            gbc.gridx = 0;
            gbc.gridwidth = 2;
            pnl.add(message, gbc);

            gbc.gridy = 7;
            gbc.gridx = 0;
            gbc.gridwidth = 1;
            pnl.add(btnCancelar, gbc);
            gbc.gridx = 1;
            pnl.add(btnActualizar, gbc);

            JLabel lblId = new JLabel(hbt.getId() + "");
            lblId.setVisible(false);

            gbc.gridy = 8;
            gbc.gridx = 0;
            pnl.add(lblId, gbc);

            btnCancelar.addActionListener((ActionEvent e) -> {
                dialog.dispose();
            });
            btnActualizar.addActionListener((ActionEvent e) -> {
                HabitacionVIP hbt1 = new HabitacionVIP();
                hbt1.setId(Integer.valueOf(lblId.getText()));
                hbt1.setNumero("" + cbxChar.getSelectedItem() + txtNumHbt.getText());
                hbt1.setPrecio(Integer.valueOf(txtPrecio.getText()));
                hbt1.setTipo("" + cbxTipoHbt.getSelectedItem());
                hbt1.setEstado("" + cbxEstado.getSelectedItem());
                String servicios = lblVip.getText();
                hbt1.setServiciosExtras(servicios.substring(0, servicios.length() - 1));
                // inv.actualizarHBTVIP(hbt1);
                loadPnlIndex(pnl2);
                // pnl2.add(listarHabitacionesVIP(inv, frame));
                dialog.dispose();
            });

            dialog.setVisible(true);
        } catch (Exception e) {
            System.out.println("Aptualizacion de registro no sera posible, comuniquese con soporte");
        }

    }
}
