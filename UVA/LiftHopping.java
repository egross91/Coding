import java.io.*;
import java.util.*;
// AC
class Main {
    private static int N;
    private static int K;

    private static final int MAXN = 100;
    private static final int INF = 2_000_000;
    private static int[] elevatorCost = new int[MAXN];

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		
        String line = " ";

        while ((line = in.readLine()) != null && !line.equals("")) {
            String[] lineData = line.split(" ");
            N = Integer.parseInt(lineData[0]);
            K = Integer.parseInt(lineData[1]);

            lineData = in.readLine().split(" ");
            for (int i = 0; i < N; ++i) elevatorCost[i] = Integer.parseInt(lineData[i]);

            List<Vertex> graph = new ArrayList<Vertex>();

            for (int i = 0; i < MAXN; ++i) graph.add(new Vertex(i));

            for (int i = 0; i < N; ++i) {
                List<Integer> floors = new ArrayList<Integer>();
                int elevator = i+1;
                lineData = in.readLine().split(" ");

                for (int j = 0; j < lineData.length; ++j) {
                    int floor = Integer.parseInt(lineData[j]);
                    floors.add(floor);
                }

                // Build graph for the floor based on the elevators supplied.
                for (int j = 0; j < floors.size(); ++j) {
                    for (int k = 0; k < floors.size(); ++k) {
                        // bi-directional
                        if (j != k) {
                            int to = floors.get(j);
                            int from = floors.get(k);

                            graph.get(to).addNeighbor(new Edge(from, elevatorCost[i], elevator));
                            graph.get(from).addNeighbor(new Edge(to, elevatorCost[i], elevator));
                        }
                    }
                }
            }

            out.append(String.format("%s%n", solve(graph)));
        }
        
        System.out.print(out);
    }


    private static String solve(List<Vertex> graph) {
        PriorityQueue<State> heap = new PriorityQueue<State>(100, new Comparator<State>() {
            @Override
            public int compare(State s1, State s2) {
                return s2.costToVertex - s1.costToVertex;
            }
        });

        graph.get(0).setTotalCost(0);
        heap.offer(new State(0, -1, -1));

        while (!heap.isEmpty()) {
            State state = heap.poll();
            Vertex currentNode = graph.get(state.node);
            int costToHere = currentNode.getTotalCost();

            List<Edge> neighbors = currentNode.getNeighbors();
            for (int i = 0; i < neighbors.size(); ++i) {
                Edge neighbor = neighbors.get(i);
                Vertex neighborVertex = graph.get(neighbor.destination);
                int edgeCost = (neighbor.cost * (Math.abs(currentNode.node - neighbor.destination)));
                int currentCostToDestination = neighborVertex.getTotalCost();
                int candidateCostToDestination = costToHere + edgeCost;

                if (state.cameFromElevator != neighbor.elevator && state.cameFromElevator != -1) {
                    candidateCostToDestination += 60;
                }

                if (currentCostToDestination > candidateCostToDestination) {
                    neighborVertex.setTotalCost(candidateCostToDestination);
                    heap.offer(new State(neighbor.destination, neighbor.elevator, candidateCostToDestination));
                }
            }
        }

        if (graph.get(K).getTotalCost() == INF) {
            return "IMPOSSIBLE";
        }

        return String.valueOf(graph.get(K).getTotalCost());
    }
	

    private static class Vertex {
        public final int node;

        private List<Edge> neighbors = new ArrayList<Edge>();
        private int costTo;

        public Vertex() {
            this(0, INF);
        }

        public Vertex(int n) {
            this(n, INF);
        }

        public Vertex(int n, int c) {
            this.node = n;
            this.costTo = c;
        }

        public List<Edge> getNeighbors() {
            return this.neighbors;
        }

        public void addNeighbor(Edge e) {
            this.neighbors.add(e);
        }

        public int getTotalCost() {
            return this.costTo;
        }

        public void setTotalCost(int tc) {
            this.costTo = tc;
        }
    }

    private static class Edge {
        public final int destination;
        public final int cost;
        public final int elevator;

        private Edge() { this.destination = -1; this.cost = -1; this.elevator = -1; }

        public Edge(int d, int c, int e) {
            this.destination = d;
            this.cost = c;
            this.elevator = e;
        }
    }

    private static class State {
        public final int node;
        public final int cameFromElevator;
        public final int costToVertex;

        public State(int n, int cfe, int ctv) {
            this.node = n;
            this.cameFromElevator = cfe;
            this.costToVertex = ctv;
        }
    }
}