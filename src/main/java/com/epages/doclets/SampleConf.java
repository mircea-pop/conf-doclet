package com.epages.doclets;

import com.epages.doclets.conf.Logger;
import com.epages.doclets.conf.SampleConfConfiguration;
import com.epages.doclets.core.SampleConfFile;
import com.epages.doclets.core.SampleConfSection;
import com.epages.doclets.taglets.ConfigurationTaglets;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;

public class SampleConf {

    private final SampleConfConfiguration conf;
    private final RootDoc root;

    public SampleConf(SampleConfConfiguration conf, RootDoc root) {
        this.conf = conf;
        this.root = root;
    }

    public void start() {
        Logger logger = new Logger(root);
        SampleConfFile confFile = new SampleConfFile(conf, logger);
        ClassDoc[] classes = root.classes();

        for (ClassDoc classDoc : classes) {
            if (classDoc.tags(ConfigurationTaglets.SECTION.getTag()).length > 0) {
                SampleConfSection section = new SampleConfSection(classDoc, conf, logger);
                section.process();
                confFile.addSection(section);
            }
        }

        confFile.writeToDisk();
    }
}
