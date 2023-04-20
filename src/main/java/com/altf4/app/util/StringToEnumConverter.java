package com.altf4.app.util;

public class StringToEnumConverter {

    public static String formatEnumValue(String value) {
        return value.trim().toUpperCase().replaceAll(" ", "_");
    }
}
