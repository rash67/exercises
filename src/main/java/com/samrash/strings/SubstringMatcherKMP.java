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

import java.nio.charset.StandardCharsets;

/**
 * effectively a Knuth-Morris-Pratt string patter matcher. This is for explanatory purposes only and not optimized or intended for use
 * it assumes UTF-8 encoded strings
 */

public class SubstringMatcherKMP
{
    public static final String NO_MATCH_STRING = "";

    private final int[] suffixPrefixMatchLength;
    private final byte[] patternChars;

    public SubstringMatcherKMP(String pattern)
    {
        suffixPrefixMatchLength = new int[pattern.length()];
        patternChars = pattern.getBytes(StandardCharsets.UTF_8);
        suffixPrefixMatchLength[0] = 0;

        // let pc = patternChars
        // suffixPrefixMatchLen[i] (spml[i]) will be the length of the longest suffix ending at i that matches a prefix of i. We use
        // a DP-like relation that spml[i] = spml[i-1] + 1 if the pc[i] == pc[spml[i-1]], ie the suffix-prefix match continues in the next character
        // otherwise, the value is 0
        for (int i = 1; i < suffixPrefixMatchLength.length; i++) {
            if (patternChars[i] == patternChars[suffixPrefixMatchLength[i - 1]]) {
                suffixPrefixMatchLength[i] = suffixPrefixMatchLength[i - 1] + 1;
            }
            else {
                // explicit for readability
                suffixPrefixMatchLength[i] = 0;
            }
        }
    }

    public int strStr(String s)
    {
        byte[] sChars = s.getBytes(StandardCharsets.UTF_8);

        // candidate string location
        int i = 0;
        // pattern
        int j = 0;
        int loopCount = 0;
        while (i < sChars.length && j < patternChars.length) {
            byte patternChar = patternChars[j];
            byte currentChar = sChars[i];
            loopCount++;
            if (currentChar == patternChar) {
                i++;
                j++;
            }
            else {
                if (j > 0) {
                    j = suffixPrefixMatchLength[j - 1];
                }
                else {
                    i++;
                }
            }
        }

        assert loopCount <= 2 * (patternChars.length + sChars.length);

        if (j == patternChars.length) {
            return i - patternChars.length;
        }
        else {
            return -1;
        }
    }

    public String find(String s)
    {
        int i = strStr(s);

        if (i == -1) {
            return NO_MATCH_STRING;
        }
        else {
            return s.substring(i);
        }
    }
}
