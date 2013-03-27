package com.epages.doclets.core;

import com.sun.javadoc.FieldDoc;

public class PropertyFieldImpl implements PropertyField {
    private final FieldDoc fieldDoc;

    public PropertyFieldImpl(FieldDoc fieldDoc) {
        this.fieldDoc = fieldDoc;
    }

    @Override
    public boolean isProperty() {
        return isPropertyQualified() && isString() && !getName().toUpperCase().endsWith("DEFAULT");
    }

    @Override
    public boolean isDefault() {
        return isPropertyQualified() && getName().toUpperCase().endsWith("DEFAULT");
    }

    @Override
    public String getName() {
        return fieldDoc.name();
    }

    @Override
    public String getValue() {
        return fieldDoc.constantValueExpression() != null ? fieldDoc.constantValueExpression() : VALUE_NOT_DEFINED;
    }

    @Override
    public String getComment() {
        return fieldDoc.commentText();
    }

    private boolean isPropertyQualified() {
        return fieldDoc.isStatic() && fieldDoc.isFinal() && !VALUE_NOT_DEFINED.equals(getValue());
    }

    private boolean isString() {
        return fieldDoc.type().qualifiedTypeName().equals("java.lang.String");
    }

    @Override
    public String toString() {
        return getName() + " = " + getValue();
    }

}
