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

public class StrStrNaive
{
    public static String strStr(String input, String pattern)
    {
        if (pattern.length() > input.length()) {
            return null;
        }

        for (int i = 0; i < input.length() - pattern.length(); i++) {
            boolean foundMatch = true;

            for (int j = 0, k = i; j < pattern.length(); j++, k++) {
                if (!input.substring(k, k + 1).equals(pattern.substring(j, j + 1))) {
                    foundMatch = false;
                    break;
                }
            }

            if (foundMatch) {
                return input.substring(i);
            }
        }

        return null;
    }

    public static void main(String[] args)
    {
        System.err.println(strStr("xyzabcxxxxabcy", "abx"));
        System.err.println(strStr("xyzabcxxxxabcy", "xyz"));
    }
}
