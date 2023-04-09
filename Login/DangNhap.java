package Login;

import SQL.*;

import java.awt.event.*;

import SQL.SQLUser;
import misc.ThongBaoDialog;
public class DangNhap implements ActionListener {

    private FormDN dangnhapui;/* */
    private SQLUser master;

    private DataSet ds;
    public DangNhap(SQLUser master){
        ds = null;
        dangnhapui = new FormDN();//* */
        this.master = master;
        dangnhapui.setSubmitAction(this);
    }
    public void actionPerformed(ActionEvent e) {
        String tentk = dangnhapui.getUsernameInput();
        String mk = dangnhapui.getPasswordInput();
        
        String sql = "select * from taikhoan_nhanvien tknv where tknv.TenTaiKhoan = '"+tentk+"' and '"+mk+"'=tknv.MatKhau";
        ds = master.getDataQuery(sql);
        if(ds!=null){//là tìm thấy tài khoản trong csdl
            dangnhapui.dispose();
        }
        else{//là không tìm thấy tài khoản trong csdl
            ThongBaoDialog tb = new ThongBaoDialog("Thông tin đăng nhập không đúng!", dangnhapui);
        }
    }
    public DataSet getDs() {
        return ds;
    }
    public void addWindowEvent(WindowAdapter a){
        dangnhapui.addWindowListener(a);
    }
}
