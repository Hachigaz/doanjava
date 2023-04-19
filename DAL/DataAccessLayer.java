package DAL;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import Model.*;
import Model.Model;
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
    public int update(T tUpdated,String... statements){
        int rowsUpdated = 0;
        try{
            String sql = "UPDATE "+util.getClassVariable(classType,"fromStatement");
            sql+="\nSET ";
            Field[] fields = classType.getDeclaredFields();
            int count = 0;
            for(Field field : fields){
                if(Modifier.isPrivate(field.getModifiers())){
                    field.setAccessible(true);
                    Object value = field.get(tUpdated);
                    field.setAccessible(false);
                    String sValue = "";
                    if(value.getClass()==String.class){
                        sValue += "'"+value.toString()+"'";
                    }
                    else{
                        sValue +=value.toString();
                    }
                    sql+=field.getName()+"="+sValue;
                    if(count+1<fields.length){
                        sql+=",";
                    }
                    else{
                        sql+="\n";
                    }
                }
                count++;
            }
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

            DataSet ds = user.getDataQuery(sql);
            if(ds==null){
                throw new Exception("Danh sách trả về rỗng");
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
        catch(Exception e){
            System.out.println(e.getClass()+":"+e.getMessage());
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
                    throw new Exception("Danh sách trả về rỗng");
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
            catch(Exception e){
                System.out.println(e.getMessage());
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
        DataAccessLayer<KhoMD> DAL2 = new DataAccessLayer<>(user,KhoMD.class);


        Integer a = 1;
        System.out.println(a.getClass());
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
