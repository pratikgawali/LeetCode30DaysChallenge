/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given an integer array nums, find the contiguous subarray (containing at 
least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6

Explanation: [4,-1,2,1] has the largest sum = 6.

---------------------------------------------------------------------------
Follow up:
---------------------------------------------------------------------------
If you have figured out the O(n) solution, try coding another solution 
using the divide and conquer approach, which is more subtle.
---------------------------------------------------------------------------
*/

package leetcode.challenge;

public class Day3_MaximumSubarray {

	// Kadane's algorithm
	private static int maxSumSubarray(int[] arr) {

		int maxSum = arr[0];

		for (int i = 1, sum = arr[0]; i < arr.length; i++) {
			sum = Math.max(sum + arr[i], arr[i]);
			maxSum = Math.max(maxSum, sum);
		}

		return maxSum;
	}

	// Divide & Conquer
	private static int maxSumSubarray(int[] arr, int low, int high) {

		if (low >= high)
			return arr[low];

		int mid = (low + high) / 2;
		int maxSum = Math.max(maxSumSubarray(arr, low, mid - 1), maxSumSubarray(arr, mid + 1, high));
		maxSum = Math.max(maxSum, maxSumSubarrayWithMid(arr, low, high, mid));

		return maxSum;
	}

	private static int maxSumSubarrayWithMid(int[] arr, int low, int high, int mid) {

		int sum = 0;

		int lMaxSum = 0;
		for (int i = mid - 1; i >= low; i--) {
			sum += arr[i];
			if (sum > lMaxSum)
				lMaxSum = sum;
		}

		sum = 0;
		int rMaxSum = 0;
		for (int i = mid + 1; i <= high; i++) {
			sum += arr[i];
			if (sum > rMaxSum)
				rMaxSum = sum;
		}
		return lMaxSum + rMaxSum + arr[mid];
	}

	public static void main(String[] args) {

		int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };

		System.out.println("Kadane: Sum of maximum sum subarray is: " + maxSumSubarray(arr));
		System.out.println("D&C: Sum of maximum sum subarray is: " + maxSumSubarray(arr, 0, arr.length - 1));
	}
}
