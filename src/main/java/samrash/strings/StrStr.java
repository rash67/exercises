package samrash.strings;

public class StrStr
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
	}
}