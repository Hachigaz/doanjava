package DAL;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import DTO.*;
import DTO.Custom.DSChiTietKhuVucLoaiMD;
import SQL.*;
import misc.DataSet;
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
    public int addOne(T t){
        int rowsUpdated = 0;
        try{
            String sql = "INSERT INTO "+util.getClassVariable(classType,"fromStatement")+"\nVALUES";
            sql+=((Model)t).toSQLString();
            rowsUpdated = user.updateQuery(sql);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return rowsUpdated;
    }
    //xài update thì nhớ truyền khoá chính zo ko nó bị lỗi update(kho,"MaKho = K01")
    public int update(String key,String... statements){
        int rowsUpdated = 0;
        try{
            String sql = "UPDATE "+util.getClassVariable(classType,"fromStatement");
            sql+="\nSET ";
            for(int i = 0 ; i < statements.length;i++){
                String[] statement = statements[i].split("=");
                if(statement.length == 2){
                    sql += " ,";
                    sql +=  " "+statement[0].trim()+"='"+statement[1].trim()+"'";
                }
            }
            String whereStatement = processWhereStatement(statements);
            sql+= whereStatement;

            System.out.println(sql);
            rowsUpdated = user.updateQuery(sql);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return rowsUpdated;
    }
    //chua lam
    public int remove(String... statements){
        int rowsUpdated = 0;
        try{
            String sql = "DELETE FROM "+util.getClassVariable(classType,"fromStatement")+"\n";
            String whereStatement = processWhereStatement(statements);
            sql+= whereStatement;
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
            //class khoi tao doi tuong tu template
            InstanceCreator<T> creator = new InstanceCreator<>(classType);

            String sql = "SELECT " + util.getClassVariable(classType,"selectStatement");
            
            String whereStatement = processWhereStatement(statements);

            sql+= " FROM "+ util.getClassVariable(classType,"fromStatement") +whereStatement;

            //group by statement
            String groupByStatement = util.getClassVariable(classType,"groupByStatement");
            if(!groupByStatement.equals("")){
                sql+="\nGROUP BY ";
                sql+=groupByStatement;
            }
            DataSet ds = user.getDataQuery(sql);
            if(ds==null){
                throw new NullDALReturnException("Danh sách trả về rỗng");
            }
            this.returnedColumnLabel = ds.getColumnLabel();
            this.returnedColumnName = ds.getColumnName();
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
        catch(IllegalAccessException e){
            System.out.println(e.getMessage());
        }
        catch(NoSuchFieldException e){
            System.out.println(e.getMessage());
        }
        catch(NoSuchMethodException e){
            System.out.println(e.getMessage());
        } 
        catch (InvocationTargetException e) {
            System.out.println(e.getMessage());
        } 
        catch (InstantiationException e) {
            System.out.println(e.getMessage());
        }
        catch(NullDALReturnException e){

        }
        return list;
    }
    //tra ve gia tri dau tien tu table
    public T getFirst(String... statements){
            T t = null;
            try{
                //class khoi tao doi tuong tu template
                InstanceCreator<T> creator = new InstanceCreator<>(classType);

                String sql = "SELECT " + util.getClassVariable(classType,"selectStatement");
                
                String whereStatement = processWhereStatement(statements);

                sql+= " FROM "+ util.getClassVariable(classType,"fromStatement") +whereStatement;
                DataSet ds = user.getDataQuery(sql);
                if(ds==null){
                    throw new NullDALReturnException("Danh sách trả về rỗng");
                }
                
                this.returnedColumnLabel = ds.getColumnLabel();
                this.returnedColumnName = ds.getColumnName();
                
                //lấy danh sách tham số và khởi tạo đối tượng
                Object[] params = new Object[ds.getColumnCount()];
                for(int j = 0 ; j < ds.getColumnCount();j++){
                    params[j] = ds.getRow(0)[j];
                }
                t = creator.createInstance(params);
            }
            catch(IllegalAccessException e){
                System.out.println(e.getMessage());
            }
            catch(NoSuchFieldException e){
                System.out.println(e.getMessage());
            }
            catch(NoSuchMethodException e){
                System.out.println(e.getMessage());
            } 
            catch (InvocationTargetException e) {
                System.out.println(e.getMessage());
            } 
            catch (InstantiationException e) {
                System.out.println(e.getMessage());
            }
            catch(NullDALReturnException e){

            }
            return t;
    }

    private String processWhereStatement(String... statements){
        String whereStatement = " WHERE true";
        for(int i = 0 ; i < statements.length;i++){
            String[] statement = statements[i].split("=");
            if(statement.length == 2){
                whereStatement += " AND";
                whereStatement +=  " "+statement[0].trim()+"='"+statement[1].trim()+"'";
            }
        }
        return whereStatement;
    }
    public static void main(String[] args) {
        //SQLUser user = new SQLUser("jdbc:mysql://26.236.133.174:3306/QuanLyKho","master", "123");
        SQLUser user = new SQLUser("jdbc:mysql://localhost:3306/QuanLyKho","master", "123");
        // DataAccessLayer<Taikhoan_nhanvienMD> DAL = new DataAccessLayer<>(user,Taikhoan_nhanvienMD.class);
        // ArrayList<Taikhoan_nhanvienMD> kv = DAL.getTable("TenTaiKhoan =Admin","MatKhau= 123");
        
        
        // DataAccessLayer<NhanvienMD> NHANVIENDAL = new DataAccessLayer<>(user, NhanvienMD.class);

        // ArrayList<NhanvienMD> dsNV = NHANVIENDAL.getTable();
        


        // for(int i =0  ; i < kv.size();i++){
        //     System.out.println(kv.get(i).getTenTaiKhoan()+"   "+kv.get(i).getTenTaiKhoan().getClass());
        // }
        //DataAccessLayer<KhoMD> DAL2 = new DataAccessLayer<>(user,KhoMD.class);


        //DAL2.remove("MaKho = K03");
        //DAL2.update(new KhoMD("K01","Kho TBT","241 Tran Binh Trong"),"MaKho=K01","TenKho= Kho ADV");
        // DAL2.add(kho);
        
        // try{
        //     Class<Mat_hangMD> d = Mat_hangMD.class;
        //     Field field = d.getDeclaredField("fromStatement");
        //     System.out.println(field.get(null));
        // }
        // catch(Exception e){
        //     System.out.println(e.getMessage());
        // }
        //DataAccessLayer<CongtyMD> ctDAO = new DataAccessLayer<>(user, CongtyMD.class);
        //ArrayList<CongtyMD> dsCongTy = ctDAO.getTable("MaCty = Cty_VMX");
            
        //for(int i = 0 ; i < dsCongTy.size();i++){
        //    CongtyMD ct = dsCongTy.get(i);
        //    System.out.println(ct.getMaCty()+"   "+ct.getDiaChi());
        //}

        //ArrayList<CongtyMD> dsCT = new ArrayList<CongtyMD>();
        //dsCT.add(new CongtyMD("Cty_A", "A", "123 DA", "12355"));
       
        //ctDAO.add(dsCT);
        DataAccessLayer<DSChiTietKhuVucLoaiMD> dal = new DataAccessLayer<>(user, DSChiTietKhuVucLoaiMD.class);
        dal.getTable();
    }

    private String[] returnedColumnLabel= null;
    private String[] returnedColumnName = null;

    public String[] getReturnedColumnLabel() {
        return returnedColumnLabel;
    }
    public String[] getReturnedColumnName() {
        return returnedColumnName;
    }
}
