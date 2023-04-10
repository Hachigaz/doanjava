package misc;

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
}
