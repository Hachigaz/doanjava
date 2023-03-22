package SQL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.ArrayList;

public class DataSet {
    private String columnName[];
    private ArrayList<Object> data[];

    public DataSet(){
        this.columnName = null;
        this.data = null;
    }

    public DataSet(ResultSet rs){
        try{
            ResultSetMetaData metaData = rs.getMetaData();

            this.columnName = new String[metaData.getColumnCount()];
            for(int i = 0 ; i < this.columnName.length;i++){
                this.columnName[i] = metaData.getColumnName(i+1);
                this.data[i] = new ArrayList<Object>();
            }
            for(int i = 0 ; i < metaData.getColumnCount();i++){ 
                System.out.println(this.columnName[i]);
            }

            if(rs.isBeforeFirst()){
                rs.next();
            }
            while(!rs.isAfterLast()){
                for(int i = 0 ; i < this.columnName.length;i++){
                    this.data[i].add(rs.getString(i));
                }
                rs.next();
            }
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }
}
