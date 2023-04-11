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
import java.util.Arrays;

import javax.swing.BorderFactory;
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
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import SQL.DataSet;
import SQL.SQLUser;

public class NhanVien extends JPanel implements MouseListener{
    JTable table;
    JScrollPane scrollPane;
    JScrollBar scrollBar;
    JTextField searchField;
    JButton searchButton;
    JButton addButton;
    JButton btn;
    JPanel searchPanel,panelTable,panelInfo;
    JLabel labelCombobox;
    JComboBox comboBox;
    JTextField[] textFields;
    Object[] obj;
    String[] add;
    String[] arrange = {"Tên","Chức vụ","Kho làm việc"}; 
    SQLUser handler;
    DefaultTableModel model;
    public String[] labelForm = {"Mã nhân viên:","Tên nhân viên:","Mã chức vụ:","Giới tính:","Ngày sinh:","Địa chỉ","Kho làm việc:"};
    public NhanVien(DataSet ds){
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1200,500));
        this.setBackground(Color.red);

        panelInfo = new JPanel();
        panelInfo.setPreferredSize(new Dimension(390,0));
        panelInfo.setBackground(Color.BLUE);

        panelTable = new JPanel();
        panelTable.setLayout(new BorderLayout());
        
        searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        table = new JTable(ds.getData(),ds.getColumnLabel()){
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                int rowIndex = table.getSelectedRow();

                String maNV = table.getValueAt(rowIndex, 0).toString();
                String tenNV = table.getValueAt(rowIndex, 1).toString();
                String maChucVu = table.getValueAt(rowIndex, 2).toString();
                String gioiTinh = table.getValueAt(rowIndex, 3).toString();
                String ngaySinh = table.getValueAt(rowIndex, 4).toString();
                String diaChi = table.getValueAt(rowIndex, 5).toString();
                String khoLamViec = table.getValueAt(rowIndex, 6).toString();

                System.out.printf(maNV);
            }
        });
        // ngăn người dùng kéo thả thay đổi kích thước cột
        TableColumnModel columnModel = table.getColumnModel();
        for(int i=0;i<1;i++){
            columnModel.getColumn(i).setResizable(false);
        }

        // ngăn người kéo thả thay đổi vị trí cột
        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);
        header.setPreferredSize(new Dimension(40,25));
        // ngăn chỉnh sửa dữ liệu
        // table.setEnabled(false);
        table.setRowHeight(30);

        scrollPane = new JScrollPane(table);
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setBackground(Color.BLACK);
        table.setPreferredScrollableViewportSize(new Dimension(800,400));
        // scrollPane.setPreferredSize(new Dimension(800,500));

        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(300,30));
        searchField.setFont(new Font("Monospace",Font.PLAIN,15));
        searchField.setForeground(Color.black);

        searchButton = new JButton("Tìm kiếm");
        searchButton.setBorder(null);
        searchButton.setPreferredSize(new Dimension(65,40));
        searchButton.setFocusable(false);
        searchButton.setBackground(new Color(255,197,70));
        searchButton.setForeground(Color.black);
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.addMouseListener(this);

        addButton = new JButton("Thêm nhân viên");
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
                FormNhanVien();
            }            
        });
        addButton.addMouseListener(this);

        labelCombobox = new JLabel("Sắp xếp theo");
        JComboBox<String> comboBox = new JComboBox<>(arrange);

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(addButton);
        searchPanel.add(labelCombobox);
        searchPanel.add(comboBox); 

        panelTable.add(searchPanel,BorderLayout.NORTH);
        panelTable.add(scrollPane);
        this.add(panelTable,BorderLayout.WEST);
        this.add(panelInfo,BorderLayout.EAST);
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
}
