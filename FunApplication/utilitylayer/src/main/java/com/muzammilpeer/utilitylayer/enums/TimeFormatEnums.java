package com.muzammilpeer.utilitylayer.enums;

import android.annotation.SuppressLint;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by muzammilpeer on 15/07/2015.
 */
@SuppressLint("UseSparseArrays")
public enum TimeFormatEnums {
    FORMAT_YYYY_MM_DD(0, "hh:mm:ss"),
    FORMAT_YYYY_DD_MM(1, "yyyy-dd-MM"),
    FORMAT_YY_MM_DD(2, "yy-MM-dd"),
    FORMAT_YY_DD_MM(3, "yy-dd-MM"),
    FORMAT_YYYY_MM_DD_SLASH(4, "yyyy/MM/dd"),
    FORMAT_YYYY_DD_MM_SLASH(5, "yyyy/dd/MM"),
    FORMAT_DD_MMM_YYY_SPACE(6, "dd MMM yyyy"),
    FORMAT_DD_MMM_SPACE(7, "dd MMM"),
    FORMAT_DD(8, "dd"),
    FORMAT_MM(9, "MM"),
    FORMAT_MMMM(10, "MMMM"),
    FORMAT_YYYY(11, "yyyy"),
    FORMAT_EEE_DD_MM_YYYY_SLASH(12, "EEE, dd/MM/yyyy"),
    FORMAT_DD_MM_YYYY_SLASH(13, "dd/MM/yyyy"),
    FORMAT_MMM_DD_YYYY_SPACE(14, "MMM dd,yyyy"); // semicolon needed when fields / methods follow

    /**
     * A mapping between the integer code and its corresponding Status to
     * facilitate lookup by code.
     */
    private static Map<Integer, TimeFormatEnums> codeToStatusMapping;
    private int code;
    private String timeFormat;

    TimeFormatEnums(int code, String timeformat) {
        this.code = code;
        this.timeFormat = timeformat;
    }

    public static TimeFormatEnums getStatus(int i) {
        if (codeToStatusMapping == null) {
            initMapping();
        }
        return codeToStatusMapping.get(i);
    }

    @SuppressLint("UseSparseArrays")
    private static void initMapping() {
        codeToStatusMapping = new HashMap<Integer, TimeFormatEnums>();
        for (TimeFormatEnums s : values()) {
            codeToStatusMapping.put(s.code, s);
        }
    }

    public int getCode() {
        return code;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TimeFormatEnums");
        sb.append("{code=").append(code);
        sb.append(", timeFormat='").append(timeFormat).append('\'');
        sb.append('}');
        return sb.toString();
    }
}