package Panel.NhanVien;

import java.sql.SQLData;
import java.text.DecimalFormat;
import java.util.ArrayList;

import DAL.DataAccessLayer;
import DTO.ChucvuMD;
import DTO.KhoMD;
import DTO.NhanvienMD;
import DTO.Custom.DSNhanVienMD;
import SQL.SQLUser;
import Panel.UI;

public class NhanVienBLL {
    private DataAccessLayer<DSNhanVienMD> DSNhanVienDAL;
    private DataAccessLayer<NhanvienMD> NhanVienDAL;
    private DataAccessLayer<KhoMD> KhoDAL;
    public NhanVienBLL(){
        SQLUser master = UI.master;
        DSNhanVienDAL = new DataAccessLayer<>(master,DSNhanVienMD.class);
        NhanVienDAL = new DataAccessLayer<>(master, NhanvienMD.class);
        KhoDAL = new DataAccessLayer<>(master, KhoMD.class);
    }
    public ArrayList<DSNhanVienMD> getDanhSachNhanVien(String... statements){
        return DSNhanVienDAL.getTable(statements);
    }
    public void themNVmoi(NhanvienMD nhanvienMoi){
        NhanVienDAL.addOne(nhanvienMoi);
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
    public String[] layTenKho(){
        String[] DsTenKho = new String[KhoDAL.getTable().size()];
        for(int i=0;i<KhoDAL.getTable().size();i++){
            KhoMD DsKho = KhoDAL.getTable().get(i);
            String tenKho = DsKho.getTenKho();
            DsTenKho[i] = tenKho;
        }
        return DsTenKho;
    }
    public String[] layMaKho(){
        String[] DsMaKho = new String[KhoDAL.getTable().size()];
        for(int i=0;i<KhoDAL.getTable().size();i++){
            KhoMD dsKho = KhoDAL.getTable().get(i);
            String maKho = dsKho.getMaKho();
            DsMaKho[i] = maKho;
        }
        return DsMaKho;
    }
    public int laySoLuongKho(){
        return KhoDAL.getTable().size();
    }
}
