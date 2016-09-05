import java.util.*;
import java.io.*;
// AC
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line;

		while ((line = in.readLine()) != null) {		
			StringTokenizer tokens = new StringTokenizer(line);

			int n = Integer.parseInt(tokens.nextToken());
			int m = Integer.parseInt(tokens.nextToken());

			if (n == 0 && m == 0) break;

			out.append(String.format("%s\n", solve(n, m, in)));
		}

		System.out.println(out);
	}

	private static String solve(final int n, final int m, BufferedReader input) throws Exception {
		List<Integer> order = new LinkedList<Integer>();
		Vertex[] adjacencyList = new Vertex[n+1];
		for (int i = 0; i <= n; ++i) 
			adjacencyList[i] = new Vertex(i);

		for (int i = 0; i < m; ++i) {
			StringTokenizer tokens = new StringTokenizer(input.readLine());
			int dependency = Integer.parseInt(tokens.nextToken());
			int toNode = Integer.parseInt(tokens.nextToken());

			adjacencyList[toNode].getDependencies().add(dependency);
		}

		boolean[] visited = new boolean[n+1];
		boolean[] addedToOrder = new boolean[n+1];

		// Mark all without dependencies.
		for (int i = 1; i <= n; ++i) {
			if (adjacencyList[i].getDependencies().size() == 0) {
				order.add(i);
				addedToOrder[i] = true;
				visited[i] = true;
			}
		}

		for (int i = 1; i <= n; ++i) {
			if (!visited[i] || !addedToOrder[i]) {
				dfs(i, i, adjacencyList, visited, addedToOrder, order);
			}
		}

		return formatResult(order);
	}

	private static boolean dfs(int index, int node, final Vertex[] list, boolean[] visited, boolean[] addedToOrder, List<Integer> order) {
		if (addedToOrder[node])
			return true;

		visited[node] = true;

		List<Integer> nodeDependencies = list[node].getDependencies();
		for (int i = 0; i < nodeDependencies.size(); ++i) {
			int dependency = nodeDependencies.get(i);
			boolean satisfied = false;

			if (!visited[dependency]) {
				satisfied = dfs(index+1, dependency, list, visited, addedToOrder, order);
			}
			else if (addedToOrder[dependency]) {
				satisfied = true;
			}

			if (!satisfied) {
				visited[node] = false;
				order.remove(node);

				return false;
			}
		}

		order.add(node);
		visited[node] = true;
		addedToOrder[node] = true;

		return true;
	}

	private static String formatResult(final List<Integer> results) {
		StringBuilder builder = new StringBuilder();

		builder.append(results.get(0));
		for (int i = 1; i < results.size(); ++i) {
			builder.append(" ");
			builder.append(results.get(i));
		}

		return builder.toString();
	}

	private static class Vertex {
		private int node;
		private List<Integer> dependencies = new LinkedList<Integer>();

		public Vertex(int node) {
			this.node = node;
		}

		public List<Integer> getDependencies() {
			return this.dependencies;
		}

		public int getNode() {
			return this.node;
		}
	}
}