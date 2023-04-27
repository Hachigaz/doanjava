package DTO;

public class ChitietdonnhapMD extends Model{
    public static final String selectStatement = "*";
    public static final String fromStatement = "chitiet_donnhap";
    public static final String groupByStatement = "";
    
    private String MaDonNhap;
    private String MaMH;
    private String MaKV;
    private Float SLNhap;
    private Float SLConLai;

    public ChitietdonnhapMD(String maDonNhap, String maMH, String maKV, Float sLNhap, Float sLConLai) {
        MaDonNhap = maDonNhap;
        MaMH = maMH;
        MaKV = maKV;
        SLNhap = sLNhap;
        SLConLai = sLConLai;
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
    public Float getSLNhap() {
        return SLNhap;
    }
    public void setSLNhap(Float sLNhap) {
        SLNhap = sLNhap;
    }
    public Float getSLConLai() {
        return SLConLai;
    }
    public void setSLConLai(Float sLConLai) {
        SLConLai = sLConLai;
    }
    public String getSelectStatement(){
        return ChitietdonnhapMD.selectStatement;
    }
    public String getFromStatement(){
        return ChitietdonnhapMD.fromStatement;
    }
    @Override
    public String toSQLString() {
        return "('" + MaDonNhap + "','" + MaMH + "','" + MaKV + "'," + SLNhap  + ",'" + SLConLai + ")";
    }
}
