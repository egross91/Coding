import java.io.*;
import java.util.*;
// AC
class Main {
	private static final int INF = 2_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		
		int network = 1;

		while (true) {
			int N = Integer.parseInt(in.readLine());
			if (N == 0) break;

			List<Vertex> graph = new ArrayList<Vertex>();
			for (int i = 0; i <= N; ++i) graph.add(new Vertex(i));

			String[] lineData = in.readLine().split(" ");
			int S = Integer.parseInt(lineData[0]);
			int D = Integer.parseInt(lineData[1]);
			int C = Integer.parseInt(lineData[2]);

			for (int i = 0; i < C; ++i) {
				lineData = in.readLine().split(" ");

				int to = Integer.parseInt(lineData[0]);
				int from = Integer.parseInt(lineData[1]);
				int cost = Integer.parseInt(lineData[2]);

				graph.get(to).addNeighbor(new Edge(to, from, cost));
				graph.get(from).addNeighbor(new Edge(from, to, cost));
			}

			out.append(String.format("Network %d%nThe bandwidth is %d.%n%n", network++, solve(graph, S, D)));
		}

		System.out.print(out);
	}

	private static final int solve(List<Vertex> graph, int start, int destination) {
		int bandwidthSum = 0;

		while (true) {
			Queue<Integer> Q = new LinkedList<Integer>();
			Edge[] pred = new Edge[graph.size()];
			Arrays.fill(pred, null);

			Q.offer(start);

			while (!Q.isEmpty()) {
				int currentNode = Q.poll();

				for (Edge neighbor : graph.get(currentNode).getNeighbors()) {
					if (pred[neighbor.destination] == null && neighbor.destination != start && neighbor.capacity > neighbor.flow) {
						pred[neighbor.destination] = neighbor;
						Q.offer(neighbor.destination);
					}
				}
			}	

			if (pred[destination] == null) break;

			int deltaFlow = INF;
			for (Edge e = pred[destination]; e != null; e = pred[e.start]) {
				deltaFlow = Math.min(deltaFlow, e.capacity - e.flow);
			}

			for (Edge e = pred[destination]; e != null; e = pred[e.start]) {
				e.flow += deltaFlow;
			}

			bandwidthSum += deltaFlow;
		}

		return bandwidthSum;
	}

	private static class Vertex {
		public final int node;

		private List<Edge> neighbors = new ArrayList<Edge>();

		public Vertex(int n) {
			this.node = n;
		}

		public List<Edge> getNeighbors() {
			return Collections.unmodifiableList(this.neighbors);
		}

		public void addNeighbor(Edge e) {
			this.neighbors.add(e);
		}
	}

	private static class Edge {
		public final int start;
		public final int destination;
		public final int capacity;
		public int flow;

		public Edge(int s, int d, int c) {
			this.start = s;
			this.destination = d;
			this.capacity = c;
			this.flow = 0;
		}
	}
}