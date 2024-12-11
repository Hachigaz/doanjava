package DTO;

public class DonKiemMD extends Model{
    public static final String selectStatement = "*";
    public static final String fromStatement = "donkiem";
    public static final String groupByStatement = "";
    
    private String MaDonKiem;
    private String MaKho;
    private String MaNVNhap;
    private String NgayKiem;
    
    public DonKiemMD(String maDonKiem, String maKho, String maNVNhap, String ngayKiem) {
        MaDonKiem = maDonKiem;
        MaKho = maKho;
        MaNVNhap = maNVNhap;
        NgayKiem = ngayKiem;
    }
    
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
    
    public String getSelectStatement(){
        return DonKiemMD.selectStatement;
    }
    public String getFromStatement(){
        return DonKiemMD.fromStatement;
    }
    @Override
    public String toSQLString() {
        return "('" + MaDonKiem + "','" + MaKho + "','" + MaNVNhap + "','"
                + NgayKiem + "')";
    }
}
