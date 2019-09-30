package com.aq.mcorpdemo;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static com.aq.mcorpdemo.WebUtil.DEFAULT_REPLACE;
import static org.junit.Assert.assertEquals;

public class WebUtilTest {

    public static final String START = "ABCDE";
    public static final String END = "VWXYZ";

    @Test
    public void testPayloadLongStrig() {
        String data = START + StringUtils.repeat("A", 200) + StringUtils.repeat("B", 200) + END;
        assertEquals(410, data.length());
        int limit = 27; //DEFALT_REPLACE is 17 chars, so 10 more from origial
        String truncated = WebUtil.truncate(data, limit);
        assertEquals(limit, truncated.length());
        assertEquals(START + new String(DEFAULT_REPLACE) + END, truncated);
    }

    @Test
    public void testPayloadShort() {
        String expected = "HelloWorld";
        int limit = 27;
        String truncated = WebUtil.truncate(expected, limit);
        assertEquals(expected, truncated);
    }

    @Test
    public void testPayloadBoudaryCase1() {
        String expected = StringUtils.repeat("X", 17);
        int limit = 17;
        String truncated = WebUtil.truncate(expected, limit);
        assertEquals(expected, truncated);
    }

    @Test
    public void testPayloadBoudaryCase2() {
        String expected = StringUtils.repeat("X", 20);
        int limit = 18;
        String truncated = WebUtil.truncate(expected, limit);
        assertEquals("X" + new String(DEFAULT_REPLACE), truncated);
    }

    @Test
    public void testPayloadBoudaryCase3() {
        String expected = StringUtils.repeat("X", 20);
        int limit = 19;
        String truncated = WebUtil.truncate(expected, limit);
        assertEquals("X" + new String(DEFAULT_REPLACE) + "X", truncated);
    }

    /**
     * need to revisit requirements, we are emiiting entire string to logs when limit < REPLACE_STRINg
     */
    @Test
    public void testBadRequirement() {
        String data = START + StringUtils.repeat("A", 200) + StringUtils.repeat("B", 200) + END;
        assertEquals(410, data.length());
        int limit = 16; //limit lass then replace string
        String truncated = WebUtil.truncate(data, limit);

        assertEquals(data, truncated);
    }


}
