package com.epages.doclets.conf;

import com.sun.tools.doclets.formats.html.ConfigurationImpl;

public interface SampleConfConfiguration {

    public static final String OPTIONS_OUTPUT_FILE = "-outputFile";
    public static final String OPTIONS_EXCLUDE_PROP_WITH_UNDEFINED_DEFAULTS = "-excludePropWithUndefinedDefaults";
    public static final String OPTIONS_INCLUDE_SECTION_IN_KEY_NAME = "-includeSectionInKeyName";

    public void setOptions();

    public String getDestDir();

    public String getOutputFileName();

    public boolean excludePropWithUndefinedDefaults();
    
    public boolean includeSectionInKeyName();

    public ConfigurationImpl getParentConfiguration();
}
