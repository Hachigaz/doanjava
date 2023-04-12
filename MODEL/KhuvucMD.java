package Model;

public class KhuvucMD implements Model {
    public static final String tableName = "khuvuc_loaihang";

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
    public String toSQLString() {
        return "(" + MaKho + "," + MaKV + "," + TenKV + "," + SucChua + ")";
    }
}
