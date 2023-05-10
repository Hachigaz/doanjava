package Panel.TraCuuHang;

import java.util.ArrayList;

import DAL.DataAccessLayer;
import DTO.CongtyMD;
import DTO.KhoMD;
import DTO.KhuvucMD;
import DTO.Loai_hangMD;
import DTO.Custom.DSTraCuuHangMD;
import SQL.SQLUser;
import Panel.UI;
public class TraCuuHangBLL {
    private DataAccessLayer<DSTraCuuHangMD> TraCuuHangDAL;
    private DataAccessLayer<KhoMD> khoDAL;
    DataAccessLayer<KhuvucMD> kvDAL;
    DataAccessLayer<Loai_hangMD> loaiHangDAL;
    DataAccessLayer<CongtyMD> congTyDAL;

    public TraCuuHangBLL(){
        SQLUser master = UI.master;
        TraCuuHangDAL = new DataAccessLayer<>(master, DSTraCuuHangMD.class);
        khoDAL= new DataAccessLayer<>(master, KhoMD.class);
        kvDAL = new DataAccessLayer<KhuvucMD>(master, KhuvucMD.class);
        loaiHangDAL = new DataAccessLayer<Loai_hangMD>(master, Loai_hangMD.class);
        congTyDAL = new DataAccessLayer<CongtyMD>(master, CongtyMD.class);
    }
    public ArrayList<DSTraCuuHangMD> getDanhSachTCH(String... statements){
        return TraCuuHangDAL.getTable(statements);
    }
    public ArrayList<KhoMD> getDanhSachKho(String... statements){
        return khoDAL.getTable(statements);
    }
    public ArrayList<KhuvucMD> getDanhSachKV(String... statements){
        return kvDAL.getTable(statements);
    }
    public ArrayList<Loai_hangMD> getDanhSachLoaiHang(String... statements){
        return loaiHangDAL.getTable(statements);
    }
    public ArrayList<CongtyMD> getDanhSachCongTy(String... statements){
        return congTyDAL.getTable(statements);
    }
}