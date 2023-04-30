package Panel.NhanVien;

import java.sql.SQLData;
import java.util.ArrayList;

import DAL.DataAccessLayer;
import DTO.NhanvienMD;
import DTO.Custom.DSNhanVienMD;
import SQL.SQLUser;
import Panel.UI;

public class NhanVienBLL {
    private DataAccessLayer<DSNhanVienMD> DSNhanVienDAL;
    private DataAccessLayer<NhanvienMD> NhanVienDAL;
    public NhanVienBLL(){
        SQLUser master = UI.master;
        DSNhanVienDAL = new DataAccessLayer<>(master,DSNhanVienMD.class);
        NhanVienDAL = new DataAccessLayer<>(master, NhanvienMD.class);
    }
    public ArrayList<DSNhanVienMD> getDanhSachNhanVien(String... statements){
        return DSNhanVienDAL.getTable(statements);
    }
    public void themNVmoi(NhanvienMD nhanvienMoi){
        NhanVienDAL.addOne(nhanvienMoi);
    }
    public ArrayList<NhanvienMD> getDSNhanVien(String... statements){
        return NhanVienDAL.getTable(statements);
    }
}
