/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given a binary tree where each path going from the root to any leaf form a 
valid sequence, check if a given string is a valid sequence in such binary 
tree. 

We get the given string from the concatenation of an array of integers arr 
and the concatenation of all values of the nodes along a path results in a 
sequence in the given binary tree.


Example 1:

Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,0,1]
Output: true
Explanation: 
The path 0 -> 1 -> 0 -> 1 is a valid sequence (green color in the figure). 
Other valid sequences are: 
0 -> 1 -> 1 -> 0 
0 -> 0 -> 0

Example 2:

Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,0,1]
Output: false 
Explanation: The path 0 -> 0 -> 1 does not exist, therefore it is not even 
			 a sequence.

Example 3:

Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,1]
Output: false
Explanation: The path 0 -> 1 -> 1 is a sequence, but it is not a valid 
			 sequence.
 

Constraints:

1 <= arr.length <= 5000
0 <= arr[i] <= 9
Each node's value is between [0 - 9].
---------------------------------------------------------------------------
*/
package leetcode.challenge;

public class Day30_CheckValidSequence {
	
	private static boolean isValidSequence(TreeNode root, int[] arr) {
	     
        return traverse(root, arr, 0);
    }
    
    private static boolean traverse(TreeNode root, int[] arr, int i) {
        
        // leaf node check
        if (root.left == null && root.right == null) {
            return  i == arr.length - 1 && root.val == arr[i];
        }
        
        // last ele but not a leaf node
        if (i == arr.length-1) {
            return false;
        }
        
        if (arr[i] != root.val) {
            return false;
        }
        
        if (root.left != null && traverse(root.left, arr, i+1)) {
            return true;
        }
        
        return root.right != null && traverse(root.right, arr, i+1);
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
		
		/*
		  	   1
		  	  / \
		     0   1
		    / \
		   1   0
		 */

		
		TreeNode n3 = new TreeNode(1);
		
		TreeNode n4 = new TreeNode(1);
		TreeNode n5 = new TreeNode(0);
		TreeNode n2 = new TreeNode(0, n4, n5);

		TreeNode n1 = new TreeNode(1, n2, n3);
		
		int[] arr = new int[] {1,0,1};

		System.out.println("Given sequence is valid: " + isValidSequence(n1, arr));
	}

	/*
	 * Time-complexity : O(n) 
	 * Space-complexity : O(n)
	 */
}
