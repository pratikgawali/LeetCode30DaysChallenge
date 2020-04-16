/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given a string containing only three types of characters: '(', ')' and '*', 
write a function to check whether this string is valid. We define the 
validity of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left 
parenthesis '(' or an empty string.

An empty string is also valid.

Example 1:
Input: "()"
Output: True

Example 2:
Input: "(*)"
Output: True

Example 3:
Input: "(*))"
Output: True

Note:
The string size will be in the range [1, 100].
---------------------------------------------------------------------------
*/
package leetcode.challenge;

public class Day16_ValidParenthesisString {

	// Time-complexity: O(n)
	// Space-complexity: O(1)
	private static boolean checkValidStringWithGreedy(String s) {

		int n = s.length();

		if (n == 0) {
			return true;
		}

		int max = 0; // max number of ')' that CAN be satisfied.
		int min = 0; // min number of ')' that MUST be satisfied.

		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);

			if (c == '(') {
				max++;
				min++;
			} else if (c == ')') {
				max--;
				min--;
			} else {
				max++;
				min--;
			}

			if (max < 0) { // at every point ')' must have an '(' pair.
				return false;
			}
			
			if (min < 0) { // min number of ')' that MUST be satisfied cannot be a -ve value.
				min = 0;
			}
		}
		
		return min == 0;
	}

	// Time-complexity: O(3^n)
	// Space-complexity: O(n)
	private static boolean checkValidStringWithBacktracking(String s) {

		int n = s.length();

		if (n == 0) {
			return true;
		}

		char[] chars = new char[n];
		for (int i = 0; i < n; i++) {
			chars[i] = s.charAt(i);
		}

		return isValidString(chars, 0, 0);
	}

	private static boolean isValidString(char[] chars, int i, int count) {

		if (count < 0) {
			return false;
		}

		if (i == chars.length - 1) {
			if (chars[i] == '(') {
				return false;
			} else if (chars[i] == ')') {
				return count == 1;
			} else {
				return count == 1 || count == 0;
			}
		}

		if (chars[i] == '*') {

			chars[i] = '(';
			if (isValidString(chars, i + 1, count + 1)) {
				return true;
			}

			chars[i] = ')';
			if (isValidString(chars, i + 1, count - 1)) {
				return true;
			}

			chars[i] = ' ';
			if (isValidString(chars, i + 1, count)) {
				return true;
			}

			return false;
		} else if (chars[i] == '(') {

			return isValidString(chars, i + 1, count + 1);
		} else {

			return isValidString(chars, i + 1, count - 1);
		}
	}

	public static void main(String[] args) {

		String s = "(*))";

		System.out.print("Parenthesis string is valid: " + checkValidStringWithGreedy(s));
	}
}
