package com.epages.doclets.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.Type;

public class PropertyFieldImplTest {

    @Test
    public void testIsProperty() {
        PropertyFieldImpl propertyField = new PropertyFieldImpl(getPropertyFieldDoc("PROPERTY1", true));
        assertTrue(propertyField.isProperty());
        
        propertyField = new PropertyFieldImpl(getPropertyFieldDoc("PROPERTY1", false));
        assertFalse(propertyField.isProperty());
    }
    
    @Test
    public void testIsDefault() {
        
        PropertyFieldImpl propertyField = new PropertyFieldImpl(getPropertyFieldDoc("PROPERTY1_DEFAULT", true));
        assertTrue(propertyField.isDefault());
        
        propertyField = new PropertyFieldImpl(getPropertyFieldDoc("PROPERTY1", true));        
        assertFalse(propertyField.isDefault());
    }
    
    @Test
    public void testGetValue() {
        PropertyFieldImpl propertyField = new PropertyFieldImpl(getPropertyFieldDoc("PROPERTY1_DEFAULT", true));
        assertEquals("property1_value", propertyField.getValue());
    }
    
    @Test
    public void testGetComment() {
        PropertyFieldImpl propertyField = new PropertyFieldImpl(getPropertyFieldDoc("PROPERTY1_DEFAULT", true));
        assertEquals("property1_Comment", propertyField.getComment());
    }

    private FieldDoc getPropertyFieldDoc(String name, boolean isStatic) {
        FieldDoc property1Field = Mockito.mock(FieldDoc.class);
        when(property1Field.name()).thenReturn(name);
        when(property1Field.commentText()).thenReturn("property1_Comment");
        when(property1Field.constantValueExpression()).thenReturn("property1_value");
        when(property1Field.isStatic()).thenReturn(isStatic);
        when(property1Field.isFinal()).thenReturn(true);
        
        Type stringType = Mockito.mock(Type.class);
        when(stringType.qualifiedTypeName()).thenReturn("java.lang.String");
        when(property1Field.type()).thenReturn(stringType );

        return property1Field;
    }
}
