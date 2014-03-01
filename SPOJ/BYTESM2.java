import java.util.*;

class Main {
	private static Scanner in = new Scanner(System.in);
	private static int[][] matrix;
	private static int M;
	private static int N;
	public static void main(String[] args) {
		int tests = in.nextInt();
		
		for (int i = 0; i < tests; ++i) {
			matrix = buildMatrix();
			
			int most = findMaxPath();
			System.out.printf("%d\n", most);
		}
	}
	
	/** BUILD THE MATRIX FOR THE CURRENT TEST **/
	public static int[][] buildMatrix() {
		M = in.nextInt();
		N = in.nextInt();
		
		int[][] temp = new int[M][N];
		
		for (int r = 0; r < M; ++r) {
			for (int c = 0; c < N; ++c) {
				temp[r][c] = in.nextInt();
			}
		}
		
		return temp;
	}
	
	/** FIND THE MAXIMUM PATH USING RECURSION **/	
	public static int findMaxPath() {
		int temp = 0;
		for (int r = M-2; r >= 0; --r) {
			for (int c = 0; c < N; ++c) {
				for (int i = c-1; i <= c+1; ++i) {
					if (i > -1 && i < N)
						temp = max(temp,matrix[r+1][i]);
				}
				matrix[r][c] += temp;
				temp = 0;
			}
		}
		
		int pathMax = 0;
		for (int y = 0; y < N; ++y)
			pathMax = ((matrix[0][y] > pathMax) ? pathMax = matrix[0][y] : pathMax);
			
		return pathMax;
	}
	
	public static int max(int a, int b) {
		return ((a > b) ? a : b);
	}
}
