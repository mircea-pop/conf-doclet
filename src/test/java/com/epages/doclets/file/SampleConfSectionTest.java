package com.epages.doclets.file;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.epages.doclets.taglets.ConfigurationTaglets;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.Tag;

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

        SUT = new SampleConfSection(classDoc);
        SUT.process();
    }

    private FieldDoc[] getMockFields() {
        FieldDoc property1Field = Mockito.mock(FieldDoc.class);
        when(property1Field.name()).thenReturn("PROPERTY1");
        when(property1Field.commentText()).thenReturn("property1_Comment");
        when(property1Field.constantValueExpression()).thenReturn("property1_value");

        FieldDoc property1DefaultField = Mockito.mock(FieldDoc.class);
        when(property1DefaultField.name()).thenReturn("PROPERTY1_DEFAULT");
        when(property1DefaultField.constantValueExpression()).thenReturn("property1_default_value");

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
        assertEquals(";;section_comment\n[Section]\n;;property1_Comment\n;Section.property1_value = property1_default_value\n",
                SUT.asString());
    }
}
