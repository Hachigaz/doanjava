package Panel;

import Panel.SubPanel.LocPanel;
import Panel.SubPanel.TablePanel;

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
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
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
    JButton searchButton,addButton,infoButton,filterButton;
    JButton btn;
    JPanel searchPanel,panelTable,panelRight,panelInfo,panelFilter;
    JLabel labelCombobox,labelTitle;
    JComboBox comboBox;
    JTextField[] textFields;
    // Object[] obj;
    // String[] add;
    String[] arrange = {"Tên","Chức vụ","Kho làm việc"}; 
    private SQLUser master;
    private Taikhoan_nhanvienMD tkDangNhap;
    private JPanel panelLoc;
    private TablePanel panelDanhSach;

    DefaultTableModel model;
    TableRowSorter<TableModel> rowSorter;
    private final String sqlDSNV = "select nhanvien.MaNV as 'Mã nhân viên', nhanvien.TenNV as 'Tên nhân viên', chucvu.TenCV as 'Chức vụ', nhanvien.GioiTinh as 'Giới tính', nhanvien.NgaySinh as 'Ngày sinh', nhanvien.DiaChi as 'Địa chỉ', kho.TenKho as 'Kho làm việc' from nhanvien , chucvu, kho where nhanvien.MaCV = chucvu.MaCV and kho.MaKho = nhanvien.Kho_lam_viec";
    public String[] labelForm = {"Mã nhân viên:       ","Tên nhân viên:     ","Chức vụ:               ","Giới tính:                ","Ngày sinh:            ","Địa chỉ:                  ","Kho làm việc:       "};
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

        panelInfo = new JPanel();
        panelInfo.setPreferredSize(new Dimension(370,400));
        panelInfo.setLayout(new FlowLayout());
        panelInfo.setVisible(false);

        panelInfo.add(labelTitle);

        for(int i=0;i<labelForm.length;i++){
            panelInfo.add(createLabelInfo(labelForm[i]));
        }

        panelFilter = new JPanel();
        panelFilter.setBackground(Color.red);
        panelFilter.setVisible(true);
        panelFilter.setPreferredSize(new Dimension(370, 600));

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
        searchField.setPreferredSize(new Dimension(300,30));
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
        searchButton.setPreferredSize(new Dimension(35,35));
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
        addButton.setPreferredSize(new Dimension(35,35));
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
        infoButton.setPreferredSize(new Dimension(35,35));
        infoButton.setFocusable(false);
        infoButton.setBorder(null);
        infoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelInfo.setVisible(true);
                panelFilter.setVisible(false);
            } 
        });
        infoButton.addMouseListener(this);

        
        ImageIcon iconFilter = new ImageIcon("res/img/filter.png");
        Image imgIconFilter = iconFilter.getImage();
        Image newImgFilter = imgIconFilter.getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIconFilter = new ImageIcon(newImgFilter);
        filterButton = new JButton(newIconFilter);
        filterButton.setToolTipText("Lọc");
        filterButton.setBackground(new Color(0,255,119));
        filterButton.setPreferredSize(new Dimension(35,35));
        filterButton.setFocusable(false);
        filterButton.setBorder(null);
        filterButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelInfo.setVisible(false);
                panelFilter.setVisible(true);
            } 
        });
        filterButton.addMouseListener(this);

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(addButton);
        searchPanel.add(infoButton);
        searchPanel.add(filterButton);

        panelTable.add(searchPanel,BorderLayout.NORTH);
        panelTable.add(scrollPane);

        panelRight.add(panelInfo);
        panelRight.add(panelFilter);

        this.add(panelTable,BorderLayout.WEST);
        this.add(panelRight,BorderLayout.EAST);
        this.setVisible(false);
    }
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
                
                // btn.addActionListener(new ActionListener() {
                //     @Override
                //     public void actionPerformed(ActionEvent e) {
                //         // TODO Auto-generated method stub
                //         if(model instanceof DefaultTableModel){
                //             model = (DefaultTableModel) table.getModel();
                //             add = new String[labelForm.length];
                //             obj = new Object[labelForm.length];
                //             for(int i=0;i<labelForm.length;i++){
                //                 add[i] = textFields[i].getText();
                //                 obj[i] = (Object)add[i];
                //             }
                //             table.setModel(model);
                //             model.addRow(obj);
                //             JOptionPane.showMessageDialog(null, "Thêm thành công");
                //             dialog.dispose();
                //         }else{
                //             JOptionPane.showMessageDialog(null, "Lỗi: Đối tượng mô hình bảng không phải là một đối tượng DefaultTableModel");
                //         }     
                //     }
                // });
                // btn.addActionListener(new ActionListener() {
                //     @Override
                //     public void actionPerformed(ActionEvent e) {
                //         TableModel model = table.getModel();
                //         if (table.getModel() instanceof DefaultTableModel) {
                //             DefaultTableModel defaultModel = (DefaultTableModel) model;
                //             Object[] row = new Object[labelForm.length];
                //             for (int i = 0; i < labelForm.length; i++) {
                //                 row[i] = textFields[i].getText();
                //             }
                //             defaultModel.addRow(row);
                //             JOptionPane.showMessageDialog(null, "Thêm thành công");
                //             dialog.dispose();
                //         } else {
                //             JOptionPane.showMessageDialog(null, "Lỗi: Đối tượng mô hình bảng không phải là một đối tượng DefaultTableModel");
                //         }
                //     }
                // });

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
        }else if(e.getSource()==infoButton){
            infoButton.setBackground(new Color(223,18,133));
            infoButton.setForeground(Color.white);
        }else if(e.getSource()==filterButton){
            filterButton.setBackground(new Color(223,18,133));
            filterButton.setForeground(Color.white);
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
        }else if(e.getSource()==filterButton){
            filterButton.setBackground(new Color(0,255,119));
            filterButton.setForeground(Color.black);
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
            String[] arr = new String[7];     
            tableDS.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e){
                    int rowIndex = tableDS.getSelectedRow();
                    
                    arr[0] = tableDS.getValueAt(rowIndex, 0).toString();
                    arr[1] = tableDS.getValueAt(rowIndex, 1).toString();
                    arr[2] = tableDS.getValueAt(rowIndex, 2).toString();
                    arr[3] = tableDS.getValueAt(rowIndex, 3).toString();
                    arr[4] = tableDS.getValueAt(rowIndex, 4).toString();
                    arr[5] = tableDS.getValueAt(rowIndex, 5).toString();
                    arr[6] = tableDS.getValueAt(rowIndex, 6).toString();
                    panelInfo.removeAll();    
                    panelInfo.add(labelTitle);
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
