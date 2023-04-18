package Panel;

import Panel.SubPanel.LocPanel;
import Panel.SubPanel.TablePanel;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import DAO.DataAccessLayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.PatternSyntaxException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import SQL.SQLUser;
import misc.DataSet;
import Model.*;

public class NhanVien extends JPanel implements MouseListener{
    public DefaultTableCellRenderer center;
    JTable table;
    JScrollPane scrollPane;
    JScrollBar scrollBar;
    JTextField searchField;
    JButton searchButton,addButton,infoButton,salaryButton;
    JButton btn;
    JPanel searchPanel,panelTable,panelRight,panelInfo,panelSalary,panelDefault;
    JLabel labelCombobox,labelTitle,labelDefault;
    JComboBox comboBox;
    JTextField[] textFields;
    Object[] atributeNV;
    // Object[] obj;
    // String[] add;
    String[] arrange = {"Tên","Chức vụ","Kho làm việc"}; 
    private SQLUser master;
    private Taikhoan_nhanvienMD tkDangNhap;
    private JPanel panelLoc;
    private TablePanel panelDanhSach;

    DefaultTableModel model;
    TableRowSorter<TableModel> rowSorter;
    private final String sqlDSNV = "select nhanvien.MaNV as 'Mã nhân viên', nhanvien.TenNV as 'Tên nhân viên', chucvu.TenCV as 'Chức vụ', nhanvien.GioiTinh as 'Giới tính', nhanvien.NgaySinh as 'Ngày sinh', nhanvien.DiaChi as 'Địa chỉ', kho.TenKho as 'Kho làm việc',nhanvien.SoGioLamViec as 'Số giờ làm việc',nhanvien.LuongCoBan as 'Lương cơ bản' from nhanvien , chucvu, kho where nhanvien.MaCV = chucvu.MaCV and kho.MaKho = nhanvien.Kho_lam_viec";
    public String[] labelForm = {"Mã nhân viên:       ","Tên nhân viên:     ","Chức vụ:               ","Giới tính:                ","Ngày sinh:            ","Địa chỉ:                  ","Kho làm việc:       ","Số giờ làm:           ","Lương cơ bản:    "};
    public String[] btnChucNang = {};
    public NhanVien(SQLUser master,Taikhoan_nhanvienMD tkdn){
        this.master = master;
        this.tkDangNhap = tkdn;

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1200,590));

        this.SetTable(master.getDataQuery(sqlDSNV));

        panelRight = new JPanel();
        panelRight.setPreferredSize(new Dimension(370,500));
        panelRight.setLayout(new FlowLayout());

        labelTitle = new JLabel("Thông tin chi tiết");
        labelTitle.setPreferredSize(new Dimension(370, 70));
        labelTitle.setFont(new Font("Poppins",Font.BOLD,18));
        labelTitle.setHorizontalAlignment(JLabel.CENTER);

        panelDefault = new JPanel();
        labelDefault = new JLabel("Chọn nhân viên mà\n bạn muốn xem thông tin");
        labelDefault.setFont(new Font("Poppins",Font.PLAIN,18));
        panelDefault.setBorder(BorderFactory.createEmptyBorder(100,0,0,0));
        panelDefault.add(labelDefault);
        

        panelInfo = new JPanel();
        panelInfo.setPreferredSize(new Dimension(370,400));
        panelInfo.setLayout(new FlowLayout());
        panelInfo.setVisible(false);

        panelInfo.add(labelTitle);

        for(int i=0;i<labelForm.length;i++){
            panelInfo.add(createLabelInfo(labelForm[i]));
        }

        panelSalary = new JPanel();
        panelSalary.setBackground(Color.red);
        panelSalary.setVisible(false);
        panelSalary.setPreferredSize(new Dimension(370, 600));

        panelTable = new JPanel();
        panelTable.setLayout(new BorderLayout());
        
        searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        searchField = new JTextField();
        searchField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String searchText = searchField.getText();
                if (searchText.length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    try {
                        rowSorter.setRowFilter(RowFilter.regexFilter(searchText));
                    } catch (PatternSyntaxException ex) {
                        System.err.println("Invalid regex pattern: " + ex.getMessage());
                    }
                }
            }
        });
        searchField.setPreferredSize(new Dimension(300,35));
        searchField.setFont(new Font("Monospace",Font.PLAIN,15));
        searchField.setForeground(Color.black);

        ImageIcon iconSearch = new ImageIcon("res/img/search.png");
        Image imgIconSearch = iconSearch.getImage();
        Image newImgSearch = imgIconSearch.getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIconSearch = new ImageIcon(newImgSearch);
        searchButton = new JButton(newIconSearch);
        searchButton.setToolTipText("Tìm kiếm");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lấy nội dung từ ô tìm kiếm
                String searchText = searchField.getText();
                if (searchText.length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    try {
                        // Áp dụng bộ lọc với chuỗi tìm kiếm
                        rowSorter.setRowFilter(RowFilter.regexFilter(searchText));
                    } catch (PatternSyntaxException ex) {
                        System.err.println("Invalid regex pattern: " + ex.getMessage());
                    }
                }
            }
        });
        searchButton.setBorder(null);
        searchButton.setPreferredSize(new Dimension(45,45));
        searchButton.setFocusable(false);
        searchButton.setBackground(new Color(255,197,70));
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // lấy ra đối tượng JFrame cha của NhanVien
                FormNhanVien();
            }            
        });
        addButton.addMouseListener(this);

        ImageIcon iconInfo = new ImageIcon("res/img/info.png");
        Image imgIconInfo = iconInfo.getImage();
        Image newImgInfo = imgIconInfo.getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIconInfo = new ImageIcon(newImgInfo);
        infoButton = new JButton(newIconInfo);
        infoButton.setToolTipText("Thông tin");
        infoButton.setBackground(new Color(0,255,119));
        infoButton.setPreferredSize(new Dimension(45,45));
        infoButton.setFocusable(false);
        infoButton.setBorder(null);
        infoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelInfo.setVisible(true);
                panelSalary.setVisible(false);
                panelDefault.setVisible(false);
            } 
        });
        infoButton.addMouseListener(this);

        
        ImageIcon iconFilter = new ImageIcon("res/img/coin.png");
        Image imgIconFilter = iconFilter.getImage();
        Image newImgFilter = imgIconFilter.getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIconFilter = new ImageIcon(newImgFilter);
        salaryButton = new JButton(newIconFilter);
        salaryButton.setToolTipText("Lương");
        salaryButton.setBackground(new Color(0,255,119));
        salaryButton.setPreferredSize(new Dimension(45,45));
        salaryButton.setFocusable(false);
        salaryButton.setBorder(null);
        salaryButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        salaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelInfo.setVisible(false);
                panelSalary.setVisible(true);
                panelDefault.setVisible(false);
            } 
        });
        salaryButton.addMouseListener(this);

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(addButton);
        searchPanel.add(infoButton);
        searchPanel.add(salaryButton);

        panelTable.add(searchPanel,BorderLayout.NORTH);
        panelTable.add(scrollPane);

        panelRight.add(panelInfo);
        panelRight.add(panelSalary);
        panelRight.add(panelDefault);

        this.add(panelTable,BorderLayout.WEST);
        this.add(panelRight,BorderLayout.EAST);
        this.setVisible(false);
    }
    public JDialog FormNhanVien(){
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(NhanVien.this);
                JDialog dialog = new JDialog(parentFrame,"Thêm nhân viên",true);

                JPanel panelCenter = new JPanel();
                panelCenter.setLayout(new GridBagLayout());

                JPanel panelTop = new JPanel();
                JLabel title = new JLabel("FORM THÊM NHÂN VIÊN");
                title.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
                title.setFont(new Font("Monospace",Font.BOLD,20));
                panelTop.add(title);

                JPanel panelBottom = new JPanel();
                panelBottom.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));
                btn = new JButton("Thêm");
                panelBottom.add(btn);

                JPanel panelDialog = new JPanel();
                panelDialog.setPreferredSize(new Dimension(1000,700));
                panelDialog.setLayout(new BorderLayout());
                panelDialog.add(panelTop,BorderLayout.NORTH);
                panelDialog.add(panelCenter,BorderLayout.CENTER);
                panelDialog.add(panelBottom,BorderLayout.SOUTH);

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(10, 10, 10, 10);
                for(int i=0;i<labelForm.length;i++){
                    gbc.gridx = 0;
                    gbc.gridy = i;
                    panelCenter.add(createLabel(labelForm[i]),gbc);
                    if(i==2){
                        gbc.gridx = 1;
                        gbc.gridy = i;
                        String[] chucvu = {"Quản trị","Nhân viên kho","Quản lý kho"}; 
                        panelCenter.add(createComboBox(chucvu,i),gbc);      
                    }else if(i==3){
                        gbc.gridx = 1;
                        gbc.gridy = i;
                        String[] gioitinh = {"Nam","Nữ"};
                        panelCenter.add(createComboBox(gioitinh,i),gbc); 
                    }else if(i==4){
                        gbc.gridx = 1;
                        gbc.gridy = i;
                        panelCenter.add(createDateChooser(i),gbc);      
                    }else if(i==6){
                        gbc.gridx = 1;
                        gbc.gridy = i;
                        String[] str = {"Kho ADV","Kho THD","Kho NVC","Kho LHP"};
                        panelCenter.add(createComboBox(str,i),gbc);      
                    }else if(i==7){
                        gbc.gridx = 1;
                        gbc.gridy = i;
                        panelCenter.add(createSpinner(0,0,192,8,i),gbc);      
                    }else if(i==8){
                        gbc.gridx = 1;
                        gbc.gridy = i;
                        panelCenter.add(createSpinner(1000000,1000000,10000000,100000,i),gbc);      
                    }else{
                        gbc.gridx = 1;
                        gbc.gridy = i;
                        panelCenter.add(createTextField(i),gbc);
                    }
                }
                
                btn.setPreferredSize(new Dimension(300,50));
                btn.setFont(new Font("Monospace",Font.BOLD,16));
                btn.setForeground(Color.white);
                btn.setForeground(Color.white);
                btn.setBackground(Color.red);
                btn.setFocusable(false);
                btn.setBorder(null);       
                btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btn.addMouseListener(this);

                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JTextField textFieldMaNV = (JTextField)atributeNV[0];
                        String MaNV = textFieldMaNV.getText();
                        JTextField textFieldTenNV = (JTextField)atributeNV[1];
                        String TenNV = textFieldTenNV.getText();
                        JComboBox comboBoxMaCV = (JComboBox)atributeNV[2];
                        String MaCV;
                        if(comboBoxMaCV.getSelectedItem()=="Quản trị"){
                            MaCV = "CV00";
                        }else if(comboBoxMaCV.getSelectedItem()=="Quản lý kho"){
                            MaCV = "CV01";
                        }else{
                            MaCV = "CV02";
                        }
                        JComboBox comboBoxGioiTinh = (JComboBox)atributeNV[3];
                        String GioiTinh = (String)comboBoxGioiTinh.getSelectedItem();
                        JDateChooser dateChooserNgaySinh = (JDateChooser)atributeNV[4];
                        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        // String ngaySinh = sdf.format(dateChooserNgaySinh.getDate());
                        Date NgaySinh = (Date)dateChooserNgaySinh.getDate();
                        java.sql.Date sqlDate = new java.sql.Date(NgaySinh.getTime());
                        
                        JTextField textFieldDiaChi = (JTextField)atributeNV[5];
                        String DiaChi = textFieldDiaChi.getText();
                        JComboBox comboBoxKhoLamViec = (JComboBox)atributeNV[6];
                        String MaKho;
                        if(comboBoxKhoLamViec.getSelectedItem()=="Kho ADV"){
                            MaKho = "K01";
                        }else if(comboBoxKhoLamViec.getSelectedItem()=="Kho THD"){
                            MaKho = "K02";
                        }else if(comboBoxKhoLamViec.getSelectedItem()=="Kho NVC"){
                            MaKho = "K03";
                        }else{
                            MaKho = "K04";
                        }
                        JSpinner spinnerSoGioLam = (JSpinner)atributeNV[7];
                        Integer SoGioLam = (Integer)spinnerSoGioLam.getValue();
                        JSpinner spinnerLuongCoBan = (JSpinner)atributeNV[8];
                        Float LuongCoBan = (Float)(spinnerLuongCoBan.getValue());
                        if (MaNV.isEmpty() || TenNV.isEmpty() || MaCV.isEmpty() || GioiTinh.isEmpty() || NgaySinh==null || DiaChi.isEmpty() || MaKho.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        DataAccessLayer<NhanvienMD> NhanVienDAO = new DataAccessLayer<>(master,NhanvienMD.class);
                        ArrayList<NhanvienMD> DSNV = new ArrayList<NhanvienMD>();
                        DSNV.add(new NhanvienMD(MaNV, TenNV, MaCV, GioiTinh, sqlDate, DiaChi, MaKho, SoGioLam, LuongCoBan));
                        NhanVienDAO.add(DSNV);
                        panelTable.remove(scrollPane);
                        SetTable(master.getDataQuery(sqlDSNV));
                        panelTable.add(scrollPane);
                        JOptionPane.showMessageDialog(null, "Thêm thành công");
                        dialog.dispose();
                    }
                });

                dialog.add(panelDialog);
                dialog.setPreferredSize(new Dimension(1000,650));
                dialog.pack();
                dialog.setLocationRelativeTo(parentFrame);
                dialog.setVisible(true);
                return dialog;
            }

    private JLabel createLabel(String text){
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(150, 30)); // đặt kích thước ưu tiên cho nhãn
        label.setFont(new Font("Poppins",Font.BOLD,13));
        return label;
    }
    private JTextField createTextField(int index) {
        if (atributeNV == null) {
            atributeNV = new Object[labelForm.length];
        }
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(250, 30));
        textField.setFont(new Font("Monospace", Font.BOLD, 13));
        atributeNV[index] = textField;
        textField.setBorder(null);
        return textField;
    }
    private JDateChooser createDateChooser(int index){
        JDateChooser datecChooser = new JDateChooser();
        datecChooser.setPreferredSize(new Dimension(150, 30));
        datecChooser.setFont(new Font("Poppins",Font.PLAIN,15));
        atributeNV[index] = datecChooser;
        return datecChooser;
    }
    private JComboBox createComboBox(String[] str,int index){
        JComboBox comboBox = new JComboBox<>(str);
        comboBox.setPreferredSize(new Dimension(150, 30));
        atributeNV[index] = comboBox;
        return comboBox;
    }
    private JSpinner createSpinner(int value,int minimum,int maximum,int stepSize,int index){
        SpinnerModel model = new SpinnerNumberModel(value,minimum,maximum,stepSize);
        JSpinner spinner = new JSpinner(model);
        spinner.setPreferredSize(new Dimension(150, 35));
        spinner.setFont(new Font("Poppins",Font.PLAIN,15));
        atributeNV[index] = spinner;
        return spinner;
    }
    private JLabel createLabelInfo(String text){
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(300, 25)); // đặt kích thước ưu tiên cho nhãn
        label.setFont(new Font("Monospace",Font.BOLD,13));
        return label;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==searchButton){
            searchButton.setBackground(new Color(223,18,133));
            searchButton.setForeground(Color.white);
        }else if(e.getSource()==addButton){
            addButton.setBackground(new Color(223,18,133));
            addButton.setForeground(Color.white);
        }else if(e.getSource()==infoButton){
            infoButton.setBackground(new Color(223,18,133));
            infoButton.setForeground(Color.white);
        }else if(e.getSource()==salaryButton){
            salaryButton.setBackground(new Color(223,18,133));
            salaryButton.setForeground(Color.white);
        }else if(e.getSource()==btn){
            btn.setBackground(Color.GRAY);
        }
    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==searchButton){
            searchButton.setBackground(new Color(255,197,70));
            searchButton.setForeground(Color.black);
        }else if(e.getSource()==addButton){
            addButton.setBackground(new Color(0,255,119));
            addButton.setForeground(Color.black);
        }else if(e.getSource()==infoButton){
            infoButton.setBackground(new Color(0,255,119));
            infoButton.setForeground(Color.black);
        }else if(e.getSource()==salaryButton){
            salaryButton.setBackground(new Color(0,255,119));
            salaryButton.setForeground(Color.black);
        }else if(e.getSource()==btn){
            btn.setBackground(Color.red);
        }
    }
    private void SetTable(DataSet ds){
        if(ds!=null){
            if(this.scrollPane!=null){
                this.remove(scrollPane);
            }
            TableModel tbModel = new DefaultTableModel(ds.getData(),ds.getColumnLabel());

            JTable tableDS = new JTable(tbModel){
                public boolean isCellEditable(int row,int column){
                    return false;
                }
            };
            tableDS.getColumnModel().removeColumn(tableDS.getColumnModel().getColumn(7));
            tableDS.getColumnModel().removeColumn(tableDS.getColumnModel().getColumn(7));
            String[] arr = new String[9];     
            tableDS.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e){
                    int rowIndex = tableDS.getSelectedRow();
                    for(int i=0;i<arr.length;i++){

                        if(i==7){
                            arr[i] = tableDS.getModel().getValueAt(rowIndex, i).toString();
                        }else if(i==8){
                            float temp = (float) tableDS.getModel().getValueAt(rowIndex, i);
                            int tempInt = (int) Math.floor(temp);
                            String formattedNum = String.format("%,d", tempInt).replace(",", ".");
                            arr[i] = String.valueOf(formattedNum)+" VNĐ";
                        }else{
                            arr[i] = tableDS.getValueAt(rowIndex, i).toString();
                        }
                    }
                    panelDefault.setVisible(false);
                    panelInfo.removeAll();    
                    panelInfo.add(labelTitle);
                    if(panelSalary.isVisible()){
                        panelInfo.setVisible(false);
                    }else{
                        panelInfo.setVisible(true);
                    }
                    
                    for(int i=0;i<labelForm.length;i++){
                        JLabel label = createLabelInfo(labelForm[i] + " " + arr[i]);
                        panelInfo.add(label);
                    }
            
                    // Cập nhật lại giao diện người dùng
                    panelInfo.revalidate();
                    panelInfo.repaint();
                }
            });    
            TableColumnModel columnModel = tableDS.getColumnModel();
            for(int i=0;i<7;i++){
                columnModel.getColumn(i).setResizable(false);
            }
            tableDS.getTableHeader().setReorderingAllowed(false);
            tableDS.setRowHeight(30);

            JTableHeader header = tableDS.getTableHeader();
            header.setPreferredSize(new Dimension(40, 35));
            
            this.rowSorter = new TableRowSorter<>(tbModel);
            tableDS.setRowSorter(rowSorter);

            this.scrollPane = new JScrollPane(tableDS);
            tableDS.setPreferredScrollableViewportSize(new Dimension(800,0));

            this.add(scrollPane);
            
            // Revalidate and repaint the frame
            this.revalidate();
            this.repaint();
        }
    }
}
