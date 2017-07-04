import java.io.*;
import java.util.*;
// AC
class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
        String[] data = null;
		
        while (true) {
            data = null; data = in.readLine().split(" ");

            int N = Integer.parseInt(data[0]);
            int D = Integer.parseInt(data[1]);
            int R = Integer.parseInt(data[2]);

            if (N == 0 && D == 0 && R == 0) { break; }

            int[] days   = new int[N];
            int[] nights = new int[N];

            data = null; data = in.readLine().split(" ");
            for (int i = 0; i < N; ++i) { days[i] = Integer.parseInt(data[i]); }

            data = null; data = in.readLine().split(" ");
            for (int i = 0; i < N; ++i) { nights[i] = Integer.parseInt(data[i]); }

            Arrays.sort(days);
            Arrays.sort(nights);

            out.append(String.format("%d%n", solve(days, nights, N, D, R)));
        }

        System.out.print(out);
	}

    private static long solve(int[] days, int[] nights, int N, int D, int R) {
        int sum = 0;

        for (int i = 0; i < N; ++i) {
            sum += Math.max(days[i] + nights[N-i-1] - D, 0);
        }

        long product = sum * R;

        return product; 
    }
}