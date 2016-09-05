import java.util.*;
import java.io.*;
// AC
@SuppressWarnings("unchecked")
class Main {
	private static Map<Character, Integer> charToIndex;
	private static Map<Integer, Character> indexToChar;
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		int tests = Integer.parseInt(in.readLine());

		while (tests-- > 0) {
			in.readLine(); // Get rid of blank line.

			String[] lineData                  = in.readLine().split(" ");
			List<List<Character>> dependencies = new LinkedList<List<Character>>();

			charToIndex = null;
			indexToChar = null;
			charToIndex = new HashMap<Character, Integer>();
			indexToChar = new HashMap<Integer, Character>();

			for (int i = 0; i < lineData.length; ++i) {
				charToIndex.put(lineData[i].charAt(0), i);
				indexToChar.put(i, lineData[i].charAt(0));
				dependencies.add(new LinkedList<Character>());
			}

			lineData = in.readLine().split(" ");
			for (int i = 0; i < lineData.length; ++i) {
				char dependency = lineData[i].charAt(0);
				char toNode     = lineData[i].charAt(2);

				dependencies.get(convertCharToInt(toNode)).add(dependency);
			}

			out.append(solve(dependencies));
			if (tests != 0)
				out.append("\n");
		}

		System.out.print(out);
	}

	private static String solve(List<List<Character>> dependencies) {
		N                                  = dependencies.size();
		List<List<Character>> permutations = new LinkedList<List<Character>>();

		for (int i = 0; i < N; ++i) {
			List<Character> nodeDeps = dependencies.get(i);

			if (nodeDeps.size() == 0) {
				boolean[] visited = new boolean[N];

				dfs(i, dependencies, visited, new LinkedList<Character>(), permutations);
			}
		}

		if (permutations.size() == 0) 
			return "NO\n";

		StringBuilder builder = new StringBuilder();

		for (List<Character> perm : permutations)
			builder.append(String.format("%s%n", formatResult(perm)));

		return builder.toString();
	}

	private static boolean dfs(int currentCand, List<List<Character>> dependencies, boolean[] visited, 
							   List<Character> permutation, List<List<Character>> allPermutations) {
		Character currentCandChar = convertIntToChar(currentCand);
		if (permutation.contains(currentCandChar))
			return true;

		visited[currentCand] = true;

		List<Character> candDeps = dependencies.get(currentCand);
		for (int i = 0; i < candDeps.size(); ++i) {
			Character currentDepChar = candDeps.get(i);

			if (!permutation.contains(currentDepChar)) {
				visited[currentCand] = false;
				return false;
			}
		}

		// We made it through all of the dependencies for the candidate, it's safe to add.
		permutation.add(currentCandChar);

		if (permutation.size() == N && !allPermutations.contains(permutation))
			allPermutations.add(new LinkedList(permutation));
		else {
			for (int i = 0; i < N; ++i) {
				Character otherChar = convertIntToChar(i);
				if (!visited[i])
					dfs(i, dependencies, visited, permutation, allPermutations);
			}

			if (permutation.size() == N && !allPermutations.contains(permutation))
				allPermutations.add(new LinkedList(permutation));
		}

		permutation.remove(currentCandChar);
		visited[currentCand] = false;
		return true;
	}

	private static String formatResult(final List<Character> list) {
		StringBuilder result = new StringBuilder();

		result.append(list.get(0));
		for (int i = 1; i < list.size(); ++i)
			result.append(String.format(" %c", list.get(i))); 

		return result.toString();
	}

	private static int convertCharToInt(char c) {
		return charToIndex.get(c);
	}

	private static Character convertIntToChar(int i) {
		return indexToChar.get(i);
	}
}