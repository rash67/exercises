package samrash.strings;

import java.util.*;

public class SubstringMatcher
{
	private static final char[] ALPHABET = new char[256];
	static {
		for (char c = 0; c < 255; c++) {
			ALPHABET[c] = c;
		}
	}

	private final Node startNode;
	private final int patternLength;

	public SubstringMatcher(String pattern)
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

		for (int i=0; i < inputAsChar.length; i++) {
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
		SubstringMatcher matcher = new SubstringMatcher("here");

		System.err.println(matcher.match("overhere"));
		System.err.println(matcher.match("hereafter"));
		System.err.println(matcher.match("there"));
		System.err.println(matcher.match("thereare"));
		System.err.println(matcher.match("herearethe"));
		System.err.println(matcher.match("not her but here instead"));

	}
}