package MODEL;

public class ChitietdonnhapMD {
    public final String tableName = "chitiet_donnhap";
    @Override
    public String toString() {
        return "(" + MaDonNhap + "," + MaMH + "," + MaKV + "," + SLNhap
                + "," + SLConLai + ")";
    }
    private String MaDonNhap;
    private String MaMH;
    private String MaKV;
    private float SLNhap;
    private float SLConLai;
    public String getMaDonNhap() {
        return MaDonNhap;
    }
    public void setMaDonNhap(String maDonNhap) {
        MaDonNhap = maDonNhap;
    }
    public String getMaMH() {
        return MaMH;
    }
    public void setMaMH(String maMH) {
        MaMH = maMH;
    }
    public String getMaKV() {
        return MaKV;
    }
    public void setMaKV(String maKV) {
        MaKV = maKV;
    }
    public float getSLNhap() {
        return SLNhap;
    }
    public void setSLNhap(float sLNhap) {
        SLNhap = sLNhap;
    }
    public float getSLConLai() {
        return SLConLai;
    }
    public void setSLConLai(float sLConLai) {
        SLConLai = sLConLai;
    }
}
