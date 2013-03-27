package com.epages.doclets.core;

public interface PropertyField {

    String VALUE_NOT_DEFINED = "ValueNotDefined";

    boolean isProperty();

    boolean isDefault();

    String getName();

    /**
     * @return the constant value of the field, or
     *         {@value #VALUE_NOT_DEFINED} if the value is not a constant.
     */
    String getValue();

    String getComment();
}