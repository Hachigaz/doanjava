package misc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class InstanceCreator<T>{
    Class<T> classType;
    public InstanceCreator(Class<T> classType){
        this.classType = classType;
    }
    public T createInstance(Object... parameters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException{
        Class<?> paramsTypes[] = new Class[parameters.length];
        for(int i = 0 ; i < parameters.length;i++){
            paramsTypes[i] = parameters[i].getClass();
        }
        Constructor<T> constructor = classType.getConstructor(paramsTypes);
        T t = constructor.newInstance(parameters);
        return t;
    }
}