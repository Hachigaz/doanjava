package Panel;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

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
    JPanel searchPanel;
    public String[] labelForm = {"Mã nhân viên:","Tên nhân viên:","Mã chức vụ:","Giới tính:","Ngày sinh:","Kho làm việc:"};
    public NhanVien(DataSet ds){
        this.setLayout(new BorderLayout());
        
        searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        table = new JTable(ds.getData(),ds.getColumnName());
        // ngăn người dùng kéo thả thay đổi kích thước cột
        TableColumnModel columnModel = table.getColumnModel();
        for(int i=0;i<7;i++){
            columnModel.getColumn(i).setResizable(false);
        }
        // ngăn người kéo thả thay đổi vị trí cột
        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);
        // ngăn chỉnh sửa dữ liệu
        table.setEnabled(false);

        table.setRowHeight(30);
        scrollPane = new JScrollPane(table);
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setBackground(Color.BLACK);
        table.setPreferredScrollableViewportSize(new Dimension(800,500));
        // scrollPane.setPreferredSize(new Dimension(800,500));

        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(300,30));
        searchField.setFont(new Font("Monospace",Font.PLAIN,15));
        searchField.setForeground(Color.black);
        searchField.setFocusable(false);
        // searchField.addMouseListener(this);
        // searchField.addFocusListener(new FocusListener() {
        //     @Override
        //     public void focusGained(FocusEvent e) {
        //         if (searchField.getText().equals("Nhập tên nhân viên muốn tìm...")) {
        //             searchField.setText("");
        //             searchField.setForeground(Color.BLACK);
        //             searchField.requestFocusInWindow();
        //         }
        //     }
        //     @Override
        //     public void focusLost(FocusEvent e) {
        //         if (searchField.getText().isEmpty()) {
        //             searchField.setForeground(Color.GRAY);
        //             searchField.setText("Nhập tên nhân viên muốn tìm...");
        //             searchField.setFocusable(false);
        //         }        
        //     } 
        // });

        searchButton = new JButton("Tìm kiếm");
        searchButton.setBorder(null);
        searchButton.setPreferredSize(new Dimension(80,40));
        searchButton.setFocusable(false);
        searchButton.setBackground(new Color(255,197,70));
        searchButton.setForeground(Color.black);
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.addMouseListener(this);

        addButton = new JButton("Thêm nhân viên");
        addButton.setBackground(new Color(0,255,119));
        addButton.setForeground(Color.BLACK);
        addButton.setPreferredSize(new Dimension(140,40));
        addButton.setFocusable(false);
        addButton.setBorder(null);
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // lấy ra đối tượng JFrame cha của NhanVien
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(NhanVien.this);
                JDialog dialog = new JDialog(parentFrame,"Thêm nhân viên",true);

                JPanel panel = new JPanel();
                panel.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();

                gbc.insets = new Insets(10, 10, 10, 10);
                
                for(int i=0;i<labelForm.length;i++){
                    gbc.gridx = 0;
                    gbc.gridy = i;
                    panel.add(createLabel(labelForm[i]),gbc);

                    gbc.gridx = 1;
                    gbc.gridy = i;
                    panel.add(createTextField(),gbc);
                }

                // JLabel lblHoTen = new JLabel("Họ tên:");
                // lblHoTen.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
                // lblHoTen.setFont(new Font("Monospace",Font.BOLD,15));
                // JTextField txtHoTen = new JTextField(20);

                // JLabel lblSoDienThoai = new JLabel("Số điện thoại:");
                // lblSoDienThoai.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
                // lblSoDienThoai.setFont(new Font("Monospace",Font.BOLD,15));
                // JTextField txtSoDienThoai = new JTextField(20);

                // panel.add(lblHoTen);
                // panel.add(txtHoTen);
                // panel.add(lblSoDienThoai);
                // panel.add(txtSoDienThoai);
                
                btn = new JButton("Thêm");
                btn.setPreferredSize(new Dimension(80,50));
                btn.setBackground(Color.red);

                gbc.gridx = 0;
                gbc.gridy = 7;
                panel.add(btn,gbc);

                dialog.add(panel);
                dialog.setPreferredSize(new Dimension(800,500));
                dialog.pack();
                dialog.setLocationRelativeTo(parentFrame);
                dialog.setVisible(true);
            }
        });
        addButton.addMouseListener(this);

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(addButton);

        
        this.add(searchPanel,BorderLayout.NORTH);
        this.add(scrollPane);
    }
    private JLabel createLabel(String text){
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(150, 30)); // đặt kích thước ưu tiên cho nhãn
        label.setFont(new Font("Monospace",Font.BOLD,13));
        return label;
    }
    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(300, 30)); // đặt kích thước ưu tiên cho trường văn bản
        textField.setFont(new Font("Monospace",Font.BOLD,13));
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
