package Model;

public class CongtyMD extends Model{
    public static final String selectStatement = "*";
    public static final String fromStatement = "cong_ty";
    public static final String groupByStatement = "";
    
    private String MaCty;
    private String TenCty;
    private String DiaChi;
    private String SDT;

    
    
    public CongtyMD(String maCty, String tenCty, String diaChi, String sDT) {
        MaCty = maCty;
        TenCty = tenCty;
        DiaChi = diaChi;
        SDT = sDT;
    }

    public String getMaCty() {
        return MaCty;
    }
    public void setMaCty(String maCty) {
        MaCty = maCty;
    }
    public String getTenCty() {
        return TenCty;
    }
    public void setTenCty(String tenCty) {
        TenCty = tenCty;
    }
    public String getDiaChi() {
        return DiaChi;
    }
    public void setDiaChi(String diaChi) {
        this.DiaChi = diaChi;
    }
    public String getSDT() {
        return SDT;
    }
    public void setSDT(String DDT) {
        this.SDT = DDT;
    }
    
    public String getSelectStatement(){
        return CongtyMD.selectStatement;
    }
    public String getFromStatement(){
        return CongtyMD.fromStatement;
    }
    @Override
    public String toSQLString() {
        return "('" + MaCty + "','" + TenCty + "','" + DiaChi + "','" + SDT + "')";
    }
}
