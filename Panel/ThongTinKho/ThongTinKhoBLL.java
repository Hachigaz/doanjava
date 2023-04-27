package Panel.ThongTinKho;

import java.text.DecimalFormat;
import java.util.ArrayList;

import Panel.UI;

import DAL.DataAccessLayer;
import DTO.*;
import DTO.Custom.DSChiTietKhuVucLoaiMD;
import SQL.SQLUser;

public class ThongTinKhoBLL {
    private DataAccessLayer<KhoMD> khoDAL;
    private DataAccessLayer<NhanvienMD> nvDAL;
    private DataAccessLayer<KhuvucMD> kvDAL;
    private DataAccessLayer<DSChiTietKhuVucLoaiMD>  chiTietKVLoaiDAL;
    private DataAccessLayer<Loai_hangMD> loaiHangDAL;
    private DataAccessLayer<Khuvuc_loaihangMD> khuVucLoaiDAL;
    
    public ThongTinKhoBLL(){
        SQLUser master = UI.master;

        khoDAL = new DataAccessLayer<>(master, KhoMD.class);
        nvDAL = new DataAccessLayer<>(master, NhanvienMD.class);
        kvDAL = new DataAccessLayer<>(master, KhuvucMD.class);
        chiTietKVLoaiDAL = new DataAccessLayer<>(master, DSChiTietKhuVucLoaiMD.class);
        loaiHangDAL = new DataAccessLayer<>(master, Loai_hangMD.class);
        khuVucLoaiDAL = new DataAccessLayer<>(master, Khuvuc_loaihangMD.class);
    }
    public String taoMaKVMoi(String maKho){
        DecimalFormat df = new DecimalFormat("000");
        return  maKho + "_" +df.format(kvDAL.getTable("MaKho ="+maKho).size()+1);
    }
    public void themKVMoi(KhuvucMD khuvucMoi){
        kvDAL.addOne(khuvucMoi);
    }
    public ArrayList<KhuvucMD> getDanhSachKV(String... statements){
        String newStatements[] = new String[statements.length+1];
        for(int i =0 ; i < statements.length;i++){
            newStatements[i]=statements[i];
        }
        newStatements[statements.length]="MaKho = "+UI.khoNVDangNhap.getMaKho();
        return kvDAL.getTable(newStatements);
    }
    public ArrayList<DSChiTietKhuVucLoaiMD> getDanhSachCTKV(String... statements){
        return chiTietKVLoaiDAL.getTable(statements);
    }
    public ArrayList<Loai_hangMD> getDanhSachLoaiHang(String... statements){
        return loaiHangDAL.getTable(statements);
    }
    public ArrayList<Khuvuc_loaihangMD> getDanhSachKhuVucLoai(String... statements){
        return khuVucLoaiDAL.getTable(statements);
    }
    public void addNewKhuVucLoai(Khuvuc_loaihangMD kv_loai){
        khuVucLoaiDAL.addOne(kv_loai);
    }
    public void removeKhuVucLoai(String... keys){
        khuVucLoaiDAL.remove(keys);
    }
    public ArrayList<KhoMD> getDanhSachKho(String... statements){
        return khoDAL.getTable(statements);
    }
    public KhoMD getThongTinKhoNV(String maNV){
        return khoDAL.getFirst("MaKho="+nvDAL.getFirst("MaNV="+maNV).getKho_lam_viec());
    }
    public Float getTongSucChuaKho(String maKho){
        Float tongSC = 0.0f;
        for(KhuvucMD kv : kvDAL.getTable("MaKho="+maKho)){
            tongSC+=kv.getSucChua();
        }
        return tongSC;
    }
    public void removeKhuVucTrongKho(String maKV){
        kvDAL.remove("MaKV="+maKV);
    }
}
