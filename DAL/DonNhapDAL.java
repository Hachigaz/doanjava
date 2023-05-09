package DAL;

import java.awt.List;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.PreparableStatement;

import DTO.DonNhapMD;
import SQL.SQLUser;
import misc.DataSet;

public class DonNhapDAL {
    private SQLUser master;
    private DataSet donnhap= master.getDataQuery("select * from donnhap");

    // public static List getALLDonnhap() {
    //     return ;
    // }

    public static void addDonnhap(DonNhapMD donnhap) {
        try{
            PreparableStatement pstmt = donnhap.PreparableStatement("INSERT INTO donnhap (MaDonNhap,MaKho,MaCty,MaNV,NgayNhap) VALUES (?,?,?,?,?)");
            pstmt.setString(1, donnhap.getMaDonNhap());
            pstmt.setString(2, donnhap.getMaKho());
            pstmt.setString(4, donnhap.getMaNV());
            pstmt.setString(3, donnhap.getMaCty());
            pstmt.setString(5, donnhap.getNgayNhap());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

}
