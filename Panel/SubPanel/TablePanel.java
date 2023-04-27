package Panel.SubPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

public class TablePanel extends JPanel{
    private JScrollPane bangDanhSach;
    private TableRowSorter<TableModel> rowSorter;
    private ArrayList<ArrayList<String>> DSDKLoc = new ArrayList<ArrayList<String>>();
    private ListSelectionListener currentListener;
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
        tableDS.setRowHeight(40);
        tableDS.setFont(new Font("Poppins",Font.PLAIN,13));

        JTableHeader header = tableDS.getTableHeader();
        header.setPreferredSize(new Dimension(40, 35));

        TableColumnModel columnModel = tableDS.getColumnModel();
        for(int i=0;i<tableDS.getColumnCount();i++){
            columnModel.getColumn(i).setResizable(false);
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i=0;i<tableDS.getColumnCount();i++){
            tableDS.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        //cho phep thay doi kich thuoc cac cot
        // tableDS.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        //chi duoc chon mot dong trong bang
        tableDS.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //xoa listener cu
        tableDS.getSelectionModel().removeListSelectionListener(currentListener);
        currentListener=null;
        //them listener
        if(listener!=null){
            tableDS.getSelectionModel().addListSelectionListener(listener);
            currentListener = listener;
        }

        for(int i = 0 ; i < tableModel.getColumnCount(); i++){
            DSDKLoc.add(new ArrayList<String>());
        }

        this.rowSorter = new TableRowSorter<>(tableModel);
        tableDS.setRowSorter(rowSorter);

        this.bangDanhSach = new JScrollPane(tableDS);
        bangDanhSach.getVerticalScrollBar().setPreferredSize(new Dimension(13, 0));
        this.bangDanhSach.setPreferredSize(this.getPreferredSize());
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
    public JScrollPane getBangDanhSach(){
        return bangDanhSach;
    }
    public JTable getTableDS() {
        return (JTable) bangDanhSach.getViewport().getView();
    }
}
