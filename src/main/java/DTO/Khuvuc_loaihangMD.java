package DTO;

public class Khuvuc_loaihangMD extends Model{
    public static final String selectStatement = "*";
    public static final String fromStatement = "khuvuc_loaihang";
    public static final String groupByStatement = "";

    private String MaKV;
    private String MaLoai;
    
    public Khuvuc_loaihangMD(String maKV, String maLoai) {
        MaKV = maKV;
        MaLoai = maLoai;
    }
    public String getMaKV() {
        return MaKV;
    }
    public void setMaKV(String maKV) {
        MaKV = maKV;
    }
    public String getMaLoai() {
        return MaLoai;
    }
    public void setMaLoai(String maLoai) {
        MaLoai = maLoai;
    }
    public String getSelectStatement(){
        return Khuvuc_loaihangMD.selectStatement;
    }
    public String getFromStatement(){
        return Khuvuc_loaihangMD.fromStatement;
    }
    @Override
    public String toSQLString() {
        return "('" + MaKV + "','" + MaLoai + "')";
    }
}
