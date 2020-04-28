/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given two strings text1 and text2, return the length of their longest 
common subsequence.

A subsequence of a string is a new string generated from the original 
string with some characters(can be none) deleted without changing the 
relative order of the remaining characters. (eg, "ace" is a subsequence 
of "abcde" while "aec" is not). A common subsequence of two strings is a 
subsequence that is common to both strings.

 

If there is no common subsequence, return 0.
---------------------------------------------------------------------------
*/
package leetcode.challenge;

public class Day27_MaximalSquare {

	private static int maximalSquare(char[][] matrix) {

		// note height and width of the matrix
		int H = matrix.length;
		if (H == 0 || matrix[0].length == 0) {
			return 0;
		}
		int W = matrix[0].length;

		int side = 0;
		int[][] dp = new int[H][W]; // dp[i][j] -> maximal square whose bottom right cell is (i,j)

		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				if (matrix[r][c] == '1') {
					dp[r][c] = 1;
					if (r > 0 && c > 0) { // dp[i][j] = min(cell at left, cell at top, cell diagonally left up) + 1
						dp[r][c] = 1 + Math.min(Math.min(dp[r][c - 1], dp[r - 1][c]), dp[r - 1][c - 1]);
					}

					if (dp[r][c] > side) {
						side = dp[r][c];
					}
				}
			}
		}

		return side * side; // area
	}

	private static int maximalSquareBruteForce(char[][] matrix) {

		if (matrix.length == 0) {
			return 0;
		}

		int h = matrix.length;
		int w = matrix[0].length;

		int l = Math.min(h, w);

		int dp[][][] = new int[h][w][l];
		int ans = 0;

		// for single cells
		for (int r = 0; r < h; r++) {
			for (int c = 0; c < w; c++) {
				if (matrix[r][c] == '1') {
					dp[r][c][0] = 1;
					ans = 1;
				} else {
					dp[r][c][0] = 0;
				}
			}
		}

		for (int k = 1; k < l; k++) {

			for (int r = 0; r < h - k; r++) {
				for (int c = 0; c < w - k; c++) {

					if (dp[r][c][k - 1] == 0) {
						dp[r][c][k] = 0;
						continue;
					}

					boolean isSquare = true;

					// next column
					int nc = c + k;
					for (int i = r; i <= r + k; i++) {
						if (matrix[i][nc] != '1') {
							isSquare = false;
							break;
						}
					}

					if (!isSquare) {
						dp[r][c][k] = 0;
						continue;
					}

					// next row
					int nr = r + k;
					for (int i = c; i <= c + k; i++) {
						if (matrix[nr][i] != '1') {
							isSquare = false;
							break;
						}
					}

					if (isSquare) {
						dp[r][c][k] = 1;
						ans = (k + 1) * (k + 1);
					} else {
						dp[r][c][k] = 0;
					}
				}
			}
		}

		return ans;
	}

	public static void main(String[] args) {

		char[][] matrix = { 
				{ '1', '0', '1', '0', '0' }, 
				{ '1', '0', '1', '1', '1' }, 
				{ '1', '1', '1', '1', '1' },
				{ '1', '0', '0', '1', '0' } 
			};

		System.out.println("Maximal square area is: " + maximalSquare(matrix));
	}

	/*
	  Brute-force: 
		Time-complexity : O((mn)^2) 
		Space-complexity : O(1)
	  
	  Dynamic programming: 
	  	Time-complexity : O(mn) 
	  	Space-complexity : O(mn)
	 */
}
