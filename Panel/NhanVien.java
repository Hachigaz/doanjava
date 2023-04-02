package Panel;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import SQL.DataSet;

public class NhanVien extends JPanel implements MouseListener{
    JTable table;
    JScrollPane scrollPane;
    JTextField searchField;
    JButton searchButton;
    JButton addButton;
    JPanel searchPanel;
    public NhanVien(DataSet ds){
        this.setLayout(new BorderLayout());
        
        searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        table = new JTable(ds.getData(),ds.getColumnName());
        table.setEnabled(false);
        table.setRowHeight(30);
        scrollPane = new JScrollPane(table);
        // table.setPreferredScrollableViewportSize(new Dimension(800,500));
        scrollPane.setPreferredSize(new Dimension(800,500));
        scrollPane.setBackground(Color.white);

        searchField = new JTextField("Nhập tên nhân viên muốn tìm...");
        searchField.setPreferredSize(new Dimension(300,30));
        searchField.setFont(new Font("Monospace",Font.PLAIN,15));
        searchField.setForeground(Color.gray);
        searchField.setFocusable(false);
        searchField.addMouseListener(this);
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Nhập tên nhân viên muốn tìm...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                    searchField.requestFocusInWindow();
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(Color.GRAY);
                    searchField.setText("Nhập tên nhân viên muốn tìm...");
                    searchField.setFocusable(false);
                }        
            } 
        });

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
        addButton.addMouseListener(this);

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(addButton);

            
        this.add(searchPanel,BorderLayout.NORTH);
        this.add(scrollPane);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==searchField){
            searchField.setFocusable(true);
        }else if(e.getSource()!=searchField){
            searchField.setFocusable(false);
        }

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
        }
    }
}
