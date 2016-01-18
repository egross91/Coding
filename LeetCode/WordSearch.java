public class Solution {
    private static final int[] dr = { -1, 0, 1, 0 };
    private static final int[] dc = { 0, 1, 0, -1 };
    private static final int DELTA_LEN = 4;
    
    public boolean exist(char[][] board, String word) {
        if (board == null || board[0].length == 0)
            return false;
        
        for (int r = 0; r < board.length; ++r) {
            for (int c = 0; c < board[r].length; ++c) {
                if (board[r][c] == word.charAt(0)) {
                    boolean[][] visited = new boolean[board.length][board[0].length];
                    boolean found = search(board, word, visited, r, c, 1);
                    
                    if (found)
                        return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean search(final char[][] board, final String word, boolean[][] visited, int row, int col, int currChar) {
        if (currChar == word.length())
            return true;
        
        visited[row][col] = true;
        
        for (int i = 0; i < DELTA_LEN; ++i) {
            int newRow = row + dr[i];
            int newCol = col + dc[i];
            
            if (withinBounds(board.length, board[0].length, newRow, newCol) && 
                !visited[newRow][newCol] &&
                (board[newRow][newCol] == word.charAt(currChar))) {
                    
                boolean found = search(board, word, visited, newRow, newCol, currChar+1);
                if (found)
                    return true;
            }
        }
        
        visited[row][col] = false;
        return false;
    }
    
    private boolean withinBounds(int n, int m, int r, int c) {
        return ((r >= 0 && r < n) && (c >= 0 && c < m));
    }
}