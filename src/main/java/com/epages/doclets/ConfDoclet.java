package com.epages.doclets;

import java.util.logging.Logger;

import com.epages.doclets.conf.SampleConfConfiguration;
import com.sun.javadoc.RootDoc;
import com.sun.tools.doclets.formats.html.ConfigurationImpl;
import com.sun.tools.doclets.formats.html.HtmlDoclet;

public class ConfDoclet {
    private final HtmlDoclet htmlDoclet = new HtmlDoclet();
    private final RootDoc root;
    private final SampleConfConfiguration conf;
    private static final Logger logger = Logger.getLogger(ConfDoclet.class.getName());

    public ConfDoclet(RootDoc root) {
        this.root = root;
        htmlDoclet.configuration.root = root;
        this.conf = makeConfiguration(htmlDoclet.configuration);
    }

    public static boolean start(final RootDoc root) {
        new ConfDoclet(root).start();
        return true;
    }

    public static int optionLength(final String option) {
        return HtmlDoclet.optionLength(option);
    }

    private SampleConfConfiguration makeConfiguration(ConfigurationImpl configuration) {
        return new SampleConfConfiguration(configuration);
    }

    public void start() {
        logger.info("EPJDoclet.start()");
        SampleConf sampleConf = new SampleConf(conf, root);
        sampleConf.start();
    }
}
