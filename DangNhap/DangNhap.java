package DangNhap;

import UI.*;
import SQL.*;
import Panel.*;
import javax.swing.*;

import java.awt.event.*;

import SQL.SQLUser;
public class DangNhap implements ActionListener {

    private DangNhapUI dangnhapui;
    private SQLUser master;

    public DangNhap(SQLUser master){
        dangnhapui = new DangNhapUI();
        this.master = master;
        dangnhapui.setSubmitAction(this);
    }
    public void actionPerformed(ActionEvent e) {
        String tentk = dangnhapui.getUsernameInput();
        String mk = dangnhapui.getPasswordInput();
        
        String sql = "select * from taikhoan_nhanvien tknv where tknv.TenTaiKhoan = '"+tentk+"' and '"+mk+"'=tknv.MatKhau";
        DataSet ds = master.getDataQuery(sql);
        if(ds!=null){//là tìm thấy tài khoản trong csdl
            Test test = new Test(master);
        }
        else{//là không tìm thấy tài khoản trong csdl
            JDialog dialog = new JDialog(dangnhapui,JDialog.ModalityType.DOCUMENT_MODAL);
            dialog.setVisible(true);
        }
    }
}
