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
import javax.swing.table.TableModel;

import Panel.SubPanel.LocPanel;
import Panel.SubPanel.TablePanel;
import misc.DataSet;
import misc.util;
public class TraCuuHangUI extends JPanel{

    private JPanel panelChucNang;
    private JPanel panelLoc;
    private TablePanel panelDanhSach;



    private JTextField searchBar;



    public TraCuuHangUI(Dimension d){

        this.panelChucNang = new JPanel();
        this.panelLoc = new JPanel();
        this.panelDanhSach = new TablePanel();

        this.setLayout(new BorderLayout());

        this.setPreferredSize(d);

        panelChucNang.setPreferredSize(new Dimension(this.getPreferredSize().width,60));
        panelLoc.setPreferredSize(new Dimension(240,this.getPreferredSize().height-60));
        panelDanhSach.setPreferredSize(new Dimension(this.getPreferredSize().width-240,this.getPreferredSize().height-60));

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
            this.panelDanhSach.themDieuKienLoc(panel.getColumnIndex(),key);
            this.panelDanhSach.locCacDieuKien();
        }
        else{
            String key = cb.getName();
            this.panelDanhSach.xoaDieuKienLoc(panel.getColumnIndex(),key);
            this.panelDanhSach.locCacDieuKien();
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
            searchedText="";
            panelDanhSach.themDieuKienLoc(1, searchText);
            this.panelDanhSach.locCacDieuKien();
        } else {
            try {
                this.panelDanhSach.xoaDieuKienLoc(1,searchedText);
                this.panelDanhSach.themDieuKienLoc(1,searchText);//1 là cột tên sản phẩm
                searchedText=searchText;
                this.panelDanhSach.locCacDieuKien();
            } catch (PatternSyntaxException ex) {
                System.err.println("Invalid regex pattern: " + ex.getMessage());
            }
        }
    }
    public void UpdateTable(TableModel table){
        this.panelDanhSach.SetTable(table);
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





}