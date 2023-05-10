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

import BLL.CongTy2BLL;
import BLL.ThongTinSPBLL;
import Panel.ThongTinSP.*;
import DAL.DataAccessLayer;
import DTO.Model;
import DTO.NhanvienMD;
import DTO.Custom.DSNhanVienMD;
import DTO.CongtyMD;

import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import Panel.Form.Form;
import Panel.SubPanel.TablePanel;
import misc.ThongBaoDialog;

public class CongTy2UI extends JPanel implements MouseListener{
CongTy2BLL CongTy2BLL = new CongTy2BLL();
    private ThongTinSPUI spui ;
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
    private JTextField searchField;
    public static JButton searchButton,addButton,infoButton,SPButton;
    private JButton editButton,deleteButton;
    private TableRowSorter<TableModel> rowSorter;
    private TableModel tableDanhSach;
    private Object[] atributeNV;
    private ArrayList<JButton> btns = new ArrayList<JButton>();
    private Form form;
    public String[] labelForm = {"Mã công ty:       ","Tên công ty:     ","Địa chỉ:               ","SDT:                "};
    public CongTy2UI(Dimension d){
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
        deleteButton.setForeground(Color.BLACK);
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.setBackground(new Color(0,255,119));
        deleteButton.setBorder(null);
        deleteButton.addActionListener(deleteAction);

        editButton = new JButton("Sửa");
        editButton.setPreferredSize(new Dimension(100,40));
        editButton.setForeground(Color.BLACK);
        editButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        editButton.setBackground(new Color(0,255,119));
        editButton.setBorder(null);
        editButton.addActionListener(editAction);

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
        addButton.setToolTipText("Thêm công ty");
        addButton.setBackground(new Color(0,255,119));
        addButton.setPreferredSize(new Dimension(45,45));
        addButton.setFocusable(false);
        addButton.setBorder(null);
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.addActionListener(addAction);
        addButton.addMouseListener(this);

        SPButton = new JButton( "XemSp");
        SPButton.setEnabled(false);
        SPButton.setBackground(new Color(0,255,119));
        SPButton.setPreferredSize(new Dimension(45,45));
        SPButton.setFocusable(false);
        SPButton.setBorder(null);
        SPButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        SPButton.addActionListener(CheckAction);
        SPButton.addMouseListener(this);

        panelSearch.add(searchField);
        panelSearch.add(searchButton);
        panelSearch.add(addButton);
        panelSearch.add(SPButton);

        panelDanhSach.setPreferredSize(new Dimension(830, 520));
        panelDanhSach.setOpaque(true);

        panelTable.add(panelSearch,BorderLayout.NORTH);
        panelTable.add(panelDanhSach);

        this.add(panelRight,BorderLayout.EAST);
        this.add(panelTable,BorderLayout.WEST);

        String[] columnNames = {"Mã Công ty","Tên","Địa chỉ","SDT"};
        ArrayList<CongtyMD> DanhSachCT = CongTy2BLL.getDSCT(columnNames);
        tableDanhSach = new DefaultTableModel(Model.to2DArray(DanhSachCT),columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        panelDanhSach.SetTable(tableDanhSach, null);
        tableTemp = panelDanhSach.getTableDS();
        tableTemp.addMouseListener(actionInfo);

        panelDefault = new JPanel();
        labelDefault = new JLabel("Chọn công ty mà\n bạn muốn xem thông tin");
        labelDefault.setFont(new Font("Poppins",Font.PLAIN,18));
        panelDefault.setBorder(BorderFactory.createEmptyBorder(100,0,0,0));
        panelDefault.add(labelDefault);

        // panelSalary = new JPanel();
        // panelSalary.setBackground(Color.red);
        // panelSalary.setVisible(false);
        // panelSalary.setPreferredSize(new Dimension(370, 600));

        // panelRight.add(panelSalary);
        panelRight.add(panelDefault);

        // panelTable.setOpaque(true);
        // panelRight.setBackground(Color.BLUE);
        // panelRight.setOpaque(true);
        // panelSearch.setOpaque(true);
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
    private TableModel currentTableDS;
    ActionListener editButtonAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int rowIndex = tableTemp.getSelectedRow();
            arr[0] = tableTemp.getValueAt(rowIndex, 0).toString();
            if(form.check()==false){
                JOptionPane.showMessageDialog(form, "Mời bạn nhập đầy đủ thông tin");
            }else{
                String[] data = form.getData();
                CongTy2BLL.xoaCT("MaCty = "+arr[0]);
                CongTy2BLL.themCTmoi(new CongtyMD(data[0],data[1],data[2],data[3]));
                
               
                String[] columnNames = {"Mã công ty","Tên","Địa chỉ","SDT"};
                currentTableDS = new DefaultTableModel(Model.to2DArray(CongTy2BLL.getDSCT(), "MaCty","TenCty","DiaChi","SDT"), columnNames){
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
    };
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
            if(form.check()==false){
                JOptionPane.showMessageDialog(form, "Mời bạn nhập đầy đủ thông tin");
            }else{
                
                String[] data = form.getData();
                if (data[0].matches("Cty_[a-zA-Z]{3}")) {
                    CongTy2BLL.themCTmoi(new CongtyMD(data[0],data[1],data[2],data[3]));

                    String[] columnNames = {"Mã công ty","Tên","Địa chỉ","SDT"};
                
                    currentTableDS = new DefaultTableModel(Model.to2DArray(CongTy2BLL.getDSCT(),"MaCty","TenCty","DiaChi","SDT"),columnNames){
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
        
                } else {
                    // Nếu MaCty không đúng định dạng, hiển thị thông báo lỗi
                    JOptionPane.showMessageDialog(null, "MaCty không đúng định dạng");
                    
                }
                ;
                
            }        
        }
    };

    String[] arr = new String[4];
    ActionListener deleteAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int rowIndex = tableTemp.getSelectedRow();
            arr[0] = tableTemp.getValueAt(rowIndex, 0).toString();
            arr[1] = tableTemp.getValueAt(rowIndex, 1).toString();
            int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa công ty "+arr[1]+" không?", "Xác nhận xóa dữ liệu", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                CongTy2BLL.xoaCT("MaCty = "+arr[0]);
                String[] columnNames = {"Mã công ty","Tên","Địa chỉ","SDT"};
                currentTableDS = new DefaultTableModel(Model.to2DArray(CongTy2BLL.getDSCT(),"MaCty","TenCty","DiaChi","SDT"),columnNames){
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
            arr[3] = tableTemp.getValueAt(rowIndex,3).toString();
            editForm();
        }
    };
    
    public void editForm(){
        Window window = SwingUtilities.getWindowAncestor(this);
        form = new Form((JFrame) window, editButtonAction);
        form.addButton.setText("Sửa");
        form.textTenCty.setText(arr[1]);
        form.textMaCty.setText(arr[0]);
        form.textSDT.setText(arr[3]);
        form.textDiaChi.setText(arr[2]);
        form.setVisible(true);
    }   
    
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
            SPButton.setEnabled(true);
            
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
    ActionListener CheckAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //new ThongTinSPUI("Cty_ABC");
            new ThongTinSPUI(arr[0]);
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

    
}
