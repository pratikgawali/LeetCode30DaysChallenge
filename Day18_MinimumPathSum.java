/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given a m x n grid filled with non-negative numbers, find a path from top 
left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
---------------------------------------------------------------------------
*/
package leetcode.challenge;

public class Day18_MinimumPathSum {

	private static int m, n;
	
	private static int minPathSumIterative(int[][] grid, int[][] dp) {
		
		dp[0][0] = grid[0][0]; 
		
		// top most row -> only immediate left is relevant
		for (int c = 1; c < n; c++) {
			dp[0][c] = dp[0][c-1] + grid[0][c];
		}
		
		// left most col -> only immediate up is relevant
		for (int r=1; r < n; r++) {
			dp[r][0] = dp[r-1][0] + grid[r][0];
		}
		
		for (int r = 1; r < m; r++) {
			for (int c = 1; c < n; c++) {
				dp[r][c] = grid[r][c] + Math.min(dp[r-1][c], dp[r][c-1]);
			}
		}
		
		return dp[m-1][n-1];
	}

	private static int minPathSum(int[][] grid) {

		m = grid.length;
		n = grid[0].length;

		int[][] dp = new int[m][n];
		
		// uncomment for recursive solution instead
		/*
		initialize(dp);
		return minPathRecursive(grid, m - 1, n - 1, dp);
		*/
		
		return minPathSumIterative(grid, dp);
	}

	private static int minPathRecursive(int[][] grid, int r, int c, int[][] dp) {

		if (r == 0 && c == 0) {
			dp[r][c] = grid[r][c];
			return dp[r][c];
		} else if (!isValidCell(r, c)) {
			return Integer.MAX_VALUE;
		}

		if (dp[r][c] != Integer.MAX_VALUE) {
			return dp[r][c];
		}

		dp[r][c] = grid[r][c] + Math.min(minPathRecursive(grid, r - 1, c, dp), // left
				minPathRecursive(grid, r, c - 1, dp)); // up

		return dp[r][c];
	}

	private static void initialize(int[][] dp) {

		for (int r = 0; r < m; r++) {
			for (int c = 0; c < n; c++) {
				dp[r][c] = Integer.MAX_VALUE;
			}
		}
	}

	private static boolean isValidCell(int r, int c) {

		return r >= 0 && r < m && c >= 0 && c < n;
	}

	public static void main(String[] args) {

		int[][] grid = { 
				{ 1, 3, 1 }, 
				{ 1, 5, 1 }, 
				{ 4, 2, 1 } 
			};

		System.out.println("Minimum path length to reach the bottom right from the top left is: " + minPathSum(grid));
	}
}
