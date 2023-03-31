package Panel;



import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import SQL.DataSet;

public class DanhMucSP extends JPanel{
    JTable table;
    JScrollPane scrollPane;
    public DanhMucSP(DataSet ds){
        table = new JTable(ds.getData(),ds.getColumnName());
        scrollPane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(800,500));
        scrollPane.setPreferredSize(new Dimension(800,500));
        this.add(scrollPane);
    }
}
