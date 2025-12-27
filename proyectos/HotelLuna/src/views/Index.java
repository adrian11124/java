package views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.plaf.FontUIResource;

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
        JMenu huesped = new JMenu();
        JMenu reserva = new JMenu("RESERVA");
        JMenuBar menuBar = new JMenuBar();
        JMenuItem itemInicio = new JMenuItem();
        JMenuItem itemListaHabitacion = new JMenuItem();
        JMenuItem itemListaHabitacionVIP = new JMenuItem();
        JMenuItem itemReservarHabitacion = new JMenuItem();
        JMenuItem itemlistarHuesped = new JMenuItem();
        JMenuItem item_listarReserva = new JMenuItem();
        JMenuItem item_salir = new JMenuItem();
        JMenuItem itemEstadistica = new JMenuItem();
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
        frame.setJMenuBar(menuBar);
        frame.setIconImage( Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/luna1.png")));
        frame.setUndecorated(true);

        menuPrincipal.setText("FUNCIONES");
        huesped.setText("HUESPED");
        itemInicio.setText("INICIO");
        menuHabitacion.setText("HABITACIONES");
        itemListaHabitacion.setText("LISTAR HABITACIONES");
        itemListaHabitacionVIP.setText("LISTAR HABITACIONES VIP");
        itemReservarHabitacion.setText("RESERVAR HABITACION");
        itemEstadistica.setText("ESTADISTICA");
        item_salir.setText("SALIR");
        pnlIndex.setLayout(new GridLayout(1, 1));
        pnlIndexLogo.setLayout(new GridBagLayout());
        lbl_norte.setText("WELCOME HOTEL");
        lbl_norte.setFont(new FontUIResource("Times New Roman", FontUIResource.BOLD, 40));
        lbl_sur.setText("LUNA");
        lbl_sur.setFont(new FontUIResource("Times New Roman", FontUIResource.BOLD, 40));
        lbl_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/luna1.png")));
        pnlIndexImg.setBackground(Color.WHITE);
        pnlIndexLogo.setBackground(Color.WHITE);
        pnlIndexImg.add(lbl_logo);
        itemlistarHuesped.setText("HUEPEDES REGISTRADOS");
        reserva.setText("RESERVA");
        item_listarReserva.setText("LISTAR RESERVAS");
            
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        pnlIndexLogo.add(lbl_norte, gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        pnlIndexLogo.add(pnlIndexImg, gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        pnlIndexLogo.add(lbl_sur, gbc);

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
        item_listarReserva.addActionListener((e) -> {
            loadPnlIndex(pnlIndex);
            // pnlIndex.add(listarReserva(inventario, frame));
        });
        itemEstadistica.addActionListener((e) -> {
            loadPnlIndex(pnlIndex);
            // pnlIndex.add(estadistica(inventario));
        });
        item_salir.addActionListener((e) -> {
            // inventario.guardarProceso();
            frame.dispose();
        });
        item_salir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        
        huesped.add(itemlistarHuesped);
        reserva.add(item_listarReserva);
        menuHabitacion.add(itemListaHabitacion);
        menuHabitacion.add(itemListaHabitacionVIP);
        menuHabitacion.add(itemReservarHabitacion);
        menuPrincipal.add(itemInicio);
        menuPrincipal.add(menuHabitacion);
        menuPrincipal.add(reserva);
        menuPrincipal.add(huesped);
        menuPrincipal.add(itemEstadistica);
        menuPrincipal.add(item_salir);
        menuBar.add(menuPrincipal);
        pnlIndex.add(pnlIndexLogo);
        frame.add(pnlIndex);
        
        frame.setVisible(true);
    }
    public void loadPnlIndex(JPanel pnl) {
        pnl.setVisible(false);
        pnl.removeAll();
        pnl.setVisible(true);
    }
}
