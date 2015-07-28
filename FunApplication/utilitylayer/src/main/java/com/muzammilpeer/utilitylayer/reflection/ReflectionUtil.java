package com.muzammilpeer.utilitylayer.reflection;

import com.muzammilpeer.utilitylayer.logger.Log4a;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * Created by muzammilpeer on 15/07/2015.
 */
public class ReflectionUtil {
    // reflection utility methods

    public static String getToStringDescription(Class<?> cls, Object obj) {
        StringBuilder sb = new StringBuilder();
        sb.append(cls.getName());
        sb.append(": ");
        for (Field f : cls.getDeclaredFields()) {
            sb.append(f.getName());
            sb.append("=");
            try {
                sb.append(f.get(obj));
            } catch (IllegalAccessException | IllegalArgumentException e) {
                e.printStackTrace();
            }
            sb.append(", ");
        }
        return sb.toString();
    }

    public static Object instantiate(Class clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            Log4a.printException(e);
        } catch (IllegalAccessException e) {
            Log4a.printException(e);
        }
        return null;
    }

    public static void callMethod(String methodName, Class parametersClazz, Object parametersObject, Class clazzReference, Object clazzObject) {
        //String parameter
        Class[] parametersClass = new Class[1];
        parametersClass[0] = parametersClazz;

        //call the printItString method, pass a String param
        try {
            Method method = clazzReference.getDeclaredMethod(methodName, parametersClass);
            method.invoke(clazzObject, parametersObject);
        } catch (NoSuchMethodException | SecurityException e) {
            Log4a.printException(e);
        } catch (IllegalAccessException e) {
            Log4a.printException(e);
        } catch (IllegalArgumentException e) {
            Log4a.printException(e);
        } catch (InvocationTargetException e) {
            Log4a.printException(e);
        }

    }

    public static String getClassName(Class<?> cls) {
        Class<?> enclosingClass = cls.getEnclosingClass();
        if (enclosingClass != null) {
            return enclosingClass.getName();
        } else {
            return cls.getName();
        }
    }

    public static ArrayList<Method> findGettersSetters(Class<?> c,
                                                       Boolean isGetterRequired) {
        ArrayList<Method> list = new ArrayList<Method>();
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if (isGetterRequired) {
                if (isGetter(method))
                    list.add(method);
            } else {
                if (isSetter(method))
                    list.add(method);
            }
        }
        return list;
    }

    public static boolean isGetter(Method method) {
        if (Modifier.isPublic(method.getModifiers())
                && method.getParameterTypes().length == 0) {
            if (method.getName().matches("^get[A-Z].*")
                    && !method.getReturnType().equals(void.class))
                return true;
            if (method.getName().matches("^is[A-Z].*")
                    && method.getReturnType().equals(boolean.class))
                return true;
        }
        return false;
    }

    public static boolean isSetter(Method method) {
        return Modifier.isPublic(method.getModifiers())
                && method.getReturnType().equals(void.class)
                && method.getParameterTypes().length == 1
                && method.getName().matches("^set[A-Z].*");
    }
}
