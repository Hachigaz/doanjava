package Panel.TraCuuHang;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

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
                ui.SetTable(new DefaultTableModel(Model.to2DArray(dsTraCuu),TraCuuHangDAL.getReturnedColumnName()));
            }
        };

        ActionListener onSubmitSearch = new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ui.timTheoGiaTri();
            }
        };
        
        ui.SetupPanelChucNang(master.getDataQuery("SELECT MaKho,TenKho from kho"), onChangeMaKho, onSubmitSearch);



        String[] locPanelTitle = {"Lọc theo khu vực","Lọc theo loại hàng","Lọc theo sản phẩm của công ty"};
        int[] columnIndexes = {0,3,4};
        ArrayList<ArrayList<String>> tenLoc = new ArrayList<ArrayList<String>>();

        
        DataAccessLayer<KhuvucMD> KhuVucDAL = new DataAccessLayer<KhuvucMD>(user, KhuvucMD.class);
        ArrayList<KhuvucMD> danhSachKV = KhuVucDAL.getTable("MaKho = "+ui.getSelectedMaKhoKey());


        tenLoc.add(new ArrayList<String>());
        for(KhuvucMD khuvuc : danhSachKV){          
            tenLoc.get(0).add(khuvuc.getTenKV());
        }

        DataAccessLayer<Loai_hangMD> LoaiHangDAL = new DataAccessLayer<Loai_hangMD>(user, Loai_hangMD.class);
        ArrayList<Loai_hangMD> danhSachLH = LoaiHangDAL.getTable();
        

        tenLoc.add(new ArrayList<String>());
        for(Loai_hangMD loaihang : danhSachLH){          
            tenLoc.get(1).add(loaihang.getTenloai());
        }

        DataAccessLayer<CongtyMD> CongTyDAL = new DataAccessLayer<CongtyMD>(user, CongtyMD.class);
        ArrayList<CongtyMD> danhSachCT = CongTyDAL.getTable();
        

        tenLoc.add(new ArrayList<String>());
        for(CongtyMD cty : danhSachCT){          
            tenLoc.get(2).add(cty.getTenCty());
        }
        



        MouseListener panelCollapseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ui.findClickedLocPanel(e.getSource());
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        };

        ItemListener locCheckboxAction = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                ui.sortSelectedCheckbox(e.getSource());
            }
        };

        ui.SetupPanelLoc(locPanelTitle, columnIndexes, tenLoc, panelCollapseListener,locCheckboxAction);

        //setup bảng        
        ArrayList<DSTraCuuHangMD> dsTraCuu = TraCuuHangDAL.getTable("donnhap.MaKho = "+ui.getSelectedMaKhoKey());
        ui.SetTable(new DefaultTableModel(Model.to2DArray(dsTraCuu),TraCuuHangDAL.getReturnedColumnName()));
    }

    public TraCuuHangUI getUI() {
        return ui;
    }
}
