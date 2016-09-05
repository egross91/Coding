import java.io.*;
import java.util.*;
// AC
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		String[] lineData = in.readLine().split(" ");
		int N = Integer.parseInt(lineData[0]);
		int M = Integer.parseInt(lineData[1]);
		int wallCount = 0;
		List<String> graph = new ArrayList<String>();
		int[] colCount = new int[M];
		int[] rowCount = new int[N];

		for (int i = 0; i < N; ++i) {
			String line = in.readLine();
			graph.add(line);

			for (int k = 0; k < M; ++k) {
				char c = line.charAt(k);
				if (c == '*') {
					++wallCount;
					colCount[k]++;
					rowCount[i]++;
				}
			}
		}

		for (int y = 0; y < N; ++y) {
			for (int x = 0; x < M; ++x) {
				int count = colCount[x] + rowCount[y];

				if (graph.get(y).charAt(x) == '*')
					--count;

				if (count == wallCount) {
					System.out.printf("YES%n%d %d", (y+1), (x+1));
					System.exit(0);
				}
			}
		}

		System.out.println("NO");
	}
}