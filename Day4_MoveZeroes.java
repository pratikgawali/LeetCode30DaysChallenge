/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given an array nums, write a function to move all 0's to the end of it 
while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]

Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
---------------------------------------------------------------------------
*/
package leetcode.challenge;

public class Day4_MoveZeroes {

	// my first try
	private static void moveZeroes1(int[] nums) {

		int firstZero = -1;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				if (firstZero == -1) {
					firstZero = i;
				}
			} else if (firstZero != -1) {
				nums[firstZero] = nums[i];
				nums[i] = 0;
				firstZero++;
			}
		}
	}

	// optimized after research
	private static void moveZeroes2(int[] nums) {

		int nonZeroCount = 0;

		// move non-zeroes to the front
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				nums[nonZeroCount++] = nums[i];
			}
		}

		// make all remaining elements as zero
		for (int i = nonZeroCount; i < nums.length; i++) {
			nums[i] = 0;
		}
	}

	public static void main(String[] args) {

		int[] nums1 = { 0, 1, 4, 3, 12 };
		int[] nums2 = { 1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0, 9 };

		moveZeroes1(nums1);
		System.out.print("Initial approach: ");
		printArray(nums1);

		moveZeroes2(nums2);
		System.out.print("Optimized approach: ");
		printArray(nums2);
	}

	private static void printArray(int[] arr) {

		System.out.print("[ ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println("]");
	}
}
