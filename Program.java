import java.awt.event.*;

import UI.DangNhapUI;
import SQL.SQLHandler;

public class Program {
    private final String url = "jdbc:mysql://localhost:3306/QuanLyKho";
    private final String url2 = "jdbc:mysql://192.168.25.159:3306/QuanLyKho";

    private final String username = "master";
    private final String password = "123";

    private SQLHandler masterHandler;

    public Program(){
        masterHandler = new SQLHandler(url2, username, password);

        DangNhapUI dangNhapUI = new DangNhapUI();
        ActionListener submitAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(dangNhapUI.getUsernameInput());
                System.out.println(dangNhapUI.getPasswordInput());
                masterHandler.query("SELECT * FROM nhanvien");
            }
        };
        dangNhapUI.setSubmitAction(submitAction);
    }
}
