import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		
        int test = 1;

        while (true) {
            int n = Integer.parseInt(in.readLine().trim());

            if (n == 0) { break; }

            long[][] graph = new long[n][3];
            long[][] bests = new long[n][3];

            // Build graph.
            for (int i = 0; i < n; ++i) {
                String[] lineData = in.readLine().trim().split(" ");

                graph[i][0] = Long.parseLong(lineData[0]);
                graph[i][1] = Long.parseLong(lineData[1]);
                graph[i][2] = Long.parseLong(lineData[2]);
            }

            bests[0][0] = 1_000_001;
            bests[0][1] = graph[0][1]; // Math.min(graph[0][1], graph[0][0] + graph[0][1]);
            bests[0][2] = Math.min(1_000_001, bests[0][1] + graph[0][2]); // Math.min(Integer.MAX_VALUE, bests[0][1] + graph[0][2]);

            for (int i = 1; i < n; ++i) {
                // System.out.println(Arrays.toString(bests[i-1]));

                bests[i][0] = Math.min(bests[i-1][0] + graph[i][0], bests[i-1][1] + graph[i][0]);
                bests[i][1] = Math.min(bests[i-1][0] + graph[i][1], Math.min(bests[i-1][1] + graph[i][1], Math.min(bests[i-1][2] + graph[i][1], bests[i][0] + graph[i][1])));
                bests[i][2] = Math.min(bests[i-1][1] + graph[i][2], Math.min(bests[i-1][2] + graph[i][2], bests[i][1] + graph[i][2]));
            }

            // System.out.println(Arrays.toString(bests[n-1]));

            out.append(String.format("%d. %d\n", test++, bests[n-1][1]));
        }

        System.out.print(out);
	}
}