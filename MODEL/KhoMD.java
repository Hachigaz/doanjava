package Model;

public class KhoMD implements Model{
    public static final String tableName = "kho";

    private String MaKho;
    private String TenKho;
    private String DiaChi;
    
    public KhoMD(String maKho, String tenKho, String diaChi) {
        MaKho = maKho;
        TenKho = tenKho;
        DiaChi = diaChi;
    }
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
