package com.epages.doclets.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.epages.doclets.core.SampleConfProperty;

@RunWith(JUnit4.class)
public class SampleConfPropertyTest{
    private SampleConfProperty SUT;
    private SampleConfProperty SUT_NULL_COMMENT;
    
    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Before
    public void before() {
        SUT = new SampleConfProperty("\"key\"", "comment", "\"defaultValue\"");
        SUT_NULL_COMMENT = new SampleConfProperty("key", null, "3");
    }
    
    @Test
    public void testNullKey()
    {
        expected.expect(IllegalArgumentException.class);
        new SampleConfProperty(null, "comment", "3");
    }

    @Test
    public void testGetKey() {
        assertEquals("key", SUT.getKey());
        assertEquals("key", SUT_NULL_COMMENT.getKey());
    }
    
    @Test
    public void testGetComment() {
        assertEquals("comment", SUT.getComment());
        assertNull(SUT_NULL_COMMENT.getComment());
    }
    
    @Test
    public void testGetDefaultValue() {
        assertEquals("\"defaultValue\"", SUT.getDefaultValue());
        assertEquals("3", SUT_NULL_COMMENT.getDefaultValue());
    }
    
    @Test    
    public void testAsString() {
        assertEquals(";;comment\n;key = \"defaultValue\"\n", SUT.asString());
        assertEquals(";key = 3\n", SUT_NULL_COMMENT.asString());
    }
}
