import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
// AC
public class Solution {
    private static int[][] deltas = {{-1, -1}, {-1, 0}, {-1, 1},
                                     {0, -1},           {0, 1},
                                     {1, -1},  {1, 0},  {1, 1}};
    private static int currentMax = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int grid[][] = new int[n][m];
        for(int grid_i=0; grid_i < n; grid_i++){
            for(int grid_j=0; grid_j < m; grid_j++){
                grid[grid_i][grid_j] = in.nextInt();
            }
        }
        
        System.out.println(solve(grid, n, m));
    }
    
    private static int solve(int[][] grid, int n, int m) {
        int max = Integer.MIN_VALUE;
        
        for (int r = 0; r < n; ++r) {
            for (int c = 0; c < m; ++c) {
                if (grid[r][c] == 1) {
                    currentMax = 0;                         // Reset the current max.
                    floodFill(grid, n, m, r, c, 1);         // Find a new current max.
                    max        = Math.max(max, currentMax); // Find the next possible max.
                }
            }
        }
        
        return max;
    }
    
    private static void floodFill(int[][] grid, int n, int m, int row, int col, int count) {
        grid[row][col] = 0; // Mark as visited.
        currentMax     = Math.max(count, currentMax);
        
        for (int i = 0; i < deltas.length; ++i) {
            int deltaRow = row + deltas[i][0];
            int deltaCol = col + deltas[i][1];
            
            if (isInBounds(n, m, deltaRow, deltaCol) && grid[deltaRow][deltaCol] == 1) {
                floodFill(grid, n, m, deltaRow, deltaCol, count+1);
            }
        }
        
        // Mark the cell as "not visited" because we could come to the case where we divide the graph and don't get
        // an accurate count of the largest region.
        grid[row][col] = 1;
    }
    
    private static boolean isInBounds(int n, int m, int cn, int cm) {
        return ((cn < n && cn > -1) && (cm < m && cm > -1));
    }
}
