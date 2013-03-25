package com.epages.doclets.file;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epages.doclets.taglets.ConfigurationTaglets;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.Tag;

public class SampleConfSection {
    private final List<SampleConfProperty> properties = new ArrayList<>();
    private String sectionName;
    private final ClassDoc classDoc;
    private static final Logger logger = LoggerFactory.getLogger(SampleConfSection.class);

    public SampleConfSection(ClassDoc classDoc) {
        logger.debug("Configuration Class {} found", classDoc.name());
        this.classDoc = classDoc;
    }

    public void process() {
        processType();
        processFields();
    }

    private void processType() {
        Tag[] tags = classDoc.tags(ConfigurationTaglets.SECTION.getTag());
        if (tags != null && tags.length > 0) {
            Tag tag = tags[tags.length - 1];
            sectionName = tag.text();
        }
    }

    private void processFields() {
        FieldDoc[] fields = classDoc.fields();
        logger.debug("Configuration Class {} has {} fields", classDoc.name(), fields.length);

        List<FieldDoc> keys = new ArrayList<>();
        List<FieldDoc> defaults = new ArrayList<>();

        for (FieldDoc fieldDoc : fields) {
            if (fieldDoc.name().toUpperCase().endsWith("DEFAULT")) {
                defaults.add(fieldDoc);
            } else {
                keys.add(fieldDoc);
            }
        }

        checkIntegrity(keys, defaults);

        for (FieldDoc fieldDoc : keys) {
            properties.add(new SampleConfProperty(getName() + "." + fieldDoc.constantValueExpression(), fieldDoc.commentText(),
                    getDefaultValue(defaults, fieldDoc.name())));
        }
    }

    private void checkIntegrity(List<FieldDoc> keys, List<FieldDoc> defaults) {
        if (keys.size() != defaults.size()) {
            logger.warn("Not all configuration properties have default values in section {}![properties={}; default_values={}]", getName(),
                    keys.size(), defaults.size());
        }

        for (FieldDoc key : keys) {
            String property = key.name();
            String defaultKey = getDefaultKey(defaults, key.name());
            logger.debug("property={}; defaultKey={}", property, defaultKey);
            if (StringUtils.isEmpty(defaultKey)) {
                logger.warn("No default value found for property {} in section {}!", property, getName());
            }
        }

    }

    private String getDefaultKey(List<FieldDoc> defaults, String name) {
        for (FieldDoc fieldDoc : defaults) {
            if (fieldDoc.name().startsWith(name)) {
                return fieldDoc.name();
            }
        }
        return "";
    }

    private String getDefaultValue(List<FieldDoc> defaults, String name) {
        for (FieldDoc fieldDoc : defaults) {
            if (fieldDoc.name().startsWith(name)) {
                return fieldDoc.constantValueExpression();
            }
        }
        return "";
    }

    public String getName() {
        return sectionName;
    }

    public String asString() {
        StringBuilder builder = new StringBuilder();

        if (StringUtils.isNotEmpty(classDoc.commentText())) {
            builder.append(String.format(";;%s\n", classDoc.commentText()));
        }
        builder.append(String.format("[%s]\n", sectionName));

        for (SampleConfProperty property : properties) {
            builder.append(property.asString());
        }

        return builder.toString();
    }
}
