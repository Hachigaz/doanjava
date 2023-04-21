package Model;

public class ChitietnhomquyenMD extends Model{
    public static final String selectStatement = "*";
    public static final String fromStatement = "chitiet_nhomquyen";
    public static final String groupByStatement = "";

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
    
    public String getSelectStatement(){
        return ChitietnhomquyenMD.selectStatement;
    }
    public String getFromStatement(){
        return ChitietnhomquyenMD.fromStatement;
    }
    @Override
    public String toSQLString() {
        return "('" + MaNhomQuyen + "','" + MaQuyen + "')";
    }
}
