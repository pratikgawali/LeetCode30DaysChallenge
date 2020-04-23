/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise 
AND of all numbers in this range, inclusive.

Example 1:

Input: [5,7]
Output: 4

Example 2:

Input: [0,1]
Output: 0
---------------------------------------------------------------------------
*/
package leetcode.challenge;

public class Day23_BitwiseANDofNumbersRange {

	private static int rangeBitwiseAnd(int m, int n) {

		int shift = 0;
		while (m != n) {

			shift++;

			m >>= 1;
			n >>= 1;
		}

		return m << shift;
	}

	public static void main(String[] args) {

		int m = 5;
		int n = 7;

		System.out.println("Bitwise AND of numbers in the range " + m + " to " + n + " is: " + rangeBitwiseAnd(m, n));
	}
}
