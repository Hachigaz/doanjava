package DTO;

public class NhanvienMD extends Model{
    public static final String selectStatement = "*";
    public static final String fromStatement = "nhanvien";
    public static final String groupByStatement = "";

    private String MaNV;
    private String TenNV;
    private String MaCV;
    private String GioiTinh;
    private String NgaySinh;
    private String DiaChi;
    private String Kho_lam_viec;
    private Integer SoGioLamViec;
    private Float LuongCoBan;

    public NhanvienMD(String maNV, String tenNV, String maCV, String gioiTinh, String ngaySinh, String diaChi, String kho_lam_viec,Integer soGioLamViec,Float luongCoBan) {
        MaNV = maNV;
        TenNV = tenNV;
        MaCV = maCV;
        GioiTinh = gioiTinh;
        NgaySinh = ngaySinh;
        DiaChi = diaChi;
        Kho_lam_viec = kho_lam_viec;
        SoGioLamViec = soGioLamViec;
        LuongCoBan = luongCoBan;
    }
    
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
    public Integer getSoGioLamViec() {
        return SoGioLamViec;
    }
    public void setSoGioLamViec(Integer soGioLamViec) {
        SoGioLamViec = soGioLamViec;
    }
    public Float getLuongCoBan() {
        return LuongCoBan;
    }
    public void setLuongCoBan(Float luongCoBan) {
        LuongCoBan = luongCoBan;
    }
    public String getSelectStatement(){
        return NhanvienMD.selectStatement;
    }
    public String getFromStatement(){
        return NhanvienMD.fromStatement;
    }
    @Override
    public String toSQLString() {
        return "('" + MaNV + "','" + TenNV + "','" + MaCV + "','" + GioiTinh
                + "','" + NgaySinh + "','" + DiaChi + "','" + Kho_lam_viec +"',"+ SoGioLamViec +","+ LuongCoBan + ")";
    }
}
