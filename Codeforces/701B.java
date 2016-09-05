import java.io.*;
import java.util.*;
// AC
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		String[] lineData      = in.readLine().split(" ");
		int N                  = Integer.parseInt(lineData[0]);
		int M                  = Integer.parseInt(lineData[1]);
		long longN             = (long) N;
		long unattackedCells   = (longN * longN);
		int rowsTouched        = 0;
		int colsTouched        = 0;
		boolean[] colIsTouched = new boolean[N+1];
		boolean[] rowIsTouched = new boolean[N+1];

		for (int i = 0; i < M; ++i) {
			lineData = null;
			lineData = in.readLine().split(" ");
			int row = Integer.parseInt(lineData[0]);
			int col = Integer.parseInt(lineData[1]);

			if (!rowIsTouched[row] && !colIsTouched[col]) {
				unattackedCells -= ((N * 2) - colsTouched - rowsTouched - 1);
				rowIsTouched[row] = true;
				colIsTouched[col] = true;
				++rowsTouched; ++colsTouched;
			}
			else if (!rowIsTouched[row]) {
				unattackedCells -= (N - colsTouched);
				rowIsTouched[row] = true;
				++rowsTouched;
			}
			else if (!colIsTouched[col]) {
				unattackedCells	-= (N - rowsTouched);
				colIsTouched[col] = true;
				++colsTouched;
			}

			unattackedCells = Math.max(0, unattackedCells);
			out.append(String.format("%d ", unattackedCells));
		}

		System.out.print(out);
	}
}