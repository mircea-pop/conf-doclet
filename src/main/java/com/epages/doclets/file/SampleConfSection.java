package com.epages.doclets.file;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

import com.epages.doclets.ConfDoclet;
import com.epages.doclets.taglets.ConfigurationTaglets;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.Tag;

public class SampleConfSection {

    private final List<SampleConfProperty> properties = new ArrayList<>();
    private String sectionName;
    private final ClassDoc classDoc;
    private static final Logger log = Logger.getLogger(ConfDoclet.class.getName());

    public SampleConfSection(ClassDoc classDoc) {
        log.info(String.format("Configuration Class %s detected", classDoc.name()));
        this.classDoc = classDoc;
    }

    public void process() {
        processType();
        processFields();

        // processEnumWithReflection();
    }

    private void processEnumWithReflection() {
        try {
            Class<?> clazz = Class.forName(classDoc.qualifiedTypeName());
            if (!clazz.isEnum()) {
                return;
            }

            Field[] fields = clazz.getDeclaredFields();

            List<Field> cst = new ArrayList<Field>();

            for (Field f : fields) {
                if (f.isEnumConstant()) {
                    cst.add(f);
                    System.out.println(f.getName());
                }
            }

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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
        log.info(String.format("Configuration Class %s has %d fields", classDoc.name(), fields.length));

        List<FieldDoc> keys = new ArrayList<>();
        List<FieldDoc> defaults = new ArrayList<>();

        for (FieldDoc fieldDoc : fields) {
            if (fieldDoc.name().toUpperCase().endsWith("DEFAULT")) {
                defaults.add(fieldDoc);
            } else {
                keys.add(fieldDoc);
            }
        }

        if (keys.size() != defaults.size()) {
            log.warning(String.format("%s: Not all keys have default values![keys=%d; defaults=%d]", classDoc.name(), keys.size(),
                    defaults.size()));
        }

        for (FieldDoc fieldDoc : keys) {
            properties.add(new SampleConfProperty(getName() + "." + fieldDoc.constantValueExpression(), fieldDoc.commentText(), getDefault(
                    defaults, fieldDoc.name())));
        }

    }

    private String getDefault(List<FieldDoc> defaults, String name) {
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
            builder.append("\n");
        }

        return builder.toString();
    }
}
