import java.io.*;
import java.util.*;
// AC
class Main {
    private static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		
        int T = Integer.parseInt(in.readLine());

        while (T-- > 0) {
            String[] lineData = in.readLine().split(" ");
            N = Integer.parseInt(lineData[0]);
            M = Integer.parseInt(lineData[1]);

			List<Edge> edges = new ArrayList<Edge>();

			for (int i = 0; i < M; ++i) {
				lineData = null; lineData = in.readLine().split(" ");

				int from = Integer.parseInt(lineData[0]);
				int to   = Integer.parseInt(lineData[1]);
				int cost = Integer.parseInt(lineData[2]);

				edges.add(new Edge(from, to, cost));
			}

			out.append(String.format("%s%n", solve(N, edges)));
        }

        System.out.println(out);
	}

	private static String solve(int N, List<Edge> edges) {
		Pair best = new Pair();
		Set<Set<String>> edgesUsed = new HashSet<Set<String>>();

		edges.sort(Edge.comparator());

		for (Edge edge : edges) {
			best.add(findMinimumSpanningTree(N, edges, edge, edgesUsed));
		}

		return best.toString();
	}

	private static int findMinimumSpanningTree(int N, List<Edge> edges, Edge start, Set<Set<String>> edgesUsed) {
		UnionFind uf = new UnionFind(N);
		Set<String> used = new HashSet<String>();
		int sum = start.cost;

		uf.merge(start);
		used.add(start.toString());

		for (int i = 0; i < edges.size() && !uf.done(); ++i) {
			Edge edge = edges.get(i);
			if (edge == start) { continue; }

			if (uf.merge(edge)) {
				sum += edge.cost;
				used.add(edge.toString());
			}
		}
		
		if (!edgesUsed.add(used)) {
			sum = Integer.MAX_VALUE;
		}

		return sum;
	}

	private static class Edge {
		public final int from, to, cost;

		public Edge(int f, int t, int c) {
			this.from = f;
			this.to = t;
			this.cost = c;
		}

		public static Comparator<Edge> comparator() {
			return new Comparator<Edge>() {
				@Override
				public int compare(Edge e1, Edge e2) {
					// Desc. sort.
					return e1.cost - e2.cost;
				}
			};
		}

		@Override
		public String toString() {
			return String.format("%d %d", this.from, this.to);
		}
	}

	private static class Pair {
		private int best1, best2;

		public Pair() {
			this.best1 = Integer.MAX_VALUE;
			this.best2 = Integer.MAX_VALUE;
		}

		public void add(int val) {
			if (val <= this.best1) {
				this.best2 = this.best1;
				this.best1 = val;
			} else if (val <= this.best2) {
				this.best2 = val;
			}
		}

		@Override
		public String toString() {
			return String.format("%d %d", this.best1, this.best2);
		}
	}

	private static class UnionFind {
		private int[] parents;
		private int subgraphs;

		public UnionFind(int n) {
			this.parents = new int[n+1];
			this.subgraphs = n;
		}

		private int find(int x) {
			if (this.parents[x] == 0) {
				return x;
			}

			return this.parents[x] = find(this.parents[x]);
		}

		public boolean merge(Edge e) {
			int pX = find(e.from);
			int pY = find(e.to);

			if (pX == pY) {
				return false;
			}

			this.subgraphs--;
			this.parents[pX] = pY;

			return true;
		}

		public boolean done() {
			return (this.subgraphs == 1);
		}
	}
}