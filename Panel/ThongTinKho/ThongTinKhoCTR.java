package Panel.ThongTinKho;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

import DAL.DataAccessLayer;
import Model.KhoMD;
import Model.KhuvucMD;
import Model.Model;
import Model.NhanvienMD;
import Model.Taikhoan_nhanvienMD;
import Model.Custom.DSChiTietKhuVucLoaiMD;
import Panel.ThongTinKho.Form.FormThemKhuVuc;
import SQL.SQLUser;

public class ThongTinKhoCTR {
    private ThongTinKhoUI ui;
    private SQLUser user;
    private Taikhoan_nhanvienMD tkDangNhap;

    private String maKhoHT;
    private String tenKhoHT;
    private TableModel currentTable;

    
    public ThongTinKhoCTR(SQLUser user,Taikhoan_nhanvienMD taiKhoanDangNhap,Dimension size){
        this.user=user;
        this.tkDangNhap=taiKhoanDangNhap;
        ui = new ThongTinKhoUI(size);
        
        //lay ten kho dang nhap
        DataAccessLayer<KhoMD> khoDAL = new DataAccessLayer<>(user, KhoMD.class);
        DataAccessLayer<NhanvienMD> nvDAL = new DataAccessLayer<>(user, NhanvienMD.class);
        maKhoHT = nvDAL.getFirst("MaNV = "+tkDangNhap.getMaNV()).getKho_lam_viec();
        tenKhoHT = khoDAL.getFirst("MaKho= "+maKhoHT).getTenKho();
        //lay tong suc chua cua ca kho
        DataAccessLayer<KhuvucMD> kvDAL = new DataAccessLayer<>(user, KhuvucMD.class);
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
        DataAccessLayer<KhuvucMD> kvDAL = new DataAccessLayer<>(user, KhuvucMD.class);
        ArrayList<KhuvucMD> dsKhuVuc = kvDAL.getTable("MaKho = "+maKhoHT);
        String[] columnNames = {"Mã khu vực","Tên khu vực", "Sức chứa"};
        currentTable = new DefaultTableModel(Model.to2DArray(dsKhuVuc,"MaKV","TenKV","SucChua"),columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        ui.setupDanhSachPanel(currentTable,selectedRowListener);
    }

    //action listeners
    private ActionListener themKVListener = new ActionListener() {
        public void actionPerformed(ActionEvent e){
            new FormThemKhuVuc();
        }
    };
    private TableModel chiTietKVLoaiTable;
    private ListSelectionListener selectedRowListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            //lay ma khu vuc duoc chon
            String maKVChon = currentTable.getValueAt(ui.getDanhSachPanel().getSelectedRow(),0).toString();
            //lay ma kho cua tai khoan dang nhap
            DataAccessLayer<NhanvienMD> nhanvienDAL = new DataAccessLayer<>(user, NhanvienMD.class);
            String maKhoDN = nhanvienDAL.getFirst("MaNV="+tkDangNhap.getMaNV()).getKho_lam_viec();
            //lay du lieu de tao bang
            DataAccessLayer<DSChiTietKhuVucLoaiMD> chiTietKVLoaiDAL = new DataAccessLayer<>(user, DSChiTietKhuVucLoaiMD.class);
            String[] columnNames = {"Tên kho","Tên khu vực","Tên loại hàng","Số lượng hiện tại"};
            ArrayList<DSChiTietKhuVucLoaiMD> dsChiTietKVL = chiTietKVLoaiDAL.getTable("kho.MaKho = "+maKhoDN,"khuvuc.MaKV = " + maKVChon);
            if(dsChiTietKVL!=null){
                
                // DataAccessLayer<DSChiTietKhuVucLoaiMD> dsChiTietKVLDAL = new DataAccessLayer<>(user,DSChiTietKhuVucLoaiMD.class);
                // float tongSLHang = 0;
                // for(DSChiTietKhuVucLoaiMD chiTietKVL : dsChiTietKVLDAL.getTable("kho.MaKho = "+maKhoHT)){
                //     tongSLHang+=chiTietKVL.getSoLuongChua();
                // };
                
                //hiện panel table
                ui.getSidePanel().setDisplayTable();
                //thêm dữ liệu vào cho table
                Object[][] data = Model.to2DArray(dsChiTietKVL);
                chiTietKVLoaiTable = new DefaultTableModel(data,columnNames){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };;
                //set table
                ui.getSidePanel().chiTietKhuVucPanel.SetTable(chiTietKVLoaiTable,null);
            }
            else{
                //hiện thông báo bảng trống
                ui.getSidePanel().setDisplayNullMessage();
            }
        }
    };
}