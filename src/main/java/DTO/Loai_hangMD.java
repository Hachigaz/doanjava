package DTO;

public class Loai_hangMD extends Model{
    public static final String selectStatement = "*";
    public static final String fromStatement = "loai_hang";
    public static final String groupByStatement = "";

    private String MaLoai;
    private String TenLoai;
    
    public Loai_hangMD(String maLoai, String tenloai) {
        MaLoai = maLoai;
        TenLoai = tenloai;
    }
    public String getMaLoai() {
        return MaLoai;
    }
    public void setMaLoai(String maLoai) {
        MaLoai = maLoai;
    }
    public String getTenloai() {
        return TenLoai;
    }
    public void setTenloai(String tenloai) {
        TenLoai = tenloai;
    }
    public String getSelectStatement(){
        return Loai_hangMD.selectStatement;
    }
    public String getFromStatement(){
        return Loai_hangMD.fromStatement;
    }
    @Override
    public String toSQLString() {
        return "('" + MaLoai + "','" + TenLoai + "')";
    }
}
