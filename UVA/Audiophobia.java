import java.io.*;
import java.util.*;
// AC
class Main {
    private static int C, S, Q;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
        // PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("Audiophobia.out")));
		
        int caze = 1;

        while (true) {
            String[] lineData = in.readLine().split(" ");

            C = Integer.parseInt(lineData[0]);
            S = Integer.parseInt(lineData[1]);
            Q = Integer.parseInt(lineData[2]);

            if (C == 0 && S == 0 && Q == 0) break;

            List<Edge> edges   = new ArrayList<Edge>();
            List<Edge> queries = new ArrayList<Edge>();

            // Build edge list.
            for (int i = 0; i < S; ++i) {
                lineData = null; lineData = in.readLine().split(" ");

                int from     = Integer.parseInt(lineData[0]);
                int to       = Integer.parseInt(lineData[1]);
                int decibels = Integer.parseInt(lineData[2]);

                edges.add(new Edge(from, to, decibels));
            }

            // Build queries - the @decibels for these doesn't matter.
            for (int i = 0; i < Q; ++i) {
                lineData = null;  lineData = in.readLine().split(" ");

                int u = Integer.parseInt(lineData[0]);
                int v = Integer.parseInt(lineData[1]);

                queries.add(new Edge(u, v, Integer.MAX_VALUE));
            }

            edges.sort(Edge.comparator());

            out.append(solve(edges, queries, caze++));
        }

        System.out.println(out.toString().trim());
        // writer.print(out);
        // writer.close();
	}

    private static String solve(List<Edge> edges, List<Edge> queries, int caze) {
        StringBuilder queryResults = new StringBuilder();

        for (Edge query : queries) {
            queryResults.append(String.format("%s%n", findMinDecibelsForPath(edges, query)));
        }

        return String.format("Case #%d%n%s%n", caze, queryResults);
    }

    private static String findMinDecibelsForPath(List<Edge> edgeList, Edge path) {
        UnionFind uf = new UnionFind(C);

        for (Edge edge : edgeList) {
            if (uf.isPathFrom(path.from, path.to)) {
                return String.valueOf(uf.getMaxDecibels());
            }

            uf.merge(edge);
        }

        if (uf.isPathFrom(path.from, path.to)) {
            return String.valueOf(uf.getMaxDecibels());
        }

        return "no path";
    }

    private static class UnionFind {
        private int[] parents;
        private int maxWeight;

        public UnionFind(int N) {
            this.parents = new int[N+1];
        }

        private int find(int x) {
            if (this.parents[x] == 0) {
                return x;
            }

            return (this.parents[x] = find(this.parents[x]));
        }

        public boolean merge(Edge e) {
            int pX = find(e.from);
            int pY = find(e.to);

            if (pX == pY) {
                return false;
            }

            this.parents[pY] = pX;

            this.maxWeight = Math.max(this.maxWeight, e.decibels);

            return true;
        }

        public int getMaxDecibels() {
            return this.maxWeight;
        }

        public boolean isPathFrom(int x, int y) {
            int pX = find(x);
            int pY = find(y);

            return (pX == pY && pX != 0);
        }
    }

    private static class Edge {
        public final int from;
        public final int to;
        public final int decibels;

        public Edge(int f, int t, int d) {
            this.from = f;
            this.to = t;
            this.decibels = d;
        }

        public static Comparator<Edge> comparator() {
            return new Comparator<Edge>() {
                @Override
                public int compare(Edge e1, Edge e2) {
                    // Desc. sort.
                    return e1.decibels - e2.decibels;
                }
            };
        }
    }
}