package Panel.ThongTinSP;

import java.util.ArrayList;

import DAL.DataAccessLayer;
import DTO.Mat_hangMD;
import DTO.Custom.ThongTinSPMD;
import Panel.UI;
import SQL.SQLUser;
import misc.util;

public class ThongTinSPBLL {

    private DataAccessLayer<Mat_hangMD> matHangDAL;
    private DataAccessLayer<ThongTinSPMD> ttspDAL;
    private String maCtyChon;
    static SQLUser master = new SQLUser("jdbc:mysql://localhost:3306/QuanLyKho", "master", "123");

    public ThongTinSPBLL(String maCtyChon){
        //SQLUser master = UI.master;
        this.maCtyChon = maCtyChon;
        matHangDAL = new DataAccessLayer<>(master, Mat_hangMD.class);
        ttspDAL = new DataAccessLayer<>(master, ThongTinSPMD.class);
    }
    public ArrayList<Mat_hangMD> getDanhSachMatHangCty(String... statements){
        String[] newStatements = util.themStringVaoArray("MaCty="+maCtyChon, statements);
        return matHangDAL.getTable(newStatements);
    }
    public ArrayList<ThongTinSPMD> getDanhSachTTSP(String... statements){
        String[] newStatements = util.themStringVaoArray(maCtyChon, statements);
        return ttspDAL.getTable(newStatements);
    }
    public String taoMaMatHangMoi(){
        String maMHMoi = "MH_"+maCtyChon.split("Cty_")+"_";
        return maMHMoi;
    }
    public void themMatHangMoi(Mat_hangMD mhMoi){
        matHangDAL.addOne(mhMoi);
    }
}