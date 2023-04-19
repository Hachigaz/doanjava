package Panel.ThongTinKho;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.table.*;

import DAL.DataAccessLayer;
import Kho.KhuVuc;
import Model.KhoMD;
import Model.KhuvucMD;
import Model.Model;
import Model.NhanvienMD;
import Model.Taikhoan_nhanvienMD;
import SQL.SQLUser;

public class ThongTinKhoCTR {
    private ThongTinKhoUI ui;
    private SQLUser user;
    private Taikhoan_nhanvienMD taiKhoanDangNhap;

    private String maKhoHT;
    private String tenKhoHT;

    public ThongTinKhoCTR(SQLUser user,Taikhoan_nhanvienMD taiKhoanDangNhap,Dimension size){
        this.user=user;
        this.taiKhoanDangNhap=taiKhoanDangNhap;
        ui = new ThongTinKhoUI(size);
        
        //lay ten kho dang nhap
        DataAccessLayer<KhoMD> khoDAO = new DataAccessLayer<>(user, KhoMD.class);
        DataAccessLayer<NhanvienMD> nvDAO = new DataAccessLayer<>(user, NhanvienMD.class);
        maKhoHT = nvDAO.getFirst("MaNV = "+taiKhoanDangNhap.getMaNV()).getKho_lam_viec();
        tenKhoHT = khoDAO.getFirst("MaKho= "+maKhoHT).getTenKho();

        ui.setupSubPanel(tenKhoHT);
        
        DataAccessLayer<KhuvucMD> kvDAO = new DataAccessLayer<>(user, KhuvucMD.class);
        ArrayList<KhuvucMD> dsKhuVuc = kvDAO.getTable("MaKho = "+maKhoHT);

        ui.setupDanhSachPanel(new DefaultTableModel(Model.to2DArray(dsKhuVuc),kvDAO.getReturnedColumnLabel()));
    }
    public ThongTinKhoUI getUI() {
        return ui;
    }
}