package SQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Properties;

import misc.DataSet;

public class SQLUser {
    private String url;
    private Properties info;

    public SQLUser(String url,String username,String password){
        this.url = url;
        
        info = new Properties();
        info.setProperty("characterEncoding", "utf8");
        info.setProperty("user", username);
        info.setProperty("password", password);
    }
    
    public int updateQuery(String sql){
        int rowsUpdated = 0;
        try (Connection connection = DriverManager.getConnection(url,info);) {
            Statement statement = connection.createStatement();
            
            rowsUpdated = statement.executeUpdate(sql);
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        return rowsUpdated;
    }

    public DataSet getDataQuery(String sql){
        DataSet ds = null;
        try (Connection connection = DriverManager.getConnection(url,info);) {
            Statement statement = connection.createStatement();
            
            ResultSet rs = statement.executeQuery(sql);
            if(rs.isBeforeFirst()){
                 ds = new DataSet(rs);
                 rs.close();
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        return ds;
    }
}