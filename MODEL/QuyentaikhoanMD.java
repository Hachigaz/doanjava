package Model;

public class QuyentaikhoanMD implements Model{
    public static final String tableName = "quyentaikhoan";

    private String MaQuyen;
    private String TenQuyen;

    
    public QuyentaikhoanMD(String maQuyen, String tenQuyen) {
        MaQuyen = maQuyen;
        TenQuyen = tenQuyen;
    }
    
    public String getMaQuyen() {
        return MaQuyen;
    }
    public void setMaQuyen(String maQuyen) {
        MaQuyen = maQuyen;
    }
    public String getTenQuyen() {
        return TenQuyen;
    }
    public void setTenQuyen(String tenQuyen) {
        TenQuyen = tenQuyen;
    }
    @Override
    public String toSQLString() {
        return "(" + MaQuyen + "," + TenQuyen + ")";
    }
}
