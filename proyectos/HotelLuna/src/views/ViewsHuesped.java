package views;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

import controllers.CtrHuesped;
import controllers.Inventario;

/**
 *
 * @author adrian11124
 */
public class ViewsHuesped {

    private GeneryViews genery = new GeneryViews();

    public JPanel listarHuesped() {
        JPanel pnl = new JPanel();
        JPanel pnl_south = new JPanel();
        JPanel pnl_buscar = new JPanel();
        JTable table = new JTable();
        CtrHuesped ctr = new CtrHuesped();
        JTextField txt_buscar = new JTextField();
        JButton btn_buscar = new JButton();
        JButton btn_registrar = new JButton();
        JButton btn_actualizar = new JButton();
        JButton btn_eliminar = new JButton();
        JScrollPane scroll = new JScrollPane();
        DefaultTableModel model = genery.tableModel(ctr.getDataHPD(), ctr.atributeTable());
        
        pnl.setLayout(new BorderLayout());
        pnl.setBackground(Color.WHITE);
        pnl_south.setLayout(new FlowLayout());
        pnl_buscar.setLayout(new FlowLayout());
        table.setFocusable(false);
        txt_buscar.setColumns(30);
        btn_buscar.setText("BUSCAR");
        btn_buscar.setFocusable(false);
        btn_registrar.setText("REGISTRAR");
        btn_actualizar.setText("ACTUALIZAR");
        btn_eliminar.setText("ELIMINAR");
        btn_eliminar.setEnabled(false);
        btn_eliminar.setFocusable(false);
        btn_registrar.setFocusable(false);
        btn_actualizar.setFocusable(false);
        btn_actualizar.setEnabled(false);
        table.setModel(model);
        scroll.setViewportView(table);
        
        
        
        
        btn_buscar.addActionListener((e) -> {
            model.setRowCount(0);
            for (String[] obj : ctr.buscarHPD("numDocumento", txt_buscar.getText())) {
                model.addRow(obj);
            }
            btn_actualizar.setEnabled(false);
            btn_eliminar.setEnabled(false);
        });
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                btn_actualizar.setEnabled(true);
                btn_eliminar.setEnabled(true);
            }
        });
        
        pnl_buscar.add(txt_buscar);
        pnl_buscar.add(btn_buscar);
        pnl_south.add(btn_registrar);
        pnl_south.add(btn_actualizar);
        pnl_south.add(btn_eliminar);
        btn_registrar.addActionListener((e) -> {
            registrarHuesped();
        });
        btn_actualizar.addActionListener((e) -> {
            int row = table.getSelectedRow();
            actualizarHuesped(table.getValueAt(row, 1) + "");
        });
        btn_eliminar.addActionListener((e) -> {
            int row = table.getSelectedRow();
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar Huesped?", "CONFIRMACION", JOptionPane.YES_NO_OPTION);
            boolean op = (opcion == JOptionPane.YES_OPTION);
            if(op){
                ctr.removeHPD(table.getValueAt(row, 1).toString());
                loadPnlIndex(pnl);
                pnl.add(listarHuesped());
            }
            
        });
        pnl.add(pnl_buscar, BorderLayout.NORTH);
        pnl.add(scroll, BorderLayout.CENTER);
        pnl.add(pnl_south, BorderLayout.SOUTH);

        return pnl;
    }

    public void registrarHuesped() {
        JFrame frame = Index.jfFrame();
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
        txtDocumento.addKeyListener(txtKeyListenerNumber());
        JTextField txtNombre = new JTextField(20);
        txtNombre.addKeyListener(txtKeyListenerChar());
        JButton btnCancelar = new JButton("CANCELAR");
        JButton btn_registrar = new JButton("REGISTRAR");
        btnCancelar.setFocusable(false);
        btn_registrar.setFocusable(false);

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
        pnl.add(btn_registrar, gbc);

        btn_registrar.addActionListener((ActionEvent e) -> {
            // lblMessage.setText(CtrHuesped.registrarHuesped(txtNombre.getText(), txtDocumento.getText()));
            if (lblMessage.getText().equals("")) {
                dg.dispose();
                genery.ajustarNewPanel(pnl, listarHuesped());
            }
        });
        btnCancelar.addActionListener((ActionEvent e) -> {
            dg.dispose();
        });
        dg.setVisible(true);
    }

    public void actualizarHuesped(String documentoH) {
        JFrame frame = Index.jfFrame();
        CtrHuesped ctr = new CtrHuesped();
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
            ctr.actualizarHPD(hpd2);
            genery.ajustarNewPanel(pnl, listarHuesped());
            dg.dispose();
        });
        btnCancelar.addActionListener((ActionEvent e) -> {
            dg.dispose();
        });
        dg.setVisible(true);
    }

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

    public void loadPnlIndex(JPanel pnl) {
        pnl.setVisible(false);
        pnl.removeAll();
        pnl.setVisible(true);
    }
}
