/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given a binary array, find the maximum length of a contiguous subarray with 
equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 
0 and 1.

Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal 
number of 0 and 1.

Note: The length of the given binary array will not exceed 50,000.
---------------------------------------------------------------------------
*/
package leetcode.challenge;

import java.util.HashMap;
import java.util.Map;

public class Day13_ContiguousArray {

	private static int maxLengthBruteForce(int[] nums) {

		int[] count0 = new int[nums.length];
		int[] count1 = new int[nums.length];

		int sum0 = 0, sum1 = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				sum0++;
			} else {
				sum1++;
			}
			count0[i] = sum0;
			count1[i] = sum1;
		}

		int maxLength = 0;

		for (int l = 0; l < nums.length - 1; l++) {
			for (int r = l + 1; r < nums.length; r++) {

				int zeroCount = count0[r] - count0[l];
				int oneCount = count1[r] - count1[l];

				if (nums[l] == 0) {
					zeroCount++;
				} else {
					oneCount++;
				}

				if (zeroCount == oneCount) {
					maxLength = Math.max(maxLength, r - l + 1);
				}
			}
		}

		return maxLength;
	}

	private static int maxLength(int[] nums) {

		if (nums.length == 0) {
			return 0;
		}

		// replace 0 with -1
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				nums[i] = -1;
			}
		}

		int[] cummulativeSum = new int[nums.length];
		int maxLength = 0;

		cummulativeSum[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			cummulativeSum[i] = cummulativeSum[i - 1] + nums[i];
			if (cummulativeSum[i] == 0) {
				maxLength = Math.max(maxLength, i + 1);
			}
		}

		Map<Integer, Integer> cummSumIndexMap = new HashMap<>();
		for (int i = 0; i < cummulativeSum.length; i++) {
			if (cummSumIndexMap.containsKey(cummulativeSum[i])) {
				maxLength = Math.max(maxLength, i - cummSumIndexMap.get(cummulativeSum[i]));
			} else {
				cummSumIndexMap.put(cummulativeSum[i], i);
			}
		}

		return maxLength;
	}

	public static void main(String[] args) {

		int[] nums = new int[] { 1, 1 };

		System.out.println("Max length of subarray with equal number of 0s and 1s: " + maxLength(nums));
	}
}
