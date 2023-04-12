package MODEL;

public class CongtyMD {
    public final String tableName = "congty";

    private String MaCty;
    private String TenCty;
    private String diaChi;
    private String SDT;
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
        return diaChi;
    }
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    public String getSDT() {
        return SDT;
    }
    public void setSDT(String sDT) {
        SDT = sDT;
    }
    @Override
    public String toString() {
        return "(" + MaCty + "," + TenCty + "," + diaChi + "," + SDT + ")";
    }
}
