package Model;

public class ChucvuMD implements Model{
    public static final String tableName = "chucvu";

    private String MaCV;
    private String TenCV;

    public ChucvuMD(String maCV, String tenCV) {
        MaCV = maCV;
        TenCV = tenCV;
    }
    public String getMaCV() {
        return MaCV;
    }
    public void setMaCV(String maCV) {
        MaCV = maCV;
    }
    public String getTenCV() {
        return TenCV;
    }
    public void setTenCV(String tenCV) {
        TenCV = tenCV;
    }
    @Override
    public String toSQLString() {
        return "(" + MaCV + "," + TenCV + ")";
    }
}
