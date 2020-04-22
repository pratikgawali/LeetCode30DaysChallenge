/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given an array of integers and an integer k, you need to find the total 
number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2

Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the 
integer k is [-1e7, 1e7].
---------------------------------------------------------------------------
*/
package leetcode.challenge;

import java.util.HashMap;
import java.util.Map;

public class Day22_SubarraySumEqualsK {

	private static int subarraySum(int[] nums, int k) {

		int n = nums.length;

		if (n == 0) {
			return 0;
		}

		int[] prefixSum = new int[n];

		prefixSum[0] = nums[0];
		for (int i = 1; i < n; i++) {
			prefixSum[i] = nums[i] + prefixSum[i - 1];
		}

		int count = 0;
		Map<Integer, Integer> map = new HashMap<>();

		map.put(0, 1);

		for (int i = 0; i < n; i++) {

			if (map.containsKey(prefixSum[i] - k)) {
				count += map.get(prefixSum[i] - k);
			}

			int prefixSumCount = map.getOrDefault(prefixSum[i], 0);
			map.put(prefixSum[i], prefixSumCount + 1);
		}

		return count;
	}

	public static void main(String[] args) {

		int[] nums = { 1, 1, 1 };
		int k = 2;

		System.out.println("Number of subarrays with sum " + k + " is: " + subarraySum(nums, k));
	}
}
