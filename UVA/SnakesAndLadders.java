import java.io.*;
import java.util.*;
// AC
class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader in = new BufferedReader(new FileReader("SnakesAndLadders.in"));
		StringBuilder out = new StringBuilder();
		// PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("SnakesAndLadders.out")));

		int tests = Integer.parseInt(in.readLine().trim());
		while (true) {
			String[] lineData = in.readLine().replaceAll("\\s+", " ").trim().split(" ");
			if (lineData.length < 3) continue;

			int players = Integer.parseInt(lineData[0]);
			long portals = Long.parseLong(lineData[1]);
			int rolls = Integer.parseInt(lineData[2]);

			int[] warps = new int[105];
			Arrays.fill(warps, -1);
			for (long i = 0; i < portals; ++i) {
				lineData = in.readLine().replaceAll("\\s+", " ").trim().split(" ");
				int from = (int) Math.min(100L, Long.parseLong(lineData[0]));
				int to   = (int) Math.min(100L, Long.parseLong(lineData[1]));

				warps[from] = to;
			}

			int[] positions = new int[1_000_002];
			Arrays.fill(positions, 1);

			boolean done = false;
			for (int i = 0; i < rolls; ++i) {
				if (!done) {
					int player = (i % players);
					int playerPosition = positions[player];
					int dieRoll = Integer.parseInt(in.readLine().trim());

					playerPosition += dieRoll;
					playerPosition = Math.min(playerPosition, 100);

					if (warps[playerPosition] != -1) {
						playerPosition = warps[playerPosition];
					}

					positions[player] = playerPosition;
					if (playerPosition == 100) {
						done = true;
					}
				}
				else {
					in.readLine();
				}
			}	

			for (int i = 0; i < players; ++i) {
				out.append(String.format("Position of player %d is %d.%n", (i+1), positions[i]));
			}

			if (--tests == 0) break;
		}

		System.out.print(out);
		// writer.print(out);
		// writer.close();
	}
}