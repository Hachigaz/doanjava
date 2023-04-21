package Model;

public class Loai_hangMD extends Model{
    public static final String selectStatement = "*";
    public static final String fromStatement = "loai_hang";
    public static final String groupByStatement = "";

    private String MaLoai;
    private String Tenloai;
    
    public Loai_hangMD(String maLoai, String tenloai) {
        MaLoai = maLoai;
        Tenloai = tenloai;
    }
    public String getMaLoai() {
        return MaLoai;
    }
    public void setMaLoai(String maLoai) {
        MaLoai = maLoai;
    }
    public String getTenloai() {
        return Tenloai;
    }
    public void setTenloai(String tenloai) {
        Tenloai = tenloai;
    }
    public String getSelectStatement(){
        return Loai_hangMD.selectStatement;
    }
    public String getFromStatement(){
        return Loai_hangMD.fromStatement;
    }
    @Override
    public String toSQLString() {
        return "('" + MaLoai + "','" + Tenloai + "')";
    }
}
