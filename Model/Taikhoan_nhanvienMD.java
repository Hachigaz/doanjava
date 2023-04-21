package Model;

public class Taikhoan_nhanvienMD extends Model{
    public static final String selectStatement = "*";
    public static final String fromStatement = "taikhoan_nhanvien";
    public static final String groupByStatement = "";
        
    private String MaNV;
    private String TenTaiKhoan;
    private String MatKhau;
    private String MaNhomQuyen;

    
    public Taikhoan_nhanvienMD(String maNV, String tenTaiKhoan, String matKhau, String maNhomQuyen) {
        MaNV = maNV;
        TenTaiKhoan = tenTaiKhoan;
        MatKhau = matKhau;
        MaNhomQuyen = maNhomQuyen;
    }
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
    public String getSelectStatement() {
        return Taikhoan_nhanvienMD.selectStatement;
    }
    @Override
    public String getFromStatement(){
        return Taikhoan_nhanvienMD.fromStatement;
    }
    @Override
    public String toSQLString() {
        return "('"+MaNV + "','" + TenTaiKhoan + "','" + MatKhau + "','" + MaNhomQuyen + "')";
    }
}
