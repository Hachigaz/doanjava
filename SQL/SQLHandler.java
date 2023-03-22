package SQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Properties;

public class SQLHandler {
    private String url;
    private String username;
    private String password;

    public SQLHandler(String url,String username,String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    public DataSet query(String sql){
        String connectionUrl =url;
        Properties info = new Properties();
        info.setProperty("characterEncoding", "utf8");
        info.setProperty("user", username);
        info.setProperty("password", password);

        DataSet ds = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl,info);) {
            Statement statement = connection.createStatement();
            
            ResultSet rs = statement.executeQuery(sql);

            ds = new DataSet(rs);

            rs.close();
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        return ds;
    }
}