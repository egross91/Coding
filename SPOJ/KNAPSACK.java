import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String line = null;

        while ((line = in.readLine()) != null && !line.equals("")) {
            String[] lineData = line.split(" ");

            int capacity = Integer.parseInt(lineData[0]);
            int items    = Integer.parseInt(lineData[1]);

            int[][] dp = new int[items+1][capacity+1];

            for (int i = 1; i <= items; ++i) {
                lineData = null; lineData = in.readLine().split(" ");

                Item item = new Item(Integer.parseInt(lineData[0]), Integer.parseInt(lineData[1]));

                for (int j = 1; j <= capacity; ++j) {
                    if (j - item.capacity >= 0) {
                        dp[i][j] = Math.max(dp[i-1][j], Math.max(dp[i-1][j-item.capacity] + item.value, dp[i][j-1]));
                    } else {
                        dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                    }
                }

                // System.out.println(Arrays.toString(dp[i]));
            }

            out.append(String.format("%d\n", dp[items][capacity]));
        }

        System.out.print(out);
	}

    private static class Item {
        public final int capacity, value;

        public Item(int c, int v) {
            this.capacity = c;
            this.value = v;
        }
    }
}