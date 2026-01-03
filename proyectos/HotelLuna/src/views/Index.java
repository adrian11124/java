package views;

import java.awt.BorderLayout;
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
import bd.conexion;

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

        JFrame frame = jfFrame();
        JPanel pnl_index = new JPanel();
        JPanel pnl_indexLogo = new JPanel();
        JPanel pnl_indexImg = new JPanel();
        JMenu menu_principal = new JMenu();
        JMenu menu_habitacion = new JMenu();
        JMenu menu_huesped = new JMenu();
        JMenu menu_reserva = new JMenu("RESERVA");
        JMenuBar menuBar = new JMenuBar();
        JMenuItem item_inicio = new JMenuItem();
        JMenuItem item_listaHabitacion = new JMenuItem();
        JMenuItem item_listaHabitacionVIP = new JMenuItem();
        JMenuItem item_reservarHabitacion = new JMenuItem();
        JMenuItem item_listarHuesped = new JMenuItem();
        JMenuItem item_listarReserva = new JMenuItem();
        JMenuItem item_salir = new JMenuItem();
        JMenuItem item_estadistica = new JMenuItem();
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
        frame.setLocationRelativeTo(frame);
        frame.setJMenuBar(menuBar);
        frame.setIconImage( Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/luna1.png")));
        frame.setUndecorated(true);

        menu_principal.setText("FUNCIONES");
        menu_huesped.setText("HUESPED");
        item_inicio.setText("INICIO");
        menu_habitacion.setText("HABITACIONES");
        item_listaHabitacion.setText("LISTAR HABITACIONES");
        item_listaHabitacionVIP.setText("LISTAR HABITACIONES VIP");
        item_reservarHabitacion.setText("RESERVAR HABITACION");
        item_estadistica.setText("ESTADISTICA");
        item_salir.setText("SALIR");
        item_salir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        pnl_index.setLayout(new GridLayout(1, 1));
        pnl_indexLogo.setLayout(new GridBagLayout());
        lbl_norte.setText("WELCOME HOTEL");
        lbl_norte.setFont(new FontUIResource("Times New Roman", FontUIResource.BOLD, 40));
        lbl_sur.setText("LUNA");
        lbl_sur.setFont(new FontUIResource("Times New Roman", FontUIResource.BOLD, 40));
        lbl_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/luna1.png")));
        pnl_indexImg.setBackground(Color.WHITE);
        pnl_indexLogo.setBackground(Color.WHITE);
        pnl_indexImg.add(lbl_logo);
        item_listarHuesped.setText("HUEPEDES REGISTRADOS");
        menu_reserva.setText("RESERVA");
        item_listarReserva.setText("LISTAR RESERVAS");

        GeneryViews genery = new GeneryViews();
        item_inicio.addActionListener((ActionEvent e) -> {
            genery.ajustarNewPanel(pnl_index, pnl_indexLogo);
        });
        item_listaHabitacion.addActionListener((ActionEvent e) -> {
            ViewsHabitacion views = new ViewsHabitacion();
            JPanel pnl_new = views.listarHabitaciones();
            genery.ajustarNewPanel(pnl_index, pnl_new);
        });
        item_listaHabitacionVIP.addActionListener((ActionEvent e) -> {
            ViewsHabitacion views = new ViewsHabitacion();
            JPanel pnl_new = views.listarHabitacionesVIP();
            genery.ajustarNewPanel(pnl_index, pnl_new);
        });
        item_reservarHabitacion.addActionListener((ActionEvent e) -> {
            ViewsReserva views = new ViewsReserva();
            views.reservarHabitacion(frame);
        });
        item_listarReserva.addActionListener((e) -> {
            ViewsReserva views = new ViewsReserva();
            JPanel pnl_new = views.listarReserva();
            genery.ajustarNewPanel(pnl_index, pnl_new);
        });
        item_listarHuesped.addActionListener((e) -> {
            ViewsHuesped views = new ViewsHuesped();
            JPanel pnl_new = views.listarHuesped();
            genery.ajustarNewPanel(pnl_index, pnl_new);
            // pnl_index.add(listarHuesped(inventario, frame));
        });
        
        item_estadistica.addActionListener((e) -> {
            // pnl_index.add(estadistica(inventario));
        });
        item_salir.addActionListener((e) -> {
            //conexion.guardarProceso();
            frame.dispose();
        });
        
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        pnl_indexLogo.add(lbl_norte, gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        pnl_indexLogo.add(pnl_indexImg, gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        pnl_indexLogo.add(lbl_sur, gbc);
        
        menu_huesped.add(item_listarHuesped);
        menu_reserva.add(item_listarReserva);
        menu_habitacion.add(item_listaHabitacion);
        menu_habitacion.add(item_listaHabitacionVIP);
        menu_habitacion.add(item_reservarHabitacion);
        menu_principal.add(item_inicio);
        menu_principal.add(menu_habitacion);
        menu_principal.add(menu_reserva);
        menu_principal.add(menu_huesped);
        menu_principal.add(item_estadistica);
        menu_principal.add(item_salir);
        menuBar.add(menu_principal);
        pnl_index.add(pnl_indexLogo);
        frame.add(pnl_index);
        
        frame.setVisible(true);
    }

    public static JFrame jfFrame() {
        JFrame frame = new JFrame();
        return frame;
    }

    
}
