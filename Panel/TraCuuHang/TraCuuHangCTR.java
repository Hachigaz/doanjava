package Panel.TraCuuHang;

import java.awt.Dimension;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DAL.DataAccessLayer;
import DTO.*;
import DTO.Custom.DSTraCuuHangMD;
import SQL.*;
import misc.util;

public class TraCuuHangCTR {
    private SQLUser master;
    private Taikhoan_nhanvienMD tkDangNhap;

    private TraCuuHangUI ui;
    private DataAccessLayer<DSTraCuuHangMD> TraCuuHangDAL;

    public TraCuuHangCTR(SQLUser user,Taikhoan_nhanvienMD tkdn, Dimension d){
        this.master = user;
        this.tkDangNhap = tkdn;
        TraCuuHangDAL = new DataAccessLayer<DSTraCuuHangMD>(master, DSTraCuuHangMD.class);

        ui = new TraCuuHangUI(d);
        





        
    }

    public TraCuuHangUI getUI() {
        return ui;
    }
}
