package Model;

public class KhuvucMD extends Model {
    public static final String selectStatement = "*";
    public static final String fromStatement = "khuvuc";
    public static final String groupByStatement = "";

    private String MaKho;
    private String MaKV;
    private String TenKV;
    private Float SucChua;
    
    public KhuvucMD(String maKho, String maKV, String tenKV, Float sucChua) {
        MaKho = maKho;
        MaKV = maKV;
        TenKV = tenKV;
        SucChua = sucChua;
    }
    public String getMaKho() {
        return MaKho;
    }
    public void setMaKho(String maKho) {
        MaKho = maKho;
    }
    public String getMaKV() {
        return MaKV;
    }
    public void setMaKV(String maKV) {
        MaKV = maKV;
    }
    public String getTenKV() {
        return TenKV;
    }
    public void setTenKV(String tenKV) {
        TenKV = tenKV;
    }
    public Float getSucChua() {
        return SucChua;
    }
    public void setSucChua(Float SucChua) {
        this.SucChua = SucChua;
    }
    @Override
    public String getSelectStatement(){
        return KhuvucMD.selectStatement;
    }
    @Override
    public String getFromStatement(){
        return KhuvucMD.fromStatement;
    }
    public String toSQLString() {
        return "('" + MaKho + "','" + MaKV + "','" + TenKV + "'," + SucChua + ")";
    }
}
