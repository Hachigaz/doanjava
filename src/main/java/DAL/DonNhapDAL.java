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

    }

    

}
