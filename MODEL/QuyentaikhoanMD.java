package MODEL;

public class QuyentaikhoanMD {
    public final String tableName = "quyentaikhoan";

    private String MaQuyen;
    private String TenQuyen;
    public String getMaQuyen() {
        return MaQuyen;
    }
    public void setMaQuyen(String maQuyen) {
        MaQuyen = maQuyen;
    }
    public String getTenQuyen() {
        return TenQuyen;
    }
    public void setTenQuyen(String tenQuyen) {
        TenQuyen = tenQuyen;
    }
    @Override
    public String toString() {
        return "()" + MaQuyen + "," + TenQuyen + ")";
    }
}
