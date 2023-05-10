package Panel.NhanVien;

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
import BLL.NhanVienBLL;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import DTO.NhanvienMD;

public class Form extends JDialog implements MouseListener{
    NhanVienBLL nhanVienBLL = new NhanVienBLL();
    private JPanel panelContainer,panelLeft,panelRight,panelBottom,panelEnterData,panelEnterUserPass;
    private JLabel labelTitleInfo,labelTitleUserPass;
    private JLabel labelTenNV,labelChucVu,labelGioiTinh,labelNgaySinh,labelDiaChi,labelTenKho;
    private JLabel labelUsername,labelPassword,labelReTypePass,labelCheckBox;
    JTextField textUsername;
    JPasswordField password,retypepass;
    JTextField textTenNV,textDiaChi;
    JComboBox comboBox,comboBox2;
    JCheckBox checkBox;
    private ButtonGroup group;
    JRadioButton radio1,radio2;
    JButton addButton;
    JDateChooser dateChooser;
    ButtonModel selection;
    private String MaNV,TenNV,MaCV,GioiTinh,date,diaChi,maKho;
    private Date ngayDaChon;
    public Form(JFrame parent,ActionListener add){
        super(parent,"Form nhân viên",true);
        this.setPreferredSize(new Dimension(1300,700));
        labelTitleInfo = new JLabel("Thông tin nhân viên");
        labelTitleInfo.setBorder(BorderFactory.createEmptyBorder(60,0,0,0));
        labelTitleInfo.setFont(new Font("Poppins",Font.BOLD,20));
        labelTitleInfo.setHorizontalAlignment(JLabel.CENTER);

        labelTitleUserPass = new JLabel("Thiết lập tài khoản");
        labelTitleUserPass.setFont(new Font("Poppins",Font.BOLD,20));
        labelTitleUserPass.setBorder(BorderFactory.createEmptyBorder(60,0,0,0));
        labelTitleUserPass.setHorizontalAlignment(JLabel.CENTER);

        panelEnterData = new JPanel();//chứa các thành phần nhập
        panelEnterData.setLayout(new GridBagLayout());
        panelEnterData.setPreferredSize(new Dimension(800,600));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        labelTenNV = new JLabel("Họ tên");
        labelTenNV.setFont(new Font("Poppins",Font.BOLD,15));
        panelEnterData.add(labelTenNV,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        textTenNV = new JTextField();
        textTenNV.setPreferredSize(new Dimension(300,35));
        textTenNV.setBorder(null);
        textTenNV.setFont(new Font("Poppins",Font.PLAIN,16));
        textTenNV.setBorder(new EmptyBorder(5,5,5,5));
        panelEnterData.add(textTenNV,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        labelChucVu = new JLabel("Chức vụ");
        labelChucVu.setFont(new Font("Poppins",Font.BOLD,15));
        panelEnterData.add(labelChucVu,gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        String[] chucvu = {"Quản trị","Nhân viên kho","Quản lý kho"}; 
        comboBox = new JComboBox<>(chucvu);
        comboBox.setPreferredSize(new Dimension(300,35));
        panelEnterData.add(comboBox,gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        labelGioiTinh = new JLabel("Giới tính");
        labelGioiTinh.setFont(new Font("Poppins",Font.BOLD,15));
        panelEnterData.add(labelGioiTinh,gbc);


        gbc.gridx = 1;
        gbc.gridy = 2;
        radio1 = new JRadioButton("Nam");
        radio1.setFocusable(false);
        radio2 = new JRadioButton("Nữ");
        radio2.setFocusable(false);
        group = new ButtonGroup();
        group.add(radio1);
        group.add(radio2);
        JPanel radioContainer = new JPanel();
        radioContainer.add(radio1);
        radioContainer.add(radio2);
        panelEnterData.add(radioContainer,gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;
        labelNgaySinh = new JLabel("Ngày sinh");
        labelNgaySinh.setFont(new Font("Poppins",Font.BOLD,15));
        panelEnterData.add(labelNgaySinh,gbc);


        gbc.gridx = 1;
        gbc.gridy = 3;
        dateChooser = new JDateChooser();
        dateChooser.setPreferredSize(new Dimension(300,35));
        dateChooser.setFont(new Font("Poppins",Font.PLAIN,15));
        panelEnterData.add(dateChooser,gbc);


        gbc.gridx = 0;
        gbc.gridy = 4;
        labelDiaChi = new JLabel("Địa chỉ");
        labelDiaChi.setFont(new Font("Poppins",Font.BOLD,15));
        panelEnterData.add(labelDiaChi,gbc);


        gbc.gridx = 1;
        gbc.gridy = 4;
        textDiaChi = new JTextField();
        textDiaChi.setPreferredSize(new Dimension(300,35));
        textDiaChi.setBorder(null);
        textDiaChi.setFont(new Font("Poppins",Font.PLAIN,16));
        textDiaChi.setBorder(new EmptyBorder(5,5,5,5));
        panelEnterData.add(textDiaChi,gbc);


        gbc.gridx = 0;
        gbc.gridy = 5;
        labelTenKho = new JLabel("Kho làm việc");
        labelTenKho.setFont(new Font("Poppins",Font.BOLD,15));
        panelEnterData.add(labelTenKho,gbc);


        gbc.gridx = 1;
        gbc.gridy = 5;
        comboBox2 = new JComboBox<>(nhanVienBLL.layTenKho());
        comboBox2.setPreferredSize(new Dimension(300,35));
        comboBox2.setBorder(null);
        panelEnterData.add(comboBox2,gbc);

        panelEnterUserPass = new JPanel();
        panelEnterUserPass.setLayout(new GridBagLayout());
        panelEnterUserPass.setPreferredSize(new Dimension(500,600));
        panelEnterUserPass.setBorder(BorderFactory.createEmptyBorder(0, 0,100, 0));
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(10, 10, 10, 10);

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        labelUsername = new JLabel("Tên đăng nhập");
        labelUsername.setFont(new Font("Poppins",Font.BOLD,15));
        panelEnterUserPass.add(labelUsername,gbc2);

        gbc2.gridx = 1;
        gbc2.gridy = 0;
        textUsername = new JTextField();
        textUsername.setPreferredSize(new Dimension(300,35));
        textUsername.setBorder(null);
        textUsername.setFont(new Font("Poppins",Font.PLAIN,16));
        textUsername.setBorder(new EmptyBorder(5,5,5,5));
        panelEnterUserPass.add(textUsername,gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 1;
        labelPassword = new JLabel("Mật khẩu");
        labelPassword.setFont(new Font("Poppins",Font.BOLD,15));
        panelEnterUserPass.add(labelPassword,gbc2);

        gbc2.gridx = 1;
        gbc2.gridy = 1;
        password = new JPasswordField();
        password.setPreferredSize(new Dimension(300,35));
        password.setBorder(null);
        password.setFont(new Font("Poppins",Font.PLAIN,16));
        password.setBorder(new EmptyBorder(5,5,5,5));
        panelEnterUserPass.add(password,gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 2;
        labelReTypePass = new JLabel("Nhập lại mật khẩu");
        labelReTypePass.setFont(new Font("Poppins",Font.BOLD,15));
        panelEnterUserPass.add(labelReTypePass,gbc2);

        gbc2.gridx = 1;
        gbc2.gridy = 2;
        retypepass = new JPasswordField();
        retypepass.setPreferredSize(new Dimension(300,35));
        retypepass.setBorder(null);
        retypepass.setFont(new Font("Poppins",Font.PLAIN,16));
        retypepass.setBorder(new EmptyBorder(5,5,5,5));
        panelEnterUserPass.add(retypepass,gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 3;
        labelCheckBox = new JLabel("Hiện mật khẩu");
        labelCheckBox.setFont(new Font("Poppins",Font.PLAIN,12));
        panelEnterUserPass.add(labelCheckBox,gbc2);

        gbc2.gridx = 1;
        gbc2.gridy = 3;
        checkBox = new JCheckBox();
        checkBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 300));
        checkBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    password.setEchoChar((char) 0);
                    retypepass.setEchoChar((char) 0);
                }else{
                    password.setEchoChar('\u2022');
                    retypepass.setEchoChar('\u2022');
                }
            }
        });
        panelEnterUserPass.add(checkBox,gbc2);

        panelContainer = new JPanel();
        panelContainer.setLayout(new BorderLayout());
        panelContainer.setPreferredSize(new Dimension(1300,700));

        panelLeft = new JPanel();
        panelLeft.setPreferredSize(new Dimension(800,600));
        panelLeft.setLayout(new BorderLayout());

        panelRight = new JPanel();
        panelRight.setLayout(new BorderLayout());
        panelRight.setPreferredSize(new Dimension(500,600));

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

        panelRight.add(labelTitleUserPass,BorderLayout.NORTH);
        panelRight.add(panelEnterUserPass);

        panelContainer.add(panelLeft,BorderLayout.WEST);
        panelContainer.add(panelRight,BorderLayout.EAST);
        panelContainer.add(panelBottom,BorderLayout.SOUTH);

        this.add(panelContainer);
        this.pack();
        this.setLocationRelativeTo(null);
    }
    public String[] getData() {
        MaNV ="";
        String TenNV = textTenNV.getText();
        String MaCV="";
        String[] TenKho = new String[100];
        TenKho = nhanVienBLL.layTenKho();
        if(comboBox.getSelectedItem()=="Quản trị"){
            MaNV = "QTV";
            MaCV = "CV00";
        }else if(comboBox.getSelectedItem()=="Quản lý kho"){
            MaCV = "CV01";
            MaNV = "QLK";
        }else{
            MaCV = "CV02";
            MaNV = "NV";
        }
        String GioiTinh="";
        selection = group.getSelection();
        if(selection == radio1.getModel()){
            GioiTinh = "Nam";
        }else if(selection == radio2.getModel()){
            GioiTinh = "Nữ";
        }
        String date="";
        ngayDaChon = dateChooser.getDate();
        if(ngayDaChon != null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ngayDaChon);
            int nam = calendar.get(Calendar.YEAR);
            int thang = calendar.get(Calendar.MONTH)+1;
            int ngay = calendar.get(Calendar.DATE);
            date = nam+"-"+thang+"-"+ngay;
        }
        String diaChi = textDiaChi.getText();
        String maKho="";
        for(int i=0;i<nhanVienBLL.laySoLuongKho();i++){
            if(comboBox2.getSelectedItem().equals(nhanVienBLL.layTenKho()[i])){
                maKho = nhanVienBLL.layMaKho()[i];
                MaNV = MaNV + (i+1);
            }
        }
        return new String[] {MaNV, TenNV, MaCV, GioiTinh, date, diaChi, maKho};
    }
    public String[] getUserPass(){
        String Username = textUsername.getText();
        char[] passwordChars = password.getPassword();
        String Password = new String(passwordChars);
        char[] retypeChars = retypepass.getPassword();
        String Retype = new String(retypeChars);
        String check = "False";
        if(Password.equals(Retype)){
            check = "True";
        }
        String maNhomQuyen = "";
        if(MaNV.substring(0, 2).equals("NV")){
            maNhomQuyen = "NQ_NV";
        }else if(MaNV.substring(0, 3).equals("QLK")){
            maNhomQuyen = "NQ_QLK";
        }else if(MaNV.substring(0, 3).equals("QTV")){
            maNhomQuyen = "NQ_ADMIN";
        }
        return new String[] {MaNV,Username,Password,maNhomQuyen,check};
    }
    public void visible(){
        setVisible(true);
    }
    public boolean check() {
        String TenNV = textTenNV.getText();
        String diaChi = textDiaChi.getText();
        String date = null;
        ngayDaChon = dateChooser.getDate();
        if(ngayDaChon != null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ngayDaChon);
            int nam = calendar.get(Calendar.YEAR);
            int thang = calendar.get(Calendar.MONTH)+1;
            int ngay = calendar.get(Calendar.DATE);
            date = nam+"-"+thang+"-"+ngay;
        }
        String checkTenTK = textUsername.getText();
        char[] matkhau = password.getPassword();
        String checkmatkhau = new String(matkhau);
        char[] retypematkhau = retypepass.getPassword();
        String checkretype = new String(retypematkhau);
        if(radio1.isSelected()==false && radio2.isSelected()==false){
            return false;
        }
        if (TenNV.trim().equals("")) {
            return false;
        }
        if (diaChi.trim().equals("")) {
            return false;
        }
        if (date == null) {
            return false;
        }
        if(checkTenTK.trim().equals("")){
            return false;
        }
        if(checkmatkhau.trim().equals("")){
            return false;
        }
        if(checkretype.trim().equals("")){
            return false;
        }
        return true;
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
