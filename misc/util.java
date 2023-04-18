package misc;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class util {
    public static String[] objToString(Object[] obj){
        String[] arrReturn = new String[obj.length];
        int i = 0;
        for(Object object : obj){
            arrReturn[i] = object.toString();
            i++;
        }
        return arrReturn;
    }
    //dung de lay bien hang so trong class thoi
    public static <T> String getClassVariable(Class<T> classType,String variableName) throws IllegalAccessException,NoSuchFieldException{
        Field field = classType.getDeclaredField(variableName);
        return (String)field.get(null);
    }
    public static<T> Object[] getColumn(T[][] t,int index){
        ArrayList<T> arr = new ArrayList<T>();
        for(int i = 0; i < t[index].length;i++){
            arr.add(t[index][i]);
        }
        return arr.toArray();
    }
}
