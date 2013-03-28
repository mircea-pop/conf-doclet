package com.epages.doclets.conf;

import com.sun.javadoc.RootDoc;

public class Logger {

    private final RootDoc root;
    private static final String LOGGER_PREFIX = "ConfDoclet - ";

    public Logger(RootDoc root) {
        this.root = root;
    }
    
    public void notice(String message, Object... args)
    {
        root.printNotice(LOGGER_PREFIX + String.format(message, args));
    }
    
    public void error(String message, Object... args)
    {
        root.printError(LOGGER_PREFIX + String.format(message, args));
    }
    
    public void warn(String message, Object... args)
    {
        root.printWarning(LOGGER_PREFIX + String.format(message, args));
    }
}
