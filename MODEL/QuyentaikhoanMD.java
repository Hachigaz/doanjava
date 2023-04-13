package Model;

public class QuyentaikhoanMD extends Model{
    public static final String selectStatement = "*";
    public static final String fromStatement = "quyentaikhoan";

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
    public String getSelectStatement(){
        return QuyentaikhoanMD.selectStatement;
    }
    public String getFromStatement(){
        return QuyentaikhoanMD.fromStatement;
    }
    @Override
    public String toSQLString() {
        return "('" + MaQuyen + "','" + TenQuyen + "')";
    }
}
