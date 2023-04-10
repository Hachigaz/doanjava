package Panel;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.util.Vector;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import SQL.DataSet;

public class NhaCungCap extends JPanel implements MouseListener{
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
    String[] add;
    //String[] arrange = {"Tên","Chức vụ","Kho làm việc"}; 
    public String[] labelForm = {"Mã công ty:","Tên công ty:","Địa chỉ:","SDT:"};
    public NhaCungCap(DataSet ds){
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1200,500));
        //this.setBackground(Color.red);

        panelInfo = new JPanel();
        panelInfo.setPreferredSize(new Dimension(390,0));
        panelInfo.setBackground(Color.BLUE);

        panelTable = new JPanel();
        panelTable.setLayout(new BorderLayout());
        
        searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //DefaultTableModel model = new DefaultTableModel(ds.getData(),ds.getColumnName());
        //JTable table = new JTable(model);
        //table = new JTable(ds.getData(),ds.getColumnLabel()){
            //public boolean isCellEditable(int row,int column){
                //return false;
            //}
        //};        
        // ngăn người dùng kéo thả thay đổi kích thước cột
// Object[][] data = ds.getData();
// String[] columnLabels = ds.getColumnLabel();
// Object[][] newData = new Object[data.length][columnLabels.length + 1];
// for (int i = 0; i < data.length; i++) {
//     for (int j = 0; j < columnLabels.length; j++) {
//         newData[i][j] = data[i][j];
//     }
//     newData[i][columnLabels.length] = "Setting";
// }
// String[] newColumnLabels = new String[columnLabels.length + 1];
// for (int i = 0; i < columnLabels.length; i++) {
//     newColumnLabels[i] = columnLabels[i];
// }
// newColumnLabels[columnLabels.length] = "D";
// table = new JTable(newData, newColumnLabels){
//     public boolean isCellEditable(int row,int column){
//         return false;
//     }
// };
// TableColumnModel columnModel = table.getColumnModel();
//         for(int i=0; i < columnModel.getColumnCount(); i++){
//             columnModel.getColumn(i).setResizable(false);
//         }
// table.addMouseListener(new MouseAdapter() {
//     public void mouseClicked(MouseEvent e){
//         int rowIndex = table.getSelectedRow();

//         String maCty = table.getValueAt(rowIndex, 0).toString();
//         int row = table.rowAtPoint(e.getPoint());
//         int column = table.columnAtPoint(e.getPoint());
//         if (column == 4) { // columnIndexD là chỉ số cột "D"
//     String value = (String) table.getValueAt(row, column);
//     if (value.equals("Setting")) {
//         int modelRow = table.convertRowIndexToModel(row);
//         SwingUtilities.invokeLater(new Runnable() {
//             public void run() {
//                 DefaultTableModel model = (DefaultTableModel) table.getModel();
//                 model.removeRow(modelRow);
//             }
//         });
//     }
// }
    
//     }
// });
Object[][] data = ds.getData();
String[] columnLabels = ds.getColumnLabel();
Object[][] newData = new Object[data.length][columnLabels.length + 1];
for (int i = 0; i < data.length; i++) {
    for (int j = 0; j < columnLabels.length; j++) {
        newData[i][j] = data[i][j];
    }
    newData[i][columnLabels.length] = "Setting"; // thêm giá trị "Setting" vào cột "D"
}
String[] newColumnLabels = new String[columnLabels.length + 1];
for (int i = 0; i < columnLabels.length; i++) {
    newColumnLabels[i] = columnLabels[i];
}
newColumnLabels[columnLabels.length] = "D";
table = new JTable(newData, newColumnLabels){
    public boolean isCellEditable(int row,int column){
        return false;
    }
};
table.addMouseListener(new MouseAdapter() {
    public void mouseClicked(MouseEvent e) {
        int rowIndex = table.getSelectedRow();
        int columnIndexD = table.getColumnCount() - 1;
        if (columnIndexD == 4) { // columnIndexD là chỉ số cột "D"
            String value = (String) table.getValueAt(rowIndex, columnIndexD);
            if (value.equals("Setting")) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        if (rowIndex != -1 && rowIndex < table.getRowCount()) { // kiểm tra nếu hàng được chọn có dữ liệu
                            table.remove(rowIndex);
                        }
                    }
                });
            }
        }
    }
});
        // ngăn người kéo thả thay đổi vị trí cột
        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);
        header.setPreferredSize(new Dimension(40,25));
        // ngăn chỉnh sửa dữ liệu
        // table.setEnabled(false);

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
                FormCongTy();
            }            
        });
        addButton.addMouseListener(this);

        

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(addButton);
       

        panelTable.add(searchPanel,BorderLayout.NORTH);
        panelTable.add(scrollPane);
        this.add(panelTable,BorderLayout.WEST);
        //this.add(panelInfo,BorderLayout.EAST);
        this.setVisible(false);
    }

    public JDialog FormCongTy(){
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(NhaCungCap.this);
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
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        add = new String[labelForm.length];
                        for(int i=0;i<labelForm.length;i++){
                            add[i] = textFields[i].getText();
                            // System.out.println(add[i]);
                            System.out.println(add[i]);
                        }
                        // JOptionPane.showMessageDialog(null, "Thêm thành công");
                        // dialog.dispose();
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
