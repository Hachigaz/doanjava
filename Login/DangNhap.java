package Login;

import SQL.*;

import java.awt.event.*;

import SQL.SQLUser;
import misc.TaiKhoanDangNhap;
import misc.ThongBaoDialog;
public class DangNhap implements ActionListener {

    private DangNhapUI dangnhapui;
    private SQLUser master;
    private DataSet ds;

    private TaiKhoanDangNhap tkDN = null;

    public DangNhap(SQLUser master){
        ds = null;
        dangnhapui = new DangNhapUI();
        this.master = master;
        dangnhapui.setSubmitAction(this);
    }
    public void actionPerformed(ActionEvent e) {
        String tentk = DangNhapUI.getUsernameInput();
        String mk = dangnhapui.getPasswordInput();
        
        String sql = "select TenTaiKhoan,MaNV,MaNhomQuyen from taikhoan_nhanvien tknv where tknv.TenTaiKhoan = '"+tentk+"' and '"+mk+"'=tknv.MatKhau";
        ds = master.getDataQuery(sql);
        if(ds!=null){//là tìm thấy tài khoản trong csdl
            tkDN = new TaiKhoanDangNhap(ds.getRow(0)[0].toString(),ds.getRow(0)[1].toString(),ds.getRow(0)[2].toString());
            dangnhapui.dispose();
        }
        else{//là không tìm thấy tài khoản trong csdl
            new ThongBaoDialog("Thông tin đăng nhập không đúng!", dangnhapui);
        }
    }
    public TaiKhoanDangNhap getTenTKDangNhap() {
        return tkDN;
    }
    public void addWindowEvent(WindowAdapter a){
        dangnhapui.addWindowListener(a);
    }
}
