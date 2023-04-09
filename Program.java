import java.awt.event.*;

import Login.*;
import Panel.*;
import SQL.SQLUser;


public class Program {
    private final String url = "jdbc:mysql://localhost:3306/QuanLyKho";
    private final String url2 = "jdbc:mysql://26.236.133.174:3306/QuanLyKho";

    private final String username = "master";
    private final String password = "123";


    //còn cái này chắc là model (kiểu như nó dùng để gọi đến cơ sở dữ liệu)
    private SQLUser master;

    public Program(){
        ChonURL frameChon = new ChonURL();
        frameChon.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e){
                frameChon.getLuachon();
                if(frameChon.getLuachon().equals("ip")){
                    master = new SQLUser(url2, username, password);
                    dangNhap();
                }
                else if(frameChon.getLuachon().equals("localhost")){
                    master = new SQLUser(url, username, password);
                    dangNhap();
                }
            }
        });
    }

    public void dangNhap(){
        DangNhap dn = new DangNhap(master);
        dn.addWindowEvent(new WindowAdapter() {
            public void windowClosed(WindowEvent e){
                UI ui = new UI(master);
            }
        });
    }
}
