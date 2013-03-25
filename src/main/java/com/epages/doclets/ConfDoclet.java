package com.epages.doclets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epages.doclets.conf.SampleConfConfiguration;
import com.epages.doclets.conf.SampleConfConfigurationImpl;
import com.sun.javadoc.RootDoc;
import com.sun.tools.doclets.formats.html.ConfigurationImpl;
import com.sun.tools.doclets.formats.html.HtmlDoclet;

public class ConfDoclet {
    private final HtmlDoclet htmlDoclet = new HtmlDoclet();
    private final RootDoc root;
    private SampleConfConfiguration conf;

    private static final Logger logger = LoggerFactory.getLogger(ConfDoclet.class);
    
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
        logger.info("ConfDoclet.start()");
        SampleConf sampleConf = new SampleConf(conf, root);
        sampleConf.start();
    }

    public static int optionLength(final String option) {
        if (SampleConfConfiguration.OPTIONS_OUTPUT_FILE.equals(option)) {
            return 2;
        }
        return HtmlDoclet.optionLength(option);
    }

    private SampleConfConfiguration makeConfiguration(ConfigurationImpl configuration) {
        return new SampleConfConfigurationImpl(configuration);
    }
}
