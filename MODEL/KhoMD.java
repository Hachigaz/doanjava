package Model;

public class KhoMD extends Model{
    public static final String selectStatement = "*";
    public static final String fromStatement = "kho";
    public static final String groupByStatement = "";

    private String MaKho;
    private String TenKho;
    private String DiaChi;
    
    public KhoMD(String maKho, String tenKho, String diaChi) {
        MaKho = maKho;
        TenKho = tenKho;
        DiaChi = diaChi;
    }
    public String getMaKho() {
        return MaKho;
    }
    public void setMaKho(String maKho) {
        MaKho = maKho;
    }
    public String getTenKho() {
        return TenKho;
    }
    public void setTenKho(String tenKho) {
        TenKho = tenKho;
    }
    public String getDiaChi() {
        return DiaChi;
    }
    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }
    public String getSelectStatement(){
        return KhoMD.selectStatement;
    }
    public String getFromStatement(){
        return KhoMD.fromStatement;
    }
    @Override
    public String toSQLString() {
        return "('" + MaKho + "','" + TenKho + "','" + DiaChi + "')";
    }
}
