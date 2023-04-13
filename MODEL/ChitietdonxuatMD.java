package Model;

public class ChitietdonxuatMD extends Model{
    public static final String selectStatement = "*";
    public static final String fromStatement = "chitiet_donxuat";

    private String MaDonXuat;
    private String MaDonNhap;
    private String MaMH;
    private String MaKV;
    private Float SoLuong;

    public ChitietdonxuatMD(String maDonXuat, String maDonNhap, String maMH, String maKV, Float soLuong) {
        MaDonXuat = maDonXuat;
        MaDonNhap = maDonNhap;
        MaMH = maMH;
        MaKV = maKV;
        SoLuong = soLuong;
    }
    
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

    public Float getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Float soLuong) {
        SoLuong = soLuong;
    }

    public String getSelectStatement(){
        return ChitietdonxuatMD.selectStatement;
    }

    public String getFromStatement(){
        return ChitietdonxuatMD.fromStatement;
    }
    
    @Override
    public String toSQLString() {
        return "('" + MaDonXuat + "','" + MaDonNhap + "','" + MaMH + "','"+ MaKV + "','" + SoLuong + ")";
    }
}
