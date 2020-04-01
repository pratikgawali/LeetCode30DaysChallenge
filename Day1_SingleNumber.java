/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given a non-empty array of integers, every element appears twice except for
one. Find that single one.
Your algorithm should have a linear runtime complexity. Could you implement 
it without using extra memory?

Example 1:

Input: [2,2,1]
Output: 1

Example 2:

Input: [4,1,2,1,2]
Output: 4
---------------------------------------------------------------------------
*/
package leetcode.challenge;

public class Day1_SingleNumber {

	private static int singleNumber(int[] nums) {

		int n = nums.length, ans = nums[0], i = 1;
		
		while (i < n) {
			ans = ans ^ nums[i];
			i++;
		}
		
		return ans;
	}

	public static void main(String[] args) {

		int[] nums = { 4, 1, 2, 1, 2 };
		System.out.println("The only single number is: " + singleNumber(nums));
	}
}
