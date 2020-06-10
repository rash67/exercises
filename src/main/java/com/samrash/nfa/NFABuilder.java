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
package com.samrash.nfa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NFABuilder
{
    private final String startState;
    private final Set<String> acceptStates = new HashSet<String>();
    private final Map<String, Set<String>> epsilonTransitions = new HashMap<String, Set<String>>();
    private final Map<String, Map<String, Set<String>>> transitions = new HashMap<String, Map<String, Set<String>>>();

    public NFABuilder(String startState)
    {
        this.startState = startState;
    }

    public NFABuilder addAcceptState(String state)
    {
        acceptStates.add(state);

        return this;
    }

    public NFABuilder addEpsilonTransition(String start, String end)
    {
        if (!epsilonTransitions.containsKey(start)) {
            epsilonTransitions.put(start, new HashSet<String>());
        }

        epsilonTransitions.get(start).add(end);

        return this;
    }

    public NFABuilder addTransition(String start, String c, String end)
    {
        if (!transitions.containsKey(start)) {
            transitions.put(start, new HashMap<String, Set<String>>());
        }

        if (!transitions.get(start).containsKey(c)) {
            transitions.get(start).put(c, new HashSet<String>());
        }

        transitions.get(start).get(c).add(end);

        return this;
    }

    public NFA build()
    {
        return new NFA(startState, acceptStates, transitions, epsilonTransitions);
    }
}
