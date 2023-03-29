package Function;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import SQL.DataSet;

public class TaoDonNhap extends JPanel {
    JTable donnhap;
    JLabel lChuaDN;
    public TaoDonNhap(DataSet ds)
    {
        donnhap = new JTable(ds.getData(),ds.getColumnName());
        this.add(donnhap);
    }
}
