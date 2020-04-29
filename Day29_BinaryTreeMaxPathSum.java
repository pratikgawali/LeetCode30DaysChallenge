/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some 
starting node to any node in the tree along the parent-child connections. 
The path must contain at least one node and does not need to go through the 
root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6

Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
---------------------------------------------------------------------------
*/
package leetcode.challenge;

public class Day29_BinaryTreeMaxPathSum {

	private static int maxSum = Integer.MIN_VALUE;

	private static int maxPathSum(TreeNode root) {

		traverse(root);
		return maxSum;
	}

	private static int traverse(TreeNode root) {

		if (root == null) {
			return 0;
		}

		int left = Math.max(0, traverse(root.left));
		int right = Math.max(0, traverse(root.right));

		maxSum = Math.max(maxSum, root.val + left + right);

		return root.val + Math.max(left, right);
	}

	static class TreeNode {

		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	public static void main(String[] args) {

		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);

		TreeNode n1 = new TreeNode(1, n2, n3);

		System.out.println("Max path sum is: " + maxPathSum(n1));
	}

	/*
	 * Time-complexity : O(n) 
	 * Space-complexity : O(n)
	 */
}
