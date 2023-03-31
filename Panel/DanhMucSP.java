package Panel;

import javax.swing.JPanel;
import javax.swing.JTable;

public class DanhMucSP extends JPanel{
    JTable table;
    public DanhMucSP(){
        table = new JTable(5,5);
        this.add(table);
    }
}
