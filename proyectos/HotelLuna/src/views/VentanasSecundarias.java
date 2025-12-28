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
public class VentanasSecundarias {

    static KeyListener txtKeyListenerNumber() {
        return new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        };
    }

    static KeyListener txtKeyListenerChar() {
        return new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)) {
                    e.consume();
                }
            }
        };
    }

    

    

    private void actualizarHBT(Inventario inv, JFrame frame, String numHabitacion, JPanel pnl2) {
        try {
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

    

    public void actualizarHuesped(Inventario inv, JFrame frame, JPanel pnl2, String documentoH) {
        Huesped hpd = null;
        // for (Huesped h : inv.getListHPD()) {
        //     if (h.getNumDocumento().equals(Integer.valueOf(documentoH))) {
        //         hpd = h;
        //         break;
        //     }
        // }
        JDialog dg = new JDialog(frame, true);
        dg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dg.setSize(450, 600);
        JPanel pnl = new JPanel(new GridBagLayout());
        dg.add(pnl);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 2;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        pnl.setBackground(Color.WHITE);
        pnl.add(new JLabel("FORMULARIO DE REGISTRO DE HUESPED "), gbc);
        gbc.gridy = 3;
        gbc.gridx = 0;
        JLabel lblMessage = new JLabel();
        pnl.add(lblMessage, gbc);
        JTextField txtDocumento = new JTextField(20);
        txtDocumento.setText(hpd.getNumDocumento()+ "");
        txtDocumento.addKeyListener(txtKeyListenerNumber());
        JTextField txtNombre = new JTextField(20);
        txtNombre.setText(hpd.getNombre() + "");
        txtNombre.addKeyListener(txtKeyListenerChar());
        JButton btnCancelar = new JButton("CANCELAR");
        JButton btnActualizar = new JButton("ACTUALIZAR");
        btnCancelar.setFocusable(false);
        btnActualizar.setFocusable(false);

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        pnl.add(new JLabel("# DOCUMENTO: "), gbc);

        gbc.gridx = 1;
        pnl.add(txtDocumento, gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        pnl.add(new JLabel("NOMBRE: "), gbc);
        gbc.gridx = 1;
        pnl.add(txtNombre, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        pnl.add(btnCancelar, gbc);
        gbc.gridx = 1;
        pnl.add(btnActualizar, gbc);

        btnActualizar.addActionListener((ActionEvent e) -> {
            Huesped hpd2 = new Huesped();
            hpd2.setNumDocumento(Integer.valueOf(txtDocumento.getText()));
            hpd2.setNombre(txtNombre.getText());
            // inv.actualizarHPD(hpd2);
            loadPnlIndex(pnl2);
            pnl2.add(listarHuesped(inv, frame));
            dg.dispose();
        });
        btnCancelar.addActionListener((ActionEvent e) -> {
            dg.dispose();
        });
        dg.setVisible(true);
    }

    private void reservarHabitacion(Inventario inv, JFrame frame) {
        JReservar r = new JReservar();
        r.dialog1(inv, frame);
    }

    private JPanel listarHuesped(Inventario inv, JFrame frame) {
        JPanel pnl = new JPanel(new BorderLayout());
        pnl.setBackground(Color.WHITE);
        String[] columnTable = {"# DOCUMENTO", "NOMBRE"};
        DefaultTableModel model = tableModel(inv.getDataHPD(), columnTable);
        JTable table = new JTable(model);
        table.setFocusable(false);
        JTextField txtBuscar = new JTextField(20);
        JButton btnBuscar = new JButton("BUSCAR");
        JButton btnRegistrar = new JButton("REGISTRAR");
        JButton btnActualizar = new JButton("ACTUALIZAR");
        JButton btnEliminar = new JButton("ELIMINAR");
        
        btnBuscar.addActionListener((e) -> {
            model.setRowCount(0);
            for (String[] obj : inv.buscarHPD("numDocumento", txtBuscar.getText())) {
                model.addRow(obj);
            }
            btnActualizar.setEnabled(false);
            btnEliminar.setEnabled(false);
        });
        JScrollPane scroll = new JScrollPane(table);
        
        btnRegistrar.setFocusable(false);
        btnActualizar.setFocusable(false);
        btnEliminar.setFocusable(false);
        btnBuscar.setFocusable(false);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                btnActualizar.setEnabled(true);
                btnEliminar.setEnabled(true);
            }
        });
        JPanel pnlBtn = new JPanel(new FlowLayout());
        JPanel pnlBuscar = new JPanel(new FlowLayout());
        pnlBuscar.add(txtBuscar);
        pnlBuscar.add(btnBuscar);
        pnlBtn.add(btnRegistrar);
        pnlBtn.add(btnActualizar);
        pnlBtn.add(btnEliminar);
        btnRegistrar.addActionListener((e) -> {
            registrarHuesped(inv, frame, pnl);
        });
        btnActualizar.addActionListener((e) -> {
            int row = table.getSelectedRow();
            actualizarHuesped(inv, frame, pnl, table.getValueAt(row, 1) + "");
        });
        btnEliminar.addActionListener((e) -> {
            int row = table.getSelectedRow();
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar Huesped?", "CONFIRMACION", JOptionPane.YES_NO_OPTION);
            boolean op = (opcion == JOptionPane.YES_OPTION);
            if(op){
                inv.removeHPD(table.getValueAt(row, 1).toString());
                loadPnlIndex(pnl);
                pnl.add(listarHuesped(inv, frame));
            }
            
        });
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

    

    private JPanel estadistica(Inventario inventario) {
        JPanel pnl = new JPanel();
        inventario.cantidadHabitaciones();
        pnl.setBackground(Color.WHITE);

        return pnl;
    }

}
