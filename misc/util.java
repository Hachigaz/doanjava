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
            arr.add(t[i][index]);
        }
        return arr.toArray();
    }
    public static Object[][] flip2dArray(Object[][] arr) {
        Object[][] retObj = new Object[arr[0].length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                retObj[j][i]=arr[i][j];
                
            }
        }
        return retObj;
    }
    public static String[] themStringVaoArray (String string,String... stringArr){
        String[] newStringArr = new String[stringArr.length+1];
        for(int i = 0 ; i < stringArr.length;i++){
            newStringArr[i] = stringArr[i];
        }
        newStringArr[stringArr.length]=string;
        return newStringArr;
    }
}
