import java.io.*;
import java.util.*;
// AC
class Main {
    private static int[][] dp = new int[105][];
    private static int MOD    = 1_000_000;

    static {
        for (int i = 0; i < 105; ++i) {
            dp[i] = new int[105];
        }

        Arrays.fill(dp[0], 1);

        for (int r = 1; r < 101; ++r) {
            for (int c = 1; c < 101; ++c) {
                dp[r][c] += (dp[r-1][c] + dp[r][c-1]) % MOD;
            }
        }
    }

    public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		
        while (true) {
            String[] lineData = in.readLine().split(" ");
            int N = Integer.parseInt(lineData[0]);
            int K = Integer.parseInt(lineData[1]);
            
            if (N == 0 && K == 0) { break; }

            out.append(String.format("%d%n", solve(N, K)));
        }

        System.out.print(out);
	}

    private static long solve(int N, int K) {
        return dp[N][K];
    }
}