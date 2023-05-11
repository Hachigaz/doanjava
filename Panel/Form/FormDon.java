package Panel.Form;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import BLL.FormDonBLL;
import DTO.ChitietdonnhapMD;
import DTO.ChitietdonxuatMD;
import DTO.CongtyMD;
import DTO.DonNhapMD;
import DTO.DonXuatMD;
import DTO.KhuvucMD;
import DTO.Mat_hangMD;
import Panel.UI;
import Panel.SubPanel.CustomComboBox;
import Panel.SubPanel.TablePanel;
import misc.ThongBaoDialog;
import misc.TitleFrame;

public class FormDon extends TitleFrame {
    private String kieuForm;
    private JLabel labelNgayTaoDon;
    private CustomComboBox comboBox2;
    private JButton addButton;
    private JButton xoaChiTietBtn;

    private JPanel contentPanel = new JPanel();
    private FormDonBLL formDonBLL = new FormDonBLL();
    private DefaultTableModel model;
    private TablePanel ctDonPanel = new TablePanel();
    private ArrayList<DataRow> dsCTDon = new ArrayList<DataRow>();
    public FormDon(String kieuForm){
        this.kieuForm = kieuForm;
        contentPanel.setLayout(new GridLayout(1,2,0,0));
        JPanel panelTop = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        JLabel label1 = new JLabel("Tạo đơn mới");
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
        if(kieuForm.equals("FormNhap")){
            setThemMH_DonNhap();
        }
        else if(kieuForm.equals("FormXuat")){
            setThemMH_DonXuat();
        }

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
        labelNgayTaoDon = new JLabel();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        labelNgayTaoDon.setText(dateFormat.format(new java.util.Date()));
        panel.add(label5);
        panel.add(labelNgayTaoDon);

        model = new DefaultTableModel();
        model.addColumn("Tên mặt hàng");
        model.addColumn("Khu vực chứa");
        model.addColumn("Số lượng");


        addButton.setEnabled(false);
        xoaChiTietBtn = new JButton("Xoá mặt hàng đã chọn");
        xoaChiTietBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = ctDonPanel.getSelectedRow();
                if(selectedRow<0){
                    new ThongBaoDialog("Chưa chọn mặt hàng để xoá", null);
                    return;
                }
                dsCTDon.remove(selectedRow);
                updateTableModel();
                rightPanel.remove(themSPPanel);
                themSPPanel=null;

            }
            
        });

        JButton saveButton = new JButton("Lưu");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lưu dữ liệu vào cơ sở dữ liệu hoặc làm gì đó khác ở đây
                if(dsCTDon.size()==0){
                    new ThongBaoDialog("Chi tiết đơn không được trống", null);
                    return;
                }
                if(kieuForm.equals("FormNhap")){
                    taoDonNhap();
                }
                else if(kieuForm.equals("FormXuat")){
                    taoDonXuat();
                }
                new ThongBaoDialog("Tạo đơn thành công", null);
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
        panelSouth.add(xoaChiTietBtn);

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
        public String MaDonNhap;
        public float soLuong;

        
        public DataRow(Mat_hangMD mh, KhuvucMD kv, String maDonNhap, float soLuong) {
            this.mh = mh;
            this.kv = kv;
            MaDonNhap = maDonNhap;
            this.soLuong = soLuong;
        }


        public DataRow(Mat_hangMD mh, KhuvucMD kv, float soLuong) {
            this.mh = mh;
            this.kv = kv;
            this.soLuong = soLuong;
        }
        
        public String getMaDonNhap() {
            return MaDonNhap;
        }
        public void setSoLuong(float soLuong) {
            this.soLuong = soLuong;
        }
        public float getSoLuong() {
            return soLuong;
        }
    }
    public void updateTableModel(){
        model.setRowCount(0);
        for(DataRow data : dsCTDon){
            model.addRow(new Object[]{data.mh.getTenMH(),data.kv.getMaKV(),data.soLuong});
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
                    inputFields.add(new FormInput("Chọn khu vực muốn chứa", kvCB));
                    kvCB.addItem("Chọn khu vực để chứa","null");
                    ActionListener changeMHAction = new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(!mhCB.getSelectedKey().equals("null")){
                                // if(kvCB.getItemCount()>0){
                                //     kvCB.removeAllItems();
                                // }
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
                            for(DataRow ctkvRow : dsCTDon){
                                if(ctkvRow.kv.getMaKV().equals(kvCB.getSelectedKey())){                                    
                                    tongSLKV+=(float)ctkvRow.getSoLuong();
                                }
                            }
                            if(!kvCB.getSelectedKey().equals("null")){
                                sucChuaLabel.setText("Sức chứa khu vực hiện tại: "+tongSLKV+"/"+formDonBLL.getFirstKV(kvCB.getSelectedKey()).getSucChua());
                            }
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
                            //lấy tổng số lượng
                            float tongSLKV = formDonBLL.getSoLuongCL_KV(kvCB.getSelectedKey());
                            for(DataRow ctkvRow : dsCTDon){
                                if(ctkvRow.kv.getMaKV().equals(kvCB.getSelectedKey())){                                    
                                    tongSLKV+=(float)ctkvRow.getSoLuong();
                                }
                            }
                            KhuvucMD kv = formDonBLL.getFirstKV(kvCB.getSelectedKey());
                            if(soLuong+tongSLKV>kv.getSucChua()){
                                new ThongBaoDialog("Khu vực không đủ sức chứa", null);
                                return;
                            }
                            boolean timThay = false;
                            for(DataRow r : dsCTDon){
                                if(r.mh.getMaMH().equals(mhCB.getSelectedKey())&&r.kv.getMaKV().equals(kvCB.getSelectedKey())){
                                    r.setSoLuong(r.getSoLuong()+soLuong);
                                    timThay=true;
                                }
                            }
                            if(!timThay){
                                dsCTDon.add(new DataRow(formDonBLL.getFirstMH(mhCB.getSelectedKey()),formDonBLL.getFirstKV(kvCB.getSelectedKey()), soLuong));
                            }
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
        String ngayNhap = labelNgayTaoDon.getText();

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
                    JPanel panelChonContent = new JPanel();
                    panelChonContent.setLayout(new BoxLayout(panelChonContent, BoxLayout.Y_AXIS));
                    TablePanel panelChonSP = new TablePanel();


                    JLabel labelChonSP = new JLabel("Chọn sản phẩm muốn xuất");
                    labelChonSP.setFont(new Font("Helvetica", Font.BOLD, 20));
                    panelChonContent.add(labelChonSP);
                    panelChonContent.add(Box.createVerticalGlue()); // Add glue component
            

                    SpinnerNumberModel soLuongSpinnerModel = new SpinnerNumberModel(0,0,0,50);
                    JSpinner soLuongSpinner = new JSpinner(soLuongSpinnerModel);
                    soLuongSpinner.setPreferredSize(new Dimension(100,30));
                    soLuongSpinner.setEnabled(false);


                    String[] columnNames = {"Tên sản phẩm","Khu vực","Số lượng","Đơn nhập","Ngày nhập"};
                    DefaultTableModel tableChonSP = new DefaultTableModel();
                    for(String columnName:columnNames){
                        tableChonSP.addColumn(columnName);
                    }
                    ArrayList<Object[]> dsMHChon = formDonBLL.getDanhSachMHChon(comboBox2.getSelectedKey());
                    if(dsMHChon!=null){
                        for(Object[] mh :dsMHChon){
                            float soLuong = (float)mh[3];
                            for(DataRow r :dsCTDon){
                                if(r.mh.getMaMH().equals(mh[0])&&r.kv.getMaKV().equals(mh[2])&&r.getMaDonNhap().equals(mh[4])){
                                    soLuong-=r.getSoLuong();
                                }
                            }
                            tableChonSP.addRow(new Object[]{mh[1],mh[2],soLuong,mh[4],mh[5]});
                        }
                    }
                    ListSelectionListener selectedMHListener = new ListSelectionListener() {
                        @Override
                        public void valueChanged(ListSelectionEvent e) {
                            int soLuongMax = (int)Float.parseFloat(tableChonSP.getValueAt(panelChonSP.getSelectedRow(),2).toString());
                            soLuongSpinnerModel.setMaximum(soLuongMax);
                            if(!soLuongSpinner.isEnabled()){
                                soLuongSpinner.setEnabled(true);
                            }
                        }
                    };
                    panelChonSP.SetTable(tableChonSP, selectedMHListener);
                    panelChonSP.getTableDS().setPreferredSize(new Dimension(panelChonSP.getTableDS().getPreferredSize().width, 400));
                    panelChonContent.add(panelChonSP);
                    panelChonContent.add(Box.createVerticalGlue()); // Add glue component
                    //panelChonContent.setPreferredSize(new Dimension(panelChonContent.getPreferredSize().width,400));
                    JPanel panelSpinnerInput = new JPanel();
                    panelSpinnerInput.add(new JLabel("Chọn số lượng xuất"));
                    panelSpinnerInput.add(soLuongSpinner);
                    panelChonContent.add(panelSpinnerInput);
                    panelChonContent.add(Box.createVerticalStrut(10));

                    JPanel panelSubmitInput = new JPanel(new FlowLayout(FlowLayout.CENTER, 20,0));
                    JButton submitButton = new JButton("Thêm vào đơn xuất");
                    submitButton.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(panelChonSP.getSelectedRow()<0){
                                new ThongBaoDialog("Vui lòng chọn sản phẩm để xuất", null);
                                return;
                            }
                            int soLuongXuat = Integer.parseInt(soLuongSpinner.getValue().toString());
                            if(soLuongXuat==0){
                                new ThongBaoDialog("Vui lòng chọn giá trị lớn hơn 0", null);
                                return;
                            }

                            int selectedRow = panelChonSP.getSelectedRow();


                            Mat_hangMD mhChon = formDonBLL.getFirstMH(((dsMHChon.get(selectedRow))[0]).toString());
                            KhuvucMD kvChon = formDonBLL.getFirstKV(((dsMHChon.get(selectedRow)[2])).toString());
                            String maDonChon = dsMHChon.get(selectedRow)[4].toString();

                            
                            Float SLConLai = (Float)dsMHChon.get(selectedRow)[3];
                            System.out.println(SLConLai);
                            int tongSLXuat = soLuongXuat;
                            for(DataRow r: dsCTDon){
                                if(r.getMaDonNhap().equals(maDonChon)&&r.mh.getMaMH().equals(mhChon.getMaMH())&&r.kv.getMaKV().equals(kvChon.getMaKV())){
                                    tongSLXuat+=r.getSoLuong();
                                }
                            }
                            System.out.println(tongSLXuat);
                            if(tongSLXuat>SLConLai){
                                ThongBaoDialog tb = new ThongBaoDialog("Không đủ số hàng để xuất cho mặt hàng "+mhChon.getTenMH()+" trong " + kvChon.getTenKV() +" thuộc đơn nhập "+maDonChon, null);
                                tb.setSize(400, 300);
                                return;
                            }



                            boolean timThay = false;
                            for(DataRow r:dsCTDon){
                                if(r.getMaDonNhap().equals(maDonChon)&&r.mh.getMaMH().equals(mhChon.getMaMH())&&r.kv.getMaKV().equals(kvChon.getMaKV())){
                                    r.setSoLuong(r.getSoLuong()+soLuongXuat);
                                    timThay=true;
                                }
                            }
                            if(!timThay){
                                dsCTDon.add(new DataRow(mhChon, kvChon, maDonChon, soLuongXuat));
                            }
                            updateTableModel();
                            rightPanel.remove(themSPPanel);
                            themSPPanel=null;
                        }
                        
                    });
                    JButton cancelButton = new JButton("Huỷ");
                    cancelButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            rightPanel.remove(themSPPanel);
                            themSPPanel=null;
                        }
                    });
                    panelSubmitInput.add(submitButton);
                    panelSubmitInput.add(cancelButton);
                    panelChonContent.add(panelSubmitInput);
                    themSPPanel=panelChonContent;
                    rightPanel.add(themSPPanel);
                    rightPanel.revalidate();
                }
            }
        });
    }
    private void taoDonXuat(){
        String maDon = formDonBLL.taoMaDonXuatMoi();
        System.out.println(maDon);
        String maKho = UI.khoNVDangNhap.getMaKho();
        String maCongTy = comboBox2.getSelectedKey();
        String maNhanVien = UI.nvDangNhap.getMaNV();
        String ngayXuat = labelNgayTaoDon.getText();

        DonXuatMD donXuatMoi = new DonXuatMD(maDon, maKho, maCongTy, maNhanVien, ngayXuat);
        ArrayList<ChitietdonxuatMD> dsCTDX = new ArrayList<ChitietdonxuatMD>();
        for(DataRow r:dsCTDon){
            dsCTDX.add(new ChitietdonxuatMD(maDon, r.getMaDonNhap(),r.mh.getMaMH(),r.kv.getMaKV(),r.getSoLuong()));
        }
        formDonBLL.themDonXuatMoi(donXuatMoi, dsCTDX);
    }
}
