package BLL;

import java.util.ArrayList;

import DAL.DataAccessLayer;
import DTO.*;
import Panel.UI;

public abstract class FormDonBLL {
    public abstract String taoMaMoi();
    private static DataAccessLayer<CongtyMD> congTyDAL = new DataAccessLayer<CongtyMD>(UI.master, CongtyMD.class);
    private static DataAccessLayer<Mat_hangMD> matHangDAL = new DataAccessLayer<Mat_hangMD>(UI.master, Mat_hangMD.class);
    private static DataAccessLayer<KhuvucMD> kvDAL = new DataAccessLayer<>(UI.master, KhuvucMD.class);
    private static DataAccessLayer<Loai_hangMD> loaiHangDAL = new DataAccessLayer<>(UI.master, Loai_hangMD.class);
    private static DataAccessLayer<Khuvuc_loaihangMD> khuVucLoaiDAL = new DataAccessLayer<>(UI.master, Khuvuc_loaihangMD.class);


    public static ArrayList<CongtyMD> getDanhSachCongTy(String... statements){
        return congTyDAL.getTable(statements);
    }
    public static ArrayList<Mat_hangMD> getDanhSachMatHang(String... statements){
        return matHangDAL.getTable(statements);
    }
    public static ArrayList<Loai_hangMD> getDanhSachLoaiHang(String... statements){
        return loaiHangDAL.getTable(statements);
    }
    public static ArrayList<Khuvuc_loaihangMD> getDanhSachKhuVucLoai(String... statements){
        return khuVucLoaiDAL.getTable(statements);
    }
    public static ArrayList<KhuvucMD> getDanhSachKV(String... statements){
        return kvDAL.getTable(statements);
    }
    public static ArrayList<Mat_hangMD> getDanhSachMH_KhuVuc(String maKV,String... statements){
        ArrayList<Khuvuc_loaihangMD> dsKVL = khuVucLoaiDAL.getTable("MaKV="+maKV);
        if(dsKVL==null){
            return null;
        }
        ArrayList<Mat_hangMD> dsMHKV = new ArrayList<Mat_hangMD>();
        ArrayList<Mat_hangMD> dsMHDuyet = matHangDAL.getTable(statements);
        if(dsMHDuyet==null){
            return null;
        }
        for(Mat_hangMD mh : dsMHDuyet){
            for(Khuvuc_loaihangMD kvl : dsKVL){
                if(kvl.getMaLoai().equals(mh.getMaLoai())){
                    dsMHKV.add(mh);
                }
            }
        }
        return dsMHKV;
    }
}
