import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JTable;

import UI.DangNhapUI;
import SQL.SQLHandler;
import SQL.DataSet;

import Panel.*;

public class Program {
    private final String url = "jdbc:mysql://localhost:3306/QuanLyKho";
    private final String url2 = "jdbc:mysql://192.168.31.38:3306/QuanLyKho";

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
                DataSet ds = masterHandler.query("select * from taikhoan_nhanvien");
                JFrame frame = new JFrame();

                JTable table = new JTable(ds.getData(),ds.getColumnName());
                Test test = new Test(masterHandler);
                frame.add(table);
                frame.setVisible(true);
            }
        };
        dangNhapUI.setSubmitAction(submitAction);

        // ActionListener submitDanhMucSP = new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         DataSet ds = masterHandler.query("select * from mat_hang");
        //         // JTable table = new JTable(ds.getData(),ds.getColumnName());
                
                
        //         JFrame frame = new JFrame();

        //         JTable table = new JTable(ds.getData(),ds.getColumnName());

        //         Test test = new Test();
        //         frame.add(table);
        //         frame.setVisible(true);
        //     }
        // };
        // dangNhapUI.setSubmitAction(submitAction);

        ActionListener submitDanhMucSP = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // DataSet ds = masterHandler.query("select * from mat_hang");
                // JTable table = new JTable(ds.getData(),ds.getColumnName());

                DataSet ds = masterHandler.query("select * from mat_hang");
                
                
                JFrame frame = new JFrame();

                JTable table = new JTable(ds.getData(),ds.getColumnName());

                Test test = new Test(masterHandler);

                frame.add(table);
                frame.setVisible(true);
            }
        };
    }
}
