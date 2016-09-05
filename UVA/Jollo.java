import java.io.*;
import java.util.*;
// AC
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		while (true) {
			String[] lineData = in.readLine().split(" ");
			int A = Integer.parseInt(lineData[0]);
			int B = Integer.parseInt(lineData[1]);
			int C = Integer.parseInt(lineData[2]);
			int X = Integer.parseInt(lineData[3]);
			int Y = Integer.parseInt(lineData[4]);

			if (A == 0 && B == 0 && C == 0 && X == 0 && Y == 0) break;

			Set<Integer> deck = getDeck();
			
			// If the princess can win, pick the lowest card she can to win.
			// If she can only lose, pick the lowest card she can.
			// Once there's only one card left, see if the prince can possibly win.
			  // He can't win he hasn't won a hand until now.
			List<Integer> princess = new LinkedList<Integer>();
			List<Integer> prince = new LinkedList<Integer>();
			princess.add(A); princess.add(B); princess.add(C);
			prince.add(X); prince.add(Y);

			// Sort desc.
			Collections.sort(prince, new Comparator<Integer>() {
				@Override
				public int compare(Integer lhs, Integer rhs) {
					return rhs - lhs;
				}
			});

			int princeWins = 0;
			for (Integer currentPrince : prince) {
				boolean princessCanWin = false;

				for (Integer currentPrincess : princess) {
					if (currentPrincess > currentPrince) {
						princessCanWin = true;
					}
				}

				if (princessCanWin) {
					Integer winner = 53;

					for (Integer currentPrincess : princess) {
						if (currentPrincess > currentPrince && 
							currentPrincess < winner) {
							winner = currentPrincess;
						}
					}

					deck.remove(winner);
					princess.remove(winner);
				}
				else {
					Integer minimum = 53;

					for (Integer currentPrincess : princess) {
						if (currentPrincess < minimum) {
							minimum = currentPrincess;
						}
					}

					deck.remove(minimum);
					princess.remove(minimum);
					++princeWins;
				}

				deck.remove(currentPrince);
			}

			deck.removeAll(princess);

			if (princeWins == 0) {
				out.append(-1);
			}
			else if (princeWins == 2) {
				for (int i = 1; i <= 52; ++i) {
					if (deck.contains(i)) {
						out.append(i);
						break;
					}
				}
			}
			else {
				Integer princessLastMove = princess.get(0);
				boolean princeCanWin = false;
				deck.removeAll(princess);
				deck.removeAll(prince);

				int i;
				for (i = princessLastMove+1; i <= 52; ++i) {
					if (deck.contains(i)) {
						princeCanWin = true;
						break;
					}
				}

				if (!princeCanWin) {
					out.append(-1);
				}
				else {
					out.append(i);
				}
			}

			out.append("\n");
		}

		System.out.print(out);
	}

	private static Set<Integer> getDeck() {
		Set<Integer> deck = new HashSet<Integer>();

		for (int i = 1; i <= 52; ++i)
			deck.add(i);

		return deck;
	}
}