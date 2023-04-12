package MODEL;

public class ChitietdonxuatMD {
    public final String tableName = "chitiet_donxuat";

    private String MaDonXuat;
    private String MaDonNhap;
    private String MaMH;
    private String MaKV;
    private float SoLuong;
    public String getMaDonXuat() {
        return MaDonXuat;
    }
    public void setMaDonXuat(String maDonXuat) {
        MaDonXuat = maDonXuat;
    }
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
    public float getSoLuong() {
        return SoLuong;
    }
    public void setSoLuong(float soLuong) {
        SoLuong = soLuong;
    }
    @Override
    public String toString() {
        return "(" + MaDonXuat + "," + MaDonNhap + "," + MaMH + ","+ MaKV + "," + SoLuong + ")";
    }
}
