import java.io.*;
import java.util.*;
// AC
class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader in = new BufferedReader(new FileReader("HangmanJudge.in"));
		StringBuilder out = new StringBuilder();
		// PrintWriter wr	iter = new PrintWriter(new BufferedWriter(new FileWriter("HangmanJudge.out")));

		while (true) {
			int round = Integer.parseInt(in.readLine());
			if (round <= 0) break;

			char[] word = in.readLine().trim().toCharArray();
			char[] guesses = in.readLine().trim().toCharArray();

			out.append(String.format("Round %d%n%s%n", round, solve(word, guesses)));
		}

		System.out.print(out);
		// writer.print(out);
		// writer.close();
	}

	private static String solve(char[] word, char[] guesses) {
		Set<Character> guessed = new HashSet<Character>();
		int wrongGuesses = 0;

		for (int i = 0; i < guesses.length; ++i) {
			char guess = guesses[i];

			if (!guessed.contains(guess)) {
				boolean found = false;

				for (int k = 0; k < word.length; ++k) {
					if (word[k] == guess) {
						found = true;
						word[k] = '-';
					}
				}

				if (won(word)) {
					return "You win.";
				}

				if (!found) {
					if (++wrongGuesses == 7) {
						return "You lose.";
					}
				}
			}

			guessed.add(guess);
		}

		if (won(word)) {
			return "You win.";
		}
		else {
			return "You chickened out.";
		}
	}

	private static boolean won(char[] word) {
		for (int i = 0; i < word.length; ++i) {
			if (word[i] != '-') {
				return false;
			}
		}

		return true;
	}
}