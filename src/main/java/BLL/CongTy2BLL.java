package BLL;

import java.sql.SQLData;
import java.text.DecimalFormat;
import java.util.ArrayList;

import DAL.DataAccessLayer;
import DTO.ChucvuMD;
import DTO.CongtyMD;
import DTO.DonNhapMD;
import DTO.KhoMD;
import DTO.NhanvienMD;
import DTO.Custom.DSNhanVienMD;
import SQL.SQLUser;
import Panel.UI;

public class CongTy2BLL {
    
    private DataAccessLayer<KhoMD> KhoDAL;
    private DataAccessLayer<CongtyMD> CongTy2DAL;
    private DataAccessLayer<DonNhapMD> DNDAL;
    public CongTy2BLL(){
        SQLUser master = UI.master;
        KhoDAL = new DataAccessLayer<>(master, KhoMD.class);
        CongTy2DAL = new DataAccessLayer<>(master, CongtyMD.class);
        DNDAL = new DataAccessLayer<>(master, DonNhapMD.class);
    }
    // public ArrayList<DSNhanVienMD> getDanhSachNhanVien(String... statements){
    //     return DSNhanVienDAL.getTable(statements);
    // }
    public void themCTmoi(CongtyMD CTmoi){
        CongTy2DAL.addOne(CTmoi);
    }
    public void suaCT(String key,String... statements){
        CongTy2DAL.update(key, statements);
    }
    public void xoaCT(String... keys){
        CongTy2DAL.remove(keys);
    }
    public ArrayList<CongtyMD> getDSCT(String... statements){
        return CongTy2DAL.getTable(statements);
    }
    public ArrayList<DonNhapMD> getDSDN(String... statements){
        return DNDAL.getTable(statements);
    }
   
    
}
