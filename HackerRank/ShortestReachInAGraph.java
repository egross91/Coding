import java.io.*;
import java.util.*;
// AC
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        
        int Q = Integer.parseInt(in.readLine());
        
        while (Q-- > 0) {
            String[] lineData = in.readLine().split(" ");
            int N = Integer.parseInt(lineData[0]);
            int M = Integer.parseInt(lineData[1]);
            
            int[] distances = new int[N+1];
            Arrays.fill(distances, -1);
            
            Edge[] graph = new Edge[N+1];
            for (int i = 1; i <= N; ++i) { graph[i] = new Edge(i); }
            
            for (int i = 0; i < M; ++i) {
                lineData = null; lineData = in.readLine().split(" ");
                int u = Integer.parseInt(lineData[0]);
                int v = Integer.parseInt(lineData[1]);
                
                graph[u].addNeighbor(v);
                graph[v].addNeighbor(u);
            }
            
            int start = Integer.parseInt(in.readLine());
            
            out.append(String.format("%s%n", solve(start, graph, distances)));
        }
        
        System.out.print(out);
    }
    
    private static String solve(int start, Edge[] graph, int[] distances) {
        Queue<State> Q = new LinkedList<State>();

        Q.offer(new State(start, 0));

        while (!Q.isEmpty()) {
            State current = Q.poll();
            int newDistance = current.distance + 6;

            for (Integer neighbor : graph[current.node].getNeighbors()) {
                if (distances[neighbor] == -1 || distances[neighbor] > newDistance) {
                    distances[neighbor] = newDistance;
                    Q.offer(new State(neighbor, newDistance));
                }
            }
        }

        return formatDistances(start, distances);
    }

    private static String formatDistances(int start, int[] distances) {
        StringBuilder builder = new StringBuilder();

        for (int i = 1; i < distances.length; ++i) {
            if (i != start) {
                builder.append(String.format("%d ", distances[i]));
            }
        }

        return builder.toString().trim();
    }
    
    private static class Edge {
        private final int node;
        private List<Integer> neighbors;
        
        public Edge(int n) {
            this.node = n;
            this.neighbors = new ArrayList<Integer>();
        }
        
        public void addNeighbor(int v) {
            this.neighbors.add(v);
        }
        
        public List<Integer> getNeighbors() {
            return this.neighbors;
        }
    }
    
    private static class State {
        private final int node, distance;
        
        public State(int n, int d) {
            this.node = n;
            this.distance = d;
        }
    }
}