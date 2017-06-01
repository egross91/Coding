import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
// AC
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int queries = Integer.parseInt(in.readLine());
        
        while (queries-- > 0) {
            String[] lineData = in.readLine().split(" ");
            
            int numCities = Integer.parseInt(lineData[0]);
            int numRoads = Integer.parseInt(lineData[1]);
            int libCost = Integer.parseInt(lineData[2]);
            int roadCost = Integer.parseInt(lineData[3]);
            
            List<Edge> edges = new ArrayList<Edge>();
            
            for (int i = 0; i < numRoads; ++i) {
                lineData = null; lineData = in.readLine().split(" ");
                
                edges.add(new Edge(Integer.parseInt(lineData[0]), Integer.parseInt(lineData[1])));
            }
            
            System.out.println(solve(numCities, numRoads, libCost, roadCost, edges));
        }
    }
    
    private static long solve(int cities, long roads, long libCost, long roadCost, List<Edge> edges) {
        UnionFind uf = new UnionFind(cities);
        long costOfLibsWithoutRoads = (libCost * cities);
        
        for (Edge e : edges) {
            uf.merge(e);
        }
        
        long minCost = (uf.getNumberOfSubgraphs() * libCost) + (uf.getNumberOfEdges() * roadCost);
        
        return Math.min(costOfLibsWithoutRoads, minCost);
    }
    
    private static class Edge {
        public final int to, from;
        
        public Edge(int t, int f) {
            this.to = t;
            this.from = f;
        }
    }
    
    private static class UnionFind {
        private int[] parents;
        private long components, edges;
        
        public UnionFind(int n) {
            this.parents = new int[n+1];
            this.components = (long)n;
            this.edges = 0;
        }
        
        public void merge(Edge e) {
            int pX = find(e.to);
            int pY = find(e.from);
            
            if (pX != pY) {
                this.components--;
                this.edges++;
                this.parents[pX] = pY;
            }
        }
        
        private int find(int x) {
            if (this.parents[x] == 0) {
                return x;
            }
            
            return this.parents[x] = find(this.parents[x]);
        }
        
        public long getNumberOfSubgraphs() {
            return this.components;
        }
        
        public long getNumberOfEdges() {
            return this.edges;
        }
    }
}
