package com.epages.doclets.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epages.doclets.conf.SampleConfConfiguration;

public class SampleConfFile {

    private final List<SampleConfSection> sections = new LinkedList<>();
    private final SampleConfConfiguration conf;

    private static final Logger logger = LoggerFactory.getLogger(SampleConfFile.class);

    public SampleConfFile(SampleConfConfiguration conf) {
        this.conf = conf;
    }

    public void addSection(SampleConfSection section) {
        sections.add(section);
    }

    public void writeToDisk() {
        try (PrintWriter writer = new PrintWriter(new File(conf.getDestDir(), conf.getOutputFileName()))) {
            for (SampleConfSection section : sections) {
                writer.write(section.asString());
                writer.println();
            }
        } catch (IOException e) {
            logger.error("Couldn't create the {} file: {}", conf.getOutputFileName(), e.getMessage());
        }
    }
}