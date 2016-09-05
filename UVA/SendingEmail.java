import java.io.*;
import java.util.*;
// AC
class Main {
	private static final int MAX = 2_000_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		int tests = Integer.parseInt(in.readLine());

		for (int caze = 1; caze <= tests; ++caze) {
			String[] lineData = in.readLine().split(" ");
			int n = Integer.parseInt(lineData[0]);
			int m = Integer.parseInt(lineData[1]);
			int S = Integer.parseInt(lineData[2]);
			int T = Integer.parseInt(lineData[3]);

			List<Vertex> graph = new ArrayList<Vertex>();
			for (int i = 0; i < n; ++i) graph.add(new Vertex(i));

			for (int i = 0; i < m; ++i) {
				lineData     = in.readLine().split(" ");
				int fromNode = Integer.parseInt(lineData[0]);
				int toNode   = Integer.parseInt(lineData[1]);
				int cost     = Integer.parseInt(lineData[2]);

				graph.get(fromNode).addNeighbor(new Edge(toNode, cost));
				graph.get(toNode).addNeighbor(new Edge(fromNode, cost));
			}

			out.append(String.format("Case #%d: %s%n", caze, solve(S, T, graph)));
		}

		System.out.print(out);
	}

	private static String solve(final int start, final int end, final List<Vertex> graph) {
		final int N       = graph.size();
		boolean[] visited = new boolean[N];

		graph.get(start).setTotalCost(0);

		PriorityQueue<Vertex> heap = new PriorityQueue<Vertex>(N, new Comparator<Vertex>() {
			@Override
			public int compare(Vertex v1, Vertex v2) {
				if (v1.getTotalCost() < v2.getTotalCost()) 
					return -1;
				else if (v1.getTotalCost() > v2.getTotalCost())
					return 1;

				return 0;
			}
		});
		heap.offer(graph.get(start));

		while (!heap.isEmpty()) {
			Vertex currentVertex = heap.poll();
			if (currentVertex.node == end) break;

			List<Edge> neighbors = currentVertex.getNeighbors();
			int currentTotalCost = currentVertex.getTotalCost();

			for (int i = 0; i < neighbors.size(); ++i) {
				Edge neighborEdge      = neighbors.get(i);
				Vertex neighborVertex  = graph.get(neighborEdge.toNode);
				int neighborVertexCost = neighborVertex.getTotalCost();
				int candidateCost      = currentTotalCost + neighborEdge.cost;

				if (neighborVertexCost > candidateCost) {
					heap.remove(neighborVertex);
					neighborVertex.setTotalCost(candidateCost);
					heap.offer(neighborVertex);
				}
			}
		}

		if (graph.get(end).getTotalCost() == MAX)
			return "unreachable";

		return String.valueOf(graph.get(end).getTotalCost());
	}

	private static class Vertex {
		private List<Edge> neighbors = new ArrayList<Edge>();
		private int node;
		private int totalCost;

		public Vertex(int n) {
			this(n, MAX);
		}

		private Vertex(int n, int tc) {
			this.node = n;
			this.totalCost = tc;
		}

		public int getTotalCost() {
			return this.totalCost;
		}

		public void setTotalCost(int tc) {
			this.totalCost = tc;
		}

		public void addNeighbor(Edge v) {
			neighbors.add(v);
		}

		public List<Edge> getNeighbors() {
			return this.neighbors;
		}
	}

	private static class Edge {
		final public int toNode;
		final public int cost;

		private Edge() {
			this.toNode = -1;
			this.cost = -1;
		}

		public Edge(int tn, int c) {
			this.toNode = tn;
			this.cost = c;
		}
	}
}