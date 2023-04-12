package MODEL;

public class NhanvienMD {
    public final String tableName = "nhanvien";

    private String MaNV;
    private String TenNV;
    private String MaCV;
    private String GioiTinh;
    private String NgaySinh;
    private String DiaChi;
    private String Kho_lam_viec;
    public String getMaNV() {
        return MaNV;
    }
    public void setMaNV(String maNV) {
        MaNV = maNV;
    }
    public String getTenNV() {
        return TenNV;
    }
    public void setTenNV(String tenNV) {
        TenNV = tenNV;
    }
    public String getMaCV() {
        return MaCV;
    }
    public void setMaCV(String maCV) {
        MaCV = maCV;
    }
    public String getGioiTinh() {
        return GioiTinh;
    }
    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }
    public String getNgaySinh() {
        return NgaySinh;
    }
    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }
    public String getDiaChi() {
        return DiaChi;
    }
    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }
    public String getKho_lam_viec() {
        return Kho_lam_viec;
    }
    public void setKho_lam_viec(String kho_lam_viec) {
        Kho_lam_viec = kho_lam_viec;
    }
    @Override
    public String toString() {
        return "(" + MaNV + "," + TenNV + "," + MaCV + "," + GioiTinh
                + "," + NgaySinh + "," + DiaChi + "," + Kho_lam_viec + ")";
    }
}
