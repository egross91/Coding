import java.io.*;
import java.util.*;
// AC
class Main {
	private static Map<String, Integer> beverageToIndex = new HashMap<String, Integer>();

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = "";
		int caze = 1; 

		while (line != null && (line = in.readLine()) != null) {
			int N = Integer.parseInt(line);
			String[] beverages = new String[N];
			beverageToIndex.clear();

			for (int i = 0; i < N; ++i) {
				beverages[i] = in.readLine();
				beverageToIndex.put(beverages[i], i);
			}

			int M = Integer.parseInt(in.readLine());
			String[] lineData = null;
			Map<String, List<String>> dependencies = new HashMap<String, List<String>>();
			for (int i = 0; i < M; ++i) {
				lineData = in.readLine().split(" ");
				String dependency = lineData[0];
				String toNode     = lineData[1];

				List<String> bucket = dependencies.get(toNode);
				if (bucket == null) {
					bucket = new LinkedList<String>();
					dependencies.put(toNode, bucket);
				}

				bucket.add(dependency);
			}

			out.append(String.format("Case #%d: Dilbert should drink beverages in this order: %s.%n%n", caze++, solve(dependencies, beverages)));
			line = in.readLine();
		}

		System.out.print(out);
	}

	private static String solve(Map<String, List<String>> dependencies, final String[] beverages) {
		final int N = beverages.length;

		for (int i = 0; i < N; ++i) {
			List<String> deps = dependencies.get(beverages[i]);

			if (deps == null) {
				boolean[] visited = new boolean[N];
				Deque<String> stack = new LinkedList<String>();

				dfs(i, dependencies, visited, stack, beverages);

				if (stack.size() == N) 
					return formatResult(stack);
			}
		}

		return "Quit at life.";
	}

	private static boolean dfs(int currentNode, Map<String, List<String>> allDependencies, boolean[] visited, 
							   Deque<String> stack, final String[] nodes) {
		String candBeverage = nodes[currentNode];
		if (stack.contains(candBeverage))
			return true;

		visited[currentNode] = true;
		List<String> candDeps = allDependencies.get(candBeverage);
		if (candDeps != null) {
			for (int i = 0; i < candDeps.size(); ++i) {
				String dependency = candDeps.get(i);

				if (!stack.contains(dependency)) {
					visited[currentNode] = false;
					return false;
				}
			}
		}

		stack.addLast(candBeverage);

		for (int i = 0; i < nodes.length; ++i) {
			if (!visited[i]) 
				dfs(i, allDependencies, visited, stack, nodes);

			if (stack.size() == nodes.length)
				return true;
		}

		stack.removeLast();
		visited[currentNode] = false;
		return true;
	}

	private static String formatResult(Deque<String> order) {
		StringBuilder result = new StringBuilder();

		result.append(order.removeFirst());

		while (!order.isEmpty()) 
			result.append(String.format(" %s", order.removeFirst()));

		return result.toString();
	}

	private static int convertBeverageToIndex(final String beverage) {
		return beverageToIndex.get(beverage);
	}
}