package com.muzammilpeer.utilitylayer.network;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.muzammilpeer.utilitylayer.logger.Log4a;
import com.muzammilpeer.utilitylayer.reflection.ReflectionUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by muzammilpeer on 15/07/2015.
 */
public class GsonUtil {

    private GsonUtil() {
        throw new AssertionError();
    }

    // shared method for json coversion
    public static JsonObject getJsonObjectFromObject(final Object obj) {
        JsonObject model = null;
        try {
            Gson gson = new Gson();
            String modelString = gson.toJson(obj);
            JsonParser parser = new JsonParser();
            model = (JsonObject) parser.parse(modelString);
        } catch (Exception e) {
            Log4a.printException(e);
        }

        return model;
    }

    public static <T> Object getObjectFromJsonObject(final Object data, Class<T> classofT) {

        try {
            if (data instanceof JsonObject) {
                JsonObject json = (JsonObject) data;
                Gson gson = new Gson();
                T obj = gson.fromJson(json, classofT);
                return obj;
            }
        } catch (Exception e) {
            Log4a.printException(e);
        }
        return null;
    }

    public static String getQueryStringURL(String url, Object model) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(url);
            if (model != null) {
                sb.append("?");
                Boolean flag = true;
                for (Method method : ReflectionUtil.findGettersSetters(
                        model.getClass(), true)) {
                    try {
                        String returnValue = (String) method.invoke(model, null);
                        if (returnValue != null) {
                            Log4a.e(method.getName(), returnValue);
                            if (flag) {
                                sb.append(method.getName() + "=" + returnValue);
                                flag = false;
                            } else {
                                sb.append("&" + method.getName() + "="
                                        + returnValue);
                            }
                        }
                    } catch (IllegalAccessException | IllegalArgumentException
                            | InvocationTargetException e) {
                        Log4a.printException(e);
                    }
                }
            }
            Log4a.d("Full URL = ", sb.toString());
        } catch (Exception e) {
            Log4a.printException(e);
        }
        return sb.toString();
    }
}
