// AC
public class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) { return; }
        
        fillMatrix(matrix, matrix.length, matrix[0].length, 0, 0);
    }
    
    private void fillMatrix(int[][] matrix, int m, int n, int cRow, int cCol) {
        if (cRow < m && cCol < n) {
            for (int c = cCol; c < n; ++c) {
                if (matrix[cRow][c] == 0) {
                    // System.out.printf("(%d, %d)%n", cRow, c);
                    fillMatrix(matrix, m, n, cRow, c+1);
                    zeroRowAndCol(matrix, cRow, c);
                    return;
                }
            }
            
            // System.out.printf("NF(%d, %d)%n", cRow, cCol);
            
            fillMatrix(matrix, m, n, cRow+1, 0);
        } else if (cRow < m) {
            // System.out.printf("OB(%d, %d)%n", cRow, cCol);
            fillMatrix(matrix, m, n, cRow+1, 0);
        }
    }
    
    private void zeroRowAndCol(int[][] matrix, int row, int col) {
        for (int c = 0; c < matrix[0].length; ++c) {
            matrix[row][c] = 0;
        }
        
        for (int r = 0; r < matrix.length; ++r) {
            matrix[r][col] = 0;
        }
    }
}