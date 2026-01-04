package views;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author adrian11124
 */
public class GeneryViews {

    /**
     * status: active
     * descripcion: Genera un modelo de tabla
     */
    public DefaultTableModel tableModel(List<String[]> data, String[] column) {
        
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("#");
        for (String col : column) {
            model.addColumn(col);
        }
        for (String[] obj : data) {
            model.addRow(obj);
        }
  
        return model;
    }

    /**
     * status: active
     *descripcion: permite visulizar un nuevo panel
     */
    public void ajustarNewPanel(JPanel PnlContaint, JPanel panel){
        PnlContaint.removeAll();
        PnlContaint.setLayout(new GridLayout(1, 1));
        PnlContaint.add(panel);
        PnlContaint.revalidate();
        PnlContaint.repaint();
    }


    /**
     * status: inactive
     * descripcion: permite visulizar un nuevo panel
     */
    public void loadPnlIndex(JPanel pnl) {
        pnl.setVisible(false);
        pnl.removeAll();
        pnl.setVisible(true);
    }
}
