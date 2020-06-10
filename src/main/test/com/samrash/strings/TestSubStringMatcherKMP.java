/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
