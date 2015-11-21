import java.util.*;
import java.io.*;
import java.math.*;

class Main {
	public static void main(String[] args) throws FileNotFoundException {
        new Solver().run();
    }
}

class Solver {
	private int N, M;

	public void run() throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
        // Scanner in = new Scanner(new File("HeavyCycleEdges.in"));
        StringBuilder builder = new StringBuilder();

        while (true) {
        	N = in.nextInt();
        	M = in.nextInt();

        	if (N == 0 && M == 0) break;

        	builder.append(solve(N, in));
        	builder.append("\n");
        }

        System.out.print(builder);
	}

	private String solve(final int n, Scanner in) throws FileNotFoundException {
    	UnionFind UF           = new UnionFind(n);
    	StringBuilder builder  = new StringBuilder();
    	List<Edge> edges       = new ArrayList<Edge>();
    	List<Integer> heaviest = new ArrayList<Integer>();
    	int from, to, cost;

    	for (int i = 0; i < M; ++i) {
    		from = in.nextInt();
    		to   = in.nextInt();
    		cost = in.nextInt();

    		edges.add(new Edge(from, to, cost));
    	}

    	Collections.sort(edges, new Comparator<Edge>() {
    		@Override
			public int compare(final Edge e1, final Edge e2) {
				if (e1.cost < e2.cost)
					return -1;
				else if (e1.cost > e2.cost)
					return 1;

				return 0;
			}
		});

		for (Edge edge : edges) {
			if (!UF.merge(edge.from, edge.to)) {
				heaviest.add(edge.cost);
			}
		}

    	if (heaviest.size() == 0)
    		builder.append("forest");
    	else {
    		Collections.sort(heaviest);

    		builder.append(heaviest.get(0));
    		for (int i = 1; i < heaviest.size(); ++i)
    			builder.append(String.format(" %d", heaviest.get(i)));
    	}

    	return builder.toString();
    }
}

class Edge {
	public final int from, to, cost;

	public Edge(int f, int t, int c) {
		this.from = f;
		this.to   = t;
		this.cost = c;
	}
}

class UnionFind {
	private int[] parents;
	private int subgraphs;

	public UnionFind(int n) {
		this.subgraphs = n;
		parents = new int[n];

		Arrays.fill(parents, -1);
	}

	public int find(int x) {
		if (parents[x] != -1)
			return parents[x] = find(parents[x]);

		return x;
	}

	public boolean merge(int x, int y) {
		int pX = find(x);
		int pY = find(y);

		if (pX == pY) 
			return false;

		parents[pY] = pX;
		subgraphs--;
		return true;
	}

	public int getNumberSubgraphs() {
		return this.subgraphs;
	}
}
