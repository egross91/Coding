import java.io.*;
import java.util.*;
// AC
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader in = new BufferedReader(new FileReader("HowManyKnights.in"));
		StringBuilder out = new StringBuilder();
		// PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("HowManyKnights.out")));
		String[] lineData;

		while (true) {
			lineData = in.readLine().split(" ");
			int M = Integer.parseInt(lineData[0]);
			int N = Integer.parseInt(lineData[1]);

			if (M == 0 && N == 0) break;

			out.append(String.format("%d knights may be placed on a %d row %d column board.%n", solve(M, N), M, N));
		}

		System.out.print(out);
		// writer.print(out);
		// writer.close();
	}

	private static int solve(int M, int N) {
		if (M == 0 || N == 0)
			return 0;
		if (M == 1 || N == 1)
			return (M * N);

		int sum = M + N;
		if (M == 2 || N == 2) {
			if ((((N & 0x1) == 1) || ((M & 0x1) == 1)))
				--sum;
			else if ((sum % 4) != 0)
				sum -= 2;

			return sum;
		}

		int squares = M * N;
		if (squares <= 8) {
			return 4;
		}

		int result = (int) (Math.ceil((M * N) / 2D));

		return result;

		// return squares >> 1;
	}
}