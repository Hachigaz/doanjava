package Login;

import java.awt.event.*;
import java.util.ArrayList;

import DAL.DataAccessLayer;
import Model.Taikhoan_nhanvienMD;
import SQL.SQLUser;
import misc.ThongBaoDialog;
public class DangNhap implements ActionListener {

    private DangNhapUI dangnhapui;
    private SQLUser master;

    private Taikhoan_nhanvienMD tkDangNhap = null;

    public DangNhap(SQLUser master){
        dangnhapui = new DangNhapUI();
        this.master = master;
        dangnhapui.setSubmitAction(this);
    }
    public void actionPerformed(ActionEvent e) {
        String tentk = DangNhapUI.getUsernameInput();
        String mk = dangnhapui.getPasswordInput();
        
        //lấy thông tin từ csdl
        DataAccessLayer<Taikhoan_nhanvienMD> tknvDAL = new DataAccessLayer<>(master, Taikhoan_nhanvienMD.class);
        ArrayList<Taikhoan_nhanvienMD> tknv = tknvDAL.getTable("TenTaiKhoan="+tentk,"MatKhau = "+mk);

        if(tknv.size()==1){//là tìm thấy tài khoản trong csdl
            Taikhoan_nhanvienMD tk = tknv.get(0);
            tkDangNhap = new Taikhoan_nhanvienMD(tk.getMaNV(),tk.getTenTaiKhoan(),tk.getMatKhau(),tk.getMaNhomQuyen());
            dangnhapui.dispose();
        }
        else if(tknv.size()==0){//là không tìm thấy tài khoản trong csdl
            new ThongBaoDialog("Thông tin đăng nhập không đúng!", dangnhapui);
        }
        else{
            new ThongBaoDialog("Lỗi không xác định",dangnhapui);
        }
    }
    public Taikhoan_nhanvienMD getTenTKDangNhap() {
        return tkDangNhap;
    }
    public void addWindowEvent(WindowAdapter a){
        dangnhapui.addWindowListener(a);
    }
}
