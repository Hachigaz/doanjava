package Panel.NhanVien;

import Panel.SubPanel.LocPanel;
import Panel.SubPanel.TablePanel;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import DAL.DataAccessLayer;

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
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import Model.Custom.DSNhanVienMD;

public class NhanVien extends JPanel implements MouseListener{
    public DefaultTableCellRenderer center;
    JTable table;
    JScrollBar scrollBar;
    JTextField searchField;
    JButton searchButton,addButton,infoButton,salaryButton;
    JButton btn;
    JPanel searchPanel,panelTable2,panelRight,panelInfo,panelSalary,panelDefault,panelTable;
    JLabel labelCombobox,labelTitle,labelDefault;
    JComboBox comboBox;
    JTextField[] textFields;
    // Object[] obj;
    // String[] add;
    String[] arrange = {"Tên","Chức vụ","Kho làm việc"}; 
    public SQLUser master;
    private Taikhoan_nhanvienMD tkDangNhap;
    private JPanel panelLoc;
    private TablePanel panelDanhSach;
    
    DefaultTableModel model;
    TableRowSorter<TableModel> rowSorter;
    TableModel tableModel;
    TableModel tableNewModel;
    private final String sqlDSNV = "select nhanvien.MaNV as 'Mã nhân viên', nhanvien.TenNV as 'Tên nhân viên', chucvu.TenCV as 'Chức vụ', nhanvien.GioiTinh as 'Giới tính', nhanvien.NgaySinh as 'Ngày sinh', nhanvien.DiaChi as 'Địa chỉ', kho.TenKho as 'Kho làm việc',nhanvien.SoGioLamViec as 'Số giờ làm việc',nhanvien.LuongCoBan as 'Lương cơ bản' from nhanvien , chucvu, kho where nhanvien.MaCV = chucvu.MaCV and kho.MaKho = nhanvien.Kho_lam_viec";
    
    public String[] labelForm = {"Mã nhân viên:       ","Tên nhân viên:     ","Chức vụ:               ","Giới tính:                ","Ngày sinh:            ","Địa chỉ:                  ","Kho làm việc:       ","Số giờ làm:           ","Lương cơ bản:    "};
    public String[] btnChucNang = {};
    public NhanVien(Dimension d){

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1200,590));

        panelRight = new JPanel();
        panelRight.setPreferredSize(new Dimension(370,500));
        panelRight.setLayout(new FlowLayout());

        panelDanhSach = new TablePanel();
        panelDanhSach.setPreferredSize(new Dimension(830, this.getPreferredSize().height-searchPanel.HEIGHT-2));

        updateTable();

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
                // FormNhanVien();
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
        panelTable.add(panelDanhSach);

        panelRight.add(panelInfo);
        panelRight.add(panelSalary);
        panelRight.add(panelDefault);

        this.add(panelTable,BorderLayout.WEST);
        this.add(panelRight,BorderLayout.EAST);
        this.setVisible(false);
        btn.setPreferredSize(new Dimension(300,50));
        btn.setFont(new Font("Monospace",Font.BOLD,16));
        btn.setForeground(Color.white);
        btn.setForeground(Color.white);
        btn.setBackground(Color.red);
        btn.setFocusable(false);
        btn.setBorder(null);       
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(this);
    }
        private JLabel createLabelInfo(String text){
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(300, 25)); // đặt kích thước ưu tiên cho nhãn
        label.setFont(new Font("Monospace",Font.BOLD,13));
        return label;
    }
    
    public void updateTable(){
        DataAccessLayer<DSNhanVienMD> nhanvienmd = new DataAccessLayer<DSNhanVienMD>(master,DSNhanVienMD.class);
        ArrayList<DSNhanVienMD> DSNV = nhanvienmd.getTable();
        tableModel = new DefaultTableModel(Model.to2DArray(DSNV),nhanvienmd.getReturnedColumnLabel()){
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        panelDanhSach.SetTable(tableModel,null);
        JTable tableTemp = panelDanhSach.getTableDS();
        tableTemp.setPreferredSize(this.getPreferredSize());
        tableTemp.getColumnModel().removeColumn(tableTemp.getColumnModel().getColumn(7));
        tableTemp.getColumnModel().removeColumn(tableTemp.getColumnModel().getColumn(7));
        tableNewModel = tableTemp.getModel();
        String[] arr = new String[9];     
        tableTemp.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                int rowIndex = tableTemp.getSelectedRow();
                for(int i=0;i<arr.length;i++){

                    if(i==7){
                        arr[i] = tableTemp.getModel().getValueAt(rowIndex, i).toString();
                    }else if(i==8){
                        float temp = (float) tableTemp.getModel().getValueAt(rowIndex, i);
                        int tempInt = (int) Math.floor(temp);
                        String formattedNum = String.format("%,d", tempInt).replace(",", ".");
                        arr[i] = String.valueOf(formattedNum)+" VNĐ";
                    }else{
                        arr[i] = tableTemp.getValueAt(rowIndex, i).toString();
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
}
