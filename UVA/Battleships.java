import java.io.*;
import java.util.*;
// AC
class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		
        int T = Integer.parseInt(in.readLine());

        for (int test = 1; test <= T; ++test) {
            int N = Integer.parseInt(in.readLine());
            char[][] graph = new char[N][];

            for (int i = 0; i < N; ++i) { graph[i] = in.readLine().toCharArray(); }

            // for (char[] row : graph) {
            //     System.out.println(Arrays.toString(row));
            // }

            out.append(String.format("Case %d: %d%n", test, solve(graph, N)));
        }

        System.out.print(out);
	}

    private static int solve(char[][] graph, int N) {
        int count = 0;

        for (int r = 0; r < N; ++r) {
            for (int c = 0; c < N; ++c) {
                if (graph[r][c] != '.' && search(graph, r, c)) {
                    // System.out.printf("search[%d][%d]%n", r, c);
                    ++count;
                }
            }
        }

        return count;
    }

    private static boolean search(char[][] graph, int startRow, int startCol) {
        boolean isAlive = false;

        if (graph[startRow][startCol] == 'x') { isAlive = true; }

        // Mark as visited initially.
        graph[startRow][startCol] = '.';
        
        // Search vertically.
        for (int r = startRow+1; r < graph.length && graph[r][startCol] != '.'; ++r) {
            if (graph[r][startCol] == 'x') isAlive = true;

            graph[r][startCol] = '.';
        }

        for (int r = startRow-1; r >= 0 && graph[r][startCol] != '.'; --r) {
            if (graph[r][startCol] == 'x') isAlive = true;

            graph[r][startCol] = '.';
        }

        // Search horizontally.
        for (int c = startCol+1; c < graph.length && graph[startRow][c] != '.'; ++c) {
            if (graph[startRow][c] == 'x') isAlive = true;

            graph[startRow][c] = '.';
        }

        for (int c = startCol-1; c >= 0 && graph[startRow][c] != '.'; --c) {
            if (graph[startRow][c] == 'x') isAlive = true;

            graph[startRow][c] = '.';
        }

        return isAlive;
    }
}