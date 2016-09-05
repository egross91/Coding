import java.io.*;
import java.util.*;
// AC
class Main {
	private static final int NUM_PLAYERS = 2;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		int n = Integer.parseInt(in.readLine());
		String[] lineData = in.readLine().split(" ");
		int winner = 1;

		for (int i = 0; i < n; ++i) {
			int currentCycle = Integer.parseInt(lineData[i]);
			winner = solve(currentCycle, winner);

			out.append(String.format("%d%n", (winner+1)));
		}

		System.out.print(out);
	}

	private static int solve(int cycle, int lastWinner) {
		return (lastWinner + (cycle-1)) % NUM_PLAYERS;
	}
}