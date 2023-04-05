package SQL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.ArrayList;

public class DataSet {
    private String columnName[];
    private String columnLabel[];
    private ArrayList<ArrayList<Object>> data;
    private Object[][] dataArr;
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
                this.columnLabel[i] = metaData.getColumnLabel(i+1);
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

        dataArr = new Object[length][this.data.size()];
        for(int i = 0 ; i < length;i++){
            for(int j = 0 ; j < this.data.size();j++){
                dataArr[i][j] = this.data.get(j).get(i);
            }
        }
    }
    
    public String[] getColumnName(){
        return this.columnName;
    }

    public Object[][] getData(){
        return dataArr;
    }
    public int getLength() {
        return length;
    }

    public Object[] getColumn(int index){
        return dataArr[index];
    }

    public Object[] getColumn(String colName){
        for(int i  = 0 ; i  < columnName.length;i++){
            if(columnName[i].equals(colName)){
                return dataArr[i];
            }
        }
        return null;
    }

    public void printColumnName(){
        String s = "";
        for(int i = 0 ; i < columnName.length;i++){
            s+=columnName[i]+" ";
        }
        System.out.println(s);
    }

    public void printData(){
        for(int i = 0 ; i < this.length;i++){
            String data = "";
            for(int j = 0 ; j < this.data.size();j++){
                data += ((this.data.get(j).get(i)).toString() + ' ');
            }
            System.out.println(data);
        }
    }
}
