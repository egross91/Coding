// AC
public class Solution {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix[0] == null || matrix.length != matrix[0].length) { return; }
        
        int inwardPtrInc = 0;
        int n            = matrix.length;
		int limit        = n >> 1;

		for (int itr = 0; itr < limit; ++itr) {
			for (int i = itr; i < n-itr-1; ++i) {
				int temp = matrix[inwardPtrInc][i];

				// System.out.printf("Top Left: %d%n", temp);
				// System.out.printf("Top Right: %d%n", matrix[i][n-inwardPtrInc-1]);
				// System.out.printf("Bottom Right: %d%n", matrix[n-inwardPtrInc-1][n-i-1]);
				// System.out.printf("Bottom Left: %d%n", matrix[n-i-1][inwardPtrInc]);

				// Top Left gets Bottom Left
				matrix[inwardPtrInc][i] = matrix[n-i-1][inwardPtrInc];

				// Bottom Left gets Bottom Right
				matrix[n-i-1][inwardPtrInc] = matrix[n-inwardPtrInc-1][n-i-1];

				// Bottom Right gets Top Right
				matrix[n-inwardPtrInc-1][n-i-1] = matrix[i][n-inwardPtrInc-1];

				// Top Right gets Top Left
				matrix[i][n-inwardPtrInc-1] = temp;
			}

			++inwardPtrInc;
		}
    }
}