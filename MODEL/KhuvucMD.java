package MODEL;

public class KhuvucMD {
    public final String tableName = "khuvuc_loaihang";

    private String MaKho;
    private String MaKV;
    private String TenKV;
    private String Succhua;
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
    public String getSucchua() {
        return Succhua;
    }
    public void setSucchua(String succhua) {
        Succhua = succhua;
    }
    @Override
    public String toString() {
        return "(" + MaKho + "," + MaKV + "," + TenKV + "," + Succhua + ")";
    }
}
