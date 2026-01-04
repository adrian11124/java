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

import Models.Huesped;
import controllers.CtrHuesped;


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
        JTable tbl_table = new JTable();
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
        tbl_table.setFocusable(false);
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
        tbl_table.setModel(model);
        scroll.setViewportView(tbl_table);

        btn_registrar.addActionListener((e) -> {
            registrarHuesped(pnl);
        });
        btn_actualizar.addActionListener((e) -> {
            int row = tbl_table.getSelectedRow();
            actualizarHuesped(pnl, tbl_table.getValueAt(row, 1) + "");
        });
        btn_eliminar.addActionListener((e) -> {
            int row = tbl_table.getSelectedRow();
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar Huesped?", "CONFIRMACION", JOptionPane.YES_NO_OPTION);
            boolean op = (opcion == JOptionPane.YES_OPTION);
            if(op){
                ctr.removeHPD(tbl_table.getValueAt(row, 1).toString());
                genery.ajustarNewPanel(pnl, listarHuesped());
            }
            
        });
        btn_buscar.addActionListener((e) -> {
            model.setRowCount(0);
            for (String[] obj : ctr.buscarHPD("numDocumento", txt_buscar.getText())) {
                model.addRow(obj);
            }
            btn_actualizar.setEnabled(false);
            btn_eliminar.setEnabled(false);
        });
        tbl_table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tbl_table.getSelectedRow() != -1) {
                btn_actualizar.setEnabled(true);
                btn_eliminar.setEnabled(true);
            }
        });

        pnl_buscar.add(txt_buscar);
        pnl_buscar.add(btn_buscar);
        pnl_south.add(btn_registrar);
        pnl_south.add(btn_actualizar);
        pnl_south.add(btn_eliminar);
        pnl.add(pnl_buscar, BorderLayout.NORTH);
        pnl.add(scroll, BorderLayout.CENTER);
        pnl.add(pnl_south, BorderLayout.SOUTH);

        return pnl;
    }

    public void registrarHuesped(JPanel pnl_origin) {
        JFrame frame = Index.jfFrame();
        JDialog dg = new JDialog(frame, true);
        JPanel pnl = new JPanel();
        JLabel lbl_message = new JLabel();
        JLabel lbl_title = new JLabel();
        JTextField txt_documento = new JTextField(20);
        JTextField txt_nombre = new JTextField(20);
        JButton btn_registrar = new JButton();
        JButton btn_cancelar = new JButton();
        GridBagConstraints gbc = new GridBagConstraints();
        CtrHuesped ctr = new CtrHuesped();
        
        dg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dg.setSize(450, 600);
        pnl.setLayout(new GridBagLayout());
        pnl.setBackground(Color.WHITE);
        txt_documento.setColumns(20);
        txt_nombre.setColumns(20);
        btn_registrar.setText("REGISTRAR");
        btn_registrar.setFocusable(false);
        btn_cancelar.setText("CANCELAR");
        btn_cancelar.setFocusable(false);
        lbl_title.setText("FORMULARIO REGISTRO DE HUESPED ");
        
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
        pnl.add(new JLabel("# DOCUMENTO: "), gbc);
        gbc.gridx = 1;
        pnl.add(txt_documento, gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        pnl.add(new JLabel("NOMBRE: "), gbc);
        gbc.gridx = 1;
        pnl.add(txt_nombre, gbc);
        gbc.gridy = 4;
        gbc.gridx = 0;
        pnl.add(btn_cancelar, gbc);
        gbc.gridx = 1;
        pnl.add(btn_registrar, gbc);
        
        btn_registrar.addActionListener((ActionEvent e) -> {
            lbl_message.setText(ctr.registrarHuesped(txt_nombre.getText(), txt_documento.getText()));
            if (lbl_message.getText().equals("")) {
                genery.ajustarNewPanel(pnl_origin, listarHuesped());
                dg.dispose();
            }
        });
        btn_cancelar.addActionListener((ActionEvent e) -> {
            dg.dispose();
        });
        txt_documento.addKeyListener(txtKeyListenerNumber());
        txt_nombre.addKeyListener(txtKeyListenerChar());

        dg.add(pnl);
        dg.setVisible(true);
    }

    public void actualizarHuesped(JPanel pnl_origin, String document) {
        JFrame frame = Index.jfFrame();
        JDialog dg = new JDialog(frame, true);
        JPanel pnl = new JPanel();
        JTextField txt_nombre = new JTextField();
        JTextField txt_documento = new JTextField();
        JButton btn_cancelar = new JButton();
        JButton btn_actualizar = new JButton();
        JLabel lbl_title = new JLabel();
        JLabel lbl_message = new JLabel();
        CtrHuesped ctr = new CtrHuesped();
        Huesped hpd = ctr.searhHuespedByDocument(document);
        GridBagConstraints gbc = new GridBagConstraints();

        dg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dg.setSize(450, 600);
        pnl.setLayout(new GridBagLayout());
        pnl.setBackground(Color.WHITE);
        txt_nombre.setColumns(20);
        txt_documento.setColumns(20);
        txt_documento.setText(hpd.getNumDocumento() + "");
        txt_nombre.setText(hpd.getNombre());
        btn_cancelar.setText("CANCELAR");
        btn_cancelar.setFocusable(false);
        btn_actualizar.setText("ACTUALIZAR");
        btn_actualizar.setFocusable(false);
        lbl_title.setText("FORMULARIO REGISTRO DE HUESPED ");
        
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
        pnl.add(new JLabel("# DOCUMENTO: "), gbc);
        gbc.gridx = 1;
        pnl.add(txt_documento, gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        pnl.add(new JLabel("NOMBRE: "), gbc);
        gbc.gridx = 1;
        pnl.add(txt_nombre, gbc);
        gbc.gridy = 4;
        gbc.gridx = 0;
        pnl.add(btn_cancelar, gbc);
        gbc.gridx = 1;
        pnl.add(btn_actualizar, gbc);

        btn_actualizar.addActionListener((ActionEvent e) -> {
            hpd.setNumDocumento(Integer.valueOf(txt_documento.getText()));
            hpd.setNombre(txt_nombre.getText());
            ctr.actualizarHPD(hpd);
            genery.ajustarNewPanel(pnl_origin, listarHuesped());
            dg.dispose();
        });
        btn_cancelar.addActionListener((ActionEvent e) -> {
            dg.dispose();
        });
        txt_documento.addKeyListener(txtKeyListenerNumber());
        txt_nombre.addKeyListener(txtKeyListenerChar());

        dg.add(pnl);
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
}
