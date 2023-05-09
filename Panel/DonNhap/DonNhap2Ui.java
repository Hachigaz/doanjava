package Panel.DonNhap;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
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
import DTO.DonNhapMD;
import DTO.KhoMD;
import DTO.KhuvucMD;
import DTO.Loai_hangMD;
import DTO.Model;
import DTO.Custom.DSDonNhapMD;
import DTO.Custom.DSTraCuuHangMD;
import Panel.Form.FormCTDN;
import Panel.Form.FormDon;
import Panel.SubPanel.LocPanel;
import Panel.SubPanel.TablePanel;
import misc.util;
public class DonNhap2Ui extends JPanel{
    //BLL
    private DonNhap2BLL DonNhap2BLL = new DonNhap2BLL();
    private JTable tableTemp;
    private JPanel panelChucNang;
    private JPanel panelLoc;
    private TablePanel panelDanhSach;
    public static JButton SPButton,btlook;
    


    private JTextField searchBar;



    public DonNhap2Ui(Dimension d){

        this.panelChucNang = new JPanel();
        this.panelLoc = new JPanel();
        this.panelDanhSach = new TablePanel();

        this.setLayout(new BorderLayout());

        this.setPreferredSize(d);

        panelChucNang.setPreferredSize(new Dimension(this.getPreferredSize().width,60));
        panelLoc.setPreferredSize(new Dimension(300,this.getPreferredSize().height-60));
        panelDanhSach.setPreferredSize(new Dimension(this.getPreferredSize().width-240,this.getPreferredSize().height-60));

        this.add(panelChucNang,BorderLayout.NORTH);
        this.add(panelLoc,BorderLayout.WEST);
        this.add(panelDanhSach,BorderLayout.CENTER);
        String[] columnNames = {"Mã Đơn ","Mã kho","Mã Cty","Tên Cty","Mã NV","Ngày nhập"};
        ArrayList<DSDonNhapMD> dsDN = DonNhap2BLL.getDanhSachDN();
        TableModel tableDanhSach = new DefaultTableModel(Model.to2DArray(dsDN),columnNames);
        panelDanhSach.SetTable(tableDanhSach, null);
        tableTemp = panelDanhSach.getTableDS();
        tableTemp.addMouseListener(actionInfo);
        //panelChucNang.setBackground(new Color(27,101,147));
        //panelChucNang.setOpaque(true);
        //panelChucNang.setLayout(new BorderLayout());
        panelLoc.setBackground(Color.LIGHT_GRAY);
        panelLoc.setOpaque(true);
        panelLoc.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        panelDanhSach.setBackground(new Color(255, 182, 87,255));
        panelDanhSach.setOpaque(true);

        //Object[][] dsDN = Model.to2DArray(DonNhap2BLL.getDanhSachDonNhap());

        btlook = new JButton("Xem đơn nhập");
        btlook.setPreferredSize(new Dimension(100, 40));
        btlook.setBackground(new Color(255, 197, 70));
        btlook.setForeground(new Color(0, 0, 0));
        btlook.setBorder(null);
        btlook.setOpaque(true);
        btlook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btlook.setEnabled(false);
        SetupPanelChucNang();
        panelChucNang.add(btlook);
        setupPanel();
    }
    public void setupPanel(){
        String[] locPanelTitle = {"Lọc theo kho","Lọc theo công ty","Lọc theo sản phẩm của công ty"};
        int[] columnIndexes = {1,3,4};
        ArrayList<ArrayList<String>> tenLoc = new ArrayList<ArrayList<String>>();



        //Lấy danh sách kho và thêm vào bảng lộc
        ArrayList<KhoMD> danhSachKho = DonNhap2BLL.getDanhSachKho();

        tenLoc.add(new ArrayList<String>());
        for(KhoMD kho : danhSachKho){          
            tenLoc.get(0).add(kho.getMaKho());
        }

        //Lấy danh sách khu vực và thêm vào bảng lộc
         ArrayList<CongtyMD> danhSachCongtyMD = DonNhap2BLL.getDanhSachCongTy();
         tenLoc.add(new ArrayList<String>());
        for(CongtyMD congty : danhSachCongtyMD){          
            tenLoc.get(1).add(congty.getTenCty());
        }


        //Lấy danh sách khu vực và thêm vào bảng lộc
        // ArrayList<CongtyMD> danhSachCT = DonNhap2BLL.getDanhSachCongTy(); 

        // tenLoc.add(new ArrayList<String>());
        // for(CongtyMD cty : danhSachCT){
        //     tenLoc.get(2).add(cty.getTenCty());
        // }
        

        SetupPanelLoc(locPanelTitle, columnIndexes, tenLoc);

        //setup bảng
        // String[] columnNames = {"Mã Đơn ","Mã kho","Mã Cty","Tên Cty","Mã NV","Ngày nhập"};
        // ArrayList<DSDonNhapMD> dsDN = DonNhap2BLL.getDanhSachDN();
        // TableModel tableDanhSach = new DefaultTableModel(Model.to2DArray(dsDN),columnNames){
        //     @Override
        //     public boolean isCellEditable(int row, int column) {
        //         return false;
        //     }
        // };
        // UpdateTable(tableDanhSach);
        
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
    String[] arr = new String[5];
    MouseListener actionInfo = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }
        @Override
        public void mousePressed(MouseEvent e) {
            int rowIndex = tableTemp.getSelectedRow();
            for(int i=0;i<arr.length;i++){
                arr[i] = tableTemp.getValueAt(rowIndex, i).toString();
            }

            btlook.setEnabled(true);
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
    
    public void SetupPanelChucNang(){
        JButton btadd = new JButton("Thêm đơn nhập");
        btadd.setBorder(null);
        btadd.setPreferredSize(new Dimension(100,40));
        btadd.setBackground(new Color(0, 255, 119));
        btadd.setForeground(new Color(0, 0, 0));
        btadd.setOpaque(true);
        btadd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // xem  chi tiết đơn

        btadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormDon();
            }
        });
        btlook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormCTDN(arr[0]);
            }
        });
        panelChucNang.add(btadd);
        //panelChucNang.add(btlook);
    }

    //lấy mã kho đang chọn trong combobox
    // public String getSelectedMaKhoKey(){
    //     String selected = cbChonKho.getSelectedItem().toString();
    //     for(int i = 0; i < optionKey.length;i++){
    //         if(selected.equals(optionName[i])){
    //             return optionKey[i];
    //         }
    //     }
    //     return null;
    // }
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