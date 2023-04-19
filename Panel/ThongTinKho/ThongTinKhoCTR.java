package Panel.ThongTinKho;

import java.awt.Dimension;

import DAL.DataAccessLayer;
import Model.KhoMD;
import Model.NhanvienMD;
import Model.Taikhoan_nhanvienMD;
import SQL.SQLUser;

public class ThongTinKhoCTR {
    private ThongTinKhoUI ui;
    private SQLUser user;
    private Taikhoan_nhanvienMD taiKhoanDangNhap;
    public ThongTinKhoCTR(SQLUser user,Taikhoan_nhanvienMD taiKhoanDangNhap,Dimension size){
        this.user=user;
        this.taiKhoanDangNhap=taiKhoanDangNhap;
        ui = new ThongTinKhoUI(size);
        
        //lay ten kho dang nhap
        DataAccessLayer<KhoMD> khoDAO = new DataAccessLayer<>(user, KhoMD.class);
        DataAccessLayer<NhanvienMD> nvDAO = new DataAccessLayer<>(user, NhanvienMD.class);
        String maKhoNV = nvDAO.getFirst("MaNV = "+taiKhoanDangNhap.getMaNV()).getKho_lam_viec();
        String tenKhoNV = khoDAO.getFirst("MaKho= "+maKhoNV).getTenKho();

        ui.setupSubPanel(tenKhoNV);
    }
    public ThongTinKhoUI getUI() {
        return ui;
    }
}