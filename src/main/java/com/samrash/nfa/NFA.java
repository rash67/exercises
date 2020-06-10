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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class NFA
{
	private final Set<String> acceptStates;
	private final Map<String, Map<String, Set<String>>> transitions;
	private final Map<String, Set<String>> epsilonTransitions;
	private volatile Set<String> states;

	public NFA(String startState, Set<String> acceptStates, Map<String, Map<String, Set<String>>> transitions, Map<String, Set<String>> epsilonTransitions)
	{
		this.acceptStates = acceptStates;
		this.transitions = transitions;
		this.epsilonTransitions = epsilonTransitions;
		states = new CopyOnWriteArraySet<String>();
		states.add(startState);
		evaluateEpsilons();
	}

	public boolean evaluate(String c)
	{
		evaluateEpsilons();

		Set<String> nextStates = new CopyOnWriteArraySet<String>();

		for (String state : states) {
			if (transitions.containsKey(state) && transitions.get(state).containsKey(c)) {
				nextStates.addAll(transitions.get(state).get(c));
			}
		}

		states = nextStates;

		evaluateEpsilons();

		return isInAcceptState();
	}

	public boolean isInAcceptState()
	{
		evaluateEpsilons();

		for (String state : states) {
			if (acceptStates.contains(state)) {
				return true;
			}
		}
		return false;
	}

	private void evaluateEpsilons()
	{
		Set<String> statesCopy;

		do {
			statesCopy = new HashSet<String>(states);

			for (String state : states) {
				if (epsilonTransitions.containsKey(state)) {
					states.addAll(epsilonTransitions.get(state));
				}
			}
		} while (!statesCopy.equals(states));
	}

	public Set<String> getStates()
	{
		return Collections.unmodifiableSet(states);
	}

	public static void main(String[] args)
	{
		NFABuilder builder = new NFABuilder("q1")
			.addEpsilonTransition("q2", "q3")
			.addTransition("q1", "0", "q1")
			.addTransition("q1", "1", "q1")
			.addTransition("q1", "1", "q2")
			.addTransition("q2", "0", "q3")
			.addTransition("q3", "1", "q4")
			.addTransition("q4", "0", "q4")
			.addTransition("q4", "1", "q4")
			.addAcceptState("q4");

		NFA nfa = builder.build();

		runMachine(Arrays.asList("0", "1", "0", "1", "1", "0"), nfa);
	}

	private static void runMachine(List<String> input, NFA nfa)
	{
		System.err.println(nfa.isInAcceptState());
		System.err.println(nfa.getStates());

		for (String c : input) {
			step(c, nfa);
		}
	}

	private static void step(String c, NFA nfa)
	{
		System.err.println("input : " + c);
		System.err.println(nfa.evaluate(c));
		System.err.println(nfa.getStates());
	}
}
