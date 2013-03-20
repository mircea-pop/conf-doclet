package com.epages.doclets.file;


public class SampleConfProperty {
    private final String comment;
    private final String key;
    private final String defaultValue;

    public SampleConfProperty(String key, String comment, String defaultValue) {
        this.comment = comment;
        this.key = key.replaceAll("\"", "");
        this.defaultValue = defaultValue.replaceAll("\"", "");
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

    public String asString() {
        StringBuilder builder = new StringBuilder();
        if (getComment() != null) {
            builder.append(";;");
            builder.append(getComment());
            builder.append("\n");
        }
        builder.append(";");
        builder.append(String.format("%s = %s\n", getKey(), getDefaultValue()));
        return builder.toString();
    }
}
