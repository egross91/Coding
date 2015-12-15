public class Solution {
    private static int DIMENSION = 9;
    
    public boolean isValidSudoku(char[][] board) {
        // Board validation.
        if (board == null)
            return false;
        
        // Check possibilities.
        boolean rowsOk = checkRows(board);
        boolean colsOk = checkCols(board);
        boolean boxzOk = checkBoxes(board);
        
        return (rowsOk && colsOk && boxzOk);
    }
    
    private boolean checkRows(final char[][] board) {
        for (int r = 0; r < DIMENSION; ++r) {
            // Board validation.
            if (board[r] == null)
                return false;
            
            // Check to see that we only see each value [1-9] only once.
            boolean[] seen = new boolean[10];
            for (int c = 0; c < DIMENSION; ++c) {
                int value = charToInt(board[r][c]);
                
                if (value == -1)
                    continue;
                if (seen[value])
                    return false;
                
                seen[value] = true;
            }
        }
        
        return true;
    }
    
    private boolean checkCols(final char[][] board) {
        for (int c = 0; c < DIMENSION; ++c) {
            boolean[] seen = new boolean[10];
            
            for (int r = 0; r < DIMENSION; ++r) {
                int value = charToInt(board[r][c]);
                
                if (value == -1)
                    continue;
                if (seen[value])
                    return false;
                
                seen[value] = true;
            }
        }
        
        return true;
    }
    
    private boolean checkBoxes(final char[][] board) {
        // Iterate through the possible 3x3 boxes.
        for (int r = 0; r < DIMENSION; r += 3) {
            for (int c = 0; c < DIMENSION; c += 3) {
                boolean[] seen = new boolean[10];
                
                // Iterate within the 3x3 box.
                for (int sr = r; sr < r + 3; ++sr) {
                    for (int sc = c; sc < c + 3; ++sc) {
                        int value = charToInt(board[sr][sc]);
                        
                        if (value == -1)
                            continue;
                        if (seen[value])
                            return false;
                            
                        seen[value] = true;
                    }
                }
            }
        }
        
        return true;
    }
    
    private int charToInt(char c) {
        if (c == '.')
            return -1;
        
        return (c - '0');
    }
}