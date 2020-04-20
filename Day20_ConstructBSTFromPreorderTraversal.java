/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Return the root node of a binary search tree that matches the given 
preorder traversal.
---------------------------------------------------------------------------
*/
package leetcode.challenge;

public class Day20_ConstructBSTFromPreorderTraversal {

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	private static TreeNode bstFromPreorder(int[] preorder) {

		int n = preorder.length;

		if (preorder.length == 0) {
			return null;
		}

		return constructBST(preorder, 0, n - 1);
	}

	private static TreeNode constructBST(int[] preorder, int l, int h) {

		if (h < l) {
			return null;
		}

		TreeNode root = new TreeNode(preorder[l]);

		int i = l + 1;
		while (i <= h && preorder[i] < preorder[l]) {
			i++;
		}

		root.left = constructBST(preorder, l + 1, i - 1);
		root.right = constructBST(preorder, i, h);

		return root;
	}

	private static void preorder(TreeNode node) {

		if (node == null) {
			System.out.print(" null ");
		} else {
			System.out.print(" " + node.val);
			preorder(node.left);
			preorder(node.right);
		}
	}

	public static void main(String[] args) {

		int[] preorder = { 8, 5, 1, 7, 10, 12 };

		TreeNode root = bstFromPreorder(preorder);
		preorder(root);
	}
}
