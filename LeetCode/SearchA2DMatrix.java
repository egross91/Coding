public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix[0].length == 0)
            return false;
        
        int lowRow  = 0;
        int highRow = matrix.length-1;
        
        while (lowRow <= highRow) {
            int midRow  = getMid(lowRow, highRow);
            int lowCol  = 0;
            int highCol = matrix[midRow].length-1;
            // System.out.printf("lowRow: %d\tmidRow: %d\thighRow: %d\n", lowRow, midRow, highRow);
            
            if (matrix[midRow][lowCol] <= target && matrix[midRow][highCol] >= target) 
                return binarySearchRow(matrix[midRow], target, lowCol, highCol);
            else if (matrix[midRow][lowCol] > target)
                highRow = midRow-1;
            else // (matrix[midRow][highCol] < target)
                lowRow = midRow+1;
        }
        
        return false;
    }
    
    private boolean binarySearchRow(int[] row, int target, int low, int high) {
        while (low <= high) {
            int mid = getMid(low, high);
            // System.out.printf("mid: %d\tlow: %d\thigh: %d\n", mid, low, high);
            
            if (row[mid] == target)
                return true;
            else if (row[mid] > target)
                high = mid-1;
            else
                low = mid+1;
        }
        
        return false;
    }
    
    private int getMid(int low, int high) {
        return (low + ((high - low) >> 1));
    }
}