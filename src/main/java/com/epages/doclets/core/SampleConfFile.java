package com.epages.doclets.core;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import com.epages.doclets.conf.Logger;
import com.epages.doclets.conf.SampleConfConfiguration;

public class SampleConfFile {

    private final List<SampleConfSection> sections = new LinkedList<>();
    private final SampleConfConfiguration conf;
    private Logger logger;

    public SampleConfFile(SampleConfConfiguration conf, Logger logger) {
        this.conf = conf;
        this.logger = logger;
    }

    public void addSection(SampleConfSection section) {
        sections.add(section);
    }

    public void writeToDisk() {
        try (PrintWriter writer = new PrintWriter(new File(conf.getDestDir(), conf.getOutputFileName()))) {
            for (SampleConfSection section : sections) {
                writer.write(section.asString());
                writer.write(";----------------------------------------------------------------------------");
                writer.println();
                writer.println();
            }
        } catch (IOException e) {
            logger.error("Couldn't create the %s file: %s", conf.getOutputFileName(), e.getMessage());
        }
    }
}