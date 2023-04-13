package Panel.TraCuuHang;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import DAO.DataAccessLayer;
import SQL.*;
import Model.*;
import Model.Custom.DSTraCuuHangMD;

public class TraCuuHangCTR {
    private SQLUser master;
    private Taikhoan_nhanvienMD tkDangNhap;

    private TraCuuHangUI ui;
    private DataAccessLayer<DSTraCuuHangMD> TraCuuHangDAL;

    public TraCuuHangCTR(SQLUser user,Taikhoan_nhanvienMD tkdn){
        this.master = user;
        this.tkDangNhap = tkdn;
        TraCuuHangDAL = new DataAccessLayer<DSTraCuuHangMD>(user, DSTraCuuHangMD.class);

        ui = new TraCuuHangUI();
        

        ActionListener onChangeMaKho = new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ArrayList<DSTraCuuHangMD> dsTraCuu = TraCuuHangDAL.getTable("donnhap.MaKho = "+ui.getSelectedMaKhoKey());
                ui.SetTable(Model.to2DArray(dsTraCuu),TraCuuHangDAL.getReturnedColumnName());
            }
        };

        ActionListener onSubmitSearch = new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ui.timTheoGiaTri();
            }
        };
        
        ui.SetupPanelChucNang(master.getDataQuery("SELECT MaKho,TenKho from kho"), onChangeMaKho, onSubmitSearch);



        String[] tenLoc = {"Lọc theo khu vực","Lọc theo loại hàng"};
        DataSet dsLoc[] = new DataSet[2];
        dsLoc[0] = master.getDataQuery("SELECT MaKV,TenKV FROM khuvuc WHERE khuvuc.MaKho = '"+ui.getSelectedMaKhoKey()+"'");
        dsLoc[1] = master.getDataQuery("SELECT MaLoai,TenLoai FROM loai_hang");
        ui.SetupPanelLoc(dsLoc, tenLoc);

        //setup bảng        
        ArrayList<DSTraCuuHangMD> dsTraCuu = TraCuuHangDAL.getTable("donnhap.MaKho = "+ui.getSelectedMaKhoKey());
        ui.SetTable(Model.to2DArray(dsTraCuu),TraCuuHangDAL.getReturnedColumnName());
    }

    public TraCuuHangUI getUI() {
        return ui;
    }
}
