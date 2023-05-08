package Panel.ThongTinKho.Form;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BLL.FormDonBLL;
import DTO.CongtyMD;
import DTO.DonNhapMD;
import DTO.KhuvucMD;
import DTO.Mat_hangMD;
import Panel.UI;
import Panel.SubPanel.CustomComboBox;
import Panel.SubPanel.TablePanel;
import misc.ThongBaoDialog;
import misc.TitleFrame;

public class FormDon extends TitleFrame {
    private JPanel contentPanel = new JPanel();
    private FormDonBLL formDonBLL;
    private DefaultTableModel model;
    private TablePanel ctDonPanel = new TablePanel();
    private DataRow currentSPRow = null;
    public FormDon(){
        contentPanel.setLayout(new GridLayout(1,2,0,0));
        JPanel panelTop = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        JLabel label1 = new JLabel("Tạo đơn nhập mới");
        label1.setFont(new Font("Helvetica", Font.BOLD, 18));
        titlePanel.add(label1);
        panel.add(titlePanel);

        JLabel label2 = new JLabel("Mã kho:");
        JLabel maKhoLabel = new JLabel(UI.khoNVDangNhap.getTenKho());
        panel.add(label2);
        panel.add(maKhoLabel);

        JLabel label3 = new JLabel("Mã công ty:");
        CustomComboBox comboBox2 = new CustomComboBox();
        comboBox2.addItem("Chọn công ty", "null");

        JButton addButton = new JButton("Thêm sản phẩm");


        for(CongtyMD ct : FormDonBLL.getDanhSachCongTy()){
            comboBox2.addItem(ct.getTenCty(), ct.getMaCty());
        }
        comboBox2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                comboBox2.setEnabled(false);
                addButton.setEnabled(true);
            }
            
        });
        panel.add(label3);
        panel.add(comboBox2);

        JLabel label4 = new JLabel("Nhân viên nhập:");
        JLabel tenNVLabel = new JLabel(UI.nvDangNhap.getTenNV());
        panel.add(label4);
        panel.add(tenNVLabel);

        JLabel label5 = new JLabel("Ngày nhập:");
        JLabel labelNgayNhap = new JLabel();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        labelNgayNhap.setText(dateFormat.format(new java.util.Date()));
        panel.add(label5);
        panel.add(labelNgayNhap);

        model = new DefaultTableModel();
        model.addColumn("Khu vực chứa");
        model.addColumn("Tên mặt hàng");
        model.addColumn("Số lượng");


        addButton.setEnabled(false);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(themSPPanel==null){  
                    ArrayList<FormInput> inputFields = new ArrayList<FormInput>();


                    CustomComboBox mhCB = new CustomComboBox();
                    mhCB.addItem("Chọn mặt hàng", "null");

                    CustomComboBox kvCB = new CustomComboBox();
                    kvCB.addItem("Chọn khu vực để chứa","null");
                    for(KhuvucMD kv : FormDonBLL.getDanhSachKV("MaKho ="+UI.khoNVDangNhap.getMaKho())){
                        kvCB.addItem(kv.getTenKV(), kv.getMaKV());
                    }
                    inputFields.add(new FormInput("Chọn khu vực muốn chứa", kvCB));
                    ActionListener changeKVAction = new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(!kvCB.getSelectedKey().equals("null")){
                                ArrayList<Mat_hangMD> dsMatHang =  FormDonBLL.getDanhSachMH_KhuVuc(kvCB.getSelectedKey(),"MaCty = "+comboBox2.getSelectedKey());
                                if(dsMatHang!=null){
                                    for(Mat_hangMD mh : dsMatHang){
                                        mhCB.addItem(mh.getTenMH(), mh.getMaMH());
                                    }
                                }
                                kvCB.setEnabled(false);
                                mhCB.setEnabled(true);
                            }
                        }
                    };
                    kvCB.addActionListener(changeKVAction);
                    inputFields.add(new FormInput("Chọn sản phẩm", mhCB));
                    mhCB.setEnabled(false);

                    JTextField soLuongNhapField = new JTextField(20);
                    inputFields.add(new FormInput("Số lượng nhập", soLuongNhapField));


                    ActionListener submitAction = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(kvCB.getSelectedKey().equals("null")){
                                new ThongBaoDialog("Vui lòng chọn khu vực để chứa", null);
                                return;
                            }
                            
                            if(mhCB.getSelectedKey().equals("null")){
                                new ThongBaoDialog("Vui lòng chọn sản phẩm để chứa", null);
                                return;
                            }
                            int soLuong =0;
                            try{
                                soLuong = Integer.parseInt(soLuongNhapField.getText());
                            }
                            catch(NumberFormatException exception){
                                new ThongBaoDialog("Giá trị nhập vào không phải là số", null);
                                return;
                            }
                            if(soLuong<=0){
                                new ThongBaoDialog("Giá trị nhập vào phải lớn hơn 0", null);
                                return;
                            }
                            currentSPRow = new DataRow(mhCB.getSelectedKey(), mhCB.getSelectedItem().toString(), kvCB.getSelectedKey(), kvCB.getSelectedItem().toString(), soLuong);
                            model.addRow(new Object[]{currentSPRow.getTenMH(),currentSPRow.getTenKV(),currentSPRow.getSoLuong()});
                            rightPanel.remove(themSPPanel);
                            themSPPanel=null;
                        }
                    };
                    ActionListener cancelAction = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            rightPanel.remove(themSPPanel);
                            themSPPanel=null;
                        }
                    };

                    themSPPanel = new PanelThem("Thêm sản phẩm", inputFields, submitAction,cancelAction);
                    rightPanel.add(themSPPanel);
                    rightPanel.revalidate();
                }
            }
        });

        JButton saveButton = new JButton("Lưu");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maDon = formDonBLL.taoMaMoi();
                String maKho = UI.khoNVDangNhap.getMaKho();
                String maCongTy = comboBox2.getSelectedKey();
                String maNhanVien = UI.nvDangNhap.getMaNV();
                String ngayNhap = labelNgayNhap.getText();

                DonNhapMD donnhapnew= new DonNhapMD(maDon, maKho, maCongTy, maNhanVien, ngayNhap);

                // Lưu dữ liệu vào cơ sở dữ liệu hoặc làm gì đó khác ở đây

                dispose();
            }
        });

        JButton cancelButton = new JButton("Hủy");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel panelmid = new JPanel(new BorderLayout());
        panelmid.add(ctDonPanel, BorderLayout.CENTER);
        panelmid.add(addButton, BorderLayout.SOUTH);

        JPanel panelSouth = new JPanel();
        panelSouth.add(saveButton);
        panelSouth.add(cancelButton);

        JPanel panel2 = new JPanel(new BorderLayout());
        panelTop.add(titlePanel,BorderLayout.NORTH);
        panelTop.add(panel,BorderLayout.CENTER);
        panel2.add(panelTop,BorderLayout.NORTH);
        panel2.add(panelmid,BorderLayout.CENTER);
        panel2.add(panelSouth, BorderLayout.SOUTH);
        
        updateTable();


        rightPanel.setLayout(new CardLayout());
        

        contentPanel.add(panel2,BorderLayout.CENTER);
        contentPanel.add(rightPanel,BorderLayout.EAST);

        this.setContentPane(contentPanel);
        this.setSize(1200,800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    private JPanel rightPanel = new JPanel();
    private PanelThem themSPPanel;
    public void updateTable(){
        ctDonPanel.SetTable(model, null);
    }
    
    class DataRow {
        public String maMH;
        public String tenMH;
        public String maKV;
        public String tenKV;
        public int soLuong;
        public DataRow(String maMH, String tenMH, String maKV, String tenKV, int soLuong) {
            this.maMH = maMH;
            this.tenMH = tenMH;
            this.maKV = maKV;
            this.tenKV = tenKV;
            this.soLuong = soLuong;
        }
        public String getMaMH() {
            return maMH;
        }
        public void setMaMH(String maMH) {
            this.maMH = maMH;
        }
        public String getTenMH() {
            return tenMH;
        }
        public void setTenMH(String tenMH) {
            this.tenMH = tenMH;
        }
        public String getMaKV() {
            return maKV;
        }
        public void setMaKV(String maKV) {
            this.maKV = maKV;
        }
        public String getTenKV() {
            return tenKV;
        }
        public void setTenKV(String tenKV) {
            this.tenKV = tenKV;
        }
        public int getSoLuong() {
            return soLuong;
        }
        public void setSoLuong(int soLuong) {
            this.soLuong = soLuong;
        }
    }
}
