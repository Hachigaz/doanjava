package Model;

public class DonNhapMD {
    public final String tableName = "donnhap";

    private String MaDonNhap;
    private String MaKho;
    private String MaCty;
    private String MaNV;
    private String NgayNhap;
    public String getMaDonNhap() {
        return MaDonNhap;
    }
    public void setMaDonNhap(String maDonNhap) {
        MaDonNhap = maDonNhap;
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
    public String getNgayNhap() {
        return NgayNhap;
    }
    public void setNgayNhap(String ngayNhap) {
        NgayNhap = ngayNhap;
    }
    @Override
    public String toSQLString() {
        return "(" + MaDonNhap + "," + MaKho + "," + MaCty + "," + MaNV
                + "," + NgayNhap + ")";
    }
}
