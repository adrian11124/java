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

    

    

    

    

    

    

    private void reservarHabitacion(Inventario inv, JFrame frame) {
        // JReservar r = new JReservar();
        // r.dialog1(inv, frame);
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
