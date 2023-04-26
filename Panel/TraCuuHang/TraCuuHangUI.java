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


        panelChucNang.setBackground(new Color(27,101,147));
        panelChucNang.setOpaque(true);
        panelChucNang.setLayout(new BorderLayout());
        panelLoc.setBackground(Color.LIGHT_GRAY);
        panelLoc.setOpaque(true);
        panelDanhSach.setBackground(new Color(255, 182, 87,255));
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

    public void SetupPanelChucNang(String[] dsTenKho,String[]dsMaKho,ActionListener onChangeMaKho, ActionListener onSubmitSearch){
        JPanel panelChonKho = new JPanel();
        JPanel panelSearch = new JPanel();
        panelChonKho.setOpaque(false);
        panelChonKho.setBorder(BorderFactory.createEmptyBorder(14,10,0,0));
        panelSearch.setOpaque(false);
        panelSearch.setBorder(BorderFactory.createEmptyBorder(14,0,0,10));
        JLabel labelChonKho = new JLabel("Chọn kho");

        optionName = dsTenKho;
        optionKey = dsMaKho;

        cbChonKho = new JComboBox<String>(optionName);

        panelChonKho.add(labelChonKho);
        panelChonKho.add(cbChonKho);
        panelChucNang.add(panelChonKho,BorderLayout.WEST);

        cbChonKho.addActionListener(onChangeMaKho);

        JLabel timkiem = new JLabel("Tìm kiếm");
        searchBar = new JTextField(20);
        searchBar.setPreferredSize(new Dimension(0,25));
        panelSearch.add(timkiem);
        panelSearch.add(searchBar);
        searchBar.addActionListener(onSubmitSearch);

        panelChucNang.add(panelSearch,BorderLayout.EAST);
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
        this.panelDanhSach.SetTable(table,null);
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