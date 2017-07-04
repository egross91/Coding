/*
ID: eric.bg1
LANG: JAVA
TASK: subset
*/
import java.util.*;
import java.io.*;

class subset {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("subset.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
		StringBuilder builder = new StringBuilder();

		int N = Integer.parseInt(in.readLine());
		builder.append(solve(N));

		out.println(builder);
		out.close();
		System.exit(0);
	}

	private static long solve(final int N) {
		final int sumOfAll = (N * (N + 1)) >> 1;
		final int goalSum = (N * (N + 1)) >> 2;
		
		if ((sumOfAll & 0x1) == 1) return 0;

		long[] dp = new long[goalSum+1];
		dp[0] = 1;

		for (int i = 0; i <= N; ++i) {
			for (int j = goalSum - i; j >= 0; --j) {
				dp[j+i] += dp[j];
			}
		}
		
		return dp[goalSum] >> 2;
	}
}