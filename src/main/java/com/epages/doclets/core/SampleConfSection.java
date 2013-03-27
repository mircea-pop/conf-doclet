package com.epages.doclets.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epages.doclets.Utils;
import com.epages.doclets.conf.SampleConfConfiguration;
import com.epages.doclets.taglets.ConfigurationTaglets;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.Tag;

public class SampleConfSection {
    private final List<SampleConfProperty> properties = new ArrayList<>();
    private String sectionName;
    private final ClassDoc classDoc;
    private SampleConfConfiguration conf;
    private static final Logger logger = LoggerFactory.getLogger(SampleConfSection.class);

    public SampleConfSection(ClassDoc classDoc, SampleConfConfiguration conf) {
        logger.debug("Configuration Class {} found", classDoc.name());
        this.classDoc = classDoc;
        this.conf = conf;
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
        List<PropertyField> fields = convertToPropertyFields(classDoc.fields());

        logger.debug("Configuration Class {} has {} eligible fields", classDoc.name(), fields.size());

        List<PropertyField> keys = new ArrayList<>();
        List<PropertyField> defaults = new ArrayList<>();

        for (PropertyField fieldDoc : fields) {
            if (fieldDoc.isProperty()) {
                logger.debug("{}:key: Adding {}", getName(), fieldDoc);
                keys.add(fieldDoc);
            } else if (fieldDoc.isDefault()) {
                logger.debug("{}:default: Adding {}", getName(), fieldDoc);
                defaults.add(fieldDoc);
            }
        }

        checkIntegrity(keys, defaults);
        
        for (PropertyField fieldDoc : keys) {
            String defaultValue = getDefaultValue(defaults, fieldDoc.getName());
            if (StringUtils.isEmpty(defaultValue) && conf.excludePropWithUndefinedDefaults()) {
                continue;
            }
            String value = fieldDoc.getValue();

            if (!conf.includeSectionInKeyName() && value.startsWith("\"" + getName())) {
                value = value.substring(getName().length() + 2, value.length());
            }

            properties.add(new SampleConfProperty(value, fieldDoc.getComment(), defaultValue));
        }
    }

    private List<PropertyField> convertToPropertyFields(FieldDoc[] fields) {
        List<PropertyField> propertyFields = new ArrayList<>();
        for (FieldDoc fieldDoc : fields) {
            propertyFields.add(new PropertyFieldImpl(fieldDoc));
        }
        return propertyFields;
    }

    private void checkIntegrity(List<PropertyField> keys, List<PropertyField> defaults) {
        if (keys.size() > defaults.size()) {
            logger.warn("{}: Not all configuration properties have default values![properties={}; default_values={}]", getName(),
                    keys.size(), defaults.size());
        }

        for (PropertyField key : keys) {
            String propertyKey = key.getName();
            String defaultKey = getDefaultKey(defaults, key.getName());
            logger.debug("{}: propertyKey={}; defaultKey={}", getName(), propertyKey, defaultKey);
            if (StringUtils.isEmpty(defaultKey)) {
                logger.warn("{}: No default value found for property {}!", getName(), propertyKey);
            }
        }

    }

    private String getDefaultKey(List<PropertyField> defaults, String name) {
        for (PropertyField fieldDoc : defaults) {
            if (fieldDoc.getName().startsWith(name)) {
                return fieldDoc.getName();
            }
        }
        return "";
    }

    private String getDefaultValue(List<PropertyField> defaults, String name) {
        String retval = "";
        for (PropertyField fieldDoc : defaults) {
            if (fieldDoc.getName().startsWith(name)) {
                retval = fieldDoc.getValue();
            }
        }
        if (PropertyField.VALUE_NOT_DEFINED.equals(retval)) {
            retval = "";
            logger.warn("{}: Only primitives are supported for default values! {} default value not processed!", getName(), name);
        }
        return retval;
    }

    public String getName() {
        return sectionName;
    }

    public String asString() {
        StringBuilder builder = new StringBuilder();

        if (StringUtils.isNotEmpty(classDoc.commentText())) {
            String formatedComment = String.format("%s\n", classDoc.commentText());
            builder.append(Utils.ensureComment(formatedComment));
        }

        builder.append(String.format("[%s]\n", sectionName));
        builder.append("\n");
        boolean first = true;
        for (SampleConfProperty property : properties) {
            if (property.hasComment() && !first) {
                builder.append("\n");
            }
            first = false;
            builder.append(property.asString());
        }

        return builder.toString();
    }
}
