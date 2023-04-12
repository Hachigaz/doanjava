package Model;

public class ChitietnhomquyenMD implements Model{
    public static final String tableName = "chitiet_nhomquyen";

    private String MaNhomQuyen;
    private String MaQuyen;

    public ChitietnhomquyenMD(String maNhomQuyen, String maQuyen) {
        MaNhomQuyen = maNhomQuyen;
        MaQuyen = maQuyen;
    }
    public String getMaNhomQuyen() {
        return MaNhomQuyen;
    }
    public void setMaNhomQuyen(String maNhomQuyen) {
        MaNhomQuyen = maNhomQuyen;
    }
    public String getMaQuyen() {
        return MaQuyen;
    }
    public void setMaQuyen(String maQuyen) {
        MaQuyen = maQuyen;
    }
    @Override
    public String toSQLString() {
        return "(" + MaNhomQuyen + "," + MaQuyen + ")";
    }
}
