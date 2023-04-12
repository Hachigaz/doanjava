package MODEL;

public class DonXuatMD {
    public final String tableName = "donxuat";

    private String MaDonXuat;
    private String MaKho;
    private String MaCty;
    private String MaNV;
    private String NgayXuat;
    public String getMaDonXuat() {
        return MaDonXuat;
    }
    public void setMaDonXuat(String maDonXuat) {
        MaDonXuat = maDonXuat;
    }
    public String getMaKho() {
        return MaKho;
    }
    public void setMaKho(String maKho) {
        MaKho = maKho;
    }
    public String getMaCty() {
        return MaCty;
    }
    public void setMaCty(String maCty) {
        MaCty = maCty;
    }
    public String getMaNV() {
        return MaNV;
    }
    public void setMaNV(String maNV) {
        MaNV = maNV;
    }
    public String getNgayXuat() {
        return NgayXuat;
    }
    public void setNgayXuat(String ngayXuat) {
        NgayXuat = ngayXuat;
    }
    @Override
    public String toString() {
        return "(" + MaDonXuat + "," + MaKho + "," + MaCty + "," + MaNV
                + "," + NgayXuat + ")";
    }
}
