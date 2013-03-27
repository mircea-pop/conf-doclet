package com.epages.doclets.conf;

import org.apache.commons.lang.StringUtils;

import com.epages.doclets.Utils;
import com.sun.tools.doclets.formats.html.ConfigurationImpl;

public class SampleConfConfigurationImpl implements SampleConfConfiguration {

    private static final String OPTIONS_OUTPUT_FILE_DEFAULT = "sample.conf";
    private String outputFileName = OPTIONS_OUTPUT_FILE_DEFAULT;
    private String destDir = "";
    private boolean excludePropWithUndefinedDefaults = false;
    private boolean includeSectionInKeyName = false;

    private ConfigurationImpl parentConfiguration;

    public SampleConfConfigurationImpl(ConfigurationImpl configuration) {
        this.parentConfiguration = configuration;
    }

    public void setOptions() {
        parentConfiguration.setOptions();
        String configuredOutputFile = Utils.getOption(parentConfiguration.root.options(), OPTIONS_OUTPUT_FILE);
        this.outputFileName = StringUtils.isEmpty(configuredOutputFile) ? OPTIONS_OUTPUT_FILE_DEFAULT : configuredOutputFile;

        this.excludePropWithUndefinedDefaults = Utils.hasOption(parentConfiguration.root.options(),
                OPTIONS_EXCLUDE_PROP_WITH_UNDEFINED_DEFAULTS);
        
        this.includeSectionInKeyName = Utils.hasOption(parentConfiguration.root.options(),
                OPTIONS_INCLUDE_SECTION_IN_KEY_NAME);

        this.destDir = parentConfiguration.destDirName;
    }

    public String getDestDir() {
        return destDir;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public ConfigurationImpl getParentConfiguration() {
        return parentConfiguration;
    }

    public boolean excludePropWithUndefinedDefaults() {
        return excludePropWithUndefinedDefaults;
    }

    @Override
    public boolean includeSectionInKeyName() {
        return includeSectionInKeyName;
    }
}