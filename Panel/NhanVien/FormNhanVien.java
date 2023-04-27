package Panel.NhanVien;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import com.mysql.cj.protocol.a.NativeConstants.IntegerDataType;
import com.toedter.calendar.JDateChooser;

import DAL.DataAccessLayer;
import DTO.NhanvienMD;

public class FormNhanVien extends JDialog{
    private JButton btn;
    private Object[] atributeNV;
    private JPanel panelCenter,panelTop,panelBottom,panelDialog;
    private JLabel title;
    public String[] labelForm = {"Mã nhân viên:       ","Tên nhân viên:     ","Chức vụ:               ","Giới tính:                ","Ngày sinh:            ","Địa chỉ:                  ","Kho làm việc:       ","Số giờ làm:           ","Lương cơ bản:    "};
    public FormNhanVien(){
        // JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(NhanVien.this);
        this.setTitle("Thêm nhân viên");
        this.setModal(true);
        
        panelCenter = new JPanel();

        panelTop = new JPanel();
        title = new JLabel("FORM THÊM NHÂN VIÊN");
        title.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        title.setFont(new Font("Monospace",Font.BOLD,20));
        panelTop.add(title);

        panelBottom = new JPanel();
        panelBottom.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));

        panelDialog = new JPanel();
        panelDialog.setPreferredSize(new Dimension(1000,700));
        panelDialog.setLayout(new BorderLayout());
        panelDialog.add(panelTop,BorderLayout.NORTH);
        panelDialog.add(panelCenter,BorderLayout.CENTER);
        panelDialog.add(panelBottom,BorderLayout.SOUTH);

        add(panelDialog);
        setPreferredSize(new Dimension(1000,650));
        pack();
        // setLocationRelativeTo(parentFrame);
        setVisible(true);

        this.add(panelBottom);
        this.add(panelCenter);
        this.add(panelTop);
        this.add(panelDialog);

        panelCenter.setOpaque(true);
        panelBottom.setOpaque(true);
        panelTop.setOpaque(true);
        panelDialog.setOpaque(true);
    }

    public void setUpForm(ActionListener add){
        panelCenter.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        btn = new JButton("Thêm");
        panelBottom.add(btn);
        btn.addActionListener(add);
        for(int i=0;i<labelForm.length;i++){
            gbc.gridx = 0;
            gbc.gridy = i;
            panelCenter.add(createLabel(labelForm[i]),gbc);
            if(i==2){
                gbc.gridx = 1;
                gbc.gridy = i;
                String[] chucvu = {"Quản trị","Nhân viên kho","Quản lý kho"}; 
                panelCenter.add(createComboBox(chucvu,i),gbc);      
            }else if(i==3){
                gbc.gridx = 1;
                gbc.gridy = i;
                String[] gioitinh = {"Nam","Nữ"};
                panelCenter.add(createComboBox(gioitinh,i),gbc); 
            }else if(i==4){
                gbc.gridx = 1;
                gbc.gridy = i;
               panelCenter.add(createDateChooser(i),gbc);      
            }else if(i==6){
                gbc.gridx = 1;
                gbc.gridy = i;
                String[] str = {"Kho ADV","Kho THD","Kho NVC","Kho LHP"};
                panelCenter.add(createComboBox(str,i),gbc);      
            }else if(i==7){
                gbc.gridx = 1;
                gbc.gridy = i;
                panelCenter.add(createSpinner(0,0,192,8,i),gbc);      
            }else if(i==8){
                gbc.gridx = 1;
                gbc.gridy = i;
                panelCenter.add(createSpinner(1000000,1000000,10000000,100000,i),gbc);      
            }else{
                gbc.gridx = 1;
                gbc.gridy = i;
                panelCenter.add(createTextField(i),gbc);
            }
        }
        JTextField textFieldMaNV = (JTextField)atributeNV[0];
                String MaNV = textFieldMaNV.getText();
                JTextField textFieldTenNV = (JTextField)atributeNV[1];
                String TenNV = textFieldTenNV.getText();
                JComboBox comboBoxMaCV = (JComboBox)atributeNV[2];
                String MaCV;
                if(comboBoxMaCV.getSelectedItem()=="Quản trị"){
                    MaCV = "CV00";
                }else if(comboBoxMaCV.getSelectedItem()=="Quản lý kho"){
                    MaCV = "CV01";
                }else{
                    MaCV = "CV02";
                }
                JComboBox comboBoxGioiTinh = (JComboBox)atributeNV[3];
                String GioiTinh = (String)comboBoxGioiTinh.getSelectedItem();
                JDateChooser dateChooserNgaySinh = (JDateChooser)atributeNV[4];
                Date ngayDaChon = dateChooserNgaySinh.getDate();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(ngayDaChon);
                int nam = calendar.get(Calendar.YEAR);
                int thang = calendar.get(Calendar.MONTH)+1;
                int ngay = calendar.get(Calendar.DATE);
                String date = nam+"-"+thang+"-"+ngay;
                
                // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                // String ngaySinh = sdf.format(dateChooserNgaySinh.getDate());
                // Date NgaySinh = (Date)dateChooserNgaySinh.getDate();
                // java.sql.Date sqlDate = new java.sql.Date(NgaySinh.getTime());
                
                JTextField textFieldDiaChi = (JTextField)atributeNV[5];
                String DiaChi = textFieldDiaChi.getText();
                JComboBox comboBoxKhoLamViec = (JComboBox)atributeNV[6];
                String MaKho;
                if(comboBoxKhoLamViec.getSelectedItem()=="Kho ADV"){
                    MaKho = "K01";
                }else if(comboBoxKhoLamViec.getSelectedItem()=="Kho THD"){
                    MaKho = "K02";
                }else if(comboBoxKhoLamViec.getSelectedItem()=="Kho NVC"){
                    MaKho = "K03";
                }else{
                    MaKho = "K04";
                }
                JSpinner spinnerSoGioLam = (JSpinner)atributeNV[7];
                Integer SoGioLam = (Integer)spinnerSoGioLam.getValue();
                JSpinner spinnerLuongCoBan = (JSpinner)atributeNV[8];
                Integer value = (Integer) spinnerLuongCoBan.getValue();
                Float LuongCoBan = value.floatValue();
                if (MaNV.isEmpty() || TenNV.isEmpty() || MaCV.isEmpty() || GioiTinh.isEmpty() || date.isEmpty() || DiaChi.isEmpty() || MaKho.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
    }
    public String getMaNV() {
        JTextField textFieldMaNV = (JTextField) atributeNV[0]; // Lấy đối tượng JTextField tại vị trí i=1
        return textFieldMaNV.getText(); // Lấy giá trị của JTextField
    }
    public String getTenNV() {
        JTextField textFieldTenNV = (JTextField) atributeNV[1]; // Lấy đối tượng JTextField tại vị trí i=1
        return textFieldTenNV.getText(); // Lấy giá trị của JTextField
    }
    public String getMaCV() {
        JComboBox comboBoxMaCV = (JComboBox)atributeNV[2];
        if(comboBoxMaCV.getSelectedItem()=="Quản trị"){
            return "CV00";
        }else if(comboBoxMaCV.getSelectedItem()=="Quản lý kho"){
            return "CV01";
        }else{
            return "CV02";
        }
    }
    public String getGioiTinh() {
        JComboBox comboBoxGioiTinh = (JComboBox)atributeNV[3];
        return (String)comboBoxGioiTinh.getSelectedItem();
    }
    public String getNgaySinh() {
        JDateChooser dateChooserNgaySinh = (JDateChooser)atributeNV[4];
        Date ngayDaChon = dateChooserNgaySinh.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ngayDaChon);
        int nam = calendar.get(Calendar.YEAR);
        int thang = calendar.get(Calendar.MONTH)+1;
        int ngay = calendar.get(Calendar.DATE);
        return nam+"-"+thang+"-"+ngay;
    }
    public String getDiaChi(){
        JTextField textFieldDiaChi = (JTextField)atributeNV[5];
        return textFieldDiaChi.getText();
    }
    public String getMaKho(){
        JComboBox comboBoxKhoLamViec = (JComboBox)atributeNV[6];
        if(comboBoxKhoLamViec.getSelectedItem()=="Kho ADV"){
            return "K01";
        }else if(comboBoxKhoLamViec.getSelectedItem()=="Kho THD"){
            return "K02";
        }else if(comboBoxKhoLamViec.getSelectedItem()=="Kho NVC"){
            return "K03";
        }else{
            return "K04";
        }
    }
    public Integer getSoGio(){
        JSpinner spinnerSoGioLam = (JSpinner)atributeNV[7];
        return (Integer)spinnerSoGioLam.getValue();
    }
    public Float getLuongCoBan(){
        JSpinner spinnerLuongCoBan = (JSpinner)atributeNV[8];
        Integer value = (Integer) spinnerLuongCoBan.getValue();
        return value.floatValue();
    }
    private JLabel createLabel(String text){
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(150, 30)); // đặt kích thước ưu tiên cho nhãn
        label.setFont(new Font("Poppins",Font.BOLD,13));
        return label;
    }
    private JTextField createTextField(int index) {
        if (atributeNV == null) {
            atributeNV = new Object[labelForm.length];
        }
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(250, 30));
        textField.setFont(new Font("Monospace", Font.BOLD, 13));
        atributeNV[index] = textField;
        textField.setBorder(null);
        return textField;
    }
    private JDateChooser createDateChooser(int index){
        JDateChooser datecChooser = new JDateChooser();
        datecChooser.setPreferredSize(new Dimension(150, 30));
        datecChooser.setFont(new Font("Poppins",Font.PLAIN,15));
        atributeNV[index] = datecChooser;
        return datecChooser;
    }
    private JComboBox createComboBox(String[] str,int index){
        JComboBox comboBox = new JComboBox<>(str);
        comboBox.setPreferredSize(new Dimension(150, 30));
        atributeNV[index] = comboBox;
        return comboBox;
    }
    private JSpinner createSpinner(int value,int minimum,int maximum,int stepSize,int index){
        SpinnerModel model = new SpinnerNumberModel(value,minimum,maximum,stepSize);
        JSpinner spinner = new JSpinner(model);
        spinner.setPreferredSize(new Dimension(150, 35));
        spinner.setFont(new Font("Poppins",Font.PLAIN,15));
        atributeNV[index] = spinner;
        return spinner;
    }
}
