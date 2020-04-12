/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
We have a collection of stones, each stone has a positive integer weight.

Each turn, we choose the two heaviest stones and smash them together.  
Suppose the stones have weights x and y with x <= y.  The result of this 
smash is:

If x == y, both stones are totally destroyed;
If x != y, the stone of weight x is totally destroyed, and the stone of 
weight y has new weight y-x.
At the end, there is at most 1 stone left.  Return the weight of this stone 
(or 0 if there are no stones left.)

Example 1:

Input: [2,7,4,1,8,1]
Output: 1
Explanation: 
We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of last stone.
 
Note:
1 <= stones.length <= 30
1 <= stones[i] <= 1000
---------------------------------------------------------------------------
*/
package leetcode.challenge;

import java.util.PriorityQueue;

public class Day12_LastStoneWeight {

	private static int lastStoneWeight(int[] stones) {

		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((i1, i2) -> i2 - i1);
		for (int i = 0; i < stones.length; i++) {
			maxHeap.add(stones[i]);
		}

		while (maxHeap.size() > 1) {
			int max1 = maxHeap.poll();
			int max2 = maxHeap.poll();

			if (max1 != max2) {
				maxHeap.add(max1 - max2);
			}
		}

		return maxHeap.size() > 0 ? maxHeap.poll() : 0;
	}

	public static void main(String[] args) {

		int[] stones = new int[] { 2, 7, 4, 1, 8, 1 };

		System.out.println("Last stone remaining will have a weight of: " + lastStoneWeight(stones));
	}
}
