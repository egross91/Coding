import java.io.*;
import java.util.*;
// AC
class Main {
	private static int[][] knightMoves = {{1, 2}, {1, -2}, {-1, 2}, {-1, -2},
										  {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
	private static int[][] kingMoves = {{-1, -1}, {-1, 0}, {-1, 1},
										{0, -1}, {0, 1},
										{1, -1}, {1, 0}, {1, 1}};

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader in = new BufferedReader(new FileReader("Chess.in"));
		StringBuilder out = new StringBuilder();
		// PrintWriter writer = new PrintWriter(new FileWriter("Chess.out"));

		String line = "";

		while ((line = in.readLine()) != null) {
			String[] rowData = line.split("/");

			out.append(String.format("%d%n", solve(rowData)));
		}

		System.out.print(out);
		// writer.print(out);
		// writer.close();
	}

	private static int solve(final String[] rows) {
		char[][] board = new char[8][8];

		for (int i = 0; i < 8; ++i) {
			// u == unattacked
			Arrays.fill(board[i], 'u');
		}

		// Fill the board with the pieces.
		for (int r = 0; r < 8; ++r) {
			String row = rows[r];

			int c = 0;
			for (int cPtr = 0; cPtr < 8; ++cPtr) {
				char square = row.charAt(c++);

				if (Character.isDigit(square)) {
					int value = (square - '0');
					cPtr += (value - 1);
				}
				else {
					board[r][cPtr] = square;
				}
			}
		}

		// Play the pieces possible moves.
		markAttackSpaces(board);
		// printBoard(board);

		return countUnattackedSquares(board);
	}

	private static void markAttackSpaces(char[][] board) {
		for (int r = 0; r < 8; ++r) {
			for (int c = 0; c < 8; ++c) {
				char square = board[r][c];

				if (square != 'a' || square != 'u') {
					playPiece(r, c, board);
				}
			}
		}
	}

	private static void playPiece(int row, int col, char[][] board) {
		char piece = board[row][col];

		if (piece == 'r' || piece == 'R') {
			// Rook.
			playRookPiece(row, col, board);
		}
		else if (piece == 'n' || piece == 'N') {
			// Knight.
			playKnightPiece(row, col, board);
		}
		else if (piece == 'b' || piece == 'B') {
			// Bishop.
			playBishopPiece(row, col, board);
		}
		else if (piece == 'q' || piece == 'Q') {
			// Queen.
			playQueenPiece(row, col, board);
		}
		else if (piece == 'k' || piece == 'K') {
			// King.
			playKingPiece(row, col, board);
		}
		else if (piece == 'p' || piece == 'P') {
			// Pawn.
			playPawnPiece(row, col, board);
		}
	}

	private static void playRookPiece(int row, int col, char[][] board) {
		for (int c = col+1; c < 8; ++c) {
			if (isAttacked(row, c, board)) {
				continue;
			}
			else if (canAttack(row, c, board)) {
				board[row][c] = 'a';
			}
			else {
				break;
			}
		}

		for (int c = col-1; c >= 0; --c) {
			if (isAttacked(row, c, board)) {
				continue;
			}
			else if (canAttack(row, c, board)) {
				board[row][c] = 'a';
			}
			else {
				break;
			}
		}

		for (int r = row+1; r < 8; ++r) {
			if (isAttacked(r, col, board)) {
				continue;
			}
			else if (canAttack(r, col, board)) {
				board[r][col] = 'a';
			}
			else {
				break;
			}
		}

		for (int r = row-1; r >= 0; --r) {
			if (isAttacked(r, col, board)) {
				continue;
			}
			else if (canAttack(r, col, board)) {
				board[r][col] = 'a';
			}
			else {
				break;
			}
		}
	}

	private static void playKnightPiece(int row, int col, char[][] board) {
		for (int i = 0; i < knightMoves.length; ++i) {
			int dr = row + knightMoves[i][0];
			int dc = col + knightMoves[i][1];

			if (isInBounds(dr, dc) && canAttack(dr, dc, board)) {
				board[dr][dc] = 'a';
			}
		}
	}

	private static void playBishopPiece(int row, int col, char[][] board) {
		for (int i = 1; i < 8; ++i) {
			int dr = row + i;
			int dc = col + i;

			if (isInBounds(dr, dc)) {
				if (canAttack(dr, dc, board)) {
					board[dr][dc] = 'a';
				}
				else if (!isAttacked(dr, dc, board)) {
					break;
				}
			}
			else {
				break;
			}
		}

		for (int i = 1; i < 8; ++i) {
			int dr = row + i;
			int dc = col - i;

			if (isInBounds(dr, dc)) {
				if (canAttack(dr, dc, board)) {
					board[dr][dc] = 'a';
				}
				else if (!isAttacked(dr, dc, board)) {
					break;
				}
			}
			else {
				break;
			}
		}

		for (int i = 1; i < 8; ++i) {
			int dr = row - i;
			int dc = col + i;

			if (isInBounds(dr, dc)) {
				if (canAttack(dr, dc, board)) {
					board[dr][dc] = 'a';
				}
				else if (!isAttacked(dr, dc, board)) {
					break;
				}
			}
			else {
				break;
			}
		}

		for (int i = 1; i < 8; ++i) {
			int dr = row - i;
			int dc = col - i;

			if (isInBounds(dr, dc)) {
				if (canAttack(dr, dc, board)) {
					board[dr][dc] = 'a';
				}
				else if (!isAttacked(dr, dc, board)) {
					break;
				}
			}
			else {
				break;
			}
		}
	}

	private static void playQueenPiece(int row, int col, char[][] board) {
		playRookPiece(row, col, board);
		playBishopPiece(row, col, board);
	}

	private static void playKingPiece(int row, int col, char[][] board) {
		for (int i = 0; i < kingMoves.length; ++i) {
			int dr = row + kingMoves[i][0];
			int dc = col + kingMoves[i][1];

			if (isInBounds(dr, dc) && canAttack(dr, dc, board)) {
				board[dr][dc] = 'a';
			}
		}
	}

	private static void playPawnPiece(int row, int col, char[][] board) {
		int candidateRow;
		char piece = board[row][col];

		if (Character.isUpperCase(piece)) {
			candidateRow = row - 1;
		}
		else {
			candidateRow = row + 1;
		}

		if (isInBounds(candidateRow, col-1) && canAttack(candidateRow, col-1, board)) {
			board[candidateRow][col-1] = 'a';
		}
		if (isInBounds(candidateRow, col+1) && canAttack(candidateRow, col+1, board)) {
			board[candidateRow][col+1] = 'a';
		}
	}

	private static int countUnattackedSquares(final char[][] board) {
		int count = 0;

		for (int r = 0; r < 8; ++r) {
			for (int c = 0; c < 8; ++c) {
				if (canAttack(r, c, board)) {
					++count;
				}
			}
		}

		return count;
	}

	private static void printBoard(char[][] board) {
		for (int i = 0; i < board.length; ++i)
			System.out.println(Arrays.toString(board[i]));
		System.out.println();
	}

	private static boolean canAttack(int row, int col, final char[][] board) {
		return (board[row][col] == 'u');
	}

	private static boolean isAttacked(int row, int col, final char[][] board) {
		return (board[row][col] == 'a');
	}

	private static boolean isInBounds(int row, int col) {
		return ((row > -1 && row < 8) && (col > -1 && col < 8));
	}
}