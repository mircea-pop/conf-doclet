package com.epages.doclets;

import com.epages.doclets.conf.SampleConfConfiguration;
import com.epages.doclets.file.SampleConfFile;
import com.epages.doclets.file.SampleConfSection;
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
        SampleConfFile confFile = new SampleConfFile(conf);
        ClassDoc[] classes = root.classes();

        for (ClassDoc classDoc : classes) {
            if (classDoc.tags(ConfigurationTaglets.SECTION.getTag()).length > 0) {
                SampleConfSection section = new SampleConfSection(classDoc);
                section.process();
                confFile.addSection(section);
            }
        }

        confFile.writeToDisk();
    }
}
