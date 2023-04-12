package MODEL;

public class Mat_hangMD {
    public final String tableName = "mat_hang";

    @Override
    public String toString() {
        return "(" + MaMH + "," + MaCty + "," + MaLoai + "," + TenMH
                + "," + SoLuongMoiThung + ")";
    }
    private String MaMH;
    private String MaCty;
    private String MaLoai;
    private String TenMH;
    private int SoLuongMoiThung;
    public String getMaMH() {
        return MaMH;
    }
    public void setMaMH(String maMH) {
        MaMH = maMH;
    }
    public String getMaCty() {
        return MaCty;
    }
    public void setMaCty(String maCty) {
        MaCty = maCty;
    }
    public String getMaLoai() {
        return MaLoai;
    }
    public void setMaLoai(String maLoai) {
        MaLoai = maLoai;
    }
    public String getTenMH() {
        return TenMH;
    }
    public void setTenMH(String tenMH) {
        TenMH = tenMH;
    }
    public int getSoLuongMoiThung() {
        return SoLuongMoiThung;
    }
    public void setSoLuongMoiThung(int soLuongMoiThung) {
        SoLuongMoiThung = soLuongMoiThung;
    }
}
