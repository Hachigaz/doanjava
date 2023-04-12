package Model;

public class KhoMD {
    public final String tableName = "kho";

    private String MaKho;
    private String TenKho;
    private String DiaChi;
    public String getMaKho() {
        return MaKho;
    }
    public void setMaKho(String maKho) {
        MaKho = maKho;
    }
    public String getTenKho() {
        return TenKho;
    }
    public void setTenKho(String tenKho) {
        TenKho = tenKho;
    }
    public String getDiaChi() {
        return DiaChi;
    }
    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }
    @Override
    public String toSQLString() {
        return "(" + MaKho + "," + TenKho + "," + DiaChi + ")";
    }
}
