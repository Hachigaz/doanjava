package Model;

public class NhomquyenMD {
    public final String tableName = "nhomquyen";
    
    private String MaNhomQuyen;
    private String TenNhomQuyen;
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
    @Override
    public String toSQLString() {
        return "(" + MaNhomQuyen + "," + TenNhomQuyen + ")";
    }
}
