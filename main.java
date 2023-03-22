import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.swing.JButton;

public class main{
    
    public static void connectToMySQL(){
        String connectionUrl ="jdbc:mysql://172.20.10.5:3306/test";
        Properties info = new Properties();
        info.setProperty("characterEncoding", "utf8");
        info.setProperty("user", "master");
        info.setProperty("password", "123");
        
        try (Connection connection = DriverManager.getConnection(connectionUrl,info);) {
            Statement statement = connection.createStatement();
            String sql = "SELECT * from sinhvien";
            ResultSet data = statement.executeQuery(sql);
            for(int i = 0 ; i < 20 ; i++){
                data.next();
                System.out.println(data.getString(1)+" "+data.getString(2));
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
    }
    // public static void connectToSQLServer(){
    //     String connectionUrl =
    //     "jdbc:sqlserver://localhost;"
    //             + "database=QuanLyKho;"
    //             + "user=sa;"
    //             + "password=sa;"
    //             + "encrypt=true;"
    //             + "trustServerCertificate=false;"
    //             + "loginTimeout=30;";
                
    //     try (Connection connection = DriverManager.getConnection(connectionUrl);) {

    //     }
    //     // Handle any errors that may have occurred.
    //     catch (SQLException e) {
    //         e.printStackTrace();
    //         System.out.println(e.getLocalizedMessage());
    //     }
    // }
    public static void main(String[] args) {
        // javax.swing.JFrame frame = new javax.swing.JFrame("Phan mem quan ly kho");
        // frame.setBounds(10,10,800,600);
        // //frame.setVisible(true);

        // javax.swing.JFrame frameDangNhap = new javax.swing.JFrame("Dang nhap");
        // frameDangNhap.setAlwaysOnTop(true);
        // frameDangNhap.setBounds(300,300,500,250);
        // javax.swing.JButton button = new javax.swing.JButton("Dang Nhap");

        connectToMySQL();
        //connectToSQLServer();
    }
}
