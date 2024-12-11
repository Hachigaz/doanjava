package DTO;

public class DonXuatMD extends Model{
    public static final String selectStatement = "*";
    public static final String fromStatement = "donxuat";
    public static final String groupByStatement = "";

    private String MaDonXuat;
    private String MaKho;
    private String MaCty;
    private String MaNV;
    private String NgayXuat;


    
    public DonXuatMD(String maDonXuat, String maKho, String maCty, String maNV, String ngayXuat) {
        MaDonXuat = maDonXuat;
        MaKho = maKho;
        MaCty = maCty;
        MaNV = maNV;
        NgayXuat = ngayXuat;
    }
    
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
    public String getSelectStatement(){
        return DonXuatMD.selectStatement;
    }
    public String getFromStatement(){
        return DonXuatMD.fromStatement;
    }
    @Override
    public String toSQLString() {
        return "('" + MaDonXuat + "','" + MaKho + "','" + MaCty + "','" + MaNV
                + "','" + NgayXuat + "')";
    }
}
