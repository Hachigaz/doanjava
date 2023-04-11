package Panel.TraCuuHang;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import SQL.*;
import misc.*;

public class TraCuuHangCTR {
    private final String sqlDSMH = 
    "SELECT kv.TenKV as 'Khu vực', mh.TenMH as 'Tên mặt hàng', ctdn.SLConLai as 'Số lượng', loai.TenLoai as 'Loại sản phẩm', DATE(dn.NgayNhap) as 'Ngày nhập'\n"+
    "FROM mat_hang mh, khuvuc kv,chitiet_donnhap ctdn, donnhap dn, loai_hang loai\n"+
    "where kv.MaKV = ctdn.MaKV AND mh.MaMH = ctdn.MaMH AND dn.MaDonNhap = ctdn.MaDonNhap AND loai.MaLoai = mh.MaLoai";

    private SQLUser master;
    TaiKhoanDangNhap tkDangNhap;

    private TraCuuHangUI ui;


    public TraCuuHangCTR(SQLUser user,TaiKhoanDangNhap tkdn){
        this.master = user;
        this.tkDangNhap = tkdn;

        ui = new TraCuuHangUI();
        String sql = sqlDSMH+" AND dn.MaKho = '" + util.objToString(master.getDataQuery("SELECT Kho_Lam_Viec FROM nhanvien WHERE MaNV = '"+ tkdn.getMaNV()+"'").getRow(0))[0] +"'";
        ui.SetTable(master.getDataQuery(sql));

        ActionListener onChangeMaKho = new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String sql = sqlDSMH + " AND dn.MaKho = '"+ui.getSelectedMaKhoKey()+"'";
                ui.SetTable(master.getDataQuery(sql));
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
    }

    public TraCuuHangUI getUI() {
        return ui;
    }
}
