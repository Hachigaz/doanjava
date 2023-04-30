package DTO.Custom;
import DTO.*;

public class DSNhanVienMD extends Model{
    
    public static final String selectStatement = "nv.MaNV as 'Mã nhân viên',nv.TenNV as 'Tên nhân viên',cv.TenCV as 'Chức vụ',nv.GioiTinh as 'Giới tính',DATE_FORMAT(NgaySinh, '%d/%m/%Y') as 'Ngày sinh',nv.DiaChi as 'Địa chỉ',kho.TenKho as 'Kho làm việc'";
    public static final String fromStatement = "nhanvien as nv join chucvu as cv on cv.MaCV = nv.MaCV join kho on kho.MaKho = nv.Kho_lam_viec";//where nv.MaNV not in ('ADMIN')
    public static final String groupByStatement = "";
   
    private String MaNV;
    private String TenNV;
    private String TenCV;
    private String GioiTinh;
    private String NgaySinh;
    private String DiaChi;
    private String TenKho;
    public DSNhanVienMD(String maNV, String tenNV, String chucVu, String gioiTinh, String ngaySinh, String diaChi,String khoLamViec) {
        MaNV = maNV;
        TenNV = tenNV;
        TenCV = chucVu;
        GioiTinh = gioiTinh;
        NgaySinh = ngaySinh;
        DiaChi = diaChi;
        TenKho = khoLamViec;
    }

    public static String getSelectstatement() {
        return selectStatement;
    }

    public static String getFromstatement() {
        return fromStatement;
    }
    public String getMaNV() {
        return MaNV;
    }
    public void setMaNV(String maNV) {
        MaNV = maNV;
    }
    public String getTenNV() {
        return TenNV;
    }
    public void setTenNV(String tenNV) {
        TenNV = tenNV;
    }
    public String getTenCV() {
        return TenCV;
    }
    public void setTenCV(String tenCV) {
        TenCV = tenCV;
    }
    public String getGioiTinh() {
        return GioiTinh;
    }
    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }
    public String getNgaySinh() {
        return NgaySinh;
    }
    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }
    public String getDiaChi() {
        return DiaChi;
    }
    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }
    public String getTenKho() {
        return TenKho;
    }
    public void setTenKho(String tenKho) {
        TenKho = tenKho;
    }

    @Override
    public String getSelectStatement() {
        return DSTraCuuHangMD.selectStatement;
    }
    
    @Override
    public String getFromStatement() {
        return DSTraCuuHangMD.fromStatement;
    }

    @Override
    public String toSQLString() {
        String returnString = "('"+MaNV+"','"+TenNV+"',"+TenCV+",'"+GioiTinh+"','"+NgaySinh+"','"+DiaChi+"','"+TenKho+"')";
        return returnString;
    }
}
