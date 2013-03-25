package com.epages.doclets;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static String getOption(String options[][], String optionName) {
        List<String> result = getOptions(options, optionName);
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    public static List<String> getOptions(String options[][], String optionName) {
        List<String> result = new ArrayList<String>();
        for (String option[] : options) {
            String name = option[0];
            if (!optionName.equals(name)) {
                continue;
            }
            String value = option.length > 1 ? option[1] : null;
            result.add(value);
        }
        return result;
    }
}
