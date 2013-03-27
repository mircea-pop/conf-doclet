package com.epages.doclets;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;

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
    
    /**
     * @return true if optionName exists in one of the options.
     */
    public static boolean hasOption(String options[][], String optionName) {
      for (String option[] : options) {
        String name = option[0];
        if (!optionName.equals(name)) {
          continue;
        }
        return true;
      }
      return false;
    }
    
    /**
     * Make sure the comment lines start with ";;", in order to be ignored by
     * property files engines.
     */
    public static String ensureComment(String formatedComment) {
        StringBuilder retval = new StringBuilder();
        Scanner scanner = new Scanner(formatedComment);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!StringUtils.isEmpty(line.trim()) && !line.trim().startsWith(";;")) {
                line = ";;" + line;
            }
            retval.append(line);
            retval.append("\n");
        }
        scanner.close();

        return retval.length() == 0 ? formatedComment : retval.toString();
    }
}
