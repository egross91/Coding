import java.io.*;
import java.util.*;
// AC
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		int tests = Integer.parseInt(in.readLine());

		for (int caze = 1; caze <= tests; ++caze) {
			String[] deck = in.readLine().split(" ");
			Deque<String> deque = new LinkedList<String>();
			for (int i = 0; i < 52; ++i)
				deque.addLast(deck[i]);

			out.append(String.format("Case %d: %s%n", caze, solve(deque)));
		}

		System.out.print(out);
	}

	private static String solve(Deque<String> pile) {
		int Y = 0;
		Deque<String> hand = new LinkedList<String>();

		for (int i = 0; i < 25; ++i) {
			hand.addFirst(pile.removeLast());
		}

		for (int i = 0; i < 3; ++i) {
			String topCard = pile.removeLast();
			int X = determineValue(topCard);
			int Z = 10 - X;
			Y += X;

			for (int j = 0; j < Z; ++j)
				pile.removeLast();
		}

		while (!hand.isEmpty())
			pile.addLast(hand.removeFirst());

		while (Y-- > 1)
			pile.removeFirst();

		return pile.removeFirst();
	}

	private static int determineValue(String card) {
		char value = card.charAt(0);

		switch (value) {
			case '2':
				return 2;
			case '3':
				return 3;
			case '4':
				return 4;
			case '5':
				return 5;
			case '6':
				return 6;
			case '7':
				return 7;
			case '8':
				return 8;
			case '9':
				return 9;
			default:
				return 10;
		}
	}
}