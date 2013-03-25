package com.epages.doclets;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilsTest {

    @Test
    public void testGetOption() {
        String[][] options = { { "option1", "options1.value1" }, { "option2", "option2.value1", "option2.value2" } };
        assertEquals("options1.value1", Utils.getOption(options, "option1"));
    }

    @Test
    public void testGetOptions() {
        String[][] options = { { "option1", "options1.value1" }, { "option2", "option2.value1" }, { "option2", "option2.value2" } };
        assertEquals("option2.value1", Utils.getOptions(options, "option2").get(0));
        assertEquals("option2.value2", Utils.getOptions(options, "option2").get(1));
    }
}
