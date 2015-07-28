package com.muzammilpeer.utilitylayer.form;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by muzammilpeer on 15/07/2015.
 */
public class FormUtil {
    public static boolean testEmpty(String str) {
        if ((str == null) || str.matches("^\\s*$")) {
            return true;
        } else {
            if (str.equalsIgnoreCase("null")) {
                return true;
            } else return str.length() == 4 && str.contains("null");
        }
    }

    public static String getFormattedString(String str) {
        if (testEmpty(str)) {
            return "--";
        }

        return str;
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches()) {
            isValid = true;
        }

        return isValid;
    }

    public static boolean isValidUrl(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    public static boolean isNumeric(String str) {
        try {
            @SuppressWarnings("unused")
            int d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isNumericFloat(String str) {
        try {
            @SuppressWarnings("unused")
            float f = Float.parseFloat(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public String getRandomNumber(int digCount) {
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(digCount);
        for (int i = 0; i < digCount; i++)
            sb.append((char) ('0' + rnd.nextInt(10)));
        return sb.toString();

    }

}
