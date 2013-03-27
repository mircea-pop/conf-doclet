package com.epages.doclets.core;

import org.apache.commons.lang.StringUtils;

import com.epages.doclets.Utils;

public class SampleConfProperty {
    private final String comment;
    private final String key;
    private final String defaultValue;

    public SampleConfProperty(String key, String comment, String defaultValue) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException("NULL or empty property keys not allowed!");
        }
        this.key = key.replaceAll("\"", "");
        this.defaultValue = defaultValue;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public String getKey() {
        return key;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof SampleConfProperty)) {
            return false;
        }
        SampleConfProperty other = (SampleConfProperty) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

    public boolean hasComment() {
        return !StringUtils.isEmpty(getComment());
    }

    public boolean hasKey() {
        return !StringUtils.isEmpty(getKey());
    }

    public String asString() {
        StringBuilder builder = new StringBuilder();
        if (hasComment()) {
            builder.append(Utils.ensureComment(String.format("%s\n", getComment())));
        }
        if (hasKey()) {
            builder.append(String.format(";%s = %s\n", getKey(), getDefaultValue()));
        }
        return builder.toString();
    }
}
