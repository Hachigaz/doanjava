package Model;

<<<<<<< Updated upstream:Model/Taikhoan_nhanvienMD.java
public class Taikhoan_nhanvienMD extends Model{
    public static final String selectStatement = "*";
    public static final String fromStatement = "taikhoan_nhanvien";
        
=======
public class Taikhoan_nhanvien {
    public final String tableName = "taikhoan_nhanvien";
>>>>>>> Stashed changes:MODEL/Taikhoan_nhanvien.java
    private String MaNV;
    private String TenTaiKhoan;
    private String MatKhau;
    private String MaNhomQuyen;
<<<<<<< Updated upstream:Model/Taikhoan_nhanvienMD.java

    
    public Taikhoan_nhanvienMD(String maNV, String tenTaiKhoan, String matKhau, String maNhomQuyen) {
        MaNV = maNV;
        TenTaiKhoan = tenTaiKhoan;
        MatKhau = matKhau;
        MaNhomQuyen = maNhomQuyen;
    }
=======
>>>>>>> Stashed changes:MODEL/Taikhoan_nhanvien.java
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
<<<<<<< Updated upstream:Model/Taikhoan_nhanvienMD.java
    public String toSQLString() {
        return "('"+MaNV + "','" + TenTaiKhoan + "','" + MatKhau + "','" + MaNhomQuyen + "')";
=======
    public String toString() {
        return "("+MaNV + "," + TenTaiKhoan + "," + MatKhau + "," + MaNhomQuyen + ")";
>>>>>>> Stashed changes:MODEL/Taikhoan_nhanvien.java
    }
}
