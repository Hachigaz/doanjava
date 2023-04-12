package Model;

public class DonKiemMD {
    public final String tableName = "donkiem";

    private String MaDonKiem;
    private String MaKho;
    private String MaNVNhap;
    private String NgayKiem;
    public String getMaDonKiem() {
        return MaDonKiem;
    }
    public void setMaDonKiem(String maDonKiem) {
        MaDonKiem = maDonKiem;
    }
    public String getMaKho() {
        return MaKho;
    }
    public void setMaKho(String maKho) {
        MaKho = maKho;
    }
    public String getMaNVNhap() {
        return MaNVNhap;
    }
    public void setMaNVNhap(String maNVNhap) {
        MaNVNhap = maNVNhap;
    }
    public String getNgayKiem() {
        return NgayKiem;
    }
    public void setNgayKiem(String ngayKiem) {
        NgayKiem = ngayKiem;
    }
    @Override
    public String toSQLString() {
        return "(" + MaDonKiem + "," + MaKho + "," + MaNVNhap + ","
                + NgayKiem + ")";
    }
}
