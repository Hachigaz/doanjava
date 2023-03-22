package SQL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.ArrayList;

public class DataSet {
    private String columnName[];
    private ArrayList<ArrayList<Object>> data;
    private int length;

    public DataSet(){
        this.columnName = null;
        this.data = null;
    }

    public DataSet(ResultSet rs){
        try{
            ResultSetMetaData metaData = rs.getMetaData();
            this.columnName = new String[metaData.getColumnCount()];
            this.data = new ArrayList<ArrayList<Object>>();
            for(int i = 0 ; i < this.columnName.length;i++){
                this.columnName[i] = metaData.getColumnName(i+1);
                this.data.add(new ArrayList<Object>());
            }
            if(rs.isBeforeFirst()){
                rs.next();
            }
            while(!rs.isAfterLast()){
                for(int i = 0 ; i < this.columnName.length;i++){
                    this.data.get(i).add(rs.getString(i+1));
                }
                rs.next();
            }
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }

        length = this.data.get(0).size();
    }
    
    public String[] getColumnName(){
        return this.columnName;
    }

    public Object[][] getData(){
        Object[][] objArr = new Object[this.data.size()][length];
        for(int i = 0 ; i < this.data.size();i++){
            for(int j = 0 ; j < length;j++){
                objArr[i][j] = this.data.get(i).get(j);
            }
        }
        return objArr;
    }

    public void print(){
        for(int i = 0 ; i < this.data.size();i++){
            for(int j = 0 ; j < this.data.get(i).size();j++){
                System.out.println(this.data.get(i).get(j));
            }
        }
    }
}
