package Model;

public class ChucvuMD extends Model{
    public static final String fromStatement = "chucvu";

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
    public String getFromStatement(){
        return ChucvuMD.fromStatement;
    }
    @Override
    public String toSQLString() {
        return "('" + MaCV + "','" + TenCV + "')";
    }
}
