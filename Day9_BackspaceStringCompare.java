/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given two strings S and T, return if they are equal when both are typed 
into empty text editors. # means a backspace character.

Example 1:

Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".

Example 2:

Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".

Example 3:

Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".

Example 4:

Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".

Note:
1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.
Follow up:

Can you solve it in O(N) time and O(1) space?
---------------------------------------------------------------------------
*/
package leetcode.challenge;

import java.util.ArrayDeque;
import java.util.Deque;

public class Day9_BackspaceStringCompare {

	// solution 1 with O(n) extra space
	private static boolean backspaceCompareWithExtraSpace(String S, String T) {

		Deque<Character> dq1 = new ArrayDeque<>();
		Deque<Character> dq2 = new ArrayDeque<>();

		fillStack(S, dq1);
		fillStack(T, dq2);

		return isEqual(dq1, dq2);
	}

	// solution 2 with O(1) extra space
	private static boolean backspaceCompare(String S, String T) {

		int s = S.length() - 1;
		int t = T.length() - 1;

		while (s >= 0 && t >= 0) {

			s = getValidCharIndex(S, s);
			t = getValidCharIndex(T, t);

			if (s == -1 || t == -1) {
				return s == t;
			}

			if (S.charAt(s) != T.charAt(t)) {
				return false;
			}

			s--;
			t--;
		}

		return getValidCharIndex(S, s) == getValidCharIndex(T, t);
	}

	private static void fillStack(String str, Deque<Character> dq) {

		for (int i = 0, n = str.length(); i < n; i++) {
			char c = str.charAt(i);
			if (c == '#') {
				if (!dq.isEmpty()) {
					dq.removeFirst();
				}
			} else {
				dq.addFirst(c);
			}
		}
	}

	private static boolean isEqual(Deque<Character> dq1, Deque<Character> dq2) {

		int n = dq1.size();

		if (n == dq2.size()) {
			for (int i = 0; i < n; i++) {
				if (dq1.removeFirst() != dq2.removeFirst()) {
					return false;
				}
			}
			return true;
		}

		return false;
	}

	private static int getValidCharIndex(String str, int i) {

		int skipCount = 0;
		while (i >= 0) {

			char c = str.charAt(i);
			if (c == '#') {
				skipCount++;
			} else if (skipCount != 0) {
				skipCount--;
			} else {
				return i;
			}

			i--;
		}

		return -1;
	}

	public static void main(String[] args) {

		String S = "nzp#o#g";
		String T = "b#nzp#o#g";

		System.out.println("\"" + S + "\"" + " and " + "\"" + T + "\"" + ":");
		System.out.println("O(n) space: " + backspaceCompareWithExtraSpace(S, T));
		System.out.println("O(1) space: " + backspaceCompare(S, T));
	}
}
