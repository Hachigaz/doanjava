package GUI;

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
import java.text.DecimalFormat;
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
import javax.xml.crypto.Data;

import com.toedter.calendar.JDateChooser;

import BLL.NhanVienBLL;
import DAL.DataAccessLayer;
import DTO.Model;
import DTO.NhanvienMD;
import DTO.Taikhoan_nhanvienMD;
import DTO.Custom.DSNhanVienMD;

import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import Panel.NhanVien.Form;
import Panel.SubPanel.TablePanel;
import misc.ThongBaoDialog;


public class NhanVienUI2 extends JPanel implements MouseListener{
    NhanVienBLL nhanVienBLL = new NhanVienBLL();

    private JPanel panelRight;
    private TablePanel panelDanhSach = new TablePanel();
    private JPanel panelTable;
    private JPanel panelSearch;
    private JPanel panelInfo,panelButton;
    private JPanel panelDefault;
    private JPanel panelSalary;
    private JLabel labelTitle;
    private JLabel labelDefault;
    private JTable tableTemp;
    private JComboBox comboChucVu;
    private JTextField searchField;
    public static JButton searchButton,addButton,infoButton;
    private JButton editButton,deleteButton;
    private TableRowSorter<TableModel> rowSorter;
    private TableModel tableDanhSach;
    private Object[] atributeNV;
    private ArrayList<JButton> btns = new ArrayList<JButton>();
    public Form form;
    public String[] labelForm = {"Mã nhân viên:       ","Tên nhân viên:     ","Chức vụ:               ","Giới tính:                ","Ngày sinh:            ","Địa chỉ:                  ","Kho làm việc:       "};
    public NhanVienUI2(Dimension d){
        // form = new Form((JFrame)SwingUtilities.getWindowAncestor(this),addButtonAction);
        this.setLayout(new BorderLayout());
        this.setPreferredSize(d);
        // panelRight and components
        panelRight = new JPanel();
        panelRight.setPreferredSize(new Dimension(370,500));
        panelRight.setLayout(new FlowLayout());

        labelTitle = new JLabel("Thông tin chi tiết");
        labelTitle.setPreferredSize(new Dimension(370, 70));
        labelTitle.setFont(new Font("Poppins",Font.BOLD,18));
        labelTitle.setHorizontalAlignment(JLabel.CENTER);

        deleteButton = new JButton("Xóa");
        deleteButton.setPreferredSize(new Dimension(100,40));
        deleteButton.setBackground(new Color(0,255,119));
        deleteButton.setForeground(Color.BLACK);
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.setFocusable(false);
        deleteButton.setBorder(null);
        deleteButton.addActionListener(deleteAction);
        deleteButton.addMouseListener(this);

        editButton = new JButton("Sửa");
        editButton.setPreferredSize(new Dimension(100,40));
        editButton.setBackground(new Color(0,255,119));
        editButton.setForeground(Color.BLACK);
        editButton.setFocusable(false);
        editButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        editButton.setBorder(null);
        editButton.addActionListener(editAction);
        editButton.addMouseListener(this);

        panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout());
        panelButton.add(deleteButton);
        panelButton.add(editButton);
        panelButton.setPreferredSize(new Dimension(400,100));
        panelButton.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));

        panelInfo = new JPanel();
        panelInfo.setPreferredSize(new Dimension(370,400));
        panelInfo.setLayout(new FlowLayout());
        panelInfo.setVisible(false);
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

        String[] dschucvu = {"Tất cả",nhanVienBLL.layTenChucVu()[0],nhanVienBLL.layTenChucVu()[1],nhanVienBLL.layTenChucVu()[2]};
        comboChucVu = new JComboBox<>(dschucvu);
        comboChucVu.addActionListener(changeChucVu);

        panelSearch.add(searchField);
        panelSearch.add(searchButton);
        panelSearch.add(addButton);
        panelSearch.add(comboChucVu);

        panelDanhSach.setPreferredSize(new Dimension(830, 520));
        panelDanhSach.setOpaque(true);

        panelTable.add(panelSearch,BorderLayout.NORTH);
        panelTable.add(panelDanhSach);

        this.add(panelRight,BorderLayout.EAST);
        this.add(panelTable,BorderLayout.WEST);

        String[] columnNames = {"Mã nhân viên","Họ tên","Chức vụ","Giới tính","Ngày sinh","Địa chỉ","Kho làm việc"};
        ArrayList<DSNhanVienMD> DanhSachNhanVien = nhanVienBLL.getDanhSachNhanVien();
        tableDanhSach = new DefaultTableModel(Model.to2DArray(DanhSachNhanVien),columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        panelDanhSach.SetTable(tableDanhSach, null);
        tableTemp = panelDanhSach.getTableDS();
        tableTemp.addMouseListener(actionInfo);

        panelDefault = new JPanel();
        labelDefault = new JLabel("Chọn nhân viên mà\n bạn muốn xem thông tin");
        labelDefault.setFont(new Font("Poppins",Font.PLAIN,18));
        panelDefault.setBorder(BorderFactory.createEmptyBorder(100,0,0,0));
        panelDefault.add(labelDefault);

        panelRight.add(panelDefault);
    }
    private String[] optionName = {"Tất cả",nhanVienBLL.layTenChucVu()[0],nhanVienBLL.layTenChucVu()[1],nhanVienBLL.layTenChucVu()[2]};
    public String getSelectedChucVuKey(){
        String selected = comboChucVu.getSelectedItem().toString();
        for(int i = 0; i < optionName.length;i++){
            if(selected.equals(optionName[i])){
                return optionName[i];
            }
        }
        return null;
    }

    String searchedText="";
    public void timKiem(){
        String searchText = searchField.getText();
        if (searchText.length() == 0) {
            panelDanhSach.xoaDieuKienLoc(1, searchedText);
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
    ActionListener changeChucVu = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] comlumnname = {"Mã nhân viên","Họ tên","Chức vụ","Giới tính","Ngày sinh","Địa chỉ","Kho làm việc"};
            if(getSelectedChucVuKey()=="Tất cả"){
                ArrayList<DSNhanVienMD> dsTraCuu = nhanVienBLL.getDsNhanVienMD();
                TableModel tableDanhSach = new DefaultTableModel(Model.to2DArray(dsTraCuu), comlumnname){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                panelDanhSach.SetTable(tableDanhSach, null);
                tableTemp = panelDanhSach.getTableDS();
                tableTemp.addMouseListener(actionInfo); 
            }else{
                ArrayList<DSNhanVienMD> dsTraCuu = nhanVienBLL.getDsNhanVienMD("TenCV = "+getSelectedChucVuKey());
                TableModel tableDanhSach = new DefaultTableModel(Model.to2DArray(dsTraCuu),comlumnname){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            panelDanhSach.SetTable(tableDanhSach, null);
            tableTemp = panelDanhSach.getTableDS();
            tableTemp.addMouseListener(actionInfo);
            }
        }   
    };
    private TableModel currentTableDS;
    public void createForm(){
        Window window = SwingUtilities.getWindowAncestor(this);
        form = new Form((JFrame) window, addButtonAction);
        form.setVisible(true);
        form.addButton.addMouseListener(this);
    }
    private TableModel currentTable;
    String chucvu, tenkho;
    ActionListener addButtonAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] data = form.getData();
            String[] dataTK = form.getUserPass();
            if(form.check()==false){
                JOptionPane.showMessageDialog(form, "Mời bạn nhập đầy đủ thông tin");
            }else{
                if(dataTK[4] == "False"){
                    JOptionPane.showMessageDialog(form, "Bạn nhập mật khẩu không hợp lệ!");
                }else{
                    DecimalFormat df = new DecimalFormat("000"); 
                String manhanvien = data[0]+df.format(nhanVienBLL.layMa());
                nhanVienBLL.themNVmoi(new NhanvienMD(manhanvien,data[1],data[2],data[3],data[4],data[5],data[6]));
                nhanVienBLL.themTKmoi(new Taikhoan_nhanvienMD(manhanvien, dataTK[1], dataTK[2], dataTK[3]));
                String[] columnNames = {"Mã nhân viên","Họ tên","Chức vụ","Giới tính","Ngày sinh","Địa chỉ","Kho làm việc"};
                currentTableDS = new DefaultTableModel(Model.to2DArray(nhanVienBLL.getDsNhanVienMD(),"MaNV","TenNV","TenCV","GioiTinh","NgaySinh","DiaChi","TenKho"),columnNames){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
                panelDanhSach.SetTable(currentTableDS, null);
                tableTemp = panelDanhSach.getTableDS();
                tableTemp.addMouseListener(actionInfo);
                JOptionPane.showMessageDialog(form, "Thêm thành công");
                form.dispose();
                }   
            }        
        }
    };
    ActionListener editButtonAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] data = form.getData();
            String[] dataTK = form.getUserPass();
            int rowIndex = tableTemp.getSelectedRow();
            arr[0] = tableTemp.getValueAt(rowIndex, 0).toString();
            if(form.check()==false){
                JOptionPane.showMessageDialog(form, "Mời bạn nhập đầy đủ thông tin");
            }else{
                if(dataTK[4] == "False"){
                    JOptionPane.showMessageDialog(form, "Bạn nhập mật khẩu không hợp lệ!");
                }else{
                    nhanVienBLL.suaNV("MaNV = "+arr[0],"TenNV = "+data[1]," MaCV = "+data[2]," GioiTinh = "+data[3]," NgaySinh = "+data[4]," DiaChi = "+data[5]," Kho_lam_viec = "+data[6]);
                    nhanVienBLL.suaTK("MaNV = "+arr[0],"TenTaiKhoan = "+dataTK[1]," MatKhau = "+dataTK[2]," MaNhomQuyen = "+dataTK[3]);
                    String[] columnNames = {"Mã nhân viên","Họ tên","Chức vụ","Giới tính","Ngày sinh","Địa chỉ","Kho làm việc"};
                    currentTableDS = new DefaultTableModel(Model.to2DArray(nhanVienBLL.getDsNhanVienMD(),"MaNV","TenNV","TenCV","GioiTinh","NgaySinh","DiaChi","TenKho"),columnNames){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                panelDanhSach.SetTable(currentTableDS, null);
                tableTemp = panelDanhSach.getTableDS();
                tableTemp.addMouseListener(actionInfo);
                JOptionPane.showMessageDialog(form, "Sửa thành công");
                panelDefault.setVisible(true);
                panelInfo.setVisible(false);
                form.dispose();
                }
        }
        }
    };
    String[] arr = new String[10];
    ActionListener deleteAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int rowIndex = tableTemp.getSelectedRow();
            arr[0] = tableTemp.getValueAt(rowIndex, 0).toString();
            arr[1] = tableTemp.getValueAt(rowIndex, 1).toString();
            int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa nhân viên "+arr[1]+" không?", "Xác nhận xóa dữ liệu", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                nhanVienBLL.xoaTK("MaNV = "+arr[0]);
                nhanVienBLL.xoaNV("MaNV = "+arr[0]);
                String[] columnNames = {"Mã nhân viên","Họ tên","Chức vụ","Giới tính","Ngày sinh","Địa chỉ","Kho làm việc"};
                currentTableDS = new DefaultTableModel(Model.to2DArray(nhanVienBLL.getDsNhanVienMD(),"MaNV","TenNV","TenCV","GioiTinh","NgaySinh","DiaChi","TenKho"),columnNames){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
                panelDanhSach.SetTable(currentTableDS, null);
                tableTemp = panelDanhSach.getTableDS();
                tableTemp.addMouseListener(actionInfo);
                JOptionPane.showMessageDialog(form, "Xóa thành công");
                panelDefault.setVisible(true);
                panelInfo.setVisible(false);
            } else {
                // xử lý khi người dùng chọn "Không"
            }
        }    
    };
    ActionListener editAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int rowIndex = tableTemp.getSelectedRow();
            arr[0] = tableTemp.getValueAt(rowIndex, 0).toString();
            arr[1] = tableTemp.getValueAt(rowIndex, 1).toString();
            arr[2] = tableTemp.getValueAt(rowIndex, 2).toString();
            arr[3] = tableTemp.getValueAt(rowIndex, 3).toString();
            arr[4] = tableTemp.getValueAt(rowIndex, 4).toString();
            arr[5] = tableTemp.getValueAt(rowIndex, 5).toString();
            arr[6] = tableTemp.getValueAt(rowIndex, 6).toString();
            arr[7] = nhanVienBLL.layTaiKhoan(arr[0])[1];
            arr[8] = nhanVienBLL.layTaiKhoan(arr[0])[2];
            arr[9] = nhanVienBLL.layTaiKhoan(arr[0])[3];
            editForm();
        }
    };
    
    public void editForm(){
        Window window = SwingUtilities.getWindowAncestor(this);
        form = new Form((JFrame) window, editButtonAction);
        form.addButton.setText("Sửa");
        form.textTenNV.setText(arr[1]);
        form.comboBox.setSelectedItem(arr[2]);
        if(arr[3]=="Nam"){
            form.radio1.setSelected(true);
        }else{
            form.radio2.setSelected(true);
        }
        Calendar calendar = Calendar.getInstance();
        String[] dateParts = arr[4].split("/");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]) - 1; // Giá trị tháng trong Calendar bắt đầu từ 0
        int year = Integer.parseInt(dateParts[2]);
        calendar.set(year, month, day);
        Date date = calendar.getTime();
        form.dateChooser.setDate(date);
        form.textDiaChi.setText(arr[5]);
        form.comboBox2.setSelectedItem(arr[6]);
        form.textUsername.setText(nhanVienBLL.layTaiKhoan(arr[0])[1]);
        form.password.setText(nhanVienBLL.layTaiKhoan(arr[0])[2]);
        form.retypepass.setText(nhanVienBLL.layTaiKhoan(arr[0])[3]);
        form.setVisible(true);
    }   
    
    MouseListener actionInfo = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }
        @Override
        public void mousePressed(MouseEvent e) {
            int rowIndex = tableTemp.getSelectedRow();
            for(int i=0;i<7;i++){
                arr[i] = tableTemp.getValueAt(rowIndex, i).toString();
            }
            panelDefault.setVisible(false);
            panelInfo.setVisible(true);
            panelInfo.removeAll();     
            panelInfo.add(labelTitle);
            for(int i=0;i<labelForm.length;i++){
                JLabel label = createLabelInfo(labelForm[i] + " " + arr[i]);
                panelInfo.add(label);
            }
            panelInfo.add(panelButton);
            panelInfo.revalidate();
            panelInfo.repaint();    
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
    ActionListener addAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            createForm();
        }
    };
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e){  
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
        }else if(e.getSource() == deleteButton){
            deleteButton.setBackground(new Color(223,18,133));
            deleteButton.setForeground(Color.white);
        }else if(e.getSource() == editButton){
            editButton.setBackground(new Color(223,18,133));
            editButton.setForeground(Color.white);
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
        }else if(e.getSource() == deleteButton){
            deleteButton.setBackground(new Color(0,255,119));
            deleteButton.setForeground(Color.BLACK);
        }else if(e.getSource() == editButton){
            editButton.setBackground(new Color(0,255,119));
            editButton.setForeground(Color.BLACK);
        }
    }

    public void UpdateTable(TableModel table){
        this.panelDanhSach.SetTable(table,null);
    }
}