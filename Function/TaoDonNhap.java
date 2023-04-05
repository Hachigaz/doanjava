package Function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import SQL.DataSet;

public class TaoDonNhap extends JPanel implements MouseListener{
    JTable donnhap;
    JLabel lChuaDN;
    JButton add,look,btn;
    public String[] labelForm = {"Mã nhân viên:","Tên nhân viên:","Mã chức vụ:","Giới tính:","Ngày sinh:","Địa chỉ","Kho làm việc:"};
    public TaoDonNhap(DataSet ds)
    {
        // donnhap = new JTable(ds.getData(),ds.getColumnName());
        // this.add(donnhap);

        // tạo đơn nhập mới
        add = new JButton("Thêm đơn nhập");
        add.setBorder(null);
        add.setPreferredSize(new Dimension(100,40));
        add.setBackground(new Color(0, 255, 119));
        add.setForeground(new Color(0, 0, 0));
        add.setOpaque(true);
        add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormNhanVien();
            }            
        });

        look = new JButton("Xem đơn nhập");
        look.setPreferredSize(new Dimension(100, 40));
        look.setBackground(new Color(255, 197, 70));
        look.setForeground(new Color(0, 0, 0));
        look.setBorder(null);
        look.setOpaque(true);
        look.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        this.add(add);
        this.add(look);
    }
    public JDialog FormNhanVien(){
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(TaoDonNhap.this);
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
            panelCenter.add(createTextField(),gbc);
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
    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(300, 30)); // đặt kích thước ưu tiên cho trường văn bản
        textField.setFont(new Font("Monospace",Font.BOLD,13));
        return textField;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
}
