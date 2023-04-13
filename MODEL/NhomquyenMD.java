package Model;

public class NhomquyenMD extends Model{
    public static final String selectStatement = "*";
    public static final String fromStatement = "nhomquyen";
    
    private String MaNhomQuyen;
    private String TenNhomQuyen;

    
    public NhomquyenMD(String maNhomQuyen, String tenNhomQuyen) {
        MaNhomQuyen = maNhomQuyen;
        TenNhomQuyen = tenNhomQuyen;
    }
    public String getMaNhomQuyen() {
        return MaNhomQuyen;
    }
    public void setMaNhomQuyen(String maNhomQuyen) {
        MaNhomQuyen = maNhomQuyen;
    }
    public String getTenNhomQuyen() {
        return TenNhomQuyen;
    }
    public void setTenNhomQuyen(String tenNhomQuyen) {
        TenNhomQuyen = tenNhomQuyen;
    }
    public String getSelectStatement(){
        return NhomquyenMD.selectStatement;
    }
    public String getFromStatement(){
        return NhomquyenMD.fromStatement;
    }
    @Override
    public String toSQLString() {
        return "('" + MaNhomQuyen + "','" + TenNhomQuyen + "')";
    }
}
