package SQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Array;
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
    
    public String[] query(String sql){
        String connectionUrl =url;
        Properties info = new Properties();
        info.setProperty("characterEncoding", "utf8");
        info.setProperty("user", username);
        info.setProperty("password", password);
        
        String[] resultString = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl,info);) {
            Statement statement = connection.createStatement();
            
            ResultSet data = statement.executeQuery(sql);
            ResultSetMetaData metaData = data.getMetaData();
            data.next();
            
            resultString = new String[metaData.getColumnCount()];
            for(int i = 1 ; i < metaData.getColumnCount();i++){
                resultString[i] = new String();
                resultString[i]+=metaData.getColumnName(i)+',';
            }
            while(!data.isLast()){
                for(int i = 0 ;i <resultString.length;i++){
                    resultString[i]+=data.getString(i+1);
                }
                data.next();
                if(!data.isLast()){
                    for(int i = 0; i < resultString.length; i++){
                        resultString[i]+=",";
                    }
                }
            }
            for(int i = 1 ; i < resultString.length;i++){
                System.out.println(resultString[i]);
            }
            data.close();
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        return resultString;
    }
}