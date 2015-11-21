public class Solution {
    private static final int[] dc = { 0, 1, 0, -1 };
    private static final int[] dr = { -1, 0, 1, 0 };
    
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null)
            return 0;
        
        int numIslands = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        
        for (int r = 0; r < grid.length; ++r) {
            for (int c = 0; c < grid[r].length; ++c) {
                if (!visited[r][c] && grid[r][c] == '1') {
                    ++numIslands;
                    dfs(grid, visited, r, c);
                }
            }
        }
        
        return numIslands;
    }
    
    private void dfs(final char[][] grid, boolean[][] visited, int r, int c) {
        visited[r][c] = true;
        int newR, newC;
        
        for (int i = 0; i < dr.length; ++i) {
            newR = r + dr[i];
            newC = c + dc[i];
            
            if ((newR < grid.length && newR >= 0) && (newC < grid[newR].length && newC >= 0) &&
                !visited[newR][newC] && grid[newR][newC] == '1')
                dfs(grid, visited, newR, newC);
        }
    }
}