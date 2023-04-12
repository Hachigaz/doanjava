package Model;

public class Loai_hangMD {
    public final String tableName = "loai_hang";

    private String MaLoai;
    private String Tenloai;
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
