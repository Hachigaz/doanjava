package Panel.TraCuuHang;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.PatternSyntaxException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DTO.CongtyMD;
import DTO.KhuvucMD;
import DTO.Loai_hangMD;
import DTO.Model;
import DTO.Custom.DSTraCuuHangMD;
import Panel.SubPanel.LocPanel;
import Panel.SubPanel.TablePanel;
import misc.util;
public class TraCuuHangUI extends JPanel{
    //BLL
    private TraCuuHangBLL traCuuHangBLL = new TraCuuHangBLL();

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
        panelLoc.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        panelDanhSach.setBackground(new Color(255, 182, 87,255));
        panelDanhSach.setOpaque(true);

        Object[][] dsKho = Model.to2DArray(traCuuHangBLL.getDanhSachKho(),"MaKho","TenKho");

        SetupPanelChucNang(util.objToString(util.getColumn(dsKho, 1)),util.objToString(util.getColumn(dsKho, 0)));

        setupPanel();
    }
    public void setupPanel(){
        String[] locPanelTitle = {"Lọc theo khu vực","Lọc theo loại hàng","Lọc theo sản phẩm của công ty"};
        int[] columnIndexes = {0,3,4};
        ArrayList<ArrayList<String>> tenLoc = new ArrayList<ArrayList<String>>();



        //Lấy danh sách khu vực và thêm vào bảng lộc
        ArrayList<KhuvucMD> danhSachKV = traCuuHangBLL.getDanhSachKV("MaKho = "+getSelectedMaKhoKey());

        tenLoc.add(new ArrayList<String>());
        for(KhuvucMD khuvuc : danhSachKV){          
            tenLoc.get(0).add(khuvuc.getTenKV());
        }

        //Lấy danh sách khu vực và thêm vào bảng lộc
        ArrayList<Loai_hangMD> danhSachLH = traCuuHangBLL.getDanhSachLoaiHang();

        tenLoc.add(new ArrayList<String>());
        for(Loai_hangMD loaihang : danhSachLH){          
            tenLoc.get(1).add(loaihang.getTenloai());
        }


        //Lấy danh sách khu vực và thêm vào bảng lộc
        ArrayList<CongtyMD> danhSachCT = traCuuHangBLL.getDanhSachCongTy(); 

        tenLoc.add(new ArrayList<String>());
        for(CongtyMD cty : danhSachCT){
            tenLoc.get(2).add(cty.getTenCty());
        }
        

        SetupPanelLoc(locPanelTitle, columnIndexes, tenLoc);

        //setup bảng
        String[] columnNames = {"Khu vực","Tên hàng","Số lượng","Loại sản phẩm","Công ty","Ngày nhập"};
        ArrayList<DSTraCuuHangMD> dsTraCuu = traCuuHangBLL.getDanhSachTCH("donnhap.MaKho = "+getSelectedMaKhoKey());
        TableModel tableDanhSach = new DefaultTableModel(Model.to2DArray(dsTraCuu),columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        UpdateTable(tableDanhSach);
        
    }
    public void setVisible(boolean isVisible){
        super.setVisible(isVisible);
        String[] columnNames = {"Khu vực","Tên hàng","Số lượng","Loại sản phẩm","Công ty","Ngày nhập"};
        ArrayList<DSTraCuuHangMD> dsTraCuu = traCuuHangBLL.getDanhSachTCH("donnhap.MaKho = "+getSelectedMaKhoKey());
        TableModel tableDanhSach = new DefaultTableModel(Model.to2DArray(dsTraCuu),columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        UpdateTable(tableDanhSach);
    }
    //lọc theo loại sp và khu vực
    private ArrayList<JPanel> arrLocLabel = new ArrayList<JPanel>();
    private HashMap<JPanel,JScrollPane> arrLocPanel = new HashMap<JPanel,JScrollPane>();
    private ArrayList<JPanel> arrLocPanelWrapper = new ArrayList<JPanel>();

    private void xoaChucNangLocCu(){
        for(JPanel panelWrapper : arrLocPanelWrapper){
            panelLoc.remove(panelWrapper);
        }
        arrLocPanel.clear();
        arrLocLabel.clear();
        arrLocPanelWrapper.clear();
    }

    public void SetupPanelLoc(String[] panelTitles,int[] columnIndexes,ArrayList<ArrayList<String>> tenLoc){
        xoaChucNangLocCu();
        for(int i = 0 ; i < tenLoc.size();i++){
            themChucNangLoc(panelTitles[i],columnIndexes[i],tenLoc.get(i));
        }
        panelLoc.revalidate();
        panelLoc.repaint();
    }

    public void collapseClickedLocPanel(Object component){
        for(JPanel panel : arrLocLabel){
            if(component==panel){
                JScrollPane paneLoc = arrLocPanel.get(panel);
                paneLoc.setVisible(!paneLoc.isVisible());
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
    MouseListener panelCollapseListener = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {            
            collapseClickedLocPanel(e.getSource());
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        
        }

        @Override
        public void mouseExited(MouseEvent e) {
        
        }

    };

    ItemListener locCheckboxAction = new ItemListener() {

        @Override
        public void itemStateChanged(ItemEvent e) {
            sortSelectedCheckbox(e.getSource());
        }
        
    };
    private void themChucNangLoc(String title,int columnIndex,ArrayList<String> locLabelName){
        

        JLabel label = new JLabel(title);
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0,0));
        labelPanel.add(label);
        labelPanel.setPreferredSize(new Dimension(panelLoc.getPreferredSize().width,30));
        labelPanel.addMouseListener(panelCollapseListener);
        arrLocLabel.add(labelPanel);
        LocPanel panel = new LocPanel();
        panel.setupPanel(locLabelName,columnIndex);
        JScrollPane scrollPane = new JScrollPane(panel);

        JPanel panelWrapper = new JPanel();
        arrLocPanelWrapper.add(panelWrapper);
        
        panelWrapper.setLayout(new BoxLayout(panelWrapper, BoxLayout.Y_AXIS));
        panelWrapper.add(labelPanel);
        panelWrapper.add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(panelLoc.getPreferredSize().width,250));
        panelLoc.add(panelWrapper);
        arrLocPanel.put(labelPanel,scrollPane);

        panel.setActionForCheckBoxes(locCheckboxAction);
    }

    private String[] optionName;
    private String[] optionKey;
    private JComboBox<String> cbChonKho;

    ActionListener onChangeMaKho = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            setupPanel();
        }

    };

    ActionListener onSubmitSearch = new ActionListener() {
        public void actionPerformed(ActionEvent e){
            timTheoGiaTri();
        }
    };
    
    public void SetupPanelChucNang(String[] dsTenKho,String[]dsMaKho){
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
            panelDanhSach.xoaDieuKienLoc(1,searchedText);
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