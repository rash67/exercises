package com.samrash.nfa;

import java.util.*;

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
