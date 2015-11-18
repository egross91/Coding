import java.util.*;
import java.io.*;
import java.math.*;

@SuppressWarnings("unchecked")
class Main {
	public static void main(String[] args) throws FileNotFoundException {
        new Solver().run();
    }

    private static class Solver {
    	private int N, M;

    	public void run() throws FileNotFoundException {
    		// Scanner in = new Scanner(new File("DarkRoads.in"));
    		Scanner in = new Scanner(System.in);
        
	        while (true) {
	        	N = in.nextInt();
	        	M = in.nextInt();

	        	// Terminating condition.
	        	if (M == 0 && N == 0) break;

	        	int from, to, cost;
	        	List<Edge> edgeList = new ArrayList<Edge>();
	        	for (int i = 0; i < M; ++i) {
	        		from = in.nextInt();
	        		to   = in.nextInt();
	        		cost = in.nextInt();

	        		edgeList.add(new Edge(from, to, cost));
	        	}

	        	Collections.sort(edgeList, new Comparator<Edge>() {
	        		@Override
	        		public int compare(final Edge e1, final Edge e2) {
	        			if (e1.cost < e2.cost)
	        				return -1;
	        			else if (e1.cost > e2.cost)
	        				return 1;

	        			return 0;
	        		}
	        	});

	        	solve(edgeList);
	        }
    	}

    	private void solve(List<Edge> edges) {
            UnionFind UF = new UnionFind(N);
            int shortestCost, totalCost;
            shortestCost = totalCost = 0;

            for (Edge edge : edges) {
                if (UF.getNumSubgraphs() != 1) {
                    if (UF.merge(edge.from, edge.to)) {
                        shortestCost += edge.cost;
                    }
                }

                totalCost += edge.cost;
            }

            System.out.printf("%d\n", (totalCost - shortestCost));
        }
    }

    private static class Edge {
    	public final int to, from, cost;

    	public Edge(int f, int t, int c) {
    		this.from = f;
    		this.to   = t;
    		this.cost = c;
    	}
    }

    private static class UnionFind {
        private int subgraphs;
        private int[] parents;

        public UnionFind(int n) {
            this.subgraphs = n;

            parents = new int[n];
            for (int i = 0; i < n; ++i)
                parents[i] = -1;
        }

        public int find(int x) {
            if (parents[x] != -1)
                return parents[x] = find(parents[x]);

            return x;
        }

        public boolean merge(int x, int y) {
            int repX = find(x);
            int repY = find(y);

            if (repX != repY) {
                parents[repX] = repY;
                --subgraphs;
                return true;
            }

            return false;
        }

        public int getNumSubgraphs() {
            return this.subgraphs;
        }
    }
}
