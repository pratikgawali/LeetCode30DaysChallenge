/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given a binary tree, you need to compute the length of the diameter of the 
tree. The diameter of a binary tree is the length of the longest path 
between any two nodes in a tree. This path may or may not pass through the 
root.

Example:
Given a binary tree
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of 
edges between them.
---------------------------------------------------------------------------
*/
package leetcode.challenge;

public class Day11_DiameterOfBinaryTree {

	public static class TreeNode {
		
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	private static int diameter = 0;

	public static int diameterOfBinaryTree(TreeNode root) {

		traverse(root);
		return diameter;
	}

	private static int traverse(TreeNode root) {

		if (root == null) {
			return 0;
		}

		int leftDepth = traverse(root.left);
		int rightDepth = traverse(root.right);

		int pathLength = leftDepth + rightDepth;
		diameter = pathLength > diameter ? pathLength : diameter;

		return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
	}

	public static void main(String[] args) {
		
		/* Given Binary Tree:
		 
			  1
	         / \
	        2   3
	       / \     
	      4   5   
	       
		*/

		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n2 = new TreeNode(2);
		TreeNode n1 = new TreeNode(1);
		
		n2.left = n4;
		n2.right = n5;
		n1.left = n2;
		n1.right = n3;
		
		System.out.println("Diameter of the tree is: " + diameterOfBinaryTree(n1));
	}
}