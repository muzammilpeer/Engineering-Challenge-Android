package com.muzammilpeer.utilitylayer.file;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.muzammilpeer.utilitylayer.logger.Log4a;

import java.util.Set;

/**
 * Created by muzammilpeer on 15/07/2015.
 */
public class PreferenceUtil {

    private PreferenceUtil() {
        throw new AssertionError();
    }

    public static void saveToPrefs(Context context, String key, String value) {
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            final SharedPreferences.Editor editor = prefs.edit();
            editor.putString(key, value);
            editor.commit();
        } catch (Exception e) {
            Log4a.printException(e);
        }
    }

    public static void saveToPrefs(Context context, String key, boolean value) {
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            final SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(key, value);
            editor.commit();
        } catch (Exception e) {
            Log4a.printException(e);
        }
    }

    public static void saveToPrefs(Context context, String key, Set value) {
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            final SharedPreferences.Editor editor = prefs.edit();
            editor.putStringSet(key, value);
            editor.commit();
        } catch (Exception e) {
            Log4a.printException(e);
        }
    }


    public static String getFromPrefs(Context context, String key, String defaultValue) {
        try {
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
            defaultValue = sharedPrefs.getString(key, defaultValue);
        } catch (Exception e) {
            Log4a.printException(e);
        }
        return defaultValue;
    }

    public static boolean getFromPrefs(Context context, String key, boolean defaultValue) {
        try {
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
            defaultValue = sharedPrefs.getBoolean(key, defaultValue);
        } catch (Exception e) {
            Log4a.printException(e);
        }
        return defaultValue;
    }

    public static void removeFromPrefs(Context context, String key) {
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove(key);
            editor.commit();
        } catch (Exception e) {
            Log4a.printException(e);
        }
    }

    public static void clearAllPrefs(Context context) {
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.commit();
        } catch (Exception e) {
            Log4a.printException(e);
        }
    }
}
