package com.epages.doclets.core;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.epages.doclets.conf.SampleConfConfiguration;
import com.epages.doclets.taglets.ConfigurationTaglets;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.Tag;
import com.sun.javadoc.Type;

@RunWith(MockitoJUnitRunner.class)
public class SampleConfSectionTest {
    private SampleConfSection SUT;

    @Mock
    private ClassDoc classDoc;

    @Before
    public void before() {
        Tag[] sectionTags = getMockSectionTags();
        FieldDoc[] fields = getMockFields();

        when(classDoc.tags(ConfigurationTaglets.SECTION.getTag())).thenReturn(sectionTags);
        when(classDoc.fields()).thenReturn(fields);
        when(classDoc.name()).thenReturn("TestConfigClass");
        when(classDoc.commentText()).thenReturn("section_comment");

        SUT = new SampleConfSection(classDoc, Mockito.mock(SampleConfConfiguration.class));
        SUT.process();
    }

    private FieldDoc[] getMockFields() {
        FieldDoc property1Field = Mockito.mock(FieldDoc.class);
        when(property1Field.name()).thenReturn("PROPERTY1");
        when(property1Field.commentText()).thenReturn("property1_Comment");
        when(property1Field.constantValueExpression()).thenReturn("\"Section.property1_value\"");
        when(property1Field.isStatic()).thenReturn(true);
        when(property1Field.isFinal()).thenReturn(true);
        
        Type stringType = Mockito.mock(Type.class);
        when(stringType.qualifiedTypeName()).thenReturn("java.lang.String");
        when(property1Field.type()).thenReturn(stringType );

        FieldDoc property1DefaultField = Mockito.mock(FieldDoc.class);
        when(property1DefaultField.name()).thenReturn("PROPERTY1_DEFAULT");
        when(property1DefaultField.constantValueExpression()).thenReturn("property1_default_value");
        when(property1DefaultField.isStatic()).thenReturn(true);
        when(property1DefaultField.isFinal()).thenReturn(true);
        when(property1DefaultField.type()).thenReturn(stringType );

        return new FieldDoc[] { property1Field, property1DefaultField };
    }

    private Tag[] getMockSectionTags() {
        Tag tag = Mockito.mock(Tag.class);
        when(tag.text()).thenReturn("Section");
        return new Tag[] { tag };
    }

    @Test
    public void testGetName() {
        assertEquals("Section", SUT.getName());
    }

    @Test
    public void testAsString() {
        System.out.println("SampleConfSectionTest.testAsString() \n" +SUT.asString() );
        
        assertEquals(";;section_comment\n[Section]\n\n;;property1_Comment\n;property1_value = property1_default_value\n",
                SUT.asString());
    }
}
