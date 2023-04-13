package misc;

import java.lang.reflect.Field;

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
    public static <T> String getClassVariable(Class<T> classType,String variableName) throws IllegalAccessException,NoSuchFieldException{
        Field field = classType.getDeclaredField(variableName);
        return (String)field.get(null);
    }
}
