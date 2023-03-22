import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JTable;

import UI.DangNhapUI;
import SQL.SQLHandler;
import SQL.DataSet;

public class Program {
    private final String url = "jdbc:mysql://localhost:3306/QuanLyKho";
    private final String url2 = "jdbc:mysql://192.168.25.159:3306/QuanLyKho";

    private final String username = "master";
    private final String password = "123";

    private SQLHandler masterHandler;

    public Program(){
        masterHandler = new SQLHandler(url, username, password);

        DangNhapUI dangNhapUI = new DangNhapUI();
        ActionListener submitAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(dangNhapUI.getUsernameInput());
                System.out.println(dangNhapUI.getPasswordInput());
                DataSet ds = masterHandler.query("SELECT * FROM nhanvien");

                
                JFrame frame = new JFrame();
                frame.add(new JTable(ds.getData(),ds.getColumnName()));
                frame.setVisible(true);
                frame.pack();
            }
        };
        dangNhapUI.setSubmitAction(submitAction);
    }
}
