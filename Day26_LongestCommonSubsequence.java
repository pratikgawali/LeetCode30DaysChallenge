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

public class Day26_LongestCommonSubsequence {

	private static int longestCommonSubsequence(String text1, String text2) {
        
        int m = text1.length();
        int n = text2.length();
        
        if (m == 0 || n == 0) {
            return 0;
        }
        
        int[][] dp = new int[m+1][n+1];
        
        for (int i=1; i<=m; i++){
            for (int j=1; j<=n; j++){
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        
        return dp[m][n];
    }

	public static void main(String[] args) {

		String text1 = "abcde";
		String text2 = "acze";

		System.out.println("Size of longest common sequence is: " + longestCommonSubsequence(text1, text2));
	}
}
