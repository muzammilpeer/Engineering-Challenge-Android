package com.muzammilpeer.utilitylayer.enums;

import android.annotation.SuppressLint;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by muzammilpeer on 15/07/2015.
 */
@SuppressLint("UseSparseArrays")
public enum NetworkEnums {

    BASE_REQUEST(0, "http://a.A.com/WebAPI/", "Base server url request"),
    SUBMIT_RECORD_PAYMENT(1, "api/Service/SetPaymentDetails", "Submit payment request"),
    RECORD_PAYMENT(2, "api/Service/RecordPaymentDetails", "Record payment request"); // semicolon needed when fields / methods follow

    /**
     * A mapping between the integer code and its corresponding Status to
     * facilitate lookup by code.
     */
    private static Map<Integer, NetworkEnums> codeToStatusMapping;
    private int code;
    private String relativeUrl;
    private String description;

    NetworkEnums(int code, String relativeUrl, String description) {
        this.code = code;
        this.relativeUrl = relativeUrl;
        this.description = description;
    }

    public static NetworkEnums getStatus(int i) {
        if (codeToStatusMapping == null) {
            initMapping();
        }
        return codeToStatusMapping.get(i);
    }

    @SuppressLint("UseSparseArrays")
    private static void initMapping() {
        codeToStatusMapping = new HashMap<Integer, NetworkEnums>();
        for (NetworkEnums s : values()) {
            codeToStatusMapping.put(s.code, s);
        }
    }

    public int getCode() {
        return code;
    }

    public String getRelativeUrl() {
        return relativeUrl;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("NetworkEnums");
        sb.append("{code=").append(code);
        sb.append(", relativeUrl='").append(relativeUrl).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
