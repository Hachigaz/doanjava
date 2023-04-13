package Model;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public abstract class Model {
    public abstract String getFromStatement();
    public abstract String toSQLString();
    public ArrayList<Object> makeArray(){
        ArrayList<Object> objects = null;
        try{
            Field[] fields = this.getClass().getDeclaredFields();
            objects = new ArrayList<Object>();
            for(int i = 0; i < fields.length;i++){
                Field field = fields[i];
                if (Modifier.isPrivate(field.getModifiers())) {
                    field.setAccessible(true);
                    objects.add(field.get(this));
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return objects;
    }
    public static void main(String[] args) {
        KhoMD kho = new KhoMD("asd", "123", "ok");
        ArrayList<Object> objects = kho.makeArray();
        for(Object object : objects){
            System.out.println((String)object);
        }
    }
}