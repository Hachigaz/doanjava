package Nhan_vien;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import SQL.DataSet;

public class NhanVien extends JPanel{
    JTable table;
    JScrollPane scrollPane;
    public NhanVien(DataSet ds){
        table = new JTable(ds.getData(),ds.getColumnName());
        scrollPane = new JScrollPane(table);
        TableColumnModel columnModel = table.getColumnModel();
        for(int i=0;i<columnModel.getColumnCount();i++){
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(50);
        }
        this.add(table);
    }
}
