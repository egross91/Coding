import java.io.*;
import java.util.*;
// AC
class Main {
	private static int[][] knightMoves = {{1, 2}, {1, -2}, {-1, -2}, {-1, 2}, {2, 1}, {2, -1}, {-2, -1}, {-2, 1}};
	private static int[][] kingMoves = {{-1, -1}, {-1, 0}, {-1, 1}, 
										{0, 1}, {0, -1}, 
										{1, -1}, {1, 0}, {1, 1}};

	private static int N;
	private static int M;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader in = new BufferedReader(new FileReader("Chess.in"));
		StringBuilder out = new StringBuilder();
		// PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("Chess.out")));

		int t = Integer.parseInt(in.readLine());

		while (true) {
			String line = in.readLine();
			if (line.equals("")) continue;

			String[] lineData = line.split(" ");
			char piece = lineData[0].charAt(0);
			M = Integer.parseInt(lineData[1]);
			N = Integer.parseInt(lineData[2]);

			out.append(String.format("%d%n", solve(piece, M, N)));

			if (--t <= 0) break;
		}

		System.out.print(out);
	// 	writer.print(out);
	// 	writer.close();
	}

	private static int solve(final char piece, int M, int N) {
		boolean[][] board = new boolean[M][N];

		switch (piece) {
			case 'r':
				return solveForRook(0, 0, board, 0, 0);
			case 'k':
				return solveForKnight(M, N);
			case 'Q':
				return solveForQueen(M, N);
			case 'K':
				return solveForKing(M, N);
			default:
				return -1;
		}
	}

	private static int solveForRook(int row, int col, boolean[][] board, int count, int max) {
		max = Math.max(count, max);

		for (int r = row; r < M; ++r) {
			for (int c = col; c < N; ++c) {
				if (!board[r][c]) {
					// Check if we can place a piece here.
					boolean canPlace = true;
					// Check rows.
					for (int i = 0; i < M; ++i) {
						if (i != r && board[i][c]) {
							canPlace = false;
							break;
						}
					}

					if (canPlace) {
						for (int i = 0; i < N; ++i) {
							if (i != c && board[r][i]) {
								canPlace = false;
								break;
							}
						}

						if (canPlace) {
							board[r][c] = true;
							int depthCount = solveForRook(r, c, board, count+1, max);
							max = Math.max(depthCount, max);
							board[r][c] = false;
						}
					}
				}
			}
		}

		return max;
	}

	private static int solveForKnight(double M, double N) {
		int result = (int) (Math.ceil((M * N) / 2d));

		return result;
	}

	private static int solveForQueen(int M, int N) {
		return Math.min(M, N);
	}

	private static int solveForKing(double M, double N) {
		int leftCeil = (int) Math.ceil(M / 2d);
		int rightCeil = (int) Math.ceil(N / 2d);

		int left = Math.max(1, leftCeil);
		int right = Math.max(1, rightCeil);

		return (left * right);
	}

	private static boolean inBounds(int row, int col, final boolean[][] board) {
		return ((row > -1 && row < M) && (col > -1 && col < N));
	}
}