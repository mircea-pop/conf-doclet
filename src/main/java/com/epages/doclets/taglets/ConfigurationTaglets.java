package com.epages.doclets.taglets;

public enum ConfigurationTaglets {

    SECTION("Section"), PROPERTY("Property");

    private final String tag;

    private ConfigurationTaglets(String tag) {
        this.tag = "Configuration" + tag;
    }

    public String getTag() {
        return tag;
    }
}
