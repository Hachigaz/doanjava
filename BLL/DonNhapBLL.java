package BLL;

import java.awt.List;
import DTO.*;
import DAL.DonNhapDAL;

public class DonNhapBLL {
    private DonNhapDAL donnhapDAL;

    public DonNhapBLL(DonNhapDAL donNhapDAL)
    {
        this.donnhapDAL = donNhapDAL;
    }

    // public List<DonNhapMD> getDonnhap(){
    //     return DonNhapDAL.getALLDonnhap();
    // }

    public void addDonnhap(DonNhapMD donnhap){
        DonNhapDAL.addDonnhap(donnhap);
    }
}
