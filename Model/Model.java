package Model;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public abstract class Model {
    public abstract String getSelectStatement();
    public abstract String getFromStatement();
    public abstract String toSQLString();
    public Object[] makeArray(String... paramNames){

        ArrayList<Object> objects = null;
        try{
            Field[] fields = this.getClass().getDeclaredFields();
            objects = new ArrayList<Object>();
            for(int i = 0; i < fields.length;i++){
                Field field = fields[i];
                if(paramNames.length>0){
                    for(String paramName : paramNames){
                        if (field.getName().equals(paramName)) {
                            field.setAccessible(true);
                            objects.add(field.get(this));
                        }
                    }
                }
                else{
                    if (Modifier.isPrivate(field.getModifiers())) {
                        field.setAccessible(true);
                        objects.add(field.get(this));
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return objects.toArray();
    }
    
    public static<T> Object[][] to2DArray(ArrayList<T> models,String... paramNames){
        Object[][] returnObjects = new Object[models.size()][];
        for(int i =0; i<models.size();i++){
            returnObjects[i] =  ((Model)models.get(i)).makeArray(paramNames);
        }
        return returnObjects;
    }
}