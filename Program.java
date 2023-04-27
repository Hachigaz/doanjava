import java.awt.event.*;

import DTO.Taikhoan_nhanvienMD;
import Login.*;
import Panel.*;
import SQL.*;


public class Program {
    private final String url = "jdbc:mysql://26.236.133.174:3306/QuanLyKho";

    private final String username = "master";
    private final String password = "123";


    private  SQLUser master;

    public Program(){
        master = new SQLUser(url, username, password);
        dangNhap();
    }

    public void dangNhap(){
        DangNhap dn = new DangNhap(master);
        dn.addWindowEvent(new WindowAdapter() {
            public void windowClosed(WindowEvent e){
                Taikhoan_nhanvienMD tkDangNhap = dn.getTenTKDangNhap();
                if(tkDangNhap!=null){
                    new UI(master,tkDangNhap);
                }
            }
        });
    }
}
