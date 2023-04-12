package MODEL;

public class Khuvuc_loaihangMD {
    public final String tableName = "khuvuc";

    private String MaKV;
    private String MaLoai;
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
    @Override
    public String toString() {
        return "(" + MaKV + "," + MaLoai + ")";
    }
}
