package MODEL;

public class ChitietnhomquyenMD {
    public final String tableName = "chitiet_nhomquyen";

    private String MaNhomQuyen;
    private String MaQuyen;
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
    public String toString() {
        return "(" + MaNhomQuyen + "," + MaQuyen + ")";
    }
}
