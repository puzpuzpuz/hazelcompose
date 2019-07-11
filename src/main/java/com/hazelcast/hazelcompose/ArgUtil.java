package com.hazelcast.hazelcompose;

public final class ArgUtil {

    private ArgUtil() {
    }

    public static int parseInt(String val, String defVal) {
        try {
            return Integer.valueOf(val);
        } catch (Exception e) {
            return Integer.valueOf(defVal);
        }
    }

    public static boolean parseBoolean(String val, String defVal) {
        if (val != null && val.isEmpty()) {
            return Boolean.valueOf(val);
        }
        return Boolean.valueOf(defVal);
    }

}
