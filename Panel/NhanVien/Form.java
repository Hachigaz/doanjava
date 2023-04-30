package Panel.NhanVien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class Form extends JDialog{
    private JPanel panelContainer,panelLeft,panelRight,panelBottom,panelEnterData;
    private JLabel labelName,labelEmail,labelTitleInfo;
    private JLabel labelMaNV,labelTenNV,labelChucVu,labelGioiTinh,labelNgaySinh,labelDiaChi,labelTenKho;
    private JTextField textMaNV,textTenNV,textDiaChi;
    private JComboBox comboBox,comboBox2;
    private ButtonGroup group;
    private JRadioButton radio1,radio2;
    private JButton addButton;
    private JDateChooser dateChooser;
    public Form(JFrame parent,ActionListener add){
        super(parent,"Form nhân viên",true);
        this.setPreferredSize(new Dimension(1300,700));
        labelTitleInfo = new JLabel("Thông tin nhân viên");
        labelTitleInfo.setFont(new Font("Poppins",Font.BOLD,20));
        labelTitleInfo.setBorder(BorderFactory.createEmptyBorder(20,320,0,0));

        panelEnterData = new JPanel();//chứa các thành phần nhập
        panelEnterData.setLayout(new GridBagLayout());
        panelEnterData.setPreferredSize(new Dimension(800,600));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        labelMaNV = new JLabel("Mã nhân viên");
        labelMaNV.setFont(new Font("Poppins",Font.BOLD,15));
        panelEnterData.add(labelMaNV,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        textMaNV = new JTextField();
        textMaNV.setPreferredSize(new Dimension(300,35));
        textMaNV.setFont(new Font("Poppins",Font.PLAIN,15));
        panelEnterData.add(textMaNV,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        labelTenNV = new JLabel("Họ tên");
        labelTenNV.setFont(new Font("Poppins",Font.BOLD,15));
        panelEnterData.add(labelTenNV,gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        textTenNV = new JTextField(20);
        panelEnterData.add(textTenNV,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        labelChucVu = new JLabel("Chức vụ");
        labelChucVu.setFont(new Font("Poppins",Font.BOLD,15));
        panelEnterData.add(labelChucVu,gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        String[] chucvu = {"Quản trị","Nhân viên kho","Quản lý kho"}; 
        comboBox = new JComboBox<>(chucvu);
        comboBox.setPreferredSize(new Dimension(150, 30));
        panelEnterData.add(comboBox,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        labelGioiTinh = new JLabel("Giới tính");
        labelGioiTinh.setFont(new Font("Poppins",Font.BOLD,15));
        panelEnterData.add(labelGioiTinh,gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
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
        gbc.gridy = 4;
        labelNgaySinh = new JLabel("Ngày sinh");
        labelNgaySinh.setFont(new Font("Poppins",Font.BOLD,15));
        panelEnterData.add(labelNgaySinh,gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        dateChooser = new JDateChooser();
        dateChooser.setPreferredSize(new Dimension(150, 30));
        dateChooser.setFont(new Font("Poppins",Font.PLAIN,15));
        panelEnterData.add(dateChooser,gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        labelDiaChi = new JLabel("Địa chỉ");
        labelDiaChi.setFont(new Font("Poppins",Font.BOLD,15));
        panelEnterData.add(labelDiaChi,gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        textDiaChi = new JTextField(20);
        panelEnterData.add(textDiaChi,gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        labelTenKho = new JLabel("Kho làm việc");
        labelTenKho.setFont(new Font("Poppins",Font.BOLD,15));
        panelEnterData.add(labelTenKho,gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        String[] str = {"Kho ADV","Kho THD","Kho NVC","Kho LHP"};
        comboBox2 = new JComboBox<>(str);
        comboBox2.setPreferredSize(new Dimension(150, 30));
        panelEnterData.add(comboBox2,gbc);

        panelContainer = new JPanel();
        panelContainer.setLayout(new BorderLayout());
        panelContainer.setPreferredSize(new Dimension(1300,700));

        panelLeft = new JPanel();
        panelLeft.setPreferredSize(new Dimension(800,600));
        panelLeft.setLayout(new BorderLayout());

        panelRight = new JPanel();
        panelRight.setBackground(Color.red);
        panelRight.setPreferredSize(new Dimension(500,600));

        addButton = new JButton("Thêm");
        addButton.setPreferredSize(new Dimension(300, 40));
        addButton.setBorder(null);
        addButton.setFocusable(false);
        addButton.setBackground(Color.GREEN);
        addButton.addActionListener(add);

        panelBottom = new JPanel();
        panelBottom.setBackground(Color.gray);
        panelBottom.setPreferredSize(new Dimension(800,100));
        panelBottom.setLayout(new FlowLayout());
        panelBottom.add(addButton);

        panelLeft.add(labelTitleInfo,BorderLayout.NORTH);
        panelLeft.add(panelEnterData);


        // labelName = new JLabel("Họ tên");
        // textFieldName = new JTextField(20);
        // textFieldName.setBorder(null);
        // labelEmail = new JLabel("Email");
        // textFieldEmail = new JTextField(20);
        // textFieldEmail.setBorder(null);
        // panelCenterInfo = new JPanel();
        // panelCenterInfo.setLayout(new GridBagLayout());
        // GridBagConstraints gbc = new GridBagConstraints();
        // gbc.insets = new Insets(10, 10, 10, 10);

        // gbc.gridx = 0;
        // gbc.gridy = 0;
        // panelCenterInfo.add(labelName,gbc);
        
        // gbc.gridx = 1;
        // gbc.gridy = 0;
        // panelCenterInfo.add(textFieldName,gbc);

        // gbc.gridx = 0;
        // gbc.gridy = 1;
        // panelCenterInfo.add(labelEmail,gbc);
        
        // gbc.gridx = 1;
        // gbc.gridy = 1;
        // panelCenterInfo.add(textFieldEmail,gbc);

        // addButton = new JButton("Thêm");
        // panelBottom = new JPanel();
        // panelBottom.add(addButton);

        panelContainer.add(panelLeft,BorderLayout.WEST);
        panelContainer.add(panelRight,BorderLayout.EAST);
        panelContainer.add(panelBottom,BorderLayout.SOUTH);

        this.add(panelContainer);
        this.pack();
        this.setLocationRelativeTo(null);
    }
    public String[] getData() {
        String MaNV = textMaNV.getText();
        String TenNV = textTenNV.getText();
        String MaCV;
        if(comboBox.getSelectedItem()=="Quản trị"){
            MaCV = "CV00";
        }else if(comboBox.getSelectedItem()=="Quản lý kho"){
            MaCV = "CV01";
        }else{
            MaCV = "CV02";
        }
        String GioiTinh="";
        ButtonModel selection = group.getSelection();
        if(selection == radio1.getModel()){
            GioiTinh = "Nam";
        }else if(selection == radio2.getModel()){
            GioiTinh = "Nữ";
        }
        Date ngayDaChon = dateChooser.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ngayDaChon);
        int nam = calendar.get(Calendar.YEAR);
        int thang = calendar.get(Calendar.MONTH)+1;
        int ngay = calendar.get(Calendar.DATE);
        String date = nam+"-"+thang+"-"+ngay;
        String diaChi = textDiaChi.getText();
        String maKho="";
        if(comboBox2.getSelectedItem()=="Kho ADV"){
            maKho = "K01";
        }else if(comboBox2.getSelectedItem()=="Kho THD"){
            maKho = "K02";
        }else if(comboBox2.getSelectedItem()=="Kho NVC"){
            maKho = "K03";
        }else{
            maKho = "K04";
        }
        return new String[] {MaNV, TenNV, MaCV, GioiTinh, date, diaChi, maKho};
    }
    public void visible(){
        setVisible(true);
    }
}
