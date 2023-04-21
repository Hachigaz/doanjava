package Panel.SubPanel;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

public class TablePanel extends JPanel{
    private JScrollPane bangDanhSach;
    private TableRowSorter<TableModel> rowSorter;
    private ArrayList<ArrayList<String>> DSDKLoc = new ArrayList<ArrayList<String>>();

    public TablePanel(){
        super();
        this.setLayout(new BorderLayout());
    }
    public void SetTable(TableModel tableModel,ListSelectionListener listener){
        if(this.bangDanhSach!=null){
            this.remove(bangDanhSach);
            DSDKLoc.clear();
        }

        JTable tableDS = new JTable(tableModel);
        tableDS.getTableHeader().setReorderingAllowed(false);
        tableDS.setRowHeight(30);

        //cho phep thay doi kich thuoc cac cot
        tableDS.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        //chi duoc chon mot dong trong bang
        tableDS.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //them listener
        if(listener!=null){
            tableDS.getSelectionModel().addListSelectionListener(listener);
        }

        for(int i = 0 ; i < tableModel.getColumnCount(); i++){
            DSDKLoc.add(new ArrayList<String>());
        }

        this.rowSorter = new TableRowSorter<>(tableModel);
        tableDS.setRowSorter(rowSorter);

        this.bangDanhSach = new JScrollPane(tableDS);
        this.add(bangDanhSach,BorderLayout.CENTER);

        // Revalidate and repaint the frame
        this.revalidate();
        this.repaint();
    }
    private JTable getTable(){
        return (JTable)this.bangDanhSach.getViewport().getView();
    }
    public int getSelectedRow(){
        return getTable().getSelectedRow();
    }
    
    //xu ly loc bang
    public void themDieuKienLoc(int columnIndex,String value){
        DSDKLoc.get(columnIndex).add(value);
    }
    public void xoaDieuKienLoc(int columnIndex,String value){
        DSDKLoc.get(columnIndex).remove(value);
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
}
