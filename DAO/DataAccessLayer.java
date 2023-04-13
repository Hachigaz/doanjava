package DAO;

import java.util.ArrayList;
import java.util.List;

import Model.*;
import Model.Model;
import SQL.*;
import misc.InstanceCreator;
import misc.util;

public class DataAccessLayer<T> {
    private SQLUser user;
    private Class<T> classType;
    public DataAccessLayer(SQLUser user,Class<T> classType){
        this.classType = classType;
        this.user = user;
    }
    public int add(List<T> t){
        int rowsUpdated = 0;
        try{
            String sql = "INSERT INTO "+util.getClassVariable(classType,"fromStatement")+"\nVALUES";
            for(int i = 0 ;i < t.size();i++){
                sql+=((Model)t.get(i)).toSQLString();
                if(i+1<t.size()){
                    sql+=',';
                }
            }
            //System.out.println(sql);
            rowsUpdated = user.updateQuery(sql);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return rowsUpdated;
    }
    //chua lam
    public int update(List<T> t){
        int rowsUpdated = 0;
        try{
            String sql = "UPDATE "+util.getClassVariable(classType,"fromStatement")+"\nVALUES";
            for(int i = 0 ;i < t.size();i++){
                sql+=((Model)t.get(i)).toSQLString();
                if(i+1<t.size()){
                    sql+=',';
                }
            }
            //System.out.println(sql);
            rowsUpdated = user.updateQuery(sql);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return rowsUpdated;
    }
    //chua lam
    public int remove(List<T> t){
        int rowsUpdated = 0;
        try{
            String sql = "INSERT INTO "+util.getClassVariable(classType,"fromStatement")+"\nVALUES";
            for(int i = 0 ;i < t.size();i++){
                sql+=((Model)t.get(i)).toSQLString();
                if(i+1<t.size()){
                    sql+=',';
                }
            }
            //System.out.println(sql);
            rowsUpdated = user.updateQuery(sql);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return rowsUpdated;
    }
    public ArrayList<T> getTable(String... statements){
        ArrayList<T> list = null;
        try{
            InstanceCreator<T> creator = new InstanceCreator<>(classType);
            String whereStatement = " WHERE true";
            for(int i = 0 ; i < statements.length;i++){
                String[] statement = statements[i].split("=");
                if(statement.length == 2){
                    whereStatement += " AND";
                    whereStatement +=  " "+statement[0].trim()+"='"+statement[1].trim()+"'";
                }
            }

            String sql = "SELECT * FROM "+ util.getClassVariable(classType,"fromStatement") +whereStatement;

            System.out.println(sql);

            DataSet ds = user.getDataQuery(sql);
            list = new ArrayList<T>();
            Object[] params = new Object[ds.getColumnCount()];
            for(int i = 0 ; i < ds.getRowCount();i++){
                for(int j = 0 ; j < ds.getColumnCount();j++){
                    params[j] = ds.getRow(i)[j];
                }
                T t = creator.createInstance(params);
                list.add(t);
            }
        }
        catch(Exception e){
            System.out.println(e.getClass()+":"+e.getMessage());
        }
        return list;
    }
    public static void main(String[] args) {
        SQLUser user = new SQLUser("jdbc:mysql://localhost:3306/QuanLyKho","master", "123");
        DataAccessLayer<Taikhoan_nhanvienMD> DAL = new DataAccessLayer<>(user,Taikhoan_nhanvienMD.class);
        ArrayList<Taikhoan_nhanvienMD> kv = DAL.getTable("TenTaiKhoan =Admin","MatKhau= 123");
        for(int i =0  ; i < kv.size();i++){
            System.out.println(kv.get(i).getTenTaiKhoan()+"   "+kv.get(i).getTenTaiKhoan().getClass());
        }
        // DataAccessLayer<KhoMD> DAL2 = new DataAccessLayer<>(user,KhoMD.class);

        // ArrayList<KhoMD> kho = new ArrayList<KhoMD>();
        // kho.add(new KhoMD("K03","Kho NVC","293 Nguyễn Văn Cừ"));
        // kho.add(new KhoMD("K04", "Kho LHP", "784 Lê Hồng Phong"));
        //DAL2.add(kho);
        
        // try{
        //     Class<Mat_hangMD> d = Mat_hangMD.class;
        //     Field field = d.getDeclaredField("fromStatement");
        //     System.out.println(field.get(null));
        // }
        // catch(Exception e){
        //     System.out.println(e.getMessage());
        // }

    }
}
