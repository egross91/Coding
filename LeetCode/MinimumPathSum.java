public class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0)
            return -1;
            
        int val = 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n]; // Defaults to 0s.
        
        dp[0][0] = grid[0][0];
        
        for (int i = 1; i < m; ++i)
            dp[i][0] = (dp[i-1][0] + grid[i][0]);
        for (int i = 1; i < n; ++i)
            dp[0][i] = (dp[0][i-1] + grid[0][i]);
        
        for (int r = 1; r < m; ++r) {
            for (int c = 1; c < n; ++c) {
                val      = grid[r][c];
                dp[r][c] = Math.min(dp[r-1][c] + val, dp[r][c-1] + val);
            }
        }
        
        // for (int[] row : dp)
        //     System.out.printf("%s\n", Arrays.toString(row));
        
        return dp[m-1][n-1];
    }
}