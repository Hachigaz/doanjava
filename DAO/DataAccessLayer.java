package DAO;

import Model.KhuvucMD;
import Model.Mat_hangMD;
import Model.Model;
import Model.NhanvienMD;
import SQL.*;
import misc.InstanceCreator;

public class DataAccessLayer<T> {
    private SQLUser user;
    private Class<T> classType;
    public DataAccessLayer(SQLUser user,Class<T> classType){
        this.classType = classType;
        this.user = user;
    }
    public void add(T t){
        user.getDataQuery(((Model)t).toSQLString());
    }
    public void remove(T t){
        
    }
    public T getTable(){
        T t = null;
        try{
            InstanceCreator<T> creator = new InstanceCreator<>(classType);
            String sql = "SELECT * FROM ";
            DataSet ds = user.getDataQuery(sql);
            Object[] params = new Object[ds.getColumnCount()];
            for(int i = 0 ; i < ds.getColumnCount();i++){
                params[i] = ds.getRow(1)[i];
            }
            t = creator.createInstance(params);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return t;
    }
    public static void main(String[] args) {
        SQLUser user = new SQLUser("jdbc:mysql://localhost:3306/QuanLyKho","master", "123");
        DataAccessLayer<KhuvucMD> DAL = new DataAccessLayer<>(user,KhuvucMD.class);
        KhuvucMD kv = DAL.getTable();
        System.out.println(kv.getSucChua()+"   "+kv.getSucChua().getClass());
    }
}
