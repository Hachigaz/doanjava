package Panel.TraCuuHang;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.PatternSyntaxException;
import javax.swing.*;
import javax.swing.table.*;

import misc.DataSet;
import misc.util;
public class TraCuuHangUI extends JPanel{

    private JPanel panelChucNang;
    private JPanel panelLoc;
    private JPanel panelDanhSach;

    private TableRowSorter<TableModel> rowSorter;

    private JScrollPane bangDanhSach;

    private JTextField searchBar;



    public TraCuuHangUI(){

        this.panelChucNang = new JPanel();
        this.panelLoc = new JPanel();
        this.panelDanhSach = new JPanel();

        this.setLayout(new BorderLayout());

        this.setPreferredSize(new Dimension(600,500));

        panelChucNang.setPreferredSize(new Dimension(this.getSize().width,60));
        panelLoc.setPreferredSize(new Dimension(240,this.getSize().height-60));
        panelDanhSach.setPreferredSize(new Dimension(this.getSize().width-240,this.getSize().height-60));

        this.add(panelChucNang,BorderLayout.NORTH);
        this.add(panelLoc,BorderLayout.WEST);
        this.add(panelDanhSach,BorderLayout.CENTER);


        panelChucNang.setBackground(Color.DARK_GRAY);
        panelChucNang.setOpaque(true);
        panelLoc.setBackground(Color.LIGHT_GRAY);
        panelLoc.setOpaque(true);
        panelDanhSach.setBackground(Color.GREEN);
        panelDanhSach.setOpaque(true);
    }

    //lọc theo loại sp và khu vực
    private ArrayList<JLabel> locArrLabel = new ArrayList<JLabel>();
    private HashMap<JLabel,JScrollPane> arrLocPanel = new HashMap<JLabel,JScrollPane>();

    public void SetupPanelLoc(String[] panelTitles,int[] columnIndexes,ArrayList<ArrayList<String>> tenLoc,MouseListener panelCollapseListener,ItemListener locCheckboxAction){
        for(int i = 0 ; i < tenLoc.size();i++){
            themChucNangLoc(panelTitles[i],columnIndexes[i],tenLoc.get(i),panelCollapseListener,locCheckboxAction);
        }
        panelLoc.revalidate();
        panelLoc.repaint();
    }

    public void findClickedLocPanel(Object component){
        for(JLabel label : locArrLabel){
            if(component==label){
                JScrollPane panel = arrLocPanel.get(label);
                panel.setVisible(!panel.isVisible());
                panelLoc.revalidate();
                panelLoc.repaint();
            }
        }
    }

    public void sortSelectedCheckbox(Object checkbox){
        JCheckBox cb = (JCheckBox)checkbox;
        LocPanel panel = (LocPanel)cb.getParent();
        
        if(cb.isSelected()){
            String key = cb.getName();
            DSDKLoc.get(panel.getColumnIndex()).add(key);
            this.locCacDieuKien();
        }
        else{
            String key = cb.getName();
            DSDKLoc.get(panel.getColumnIndex()).remove(key);
            this.locCacDieuKien();
        }
    }

    private void themChucNangLoc(String title,int columnIndex,ArrayList<String> locLabelName,MouseListener panelCollapseListener,ItemListener locCheckboxAction){


        JLabel label = new JLabel(title);
        panelLoc.add(label);
        label.addMouseListener(panelCollapseListener);
        locArrLabel.add(label);

        LocPanel panel = new LocPanel(locLabelName,columnIndex);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(panelLoc.getPreferredSize().width,250));
        panelLoc.add(scrollPane);
        arrLocPanel.put(label,scrollPane);

        panel.setActionForCheckBoxes(locCheckboxAction);
    }

    private String[] optionName;
    private String[] optionKey;
    private JComboBox<String> cbChonKho;

    public void SetupPanelChucNang(DataSet dsKho,ActionListener onChangeMaKho, ActionListener onSubmitSearch){
        JLabel labelChonKho = new JLabel("Chọn kho");

        optionName = util.objToString(dsKho.getColumn(1));
        optionKey = util.objToString(dsKho.getColumn(0));

        cbChonKho = new JComboBox<String>(optionName);

        panelChucNang.add(labelChonKho);
        panelChucNang.add(cbChonKho);

        cbChonKho.addActionListener(onChangeMaKho);

        searchBar = new JTextField(20);
        searchBar.addActionListener(onSubmitSearch);

        panelChucNang.add(searchBar);
    }

    //lấy mã kho đang chọn trong combobox
    public String getSelectedMaKhoKey(){
        String selected = cbChonKho.getSelectedItem().toString();
        for(int i = 0; i < optionKey.length;i++){
            if(selected.equals(optionName[i])){
                return optionKey[i];
            }
        }
        return null;
    }
    //tìm kiếm theo giá trị nhập
    String searchedText="";
    public void timTheoGiaTri(){
        String searchText = searchBar.getText();
        if (searchText.length() == 0) {
            DSDKLoc.get(1).remove(searchedText);
            searchedText="";
            this.locCacDieuKien();
        } else {
            try {
                DSDKLoc.get(1).remove(searchedText);
                DSDKLoc.get(1).add(searchText);//1 là cột tên sản phẩm
                searchedText=searchText;
                this.locCacDieuKien();
            } catch (PatternSyntaxException ex) {
                System.err.println("Invalid regex pattern: " + ex.getMessage());
            }
        }
    }


    // private String getMaKhoHienTai(){
    //     String maKho = null;
    //     String selected = cbChonKho.getSelectedItem().toString();
    //     for(int i = 0; i < optionKey.length;i++){
    //         if(selected.equals(optionName[i])){
    //             maKho = optionKey[i];
    //         }
    //     }
    //     return maKho;
    // }

    //tạo bảng và khởi tạo lại mảng chứa các đối tượng lọc
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

        this.add(bangDanhSach);

        // Revalidate and repaint the frame
        this.revalidate();
        this.repaint();
    }

    private ArrayList<ArrayList<String>> DSDKLoc = new ArrayList<ArrayList<String>>();

    private void resetDieuKieuLoc(){
        for(ArrayList<String> arr : DSDKLoc){
            arr.clear();
        }
        DSDKLoc.clear();
        rowSorter.setRowFilter(null);
    }

    private void locCacDieuKien(){
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
}