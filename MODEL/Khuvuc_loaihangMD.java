package Model;

public class Khuvuc_loaihangMD extends Model{
    public static final String fromStatement = "khuvuc_loaihang";

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
    public String getFromStatement(){
        return Khuvuc_loaihangMD.fromStatement;
    }
    @Override
    public String toSQLString() {
        return "('" + MaKV + "','" + MaLoai + "')";
    }
}
