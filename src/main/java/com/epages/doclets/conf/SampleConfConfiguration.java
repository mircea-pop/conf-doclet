package com.epages.doclets.conf;

import com.sun.tools.doclets.formats.html.ConfigurationImpl;

public interface SampleConfConfiguration {

    public static final String OPTIONS_OUTPUT_FILE = "-outputFile";
    
    public void setOptions();

    public String getDestDir();

    public String getOutputFileName();

    public ConfigurationImpl getParentConfiguration();
}
