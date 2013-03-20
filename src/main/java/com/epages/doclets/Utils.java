package com.epages.doclets;

public class Utils {

    public static String getOption(String options[][], String optionName) {
        for (String option[] : options) {
            String name = option[0];
            if (!optionName.equals(name)) {
                continue;
            }
            String value = option.length > 1 ? option[1] : null;
            return value;
        }
        return null;
    }
}
