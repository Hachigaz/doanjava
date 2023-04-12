package Model;

public class Taikhoan_nhanvien {
    public final String tableName = "taikhoan_nhanvien";
    private String MaNV;
    private String TenTaiKhoan;
    private String MatKhau;
    private String MaNhomQuyen;
    public String getMaNV() {
        return MaNV;
    }
    public void setMaNV(String maNV) {
        MaNV = maNV;
    }
    public String getTenTaiKhoan() {
        return TenTaiKhoan;
    }
    public void setTenTaiKhoan(String tenTaiKhoan) {
        TenTaiKhoan = tenTaiKhoan;
    }
    public String getMatKhau() {
        return MatKhau;
    }
    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }
    public String getMaNhomQuyen() {
        return MaNhomQuyen;
    }
    public void setMaNhomQuyen(String maNhomQuyen) {
        MaNhomQuyen = maNhomQuyen;
    }
    @Override
    public String toSQLString() {
        return "("+MaNV + "," + TenTaiKhoan + "," + MatKhau + "," + MaNhomQuyen + ")";
    }
}
