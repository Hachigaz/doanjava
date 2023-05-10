package BLL;

import java.util.ArrayList;

import DAL.DataAccessLayer;
import DTO.CongtyMD;
import DTO.DonNhapMD;
import DTO.DonXuatMD;
import DTO.KhoMD;
import DTO.KhuvucMD;
import DTO.Loai_hangMD;
import DTO.Custom.DSDonXuatMD;
import DTO.Custom.DSTraCuuHangMD;
import SQL.SQLUser;
import Panel.UI;
public class DonXuatBLL {
    private DataAccessLayer<DSTraCuuHangMD> TraCuuHangDAL;
    static DataAccessLayer<KhoMD> khoDAL;
    DataAccessLayer<KhuvucMD> kvDAL;
    DataAccessLayer<Loai_hangMD> loaiHangDAL;
    DataAccessLayer<CongtyMD> congTyDAL;
    DataAccessLayer<DonNhapMD> DonNhapDAL;
    DataAccessLayer<DonXuatMD> DonXuatDAL;
    DataAccessLayer<DSDonXuatMD> DSDonXuatDAL;
    

    public DonXuatBLL(){
        SQLUser master = UI.master;
        TraCuuHangDAL = new DataAccessLayer<>(master, DSTraCuuHangMD.class);
        khoDAL= new DataAccessLayer<>(master, KhoMD.class);
        kvDAL = new DataAccessLayer<KhuvucMD>(master, KhuvucMD.class);
        loaiHangDAL = new DataAccessLayer<Loai_hangMD>(master, Loai_hangMD.class);
        congTyDAL = new DataAccessLayer<CongtyMD>(master, CongtyMD.class);
       DonNhapDAL = new DataAccessLayer<DonNhapMD>(master, DonNhapMD.class);
       DonXuatDAL = new DataAccessLayer<DonXuatMD>(master, DonXuatMD.class);
       DSDonXuatDAL = new DataAccessLayer<DSDonXuatMD>(master, DSDonXuatMD.class);
    }
    public ArrayList<DSTraCuuHangMD> getDanhSachTCH(String... statements){
        return TraCuuHangDAL.getTable(statements);
    }
    public ArrayList<DSDonXuatMD> getDanhSachDX(String... statements){
        return DSDonXuatDAL.getTable(statements);
    }
    public ArrayList<DonXuatMD> getDanhSachDonXuat(String... statements){
        return DonXuatDAL.getTable(statements);
    }
    public ArrayList<KhoMD> getDanhSachKho(String... statements){
        return khoDAL.getTable(statements);
    }
    public ArrayList<Loai_hangMD> getDanhSachLoaiHang(String... statements){
        return loaiHangDAL.getTable(statements);
    }
    public  ArrayList<CongtyMD> getDanhSachCongTy(String... statements){
        return congTyDAL.getTable(statements);
    }
}