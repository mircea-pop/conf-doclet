package com.epages.doclets.taglets;

import java.util.Map;

import com.sun.javadoc.Tag;
import com.sun.tools.doclets.Taglet;

public class ConfigSectionTaglet implements Taglet {
    public static final String NAME = "ConfigSection";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean inConstructor() {
        return false;
    }

    @Override
    public boolean inField() {
        return false;
    }

    @Override
    public boolean inMethod() {
        return false;
    }

    @Override
    public boolean inOverview() {
        return false;
    }

    @Override
    public boolean inPackage() {
        return false;
    }

    @Override
    public boolean inType() {
        return true;
    }

    @Override
    public boolean isInlineTag() {
        return false;
    }

    @Override
    public String toString(Tag tag) {
        return tag.text();//(tag.text() == null || tag.text().length() == 0) ? null : getName(tag);
    }

    @Override
    public String toString(Tag[] tags) {
        StringBuilder builder = new StringBuilder();
        for (Tag tag : tags) {
            builder.append(tag.toString());
        }
        return builder.toString();
    }

    private String getName(Tag tag) {
        String text = tag.text().trim();
        int ws = text.indexOf(' ');
        if (ws > -1)
            return text.substring(0, ws);
        return text;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void register(Map tagletMap) {
        tagletMap.remove(NAME);
        tagletMap.put(NAME, new ConfigSectionTaglet());
    }
}
