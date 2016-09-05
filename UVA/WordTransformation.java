import java.io.*;
import java.util.*;
// AC
class Main {
	private static final int MAX_LEN = 10;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		
		int tests = Integer.parseInt(in.readLine());
		in.readLine(); // blank line
		while (tests-- > 0) {
			List<Set<String>> wordTable = new ArrayList<Set<String>>();
			List<Transformation> transformations = new LinkedList<Transformation>();
			for (int i = 0; i <= MAX_LEN; ++i) wordTable.add(new HashSet<String>());

			String line = "";
			while ((line = in.readLine()) != null && !line.equals("*")) {
				wordTable.get(line.length()).add(line);
			}

			while ((line = in.readLine()) != null && !line.equals("")) {
				String[] lineData = line.split(" ");
				transformations.add(new Transformation(lineData[0], lineData[1]));
			}

			out.append(String.format("%s", solve(wordTable, transformations)));

			if (tests > 0) out.append("\n");
		}

		System.out.print(out);
	}

	private static String solve(List<Set<String>> wordTable, List<Transformation> transformations) {
		StringBuilder builder = new StringBuilder();

		for (Transformation transformation : transformations) {
			char[] start = transformation.start.toCharArray();
			Set<String> prunedList = wordTable.get(start.length);

			builder.append(String.format("%s%n", bfs(prunedList, start, transformation)));
		}

		return builder.toString();
	}

	private static String bfs(Set<String> candidates, char[] word, Transformation transformation) {
		Queue<State> Q = new LinkedList<State>();
		Set<String> visited = new HashSet<String>();
		int min = Integer.MAX_VALUE;
		String start = new String(word);

		Q.offer(new State(start, 0));
		visited.add(start);

		while (!Q.isEmpty()) {
			State currentState = Q.poll();
			String currentString = currentState.word;
			char[] currentWord = currentString.toCharArray();
			if (currentString.equals(transformation.finish)) min = Math.min(min, currentState.editCount);

			for (int i = 0; i < currentWord.length; ++i) {
				char currentChar = currentWord[i];

				if (currentChar != transformation.finish.charAt(i)) {
					for (int j = currentChar + 1; j != currentChar; ++j) { 
						if (j >= 123) { // wrap back to 'a'.
							j = 97;
						}

						currentWord[i] = (char) j;
						String candidateTrans = new String(currentWord);
						currentWord[i] = currentChar;

						if (candidates.contains(candidateTrans) && !visited.contains(candidateTrans)) {
							Q.offer(new State(candidateTrans, currentState.editCount + 1));
							visited.add(candidateTrans);
						}

						if (j == 97 && currentChar == 'a') break;
					}
				}
			}
		}

		return String.format("%s %s %d", transformation.start, transformation.finish, min);
	}

	private static class Transformation {
		public final String start;
		public final String finish;

		public Transformation(String s, String f) {
			this.start = s;
			this.finish = f;
		 }
	}

	private static class State {
		private final String word;
		private final int editCount;

		public State(String w, int ec) {
			this.word = w;
			this.editCount = ec;
		}
	}
}