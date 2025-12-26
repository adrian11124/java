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

/**
 *
 * @author adrian11124
 */
public class Index {
    /**
     * genera interfaz visual principal
     * type: JFrame
     */
    public Index() {

        JFrame frame = new JFrame();
        JPanel pnlIndex = new JPanel();
        JPanel pnlIndexLogo = new JPanel();
        JPanel pnlIndexImg = new JPanel();
        JMenu menuPrincipal = new JMenu();
        JMenu menuHabitacion = new JMenu();
        JMenuBar menuBar = new JMenuBar();
        JMenuItem itemInicio = new JMenuItem();
        JMenuItem itemListaHabitacion = new JMenuItem();
        JMenuItem itemListaHabitacionVIP = new JMenuItem();
        JMenuItem itemReservarHabitacion = new JMenuItem();
        JLabel lbl_logo = new JLabel();
        JLabel lbl_norte = new JLabel();
        JLabel lbl_sur = new JLabel();
        GridBagConstraints gbc = new GridBagConstraints();
        
        frame.setLayout(new GridLayout(1, 1));
        frame.setSize(550, 700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(Color.WHITE);
        //javax.swing.ImageIcon(getClass().getResource("/img/luna1.png"))
        frame.setIconImage( Toolkit.getDefaultToolkit().getImage("../img/luna1sinFondo.png"));
        //frame.setUndecorated(true);

        menuPrincipal.setText("FUNCIONES");
        itemInicio.setText("INICIO");
        menuHabitacion.setText("HABITACIONES");
        itemListaHabitacion.setText("LISTAR HABITACIONES");
        itemListaHabitacionVIP.setText("LISTAR HABITACIONES VIP");
        itemReservarHabitacion.setText("RESERVAR HABITACION");  
        pnlIndex.setLayout(new GridLayout(1, 1));
        pnlIndexLogo.setLayout(new GridBagLayout());
        lbl_norte.setText("WELCOME HOTEL");
        lbl_norte.setFont(new FontUIResource("Times New Roman", FontUIResource.BOLD, 40));
        lbl_sur.setText("LUNA");
        lbl_sur.setFont(new FontUIResource("Times New Roman", FontUIResource.BOLD, 40));
        lbl_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/luna1.png")));
        pnlIndexImg.setBackground(Color.WHITE);
        

            
        //add Components
        pnlIndexImg.add(lbl_logo);    
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
            
            // new javax.swing.ImageIcon(getClass().getResource("/img/luna1sinFondo.png"))
            
            
            
            pnlIndexLogo.setBackground(Color.WHITE);
            
            
            pnlIndexLogo.add(lbl_norte, gbc);
            gbc.gridy = 1;
            gbc.gridx = 0;
            pnlIndexLogo.add(pnlIndexImg, gbc);
            gbc.gridy = 2;
            gbc.gridx = 0;
            pnlIndexLogo.add(lbl_sur, gbc);
            
            
            
            
            menuHabitacion.add(itemListaHabitacion);
            menuHabitacion.add(itemListaHabitacionVIP);
            menuHabitacion.add(itemReservarHabitacion);

            JMenu huesped = new JMenu("HUESPED");
            JMenuItem itemlistarHuesped = new JMenuItem("HUEPEDES REGISTRADOS");
            huesped.add(itemlistarHuesped);
            JMenu reserva = new JMenu("RESERVA");
            JMenuItem itemlistarReserva = new JMenuItem("LISTAR RESERVAS");
            reserva.add(itemlistarReserva);
            JMenuItem miSalir = new JMenuItem("SALIR");
            JMenuItem itemEstadistica = new JMenuItem("ESTADISTICA");

            pnlIndex.add(pnlIndexLogo);

            itemInicio.addActionListener((ActionEvent e) -> {
                loadPnlIndex(pnlIndex);
                pnlIndex.add(pnlIndexLogo);
            });
            itemListaHabitacion.addActionListener((ActionEvent e) -> {
                loadPnlIndex(pnlIndex);
                // pnlIndex.add(listarHabitaciones(inventario, frame));
            });
            itemListaHabitacionVIP.addActionListener((ActionEvent e) -> {
                loadPnlIndex(pnlIndex);
                // pnlIndex.add(listarHabitacionesVIP(inventario, frame));
            });
            itemReservarHabitacion.addActionListener((ActionEvent e) -> {
                // reservarHabitacion(inventario, frame);
            });
            itemlistarHuesped.addActionListener((e) -> {
                loadPnlIndex(pnlIndex);
                // pnlIndex.add(listarHuesped(inventario, frame));
            });
            itemlistarReserva.addActionListener((e) -> {
                loadPnlIndex(pnlIndex);
                // pnlIndex.add(listarReserva(inventario, frame));
            });
            itemEstadistica.addActionListener((e) -> {
                loadPnlIndex(pnlIndex);
                // pnlIndex.add(estadistica(inventario));
            });
            miSalir.addActionListener((e) -> {
                // inventario.guardarProceso();
                frame.dispose();
            });
            miSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));

            menuPrincipal.add(itemInicio);
            menuPrincipal.add(menuHabitacion);
            menuPrincipal.add(reserva);
            menuPrincipal.add(huesped);
    //        menuPrincipal.add(itemEstadistica);
            menuPrincipal.add(miSalir);
            menuBar.add(menuPrincipal);
            frame.add(pnlIndex);
            frame.setJMenuBar(menuBar);

            frame.setVisible(true);
    }
    public void loadPnlIndex(JPanel pnl) {
        pnl.setVisible(false);
        pnl.removeAll();
        pnl.setVisible(true);
    }
}
