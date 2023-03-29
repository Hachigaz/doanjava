package Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class DanhMucSP extends JPanel{
    JTable table;
    public DanhMucSP(){
        table = new JTable(5,5);
        this.add(table);
    }
}
