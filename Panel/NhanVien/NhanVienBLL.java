package Panel.NhanVien;

import java.sql.SQLData;
import java.text.DecimalFormat;
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
    public void themDSNVmoi(DSNhanVienMD dsNhanVienMD){
        DSNhanVienDAL.addOne(dsNhanVienMD);
    }
    public void xoaNV(String... keys){
        NhanVienDAL.remove(keys);
    }
    public ArrayList<NhanvienMD> getDSNhanVien(String... statements){
        return NhanVienDAL.getTable(statements);
    }
    public ArrayList<DSNhanVienMD> getDsNhanVienMD(String... statements){
        return DSNhanVienDAL.getTable(statements);
    }
    public int layMa(){
        int count = 0;
        for(int i=1;i<NhanVienDAL.getTable().size();i++){
            NhanvienMD NVtemp = NhanVienDAL.getTable().get(i);
            String maStr = NVtemp.getMaNV().substring(NVtemp.getMaNV().length()-3);
            int maNum = Integer.parseInt(maStr);
            if(maNum > count){
                count = maNum;
            }
        }
        return count+1;
    }
}
