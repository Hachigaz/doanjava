package Panel.Form;

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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import BLL.CongTy2BLL;

public class Form extends JDialog implements MouseListener{
    CongTy2BLL CongTy2BLL = new CongTy2BLL();
    private JPanel panelContainer,panelLeft,panelRight,panelBottom,panelEnterData,panelEnterUserPass;
    private JLabel labelTitleInfo;
    private JLabel labelTenCT,labelMaCT,labelSDT,labelDiaChi;
    private JLabel labelUsername,labelPassword,labelReTypePass,labelCheckBox;
    private JTextField textUsername;
    private JPasswordField password,retypepass;
    public JTextField textTenCty,textDiaChi,textSDT,textMaCty;
    JComboBox comboBox,comboBox2;
    JCheckBox checkBox;
    private ButtonGroup group;
    JRadioButton radio1,radio2;
    public JButton addButton;
    JDateChooser dateChooser;
    ButtonModel selection;
    private String MaNV,TenNV,MaCV,GioiTinh,date,diaChi,maKho;
    private Date ngayDaChon;
    public Form(JFrame parent,ActionListener add){
        super(parent,"Form Công ty",true);
        this.setPreferredSize(new Dimension(800,700));
        labelTitleInfo = new JLabel("Thông tin công ty");
        labelTitleInfo.setBorder(BorderFactory.createEmptyBorder(60,0,0,0));
        labelTitleInfo.setFont(new Font("Poppins",Font.BOLD,20));
        labelTitleInfo.setHorizontalAlignment(JLabel.CENTER);
        this.setResizable(false);
        

        panelEnterData = new JPanel();//chứa các thành phần nhập
        panelEnterData.setLayout(new GridBagLayout());
        panelEnterData.setPreferredSize(new Dimension(800,600));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        labelTenCT = new JLabel("Tên Công ty");
        labelTenCT.setFont(new Font("Poppins",Font.BOLD,15));
        panelEnterData.add(labelTenCT,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        textTenCty = new JTextField();
        textTenCty.setPreferredSize(new Dimension(300,35));
        textTenCty.setBorder(null);
        textTenCty.setFont(new Font("Poppins",Font.PLAIN,16));
        textTenCty.setBorder(new EmptyBorder(5,5,5,5));
        panelEnterData.add(textTenCty,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        labelMaCT = new JLabel("Mã Công ty(VD:Cty_XXX)");
        labelMaCT.setFont(new Font("Poppins",Font.BOLD,15));
        panelEnterData.add(labelMaCT,gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        textMaCty = new JTextField();
        textMaCty.setPreferredSize(new Dimension(300,35));
        textMaCty.setBorder(null);
        textMaCty.setFont(new Font("Poppins",Font.PLAIN,16));
        textMaCty.setBorder(new EmptyBorder(5,5,5,5));
        panelEnterData.add(textMaCty,gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        labelDiaChi = new JLabel("Địa chỉ");
        labelDiaChi.setFont(new Font("Poppins",Font.BOLD,15));
        panelEnterData.add(labelDiaChi,gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        textDiaChi = new JTextField();
        textDiaChi.setPreferredSize(new Dimension(300,35));
        textDiaChi.setBorder(null);
        textDiaChi.setFont(new Font("Poppins",Font.PLAIN,16));
        textDiaChi.setBorder(new EmptyBorder(5,5,5,5));
        panelEnterData.add(textDiaChi,gbc);



        gbc.gridx = 0;
        gbc.gridy = 3;
        labelSDT = new JLabel("SDT");
        labelSDT.setFont(new Font("Poppins",Font.BOLD,15));
        panelEnterData.add(labelSDT,gbc);


        gbc.gridx = 1;
        gbc.gridy = 3;
        textSDT = new JTextField();
        textSDT.setPreferredSize(new Dimension(300,35));
        textSDT.setBorder(null);
        textSDT.setFont(new Font("Poppins",Font.PLAIN,16));
        textSDT.setBorder(new EmptyBorder(5,5,5,5));
        panelEnterData.add(textSDT,gbc);


        

        panelEnterUserPass = new JPanel();
        panelEnterUserPass.setLayout(new GridBagLayout());
        panelEnterUserPass.setPreferredSize(new Dimension(500,600));
        panelEnterUserPass.setBorder(BorderFactory.createEmptyBorder(0, 0,100, 0));
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(10, 10, 10, 10);

       
      

        panelContainer = new JPanel();
        panelContainer.setLayout(new BorderLayout());
        panelContainer.setPreferredSize(new Dimension(1300,700));

        panelLeft = new JPanel();
        panelLeft.setPreferredSize(new Dimension(800,600));
        panelLeft.setLayout(new BorderLayout());

        panelRight = new JPanel();
        panelRight.setLayout(new BorderLayout());
        panelRight.setPreferredSize(new Dimension(500,600));

        Border border = BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK);
        Border emptyBorder = new EmptyBorder(0, 5, 0, 0);
        panelRight.setBorder(BorderFactory.createCompoundBorder(border, emptyBorder));

        addButton = new JButton("Thêm");
        addButton.setPreferredSize(new Dimension(300, 40));
        addButton.setBorder(null);
        addButton.setFocusable(false);
        addButton.setBackground(Color.GREEN);
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.addActionListener(add);
        addButton.addMouseListener(this);

        panelBottom = new JPanel();
        panelBottom.setPreferredSize(new Dimension(800,100));
        panelBottom.setLayout(new FlowLayout());
        panelBottom.add(addButton);

        panelLeft.add(labelTitleInfo,BorderLayout.NORTH);
        panelLeft.add(panelEnterData);

        
        panelRight.add(panelEnterUserPass);

        panelContainer.add(panelLeft,BorderLayout.WEST);
        panelContainer.add(panelRight,BorderLayout.EAST);
        panelContainer.add(panelBottom,BorderLayout.SOUTH);

        this.add(panelContainer);
        this.pack();
        this.setLocationRelativeTo(null);
    }
    public String[] getData() {
        String MaCty =textMaCty.getText();
        String TenCty = textTenCty.getText();
        
        String SDT=textSDT.getText();
   
        String DiaChi = textDiaChi.getText();
        return new String[] {MaCty, TenCty, DiaChi, SDT};
    }
    public void visible(){
        setVisible(true);
    }
    
    public boolean check() {
        String TenCty = textTenCty.getText();
        String diaChi = textDiaChi.getText(); 
        boolean check = true;
        if (TenCty.trim().equals("")) {
            check = false;
        }
        if (diaChi.trim().equals("")) {
            check = false;
        }
        return check;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == addButton){
            addButton.setBackground(Color.gray);
        }
    }
    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == addButton){
            addButton.setBackground(Color.green);
        }
    }
}
