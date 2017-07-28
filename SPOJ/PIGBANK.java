import java.io.*;
import java.util.*;

class Main {
    private static final int MAX = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		
        int tests = Integer.parseInt(in.readLine());

        while (tests-- > 0) {
            String[] lineData = in.readLine().trim().split(" ");
            int capacity = Integer.parseInt(lineData[1]) - Integer.parseInt(lineData[0]);
            int n = Integer.parseInt(in.readLine().trim());
            int[] mins = new int[capacity+1];

            Arrays.fill(mins, Main.MAX);
            mins[0] = 0;

            for (int i = 0; i < n; ++i) {
                lineData = null;
                lineData = in.readLine().trim().split(" ");

                int value = Integer.parseInt(lineData[0]);
                int weight = Integer.parseInt(lineData[1]);

                for (int j = 1; j <= capacity; ++j) {
                    if (weight <= j) {
                        mins[j] = Math.min(mins[j], mins[j-weight] + value);
                    }
                }
            }

            if (mins[capacity] == Main.MAX) {
                out.append("This is impossible.\n");
            } else {
                out.append(String.format("The minimum amount of money in the piggy-bank is %d.\n", mins[capacity]));
            }
        }

        System.out.println(out);
	}
}