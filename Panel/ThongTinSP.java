package Panel;



import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import SQL.DataSet;

public class ThongTinSP extends JPanel{
    JTable table;
    JScrollPane scrollPane;
    public ThongTinSP(DataSet ds){
        table = new JTable(ds.getData(),ds.getColumnName());
        scrollPane = new JScrollPane(table);
        // table.setPreferredScrollableViewportSize(new Dimension(300,150));
        scrollPane.setPreferredSize(new Dimension(800,500));
        this.add(scrollPane);
    }
}
