package Panel.DonNhap;

import java.util.ArrayList;

import DAL.DataAccessLayer;
import DTO.CongtyMD;
import DTO.DonNhapMD;
import DTO.KhoMD;
import DTO.KhuvucMD;
import DTO.Loai_hangMD;
import DTO.Custom.DSDonNhapMD;
import DTO.Custom.DSTraCuuHangMD;
import SQL.SQLUser;
import Panel.UI;
public class DonNhap2BLL {
    private DataAccessLayer<DSTraCuuHangMD> TraCuuHangDAL;
    DataAccessLayer<KhoMD> khoDAL;
    DataAccessLayer<KhuvucMD> kvDAL;
    DataAccessLayer<Loai_hangMD> loaiHangDAL;
    DataAccessLayer<CongtyMD> congTyDAL;
    DataAccessLayer<DonNhapMD> DonNhapDAL;
    DataAccessLayer<DSDonNhapMD> DSDonNhapDAL;
    

    public DonNhap2BLL(){
        SQLUser master = UI.master;
        TraCuuHangDAL = new DataAccessLayer<>(master, DSTraCuuHangMD.class);
        khoDAL= new DataAccessLayer<>(master, KhoMD.class);
        kvDAL = new DataAccessLayer<KhuvucMD>(master, KhuvucMD.class);
        loaiHangDAL = new DataAccessLayer<Loai_hangMD>(master, Loai_hangMD.class);
        congTyDAL = new DataAccessLayer<CongtyMD>(master, CongtyMD.class);
       DonNhapDAL = new DataAccessLayer<DonNhapMD>(master, DonNhapMD.class);
       DSDonNhapDAL = new DataAccessLayer<DSDonNhapMD>(master, DSDonNhapMD.class);
    }
    public ArrayList<DSTraCuuHangMD> getDanhSachTCH(String... statements){
        return TraCuuHangDAL.getTable(statements);
    }
    public ArrayList<DSDonNhapMD> getDanhSachDN(String... statements){
        return DSDonNhapDAL.getTable(statements);
    }
    public ArrayList<DonNhapMD> getDanhSachDonNhap(String... statements){
        return DonNhapDAL.getTable(statements);
    }
    public ArrayList<KhoMD> getDanhSachKho(String... statements){
        return khoDAL.getTable(statements);
    }
    public ArrayList<Loai_hangMD> getDanhSachLoaiHang(String... statements){
        return loaiHangDAL.getTable(statements);
    }
    public ArrayList<CongtyMD> getDanhSachCongTy(String... statements){
        return congTyDAL.getTable(statements);
    }
}