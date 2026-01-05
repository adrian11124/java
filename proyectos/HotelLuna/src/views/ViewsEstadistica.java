package views;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

/**
 *
 * @author adrian11124
 */
public class ViewsEstadistica {

    public JPanel estadistica(){
        JPanel pnl = new JPanel();
        
        pnl.setBackground(Color.WHITE);
        pnl.setLayout(new BorderLayout());
        
        return pnl;
    }
}
