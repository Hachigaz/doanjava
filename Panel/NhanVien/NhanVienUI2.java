package Panel.NhanVien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.PatternSyntaxException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import DAL.DataAccessLayer;
import DTO.Model;
import DTO.NhanvienMD;
import DTO.Custom.DSNhanVienMD;

import javax.swing.RowFilter;
import javax.swing.SwingUtilities;

import Panel.SubPanel.TablePanel;
import misc.ThongBaoDialog;

public class NhanVienUI2 extends JPanel implements MouseListener{
    NhanVienBLL nhanVienBLL = new NhanVienBLL();

    private JPanel panelRight;
    private TablePanel panelDanhSach = new TablePanel();
    private JPanel panelTable;
    private JPanel panelSearch;
    private JPanel panelInfo;
    private JPanel panelDefault;
    private JPanel panelSalary;
    private JLabel labelTitle;
    private JLabel labelDefault;
    private JTextField searchField;
    public static JButton searchButton,addButton,infoButton;
    private TableRowSorter<TableModel> rowSorter;
    private Object[] atributeNV;
    private ArrayList<JButton> btns = new ArrayList<JButton>();
    private Form form;
    public String[] labelForm = {"Mã nhân viên:       ","Tên nhân viên:     ","Chức vụ:               ","Giới tính:                ","Ngày sinh:            ","Địa chỉ:                  ","Kho làm việc:       ","Số giờ làm:           ","Lương cơ bản:    "};
    public NhanVienUI2(Dimension d){
        form = new Form((JFrame)SwingUtilities.getWindowAncestor(this),addButtonAction);
        this.setLayout(new BorderLayout());
        this.setPreferredSize(d);
        // panelRight and components
        panelRight = new JPanel();
        panelRight.setPreferredSize(new Dimension(370,500));
        panelRight.setBackground(Color.red);
        panelRight.setLayout(new FlowLayout());

        labelTitle = new JLabel("Thông tin chi tiết");
        labelTitle.setPreferredSize(new Dimension(370, 70));
        labelTitle.setFont(new Font("Poppins",Font.BOLD,18));
        labelTitle.setHorizontalAlignment(JLabel.CENTER);

        panelInfo = new JPanel();
        panelInfo.setPreferredSize(new Dimension(370,400));
        panelInfo.setLayout(new FlowLayout());
        panelInfo.setVisible(true);
        panelInfo.add(labelTitle);

        panelRight.add(panelInfo);

        //panelTable and components
        panelTable = new JPanel();
        panelTable.setLayout(new BorderLayout());
        panelTable.setBackground(Color.green);
        panelTable.setPreferredSize(new Dimension(800,500));

        panelSearch = new JPanel();
        panelSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));

        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(300,35));
        searchField.setFont(new Font("Monospace",Font.PLAIN,15));
        searchField.setForeground(Color.black);
        searchField.addActionListener(searchAction);

        ImageIcon iconSearch = new ImageIcon("res/img/search.png");
        Image imgIconSearch = iconSearch.getImage();
        Image newImgSearch = imgIconSearch.getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIconSearch = new ImageIcon(newImgSearch);
        
        searchButton = new JButton(newIconSearch);
        searchButton.setToolTipText("Tìm kiếm");
        searchButton.setBorder(null);
        searchButton.setPreferredSize(new Dimension(45,45));
        searchButton.setFocusable(false);
        searchButton.setBackground(new Color(0,255,119));
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.addActionListener(searchAction);
        searchButton.addMouseListener(this);

        ImageIcon iconAdd = new ImageIcon("res/img/add.png");
        Image imgIconAdd = iconAdd.getImage();
        Image newImgAdd = imgIconAdd.getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIconAdd = new ImageIcon(newImgAdd);

        addButton = new JButton(newIconAdd);
        addButton.setToolTipText("Thêm nhân viên");
        addButton.setBackground(new Color(0,255,119));
        addButton.setPreferredSize(new Dimension(45,45));
        addButton.setFocusable(false);
        addButton.setBorder(null);
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.addActionListener(addAction);
        addButton.addMouseListener(this);

        panelSearch.add(searchField);
        panelSearch.add(searchButton);
        panelSearch.add(addButton);

        panelDanhSach.setPreferredSize(new Dimension(830, 520));
        panelDanhSach.setOpaque(true);

        panelTable.add(panelSearch,BorderLayout.NORTH);
        panelTable.add(panelDanhSach);

        this.add(panelRight,BorderLayout.EAST);
        this.add(panelTable,BorderLayout.WEST);

        String[] columnNames = {"Mã nhân viên","Họ tên","Chức vụ","Giới tính","Ngày sinh","Địa chỉ","Kho làm việc"};
        ArrayList<DSNhanVienMD> DanhSachNhanVien = nhanVienBLL.getDanhSachNhanVien();
        TableModel tableDanhSach = new DefaultTableModel(Model.to2DArray(DanhSachNhanVien),columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        UpdateTable(tableDanhSach);

        // panelDefault = new JPanel();
        // labelDefault = new JLabel("Chọn nhân viên mà\n bạn muốn xem thông tin");
        // labelDefault.setFont(new Font("Poppins",Font.PLAIN,18));
        // panelDefault.setBorder(BorderFactory.createEmptyBorder(100,0,0,0));
        // panelDefault.add(labelDefault);

        // panelSalary = new JPanel();
        // panelSalary.setBackground(Color.red);
        // panelSalary.setVisible(false);
        // panelSalary.setPreferredSize(new Dimension(370, 600));

        // panelRight.add(panelSalary);
        // panelRight.add(panelDefault);

        // panelTable.setOpaque(true);
        // panelRight.setBackground(Color.BLUE);
        // panelRight.setOpaque(true);
        // panelSearch.setOpaque(true);
    }

    public void setUpSearchPanel(){
        
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(300,35));
        searchField.setFont(new Font("Monospace",Font.PLAIN,15));
        searchField.setForeground(Color.black);

        ImageIcon iconSearch = new ImageIcon("res/img/search.png");
        Image imgIconSearch = iconSearch.getImage();
        Image newImgSearch = imgIconSearch.getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIconSearch = new ImageIcon(newImgSearch);
        
        searchButton = new JButton(newIconSearch);
        searchButton.setToolTipText("Tìm kiếm");
        searchButton.setBorder(null);
        searchButton.setPreferredSize(new Dimension(45,45));
        searchButton.setFocusable(false);
        searchButton.setBackground(new Color(0,255,119));
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.addMouseListener(this);
        btns.add(searchButton);

        ImageIcon iconAdd = new ImageIcon("res/img/add.png");
        Image imgIconAdd = iconAdd.getImage();
        Image newImgAdd = imgIconAdd.getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIconAdd = new ImageIcon(newImgAdd);

        addButton = new JButton(newIconAdd);
        addButton.setToolTipText("Thêm nhân viên");
        addButton.setBackground(new Color(0,255,119));
        addButton.setPreferredSize(new Dimension(45,45));
        addButton.setFocusable(false);
        addButton.setBorder(null);
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.addActionListener(addAction);
        addButton.addMouseListener(this);
        btns.add(addButton);

        // ImageIcon iconInfo = new ImageIcon("res/img/info.png");
        // Image imgIconInfo = iconInfo.getImage();
        // Image newImgInfo = imgIconInfo.getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH);
        // ImageIcon newIconInfo = new ImageIcon(newImgInfo);

        // infoButton = new JButton(newIconInfo);
        // infoButton.setToolTipText("Thông tin");
        // infoButton.setBackground(new Color(0,255,119));
        // infoButton.setPreferredSize(new Dimension(45,45));
        // infoButton.setFocusable(false);
        // infoButton.setBorder(null);
        // infoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // infoButton.addActionListener(deltailAction);
        // infoButton.addMouseListener(Buttons);
        // btns.add(infoButton);

        // panelSearch.add(infoButton);
    }

    String searchedText="";
    public void timKiem(){
        String searchText = searchField.getText();
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

    public void displayInfo(){
        panelInfo.setVisible(!panelInfo.isVisible());
        panelDefault.setVisible(!panelDefault.isVisible());
    }

    private JLabel createLabelInfo(String text){
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(300, 25)); // đặt kích thước ưu tiên cho nhãn
        label.setFont(new Font("Monospace",Font.BOLD,13));
        return label;
    }

    // public void updateTable(TableModel table){
    //     panelDanhSach.SetTable(table,null);
    //     JTable tableTemp = panelDanhSach.getTableDS();
    //     tableTemp.setPreferredSize(this.getPreferredSize());
    //     tableTemp.getColumnModel().removeColumn(tableTemp.getColumnModel().getColumn(7));
    //     tableTemp.getColumnModel().removeColumn(tableTemp.getColumnModel().getColumn(7));
    //     String[] arr = new String[9];     
    //     tableTemp.addMouseListener(new MouseAdapter() {
    //         public void mousePressed(MouseEvent e){
    //             int rowIndex = tableTemp.getSelectedRow();
    //             for(int i=0;i<arr.length;i++){

    //                 if(i==7){
    //                     arr[i] = tableTemp.getModel().getValueAt(rowIndex, i).toString();
    //                 }else if(i==8){
    //                     float temp = (float) tableTemp.getModel().getValueAt(rowIndex, i);
    //                     int tempInt = (int) Math.floor(temp);
    //                     String formattedNum = String.format("%,d", tempInt).replace(",", ".");
    //                     arr[i] = String.valueOf(formattedNum)+" VNĐ";
    //                 }else{
    //                     arr[i] = tableTemp.getValueAt(rowIndex, i).toString();
    //                 }
    //             }
    //             panelDefault.setVisible(false);
    //             panelInfo.removeAll();    
    //             panelInfo.add(labelTitle);
    //             if(panelSalary.isVisible()){
    //                 panelInfo.setVisible(false);
    //             }else{
    //                 panelInfo.setVisible(true);
    //             }
                
    //             for(int i=0;i<labelForm.length;i++){
    //                 JLabel label = createLabelInfo(labelForm[i] + " " + arr[i]);
    //                 panelInfo.add(label);
    //             }
    //             // Cập nhật lại giao diện người dùng
    //             panelInfo.revalidate();
    //             panelInfo.repaint();
    //         }
    //     }); 
    // }
    ActionListener searchAction = new ActionListener() {
        public void actionPerformed(ActionEvent e){
            timKiem();
        }
    };

    ActionListener infoAction = new ActionListener() {
        public void actionPerformed(ActionEvent e){
            displayInfo();
        }
    };
    private TableModel currentTable;
    ActionListener addButtonAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] data = form.getData();
            nhanVienBLL.themNVmoi(new NhanvienMD(data[0],data[1],data[2],data[3],data[4],data[5],data[6]));
            String[] columnNames = {"Mã nhân viên","Họ tên","Chức vụ","Giới tính","Ngày sinh","Địa chỉ","Kho làm việc"};
            currentTable = new DefaultTableModel(Model.to2DArray(nhanVienBLL.getDSNhanVien(),"MaNV","TenNV","ChucVu","GioiTinh","NgaySinh","DiaChi","Kho_lam_viec"),columnNames){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            panelDanhSach.SetTable(currentTable, null);
            new ThongBaoDialog("Thêm thành công",null);
            form.dispose();
        }
    };
    ActionListener addAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            form.visible();
        }
    };
    // ActionListener displayForm = new ActionListener() {
    //     @Override
    //     public void actionPerformed(ActionEvent e) {
    //         form = new FormNhanVien();
    //         form.setUpForm(addAction);
    //     }
    // };
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == searchButton){
            searchButton.setBackground(new Color(223,18,133));
            searchButton.setForeground(Color.white);
        }else if(e.getSource() == addButton){
            addButton.setBackground(new Color(223,18,133));
            addButton.setForeground(Color.white);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == searchButton){
            searchButton.setBackground(new Color(0,255,119));
            searchButton.setForeground(Color.BLACK);
        }else if(e.getSource() == addButton){
            addButton.setBackground(new Color(0,255,119));
            addButton.setForeground(Color.BLACK);
        }
    }

    public void UpdateTable(TableModel table){
        this.panelDanhSach.SetTable(table,null);
    }

    // public void updateTable(){
    //     DataAccessLayer<DSNhanVienMD> nhanvienmd = new DataAccessLayer<DSNhanVienMD>(master,DSNhanVienMD.class);
    //     ArrayList<DSNhanVienMD> DSNV = nhanvienmd.getTable();
    //     tableModel = new DefaultTableModel(Model.to2DArray(DSNV),nhanvienmd.getReturnedColumnLabel()){
    //         public boolean isCellEditable(int row,int column){
    //             return false;
    //         }
    //     };
    //     panelDanhSach.SetTable(tableModel);
    //     JTable tableTemp = panelDanhSach.getTableDS();
    //     tableTemp.setPreferredSize(this.getPreferredSize());
    //     tableTemp.getColumnModel().removeColumn(tableTemp.getColumnModel().getColumn(7));
    //     tableTemp.getColumnModel().removeColumn(tableTemp.getColumnModel().getColumn(7));
    //     tableNewModel = tableTemp.getModel();
    //     String[] arr = new String[9];     
    //     tableTemp.addMouseListener(new MouseAdapter() {
    //         public void mousePressed(MouseEvent e){
    //             int rowIndex = tableTemp.getSelectedRow();
    //             for(int i=0;i<arr.length;i++){

    //                 if(i==7){
    //                     arr[i] = tableTemp.getModel().getValueAt(rowIndex, i).toString();
    //                 }else if(i==8){
    //                     float temp = (float) tableTemp.getModel().getValueAt(rowIndex, i);
    //                     int tempInt = (int) Math.floor(temp);
    //                     String formattedNum = String.format("%,d", tempInt).replace(",", ".");
    //                     arr[i] = String.valueOf(formattedNum)+" VNĐ";
    //                 }else{
    //                     arr[i] = tableTemp.getValueAt(rowIndex, i).toString();
    //                 }
    //             }
    //             panelDefault.setVisible(false);
    //             panelInfo.removeAll();    
    //             panelInfo.add(labelTitle);
    //             if(panelSalary.isVisible()){
    //                 panelInfo.setVisible(false);
    //             }else{
    //                 panelInfo.setVisible(true);
    //             }
                
    //             for(int i=0;i<labelForm.length;i++){
    //                 JLabel label = createLabelInfo(labelForm[i] + " " + arr[i]);
    //                 panelInfo.add(label);
    //             }
        
    //             // Cập nhật lại giao diện người dùng
    //             panelInfo.revalidate();
    //             panelInfo.repaint();
    //         }
    //     }); 
    // }
    // public void themNhanVien(){
    //     JTextField textFieldMaNV = (JTextField)atributeNV[0];
    //             String MaNV = textFieldMaNV.getText();
    //             JTextField textFieldTenNV = (JTextField)atributeNV[1];
    //             String TenNV = textFieldTenNV.getText();
    //             JComboBox comboBoxMaCV = (JComboBox)atributeNV[2];
    //             String MaCV;
    //             if(comboBoxMaCV.getSelectedItem()=="Quản trị"){
    //                 MaCV = "CV00";
    //             }else if(comboBoxMaCV.getSelectedItem()=="Quản lý kho"){
    //                 MaCV = "CV01";
    //             }else{
    //                 MaCV = "CV02";
    //             }
    //             JComboBox comboBoxGioiTinh = (JComboBox)atributeNV[3];
    //             String GioiTinh = (String)comboBoxGioiTinh.getSelectedItem();
    //             JDateChooser dateChooserNgaySinh = (JDateChooser)atributeNV[4];
    //             Date ngay = dateChooserNgaySinh.getDate();
    //             Calendar calendar = Calendar.getInstance();
    //             calendar.setTime(ngay);
    //             int nam = calendar.get(Calendar.YEAR);
    //             int thang = calendar.get(Calendar.MONTH)+1;
    //             int ngay1 = calendar.get(Calendar.DATE);
    //             String date = nam+"-"+thang+"-"+ngay1;
                
    //             JTextField textFieldDiaChi = (JTextField)atributeNV[5];
    //             String DiaChi = textFieldDiaChi.getText();
    //             JComboBox comboBoxKhoLamViec = (JComboBox)atributeNV[6];
    //             String MaKho;
    //             if(comboBoxKhoLamViec.getSelectedItem()=="Kho ADV"){
    //                 MaKho = "K01";
    //             }else if(comboBoxKhoLamViec.getSelectedItem()=="Kho THD"){
    //                 MaKho = "K02";
    //             }else if(comboBoxKhoLamViec.getSelectedItem()=="Kho NVC"){
    //                 MaKho = "K03";
    //             }else{
    //                 MaKho = "K04";
    //             }
    //             JSpinner spinnerSoGioLam = (JSpinner)atributeNV[7];
    //             Integer SoGioLam = (Integer)spinnerSoGioLam.getValue();
    //             JSpinner spinnerLuongCoBan = (JSpinner)atributeNV[8];
    //             Integer value = (Integer) spinnerLuongCoBan.getValue();
    //             Float LuongCoBan = value.floatValue();
    //             if (MaNV.isEmpty() || TenNV.isEmpty() || MaCV.isEmpty() || GioiTinh.isEmpty() || date.isEmpty() || DiaChi.isEmpty() || MaKho.isEmpty()) {
    //                 JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
    //                 return;
    //             }
    //             DataAccessLayer<NhanvienMD> NhanVienDAO = new DataAccessLayer<>(ui.master,NhanvienMD.class);
    //             ArrayList<NhanvienMD> DSNV = new ArrayList<NhanvienMD>();
    //             DSNV.add(new NhanvienMD(MaNV, TenNV, MaCV, GioiTinh, date, DiaChi, MaKho, SoGioLam, LuongCoBan));
    //             NhanVienDAO.add(DSNV);
    //             // ui.updateTable();
    //             // panelDanhSach.setPreferredSize(new Dimension(830, 700));
                
    //             // JOptionPane.showMessageDialog(null, "Thêm thành công");
    //             // dispose();
    // }
}
