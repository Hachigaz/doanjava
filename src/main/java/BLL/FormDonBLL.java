package BLL;

import java.text.DecimalFormat;
import java.util.ArrayList;

import DAL.DataAccessLayer;
import DTO.*;
import Panel.UI;
import misc.util;

public class FormDonBLL {
    private DataAccessLayer<CongtyMD> congTyDAL = new DataAccessLayer<CongtyMD>(UI.master, CongtyMD.class);
    private DataAccessLayer<Mat_hangMD> matHangDAL = new DataAccessLayer<Mat_hangMD>(UI.master, Mat_hangMD.class);
    private DataAccessLayer<KhuvucMD> kvDAL = new DataAccessLayer<>(UI.master, KhuvucMD.class);
    private DataAccessLayer<Loai_hangMD> loaiHangDAL = new DataAccessLayer<>(UI.master, Loai_hangMD.class);
    private DataAccessLayer<Khuvuc_loaihangMD> khuVucLoaiDAL = new DataAccessLayer<>(UI.master, Khuvuc_loaihangMD.class);
    private DataAccessLayer<DonNhapMD> donNhapDAL = new DataAccessLayer<>(UI.master,DonNhapMD.class);
    private DataAccessLayer<ChitietdonnhapMD> ctdnDAL = new DataAccessLayer<>(UI.master,ChitietdonnhapMD.class);
    private DataAccessLayer<DonXuatMD> donXuatDAL = new DataAccessLayer<>(UI.master, DonXuatMD.class);
    private DataAccessLayer<ChitietdonxuatMD> ctdxDAL = new DataAccessLayer<>(UI.master, ChitietdonxuatMD.class);

    public ArrayList<CongtyMD> getDanhSachCongTy(String... statements){
        return congTyDAL.getTable(statements);
    }
    public ArrayList<Mat_hangMD> getDanhSachMatHang(String... statements){
        return matHangDAL.getTable(statements);
    }
    public ArrayList<Loai_hangMD> getDanhSachLoaiHang(String... statements){
        return loaiHangDAL.getTable(statements);
    }
    public ArrayList<Khuvuc_loaihangMD> getDanhSachKhuVucLoai(String... statements){
        return khuVucLoaiDAL.getTable(statements);
    }
    public ArrayList<KhuvucMD> getDanhSachKV(String... statements){
        return kvDAL.getTable(statements);
    }
    public ArrayList<KhuvucMD> getDanhSachKhuVuc_MH(String maMH,String... statements){
        ArrayList<KhuvucMD> dsKVReturn = new ArrayList<KhuvucMD>();
        ArrayList<KhuvucMD> dsKVDuyet = kvDAL.getTable(util.themStringVaoArray("MaKho="+UI.khoNVDangNhap.getMaKho(), statements));
        String maLoai = matHangDAL.getFirst("MaMH= "+maMH).getMaLoai();
        if(dsKVDuyet==null){
            return null;
        }
        ArrayList<Khuvuc_loaihangMD> dsKVL = khuVucLoaiDAL.getTable();
        for(KhuvucMD kv : dsKVDuyet){
            for(Khuvuc_loaihangMD kvl : dsKVL){
                if(kvl.getMaKV().equals(kv.getMaKV()) && kvl.getMaLoai().equals(maLoai)){
                    dsKVReturn.add(kv);
                }
            }
        }
        return dsKVReturn;
    }
    public void themDonNhapMoi(DonNhapMD dn,ArrayList<ChitietdonnhapMD> ctdn){
        donNhapDAL.addOne(dn);
        ctdnDAL.add(ctdn);
    }
    public String taoMaDonNhapMoi(){
        DecimalFormat df = new DecimalFormat("0000");
        ArrayList<DonNhapMD> dsDN = donNhapDAL.getTable();
        int soLuong = 0;
        if(dsDN!=null){
            soLuong = dsDN.size();
        }
        String maDNMoi = "DN"+df.format(soLuong+1);
        return maDNMoi;
    }   
    
    
    public void themDonXuatMoi(DonXuatMD dx, ArrayList<ChitietdonxuatMD> ctdx){
        donXuatDAL.addOne(dx);
        ctdxDAL.add(ctdx);
    }
    public String taoMaDonXuatMoi(){
        DecimalFormat df = new DecimalFormat("0000");
        ArrayList<DonXuatMD> dsDN = donXuatDAL.getTable();
        int soLuong = 0;
        if(dsDN!=null){
            soLuong = dsDN.size();
        }
        String maDNMoi = "DX"+df.format(soLuong+1);
        return maDNMoi;
    }    
    public float getSoLuongCL_KV(String maKV){
        ArrayList<ChitietdonnhapMD> dsCTDN = ctdnDAL.getTable("MaKV="+maKV);
        float tongSucChua = 0;
        if(dsCTDN!=null){
            for(ChitietdonnhapMD ctdn : dsCTDN){
                if(maKV.equals(ctdn.getMaKV())){
                    tongSucChua+=ctdn.getSLConLai();
                }
            }
        }
        return tongSucChua;
    }

    public Mat_hangMD getFirstMH(String maMH){
        return matHangDAL.getFirst("MaMH="+maMH);
    }
    public KhuvucMD getFirstKV(String maKV){
        return kvDAL.getFirst("MaKV="+maKV);
    }

    public ArrayList<Object[]> getDanhSachMHChon(String maCty,String... statements){
        ArrayList<Object[]> dsMHReturn = new ArrayList<Object[]>();
        ArrayList<DonNhapMD> dsDonNhap = donNhapDAL.getTable("MaKho="+UI.khoNVDangNhap.getMaKho(),"MaCty = "+maCty);
        ArrayList<Mat_hangMD> dsMH = matHangDAL.getTable("MaCty="+maCty);
        ArrayList<ChitietdonnhapMD> dsCTDN = ctdnDAL.getTable(statements);
        if(dsCTDN==null){
            return null;
        }
        if(dsDonNhap==null){
            return null;
        }
        for(ChitietdonnhapMD ctdn : dsCTDN){
            for(DonNhapMD dn: dsDonNhap){
                if(ctdn.getMaDonNhap().equals(dn.getMaDonNhap())){
                    for(Mat_hangMD mh : dsMH){
                        if(mh.getMaMH().equals(ctdn.getMaMH())){
                            dsMHReturn.add(new Object[]{mh.getMaMH(),mh.getTenMH(),ctdn.getMaKV(),ctdn.getSLConLai(),dn.getMaDonNhap(),dn.getNgayNhap()});
                        }  
                    }
                }
            }
        }
        return dsMHReturn;
    }
}
