package Model;

public class Mat_hangMD extends Model{
    public static final String selectStatement = "*";
    public static final String fromStatement = "mat_hang";
    public static final String groupByStatement = "";

    private String MaMH;
    private String MaCty;
    private String MaLoai;
    private String TenMH;
    private Integer SoLuongMoiThung;

    

    public Mat_hangMD(String maMH, String maCty, String maLoai, String tenMH, Integer soLuongMoiThung) {
        MaMH = maMH;
        MaCty = maCty;
        MaLoai = maLoai;
        TenMH = tenMH;
        SoLuongMoiThung = soLuongMoiThung;
    }
    
    public String getMaMH() {
        return MaMH;
    }
    public void setMaMH(String maMH) {
        MaMH = maMH;
    }
    public String getMaCty() {
        return MaCty;
    }
    public void setMaCty(String maCty) {
        MaCty = maCty;
    }
    public String getMaLoai() {
        return MaLoai;
    }
    public void setMaLoai(String maLoai) {
        MaLoai = maLoai;
    }
    public String getTenMH() {
        return TenMH;
    }
    public void setTenMH(String tenMH) {
        TenMH = tenMH;
    }
    public Integer getSoLuongMoiThung() {
        return SoLuongMoiThung;
    }
    public void setSoLuongMoiThung(Integer soLuongMoiThung) {
        SoLuongMoiThung = soLuongMoiThung;
    }
    public String getSelectStatement(){
        return Mat_hangMD.selectStatement;
    }
    public String getFromStatement(){
        return Mat_hangMD.fromStatement;
    }
    @Override
    public String toSQLString() {
        return "('" + MaMH + "','" + MaCty + "','" + MaLoai + "','" + TenMH + "'," + SoLuongMoiThung + ")";
    }
}
