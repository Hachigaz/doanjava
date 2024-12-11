package misc;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class DataSet {
    private String columnName[];
    private String columnLabel[];
    private ArrayList<ArrayList<Object>> data;
    private int rowCount;
    private int columnCount;

    public DataSet(){
        this.columnName = null;
        this.columnLabel=null;
        this.data = null;
        this.rowCount=0;
        this.columnCount=0;
    }    
    public DataSet(String[] columnName,String[] columnLabel){
        this.columnName = columnName;
        this.columnLabel = columnLabel;
        this.columnCount = columnName.length;
    }

    public DataSet(DataSet ds){
        this.columnLabel= ds.columnLabel.clone();
        this.columnName = ds.columnName.clone();
        this.data = new ArrayList<ArrayList<Object>>(ds.data);
        this.rowCount= ds.rowCount;
        this.columnCount = ds.columnCount;
    }

    public DataSet(List<List<Object>> list){
        this.data = new ArrayList<ArrayList<Object>>();
        for(int i = 0 ; i < list.size();i++){
            ArrayList<Object> arrTemp = new ArrayList<Object>();
            for(int j = 0 ; j < list.get(i).size();i++){
                arrTemp.add(list.get(i).get(j));
            }
            this.data.add(arrTemp);
        }

    }

    public DataSet(ResultSet rs){
        try{
            ResultSetMetaData metaData = rs.getMetaData();
            this.columnName = new String[metaData.getColumnCount()];
            this.columnLabel = new String[metaData.getColumnCount()];
            this.data = new ArrayList<ArrayList<Object>>();
            for(int i = 0 ; i < this.columnName.length;i++){
                this.columnName[i] = metaData.getColumnName(i+1);
                this.columnLabel[i] = metaData.getColumnLabel(i+1);
                this.columnCount=this.columnName.length;
            }
            if(rs.isBeforeFirst()){
                rs.next();
            }
            while(!rs.isAfterLast()){
                ArrayList<Object> rowData = new ArrayList<Object>();
                for(int i = 0 ; i < columnCount;i++){
                    String dataType = metaData.getColumnTypeName(i+1);
                    if(dataType.equals("INT")){
                        Integer value = rs.getInt(i+1);
                        if(rs.wasNull()){
                            value = -1;
                        }
                        rowData.add(value);
                    }
                    else if(dataType.equals("FLOAT")){
                        Float value = rs.getFloat(i+1);
                        if(rs.wasNull()){
                            value = -1.0f;
                        }
                        rowData.add(value);
                    }
                    else{
                        String string = rs.getString(i+1);
                        if(rs.wasNull()){
                            string="N/A";
                        };
                        rowData.add(new String(string));
                    }
                }
                this.data.add(rowData);
                rs.next();
            }
            this.rowCount=this.data.size();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void add(ArrayList<Object> dataRow){
        this.data.add(dataRow);
        this.rowCount++;
    }

    public Object[] getRow(int index){
        if(index>=0 && index<rowCount){
            return this.data.get(index).toArray();
        }
        else{
            return null;
        }
    }
    
    public String[] getColumnName(){
        return this.columnName;
    }
    public String[] getColumnLabel() {
        return this.columnLabel;
    }

    public Object[][] getData(){
        Object[][] dataReturn = new Object[this.rowCount][this.columnCount];
        for(int i = 0 ; i < rowCount;i++){
            for(int j = 0 ; j < this.columnCount ; j++){
                dataReturn[i][j] = this.data.get(i).get(j);
            }
        }
        return dataReturn;
    }
    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public Object[] getColumn(int index){
        if(index < 0 && index >= this.columnCount){
            return null;
        }
        else{
            ArrayList<Object> columnData = new ArrayList<>();
            for(int i = 0 ; i < this.rowCount;i++){
                columnData.add(this.data.get(i).get(index));
            }
            return columnData.toArray();
        }
    }

    public Object[] getColumn(String colName){
        for(int i  = 0 ; i  < this.columnCount;i++){
            if(columnName[i].equals(colName)){
                return getColumn(i);
            }
        }
        return null;
    }

    public DataSet locGiaTri(int index,Object giaTri){
        DataSet ds = new DataSet();
        for(int i = 0 ; i < this.rowCount;i++){
            if(this.data.get(index).get(i).equals(giaTri)){
                this.add(this.data.get(i));
            }
        }
        ds.printData();
        return ds;
    }

    public void printColumnName(){
        String s = "";
        for(int i = 0 ; i < this.columnCount;i++){
            s+=columnName[i]+" ";
        }
        System.out.println(s);
    }

    public void printData(){
        for(int i = 0 ; i < this.rowCount;i++){
            String data = "";
            for(int j = 0 ; j < this.columnCount;j++){
                data += ((this.data.get(i).get(j)).toString() + ' ');
            }
            System.out.println(data);
        }
    }
}
