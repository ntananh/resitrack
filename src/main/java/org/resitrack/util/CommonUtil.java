package org.resitrack.util;

public class CommonUtil {

    public static final int INITIAL_ID_VALUE = 1;
    public static boolean isNullOrBlank(String input) {
        return  input == null || input.trim().isEmpty();
    }
}
