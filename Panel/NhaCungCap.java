package Panel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

import DAL.*;
import SQL.SQLUser;
import misc.DataSet;
import Model.*;

public class NhaCungCap extends JPanel implements MouseListener{
    JTable table;
    JScrollPane scrollPane;
    JScrollBar scrollBar;
    JTextField searchField;
    JButton searchButton;
    JButton addButton;
    JButton btn,btn1;
    JPanel searchPanel,panelTable,panelInfo;
    JLabel labelCombobox;
    JComboBox comboBox;
    JTextField[] textFields;
    JTextField[] textField;
    String a;
    Object[] obj;
    String[] add;
    String[] arrange = {"Tên","Chức vụ","Kho làm việc"}; 
    private SQLUser master;
    private Taikhoan_nhanvienMD tkDangNhap;
    DefaultTableModel model;
    TableRowSorter<TableModel> rowSorter;
    private final String sqlDSNV = "select MaCty as 'Mã công ty', TenCty as 'Tên công ty', DiaChi as 'Địa chỉ',SDT as 'Số điện thoại' from cong_ty";
    public String[] labelForm = {"Mã công ty:       ","Tên công ty:     ","Địa chỉ:                  ","Số điện thoại:       "};
    public NhaCungCap(SQLUser master,Taikhoan_nhanvienMD tkdn){
        this.master = master;
        this.tkDangNhap = tkdn;

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1200,500));

        this.SetTable(master.getDataQuery(sqlDSNV));
        panelInfo = new JPanel();
        panelInfo.setPreferredSize(new Dimension(390,0));
        panelInfo.setLayout(new FlowLayout());

        for(int i=0;i<labelForm.length;i++){
            panelInfo.add(createLabelInfo(labelForm[i]));
        }
        panelInfo.add(Box.createHorizontalGlue());
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
                        // Sử dụng Pattern.CASE_INSENSITIVE để không phân biệt chữ hoa/thường
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
                    } catch (PatternSyntaxException ex) {
                        System.err.println("Invalid regex pattern: " + ex.getMessage());
                    }
                }
            }
        });
        searchField.setPreferredSize(new Dimension(300,30));
        searchField.setFont(new Font("Monospace",Font.PLAIN,15));
        searchField.setForeground(Color.black);

        searchButton = new JButton("Tìm kiếm");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String searchText = searchField.getText();
                if (searchText.length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    try {
                        // Sử dụng Pattern.CASE_INSENSITIVE để không phân biệt chữ hoa/thường
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
                    } catch (PatternSyntaxException ex) {
                        System.err.println("Invalid regex pattern: " + ex.getMessage());
                    }
                }
            }
        });
        searchButton.setBorder(null);
        searchButton.setPreferredSize(new Dimension(65,40));
        searchButton.setFocusable(false);
        searchButton.setBackground(new Color(255,197,70));
        searchButton.setForeground(Color.black);
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.addMouseListener(this);

        addButton = new JButton("Thêm công ty");
        addButton.setBackground(new Color(0,255,119));
        addButton.setForeground(Color.BLACK);
        addButton.setPreferredSize(new Dimension(100,40));
        addButton.setFocusable(false);
        addButton.setBorder(null);
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // lấy ra đối tượng JFrame cha của NhanVien
                FormCongty();
            }            
        });
        addButton.addMouseListener(this);


        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(addButton);

        panelTable.add(searchPanel,BorderLayout.NORTH);
        panelTable.add(scrollPane);
        this.add(panelTable,BorderLayout.WEST);
        this.add(panelInfo,BorderLayout.EAST);
        this.setVisible(false);
    }
    public JDialog FormCongty(){
                
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(NhaCungCap.this);
                JDialog dialog = new JDialog(parentFrame,"Thêm công ty",true);

                JPanel panelCenter = new JPanel();
                panelCenter.setLayout(new GridBagLayout());

                JPanel panelTop = new JPanel();
                JLabel title = new JLabel("FORM THÊM CÔNG TY");
                title.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
                title.setFont(new Font("Monospace",Font.BOLD,20));
                panelTop.add(title);

                JPanel panelBottom = new JPanel();
                panelBottom.setBorder(BorderFactory.createEmptyBorder(0,0,50,0));
                btn = new JButton("Thêm");
                panelBottom.add(btn);

                JPanel panelDialog = new JPanel();
                panelDialog.setPreferredSize(new Dimension(800,500));
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

                    gbc.gridx = 1;
                    gbc.gridy = i;
                    panelCenter.add(createTextField(i),gbc);
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
                        // Lấy dữ liệu từ các trường nhập liệu
                        String maCty = textFields[0].getText();
                        String tenCty = textFields[1].getText();
                        String diaChi = textFields[2].getText();
                        String sdt = textFields[3].getText();
                        // Kiểm tra tính hợp lệ của dữ liệu
                        if (maCty.isEmpty() || tenCty.isEmpty() || diaChi.isEmpty() || sdt.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        DataAccessLayer<CongtyMD> ctDAO = new DataAccessLayer<>(master, CongtyMD.class);
                        ArrayList<CongtyMD> dsCT = new ArrayList<CongtyMD>();
                        dsCT.add(new CongtyMD(maCty, tenCty, diaChi, sdt));       
                        ctDAO.add(dsCT);
                        panelTable.remove(scrollPane);
                        SetTable(master.getDataQuery(sqlDSNV));
                        panelTable.add(scrollPane);
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
        public JDialog FormSuaCongty(){
                
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(NhaCungCap.this);
                JDialog dialog = new JDialog(parentFrame,"Sửa công ty",true);

                JPanel panelCenter = new JPanel();
                panelCenter.setLayout(new GridBagLayout());

                JPanel panelTop = new JPanel();
                JLabel title = new JLabel("FORM SỬA CÔNG TY");
                title.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
                title.setFont(new Font("Monospace",Font.BOLD,20));
                panelTop.add(title);

                JPanel panelBottom = new JPanel();
                panelBottom.setBorder(BorderFactory.createEmptyBorder(0,0,50,0));
                btn1 = new JButton("LƯU");
                panelBottom.add(btn1);

                JPanel panelDialog = new JPanel();
                panelDialog.setPreferredSize(new Dimension(800,500));
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

                    gbc.gridx = 1;
                    gbc.gridy = i;
                    panelCenter.add(createTextField(i),gbc);
                    
                }
                
                btn1.setPreferredSize(new Dimension(300,50));
                btn1.setFont(new Font("Monospace",Font.BOLD,16));
                btn1.setForeground(Color.white);
                btn1.setForeground(Color.white);
                btn1.setBackground(Color.red);
                btn1.setFocusable(false);
                btn1.setBorder(null);       
                btn1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btn1.addMouseListener(this);
                btn1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        // Lấy dữ liệu từ các trường nhập liệu
                        String maCty = textFields[0].getText();
                        String tenCty = textFields[1].getText();
                        String diaChi = textFields[2].getText();
                        String sdt = textFields[3].getText();
                        // Kiểm tra tính hợp lệ của dữ liệu
                        if (maCty.isEmpty() || tenCty.isEmpty() || diaChi.isEmpty() || sdt.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        DataAccessLayer<CongtyMD> ctDAO = new DataAccessLayer<>(master, CongtyMD.class);
                        
                        ctDAO.update(new CongtyMD(maCty,tenCty,diaChi,sdt),"MaCty="+a);

                        
                        panelTable.remove(scrollPane);
                        SetTable(master.getDataQuery(sqlDSNV));
                        panelTable.add(scrollPane);
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
        label.setFont(new Font("Monospace",Font.BOLD,13));
        return label;
    }
    private JTextField createTextField(int index) {
        if (textFields == null) {
            textFields = new JTextField[labelForm.length];
        }
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(300, 30));
        textField.setFont(new Font("Monospace", Font.BOLD, 13));
        textFields[index] = textField;
        return textField;
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
        }else if(e.getSource()==btn){
            btn.setBackground(Color.red);
        }
    }
     void SetTable(DataSet ds){
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
            String[] arr = new String[4];     
            JButton sua = new JButton("Sửa");
            sua.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FormSuaCongty();
            
        }
            });
            JButton xoa = new JButton("Xóa", null);
            xoa.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int rowIndex = tableDS.getSelectedRow();
                    
                    arr[0] = tableDS.getValueAt(rowIndex, 0).toString();
                    JFrame p = (JFrame) SwingUtilities.getWindowAncestor(NhaCungCap.this);
                    int result = JOptionPane.showConfirmDialog(p,
                    "Bạn có muốn xóa dữ liệu này?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                // Xử lý xóa dữ liệu
                DataAccessLayer<CongtyMD> ctDAO = new DataAccessLayer<>(master, CongtyMD.class);               
                ctDAO.remove("MaCty = " + arr[0] );                        
                panelTable.remove(scrollPane);
                SetTable(master.getDataQuery(sqlDSNV));
                panelTable.add(scrollPane);
                panelInfo.revalidate();
                panelInfo.repaint();
            } else {
                // Xử lý không xóa dữ liệu
            }
        }
            });
            tableDS.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e){
                    int rowIndex = tableDS.getSelectedRow();
                    
                    arr[0] = tableDS.getValueAt(rowIndex, 0).toString();
                    arr[1] = tableDS.getValueAt(rowIndex, 1).toString();
                    arr[2] = tableDS.getValueAt(rowIndex, 2).toString();
                    arr[3] = tableDS.getValueAt(rowIndex, 3).toString();
                    panelInfo.removeAll();    
                     for(int i=0;i<labelForm.length;i++){
                        JLabel label = createLabelInfo(labelForm[i] + " " + arr[i]);
                         panelInfo.add(label);
                        
                     }
                     JPanel panelButtons = new JPanel();
                    panelButtons.setLayout(new FlowLayout());
                    panelButtons.add(sua);
                    panelButtons.add(xoa);
                    panelInfo.add(panelButtons);
                    // Cập nhật lại giao diện người dùng
                    panelInfo.revalidate();
                    panelInfo.repaint();
                    a=arr[0];
                }
            });    
            TableColumnModel columnModel = tableDS.getColumnModel();
            for(int i=0;i<4;i++){
                columnModel.getColumn(i).setResizable(false);
            }
            tableDS.getTableHeader().setReorderingAllowed(false);
            tableDS.setRowHeight(30);
            
            this.rowSorter = new TableRowSorter<>(tbModel);
            tableDS.setRowSorter(rowSorter);

            this.scrollPane = new JScrollPane(tableDS);
            tableDS.setPreferredScrollableViewportSize(new Dimension(800,400));


            
            this.add(scrollPane);
            this.revalidate();
            this.repaint();
        }
    }
}
