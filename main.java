
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Properties;

import UI.DangNhapUI;
public class Main{
    public static void connectToMySQL(){
        String connectionUrl ="jdbc:mysql://localhost:3306/QuanLyKho";
        Properties info = new Properties();
        info.setProperty("characterEncoding", "utf8");
        info.setProperty("user", "master");
        info.setProperty("password", "123");
        
        try (Connection connection = DriverManager.getConnection(connectionUrl,info);) {
            Statement statement = connection.createStatement();
            String sql = "SELECT * from kho";
            ResultSet data = statement.executeQuery(sql);
            while(!data.isLast()){
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
    public static void main(String[] args) {
        connectToMySQL();
        DangNhapUI dangNhapUI = new DangNhapUI();
    }
}
