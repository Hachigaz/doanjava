package Panel.Form;

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
import DTO.ChitietdonnhapMD;
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
    private JLabel labelNgayNhap;
    private CustomComboBox comboBox2;
    private JButton addButton;

    private JPanel contentPanel = new JPanel();
    private FormDonBLL formDonBLL = new FormDonBLL();
    private DefaultTableModel model;
    private TablePanel ctDonPanel = new TablePanel();
    private ArrayList<DataRow> dsCTDon = new ArrayList<DataRow>();
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
        comboBox2 = new CustomComboBox();
        comboBox2.addItem("Chọn công ty", "null");

        addButton = new JButton("Thêm sản phẩm");
        setThemMH_DonXuat();

        for(CongtyMD ct : formDonBLL.getDanhSachCongTy()){
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
        labelNgayNhap = new JLabel();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        labelNgayNhap.setText(dateFormat.format(new java.util.Date()));
        panel.add(label5);
        panel.add(labelNgayNhap);

        model = new DefaultTableModel();
        model.addColumn("Khu vực chứa");
        model.addColumn("Tên mặt hàng");
        model.addColumn("Số lượng");


        addButton.setEnabled(false);


        JButton saveButton = new JButton("Lưu");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lưu dữ liệu vào cơ sở dữ liệu hoặc làm gì đó khác ở đây

                new ThongBaoDialog("Tạo đơn nhập thành công", null);
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
    private JPanel themSPPanel;
    public void updateTable(){
        ctDonPanel.SetTable(model, null);
    }
    
    class DataRow {
        public Mat_hangMD mh;
        public KhuvucMD kv;
        public float soLuong;

        
        public DataRow(Mat_hangMD mh, KhuvucMD kv, float soLuong) {
            this.mh = mh;
            this.kv = kv;
            this.soLuong = soLuong;
        }


        public float getSoLuong() {
            return soLuong;
        }
    }
    public void updateTableModel(){
        model.setRowCount(0);
        for(DataRow data : dsCTDon){
            model.addRow(new Object[]{data.mh.getMaMH(),data.kv.getMaKV(),data.soLuong});
        }
    }
    private void setThemMH_DonNhap(){
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(themSPPanel==null){  
                    ArrayList<FormInput> inputFields = new ArrayList<FormInput>();


                    CustomComboBox mhCB = new CustomComboBox();
                    mhCB.addItem("Chọn mặt hàng", "null");
                    ArrayList<Mat_hangMD> dsMatHang =  formDonBLL.getDanhSachMatHang("MaCty = "+comboBox2.getSelectedKey());
                    if(dsMatHang!=null){
                        for(Mat_hangMD mh : dsMatHang){
                            mhCB.addItem(mh.getTenMH(), mh.getMaMH());
                        }
                    }
                    inputFields.add(new FormInput("Chọn sản phẩm", mhCB));

                    CustomComboBox kvCB = new CustomComboBox();
                    kvCB.addItem("Chọn khu vực để chứa","null");

                    inputFields.add(new FormInput("Chọn khu vực muốn chứa", kvCB));
                    ActionListener changeMHAction = new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(!mhCB.getSelectedKey().equals("null")){
                                ArrayList<KhuvucMD> dsKVchuaMH = formDonBLL.getDanhSachKhuVuc_MH(mhCB.getSelectedKey());
                                if(dsKVchuaMH == null){
                                    new ThongBaoDialog("Không có khu vực được phân chứa loại mặt hàng này trong kho",null);
                                }
                                else{
                                    for(KhuvucMD kv : dsKVchuaMH){
                                        kvCB.addItem(kv.getTenKV(), kv.getMaKV());
                                    }
                                    kvCB.setEnabled(true);
                                }
                            }
                            else{
                                kvCB.setEnabled(false);
                            }
                        }
                    };
                    kvCB.setEnabled(false);
                    mhCB.addActionListener(changeMHAction);
                    JTextField soLuongNhapField = new JTextField(20);
                    inputFields.add(new FormInput("Số lượng nhập", soLuongNhapField));

                    JLabel sucChuaLabel = new JLabel();
                    
                    kvCB.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            float tongSLKV = formDonBLL.getSoLuongCL_KV(kvCB.getSelectedKey());
                            sucChuaLabel.setText("Sức chứa khu vực hiện tại: "+tongSLKV+"/"+formDonBLL.getFirstKV(kvCB.getSelectedKey()).getSucChua());
                        }
                        
                    });
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
                            float tongSLKV = formDonBLL.getSoLuongCL_KV(kvCB.getSelectedKey());
                            for(DataRow ctkvRow : dsCTDon){
                                if(ctkvRow.kv.getMaKV().equals(kvCB.getSelectedKey())){                                    
                                    tongSLKV+=(float)ctkvRow.getSoLuong()/(float)ctkvRow.mh.getSoLuongMoiThung();
                                }
                            }
                            if(soLuong/formDonBLL.getFirstMH(mhCB.getSelectedKey()).getSoLuongMoiThung()>tongSLKV){
                                new ThongBaoDialog("Khu vực không đủ sức chứa", null);
                                return;
                            }
                            dsCTDon.add(new DataRow(formDonBLL.getFirstMH(mhCB.getSelectedKey()),formDonBLL.getFirstKV(kvCB.getSelectedKey()), soLuong));
                            updateTableModel();
                            
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
                    themSPPanel.add(sucChuaLabel);
                    rightPanel.add(themSPPanel);
                    rightPanel.revalidate();
                }
            }
        });
    }
    private void taoDonNhap(){
        String maDon = formDonBLL.taoMaDonNhapMoi();
        String maKho = UI.khoNVDangNhap.getMaKho();
        String maCongTy = comboBox2.getSelectedKey();
        String maNhanVien = UI.nvDangNhap.getMaNV();
        String ngayNhap = labelNgayNhap.getText();

        DonNhapMD dn = new DonNhapMD(maDon, maKho, maCongTy, maNhanVien, ngayNhap);
        
        ArrayList<ChitietdonnhapMD> ctDN = new ArrayList<ChitietdonnhapMD>();
        for(DataRow dr : dsCTDon){
            ctDN.add(new ChitietdonnhapMD(maDon, dr.mh.getMaMH(), dr.kv.getMaKV(), dr.getSoLuong(), dr.getSoLuong()));
        }
        
        formDonBLL.themDonNhapMoi(dn, ctDN);
    }
    private void setThemMH_DonXuat(){
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(themSPPanel==null){
                    TablePanel panelChonSP = new TablePanel();
                    String[] columnNames = {"Tên sản phẩm","Khu vực","Số lượng","Ngày nhập"};
                    DefaultTableModel tableChonSP = new DefaultTableModel();
                    for(String columnName:columnNames){
                        tableChonSP.addColumn(columnName);
                    }
                    ArrayList<Object[]> dsMHChon = formDonBLL.getDanhSachMHChon(comboBox2.getSelectedKey());
                    for(Object[] mh :dsMHChon){
                        tableChonSP.addRow(new Object[]{mh[0],mh[1],mh[2],mh[3]});
                    }
                    panelChonSP.SetTable(tableChonSP0x, null);
                    themSPPanel=panelChonSP;
                    rightPanel.add(themSPPanel);
                    rightPanel.revalidate();
                }
            }
        });
    }
    private void taoDonXuat(){
        String maDon = formDonBLL.taoMaDonNhapMoi();
        String maKho = UI.khoNVDangNhap.getMaKho();
        String maCongTy = comboBox2.getSelectedKey();
        String maNhanVien = UI.nvDangNhap.getMaNV();
        String ngayNhap = labelNgayNhap.getText();
    }
}
