package Panel.SubPanel;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

public class TablePanel extends JPanel{
    private JScrollPane bangDanhSach;
    private TableRowSorter<TableModel> rowSorter;

    private ArrayList<ArrayList<String>> DSDKLoc = new ArrayList<ArrayList<String>>();

    public void SetTable(TableModel tableModel){
        if(this.bangDanhSach!=null){
            this.remove(bangDanhSach);
        }

        JTable tableDS = new JTable(tableModel);
        tableDS.getTableHeader().setReorderingAllowed(false);
        tableDS.setRowHeight(30);

        DSDKLoc.clear();
        for(int i = 0 ; i < tableModel.getColumnCount(); i++){
            DSDKLoc.add(new ArrayList<String>());
        }

        this.rowSorter = new TableRowSorter<>(tableModel);
        tableDS.setRowSorter(rowSorter);

        this.bangDanhSach = new JScrollPane(tableDS);
        this.bangDanhSach.setPreferredSize(this.getPreferredSize());
        this.add(bangDanhSach);

        // Revalidate and repaint the frame
        this.revalidate();
        this.repaint();
    }

    public void locCacDieuKien(){
        RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
            public boolean include(Entry<?, ?> entry) {
                boolean include = true;
                int columnCount = entry.getValueCount();
                //duyệt từng cột
                for(int i = 0 ; i < columnCount && include; i++){
                    //duyệt từng chuỗi trong mảng
                    boolean haveString = false;
                    for(int j  = 0; j <DSDKLoc.get(i).size()&&!haveString;j++){
                        String entryString = entry.getValue(i).toString();
                        if(entryString.indexOf(DSDKLoc.get(i).get(j))>=0){
                            haveString = haveString || true;
                        }
                    }
                    if(DSDKLoc.get(i).size()!=0){
                        include = include && haveString;
                    }
                }
                return include;
            }
        };
        rowSorter.setRowFilter(filter);
    }

    // private void resetDieuKieuLoc(){
    //     for(ArrayList<String> arr : DSDKLoc){
    //         arr.clear();
    //     }
    //     DSDKLoc.clear();
    //     rowSorter.setRowFilter(null);
    // }

    public void themDieuKienLoc(int columnIndex,String value){
        DSDKLoc.get(columnIndex).add(value);
    }
    public void xoaDieuKienLoc(int columnIndex,String value){
        DSDKLoc.get(columnIndex).remove(value);
    }
}
