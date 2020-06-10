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

import java.util.HashMap;
import java.util.Map;

/**
 * inefficient example of a DFA-based for .*pattern.*
 * ends up O(n * K) where n = lenth(input string), K is size of alphabet (255).E
 * Primarily for explanatory purposes
 */

public class SubStringMatcherDFA
{
    private static final char[] ALPHABET = new char[256];

    static {
        for (char c = 0; c < 255; c++) {
            ALPHABET[c] = c;
        }
    }

    private final Node startNode;
    private final int patternLength;

    public SubStringMatcherDFA(String pattern)
    {
        int stateNum = 0;
        Node lastNode = new Node(stateNum);

        startNode = lastNode;
        patternLength = pattern.length();

        //setup initial node with self-loops
        for (char c : ALPHABET) {
            lastNode.transitions.put(c, lastNode);
        }

        for (char letter : pattern.toCharArray()) {
            Node failNode = lastNode.transitions.get(letter);
            Node nextNode = new Node(++stateNum);

            lastNode.transitions.put(letter, nextNode);

            for (char c : ALPHABET) {
                nextNode.transitions.put(c, failNode.transitions.get(c));
            }

            lastNode = nextNode;
        }
    }

    public String match(String input)
    {
        Node currentNode = startNode;
        char[] inputAsChar = input.toCharArray();

        for (int i = 0; i < inputAsChar.length; i++) {
            char c = inputAsChar[i];

            currentNode = currentNode.transitions.get(c);

            if (currentNode.stateNum == patternLength) {
                return input.substring(i - patternLength + 1);
            }
        }

        return null;
    }

    private static class Node
    {
        private final Map<Character, Node> transitions = new HashMap<Character, Node>();
        private final int stateNum;

        private Node(int stateNum)
        {
            this.stateNum = stateNum;
        }
    }

    public static void main(String[] args)
    {
        SubStringMatcherDFA matcher = new SubStringMatcherDFA("here");

        System.err.println(matcher.match("overhere"));
        System.err.println(matcher.match("hereafter"));
        System.err.println(matcher.match("there"));
        System.err.println(matcher.match("thereare"));
        System.err.println(matcher.match("herearethe"));
        System.err.println(matcher.match("not her but here instead"));
    }
}
