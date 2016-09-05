import java.io.*;
import java.util.*;
// AC
class Main {
  private static Coord ZERO = null;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		
    int N = Integer.parseInt(in.readLine());
    long[][] square = new long[N][N];
    for (int i = 0; i < N; ++i) {
      String line = in.readLine();
      String[] lineData = line.split(" ");

      for (int j = 0; j < N; ++j) {
        square[i][j] = Long.parseLong(lineData[j]);

        if (square[i][j] == 0L) {
          ZERO = new Coord(i, j);
        }
      }
    }

    System.out.println(solve(square, N));
	}

  private static long solve(long[][] square, int N) {
    Set<Long> sums = new HashSet<Long>();

    long goalDiff = getGoalDiff(square, sums, N);
    if (goalDiff <= 0) return -1;

    square[ZERO.row][ZERO.col] = goalDiff;

    if (getGoalDiff(square, sums, N) >= 0) {
      return goalDiff;
    }

    return -1;
  }

  private static long getGoalDiff(long[][] square, Set<Long> sums, int N) {
    long goalSum = 0;

    // Check rows.
    for (int r = 0; r < N; ++r) {
      long sum = 0;

      if (r == ZERO.row) continue;

      for (int c = 0; c < N; ++c) {
        sum += square[r][c];
      }

      sums.add(sum);
      goalSum = sum;
    }

    if (sums.size() > 1) return -1;

    // Check cols.
    for (int c = 0; c < N; ++c) {
      long sum = 0;

      if (c == ZERO.col) continue;

      for (int r = 0; r < N; ++r) {
        sum += square[r][c];
      }

      sums.add(sum);
      goalSum = sum;
    }

    if (sums.size() > 1) return -1;

    // Check main diagonal.
    long rcSum = 0;
    boolean toAdd = true;
    for (int rc = 0; rc < N; ++rc) {
      if (square[rc][rc] == 0) {
        toAdd = false;
        break;
      }
      else {
        rcSum += square[rc][rc];
      }
    }

    if (toAdd) {
      sums.add(rcSum);
      if (sums.size() > 1) return -1;
      goalSum = rcSum;
    }

    // Check anti-diagonal.
    toAdd = true;
    rcSum = 0;
    for (int r = N-1, c = 0; r >= 0 && c < N; --r, ++c) {
      if (square[r][c] == 0) {
        toAdd = false;
        break;
      }
      else {
        rcSum += square[r][c];
      }
    }

    if (toAdd) {
      sums.add(rcSum);
      if (sums.size() > 1) return -1;
      goalSum = rcSum;
    }

    // Get sum of row with ZERO.
    long sum = 0;
    for (int c = 0; c < N; ++c) {
      sum += square[ZERO.row][c];
    }

    long goalDiff = goalSum - sum;

    if (sums.size() == 0) return 1;

    // if (goalDiff != 0 && secondPass) return -1;

    return goalDiff;
  }

  private static class Coord {
    public final int row;
    public final int col;

    public Coord(int r, int c) {
      this.row = r;
      this.col = c;
    }

    @Override
    public String toString() {
      return String.format("%d %d", row, col);
    }
  }
}