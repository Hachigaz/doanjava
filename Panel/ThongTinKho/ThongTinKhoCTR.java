package Panel.ThongTinKho;

import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

import DAL.DataAccessLayer;
import Model.*;
import Model.Custom.*;
import Panel.ThongTinKho.Form.*;
import SQL.SQLUser;
import misc.ThongBaoDialog;
import misc.util;

public class ThongTinKhoCTR {
    private ThongTinKhoUI ui;
    private Taikhoan_nhanvienMD tkDangNhap;

    private String maKhoHT;
    private String tenKhoHT;
    private TableModel currentTable;

    
    private DataAccessLayer<KhoMD> khoDAL;
    private DataAccessLayer<NhanvienMD> nvDAL;
    private DataAccessLayer<KhuvucMD> kvDAL;
    private DataAccessLayer<DSChiTietKhuVucLoaiMD>  chiTietKVLoaiDAL;
    private DataAccessLayer<Loai_hangMD> loaiHangDAL;
    private DataAccessLayer<Khuvuc_loaihangMD> khuVucLoaiDAL;

    public ThongTinKhoCTR(SQLUser user,Taikhoan_nhanvienMD taiKhoanDangNhap,Dimension size){
        khoDAL = new DataAccessLayer<>(user, KhoMD.class);
        nvDAL = new DataAccessLayer<>(user, NhanvienMD.class);
        kvDAL = new DataAccessLayer<>(user, KhuvucMD.class);
        chiTietKVLoaiDAL = new DataAccessLayer<>(user, DSChiTietKhuVucLoaiMD.class);
        loaiHangDAL = new DataAccessLayer<>(user, Loai_hangMD.class);
        khuVucLoaiDAL = new DataAccessLayer<>(user, Khuvuc_loaihangMD.class);

        this.tkDangNhap=taiKhoanDangNhap;
        ui = new ThongTinKhoUI(size);
        
        //lay ten kho dang nhap
        maKhoHT = nvDAL.getFirst("MaNV = "+tkDangNhap.getMaNV()).getKho_lam_viec();
        tenKhoHT = khoDAL.getFirst("MaKho= "+maKhoHT).getTenKho();
        //lay tong suc chua cua ca kho
        Float tongSucChua = 0.0f;
        for(KhuvucMD kv : kvDAL.getTable("MaKho = "+maKhoHT)){
            tongSucChua+=kv.getSucChua();
        }
        ui.getHeaderPanel().setupPanel(tenKhoHT,tongSucChua.toString(),this.themKVListener);
        
        this.updateTable();
    }
    public ThongTinKhoUI getUI() {
        return ui;
    }
    private void updateTable(){
        ArrayList<KhuvucMD> dsKhuVuc = kvDAL.getTable("MaKho = "+maKhoHT);
        String[] columnNames = {"Mã khu vực","Tên khu vực", "Sức chứa"};
        currentTable = new DefaultTableModel(Model.to2DArray(dsKhuVuc,"MaKV","TenKV","SucChua"),columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        ui.setupDanhSachPanel(currentTable,selectedKhuVucRowListener);
    }

    //update bảng CTKVL
    private void UpdateCTKVLTable(){
        //lay ma khu vuc duoc chon
        String maKVChon = layMaKVSelected();
        //lay ma kho cua tai khoan dang nhap
        String maKhoDN = nvDAL.getFirst("MaNV="+tkDangNhap.getMaNV()).getKho_lam_viec();
        //lay du lieu de tao bang
        String[] columnNames = {"Khu vực","Tên loại hàng","Mức chứa hiện tại"};
        dsChiTietKVL = chiTietKVLoaiDAL.getTable("kho.MaKho = "+maKhoDN,"khuvuc.MaKV = " + maKVChon);
        if(dsChiTietKVL!=null){
            
            // float tongSLHang = 0;
            // for(DSChiTietKhuVucLoaiMD chiTietKVL : dsChiTietKVLDAL.getTable("kho.MaKho = "+maKhoHT)){
            //     tongSLHang+=chiTietKVL.getSoLuongChua();
            // };
            
            //hiện panel table
            ui.getSidePanel().setDisplayTable();
            //thêm dữ liệu vào cho table
            Object[][] data = Model.to2DArray(dsChiTietKVL,"TenKhuVuc","TenLoai","SoLuongChua");
            TableModel chiTietKVLoaiTable = new DefaultTableModel(data,columnNames){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            //set table
            ui.getSidePanel().chiTietKhuVucPanel.SetTable(chiTietKVLoaiTable,selectedCTKVAction);


            //action cho nút thêm loại vào khu vực
            ActionListener themCTKVLoaiAction = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //hiện form thêm ctkv
                    ui.getSidePanel().setDisplayThemCTKVPanel();
                    Object[][] dsLoaiHang  = util.flip2dArray(Model.to2DArray(loaiHangDAL.getTable(),"MaLoai","TenLoai"));
                    ActionListener submitAction = new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            String TenLoai = ui.getSidePanel().getSelectedItemInComboBox();
                            for(Loai_hangMD loaiHang : loaiHangDAL.getTable()){
                                if(TenLoai.equals(loaiHang.getTenloai())){
                                    //duyệt trong dsctkv hiện tại coi nó có tồn tại trong csdl chưa
                                    String maKVHienTai = layMaKVSelected();
                                    boolean timThay = false;
                                    ArrayList<Khuvuc_loaihangMD> dsKhuVucLoai = khuVucLoaiDAL.getTable("MaKV="+maKVHienTai);
                                    for(Khuvuc_loaihangMD kvLoai : dsKhuVucLoai){
                                        if(kvLoai.getMaLoai().equals(loaiHang.getMaLoai())){
                                            timThay=true;
                                        }
                                    }
                                    if(!timThay){
                                        khuVucLoaiDAL.addOne(new Khuvuc_loaihangMD(maKVHienTai, loaiHang.getMaLoai()));
                                        new ThongBaoDialog("Thêm loại hàng vào khu vực "+ layTenKVSelected() +" thành công", null);
                                        UpdateCTKVLTable();
                                    }
                                    else{
                                        new ThongBaoDialog("Loại hàng muốn thêm đã có trong khu vực", null);
                                        UpdateCTKVLTable();
                                    }
                                }
                            }
                        }
                    };
                    ActionListener cancelAction = new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            UpdateCTKVLTable();
                        }
                    };
                    ui.getSidePanel().setupThemCTKVForm(util.objToString(dsLoaiHang[1]),submitAction,cancelAction);
                }
            };
            //thêm listener cho nút thêm khu vực
            ui.getSidePanel().setupThemButton(themCTKVLoaiAction);
            //reset nút xoá
            ui.getSidePanel().setupXoaButton(null);
        }
        else{
            //hiện thông báo bảng trống
            ui.getSidePanel().setDisplayNullMessage();
        }
    }
    private ArrayList<DSChiTietKhuVucLoaiMD> dsChiTietKVL;
    //lấy mã khu vực được chọn trong bảng
    private String layMaKVSelected(){
        return currentTable.getValueAt(ui.getDanhSachPanel().getSelectedRow(),0).toString();
    }
    private String layTenKVSelected(){
        return currentTable.getValueAt(ui.getDanhSachPanel().getSelectedRow(),1).toString();
    }
    //==================action listeners=========================
    private ActionListener themKVListener = new ActionListener() {
        public void actionPerformed(ActionEvent e){
            Window mainWindow = SwingUtilities.getWindowAncestor(ui);
            mainWindow.setEnabled(false);

            ArrayList<FormInput> inputFields = new ArrayList<FormInput>();
            inputFields.add(new FormInput("Tên khu vực",new JTextField("",20)));
            inputFields.add(new FormInput("Sức chứa",new JTextField("",20)));
            
            ActionListener themKVSubmitListener = new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    String maKho = nvDAL.getFirst("MaNV = "+tkDangNhap.getMaNV()).getKho_lam_viec();
                    DecimalFormat df = new DecimalFormat("000");
                    String maKVMoi = maKho + "_" +df.format(kvDAL.getTable("MaKho ="+maKho).size()+1);
                    System.out.println(maKVMoi);
                    JTextField tenKVField = (JTextField)(inputFields.get(0).getInputComponent());
                    String tenKV = tenKVField.getText();
                    Float soLuong = 0.0f;
                    try{
                        JTextField soLuongField = (JTextField)(inputFields.get(1).getInputComponent());
                        soLuong = Float.parseFloat(soLuongField.getText());
                        if(soLuong > 0){
                            kvDAL.addOne(new KhuvucMD(maKho, maKVMoi, tenKV, soLuong));
                            new ThongBaoDialog("Thêm khu vực thành công", null);
                            Window formThemDialog = SwingUtilities.getWindowAncestor((JComponent)e.getSource());
                            formThemDialog.dispose();
                            mainWindow.setEnabled(true);
                            mainWindow.setAlwaysOnTop(true);
                            mainWindow.setAlwaysOnTop(false);
                            updateTable();
                        }
                        else{
                            new ThongBaoDialog("Số lượng nhập vào phải lớn hơn 0", null);
                        }
                    }
                    catch(NumberFormatException exception){
                        new ThongBaoDialog("Số lượng nhập vào không phải là số",null);
                    }
                }
            };
            ActionListener themKVCancelListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    Window formThemDialog = SwingUtilities.getWindowAncestor((JComponent)e.getSource());
                    formThemDialog.dispose();
                    mainWindow.setEnabled(true);
                    mainWindow.setAlwaysOnTop(true);
                    mainWindow.setAlwaysOnTop(false);
                }
            };
            new FormThem("Thêm khu vực mới",inputFields,themKVSubmitListener,themKVCancelListener);
            
        }
    };
    //listener khi chọn vào một dòng trong bảng khu vực
    private ListSelectionListener selectedKhuVucRowListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            UpdateCTKVLTable();
        }
    };
    //action cho chọn một dòng trong chi tiet kv
    private ListSelectionListener selectedCTKVAction = new ListSelectionListener(){
        @Override
        public void valueChanged(ListSelectionEvent e) {
            //xoá một ctkv trong khu vực
            ActionListener xoaCTKVAction = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRowIndex = ui.getSidePanel().chiTietKhuVucPanel.getSelectedRow();
                    float soLuong = Float.parseFloat(dsChiTietKVL.get(selectedRowIndex).getSoLuongChua().toString());
                    if(soLuong>0){
                        new ThongBaoDialog("Không thể xoá chi tiết khu vực còn hàng",null);
                    }
                    else{
                        String maKVDelete = dsChiTietKVL.get(selectedRowIndex).getMaKV().toString();
                        String maLoaiDelete = dsChiTietKVL.get(selectedRowIndex).getMaLoai();
                        khuVucLoaiDAL.remove("MaKV = "+maKVDelete,"MaLoai="+maLoaiDelete);
                        new ThongBaoDialog("Xoá thành công",null);
                        UpdateCTKVLTable();
                    }
                    
                }
                
            };
            ui.getSidePanel().setupXoaButton(xoaCTKVAction);
        }
    };
}