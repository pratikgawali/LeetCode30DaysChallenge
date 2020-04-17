/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Given a 2d grid map of '1's (land) and '0's (water), count the number of 
islands. An island is surrounded by water and is formed by connecting 
adjacent lands horizontally or vertically. You may assume all four edges 
of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1

Example 2:

Input:
11000
11000
00100
00011

Output: 3
---------------------------------------------------------------------------
*/
package leetcode.challenge;

public class Day17_NumberOfIslands {

private static int m, n;
    
    public static int numIslands(char[][] grid) {

        if (grid.length == 0) {
            return 0;
        }
        
        m = grid.length;
        n = grid[0].length;
        
        int count  = 0;
        
        for (int r=0; r<m; r++) {
            for (int c=0; c<n; c++) {
                
                if (grid[r][c] == '1') {
                    count++;
                    scanIsland(grid, r, c);
                }
            }
        }
        
        return count;
    }
    
    private static void scanIsland(char[][] grid, int r, int c) {
        
        if (!isValidCell(r,c) || grid[r][c] == '0') {
            return;
        }
        
        grid[r][c] = '0';
        
        scanIsland(grid, r-1, c); // north
        scanIsland(grid, r, c+1); // east
        scanIsland(grid, r, c-1); // west
        scanIsland(grid, r+1, c); // south
    }
    
    private static boolean isValidCell(int r, int c) {
        
        return r >= 0 && r < m && c >= 0 && c < n;
    }

	public static void main(String[] args) {

		char[][] grid = {
				{'1','1','1','1','0'},
				{'1','1','0','1','0'},
				{'1','1','0','0','0'},
				{'0','0','0','0','0'},
			};

		System.out.print("Number of islands are: " + numIslands(grid));
	}
}
