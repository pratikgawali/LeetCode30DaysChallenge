/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Suppose an array sorted in ascending order is rotated at some pivot unknown 
to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its 
index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
---------------------------------------------------------------------------
*/
package leetcode.challenge;

public class Day19_SearchInRotatedSortedArray {

	private static int search(int[] nums, int target) {

		if (nums.length == 0) {
			return -1;
		}

		return binarySearch(nums, target, 0, nums.length - 1);
	}

	private static int binarySearch(int[] nums, int target, int l, int h) {

		if (h < l) {
			return -1;
		}

		if (l == h) {
			return target == nums[l] ? l : -1;
		}

		int mid = (l + h) / 2;

		if (nums[mid] == target) {
			return mid;
		}

		if (target > nums[mid]) {

			if (nums[h] >= nums[mid]) { // sorted 2nd half

				if (target <= nums[h]) {
					return binarySearch(nums, target, mid + 1, h);
				} else {
					return binarySearch(nums, target, l, mid - 1);
				}
			} else { // sorted 1st half
				return binarySearch(nums, target, mid + 1, h);
			}

		} else { // target is smaller than middle element

			if (nums[l] <= nums[mid]) { // sorted 1st half

				if (target >= nums[l]) {
					return binarySearch(nums, target, l, mid - 1);
				} else {
					return binarySearch(nums, target, mid + 1, h);
				}
			} else { // sorted 2nd half
				return binarySearch(nums, target, l, mid - 1);
			}
		}
	}

	public static void main(String[] args) {

		int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
		int target = 1;

		System.out.println(target + " value is present at index: " + search(nums, target));
	}
}
