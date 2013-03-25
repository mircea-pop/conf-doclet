package com.epages.doclets.file;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SampleConfPropertyTest {
    private SampleConfProperty SUT;

    @Before
    public void before() {
        SUT = new SampleConfProperty("\"key\"", "comment", "\"defaultValue\"");
    }

    @Test
    public void testGetKey() {
        assertEquals("key", SUT.getKey());
    }
    
    @Test
    public void testGetComment() {
        assertEquals("comment", SUT.getComment());
    }
    
    @Test
    public void testGetDefaultValue() {
        assertEquals("defaultValue", SUT.getDefaultValue());
    }
    
    @Test    
    public void testAsString() {
        assertEquals(";;comment\n;key = defaultValue\n", SUT.asString());
    }
}
