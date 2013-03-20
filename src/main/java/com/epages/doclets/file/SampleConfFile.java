package com.epages.doclets.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import com.epages.doclets.conf.SampleConfConfiguration;

public class SampleConfFile {

    private final List<SampleConfSection> sections = new LinkedList<>();
    private final SampleConfConfiguration conf;

    public SampleConfFile(SampleConfConfiguration conf) {
        this.conf = conf;
    }

    public void addSection(SampleConfSection section) {
        sections.add(section);
    }

    public void writeToDisk() {
        PrintWriter writer = null;
        try {   
            writer = new PrintWriter(conf.getOutputFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (SampleConfSection section : sections) {
            writer.write(section.asString());
            writer.println();
        }

        writer.close();
    }
}
