import java.awt.Dialog;
import java.awt.event.*;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;

import DangNhap.DangNhap;
import UI.DangNhapUI;
import temp.test;
import SQL.SQLUser;
import SQL.DataSet;

import Panel.*;

public class Program {
    private final String url = "jdbc:mysql://localhost:3306/QuanLyKho";
    private final String url2 = "jdbc:mysql://26.236.133.174:3306/QuanLyKho";

    private final String username = "master";
    private final String password = "123";

    private boolean testgiaodien = false;

    //tên tài khoản đăng nhập zo phần mềm hiện tại
    private String tkDangNhap;

    //còn cái này chắc là model (kiểu như nó dùng để gọi đến cơ sở dữ liệu)
    private SQLUser master;

    private JFrame uiHienTai;
    
    public Program(){
        master = new SQLUser(url, username, password);

        this.dangNhap();
        // ActionListener submitDanhMucSP = new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         DataSet ds = master.query("select * from mat_hang");
        //         // JTable table = new JTable(ds.getData(),ds.getColumnName());
                
                
        //         JFrame frame = new JFrame();

        //         JTable table = new JTable(ds.getData(),ds.getColumnName());

        //         Test test = new Test();
        //         frame.add(table);
        //         frame.setVisible(true);
        //     }
        // };
        // dangNhapUI.setSubmitAction(submitAction);


    }

    
    public void dangNhap(){
        DangNhapUI dangnhapui = new DangNhapUI();
        uiHienTai = dangnhapui;
        ActionListener submitDanhMucSP = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!testgiaodien){
                    String tentk = dangnhapui.getUsernameInput();
                    String mk = dangnhapui.getPasswordInput();
                    
                    String sql = "select * from taikhoan_nhanvien tknv where tknv.TenTaiKhoan = '"+tentk+"' and '"+mk+"'=tknv.MatKhau";
    
                    DataSet ds = master.getDataQuery(sql);
                    if(ds!=null){//là tìm thấy tài khoản trong csdl
                        Test test = new Test(master);
                    }
                    else{//là không tìm thấy tài khoản trong csdl
                        JDialog dialog = new JDialog(uiHienTai,Dialog.ModalityType.DOCUMENT_MODAL);
                        dialog.setVisible(true);
                    }
                }
                else{
                    Test test = new Test(master);
                }
            }
        };
        dangnhapui.setSubmitAction(submitDanhMucSP);
    }
}
