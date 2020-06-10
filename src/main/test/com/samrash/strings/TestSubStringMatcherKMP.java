package com.samrash.strings;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSubStringMatcherKMP
{
    @Test
    public void testMatcher1()
            throws Exception
    {
        SubstringMatcherKMP matcher1 = new SubstringMatcherKMP("ababc");

        Assert.assertEquals(matcher1.find("xxxxabababczz"), "ababczz");
        Assert.assertEquals(matcher1.find("ababfuu"), SubstringMatcherKMP.NO_MATCH_STRING);
    }

    @Test
    public void testMatcher2()
            throws Exception
    {
        SubstringMatcherKMP matcher2 = new SubstringMatcherKMP("abx");

        Assert.assertEquals(matcher2.strStr("xyzabcxxxxabcy"), -1);
    }

    @Test
    public void testMatcher3()
            throws Exception
    {
        SubstringMatcherKMP matcher3 = new SubstringMatcherKMP("xxxx");

        Assert.assertEquals(matcher3.find("xyzabcxxxaxxxxbcy"), "xxxxbcy");
        Assert.assertEquals(matcher3.find("xxxabcxxyy"), SubstringMatcherKMP.NO_MATCH_STRING);
    }
}
