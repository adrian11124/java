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

    public void registrarHuesped(Inventario inv, JFrame frame, JPanel pnl2) {
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
        JButton btnRegistrar = new JButton("REGISTRAR");
        btnCancelar.setFocusable(false);
        btnRegistrar.setFocusable(false);

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
        pnl.add(btnRegistrar, gbc);

        btnRegistrar.addActionListener((ActionEvent e) -> {
            // lblMessage.setText(CtrHuesped.registrarHuesped(txtNombre.getText(), txtDocumento.getText()));
            if (lblMessage.getText().equals("")) {
                loadPnlIndex(pnl2);
                pnl2.add(listarHuesped(inv, frame));
                dg.dispose();
            }
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
