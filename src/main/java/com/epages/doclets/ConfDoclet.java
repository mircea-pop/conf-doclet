package com.epages.doclets;

import com.epages.doclets.conf.SampleConfConfiguration;
import com.epages.doclets.conf.SampleConfConfigurationImpl;
import com.sun.javadoc.RootDoc;
import com.sun.tools.doclets.formats.html.ConfigurationImpl;
import com.sun.tools.doclets.formats.html.HtmlDoclet;

public class ConfDoclet {
    private final HtmlDoclet htmlDoclet = new HtmlDoclet();
    private final RootDoc root;
    private SampleConfConfiguration conf;

    public ConfDoclet(RootDoc root) {
        this.root = root;
        htmlDoclet.configuration.root = root;
        this.conf = makeConfiguration(htmlDoclet.configuration);
        this.conf.setOptions();
    }

    public static boolean start(final RootDoc root) {
        new ConfDoclet(root).start();
        return true;
    }

    public void start() {
        root.printNotice(this.getClass().getName() + ".start()");
        SampleConf sampleConf = new SampleConf(conf, root);
        sampleConf.start();
    }

    public static int optionLength(final String option) {
        if (SampleConfConfiguration.OPTIONS_OUTPUT_FILE.equals(option)) {
            return 2;
        }
        if (SampleConfConfiguration.OPTIONS_EXCLUDE_PROP_WITH_UNDEFINED_DEFAULTS.equals(option)
                || SampleConfConfiguration.OPTIONS_INCLUDE_SECTION_IN_KEY_NAME.equals(option)) {
            return 1;
        }
        return HtmlDoclet.optionLength(option);
    }

    private SampleConfConfiguration makeConfiguration(ConfigurationImpl configuration) {
        return new SampleConfConfigurationImpl(configuration);
    }
}
