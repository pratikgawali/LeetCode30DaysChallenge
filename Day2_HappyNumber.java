/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with 
any positive integer, replace the number by the sum of the squares of its 
digits, and repeat the process until the number equals 1 (where it will 
stay), or it loops endlessly in a cycle which does not include 1. Those 
numbers for which this process ends in 1 are happy numbers.

Example: 

Input: 19
Output: true

Explanation: 
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
---------------------------------------------------------------------------
*/
package leetcode.challenge;

public class Day2_HappyNumber {

	private static final int[] SQUARES = { 0, 1, 4, 9, 16, 25, 36, 49, 64, 81 };

	private static boolean isHappy(int num) {

		int slow = digitSquareSum(num);
		int fast = digitSquareSum(slow);

		while (slow != fast) {
			slow = digitSquareSum(slow);
			fast = digitSquareSum(digitSquareSum(fast));
		}

		return slow == 1;
	}

	private static int digitSquareSum(int num) {

		int sum = 0;

		while (num > 0) {
			int digit = num % 10;
			sum += SQUARES[digit];
			num /= 10;
		}

		return sum;
	}

	public static void main(String[] args) {

		int num = 19;
		System.out.println(num + " is a HAPPY number: " + isHappy(num));
	}
}
