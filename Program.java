import java.awt.event.*;

import UI.DangNhapUI;
import SQL.SQLHandler;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Program {
    private final String url = "jdbc:mysql://localhost:3306/QuanLyKho";
    private final String username = "master";
    private final String password = "123";

    private SQLHandler masterHandler;
    private SQLHandler employeeHandler;

    public Program(){
        masterHandler = new SQLHandler(url, username, password);

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
