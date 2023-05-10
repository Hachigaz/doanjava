package BLL;

import java.text.DecimalFormat;
import java.util.ArrayList;

import DAL.DataAccessLayer;
import DTO.ChitietdonnhapMD;
import DTO.ChitietdonxuatMD;
import DTO.Loai_hangMD;
import DTO.Mat_hangMD;
import DTO.Custom.ThongTinSPMD;
import SQL.SQLUser;
import misc.util;

public class FormCTDXBLL {

    private DataAccessLayer<Mat_hangMD> matHangDAL;
    private DataAccessLayer<ThongTinSPMD> ttspDAL;
    private DataAccessLayer<Loai_hangMD> loaiHangDAL;
    private DataAccessLayer<ChitietdonnhapMD> ctdnDAL;
    private DataAccessLayer<ChitietdonxuatMD> ctdxDAL;
    private String maDXChon;
    static SQLUser master = new SQLUser("jdbc:mysql://localhost:3306/QuanLyKho", "master", "123");

    public FormCTDXBLL(String maDXChon){
        //SQLUser master = UI.master;
        this.maDXChon = maDXChon;
        matHangDAL = new DataAccessLayer<>(master, Mat_hangMD.class);
        ttspDAL = new DataAccessLayer<>(master, ThongTinSPMD.class);
        loaiHangDAL = new DataAccessLayer<>(master, Loai_hangMD.class);
        ctdxDAL = new DataAccessLayer<>(master,ChitietdonxuatMD.class);
    }
    public ArrayList<Mat_hangMD> getDanhSachMatHangCty(String... statements){
        String[] newStatements = util.themStringVaoArray("MaDonXuat="+maDXChon, statements);
        return matHangDAL.getTable(newStatements);
    }
    public Mat_hangMD getFirstMatHangCty(String... statements){
        String[] newStatements = util.themStringVaoArray("MaDonXuat="+maDXChon, statements);
        return matHangDAL.getFirst(newStatements);
    }
    public boolean removeMatHang(String maMH){
        if(ctdnDAL.getTable(maMH)!=null){
            return false;
        }
        matHangDAL.remove(maMH);
        return true;
    }
    public void updateMatHang(String maMH,String... statements){
        matHangDAL.update(maMH, statements);
    }
    public ArrayList<ChitietdonxuatMD> getDanhSachTTSP(String... statements){
        String[] newStatements = util.themStringVaoArray("MaDonXuat="+maDXChon, statements);
        return ctdxDAL.getTable(newStatements);
    }
    public ArrayList<Loai_hangMD> getDanhSachLoaiHang(String... statements){
        return loaiHangDAL.getTable(statements);
    }
    public String taoMaMatHangMoi(){
        DecimalFormat df = new DecimalFormat("000");
        ArrayList<Mat_hangMD> dsmh = this.getDanhSachMatHangCty();
        int soluong = 0;
        if(dsmh != null){
            soluong = dsmh.size();
        }
        String maMHMoi = "MH_"+maDXChon.split("Cty_")[1]+"_"+df.format(+1);
        return maMHMoi;
    }
    public void themMatHangMoi(Mat_hangMD mhMoi){
        matHangDAL.addOne(mhMoi);
    }
    public String getMaDXChon(){
        return this.maDXChon;
    }
}