package com.epages.doclets.conf;

import java.io.File;

import org.apache.commons.lang.StringUtils;

import com.epages.doclets.Utils;
import com.sun.tools.doclets.formats.html.ConfigurationImpl;

public class SampleConfConfiguration {

    private static final String OPTIONS_OUTPUT_FILE = "title";
    private static final String OPTIONS_OUTPUT_FILE_DEFAULT = "sample.conf";
    private final ConfigurationImpl configuration;

    public SampleConfConfiguration(ConfigurationImpl configuration) {
        this.configuration = configuration;
    }
    
    public File getDestDir()
    {
        return new File(configuration.docFileDestDirName);
    }
    
    public String getOutputFile()
    {
        String outputFile = Utils.getOption(configuration.root.options(), OPTIONS_OUTPUT_FILE);
        return StringUtils.isEmpty(outputFile) ? OPTIONS_OUTPUT_FILE_DEFAULT : outputFile;
    }

    public ConfigurationImpl getParentConfiguration() {
        return configuration;
    }
}