package Model;

public class Loai_hangMD implements Model{
    public static final String tableName = "loai_hang";

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
    @Override
    public String toSQLString() {
        return "(" + MaLoai + "," + Tenloai + ")";
    }
}
